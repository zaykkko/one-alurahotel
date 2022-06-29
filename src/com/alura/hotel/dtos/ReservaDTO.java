package com.alura.hotel.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ReservaDTO implements Serializable {
	private static final long serialVersionUID = -8167574333706729567L;
	
	private Integer id;
	private Timestamp fechaEntrada;
	private Timestamp fechaSalida;
	private BigDecimal valor;
	private String formaPago;

	public ReservaDTO(Timestamp fechaEntrada, Timestamp fechaSalida, BigDecimal valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public ReservaDTO(Timestamp fechaEntrada, Timestamp fechaSalida, BigDecimal valor, String formaPago, Integer id) {
		this(fechaEntrada, fechaSalida, valor, formaPago);
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public Timestamp getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Timestamp fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Timestamp getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
}
