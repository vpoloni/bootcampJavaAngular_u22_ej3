package controllers;

import models.dto.ProyectoDTO;
import models.service.ProyectoServicio;
import views.AdministracionProyecto;
import views.InicioApp;
import views.RegistroProyecto;

public class ProyectoController {

	// atributos
	private InicioApp inicioApp;
	private RegistroProyecto registroProyecto;
	private AdministracionProyecto administracionProyecto;
	private ProyectoServicio proyectoServicio;

	// getters y setters
	public InicioApp getInicioApp() {
		return inicioApp;
	}

	public void setInicioApp(InicioApp inicioApp) {
		this.inicioApp = inicioApp;
	}

	public RegistroProyecto getRegistroProyecto() {
		return registroProyecto;
	}

	public void setRegistroProyecto(RegistroProyecto registroProyecto) {
		this.registroProyecto = registroProyecto;
	}

	public AdministracionProyecto getAdministracionProyecto() {
		return administracionProyecto;
	}

	public void setAdministracionProyecto(AdministracionProyecto administracionProyecto) {
		this.administracionProyecto = administracionProyecto;
	}

	public ProyectoServicio getProyectoServicio() {
		return proyectoServicio;
	}

	public void setProyectoServicio(ProyectoServicio proyectoServicio) {
		this.proyectoServicio = proyectoServicio;
	}

	// métodos que hacen visibles la vista de registro y la vista de administración
	public void mostrarRegistroProyecto() {
		registroProyecto.setVisible(true);
	}

	public void mostrarAdministracionProyecto() {
		administracionProyecto.setVisible(true);
	}

	// invocamos los métodos de capa service para validar los datos de las vistas
	public void registrarProyecto(ProyectoDTO miProyecto) {
		proyectoServicio.validarRegistro(miProyecto);
	}

	public ProyectoDTO buscarProyecto(String idProyecto) {
		return proyectoServicio.validarConsulta(idProyecto);
	}

	public void modificarProyecto(ProyectoDTO miProyecto) {
		proyectoServicio.validarModificacion(miProyecto);
	}

	public void eliminarProyecto(String idProyecto) {
		proyectoServicio.validarEliminacion(idProyecto);
	}

}
