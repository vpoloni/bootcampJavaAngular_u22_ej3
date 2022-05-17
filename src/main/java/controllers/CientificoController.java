package controllers;

import models.dto.CientificoDTO;
import models.service.CientificoServicio;
import views.AdministracionCientifico;
import views.InicioApp;
import views.RegistroCientifico;

public class CientificoController {

	// atributos
	private InicioApp inicioApp;
	private RegistroCientifico registroCientifico;
	private AdministracionCientifico administracionCientifico;
	private CientificoServicio cientificoServicio;

	// getters y setters
	public InicioApp getInicioApp() {
		return inicioApp;
	}

	public void setInicioApp(InicioApp inicioApp) {
		this.inicioApp = inicioApp;
	}

	public RegistroCientifico getRegistroCientifico() {
		return registroCientifico;
	}

	public void setRegistroCientifico(RegistroCientifico registroCientifico) {
		this.registroCientifico = registroCientifico;
	}

	public AdministracionCientifico getAdministracionCientifico() {
		return administracionCientifico;
	}

	public void setAdministracionCientifico(AdministracionCientifico administracionCientifico) {
		this.administracionCientifico = administracionCientifico;
	}

	public CientificoServicio getCientificoServicio() {
		return cientificoServicio;
	}

	public void setCientificoServicio(CientificoServicio cientificoServicio) {
		this.cientificoServicio = cientificoServicio;
	}

	// métodos que hacen visibles la vista de registro y la vista de administración
	public void mostrarInicioApp() {
		inicioApp.setVisible(true);
	}

	public void mostrarRegistroCientifico() {
		registroCientifico.setVisible(true);
	}

	public void mostrarAdministracionCientifico() {
		administracionCientifico.setVisible(true);
	}

	// invocamos los métodos de capa service para validar los datos de las vistas
	public void registrarCientificos(CientificoDTO miCientifico) {
		cientificoServicio.validarRegistro(miCientifico);
	}

	public CientificoDTO buscarCientificos(String idCientifico) {
		return cientificoServicio.validarConsulta(idCientifico);
	}

	public void modificarCientificos(CientificoDTO miCientifico) {
		cientificoServicio.validarModificacion(miCientifico);
	}

	public void eliminarCientificos(String idCientifico) {
		cientificoServicio.validarEliminacion(idCientifico);
	}

}
