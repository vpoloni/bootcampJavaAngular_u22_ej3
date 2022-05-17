package models.service;

import javax.swing.JOptionPane;
import controllers.CientificoController;
import models.dao.CientificoDAO;
import models.dto.CientificoDTO;

public class CientificoServicio {
	
	// atributos 
	private CientificoController cientificoController;
	public static boolean consultaCientificos = false;
	public static boolean modificarCientificos = false;
	
	// getters y setters
	public CientificoController getCientificosController() {
		return cientificoController;
	}

	public void setController(CientificoController CientificosController) {
		this.cientificoController = CientificosController;
	}

	// método de vinculación con el controller principal
	public void setCientificosController(CientificoController cientificoController) {
		this.setController(cientificoController);
	}

	// método que valida los datos de registro antes de pasarlos al DAO
	public void validarRegistro(CientificoDTO miCientifico) {
		CientificoDAO miCientificoDAO;
		if (miCientifico.getDni().length() < 9 || miCientifico.getNombreApellidos().length() < 256) {
			miCientificoDAO = new CientificoDAO();
			miCientificoDAO.registrarCientifico(miCientifico);
		} else {
			JOptionPane.showMessageDialog(null, "El DNI del Cientificos debe tener menos de 9 caracteres",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	// método que valida los datos de búsqueda antes de pasarlos al DAO
	public CientificoDTO validarConsulta(String dniCientifico) {
		CientificoDAO miCientificosDao;
		if (dniCientifico.length() < 9) {
			miCientificosDao = new CientificoDAO();
			consultaCientificos = true;
			return miCientificosDao.buscarCientifico(dniCientifico);
		} else {
			JOptionPane.showMessageDialog(null, "El dni del Cientificos debe tener menos de 9 caracteres",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	// método que valida los datos de modificación antes de pasarlos al DAO
	public void validarModificacion(CientificoDTO miCientifico) {
		CientificoDAO miCientificosDao;
		if (miCientifico.getDni().length() < 9 && miCientifico.getNombreApellidos().length() < 256) {
			miCientificosDao = new CientificoDAO();
			miCientificosDao.modificarCientifico(miCientifico);
			modificarCientificos = true;
		} else {
			JOptionPane.showMessageDialog(null, "El dni del Cientifico debe tener menos de 9 caracteres", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			modificarCientificos = false;
		}
	}

	// método que valida los datos de eliminación antes de pasarlos al DAO
	public void validarEliminacion(String dniCientifico) {
		CientificoDAO miCientificosDao = new CientificoDAO();
		if (dniCientifico.length() < 9) {
			miCientificosDao.eliminarCientifico(dniCientifico);
		} else {
			JOptionPane.showMessageDialog(null, "El dni del Cientifico debe tener menos de 9 caracteres", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// el método que vicula diferentes vistas
	public void setCoordinador(CientificoController cientificoController) {
		this.cientificoController = cientificoController;
	}

}
