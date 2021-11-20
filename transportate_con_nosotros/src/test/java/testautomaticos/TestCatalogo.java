package testautomaticos;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorCatalogo;
import edu.cecar.modelos.catalogo_viaje;
import edu.cecar.modelos.ruta;

public class TestCatalogo {
	
	public TestCatalogo() {

		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarCatalogo() {
		try {
			
			ruta ruta1 = new ruta();
			ruta1.setCodigo("RU10");
			ruta1.setIdentificacion_origen("SIN");
			ruta1.setIdentificacion_destino("MON");
			ruta1.setFormaRuta("Directo");
			ruta1.setPrecio(2000);
			
			ruta ruta2 = new ruta();
			ruta2.setCodigo("RU11");
			ruta2.setIdentificacion_origen("MON");
			ruta2.setIdentificacion_destino("SIN");
			ruta2.setFormaRuta("Directo");
			ruta2.setPrecio(2000);
			
			List<ruta> rutas = new ArrayList<ruta>();
			rutas.add(ruta1);
			rutas.add(ruta2);
						
			
			catalogo_viaje catalogo_viaje = new catalogo_viaje();
			catalogo_viaje.setRuta(rutas);
			
			controladorCatalogo controladorCatalogo = new controladorCatalogo();
			controladorCatalogo.guardarCatalogo(catalogo_viaje);
			
			catalogo_viaje catalogo_viajeBD = controladorCatalogo.getUltimoCatalogo();

			//assertEquals("El dato a insertar no coincide con el insertado",catalogo_viaje.getRuta(),catalogo_viajeBD.getRuta());						
			assertTrue(isIgualDetalle(catalogo_viaje.getRuta(), catalogo_viajeBD.getRuta()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private boolean isIgualDetalle(List<ruta> rutauno, List<ruta> rutados) {
		
		
		boolean sw = rutauno.size() == rutados.size();
		
		for (int i = 0; i < rutauno.size() && sw; i++) {

			ruta rutaOriginal = rutauno.get(i);
			ruta rutaBD = rutados.get(i);
			
			sw = rutaOriginal.getCodigo().equals(rutaBD.getCodigo()) && rutaOriginal.getIdentificacion_destino().equals(rutaBD.getIdentificacion_destino()) && rutaOriginal.getIdentificacion_origen().equals(rutaBD.getIdentificacion_origen()) && rutaOriginal.getFormaRuta().equals(rutaBD.getFormaRuta()) && rutaOriginal.getPrecio()==rutaBD.getPrecio();

		}
		
		return sw;
		
	}
}
