package models.service;

import javax.swing.JOptionPane;
import controllers.ProyectoController;
import models.dao.ProyectoDAO;
import models.dto.ProyectoDTO;

public class ProyectoServicio {

	// atributos
	private ProyectoController proyectoController;
	public static boolean consultarProyecto = false;
	public static boolean modificarProyecto = false;

	// getters y setters
	public ProyectoController getproyectoController() {
		return proyectoController;
	}

	public void setController(ProyectoController proyectoController) {
		this.proyectoController = proyectoController;
	}

	// método de vinculación con el controller principal
	public void setproyectoController(ProyectoController proyectoController) {
		this.setController(proyectoController);
	}

	// método que valida los datos de registro antes de pasarlos al DAO
	public void validarRegistro(ProyectoDTO miProyecto) {
		ProyectoDAO miProyectoDao;
		if ((miProyecto.getIdProyecto().length() < 5) && (miProyecto.getNombreProyecto().length() < 255)) {
			miProyectoDao = new ProyectoDAO();
			miProyectoDao.registrarProyecto(miProyecto);
		} else {
			JOptionPane.showMessageDialog(null, "Has de introducir un id menor a 5 caracteres", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// método que valida los datos de búsqueda antes de pasarlos al DAO
	public ProyectoDTO validarConsulta(String idProyecto) {
		ProyectoDAO miProyectoDao;
		if (idProyecto.length() < 5) {
			miProyectoDao = new ProyectoDAO();
			consultarProyecto = true;
			return miProyectoDao.buscarProyecto(idProyecto);
		} else {
			JOptionPane.showMessageDialog(null, "Has de introducir un id menor a 5 caracteres", "Error",
					JOptionPane.ERROR_MESSAGE);
			consultarProyecto = false;
		}
		return null;
	}

	// método que valida los datos de modificación antes de pasarlos al DAO
	public void validarModificacion(ProyectoDTO miProyecto) {
		ProyectoDAO miProyectoDao;
		if ((miProyecto.getIdProyecto().length() < 5)) {
			miProyectoDao = new ProyectoDAO();
			miProyectoDao.modificarProyecto(miProyecto);
			modificarProyecto = true;
		} else {
			JOptionPane.showMessageDialog(null, "Has de introducir un id menor a 5 caracteres", "Error",
					JOptionPane.ERROR_MESSAGE);
			modificarProyecto = false;
		}
	}

	// método que valida los datos de eliminación antes de pasarlos al DAO
	public void validarEliminacion(String idProyecto) {
		ProyectoDAO miProyectoDao = new ProyectoDAO();
		if (idProyecto.length() < 5) {
			miProyectoDao = new ProyectoDAO();
			miProyectoDao.eliminarProyecto(idProyecto);
		} else {
			JOptionPane.showMessageDialog(null, "Has de introducir un id menor a 5 caracteres", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// el método que vicula diferentes vistas
	public void setCoordinador(ProyectoController proyectoController) {
		this.proyectoController = proyectoController;
	}
}
