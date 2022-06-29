package com.alura.hotel.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.dtos.HuespedDTO;
import com.alura.hotel.dtos.ReservaDTO;
import com.alura.hotel.exceptions.HuespedNotFoundException;

public class HuespedDAO {
	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public Boolean save(HuespedDTO huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes(nombre, apellido, fechanacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setTimestamp(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getReserva().getId());

				int updateCount = statement.executeUpdate();

				return updateCount > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	public List<HuespedDTO> listar() {
		List<HuespedDTO> result = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT H.*, R.fechaentrada, R.fechasalida, R.valor, R.formapago FROM huespedes H INNER JOIN reservas R ON R.id = H.id_reserva ORDER BY H.id ASC");

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						ReservaDTO reserva = new ReservaDTO(resultSet.getTimestamp("fechaentrada"),
								resultSet.getTimestamp("fechasalida"), resultSet.getBigDecimal("valor"),
								resultSet.getString("formapago"), resultSet.getInt("id_reserva"));

						HuespedDTO huesped = new HuespedDTO(resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getTimestamp("fechanacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("id"), reserva);

						result.add(huesped);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int update(HuespedDTO huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE huespedes SET nombre = ?, apellido = ?, fechanacimiento = ?, nacionalidad = ?, telefono = ? WHERE id = ?");

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setTimestamp(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId());

				int updateCount = statement.executeUpdate();

				return updateCount;
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	public List<HuespedDTO> findByApellido(String apellido) throws HuespedNotFoundException {
		List<HuespedDTO> huespedes = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT H.*, R.fechaentrada, R.fechasalida, R.valor, R.formapago FROM huespedes H INNER JOIN reservas R ON H.id_reserva = R.id WHERE LOWER(H.apellido) = LOWER(?)");

			try (statement) {
				statement.setString(1, apellido);

				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						ReservaDTO reserva = new ReservaDTO(resultSet.getTimestamp("fechaentrada"),
								resultSet.getTimestamp("fechasalida"), resultSet.getBigDecimal("valor"),
								resultSet.getString("formapago"), resultSet.getInt("id_reserva"));

						HuespedDTO huesped = new HuespedDTO(resultSet.getString("nombre"), resultSet.getString("apellido"),
								resultSet.getTimestamp("fechanacimiento"), resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"), resultSet.getInt("id"), reserva);
						
						huespedes.add(huesped);
					}
				}

				if (huespedes.size() == 0) {
					throw new HuespedNotFoundException();
				}

				return huespedes;
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw new HuespedNotFoundException();
		}
	}

	public HuespedDTO findByReservaId(int reservaId) throws HuespedNotFoundException {
		HuespedDTO huesped = null;

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT H.*, R.fechaentrada, R.fechasalida, R.valor, R.formapago FROM huespedes H INNER JOIN reservas R ON H.id_reserva = R.id WHERE R.id = ? LIMIT 1");

			try (statement) {
				statement.setInt(1, reservaId);

				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						ReservaDTO reserva = new ReservaDTO(resultSet.getTimestamp("fechaentrada"),
								resultSet.getTimestamp("fechasalida"), resultSet.getBigDecimal("valor"),
								resultSet.getString("formapago"), resultSet.getInt("id_reserva"));

						huesped = new HuespedDTO(resultSet.getString("nombre"), resultSet.getString("apellido"),
								resultSet.getTimestamp("fechanacimiento"), resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"), resultSet.getInt("id"), reserva);
					}
				}

				if (huesped == null) {
					throw new HuespedNotFoundException();
				}

				return huesped;
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw new HuespedNotFoundException();
		}
	}
}