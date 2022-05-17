package models.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import models.conexion.Conexion;
import models.dto.AsignadoADTO;

public class AsignadoADAO {

	// método que inserta datos en la tabla asignado_a
	public void registrarAsignadoA(AsignadoADTO miAsignadoA) {

		Conexion conexion = new Conexion();

		try {
			Statement st = conexion.obtenerConexion().createStatement();
			String query = "INSERT INTO asignado_a VALUES(" + "\"" + miAsignadoA.getCientificoAsignado() + "\", " + "\""
					+ miAsignadoA.getProyectoAsignado() + "\"); ";

			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "El nuevo asignadoA creado exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(query);
			st.close();
			conexion.desconectar();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al crear el nuevo asignadoA");
		}
	}

	// método que busca el asignadoA por dni de científico y id de proyecto
	public AsignadoADTO buscarAsignadoA(String dniCientificoAsignado, String proyectoAsignado) {

		Conexion conexion = new Conexion();

		AsignadoADTO asignadoADTO = new AsignadoADTO();
		boolean existe = false;

		try {
			String query = "SELECT * FROM  asignado_a where cientifico = ? AND proyecto = ? ";
			PreparedStatement consulta = conexion.obtenerConexion().prepareStatement(query);
			consulta.setString(1, dniCientificoAsignado);
			consulta.setString(2, proyectoAsignado);
			ResultSet res = consulta.executeQuery();

			while (res.next()) {
				existe = true;
				asignadoADTO.setCientificoAsignado(res.getString("cientifico"));
				asignadoADTO.setProyectoAsignado(res.getString("proyecto"));
			}
			res.close();
			conexion.desconectar();
			System.out.println(query);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al conectarse a la BBDD");
		}
		if (existe) {
			return asignadoADTO;
		} else {
			return null;
		}
	}

	// método que modifica el asignadoA que ha introducido el usuario
	public void modificarAsignadoA(AsignadoADTO miAsignadoA) {

		Conexion conexion = new Conexion();

		try {
			String consulta = "UPDATE asignado_a SET cientifico= ? , proyecto = ? WHERE cientifico = ? AND proyecto = ?";
			PreparedStatement statement = conexion.obtenerConexion().prepareStatement(consulta);

			statement.setString(1, miAsignadoA.getCientificoAsignado());
			statement.setString(2, miAsignadoA.getProyectoAsignado());
			statement.setString(3, miAsignadoA.getCientificoAsignado());
			statement.setString(4, miAsignadoA.getProyectoAsignado());

			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(consulta);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error, no se conectó con la BD");
		}
	}

	// método que elimina el asignadoA de la tabla asignado_a
	public void eliminarAsignadoA(String cientificoAsignado, String proyectoAsignado) {

		Conexion conexion = new Conexion();

		try {
			String sql = "DELETE FROM asignado_a WHERE cientifico ='" + cientificoAsignado + "' AND proyecto ='"
					+ proyectoAsignado + "'";

			Statement st = conexion.obtenerConexion().createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println(sql);
			st.close();
			conexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se ha podido eliminar");
		}
	}
}
