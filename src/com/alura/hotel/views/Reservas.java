package com.alura.hotel.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.alura.hotel.dtos.ReservaDTO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Reservas extends JFrame {
	private static final long serialVersionUID = 5698394712562481242L;
	
	private JPanel contentPane;
	private JTextField txtValor;

	/**
	 * Create the frame.
	 */
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/resources/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		JDateChooser txtFechaE = new JDateChooser();
		txtFechaE.setBounds(88, 166, 235, 33);
		panel.add(txtFechaE);

		JLabel lblNewLabel_1 = new JLabel("Fecha de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		JDateChooser txtFechaS = new JDateChooser();
		txtFechaS.setBounds(88, 234, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaS);

		JTextFieldDateEditor fechaEEditor = (JTextFieldDateEditor) txtFechaE.getDateEditor();
		fechaEEditor.setEditable(false);

		JTextFieldDateEditor fechaSEditor = (JTextFieldDateEditor) txtFechaS.getDateEditor();
		fechaSEditor.setEditable(false);

		txtValor = new JTextField();
		txtValor.setBounds(88, 303, 235, 33);
		panel.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Valor de la Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);

		JComboBox<String> txtFormaPago = new JComboBox<>();
		txtFormaPago.setBounds(88, 373, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 133, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar fechaECalendar = txtFechaE.getCalendar();
				Calendar fechaSCalendar = txtFechaS.getCalendar();

				if (fechaECalendar == null || fechaSCalendar == null) {
					JOptionPane.showMessageDialog(null, "Por favor introduce una fecha de entrada y salida.",
							"Datos incorrectos", JOptionPane.ERROR_MESSAGE);
					return;
				}

				BigDecimal valor;

				try {
					valor = new BigDecimal(txtValor.getText());
				} catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(null, "Por favor introduce un valor que sea numérico.",
							"Datos incorrectos", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String formaDePago = (String) txtFormaPago.getSelectedItem();
				
				Timestamp entradaTimestamp = new Timestamp(fechaECalendar.getTimeInMillis());
				Timestamp salidaTimestamp = new Timestamp(fechaSCalendar.getTimeInMillis());

				ReservaDTO reserva = new ReservaDTO(entradaTimestamp, salidaTimestamp, valor, formaDePago);

				RegistroHuesped huesped = new RegistroHuesped(reserva);
				huesped.setVisible(true);
				dispose();
			}
		});
		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(183, 436, 140, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/resources/calendario.png")));
		btnReservar.setBackground(new Color(65, 105, 225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);
		
		JButton btnSalir = new JButton();
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/resources/cerrar-sesion 32-px.png")));
		btnSalir.setBackground(SystemColor.menu);
		btnSalir.setBounds(824, 446, 54, 41);
		panel.add(btnSalir);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/resources/reservas-img-2.png")));

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/resources/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
