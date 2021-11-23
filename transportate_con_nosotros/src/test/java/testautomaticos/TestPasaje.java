package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorPasaje;
import edu.cecar.modelos.pasaje;

public class TestPasaje {
	public TestPasaje() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarPasaje() {
		try {			

			pasaje pasaje = new pasaje();
			pasaje.setTiquete("AAA");
			pasaje.setIdentificacion_viaje("ANN-1");
			pasaje.setCedula_funcionario("1090");

			controladorPasaje controladorPasaje = new controladorPasaje();
			boolean guardar = controladorPasaje.guardarPasaje(pasaje);
			
			if(guardar==true) {
				pasaje pasajeBD = controladorPasaje.getPasaje(pasaje.getTiquete());
							
				assertTrue(pasaje.getTiquete().equals(pasajeBD.getTiquete())&&pasaje.getIdentificacion_viaje().equals(pasajeBD.getIdentificacion_viaje())&&pasaje.getCedula_funcionario().equals(pasajeBD.getCedula_funcionario()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque ya existe o por inconsistencias en los datos");
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}

}
