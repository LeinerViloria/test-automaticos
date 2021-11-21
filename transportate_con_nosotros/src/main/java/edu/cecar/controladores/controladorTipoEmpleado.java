package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.tipo_empleado;

public class controladorTipoEmpleado {
	
	public boolean guardarTipoEmpleado(tipo_empleado tipo_empleado) throws SQLException{		
		
		//Para verificar que no exista
		boolean flag=false;
		tipo_empleado tipoBD = getTipoEmpleado(tipo_empleado.getCodigo());
		
		if(tipoBD==null) {

			String sql = "Call gestionarinserciondinamica(?,?)";
	
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
	
			ejecutarProcedimiento.setString(1, "tipos_empleados");
			ejecutarProcedimiento.setString(2, "'"+tipo_empleado.getCodigo()+"', "+"'"+tipo_empleado.getNombre()+"'/");
			ejecutarProcedimiento.execute();
			flag=true;
		}
		
		return flag;
		
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
