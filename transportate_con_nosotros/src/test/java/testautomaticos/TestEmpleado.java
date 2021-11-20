package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorEmpleado;
import edu.cecar.controladores.controladorTipoEmpleado;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.tipo_empleado;

public class TestEmpleado {
	public TestEmpleado() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarEmpleado() {
		try {			

			empleado empleado = new empleado("1090", "2", "Andres", "Benitez");		

			controladorEmpleado controladorEmpleado = new controladorEmpleado();
			boolean guardar = controladorEmpleado.guardarEmpleado(empleado);
			
			if(guardar==true) {
				empleado empleadoBD = controladorEmpleado.getEmpleado(empleado.getCedula());
							
				assertTrue(empleado.getCedula().equals(empleadoBD.getCedula())&&empleado.getCodigo_tipo_empleado().equals(empleadoBD.getCodigo_tipo_empleado())&&empleado.getNombres().equals(empleadoBD.getNombres())&&empleado.getApellidos().equals(empleadoBD.getApellidos()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque ya existe");
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
