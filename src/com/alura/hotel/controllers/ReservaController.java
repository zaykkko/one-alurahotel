package com.alura.hotel.controllers;

import com.alura.hotel.daos.ReservaDAO;
import com.alura.hotel.dtos.ReservaDTO;
import com.alura.hotel.factories.ConnectionFactory;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().getConnection());
	}

	public Boolean save(ReservaDTO reserva) {
		return reservaDAO.save(reserva);
	}

	public int update(ReservaDTO reserva) {
		return reservaDAO.update(reserva);
	}

	public int delete(Integer reservaId) {
		return reservaDAO.delete(reservaId);
	}
}
