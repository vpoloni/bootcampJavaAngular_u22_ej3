package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controllers.AsignadoAController;
import models.dto.AsignadoADTO;
import models.service.AsignadoAServicio;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AdministracionAsignadoA extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// atributos
	private JPanel contentPane;
	private JButton guardarBoton, modificarBoton, eliminarBoton, cancelarBoton, buscarBoton;
	private JTextField cientificoTextField, proyectoTextField;
	AsignadoAController asignadoAController;

	// constructor
	public AdministracionAsignadoA() {

		setTitle("Administración AsignadoA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// labels
		JLabel administrarAsignadoA = new JLabel("Administración AsignadoA");
		administrarAsignadoA.setBounds(146, 20, 248, 13);
		contentPane.add(administrarAsignadoA);

		JLabel cientifico = new JLabel("CIENTIFICO");
		cientifico.setBounds(24, 109, 78, 36);
		contentPane.add(cientifico);

		JLabel proyecto = new JLabel("PROYECTO");
		proyecto.setBounds(24, 154, 78, 13);
		contentPane.add(proyecto);

		// textFields
		cientificoTextField = new JTextField();
		cientificoTextField.setBounds(112, 118, 96, 19);
		contentPane.add(cientificoTextField);
		cientificoTextField.setColumns(10);

		proyectoTextField = new JTextField();
		proyectoTextField.setBounds(112, 151, 96, 19);
		contentPane.add(proyectoTextField);
		proyectoTextField.setColumns(10);

		// buttons
		buscarBoton = new JButton("BUSCAR");
		buscarBoton.setBounds(244, 117, 85, 21);
		contentPane.add(buscarBoton);
		buscarBoton.addActionListener(this);

		modificarBoton = new JButton("MODIFICAR");
		modificarBoton.setBounds(24, 58, 160, 21);
		contentPane.add(modificarBoton);
		modificarBoton.addActionListener(this);

		eliminarBoton = new JButton("ELIMINAR");
		eliminarBoton.setBounds(244, 58, 160, 21);
		contentPane.add(eliminarBoton);
		eliminarBoton.addActionListener(this);

		guardarBoton = new JButton("GUARDAR CAMBIOS");
		guardarBoton.setBounds(223, 180, 173, 21);
		contentPane.add(guardarBoton);
		guardarBoton.addActionListener(this);

		cancelarBoton = new JButton("VOLVER");
		cancelarBoton.setBounds(271, 216, 125, 21);
		contentPane.add(cancelarBoton);
		cancelarBoton.addActionListener(this);

		// llamamos al método que resetea valores introducidos en textFields
		// anteriormente
		resetearComponentes();

	}

	// para vincular diferentes vistas
	public void setCoordinador(AsignadoAController asignadoAController) {
		this.asignadoAController = asignadoAController;
	}

	// método con la lógica implementada de los botones clickados
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == guardarBoton) {
			try {
				AsignadoADTO miAsignadoA = new AsignadoADTO();
				miAsignadoA.setCientificoAsignado(cientificoTextField.getText());
				miAsignadoA.setProyectoAsignado(proyectoTextField.getText());
				asignadoAController.modificarAsignadoA(miAsignadoA);
				if (AsignadoAServicio.modificarAsignadoA == true) {
					habilita(false, false, true, false, true, true);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error al ingresar los datos", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(e2);
			}

		}

		if (e.getSource() == buscarBoton) {
			AsignadoADTO miAsignadoA = asignadoAController.buscarAsignadoA(cientificoTextField.getText(),
					proyectoTextField.getText());
			if (miAsignadoA != null) {
				muestraAsignadoA(miAsignadoA);
				habilita(false, false, true, false, true, true);
			} else if (AsignadoAServicio.consultaAsignadoA == true) {
				JOptionPane.showMessageDialog(null, "Proyecto o cientifico no existe en la BBDD", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == modificarBoton) {
			habilita(true, true, false, true, false, false);

		}

		if (e.getSource() == eliminarBoton) {
			if (!cientificoTextField.getText().equals("") || !proyectoTextField.getText().equals("")) {
				int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar el AsignadoA?",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION) {
					asignadoAController.eliminarAsignadoA(cientificoTextField.getText(), proyectoTextField.getText());
					resetearComponentes();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ingrese el DNI del científico y ID del proyecto", "Información",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == cancelarBoton) {
			resetearComponentes();
			this.dispose();
		}

	}

	// método que resetea textFields con valores vacíós
	public void resetearComponentes() {
		cientificoTextField.setText("");
		proyectoTextField.setText("");
		habilita(true, true, true, false, false, false);
	}
	
	// método que configura los textfields/botones como editables/habilitados/deshabilitados
	public void habilita(boolean cientificoAsignado, boolean proyectoAsignado, boolean bBuscar, boolean bGuardar,
			boolean bModificar, boolean bEliminar) {
		cientificoTextField.setEditable(cientificoAsignado);
		proyectoTextField.setEditable(proyectoAsignado);
		buscarBoton.setEnabled(bBuscar);
		guardarBoton.setEnabled(bGuardar);
		modificarBoton.setEnabled(bModificar);
		eliminarBoton.setEnabled(bEliminar);
	}

	//método que setea los textFields con los valores pasados del objeto AsignadoADTO
	private void muestraAsignadoA(AsignadoADTO miAsignadoA) {
		cientificoTextField.setText(miAsignadoA.getCientificoAsignado());
		proyectoTextField.setText(miAsignadoA.getProyectoAsignado());
		habilita(true, true, true, false, false, false);
	}

}
