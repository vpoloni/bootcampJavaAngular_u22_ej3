package views;

import javax.swing.JFrame;
import controllers.AsignadoAController;
import controllers.CientificoController;
import controllers.ProyectoController;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class InicioApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// atributos
	private JButton registrarProyectoInicio, registrarCientificoInicio, registrarAsignadoAInicio;
	private JButton administrarProyectoInicio, administrarCientificoInicio, administrarAsignadoAInicio;
	ProyectoController proyectoController;
	CientificoController cientificoController;
	AsignadoAController asignadoAController;

	// constructor
	public InicioApp() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Principal");
		setSize(480, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		// label
		JLabel eligirOperacion = new JLabel("ELIGE LA OPERACIÓN A REALIZAR:");
		eligirOperacion.setBounds(128, 20, 193, 13);

		// buttons
		registrarProyectoInicio = new JButton("REGISTRAR PROYECTO");
		registrarProyectoInicio.setBounds(32, 62, 177, 21);
		registrarProyectoInicio.addActionListener(this);

		registrarCientificoInicio = new JButton("REGISTRAR CIENTIFICO");
		registrarCientificoInicio.setBounds(32, 105, 177, 21);
		registrarCientificoInicio.addActionListener(this);

		registrarAsignadoAInicio = new JButton("REGISTRAR ASIGNADO_A");
		registrarAsignadoAInicio.setBounds(32, 150, 177, 21);
		registrarAsignadoAInicio.addActionListener(this);

		administrarProyectoInicio = new JButton("ADMINISTRAR PROYECTO");
		administrarProyectoInicio.setBounds(236, 62, 202, 21);
		administrarProyectoInicio.addActionListener(this);

		administrarCientificoInicio = new JButton("ADMINISTRAR CIENTIFICO");
		administrarCientificoInicio.setBounds(236, 105, 202, 21);
		administrarCientificoInicio.addActionListener(this);

		administrarAsignadoAInicio = new JButton("ADMINISTRAR ASIGNADO_A");
		administrarAsignadoAInicio.setBounds(236, 150, 202, 21);
		administrarAsignadoAInicio.addActionListener(this);

		// añadimos los componentes al panel
		getContentPane().add(administrarAsignadoAInicio);
		getContentPane().add(administrarCientificoInicio);
		getContentPane().add(administrarProyectoInicio);
		getContentPane().add(registrarAsignadoAInicio);
		getContentPane().add(registrarCientificoInicio);
		getContentPane().add(registrarProyectoInicio);

	}

	// para vincular diferentes vistas
	public void setCoordinador(ProyectoController proyectoController) {
		this.proyectoController = proyectoController;
	}

	public void setCoordinador(CientificoController cientificoController) {
		this.cientificoController = cientificoController;
	}

	public void setCoordinador(AsignadoAController asignadoAController) {
		this.asignadoAController = asignadoAController;
	}

	// método con la lógica implementada de los botones clickados
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == registrarProyectoInicio) {
			proyectoController.mostrarRegistroProyecto();
		}

		if (e.getSource() == registrarCientificoInicio) {
			cientificoController.mostrarRegistroCientifico();
		}
		if (e.getSource() == registrarAsignadoAInicio) {
			asignadoAController.mostrarRegistroAsignadoA();
		}
		if (e.getSource() == administrarProyectoInicio) {
			proyectoController.mostrarAdministracionProyecto();
		}
		if (e.getSource() == administrarCientificoInicio) {
			cientificoController.mostrarAdministracionCientifico();
		}

		if (e.getSource() == administrarAsignadoAInicio) {
			asignadoAController.mostrarAdministracionCientifico();
		}

	};
}
