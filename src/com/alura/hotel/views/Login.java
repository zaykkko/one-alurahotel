package com.alura.hotel.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alura.hotel.config.Config;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JFrame {
	private static final long serialVersionUID = 8882808224025830261L;
	
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	
	public Login() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/perfil-del-usuario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/hotel.png")));
		lblNewLabel.setBounds(-53, 0, 422, 499);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(409, 181, 234, 33);
		contentPane.add(txtUsuario);

		JLabel lblNewLabel_1_1_1 = new JLabel("Usuário");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(409, 156, 57, 14);
		contentPane.add(lblNewLabel_1_1_1);

		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(409, 261, 234, 33);
		contentPane.add(txtContrasena);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(409, 236, 133, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/resources/perfil-del-usuario.png")));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(409, 322, 103, 33);
		contentPane.add(btnLogin);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/resources/cerrar-24px.png")));
		btnCancelar.setBounds(540, 322, 103, 33);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal usuario = new MenuPrincipal(); 
				usuario.setVisible(true);
				dispose();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/resources/Ha-100px.png")));
		lblNewLabel_1.setBounds(470, 30, 103, 94);
		contentPane.add(lblNewLabel_1);
	}

	private void login() {
		String username = txtUsuario.getText();
		String password = String.valueOf(txtContrasena.getPassword());

		if (username.isBlank() || password.isBlank()) {
			JOptionPane.showMessageDialog(null, "Los campos de nombre y contraseña son requeridos.",
					"Nombre o contraseña vacío", JOptionPane.ERROR_MESSAGE);

			return;
		}
		if (!username.equals(Config.getValue("username")) || !password.equals(Config.getValue("password"))) {
			JOptionPane.showMessageDialog(null, "El nombre y contraseña no coinciden.",
					"Nombre y contraseña incorrectos", JOptionPane.ERROR_MESSAGE);
		} else {
			MenuUsuario usuario = new MenuUsuario();
			usuario.setVisible(true);
			dispose();
		}
	}
}
