package edu.cecar.modelos;

import java.sql.Date;

public class asignacion {
	private String identificacion;
	private String cedula_empleado;
	private Date fecha;
	private String placa_transporte;
	
	public asignacion() {	
	}
	
	public asignacion(String identificacion, String cedula_empleado, Date fecha, String placa_transporte) {
		super();
		this.identificacion = identificacion;
		this.cedula_empleado = cedula_empleado;
		this.fecha = fecha;
		this.placa_transporte = placa_transporte;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCedula_empleado() {
		return cedula_empleado;
	}

	public void setCedula_empleado(String cedula_empleado) {
		this.cedula_empleado = cedula_empleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPlaca_transporte() {
		return placa_transporte;
	}

	public void setPlaca_transporte(String placa_transporte) {
		this.placa_transporte = placa_transporte;
	}
	
	
}
