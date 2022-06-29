package com.alura.hotel.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class HuespedDTO implements Serializable {
	private static final long serialVersionUID = 5156910835402656737L;
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Timestamp fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private transient ReservaDTO reserva;

	public HuespedDTO(String nombre, String apellido, Timestamp fechaNacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}

	public HuespedDTO(String nombre, String apellido, Timestamp fechaNacimiento, String nacionalidad, String telefono,
			ReservaDTO reserva) {
		this(nombre, apellido, fechaNacimiento, nacionalidad, telefono);
		this.reserva = reserva;
	}

	public HuespedDTO(String nombre, String apellido, Timestamp fechaNacimiento, String nacionalidad, String telefono,
			Integer id) {
		this(nombre, apellido, fechaNacimiento, nacionalidad, telefono);
		this.id = id;
	}

	public HuespedDTO(String nombre, String apellido, Timestamp fechaNacimiento, String nacionalidad, String telefono,
			Integer id, ReservaDTO reserva) {
		this(nombre, apellido, fechaNacimiento, nacionalidad, telefono, id);
		this.reserva = reserva;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ReservaDTO getReserva() {
		return reserva;
	}

	public void setIdReserva(ReservaDTO idReserva) {
		this.reserva = idReserva;
	}
}
