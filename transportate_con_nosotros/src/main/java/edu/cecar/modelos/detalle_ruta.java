package edu.cecar.modelos;

public class detalle_ruta {
	private String identificacion_ruta;
	private String codigo_destino;
	
	public detalle_ruta(String identificacion_ruta, String codigo_destino) {
		super();
		this.identificacion_ruta = identificacion_ruta;
		this.codigo_destino = codigo_destino;
	}

	public String getIdentificacion_ruta() {
		return identificacion_ruta;
	}

	public void setIdentificacion_ruta(String identificacion_ruta) {
		this.identificacion_ruta = identificacion_ruta;
	}

	public String getCodigo_destino() {
		return codigo_destino;
	}

	public void setCodigo_destino(String codigo_destino) {
		this.codigo_destino = codigo_destino;
	}
	
}
