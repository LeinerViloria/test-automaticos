package edu.cecar.modelos;

import java.sql.Date;

public class viaje {
	private String identificacion;
	private String identificacion_asignacion;
	private String codigo_ruta;
	private Date fecha;
	
	public viaje(String identificacion, String identificacion_asignacion, String codigo_ruta, Date fecha) {
		super();
		this.identificacion = identificacion;
		this.identificacion_asignacion = identificacion_asignacion;
		this.codigo_ruta = codigo_ruta;
		this.fecha = fecha;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getIdentificacion_asignacion() {
		return identificacion_asignacion;
	}
	public void setIdentificacion_asignacion(String identificacion_asignacion) {
		this.identificacion_asignacion = identificacion_asignacion;
	}
	public String getCodigo_ruta() {
		return codigo_ruta;
	}
	public void setCodigo_ruta(String codigo_ruta) {
		this.codigo_ruta = codigo_ruta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
		
}
