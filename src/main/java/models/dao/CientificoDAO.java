package models.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import models.conexion.Conexion;
import models.dto.CientificoDTO;

public class CientificoDAO {

	// método que inserta datos en la tabla cientificos
	public void registrarCientifico(CientificoDTO miCientifico) {

		Conexion conexion = new Conexion();

		try {
			Statement st = conexion.obtenerConexion().createStatement();
			// declaramos la consulta para crear el nuevo científico
			String Query = "INSERT INTO cientificos VALUES(" + "\"" + miCientifico.getDni() + "\", " + "\""
					+ miCientifico.getNombreApellidos() + "\");";

			st.executeUpdate(Query);
			JOptionPane.showMessageDialog(null, "El científico creado exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(Query);
			st.close();
			conexion.desconectar();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al crear el nuevo científico");
		}
	}

	// método que busca el científico por idCientifico
	public CientificoDTO buscarCientifico(String dniCientifico) {
		
		Conexion conexion = new Conexion();
		CientificoDTO cientifico = new CientificoDTO();
		boolean existe = false;
		
		try {
			// declaramos la consulta para obtener el científico buscado por id
			String query = "SELECT * FROM  cientificos where dniCientifico = ? ;";
			PreparedStatement consulta = conexion.obtenerConexion().prepareStatement(query);
			// pasamos id del científico a la consulta
			consulta.setString(1, dniCientifico);
			// guardamos el objeto buscado
			ResultSet res = consulta.executeQuery();
			// si el científico existe en la BBDD
			while (res.next()) {
				existe = true;
				// asignamos al objeto Cientifico valores obtenidos de la BBDD
				cientifico.setDni(res.getString("dniCientifico"));
				cientifico.setNombreApellidos(res.getString("nombreApellidosCientifico"));
			}
			// cerramos las conexiones abiertas
			res.close();
			conexion.desconectar();
			System.out.println(query);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al conectarse con la BBDD");
		}
		// retornamos el proyecto obtenido en el caso si se ha encontrado en la BBDD
		if (existe) {
			return cientifico;
		} else {
			return null;
		}
	}

	// método que modifica el científico que ha introducido el usuario
	public void modificarCientifico(CientificoDTO miCientifico) {
		
		Conexion conexion = new Conexion();
		
		try {
			// declaramos la consulta para modificar los valores del científico existente
			String query = "UPDATE cientificos SET dniCientifico = ? , nombreApellidosCientifico = ? WHERE dniCientifico = ?;";
			PreparedStatement statement = conexion.obtenerConexion().prepareStatement(query);

			// asignamos valores a las variables de la consulta
			statement.setString(1, miCientifico.getDni());
			statement.setString(2, miCientifico.getNombreApellidos());
			statement.setString(3, miCientifico.getDni());

			statement.executeUpdate();

			JOptionPane.showMessageDialog(null, "El científico modificado correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(query);

		} catch (SQLException ex) {
			// mostramos los avisos por consola y al user
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al conectarse a la BBDD");
		}
	}

	// método que elimina el científico de la tabla cientificos
	public void eliminarCientifico(String dniCientifico) {
		
		Conexion conexion = new Conexion();

		try {
			// declaramos la consulta para eliminar el científico buscado por id
			String query = "DELETE FROM cientificos WHERE dniCientifico ='" + dniCientifico + "'";

			Statement st = conexion.obtenerConexion().createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "El científico eliminado correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(query);
			st.close();
			conexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al eliminar el científico");
		}
	}

}
