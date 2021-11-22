package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorMedioTransporte;
import edu.cecar.modelos.medio_transporte;

public class TestMedioTransporte {
	public TestMedioTransporte() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarMedioTransporte() {
		try {			

			medio_transporte medio_transporte = new medio_transporte("1", "Van");		

			controladorMedioTransporte controladorMedioTransporte = new controladorMedioTransporte();
			boolean guardar = controladorMedioTransporte.guardarMedioTransporte(medio_transporte);
			
			if(guardar==true) {				
				medio_transporte medio_transporteBD = controladorMedioTransporte.getMedioTransporte(medio_transporte.getCodigo());
							
				assertTrue(medio_transporte.getCodigo().equals(medio_transporteBD.getCodigo())&&medio_transporte.getNombre().equals(medio_transporteBD.getNombre()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se guardó porque ya existe el medio de transporte "+medio_transporte.getCodigo());
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
