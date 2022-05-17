package views;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import controllers.ProyectoController;
import models.dto.ProyectoDTO;

public class RegistroProyecto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// atributos
	private JTextField idTextField, nombreTextField, horasTextField;
	private JButton registrarBoton, cancelarBoton;
	ProyectoController proyectoController;

	// constructor
	public RegistroProyecto() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registro Proyecto");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		// labels
		JLabel registroProyecto = new JLabel("REGISTRO DE PROYECTO");
		registroProyecto.setBounds(150, 10, 149, 25);
		
		JLabel idProyecto = new JLabel("ID");
		idProyecto.setBounds(98, 61, 45, 13);
		
		JLabel nombreProyecto = new JLabel("NOMBRE");
		nombreProyecto.setBounds(98, 104, 45, 13);
		
		JLabel horasProyecto = new JLabel("HORAS");
		horasProyecto.setBounds(98, 156, 45, 13);

		// textFields
		idTextField = new JTextField();
		idTextField.setBounds(203, 58, 124, 19);
		idTextField.setColumns(10);

		nombreTextField = new JTextField();
		nombreTextField.setBounds(203, 101, 124, 19);
		nombreTextField.setColumns(10);

		horasTextField = new JTextField();
		horasTextField.setBounds(203, 153, 124, 19);
		horasTextField.setColumns(10);

		// buttons
		registrarBoton = new JButton("REGISTRAR");
		registrarBoton.setBounds(252, 220, 132, 21);
		registrarBoton.addActionListener(this);

		cancelarBoton = new JButton("VOLVER");
		cancelarBoton.setBounds(62, 220, 139, 21);
		cancelarBoton.addActionListener(this);

		// añadimos los componentes al panel
		getContentPane().add(cancelarBoton);
		getContentPane().add(registrarBoton);
		getContentPane().add(horasTextField);
		getContentPane().add(nombreTextField);
		getContentPane().add(idTextField);
		getContentPane().add(nombreProyecto);
		getContentPane().add(idProyecto);
		getContentPane().add(horasProyecto);
		getContentPane().add(registroProyecto);
		getContentPane().add(registrarBoton);

	}

	// para vincular diferentes vistas
	public void setCoordinador(ProyectoController proyectoController) {
		this.proyectoController = proyectoController;
	}

	// listener
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == registrarBoton) {
			try {
				ProyectoDTO miProyecto = new ProyectoDTO();
				miProyecto.setIdProyecto(idTextField.getText());
				miProyecto.setNombreProyecto(nombreTextField.getText());
				miProyecto.setHorasProyecto(Integer.parseInt(horasTextField.getText()));
				proyectoController.registrarProyecto(miProyecto);
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
