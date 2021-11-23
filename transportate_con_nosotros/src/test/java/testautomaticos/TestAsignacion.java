package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorAsignacion;
import edu.cecar.modelos.asignacion;

public class TestAsignacion {
	public TestAsignacion() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarAsignacion() {
		try {			

			asignacion asignacion = new asignacion();
			asignacion.setIdentificacion("2");
			asignacion.setCedula_empleado("1009");
			asignacion.setPlaca_transporte("KOk-090");

			controladorAsignacion controladorAsignacion = new controladorAsignacion();
			boolean guardar = controladorAsignacion.guardarAsignacion(asignacion);
			
			if(guardar==true) {
				asignacion asignacionBD = controladorAsignacion.getAsignacion(asignacion.getIdentificacion());
							
				assertTrue(asignacion.getIdentificacion().equals(asignacionBD.getIdentificacion())&&asignacion.getCedula_empleado().equals(asignacionBD.getCedula_empleado())&&asignacion.getPlaca_transporte().equals(asignacionBD.getPlaca_transporte()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque ya existe o por inconsistencias en los datos");
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			if(e.getMessage().contains("Duplicate entry")) {
				System.out.println(e.getMessage()+"\n\nEse empleado ya tiene asignacion para hoy");
			}else {
				e.printStackTrace();
			}		
		}
	}

}
