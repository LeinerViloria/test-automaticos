package edu.cecar.modelos;

public class transporte {
	private String placa;
	private String codigo_transporte;
	
	public transporte() {
		
	}
	
	public transporte(String placa, String codigo_transporte) {
		super();
		this.placa = placa;
		this.codigo_transporte = codigo_transporte;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCodigo_transporte() {
		return codigo_transporte;
	}

	public void setCodigo_transporte(String codigo_transporte) {
		this.codigo_transporte = codigo_transporte;
	}
	
	
}
