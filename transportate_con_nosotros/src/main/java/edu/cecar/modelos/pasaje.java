package edu.cecar.modelos;

import java.sql.Date;

public class pasaje {
	private String tiquete;
	private String identificacion_viaje;
	private String cedula_funcionario;
	private Date fecha_venta;
	private int precio;
	
	public pasaje() {
	}
	
	public pasaje(String tiquete, String identificacion_viaje, String cedula_funcionario, Date fecha_venta,
			int precio) {
		super();
		this.tiquete = tiquete;
		this.identificacion_viaje = identificacion_viaje;
		this.cedula_funcionario = cedula_funcionario;
		this.fecha_venta = fecha_venta;
		this.precio = precio;
	}

	public String getTiquete() {
		return tiquete;
	}

	public void setTiquete(String tiquete) {
		this.tiquete = tiquete;
	}

	public String getIdentificacion_viaje() {
		return identificacion_viaje;
	}

	public void setIdentificacion_viaje(String identificacion_viaje) {
		this.identificacion_viaje = identificacion_viaje;
	}

	public String getCedula_funcionario() {
		return cedula_funcionario;
	}

	public void setCedula_funcionario(String cedula_funcionario) {
		this.cedula_funcionario = cedula_funcionario;
	}

	public Date getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}	

}
