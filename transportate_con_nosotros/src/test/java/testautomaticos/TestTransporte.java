package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorEmpleado;
import edu.cecar.controladores.controladorTransporte;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.transporte;

public class TestTransporte {
	public TestTransporte() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGestionarTransporte() {
		try {			

			transporte transporte = new transporte("KOK-090", "1");		

			controladorTransporte controladorTransporte = new controladorTransporte();
			boolean guardar = controladorTransporte.guardarTransporte(transporte);
			
			if(guardar==true) {
				transporte transporteBD = controladorTransporte.getTransporte(transporte.getPlaca());
							
				assertTrue(transporte.getPlaca().equals(transporteBD.getPlaca())&&transporte.getCodigo_transporte().equals(transporteBD.getCodigo_transporte()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque el transporte '"+transporte.getPlaca()+"' ya existe, o, porque el codigo de transporte no existe");
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	

}
