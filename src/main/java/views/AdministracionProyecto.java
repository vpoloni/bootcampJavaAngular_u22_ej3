package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controllers.ProyectoController;
import models.dto.ProyectoDTO;
import models.service.ProyectoServicio;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AdministracionProyecto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// atributos
	private JPanel contentPane;
	private JTextField idProyectoTextField, nombreProyectoTextField, horasProyectoTextField;
	private JButton guardarBoton, modificarBoton, eliminarProyectoBoton, cancelarBoton, buscarBoton;
	ProyectoController proyectoController;

	// constructor
	public AdministracionProyecto() {
		setTitle("Administración Proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// labels
		JLabel administrarProyecto = new JLabel("ADMINISTRAR PROYECTO");
		administrarProyecto.setBounds(148, 10, 166, 13);
		contentPane.add(administrarProyecto);
		
		JLabel idProyecto = new JLabel("ID");
		idProyecto.setBounds(10, 92, 77, 13);
		contentPane.add(idProyecto);

		JLabel nombreProyecto = new JLabel("NOMBRE");
		nombreProyecto.setBounds(10, 123, 77, 13);
		contentPane.add(nombreProyecto);

		JLabel horasProyecto = new JLabel("HORAS");
		horasProyecto.setBounds(10, 164, 77, 13);
		contentPane.add(horasProyecto);
		
		// textFields
		idProyectoTextField = new JTextField();
		idProyectoTextField.setBounds(114, 89, 96, 19);
		contentPane.add(idProyectoTextField);
		idProyectoTextField.setColumns(10);

		nombreProyectoTextField = new JTextField();
		nombreProyectoTextField.setBounds(114, 120, 96, 19);
		contentPane.add(nombreProyectoTextField);
		nombreProyectoTextField.setColumns(10);

		horasProyectoTextField = new JTextField();
		horasProyectoTextField.setBounds(114, 161, 96, 19);
		contentPane.add(horasProyectoTextField);
		horasProyectoTextField.setColumns(10);

		// buttons
		buscarBoton = new JButton("BUSCAR");
		buscarBoton.setBounds(278, 119, 85, 21);
		contentPane.add(buscarBoton);
		buscarBoton.addActionListener(this);
		
		modificarBoton = new JButton("MODIFICAR");
		modificarBoton.setBounds(33, 38, 128, 21);
		contentPane.add(modificarBoton);
		modificarBoton.addActionListener(this);

		eliminarProyectoBoton = new JButton("ELIMINAR");
		eliminarProyectoBoton.setBounds(264, 38, 134, 21);
		contentPane.add(eliminarProyectoBoton);
		eliminarProyectoBoton.addActionListener(this);
		
		guardarBoton = new JButton("GUARDAR CAMBIOS");
		guardarBoton.setBounds(236, 189, 166, 21);
		contentPane.add(guardarBoton);
		guardarBoton.addActionListener(this);

		cancelarBoton = new JButton("VOLVER");
		cancelarBoton.setBounds(313, 220, 85, 21);
		contentPane.add(cancelarBoton);
		cancelarBoton.addActionListener(this);

		// llamamos al método que resetea valores introducidos en textFields anteriormente 
		resetearComponentes();
	}

	// para vincular diferentes vistas
	public void setCoordinador(ProyectoController proyectoController) {
		this.proyectoController = proyectoController;
	}

	// método con la lógica implementada de los botones clickados
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == guardarBoton) {
			try {
				// guardamos datos escritos por el usuario dentro del objeto ProyectoDTO
				ProyectoDTO miProyecto = new ProyectoDTO();
				miProyecto.setIdProyecto(idProyectoTextField.getText());
				miProyecto.setNombreProyecto(nombreProyectoTextField.getText());
				miProyecto.setHorasProyecto(Integer.parseInt(horasProyectoTextField.getText()));
				// pasamos el objeto miProyecto al controller
				proyectoController.modificarProyecto(miProyecto);
				if (ProyectoServicio.modificarProyecto == true) {
					habilita(false, false, false, true, false, true, true);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error al ingresar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource() == buscarBoton) {
			ProyectoDTO miProyecto = proyectoController.buscarProyecto(idProyectoTextField.getText().toString());
			if (miProyecto != null) {
				muestraProyecto(miProyecto);
				habilita(true, false, false, true, false, true, true);
			} else if (ProyectoServicio.consultarProyecto == true) {
				JOptionPane.showMessageDialog(null, "El proyecto no existe", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (e.getSource() == modificarBoton) {
			habilita(false, true, true, false, true, false, false);
		}
		if (e.getSource() == eliminarProyectoBoton) { 
			if (!idProyectoTextField.getText().equals("")) {
				int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el proyecto?",
						"Confirmación", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_NO_OPTION) {
					proyectoController.eliminarProyecto(idProyectoTextField.getText());
					// al eliminar el proyecto limpiamos los componentes
					resetearComponentes();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ingrese un ID del proyecto", "Información",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == cancelarBoton) {
			resetearComponentes();
			this.dispose();
		}

	}
	// método que resetea textFields con valores vacíós
	public void resetearComponentes()
	{
		idProyectoTextField.setText("");
		nombreProyectoTextField.setText("");
		horasProyectoTextField.setText("");
		habilita(true, false, false, true, false, false, false);
	}
	// método que configura los textfields/botones como editables/habilitados/deshabilitados
	public void habilita(boolean isIdProyecto, boolean isNombre, boolean isHoras, boolean isBuscar, boolean isGuardar,
			boolean isModificar, boolean isEliminar) {
		idProyectoTextField.setEditable(isIdProyecto);
		nombreProyectoTextField.setEditable(isNombre);
		horasProyectoTextField.setEditable(isHoras);
		buscarBoton.setEnabled(isBuscar);
		guardarBoton.setEnabled(isGuardar);
		modificarBoton.setEnabled(isModificar);
		eliminarProyectoBoton.setEnabled(isEliminar);
	}
	//método que setea los textFields con los valores pasados del objeto ProyectoDTO
	private void muestraProyecto(ProyectoDTO miProyecto) {
		idProyectoTextField.setText(miProyecto.getIdProyecto());
		nombreProyectoTextField.setText(miProyecto.getNombreProyecto());
		//concatenamos el valor numérico con string vacio para convertir todo el resultado a str
		horasProyectoTextField.setText(miProyecto.getHorasProyecto()+"");
		habilita(true, false, false, false, false, true, true);
	}


}