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
import edu.cecar.modelos.comunicacion;
import edu.cecar.modelos.ruta;

public class TestComunicacion {
	public TestComunicacion() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarComunicacion() {
		try {
			comunicacion comunicacion1 =  new comunicacion("1009", "1", "4565");
			comunicacion comunicacion2 =  new comunicacion("1009", "2", "2344");
			comunicacion comunicacion3 =  new comunicacion("1009", "2", "5655");
			comunicacion comunicacion4 =  new comunicacion("1009", "1", "7632");
			
			List<comunicacion> comunicaciones = new ArrayList<comunicacion>();
			comunicaciones.add(comunicacion1);
			comunicaciones.add(comunicacion2);
			comunicaciones.add(comunicacion3);
			comunicaciones.add(comunicacion4);
			
			controladorComunicacion controladorComunicacion = new controladorComunicacion();
			boolean resultado = controladorComunicacion.guardarComunicacion(comunicaciones);
			
			if(resultado==true) {				
				
				List<comunicacion> comunicacionesBD = new ArrayList<comunicacion>();
				
				for (comunicacion comunicacionOriginal : comunicaciones) {
					comunicacion comunicacionBD = controladorComunicacion.getComunicacion(comunicacionOriginal.getCedula_empleado(), comunicacionOriginal.getNumero());
					comunicacionesBD.add(comunicacionBD);
				}
										
				assertTrue(isIgualComunicacion(comunicaciones, comunicacionesBD));
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque ya está ese numero para ese empleado o porque el tipo de comunicacion no existe");
				assertFalse(resultado); 
			}					
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean isIgualComunicacion(List<comunicacion> comunicacionUno, List<comunicacion> comunicacionDos) {
		
		
		boolean sw = comunicacionUno.size() == comunicacionDos.size();
		
		for (int i = 0; i < comunicacionUno.size() && sw; i++) {

			comunicacion comunicacionOriginal = comunicacionUno.get(i);
			comunicacion comunicacionBD = comunicacionDos.get(i);
			
			sw = comunicacionOriginal.getCedula_empleado().equals(comunicacionBD.getCedula_empleado()) && comunicacionOriginal.getId_tipo().equals(comunicacionBD.getId_tipo()) && comunicacionOriginal.getNumero().equals(comunicacionBD.getNumero());

		}
		
		return sw;
		
	}

}
