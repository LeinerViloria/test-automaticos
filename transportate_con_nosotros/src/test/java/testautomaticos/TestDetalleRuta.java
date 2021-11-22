package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorComunicacion;
import edu.cecar.controladores.controladorDetalleRuta;
import edu.cecar.modelos.comunicacion;
import edu.cecar.modelos.detalle_ruta;

public class TestDetalleRuta {
	public TestDetalleRuta() {
		
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarDetalleRuta() {
		try {
			detalle_ruta detalle_ruta1 =  new detalle_ruta("RU1", "BOG");
			detalle_ruta detalle_ruta2 =  new detalle_ruta("RU1", "SIN");
			detalle_ruta detalle_ruta3 =  new detalle_ruta("RU1", "BARR");
			detalle_ruta detalle_ruta4 =  new detalle_ruta("RU1", "MON");
			
			List<detalle_ruta> detalles_ruta = new ArrayList<detalle_ruta>();
			detalles_ruta.add(detalle_ruta1);
			detalles_ruta.add(detalle_ruta2);
			detalles_ruta.add(detalle_ruta3);
			detalles_ruta.add(detalle_ruta4);
			
			controladorDetalleRuta controladorDetalleRuta = new controladorDetalleRuta();
			boolean resultado = controladorDetalleRuta.guardarRuta(detalles_ruta);
			
			if(resultado==true) {				
				
				List<detalle_ruta> detalles_rutaBD = new ArrayList<detalle_ruta>();
				
				for (detalle_ruta detalle_rutaOriginal : detalles_ruta) {
					detalle_ruta detalle_rutaBD = controladorDetalleRuta.getDetalleRuta(detalle_rutaOriginal.getIdentificacion_ruta(), detalle_rutaOriginal.getCodigo_destino());
					detalles_rutaBD.add(detalle_rutaBD);
				}
										
				assertTrue(isIgualDetalleRuta(detalles_ruta, detalles_rutaBD));
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque ya está ese destino para esa ruta, o, porque la ruta o el destino no existe");
				assertFalse(resultado); 
			}					
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean isIgualDetalleRuta(List<detalle_ruta> detalle_rutaUno, List<detalle_ruta> detalle_rutaDos) {
		
		
		boolean sw = detalle_rutaUno.size() == detalle_rutaDos.size();
		
		for (int i = 0; i < detalle_rutaUno.size() && sw; i++) {

			detalle_ruta detalle_rutaOriginal = detalle_rutaUno.get(i);
			detalle_ruta detalle_rutaBD = detalle_rutaDos.get(i);
			
			sw = detalle_rutaOriginal.getIdentificacion_ruta().equals(detalle_rutaBD.getIdentificacion_ruta()) && detalle_rutaOriginal.getCodigo_destino().equals(detalle_rutaBD.getCodigo_destino());

		}
		
		return sw;
		
	}

}
