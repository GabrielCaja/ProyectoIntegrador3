
import java.util.*;

import javax.swing.JOptionPane;

public class ModeloRegistro {

	public static ArrayList<ModeloRegistro> listaClientes = new ArrayList<>();

	Scanner sc = new Scanner(System.in);
	boolean validado = true;
	boolean superEmpresa = false;
	boolean administrador = false;
	boolean validadoPass = false;
	private int id = 0;
	private String nickname, nombre, contrasena, contrasena2, ubicacion, preguntaSeguridad, respuestaSeguridad,
			superPoder, confirmacion;

	public ModeloRegistro() {
	}

	// Registrar un superheroe
	public ModeloRegistro(int id, String nickname, String nombre, String superPoder, String contrasena, String contrasena2,
			String ubicacion, String preguntaSeguridad, String respuestaSeguridad, ArrayList<ModeloRegistro> listaClientes,
			boolean administrador) {
		this.id = id;
		this.nickname = nickname;
		this.nombre = nombre;
		this.superPoder = superPoder;
		this.contrasena = contrasena;
		this.contrasena2 = contrasena2;
		this.ubicacion = ubicacion;
		this.preguntaSeguridad = preguntaSeguridad;
		this.respuestaSeguridad = respuestaSeguridad;
		this.administrador = administrador;
	}

	// Registrar una superEmpresa
	public ModeloRegistro(int id, String nickname, String nombre, String superPoder, String contrasena, String contrasena2,
			String ubicacion, String preguntaSeguridad, String respuestaSeguridad, ArrayList<ModeloRegistro> listaClientes,
			String confirmacion) {
		this.id = id;
		this.nickname = nickname;
		this.nombre = nombre;
		this.superPoder = superPoder;
		this.contrasena = contrasena;
		this.contrasena2 = contrasena2;
		this.ubicacion = ubicacion;
		this.preguntaSeguridad = preguntaSeguridad;
		this.respuestaSeguridad = respuestaSeguridad;
		this.confirmacion = confirmacion;
	}

	public static ArrayList<ModeloRegistro> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<ModeloRegistro> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContrasena2() {
		return contrasena2;
	}

	public void setContrasena2(String contrasena2) {
		this.contrasena2 = contrasena2;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getSuperPoder() {
		return superPoder;
	}

	public void setSuperPoder(String superPoder) {
		this.superPoder = superPoder;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}

	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}

	public String getRespuestaSeguridad() {
		return respuestaSeguridad;
	}

	public void setRespuestaSeguridad(String respuestaSeguridad) {
		this.respuestaSeguridad = respuestaSeguridad;
	}

	public String getNickname() {
		return nickname;
	}

	public String getContrasena() {
		return contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setContrasena(String contrasenaCambiada) {
		this.contrasena = contrasenaCambiada;
	}

	public void setContrasena1(String contrasenaCambiada2) {
		this.contrasena2 = contrasenaCambiada2;

	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean getAdministrador(ModeloRegistro re) {
		return re.administrador;
	}

	// Registro principal para los clientes de la plataforma
	public void registrarCliente(String nickname, String nombre, String superPoder, String contrasena,
			String contrasena2, String ubicacion, String preguntaSeguridad, String respuestaSeguridad,
			boolean confirmacionCrimen, boolean aceptaTerminos) {
		
		id++;
		// En el caso de ser superempresa
		if (confirmacionCrimen == true) {
			ModeloRegistro superEmpresa = new ModeloRegistro();
			if (confirmacionCrimen == true && aceptaTerminos == true) {
				String confirmacion = "Acepto terminos";
				asignarNickname(nickname);
				comprobarContraseña(contrasena, contrasena2);
				if (validado == true && validadoPass==true) {
					superEmpresa = new ModeloRegistro(id, nickname, nombre, superPoder, contrasena, contrasena2, ubicacion,
							preguntaSeguridad, respuestaSeguridad, listaClientes, confirmacion);
					JOptionPane.showMessageDialog(null, "¡Registro exitoso para la empresa " + nombre + "!", "Registro",
							JOptionPane.INFORMATION_MESSAGE);
					// Registro de una superempresa
					listaClientes.add(superEmpresa);
					validado = false;
					validadoPass = false;
				}

			}
		}
		// Registro de un superheroe
		if (confirmacionCrimen == false) {
			asignarNickname(nickname);
			comprobarContraseña(contrasena, contrasena2);
			if (validado == true && validadoPass==true) {
				ModeloRegistro registro = new ModeloRegistro(id, nickname, nombre, superPoder, contrasena, contrasena2, ubicacion,
						preguntaSeguridad, respuestaSeguridad, listaClientes, administrador);
				JOptionPane.showMessageDialog(null, "¡Registro exitoso para " + nombre + "!", "Registro",
						JOptionPane.INFORMATION_MESSAGE);
				listaClientes.add(registro);
				validado = false;
				validadoPass = false;
			}
		}

	}

	private boolean asignarNickname(String nickname) {
		if (!validarNickname(nickname) || listaClientes.contains(nickname)) {
			JOptionPane.showMessageDialog(null,
					"El nickname no es válido o ya está en uso. Inténtalo de nuevo" + nombre, "Registro",
					JOptionPane.INFORMATION_MESSAGE);
			validado = false;
		} else {
			validado = true;
		}
		return validado;
	}

	private boolean validarNickname(String nickname) {
		// Verificar si el nickname cumple con los requisitos de estar compuesto por
		// caracteres anglosajones y números
		return nickname.matches("^[a-zA-Z0-9]+$");
	}

	// Se comprobara la contraseña para ver que cumple las medidas de seguridad y
	// requisitos
	public boolean comprobarContraseña(String contrasena, String contrasena2) {
		if(!contrasena.equals(contrasena2) || !validarContraseña(contrasena)) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no cumplen los requisitos de seguridad", "Registro",
					JOptionPane.INFORMATION_MESSAGE);
			validadoPass = false;
		}else {
			validadoPass = true;
		}
		return validadoPass;
	}

