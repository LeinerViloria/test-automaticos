package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.tipo_empleado;

public class controladorEmpleado {
	
	public boolean guardarEmpleado(empleado empleado) throws SQLException{		
		
		//Para verificar si el empleado se guardó o no
		boolean flag=false;
		
		empleado empleadoBD = getEmpleado(empleado.getCedula());
		tipo_empleado tipo_empleado = getTipoEmpleado(empleado.getCodigo_tipo_empleado());
		
		if(empleadoBD==null && tipo_empleado!=null) {

			String sql = "Call gestionarinserciondinamica(?,?)";
	
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
			
			String datos = "'"+empleado.getCedula()+"', '"+empleado.getCodigo_tipo_empleado()+"', '"+empleado.getNombres()+"', '"+empleado.getApellidos()+"'/";
	
			ejecutarProcedimiento.setString(1, "empleados");
			ejecutarProcedimiento.setString(2, datos);
			ejecutarProcedimiento.execute();
			flag=true;
		}
		
		return flag;
		
	}
	
	public empleado getEmpleado(String identificador) throws SQLException {

		String sql = "select cedula, codigo_tipo_empleado, nombres, apellidos "
				+ "from empleados "
				+ "where cedula = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		empleado empleado = null;				

		while(resultado.next()) {
			if(empleado==null) {
				empleado = new empleado(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4));
			}
		}			

		return empleado;

	}
	
	public tipo_empleado getTipoEmpleado(String identificador) throws SQLException {

		String sql = "select codigo, nombre "
				+ "from tipos_empleados "
				+ "where codigo = '"+identificador+"'";				

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
