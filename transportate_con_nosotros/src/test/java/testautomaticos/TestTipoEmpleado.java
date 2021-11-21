package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorTipoEmpleado;
import edu.cecar.modelos.tipo_empleado;

public class TestTipoEmpleado {
	public TestTipoEmpleado() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarTipoEmpleado() {
		try {			

			tipo_empleado tipo_empleado = new tipo_empleado("2", "Funcionario");		

			controladorTipoEmpleado controladorTipoEmpleado = new controladorTipoEmpleado();
			boolean guardar = controladorTipoEmpleado.guardarTipoEmpleado(tipo_empleado);
			
			if(guardar==true) {
				tipo_empleado tipo_empleadoBD = controladorTipoEmpleado.getTipoEmpleado(tipo_empleado.getCodigo());
							
				assertTrue(tipo_empleado.getCodigo().equals(tipo_empleadoBD.getCodigo())&&tipo_empleado.getNombre().equals(tipo_empleadoBD.getNombre()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se guardó porque ya existe el tipo de empleado "+tipo_empleado.getCodigo());
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
