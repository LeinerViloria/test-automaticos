package edu.cecar.modelos;

import java.time.Year;
import java.util.List;

public class catalogo_viaje {
	private int identificacion;
	private Year anno;
	private List<ruta> ruta;
	
	public catalogo_viaje() {
		super();
	}

	public catalogo_viaje(int identificacion, Year anno, List<edu.cecar.modelos.ruta> ruta) {
		super();
		this.identificacion = identificacion;
		this.anno = anno;
		this.ruta = ruta;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public Year getAnno() {
		return anno;
	}

	public void setAnno(Year anno) {
		this.anno = anno;
	}

	public List<ruta> getRuta() {
		return ruta;
	}

	public void setRuta(List<ruta> ruta) {
		this.ruta = ruta;
	}
	
	

}
