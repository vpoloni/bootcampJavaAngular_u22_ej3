package controllers;

import models.dto.AsignadoADTO;
import models.service.AsignadoAServicio;
import views.AdministracionAsignadoA;
import views.InicioApp;
import views.RegistroAsignadoA;

public class AsignadoAController {

	// atributos
	private InicioApp inicioApp;
	private RegistroAsignadoA registroAsignadoA;
	private AdministracionAsignadoA administracionAsignadoA;
	private AsignadoAServicio asignadoAServicio;

	// getters y setters
	public InicioApp getInicioApp() {
		return inicioApp;
	}

	public void setInicioApp(InicioApp inicioApp) {
		this.inicioApp = inicioApp;
	}

	public RegistroAsignadoA getRegistroAsignadoA() {
		return registroAsignadoA;
	}

	public void setRegistroAsignadoA(RegistroAsignadoA registroAsignadoA) {
		this.registroAsignadoA = registroAsignadoA;
	}

	public AdministracionAsignadoA getAdministracionAsignadoA() {
		return administracionAsignadoA;
	}

	public void setAdministracionAsignadoA(AdministracionAsignadoA administracionAsignadoA) {
		this.administracionAsignadoA = administracionAsignadoA;
	}

	public AsignadoAServicio getAsignadoAServicio() {
		return asignadoAServicio;
	}

	public void setAsignadoAServicio(AsignadoAServicio asignadoAServicio) {
		this.asignadoAServicio = asignadoAServicio;
	}

	// métodos que hacen visibles la vista de registro y la vista de administración
	public void mostrarInicioApp() {
		inicioApp.setVisible(true);
	}

	public void mostrarRegistroAsignadoA() {
		registroAsignadoA.setVisible(true);
	}

	public void mostrarAdministracionCientifico() {
		administracionAsignadoA.setVisible(true);
	}

	// invocamos los métodos de capa service para validar los datos de las vistas
	public void registrarAsignadoA(AsignadoADTO miAsignadoA) {
		asignadoAServicio.validarRegistro(miAsignadoA);
	}

	public AsignadoADTO buscarAsignadoA(String dniCientifico, String idProyecto) {
		return asignadoAServicio.validarConsulta(dniCientifico, idProyecto);
	}

	public void modificarAsignadoA(AsignadoADTO miAsignadoA) {
		asignadoAServicio.validarModificacion(miAsignadoA);
	}

	public void eliminarAsignadoA(String dniCientifico, String idProyecto) {
		asignadoAServicio.validarEliminacion(dniCientifico, idProyecto);
	}

}
