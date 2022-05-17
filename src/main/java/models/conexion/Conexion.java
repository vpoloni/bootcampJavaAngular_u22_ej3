package models.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	static String bd = "ud22_ej3";
	static String login = "remote";
	static String password = "Reus_2022";
	static String url = "jdbc:mysql://192.168.187.236:3306/" + bd + "?useTimezone=true&serverTimezone=UTC";

	Connection conexion = null;

	public Conexion() {
		try {

			conexion = DriverManager.getConnection(url, login, password);
			if (conexion != null) {
				System.out.println("Conexión existosa");

			}
		} catch (SQLException e) {
			System.out.println("Error al conectarse a la base de datos");
			System.out.println(e.getMessage());
		}
	}

	// método que retorna la conexión
	public Connection obtenerConexion() {
		return conexion;
	}

	public void desconectar() {
		conexion = null;
	}
}