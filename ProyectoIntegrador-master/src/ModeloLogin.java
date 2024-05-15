import javax.swing.JOptionPane;

public class ModeloLogin {
    public static boolean iniciarSesion(String nickname, String contrasena) {
        boolean usuarioEncontrado = false;
        boolean exitoso = false;
        for (ModeloRegistro registro : ModeloRegistro.getListaClientes()) {
            if (registro.getNickname().equals(nickname) && registro.getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(null, "Bienvenido de nuevo: " + registro.getNombre());
                GestorPrueba window = new GestorPrueba();
                window.frame.setVisible(true);
                usuarioEncontrado = true;
                exitoso = true;
                break;
            }
        }
        // En caso de no encontrar al usuario
        if (!usuarioEncontrado) {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Por favor, inténtalo de nuevo.");
            exitoso = false;
        }
		return exitoso;
    }
}
