import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazCambiarPass extends JFrame {
    private JTextField nicknameField;
    private JPasswordField securityAnswerField;
    private JButton changePasswordButton;
    private JPasswordField passwordField;
    private int intentos = 3;
    private boolean camposEditados = false; 

    public InterfazCambiarPass() {
        setTitle("Cambiar Contraseña");
        setSize(367, 457);
        
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));

        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        nicknameLabel.setForeground(new Color(255, 255, 255));
        nicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nicknameLabel.setBounds(0, 38, 162, 35);
        nicknameLabel.setBackground(new Color(64, 128, 128));
        nicknameField = new JTextField();
        nicknameField.setBounds(172, 40, 148, 35);
        JLabel securityAnswerLabel = new JLabel("Respuesta:");
        securityAnswerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        securityAnswerLabel.setForeground(new Color(255, 255, 255));
        securityAnswerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        securityAnswerLabel.setBounds(0, 158, 162, 35);
        securityAnswerField = new JPasswordField();
        securityAnswerField.setHorizontalAlignment(SwingConstants.CENTER);
        securityAnswerField.setBounds(172, 160, 148, 35);
        changePasswordButton = new JButton("Cambiar Contrasena");
        changePasswordButton.setBounds(45, 334, 252, 47);
        panel.setLayout(null);

        panel.add(nicknameLabel);
        panel.add(nicknameField);
        panel.add(securityAnswerLabel);
        panel.add(securityAnswerField);
        panel.add(changePasswordButton);

        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                camposEditados = true; 
                String nickname = nicknameField.getText();
                String securityAnswer = new String(securityAnswerField.getPassword());
                String newPassword = new String(passwordField.getPassword());

                restablecerContraseña(nickname, securityAnswer, newPassword);
            }
        });

        getContentPane().add(panel);

        JLabel lblPreuntaDeSeguridad = new JLabel("Nombre de tu primera mascota?");
        lblPreuntaDeSeguridad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        lblPreuntaDeSeguridad.setHorizontalAlignment(SwingConstants.CENTER);
        lblPreuntaDeSeguridad.setBounds(10, 102, 329, 35);
        panel.add(lblPreuntaDeSeguridad);

        JLabel lblNuevaContrsasea = new JLabel("Nueva Contraseña:");
        lblNuevaContrsasea.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevaContrsasea.setForeground(Color.WHITE);
        lblNuevaContrsasea.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNuevaContrsasea.setBounds(0, 247, 162, 35);
        panel.add(lblNuevaContrsasea);

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setBounds(172, 249, 148, 35);
        panel.add(passwordField);
        setVisible(true);
    }

    public void restablecerContraseña(String nickname, String respuesta, String nuevaContraseña) {
        ModeloRegistro registro = buscarRegistroPorNickname(nickname);
        if (registro != null) {
            if (registro.getRespuestaSeguridad().equals(respuesta)) {
                registro.comprobarContraseña(nuevaContraseña, nuevaContraseña);
                registro.setContrasena(nuevaContraseña);
                registro.setContrasena1(nuevaContraseña);
                JOptionPane.showMessageDialog(null, "Contraseña cambiada con éxito.");
                limpiarCampos();
            } else {
                intentos--;
                if (intentos > 0 && camposEditados) { 
                    JOptionPane.showMessageDialog(null, "Respuesta incorrecta. Intenta de nuevo." + intentos + " intentos");
                } else if (intentos == 0 && camposEditados) { 
                    JOptionPane.showMessageDialog(null, "Se ha excedido el número máximo de intentos.");
                    limpiarCampos();
                }
            }
        } else {
            intentos--;
            if (intentos > 0 && camposEditados) { 
                JOptionPane.showMessageDialog(null, "No se encontró el usuario con el nickname proporcionado, te quedan " + intentos + " intentos");
            } else if (intentos == 0 && camposEditados) { 
                JOptionPane.showMessageDialog(null, "Se ha excedido el número máximo de intentos.");
                System.exit(0);
                limpiarCampos();
            }
        }
    }

    private ModeloRegistro buscarRegistroPorNickname(String nickname) {
    	for (ModeloRegistro registro : ModeloRegistro.getListaClientes()) {
			if (registro.getNickname().equals(nickname)) {
				return registro;
			}
		}
		return null;
	}
     

    private void limpiarCampos() {
        nicknameField.setText("");
        securityAnswerField.setText("");
        passwordField.setText("");
        camposEditados = false; 
        intentos = 3; 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazCambiarPass();
            }
        });
    }
}
