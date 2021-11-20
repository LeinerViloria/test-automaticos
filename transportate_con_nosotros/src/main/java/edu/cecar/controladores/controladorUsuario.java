package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.tipo_empleado;
import edu.cecar.modelos.usuario;

public class controladorUsuario {
	
	public boolean guardarUsuario(usuario usuario) throws SQLException{		
		
		//Para verificar si el empleado se guardó o no
		boolean flag=false;
		
		usuario usuarioBD = getUsuario(usuario.getCedula_funcionario());		
		
		if(usuarioBD==null) {
			tipo_empleado tipo_empleado = getTipoEmpleado(usuario.getCedula_funcionario());
				if(tipo_empleado.getNombre().equals("Funcionario")) {
				String sql = "Call gestionarinserciondinamica(?,?)";
		
				PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
						prepareStatement(sql);
				
				String datos = "'"+usuario.getCedula_funcionario()+"', '"+usuario.getEmail()+"', '"+usuario.getContrasena()+"'/";				
				ejecutarProcedimiento.setString(1, "usuarios");
				ejecutarProcedimiento.setString(2, datos);
				ejecutarProcedimiento.execute();
				flag=true;
			}
		}
		
		return flag;
		
	}
	
	public usuario getUsuario(String identificador) throws SQLException {

		String sql = "select u.cedula_funcionario, email, contrasena  "
				+ "from usuarios u, empleados e "
				+ "where u.cedula_funcionario=e.cedula AND u.cedula_funcionario = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		usuario usuario = null;				

		while(resultado.next()) {
			if(usuario==null) {
				usuario = new usuario(resultado.getString(1), resultado.getString(2), resultado.getString(3));
			}
		}			

		return usuario;

	}
	
	public tipo_empleado getTipoEmpleado(String identificador) throws SQLException {

		String sql = "select codigo, nombre "
				+ "from empleados e, tipos_empleados tp "
				+ "where cedula = '"+identificador+"' AND tp.codigo=e.codigo_tipo_empleado";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		tipo_empleado tipo_empleado = null;				

		while(resultado.next()) {
			if(tipo_empleado==null) {
				tipo_empleado = new tipo_empleado(resultado.getString(1), resultado.getString(2));
			}
		}			

		return tipo_empleado;

	}

}
