package testautomaticos;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorDestino;
import edu.cecar.modelos.destino;

public class TestDestino {
	
	public TestDestino() {

		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	@Test
	public void testGestionarDestino() {		
		try {			

			destino destino = new destino("BOG", "Bogota");		

			controladorDestino ControladorDestino = new controladorDestino();
			ControladorDestino.guardarDestino(destino);
			
			destino destinoBD = ControladorDestino.getDestino(destino.getId());
			
			//assertEquals("El dato a insertar no coincide con el insertado",destino,destinoBD);
			assertEquals(destino.getId(), destinoBD.getId());
			assertEquals(destino.getNombre(), destinoBD.getNombre());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
