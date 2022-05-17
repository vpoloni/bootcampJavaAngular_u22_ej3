package models.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import models.conexion.Conexion;
import models.dto.ProyectoDTO;

public class ProyectoDAO {

	// método que inserta datos en la tabla proyectos
	public void registrarProyecto(ProyectoDTO miProyecto) {

		Conexion conexion = new Conexion();

		try {
			Statement st = conexion.obtenerConexion().createStatement();
			// declaramos la consulta para crear el nuevo proyecto
			String query = "INSERT INTO proyectos VALUES(" + "\"" + miProyecto.getIdProyecto() + "\", " + "\""
					+ miProyecto.getNombreProyecto() + "\", " + "\"" + miProyecto.getHorasProyecto() + "\");";

			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "El proyecto creado exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(query);
			st.close();
			conexion.desconectar();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al crear el nuevo proyecto");
		}
	}

	// método que busca el proyecto por idProyecto
	public ProyectoDTO buscarProyecto(String idProyecto) {

		Conexion conexion = new Conexion();
		ProyectoDTO proyectoDTO = new ProyectoDTO();

		boolean existe = false;

		try {
			// declaramos la consulta para obtener el proyecto buscado por id
			String query = "SELECT * FROM proyectos where idProyecto = ? ";
			PreparedStatement consulta = conexion.obtenerConexion().prepareStatement(query);
			// pasamos id del proyecto a la consulta
			consulta.setString(1, idProyecto);
			// guardamos el objeto buscado
			ResultSet res = consulta.executeQuery();
			// si el proyecto existe en la BBDD
			while (res.next()) {
				existe = true;
				// asignamos al objeto Proyecto valores obtenidos de la BBDD
				proyectoDTO.setIdProyecto(res.getString("idProyecto"));
				proyectoDTO.setNombreProyecto(res.getString("nombreProyecto"));
				proyectoDTO.setHorasProyecto(Integer.parseInt(res.getString("horasProyecto")));
			}
			// cerramos las conexiones abiertas
			res.close();
			conexion.desconectar();
			System.out.println(query);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al conectarse a la BBDD");
		}
		// retornamos el proyecto obtenido en el caso si se ha encontrado en la BBDD
		if (existe) {
			return proyectoDTO;
		} else {
			return null;
		}
	}

	// método que modifica el proyecto que ha introducido el usuario
	public void modificarProyecto(ProyectoDTO miProyecto) {
		
		Conexion conexion = new Conexion();
		
		try {
			// declaramos la consulta para modificar los valores del proyecto existente
			String query = "UPDATE proyectos SET idProyecto= ? , nombreProyecto = ?, horasProyecto = ? WHERE idProyecto = ?";
			PreparedStatement statement = conexion.obtenerConexion().prepareStatement(query);

			// asignamos valores a las variables de la consulta
			statement.setString(1, miProyecto.getIdProyecto());
			statement.setString(2, miProyecto.getNombreProyecto());
			statement.setInt(3, miProyecto.getHorasProyecto());
			statement.setString(4, miProyecto.getIdProyecto());

			statement.executeUpdate();

			JOptionPane.showMessageDialog(null, "El proyecto modificado correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(query);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al conectarse a la BBDD");
		}
	}

	// método que elimina el proyecto de la tabla proyectos
	public void eliminarProyecto(String idProyecto) {
		
		Conexion conexion = new Conexion();

		try {
			// declaramos la consulta para eliminar el proyecto buscado por id
			String query = "DELETE FROM proyectos WHERE idProyecto ='" + idProyecto + "'";
			
			Statement st = conexion.obtenerConexion().createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "El proyecto eliminado correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(query);
			st.close();
			conexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al eliminar el proyecto");
		}
	}
}
