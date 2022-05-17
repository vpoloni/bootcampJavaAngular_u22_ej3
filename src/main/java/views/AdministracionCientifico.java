package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controllers.CientificoController;
import models.dto.CientificoDTO;
import models.service.CientificoServicio;
import models.service.ProyectoServicio;

public class AdministracionCientifico extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// atributos
	private JPanel contentPane;
	private JButton guardarBoton, modificarBoton, eliminarBoton, cancelarBoton, buscarBoton;
	private JTextField dniTextField, nombreTextField;
	CientificoController cientificoController;

	// constructor
	public AdministracionCientifico() {

		setTitle("Administración Cientifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// labels
		JLabel administrarCientifico = new JLabel("ADMINISTRAR CIENTIFICO");
		administrarCientifico.setBounds(150, 10, 122, 13);
		contentPane.add(administrarCientifico);

		JLabel dni = new JLabel("DNI");
		dni.setBounds(10, 94, 66, 13);
		contentPane.add(dni);

		JLabel nombre = new JLabel("NOMBRE");
		nombre.setBounds(10, 142, 66, 13);
		contentPane.add(nombre);

		// textFields
		dniTextField = new JTextField();
		dniTextField.setBounds(97, 91, 96, 19);
		contentPane.add(dniTextField);
		dniTextField.setColumns(10);

		nombreTextField = new JTextField();
		nombreTextField.setBounds(97, 139, 96, 19);
		contentPane.add(nombreTextField);
		nombreTextField.setColumns(10);

		// buttons
		guardarBoton = new JButton("GUARDAR CAMBIOS");
		guardarBoton.setBounds(228, 182, 164, 21);
		contentPane.add(guardarBoton);
		guardarBoton.addActionListener(this);

		modificarBoton = new JButton("MODIFICAR");
		modificarBoton.setBounds(43, 46, 138, 21);
		contentPane.add(modificarBoton);
		modificarBoton.addActionListener(this);

		eliminarBoton = new JButton("ELIMINAR");
		eliminarBoton.setBounds(257, 46, 135, 21);
		contentPane.add(eliminarBoton);
		eliminarBoton.addActionListener(this);

		cancelarBoton = new JButton("VOLVER");
		cancelarBoton.setBounds(276, 213, 116, 21);
		contentPane.add(cancelarBoton);
		cancelarBoton.addActionListener(this);

		buscarBoton = new JButton("BUSCAR");
		buscarBoton.setBounds(257, 106, 105, 21);
		contentPane.add(buscarBoton);
		buscarBoton.addActionListener(this);

		// llamamos al método que resetea valores introducidos en textFields anteriormente
		resetearComponentes();

	}

	// para vincular diferentes vistas
	public void setCoordinador(CientificoController cientificoController) {
		this.cientificoController = cientificoController;
	}

	// método con la lógica implementada de los botones clickados
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == guardarBoton) {
			try {
				// guardamos datos escritos por el usuario dentro del objeto CientificoDTO
				CientificoDTO miCientifico = new CientificoDTO();
				miCientifico.setDni(dniTextField.getText());
				miCientifico.setNombreApellidos(nombreTextField.getText());
				// pasamos el objeto miCientifico al controller
				cientificoController.modificarCientificos(miCientifico);
				if (CientificoServicio.modificarCientificos == true) {
					habilita(false, false, true, false, true, true);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error al ingresar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource() == buscarBoton) {

			CientificoDTO miCientifico = cientificoController.buscarCientificos(dniTextField.getText().toString());

			if (miCientifico != null) {
				muestraCientifico(miCientifico);
				habilita(true, false, true, false, true, true);
			} else if (ProyectoServicio.consultarProyecto == true) {
				JOptionPane.showMessageDialog(null, "El científico no existe", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == modificarBoton) {
			habilita(false, true, false, true, false, false);
		}

		if (e.getSource() == eliminarBoton) {
			if (!dniTextField.getText().equals("")) {
				int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el científico?",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION) {
					cientificoController.eliminarCientificos(dniTextField.getText());
					// al eliminar el científico limpiamos los componentes
					resetearComponentes();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ingrese un DNI del cientifico", "Información",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == cancelarBoton) {
			this.dispose();
		}

	}

	// método que resetea textFields con valores vacíós
	public void resetearComponentes() {
		dniTextField.setText("");
		nombreTextField.setText("");
		habilita(true, false, true, false, false, false);
	}

	// método que configura los textfields/botones como editables/habilitados/deshabilitados
	public void habilita(boolean isDni, boolean isNombre, boolean isBuscar, boolean isGuardar, boolean isModificar,
			boolean isEliminar) {
		dniTextField.setEditable(isDni);
		nombreTextField.setEditable(isNombre);
		buscarBoton.setEnabled(isBuscar);
		guardarBoton.setEnabled(isGuardar);
		modificarBoton.setEnabled(isModificar);
		eliminarBoton.setEnabled(isEliminar);
	}

	//método que setea los textFields con los valores pasados del objeto CientificoDTO
	private void muestraCientifico(CientificoDTO miCientifico) {
		dniTextField.setText(miCientifico.getDni());
		nombreTextField.setText(miCientifico.getNombreApellidos());
		habilita(true, false, false, false, true, true);
	}
}
