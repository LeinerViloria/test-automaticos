package edu.cecar.modelos;

public class comunicacion {
	private String cedula_empleado;
	private String id_tipo;
	private String numero;
	public comunicacion(String cedula_empleado, String id_tipo, String numero) {
		super();
		this.cedula_empleado = cedula_empleado;
		this.id_tipo = id_tipo;
		this.numero = numero;
	}
	
	public String getCedula_empleado() {
		return cedula_empleado;
	}
	public void setCedula_empleado(String cedula_empleado) {
		this.cedula_empleado = cedula_empleado;
	}
	public String getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(String id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
