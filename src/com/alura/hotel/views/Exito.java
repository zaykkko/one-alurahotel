package com.alura.hotel.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alura.hotel.dtos.HuespedDTO;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Exito extends JDialog {
	private static final long serialVersionUID = -6104900696859519163L;
	
	private final JPanel contentPanel = new JPanel();
	
	public Exito(HuespedDTO huesped) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/resources/aH-40px.png")));
		setBounds(100, 100, 324, 356);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/resources/Ha-100px.png")));
		lblNewLabel.setBounds(100, 11, 100, 100);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("¡Los datos fueron guardados!");
		lblNewLabel_1.setForeground(new Color(12, 138, 199));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(27, 122, 322, 21);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("<html><u>Nombre y apellido</u>:</html>");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(27, 162, 322, 21);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(String.format("%s %s", huesped.getNombre(), huesped.getApellido()));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(27, 192, 322, 21);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("<html><u>Número de reserva</u>:</html>");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4.setBounds(27, 222, 322, 21);
		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(huesped.getReserva().getId().toString());
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(27, 252, 322, 21);
		contentPanel.add(lblNewLabel_5);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

}
