package edu.cecar.modelos;

import java.util.List;

public class ruta {
	
	private String codigo;
	private int identificacion_catalogo;
	private String identificacion_origen;
	private String identificacion_destino;
	private String formaRuta;
	private int precio;
	private List<detalle_ruta> detalle_ruta;		

	public ruta() {
		super();
	}

	public ruta(String codigo, int identificacion_catalogo, String identificacion_origen, String identificacion_destino,
			String formaRuta, int precio) {
		super();
		this.codigo = codigo;
		this.identificacion_catalogo = identificacion_catalogo;
		this.identificacion_origen = identificacion_origen;
		this.identificacion_destino = identificacion_destino;
		this.formaRuta = formaRuta;
		this.precio = precio;
	}

	@Override
	public boolean equals(Object obj) {
		
		ruta rutaO = (ruta)obj;
		
		return rutaO.getIdentificacion_catalogo() == identificacion_catalogo;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getIdentificacion_catalogo() {
		return identificacion_catalogo;
	}

	public void setIdentificacion_catalogo(int identificacion_catalogo) {
		this.identificacion_catalogo = identificacion_catalogo;
	}

	public String getIdentificacion_origen() {
		return identificacion_origen;
	}

	public void setIdentificacion_origen(String identificacion_origen) {
		this.identificacion_origen = identificacion_origen;
	}

	public String getIdentificacion_destino() {
		return identificacion_destino;
	}

	public void setIdentificacion_destino(String identificacion_destino) {
		this.identificacion_destino = identificacion_destino;
	}

	public String getFormaRuta() {
		return formaRuta;
	}

	public void setFormaRuta(String formaRuta) {
		this.formaRuta = formaRuta;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}	
	
	public List<detalle_ruta> getDetalle_ruta() {
		return detalle_ruta;
	}

	public void setDetalle_ruta(List<detalle_ruta> detalle_ruta) {
		this.detalle_ruta = detalle_ruta;
	}

}
