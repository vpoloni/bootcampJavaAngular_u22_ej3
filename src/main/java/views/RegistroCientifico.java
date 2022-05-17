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

public class RegistroCientifico extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// atributos
	private JPanel contentPane;
	private JTextField dniCientificoTextField, nombreCientificoTextField;
	private JButton registrarBoton, cancelarBoton;
	CientificoController cientificoController;

	// constructor
	public RegistroCientifico() {

		setTitle("Registro Científico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// labels
		JLabel registroCientifico = new JLabel("REGISTRO DE CIENTÍFICO");
		registroCientifico.setBounds(151, 25, 149, 25);
		contentPane.add(registroCientifico);

		JLabel dniCientifico = new JLabel("DNI");
		dniCientifico.setBounds(98, 89, 45, 13);
		contentPane.add(dniCientifico);

		JLabel nombreCientifico = new JLabel("NOMBRE");
		nombreCientifico.setBounds(98, 136, 45, 13);
		contentPane.add(nombreCientifico);

		// textFields
		dniCientificoTextField = new JTextField();
		dniCientificoTextField.setBounds(221, 86, 132, 19);
		contentPane.add(dniCientificoTextField);
		dniCientificoTextField.setColumns(10);

		nombreCientificoTextField = new JTextField();
		nombreCientificoTextField.setBounds(221, 133, 132, 19);
		contentPane.add(nombreCientificoTextField);
		nombreCientificoTextField.setColumns(10);

		// buttons
		registrarBoton = new JButton("REGISTRAR");
		registrarBoton.setBounds(240, 205, 132, 21);
		contentPane.add(registrarBoton);
		registrarBoton.addActionListener(this);

		cancelarBoton = new JButton("CANCELAR");
		cancelarBoton.setBounds(57, 205, 132, 21);
		contentPane.add(cancelarBoton);
		cancelarBoton.addActionListener(this);

	}

	// para vincular diferentes vistas
	public void setCoordinador(CientificoController cientificoController) {
		this.cientificoController = cientificoController;
	}

	// listener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registrarBoton) {
			try {
				CientificoDTO miCientifico = new CientificoDTO();
				miCientifico.setDni(dniCientificoTextField.getText());
				miCientifico.setNombreApellidos(nombreCientificoTextField.getText());
				cientificoController.registrarCientificos(miCientifico);
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
