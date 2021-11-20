package edu.cecar.modelos;

public class empleado {
	private String cedula;
	private String codigo_tipo_empleado;
	private String nombres;
	private String apellidos;
	
	public empleado(String cedula, String codigo_tipo_empleado, String nombres, String apellidos) {
		super();
		this.cedula = cedula;
		this.codigo_tipo_empleado = codigo_tipo_empleado;
		this.nombres = nombres;
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCodigo_tipo_empleado() {
		return codigo_tipo_empleado;
	}

	public void setCodigo_tipo_empleado(String codigo_tipo_empleado) {
		this.codigo_tipo_empleado = codigo_tipo_empleado;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}	

}