	// Se comprobara la contraseña para ver que cumple las medidas de seguridad y
	// requisitos
	private boolean validarContraseña(String password) {
		return password.length() >= 6 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
				&& password.matches(".*\\d.*");
	}

	// Metodo para cambiar la contraseña a partir de comprobar la pregunta y
	// respuesta de seguridad y con un numero de intentos preestablecido(3)
	public void restablecerContraseña(Scanner sc) {
		int intentos = 3;
		do {
			System.out.println("¿Cuál es tu nickname en la plataforma?");
			String nickname = sc.next();

			ModeloRegistro registro = buscarRegistroPorNickname(nickname);
			if (registro != null) {
				System.out.println("Indica la respuesta a tu pregunta de seguridad:");
				System.out.println(registro.getPreguntaSeguridad());
				String respuesta = sc.next();

				if (registro.getRespuestaSeguridad().equals(respuesta)) {
					System.out.println("Introduce tu nueva contraseña:");
					String nuevaContraseña = sc.next();

					System.out.println("Introduce la nueva contraseña nuevamente:");
					String nuevaContraseñaConfirmacion = sc.next();

					registro.comprobarContraseña(nuevaContraseña, nuevaContraseñaConfirmacion);
					registro.setContrasena(nuevaContraseña);
					registro.setContrasena1(nuevaContraseñaConfirmacion);

					System.out.println("Contraseña cambiada con éxito.");
					// Salir del método una vez que la contraseña se haya cambiado con éxito
					return;
				} else {
					System.out.println("Respuesta incorrecta. Intenta de nuevo ." + intentos + " intentos");
					intentos--;
				}
			} else {
				System.out.println(
						"No se encontró el usuario con el nickname proporcionado, te quedan " + intentos + " intentos");
				intentos--;
			}
		} while (intentos > 0);

		System.out.println("Se ha excedido el número máximo de intentos.");
	}

	// Comprobar que el usuario esta registrado en la plataforma
	private ModeloRegistro buscarRegistroPorNickname(String nickname) {
		for (ModeloRegistro registro : listaClientes) {
			if (registro.getNickname().equals(nickname)) {
				return registro;
			}
		}
		return null;
	}

	// Metodo para iniciar sesion verificando si el usuario existe registrado en la
	// plataforma
	public void iniciarSesion(Scanner sc) {
		System.out.println("Introduzce tu nickname de usuario:");
		String nickname = sc.next();
		System.out.println("Introduzce tu contraseña:");
		String contrasena = sc.next();

		boolean usuarioEncontrado = false;
		for (ModeloRegistro registro : listaClientes) {
			if (registro.getNickname().equals(nickname) && registro.getContrasena().equals(contrasena)) {
				System.out.println("Bienvenido de nuevo: " + registro.getNombre());
				usuarioEncontrado = true;
				break;
			}

		}
		// En caso de no encontrar al usuario
		if (!usuarioEncontrado) {
			System.out.println("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
		}

	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", nickname=" + nickname + ", nombre=" + nombre + ", contrasena=" + contrasena
				+ ", contrasena2=" + contrasena2 + ", ubicacion=" + ubicacion + ", preguntaSeguridad="
				+ preguntaSeguridad + ", respuestaSeguridad=" + respuestaSeguridad + ", superPoder=" + superPoder + "]";
	}

	public void registrarse() {
		// TODO Auto-generated method stub

	}

}
