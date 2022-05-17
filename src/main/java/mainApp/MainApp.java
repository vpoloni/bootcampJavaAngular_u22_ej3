package mainApp;

import controllers.AsignadoAController;
import controllers.CientificoController;
import controllers.ProyectoController;
import models.conexion.Conexion;
import models.service.AsignadoAServicio;
import models.service.CientificoServicio;
import models.service.ProyectoServicio;
import views.AdministracionAsignadoA;
import views.AdministracionCientifico;
import views.AdministracionProyecto;
import views.InicioApp;
import views.RegistroAsignadoA;
import views.RegistroCientifico;
import views.RegistroProyecto;

public class MainApp {

	public static void main(String[] args) {

		Conexion conexion = new Conexion();
		conexion.obtenerConexion();

		MainApp miPrograma = new MainApp();

		miPrograma.iniciarPrograma();

	}

	// método que instancia y hace visibles las clases JFrame (vistas)
	private void iniciarPrograma() {

		InicioApp inicioApp = new InicioApp();

		RegistroProyecto registroProyecto = new RegistroProyecto();
		AdministracionProyecto administracionProyecto = new AdministracionProyecto();
		ProyectoServicio proyectoServicio = new ProyectoServicio();
		ProyectoController proyectoController = new ProyectoController();

		inicioApp.setCoordinador(proyectoController);
		registroProyecto.setCoordinador(proyectoController);
		administracionProyecto.setCoordinador(proyectoController);
		proyectoServicio.setCoordinador(proyectoController);

		proyectoController.setInicioApp(inicioApp);
		proyectoController.setRegistroProyecto(registroProyecto);
		proyectoController.setAdministracionProyecto(administracionProyecto);
		proyectoController.setProyectoServicio(proyectoServicio);

		RegistroCientifico registroCientifico = new RegistroCientifico();
		AdministracionCientifico administracionCientifico = new AdministracionCientifico();
		CientificoServicio cientificoServicio = new CientificoServicio();
		CientificoController cientificoController = new CientificoController();

		inicioApp.setCoordinador(cientificoController);
		registroCientifico.setCoordinador(cientificoController);
		administracionCientifico.setCoordinador(cientificoController);
		cientificoServicio.setCoordinador(cientificoController);

		cientificoController.setInicioApp(inicioApp);
		cientificoController.setRegistroCientifico(registroCientifico);
		cientificoController.setAdministracionCientifico(administracionCientifico);
		cientificoController.setCientificoServicio(cientificoServicio);

		RegistroAsignadoA registroAsignadoA = new RegistroAsignadoA();
		AdministracionAsignadoA administracionAsignadoA = new AdministracionAsignadoA();
		AsignadoAServicio asignadoAServicio = new AsignadoAServicio();
		AsignadoAController asignadoAController = new AsignadoAController();

		inicioApp.setCoordinador(asignadoAController);
		registroAsignadoA.setCoordinador(asignadoAController);
		administracionAsignadoA.setCoordinador(asignadoAController);
		asignadoAServicio.setCoordinador(asignadoAController);

		asignadoAController.setInicioApp(inicioApp);
		asignadoAController.setRegistroAsignadoA(registroAsignadoA);
		asignadoAController.setAdministracionAsignadoA(administracionAsignadoA);
		asignadoAController.setAsignadoAServicio(asignadoAServicio);

		inicioApp.setVisible(true);

	}
}
