package com.alura.hotel.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.hotel.dtos.ReservaDTO;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public Boolean save(ReservaDTO reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas(fechaentrada, fechasalida, valor, formapago) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setTimestamp(1, reserva.getFechaSalida());
				statement.setTimestamp(2, reserva.getFechaEntrada());
				statement.setBigDecimal(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());

				int updateCount = statement.executeUpdate();

				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}

				return updateCount > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	public int update(ReservaDTO reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE reservas SET fechaentrada = ?, fechasalida = ?, valor = ?, formapago = ? WHERE id = ?");

			try (statement) {
				statement.setTimestamp(1, reserva.getFechaEntrada());
				statement.setTimestamp(2, reserva.getFechaSalida());
				statement.setBigDecimal(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getId());

				int updateCount = statement.executeUpdate();

				return updateCount;
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	public int delete(Integer reservaId) {
		// Aprovechamos la clave foránea y la relación que hay entre Huesped.id_reserva
		// y Reserva.id y la opción "ON DELTE CASCADE", q al eliminar una reserva
		// también eliminará al huesped.
		// https://stackoverflow.com/a/14141354/10942774
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");

			try (statement) {
				statement.setInt(1, reservaId);

				return statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}
}
