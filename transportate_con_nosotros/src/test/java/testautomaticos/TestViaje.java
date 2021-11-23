package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorEmpleado;
import edu.cecar.controladores.controladorViaje;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.viaje;

public class TestViaje {
	public TestViaje() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarViaje() {
		try {			
			String fecha_viaje = "2021-11-23";							
			
			viaje viaje = new viaje("ANN-1", "1", "RU1", Date.valueOf(fecha_viaje));		

			controladorViaje controladorViaje = new controladorViaje();
			boolean guardar = controladorViaje.guardarViaje(viaje);
			
			if(guardar==true) {
				viaje viajeBD = controladorViaje.getViaje(viaje.getIdentificacion());
							
				assertTrue(viaje.getIdentificacion().equals(viajeBD.getIdentificacion())&&viaje.getIdentificacion_asignacion().equals(viajeBD.getIdentificacion_asignacion())&&viaje.getCodigo_ruta().equals(viajeBD.getCodigo_ruta())&&viaje.getFecha().equals(viajeBD.getFecha()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque este viaje ya existe o hay inconsistencia de datos");
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
