package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorTipoComunicacion;
import edu.cecar.controladores.controladorTipoEmpleado;
import edu.cecar.modelos.tipo_comunicacion;
import edu.cecar.modelos.tipo_empleado;

public class TestTipoComunicacion {
	public TestTipoComunicacion() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarTipoComunicacion() {
		try {			

			tipo_comunicacion tipo_comunicacion = new tipo_comunicacion("3", "Fijo");		

			controladorTipoComunicacion controladorTipoComunicacion = new controladorTipoComunicacion();
			boolean guardar = controladorTipoComunicacion.guardarTipoDeComunicacion(tipo_comunicacion);
			
			if(guardar==true) {				
				tipo_comunicacion tipo_comunicacionBD = controladorTipoComunicacion.getTipoComunicacion(tipo_comunicacion.getIdentificacion());
							
				assertTrue(tipo_comunicacion.getIdentificacion().equals(tipo_comunicacionBD.getIdentificacion())&&tipo_comunicacion.getNombre().equals(tipo_comunicacionBD.getNombre()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se guardó porque ya existe el tipo de comunicacion "+tipo_comunicacion.getIdentificacion());
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
