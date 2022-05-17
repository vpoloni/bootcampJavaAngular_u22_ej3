package models.service;

import javax.swing.JOptionPane;
import controllers.AsignadoAController;
import models.dao.AsignadoADAO;
import models.dto.AsignadoADTO;

public class AsignadoAServicio {

	// atributos
	private AsignadoAController asignadoAController;
	public static boolean consultaAsignadoA = false;
	public static boolean modificarAsignadoA = false;

	// getters y setters
	public AsignadoAController getAsignadoAController() {
		return asignadoAController;
	}

	public void setController(AsignadoAController asignadoAController) {
		this.asignadoAController = asignadoAController;
	}

	// método de vinculación con el controller principal
	public void setAsignadoAController(AsignadoAController asignadoAController) {
		this.setController(asignadoAController);
	}

	// método que valida los datos de registro antes de pasarlos al DAO
	public void validarRegistro(AsignadoADTO miAsignadoA) {
		AsignadoADAO miAsignadoADAO;
		if (!(miAsignadoA.getProyectoAsignado().length() > 4) || !(miAsignadoA.getCientificoAsignado().length() > 8)) {
			miAsignadoADAO = new AsignadoADAO();
			miAsignadoADAO.registrarAsignadoA(miAsignadoA);
		} else {
			JOptionPane.showMessageDialog(null,
					"Id del proyecto asignado tiene que ser menor a 4 caracteres, DNI del cientifico tiene que ser menor a 9 caracteres",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	// método que valida los datos de búsqueda antes de pasarlos al DAO
	public AsignadoADTO validarConsulta(String dniCientifico, String idProyecto) {
		AsignadoADAO miAsignadoADAO;
		if ((dniCientifico.length() < 9) && (idProyecto.length() < 5)) {
			miAsignadoADAO = new AsignadoADAO();
			consultaAsignadoA = true;
			return miAsignadoADAO.buscarAsignadoA(dniCientifico, idProyecto);
		} else {
			JOptionPane.showMessageDialog(null,
					"El dni del cientifico o el id del proyecto no está entre el rango de valores permitidos",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	// método que valida los datos de modificación antes de pasarlos al DAO
	public void validarModificacion(AsignadoADTO miAsignadoA) {
		AsignadoADAO miAsignadoADAO;
		if ((miAsignadoA.getCientificoAsignado().length() < 9) && (miAsignadoA.getProyectoAsignado().length() < 5)) {
			miAsignadoADAO = new AsignadoADAO();
			miAsignadoADAO.modificarAsignadoA(miAsignadoA);
			modificarAsignadoA = true;
		} else {
			JOptionPane.showMessageDialog(null,
					"El dni del cientifico o id del proyecto no está dentro de rango de valores permitidos",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
			modificarAsignadoA = false;
		}
	}

	// método que valida los datos de eliminación antes de pasarlos al DAO
	public void validarEliminacion(String dniCientifico, String idProyecto) {
		AsignadoADAO miAsignadoADao = new AsignadoADAO();
		if ((dniCientifico.length() < 9) && (idProyecto.length() < 5)) {
			miAsignadoADao.eliminarAsignadoA(dniCientifico, idProyecto);
		} else {
			JOptionPane.showMessageDialog(null,
					"El dni del cientifico o id del proyecto no está dentro de rango de valores permitidos",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	// el método que vicula diferentes vistas
	public void setCoordinador(AsignadoAController asignadoAController) {
		this.asignadoAController = asignadoAController;
	}

}
