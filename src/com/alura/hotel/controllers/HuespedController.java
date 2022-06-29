package com.alura.hotel.controllers;

import java.util.List;

import com.alura.hotel.daos.HuespedDAO;
import com.alura.hotel.dtos.HuespedDTO;
import com.alura.hotel.exceptions.HuespedNotFoundException;
import com.alura.hotel.factories.ConnectionFactory;

public class HuespedController {
	private HuespedDAO huespedDAO;

	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().getConnection());
	}

	public boolean save(HuespedDTO huesped) {
		return huespedDAO.save(huesped);
	}

	public List<HuespedDTO> listar() {
		return huespedDAO.listar();
	}

	public int update(HuespedDTO huesped) {
		return huespedDAO.update(huesped);
	}

	public HuespedDTO findByReservaId(int reservaId) throws HuespedNotFoundException {
		return huespedDAO.findByReservaId(reservaId);
	}

	public List<HuespedDTO> findByApellido(String apellido) throws HuespedNotFoundException {
		return huespedDAO.findByApellido(apellido);
	}
}
