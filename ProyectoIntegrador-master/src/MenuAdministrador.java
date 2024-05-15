

import java.util.*;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.net.ssl.CertPathTrustManagerParameters;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuAdministrador {

	private JFrame frame;
	private Button button_1;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table_2;

	private DefaultTableModel modelo;
	private DefaultTableModel modelo1;

	static MenuAdministrador window = new MenuAdministrador();
	static GestorPrueba windowPrueba = new GestorPrueba();
	static Alquilar windowAlquilar = new Alquilar();
	static Devolver windowDevolver = new Devolver();
	static UbicacionesPrivadas ubicacionesPrivadas = new UbicacionesPrivadas();
	// Gestor de los Recursos
	private Recursos recursos = new Recursos();
	private ArrayList<Recursos> listaDisponibles = new ArrayList<>();
	private ArrayList<Recursos> listaNoDisponibles = new ArrayList<>();
	private ArrayList<String> listaUbicaciones = new ArrayList<>();
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public MenuAdministrador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// añadismos la lista de recursos a la lista local
		listaDisponibles = recursos.getListaDisponibles();
		listaNoDisponibles = recursos.getListaNoDisponibles();
		// Creamos el modelo

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Ubicacion", "Fecha", "codigoPostal", "disponibilidad" });
		modelo1 = new DefaultTableModel(new Object[][] {}, new String[] { "Nombre" });

		// Recursos
		Recursos batmovil = new Recursos("Batmovil", "Gotam", "27/02/1924", 67425, false);
		Recursos batCinturon = new Recursos("Bat Cinturon", "Gotam", "27/02/1924", 67425, false);
		Recursos bastonAbuela = new Recursos("Baston de la abuela", "NYC", "19/04/1965", 38402, false);
		Recursos chancla = new Recursos("Chancla", "Bogota", "18/12/1999", 33202, false);
		Recursos capaSuperman = new Recursos("Capa de Superman", "LongVille", "12/02/1979", 49310, false);
		Recursos raton = new Recursos("Lab Rat (Eduardo)", "EEUU", "10/01/1889", 37921, false);

		recursos.anadirDis(batmovil);
		recursos.anadirDis(batCinturon);
		recursos.anadirDis(bastonAbuela);
		recursos.anadirDis(chancla);
		recursos.anadirDis(capaSuperman);
		recursos.anadirDis(raton);

		// Innicializar datos metodo
		Object[] obj = new Object[5];

		// inicializamos datos en la tabla
		inicializarDatos();
		cargarUbicaciones();

		frame = new JFrame();
		frame.setBounds(100, 100, 699, 791);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Menu Administradores");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(225, 10, 240, 47);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Ver Recursos Disponibles");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				mostrarDisponibles();

			}
		});
		btnNewButton.setBounds(314, 242, 151, 37);
		frame.getContentPane().add(btnNewButton);

		JButton btnVerRecursosNo = new JButton("Ver Recursos no Disponibles");
		btnVerRecursosNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				mostrarNoDisponibles();

			}
		});
		btnVerRecursosNo.setBounds(472, 242, 151, 37);
		frame.getContentPane().add(btnVerRecursosNo);

		JButton btnTomarPrestado = new JButton("Tomar Prestado ");
		btnTomarPrestado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < listaDisponibles.size(); i++) {
					if (textField_8.getText().equalsIgnoreCase(listaDisponibles.get(i).getNombre())) {
						listaNoDisponibles.add(listaDisponibles.get(i));
						listaDisponibles.remove(listaDisponibles.get(i));
					}
				}
				mostrarDisponibles();
				btnTomarPrestado.setEnabled(false);
			}
		});
		btnTomarPrestado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTomarPrestado.setBounds(12, 163, 128, 25);
		frame.getContentPane().add(btnTomarPrestado);
		btnTomarPrestado.setEnabled(false);

		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDevolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < listaNoDisponibles.size(); i++) {
					if (textField_8.getText().equalsIgnoreCase(listaNoDisponibles.get(i).getNombre())) {
						listaDisponibles.add(listaNoDisponibles.get(i));
						listaNoDisponibles.remove(listaNoDisponibles.get(i));
					}
				}
				mostrarNoDisponibles();
				btnDevolver.setEnabled(false);
			}
		});
		btnDevolver.setBounds(150, 163, 134, 25);
		frame.getContentPane().add(btnDevolver);
		btnDevolver.setEnabled(false);

		JButton btnAadirUbicacionPrivada = new JButton("Añadir ubicacion");
		btnAadirUbicacionPrivada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				anyadirUbicacion(textField_5.getText());
				listaUbicaciones = ubicacionesPrivadas.getLista();
			}
		});
		btnAadirUbicacionPrivada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAadirUbicacionPrivada.setBounds(73, 387, 151, 25);
		frame.getContentPane().add(btnAadirUbicacionPrivada);
		btnAadirUbicacionPrivada.setEnabled(false);

		JButton btnEliminarUbicacionPrivada = new JButton("Eliminar ubicacion");
		btnEliminarUbicacionPrivada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarUbicacionPrivada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarUbicacion(textField_6.getText());
			}
		});
		btnEliminarUbicacionPrivada.setBounds(73, 448, 151, 25);
		frame.getContentPane().add(btnEliminarUbicacionPrivada);
		btnEliminarUbicacionPrivada.setEnabled(false);

		JButton btnAadirRecurso = new JButton("Añadir Recurso");
		btnAadirRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAadirRecurso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombre = textField.getText();
				String ubicacion = textField_1.getText();
				String fecha = textField_3.getText();
				int codigoPostal = Integer.parseInt(textField_2.getText());
				Recursos nuevoRecurso = new Recursos(nombre, ubicacion, fecha, codigoPostal, true);
				anadirDis(nuevoRecurso);
				mostrarDisponibles();
			}
		});
		btnAadirRecurso.setBounds(401, 474, 151, 37);
		frame.getContentPane().add(btnAadirRecurso);
		btnAadirRecurso.setEnabled(false);

		JButton btnEliminarRecurso = new JButton("Eliminar Recurso");
		btnEliminarRecurso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarRecurso(textField_4.getText());
			}
		});
		btnEliminarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarRecurso.setBounds(401, 591, 151, 37);
		frame.getContentPane().add(btnEliminarRecurso);
		btnEliminarRecurso.setEnabled(false);

		JButton btnBuscarPorUbicacion = new JButton("Buscar por Ubicacion");
		btnBuscarPorUbicacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				burcarUbi(textField_7.getText());

			}
		});
		btnBuscarPorUbicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarPorUbicacion.setBounds(182, 659, 151, 37);
		frame.getContentPane().add(btnBuscarPorUbicacion);
		btnBuscarPorUbicacion.setEnabled(false);

		JButton btnNewButton_1 = new JButton("Ver Ubicaciones");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarUbicaciones();
				btnBuscarPorUbicacion.setEnabled(false);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(21, 639, 151, 37);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Recursos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(418, 312, 105, 22);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Ubicaciones");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(102, 312, 134, 22);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Alquiler");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(131, 75, 105, 22);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Informacion");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(418, 75, 134, 22);
		frame.getContentPane().add(lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(296, 107, 344, 131);
		frame.getContentPane().add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(modelo);

		JLabel lblNewLabel_5 = new JLabel("Ubicacion:");
		lblNewLabel_5.setBounds(314, 439, 67, 13);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Nombre:");
		lblNewLabel_5_1.setBounds(314, 396, 87, 13);
		frame.getContentPane().add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_2 = new JLabel("Codfigo Postal: ");
		lblNewLabel_5_2.setBounds(505, 393, 135, 13);
		frame.getContentPane().add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_3 = new JLabel("Fecha:");
		lblNewLabel_5_3.setBounds(505, 439, 45, 13);
		frame.getContentPane().add(lblNewLabel_5_3);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnAadirRecurso.setEnabled(true);
			}
		});
		textField.setBounds(399, 390, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(399, 436, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(589, 390, 96, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(589, 436, 96, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_5_1_1 = new JLabel("Nombre:");
		lblNewLabel_5_1_1.setBounds(401, 565, 64, 13);
		frame.getContentPane().add(lblNewLabel_5_1_1);

		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < listaDisponibles.size(); i++) {
					if (!(textField_4.getText().toLowerCase())
							.equals(listaDisponibles.get(i).getNombre().toLowerCase())) {
						btnEliminarRecurso.setEnabled(false);
					}
				}
				for (int i = 0; i < listaDisponibles.size(); i++) {
					if ((textField_4.getText()).equalsIgnoreCase(listaDisponibles.get(i).getNombre())) {
						btnEliminarRecurso.setEnabled(true);
					}
				}

			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(456, 562, 96, 19);
		frame.getContentPane().add(textField_4);

		JLabel lblNewLabel_6 = new JLabel("Añadir Recurso");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(428, 364, 105, 13);
		frame.getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("Eliminar Recurso");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6_1.setBounds(428, 535, 105, 13);
		frame.getContentPane().add(lblNewLabel_6_1);

		JLabel lblNewLabel_5_1_2 = new JLabel("Nombre:");
		lblNewLabel_5_1_2.setBounds(73, 364, 99, 13);
		frame.getContentPane().add(lblNewLabel_5_1_2);

		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				for (int i = 0; i < listaUbicaciones.size(); i++) {
					if (!(listaUbicaciones.get(i).equalsIgnoreCase(textField_5.getText()))) {
						btnAadirUbicacionPrivada.setEnabled(true);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < listaUbicaciones.size(); i++) {
					if ((listaUbicaciones.get(i).equalsIgnoreCase(textField_5.getText()))) {
						btnAadirUbicacionPrivada.setEnabled(false);
					}
				}

			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(125, 361, 96, 19);
		frame.getContentPane().add(textField_5);

		JLabel lblNewLabel_5_1_3 = new JLabel("Nombre:");
		lblNewLabel_5_1_3.setBounds(73, 425, 67, 13);
		frame.getContentPane().add(lblNewLabel_5_1_3);

		textField_6 = new JTextField();
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < listaUbicaciones.size(); i++) {
					if (textField_6.getText().equalsIgnoreCase(listaUbicaciones.get(i))) {
						btnEliminarUbicacionPrivada.setEnabled(true);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				for (int i = 0; i < listaUbicaciones.size(); i++) {
					if (!(textField_6.getText().equalsIgnoreCase(listaUbicaciones.get(i)))) {
						btnEliminarUbicacionPrivada.setEnabled(false);
					}
				}
			}
		});
		textField_6.setColumns(10);
		textField_6.setBounds(125, 422, 96, 19);
		frame.getContentPane().add(textField_6);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 504, 312, 105);
		frame.getContentPane().add(scrollPane_1);

		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		table_2.setModel(modelo1);

		JLabel lblNewLabel_6_2 = new JLabel("Informacion Ubicaciones");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6_2.setBounds(99, 483, 151, 13);
		frame.getContentPane().add(lblNewLabel_6_2);

		JButton btnNewButton_2 = new JButton("Volver");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cerrarVista();
				windowPrueba.abrirVista();

			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(259, 706, 172, 38);
		frame.getContentPane().add(btnNewButton_2);

		textField_7 = new JTextField();
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < listaUbicaciones.size(); i++) {
					if (textField_7.getText().equalsIgnoreCase(listaUbicaciones.get(i))) {
						btnBuscarPorUbicacion.setEnabled(true);
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				for (int i = 0; i < listaUbicaciones.size(); i++) {
					if (!(textField_7.getText().equalsIgnoreCase(listaUbicaciones.get(i)))) {
						btnBuscarPorUbicacion.setEnabled(false);
					}
				}

			}
		});
		textField_7.setBounds(251, 630, 96, 19);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Nombre:");
		lblNewLabel_5_1_1_1.setBounds(182, 639, 54, 13);
		frame.getContentPane().add(lblNewLabel_5_1_1_1);

		JLabel lblNewLabel_5_1_2_1 = new JLabel("Nombre:");
		lblNewLabel_5_1_2_1.setBounds(12, 116, 117, 13);
		frame.getContentPane().add(lblNewLabel_5_1_2_1);

		textField_8 = new JTextField();
		textField_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < listaDisponibles.size(); i++) {
					if (textField_8.getText().equalsIgnoreCase(listaDisponibles.get(i).getNombre())) {
						btnTomarPrestado.setEnabled(true);
					}
				}

				for (int i = 0; i < listaNoDisponibles.size(); i++) {
					if (textField_8.getText().equalsIgnoreCase(listaNoDisponibles.get(i).getNombre())) {
						btnDevolver.setEnabled(true);
					}
				}

			}
		});
		textField_8.setColumns(10);
		textField_8.setBounds(12, 131, 82, 19);
		frame.getContentPane().add(textField_8);

	}

	// Metodos propios

	public void inicializarDatos() {

	}

	public void vaciarTabla(JTable table) {
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		int filas = table.getRowCount();

		for (int i = 0; filas > i; i++) {
			tabla.removeRow(0);
		}
	}

	// mostrar los recursos
	public void mostrarDisponibles() {
		// Innicializar datos metodo
		Object[] obj = new Object[5];

		// Vaciamos la tabla
		vaciarTabla(table_1);

		for (int i = 0; i < listaDisponibles.size(); i++) {

			obj[0] = listaDisponibles.get(i).getNombre();
			obj[1] = listaDisponibles.get(i).getUbicacion();
			obj[2] = listaDisponibles.get(i).getFecha();
			obj[3] = listaDisponibles.get(i).getCodigoPostal();
			obj[4] = listaDisponibles.get(i).getDisponibilidad();

			modelo.addRow(obj);

		}
	}

	public void mostrarNoDisponibles() {
		// Innicializar datos metodo
		Object[] obj = new Object[5];

		// Vaciamos la tabla
		vaciarTabla(table_1);

		for (int i = 0; i < listaNoDisponibles.size(); i++) {

			obj[0] = listaNoDisponibles.get(i).getNombre();
			obj[1] = listaNoDisponibles.get(i).getUbicacion();
			obj[2] = listaNoDisponibles.get(i).getFecha();
			obj[3] = listaNoDisponibles.get(i).getCodigoPostal();
			obj[4] = listaNoDisponibles.get(i).getDisponibilidad();

			modelo.addRow(obj);

		}
	}

	public void cargarUbicaciones() {
		for (int i = 1; i < listaDisponibles.size(); i++) {
			listaUbicaciones.add(listaDisponibles.get(i).getUbicacion());
		}
	}

	public void mostrarUbicaciones() {
		// Innicializar datos metodo
		Object[] obj = new Object[5];
		// Vaciamos la tabla
		vaciarTabla(table_2);

		for (int i = 0; i < listaUbicaciones.size(); i++) {

			obj[0] = listaUbicaciones.get(i);
			modelo1.addRow(obj);
		}

	}

	/**
	 * 
	 * @param ubi seria la ubicacion que le entra al metodo
	 */
	public void burcarUbi(String ubi) {
		Object[] obj = new Object[5];
		vaciarTabla(table_2);

		for (int i = 0; i < listaDisponibles.size(); i++) {
			String a = listaDisponibles.get(i).getUbicacion();
			String u = ubi.toLowerCase();
			String b = a.toLowerCase();
			if (u.equals(b)) {

				obj[0] = listaDisponibles.get(i).getNombre();
				obj[1] = listaDisponibles.get(i).getUbicacion();
				obj[2] = listaDisponibles.get(i).getFecha();
				obj[3] = listaDisponibles.get(i).getCodigoPostal();
				obj[4] = listaDisponibles.get(i).getDisponibilidad();

				modelo1.addRow(obj);

			}
		}
	}

	// Añadir y eliminar recursos
	public void anyadirRecurso(String nombre, String ubicacion, String cod, String fecha) {

	}

	public void eliminarRecurso(String nombre) {

		for (int i = 0; i < listaDisponibles.size(); i++) {
			if (textField_4.getText().equalsIgnoreCase(listaDisponibles.get(i).getNombre())) {
				recursos.eliminarDis(listaDisponibles.get(i));
			}
		}
		mostrarDisponibles();
	}

	// Metodos de ubicacion
	public void anyadirUbicacion(String ubicacion) {
		int auxiluar = 0;
		for (int i = 0; i < listaUbicaciones.size(); i++) {
			if (!ubicacion.equalsIgnoreCase(listaUbicaciones.get(i))) {
				listaUbicaciones.add(ubicacion);
				for (int j = 0; j < listaUbicaciones.size() - 1; j++) {
					if (ubicacion.equalsIgnoreCase(listaUbicaciones.get(j))) {
						listaUbicaciones.remove(j);
					}
				}
			}
		}

		mostrarUbicaciones();
	}

	public void eliminarUbicacion(String ubicacion) {

		for (int i = 0; i < listaUbicaciones.size(); i++) {
			if (ubicacion.equalsIgnoreCase(listaUbicaciones.get(i))) {
				listaUbicaciones.remove(i);
			}
		}
		mostrarUbicaciones();
	}

	public void anadirDis(Recursos recursos) {
		listaDisponibles.add(recursos);

	}

	public void eliminarDis(Recursos recursos) {
		listaDisponibles.remove(recursos);

	}

	public void cerrarVista() {

		window.frame.setVisible(false);

	}

	public void abrirVista() {

		window.frame.setVisible(true);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	public JTable getTable() {
		return getTable();
	}
}
