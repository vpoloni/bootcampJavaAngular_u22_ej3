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
import controllers.AsignadoAController;
import models.dto.AsignadoADTO;

public class RegistroAsignadoA extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// atributos
	private JPanel contentPane;
	private JTextField dniCientificoTextField, idProyectoTextField;
	private JButton registrarBoton, cancelarBoton;
	AsignadoAController asignadoAController;

	// constructor
	public RegistroAsignadoA() {

		setTitle("Ventana AsignadoA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// labels
		JLabel registroAsignadoA = new JLabel("REGISTRO DE ASIGNADO_A");
		registroAsignadoA.setBounds(151, 25, 149, 25);
		contentPane.add(registroAsignadoA);

		JLabel cientificoAsignado = new JLabel("DNI CIENTÍFICO");
		cientificoAsignado.setBounds(70, 92, 107, 13);
		contentPane.add(cientificoAsignado);

		JLabel proyectoAsignado = new JLabel("ID PROYECTO");
		proyectoAsignado.setBounds(70, 136, 82, 13);
		contentPane.add(proyectoAsignado);

		// textFields
		dniCientificoTextField = new JTextField();
		dniCientificoTextField.setBounds(215, 89, 127, 19);
		contentPane.add(dniCientificoTextField);
		dniCientificoTextField.setColumns(10);

		idProyectoTextField = new JTextField();
		idProyectoTextField.setBounds(215, 133, 127, 19);
		contentPane.add(idProyectoTextField);
		idProyectoTextField.setColumns(10);

		// buttons
		registrarBoton = new JButton("REGISTRAR");
		registrarBoton.setBounds(246, 205, 127, 21);
		contentPane.add(registrarBoton);
		registrarBoton.addActionListener(this);

		cancelarBoton = new JButton("CANCELAR");
		cancelarBoton.setBounds(74, 205, 127, 21);
		contentPane.add(cancelarBoton);
		cancelarBoton.addActionListener(this);

	}

	// para vincular diferentes vistas
	public void setCoordinador(AsignadoAController asignadoAController) {
		this.asignadoAController = asignadoAController;
	}

	// listener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registrarBoton) {
			try {
				AsignadoADTO miAsignadoA = new AsignadoADTO();
				miAsignadoA.setCientificoAsignado(dniCientificoTextField.getText());
				miAsignadoA.setProyectoAsignado(idProyectoTextField.getText());
				asignadoAController.registrarAsignadoA(miAsignadoA);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error en el ingreso de datos", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex);
			}
		}
		if (e.getSource() == cancelarBoton) {
			this.dispose();
		}

	}

}
