package com.alura.hotel.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controllers.HuespedController;
import com.alura.hotel.controllers.ReservaController;
import com.alura.hotel.dtos.HuespedDTO;
import com.alura.hotel.dtos.ReservaDTO;
import com.alura.hotel.exceptions.HuespedNotFoundException;
import com.alura.hotel.util.Sanitize;
import com.alura.hotel.util.Validate;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class Busqueda extends JFrame {
	private static final long serialVersionUID = 564142074415934780L;

	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable huespedesTable, reservasTable;
	private DefaultTableModel huespedesModel, reservasModel;
	private JTabbedPane tabbedPane;

	private boolean isSearchActive = false;

	private HuespedController huespedController;
	private ReservaController reservaController;
	
	public Busqueda() {
		this.huespedController = new HuespedController();
		this.reservaController = new ReservaController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/resources/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(757, 80, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton();
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/resources/lupa2.png")));
		btnBuscar.setBounds(925, 75, 54, 41);
		contentPane.add(btnBuscar);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Puedes buscar a los huéspedes por apellido o número de reservación.");
		lblNewLabel_5.setForeground(new Color(12, 138, 199));
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(155, 65, 500, 42);
		contentPane.add(lblNewLabel_5);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 127, 974, 265);
		contentPane.add(tabbedPane);

		huespedesTable = new JTable(new DefaultTableModel() {
			private static final long serialVersionUID = 5216737738525443440L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column > 0 && column < 6;
			}
		});
		huespedesTable.setFont(new Font("Arial", Font.PLAIN, 14));
		huespedesModel = (DefaultTableModel) huespedesTable.getModel();
		huespedesModel.addColumn("Id. de huesped");
		huespedesModel.addColumn("Nombre");
		huespedesModel.addColumn("Apellido");
		huespedesModel.addColumn("Fecha nacimiento");
		huespedesModel.addColumn("Nacionalidad");
		huespedesModel.addColumn("Teléfono");
		huespedesModel.addColumn("Id. de reserva");

		tabbedPane.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/resources/persona.png")),
				new JScrollPane(huespedesTable), null);

		reservasTable = new JTable(new DefaultTableModel() {
			private static final long serialVersionUID = -679533863713654153L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column > 0 && column < 5;
			}
		});
		reservasTable.setFont(new Font("Arial", Font.PLAIN, 14));
		reservasModel = (DefaultTableModel) reservasTable.getModel();
		reservasModel.addColumn("Id. de reserva");
		reservasModel.addColumn("Fecha de entrada");
		reservasModel.addColumn("Fecha de salida");
		reservasModel.addColumn("Valor");
		reservasModel.addColumn("Forma de pago");
		reservasModel.addColumn("Id. de huesped");

		tabbedPane.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/resources/calendario.png")),
				new JScrollPane(reservasTable), null);

		JButton btnEditar = new JButton();
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/resources/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(717, 416, 54, 41);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});

		JButton btnEliminar = new JButton();
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/resources/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(781, 416, 54, 41);
		contentPane.add(btnEliminar);

		JButton btnSalir = new JButton();
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/resources/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(930, 416, 54, 41);
		contentPane.add(btnSalir);

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/resources/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);

		loadTables();

		setResizable(false);
	}

	private boolean hasSelectedRow(int activeTabIndex) {
		return (activeTabIndex == 0 && huespedesTable.getSelectedRowCount() > 0)
				|| (activeTabIndex == 1 && reservasTable.getSelectedRowCount() > 0);
	}

	private void editar() {
		int activeTabIndex = tabbedPane.getSelectedIndex();

		if (!hasSelectedRow(activeTabIndex)) {
			JOptionPane.showMessageDialog(this, "Por favor, elige un item", "Error de datos",
					JOptionPane.ERROR_MESSAGE);

			return;
		}

		if (activeTabIndex == 0) {
			Optional.ofNullable(
					huespedesModel.getValueAt(huespedesTable.getSelectedRow(), huespedesTable.getSelectedColumn()))
					.ifPresent(row -> {
						int id = Integer
								.valueOf(huespedesModel.getValueAt(huespedesTable.getSelectedRow(), 0).toString());
						String nombre = huespedesModel.getValueAt(huespedesTable.getSelectedRow(), 1).toString();
						String apellido = huespedesModel.getValueAt(huespedesTable.getSelectedRow(), 2).toString();
						String fechaNacimiento = huespedesModel.getValueAt(huespedesTable.getSelectedRow(), 3)
								.toString();
						String nacionalidad = huespedesModel.getValueAt(huespedesTable.getSelectedRow(), 4).toString();
						String telefono = Sanitize
								.phoneNumber(huespedesModel.getValueAt(huespedesTable.getSelectedRow(), 5).toString());

						if (nombre.isBlank() || apellido.isBlank() || fechaNacimiento.isBlank()
								|| nacionalidad.isBlank() || telefono.isBlank()) {
							JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válido.", "Error de datos",
									JOptionPane.ERROR_MESSAGE);

							return;
						}

						if (!Validate.dateFormat(DATE_FORMAT, fechaNacimiento)) {
							JOptionPane.showMessageDialog(this,
									"Por favor, ingresa una fecha de nacimiento válida.\n\nRecuerda respetar el formato: dd/mm/yyyy hh:mm:ss",
									"Error de datos", JOptionPane.ERROR_MESSAGE);

							return;
						}

						if (!Validate.phoneNumber(telefono)) {
							JOptionPane.showMessageDialog(this, "Por favor, ingresa un número de teléfono válido.",
									"Error de datos", JOptionPane.ERROR_MESSAGE);

							return;
						}

						SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
						Timestamp fechaTimestamp;

						// Estoy obligado a encerrarlo en un try/catch pero ya sabemos qué la fecha es
						// válida. Quizás haya una mejor forma de hacerlo.
						// https://stackoverflow.com/q/2742380/10942774
						try {
							fechaTimestamp = new Timestamp(dateFormat.parse(fechaNacimiento).getTime());
						} catch (ParseException e) {
							e.printStackTrace();

							return;
						}

						HuespedDTO huesped = new HuespedDTO(nombre, apellido, fechaTimestamp, nacionalidad, telefono,
								id);

						int rowsAffected = this.huespedController.update(huesped);

						JOptionPane.showMessageDialog(this, String.format("Se editó %s huesped(s).", rowsAffected));

						// Para evitar enviar queries a la base de datos cada vez que el usuario comete
						// un error es mucho mejor recargar la tabla cuando se hace el commit
						// correctamente.
						reloadTables();
					});
		} else {
			Optional.ofNullable(
					huespedesModel.getValueAt(reservasTable.getSelectedRow(), reservasTable.getSelectedColumn()))
					.ifPresent(row -> {
						int id = Integer
								.valueOf(reservasModel.getValueAt(reservasTable.getSelectedRow(), 0).toString());
						String fechaEntrada = reservasModel.getValueAt(reservasTable.getSelectedRow(), 1).toString();
						String fechaSalida = reservasModel.getValueAt(reservasTable.getSelectedRow(), 2).toString();
						String valor = reservasModel.getValueAt(reservasTable.getSelectedRow(), 3).toString();
						String formaPago = reservasModel.getValueAt(reservasTable.getSelectedRow(), 4).toString();

						if (fechaEntrada.isBlank() || fechaSalida.isBlank() || valor.isBlank() || formaPago.isBlank()) {
							JOptionPane.showMessageDialog(this, "Por favor asegúrese de completar todos los campos.",
									"Error de datos", JOptionPane.ERROR_MESSAGE);

							return;
						}

						if (!Validate.dateFormat(DATE_FORMAT, fechaEntrada)
								|| !Validate.dateFormat(DATE_FORMAT, fechaSalida)) {
							JOptionPane.showMessageDialog(this,
									"Por favor, ingrese fechas válidas.\\n\\nRecuerda respetar el formato: dd/mm/yyyy hh:mm:ss",
									"Error de datos", JOptionPane.ERROR_MESSAGE);

							return;
						}

						BigDecimal valorReserva;

						try {
							valorReserva = new BigDecimal(valor);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor número válido.",
									"Error de datos", JOptionPane.ERROR_MESSAGE);

							return;
						}

						SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
						Timestamp entradaTimestamp, salidaTimestamp;

						try {
							entradaTimestamp = new Timestamp(dateFormat.parse(fechaEntrada).getTime());
							salidaTimestamp = new Timestamp(dateFormat.parse(fechaSalida).getTime());
						} catch (ParseException e) {
							e.printStackTrace();

							return;
						}

						ReservaDTO reserva = new ReservaDTO(entradaTimestamp, salidaTimestamp, valorReserva, formaPago,
								id);

						int rowsAffected = this.reservaController.update(reserva);

						JOptionPane.showMessageDialog(this, String.format("Se editó %s reserva(s).", rowsAffected));

						// Para enviar queries a la base de datos cada vez que el usuario comete un
						// error es mucho mejor recargar la tabla cuando se hace el commit
						// correctamente.
						reloadTables();
					});
		}
	}

	private void eliminar() {
		int activeTabIndex = tabbedPane.getSelectedIndex();

		if (!hasSelectedRow(activeTabIndex)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item", "Error de datos",
					JOptionPane.ERROR_MESSAGE);

			return;
		}

		DefaultTableModel model;
		JTable table;
		int columnIndex;

		// De esta forma es más legible que usando ternary operator xd
		if (activeTabIndex == 0) {
			model = huespedesModel;
			table = huespedesTable;
			columnIndex = 6;
		} else {
			model = reservasModel;
			table = reservasTable;
			columnIndex = 0;
		}

		Optional.ofNullable(model.getValueAt(table.getSelectedRow(), table.getSelectedColumn())).ifPresent(row -> {
			int reservaId = Integer.valueOf(model.getValueAt(table.getSelectedRow(), columnIndex).toString());

			int deleteCount = this.reservaController.delete(reservaId);

			if (deleteCount > 0) {
				JOptionPane.showMessageDialog(this, "Se eliminó correctamente el elemento.");
			} else {
				JOptionPane.showMessageDialog(this, "No pudimos eliminar el elemento, ya que no existe.");
			}

			reloadTables();

			// No se re-renderiza automáticamente la tabla cuando se modifica el vector de
			// rows de tableModel. Hay que provocar el re-render manualmente xd
			forceTableRender();
		});
	}

	private void buscar() {
		String terminoABuscar = txtBuscar.getText();

		// Existen dos critério de búsqueda: apellido y número de Reserva
		if (!terminoABuscar.isBlank()) {
			isSearchActive = true;

			clearTables();

			try {
				if (Validate.isInteger(terminoABuscar)) {
					HuespedDTO huesped = this.huespedController.findByReservaId(Integer.valueOf(terminoABuscar));

					ReservaDTO reserva = huesped.getReserva();

					huespedesModel.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
							new SimpleDateFormat(DATE_FORMAT).format(huesped.getFechaNacimiento()),
							huesped.getNacionalidad(), huesped.getTelefono(), reserva.getId(), });

					reservasModel.addRow(new Object[] { reserva.getId(),
							new SimpleDateFormat(DATE_FORMAT).format(reserva.getFechaEntrada()),
							new SimpleDateFormat(DATE_FORMAT).format(reserva.getFechaSalida()), reserva.getValor(),
							reserva.getFormaPago(), huesped.getId() });
				} else {
					List<HuespedDTO> huespedes = this.huespedController.findByApellido(terminoABuscar);
					
					huespedes.forEach(huesped -> {
						ReservaDTO reserva = huesped.getReserva();
						
						huespedesModel.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
								new SimpleDateFormat(DATE_FORMAT).format(huesped.getFechaNacimiento()),
								huesped.getNacionalidad(), huesped.getTelefono(), reserva.getId(), });

						reservasModel.addRow(new Object[] { reserva.getId(),
								new SimpleDateFormat(DATE_FORMAT).format(reserva.getFechaEntrada()),
								new SimpleDateFormat(DATE_FORMAT).format(reserva.getFechaSalida()), reserva.getValor(),
								reserva.getFormaPago(), huesped.getId() });
					});
				}
			} catch (HuespedNotFoundException e) {
				forceTableRender();
			}
		} else if (isSearchActive) {
			isSearchActive = false;

			reloadTables();
		}
	}

	private void reloadTables() {
		clearTables();
		loadTables();
	}

	private void forceTableRender() {
		huespedesModel.fireTableDataChanged();
		reservasModel.fireTableDataChanged();
	}

	private void clearTables() {
		huespedesModel.getDataVector().clear();
		reservasModel.getDataVector().clear();
	}

	private void loadTables() {
		List<HuespedDTO> huespedes = this.huespedController.listar();

		huespedes.forEach(huesped -> {
			ReservaDTO reserva = huesped.getReserva();

			huespedesModel.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					new SimpleDateFormat(DATE_FORMAT).format(huesped.getFechaNacimiento()), huesped.getNacionalidad(),
					huesped.getTelefono(), reserva.getId(), });

			reservasModel.addRow(
					new Object[] { reserva.getId(), new SimpleDateFormat(DATE_FORMAT).format(reserva.getFechaEntrada()),
							new SimpleDateFormat(DATE_FORMAT).format(reserva.getFechaSalida()), reserva.getValor(),
							reserva.getFormaPago(), huesped.getId() });
		});
	}
}
