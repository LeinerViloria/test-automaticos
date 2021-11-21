package testautomaticos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.controladorEmpleado;
import edu.cecar.controladores.controladorUsuario;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.usuario;

public class TestUsuario {
	public TestUsuario() {
		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "transportate_con_nosotros", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Test
	public void testGestionarUsuario() {
		try {			

			usuario usuario = new usuario("109", "otro@gmail.com", "123");		

			controladorUsuario controladorUsuario = new controladorUsuario();
			boolean guardar = controladorUsuario.guardarUsuario(usuario);
			
			if(guardar==true) {
				usuario usuarioBD = controladorUsuario.getUsuario(usuario.getCedula_funcionario());
							
				assertTrue(usuario.getCedula_funcionario().equals(usuarioBD.getCedula_funcionario())&&usuario.getEmail().equals(usuarioBD.getEmail())&&usuario.getContrasena().equals(usuarioBD.getContrasena()));
				
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo guardar porque ya está registrado o porque el empleado no es funcionario o no existe");
				assertFalse(guardar);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
