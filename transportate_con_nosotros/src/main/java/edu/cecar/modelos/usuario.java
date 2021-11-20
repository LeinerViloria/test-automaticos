package edu.cecar.modelos;

public class usuario {
	private String cedula_funcionario;
	private String email;
	private String contrasena;
	public usuario(String cedula_funcionario, String email, String contrasena) {
		super();
		this.cedula_funcionario = cedula_funcionario;
		this.email = email;
		this.contrasena = contrasena;
	}
	public String getCedula_funcionario() {
		return cedula_funcionario;
	}
	public void setCedula_funcionario(String cedula_funcionario) {
		this.cedula_funcionario = cedula_funcionario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
