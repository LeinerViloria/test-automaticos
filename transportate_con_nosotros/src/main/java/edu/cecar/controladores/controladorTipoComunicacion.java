package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.tipo_comunicacion;

public class controladorTipoComunicacion {
	
	public boolean guardarTipoDeComunicacion(tipo_comunicacion tipo_comunicacion) throws SQLException{		
		
		//Para verificar que no exista
		boolean flag=false;
		tipo_comunicacion tipoBD = getTipoComunicacion(tipo_comunicacion.getIdentificacion());
		
		if(tipoBD==null) {

			String sql = "Call gestionarinserciondinamica(?,?)";
	
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
	
			ejecutarProcedimiento.setString(1, "tipos_comunicacion");
			ejecutarProcedimiento.setString(2, "'"+tipo_comunicacion.getIdentificacion()+"', "+"'"+tipo_comunicacion.getNombre()+"'/");
			ejecutarProcedimiento.execute();
			flag=true;
		}
		
		return flag;
		
	}
	
	public tipo_comunicacion getTipoComunicacion(String identificador) throws SQLException {

		String sql = "select identificacion, nombre "
				+ "from tipos_comunicacion "
				+ "where identificacion = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		tipo_comunicacion tipo_comunicacion = null;				

		while(resultado.next()) {
			if(tipo_comunicacion==null) {
				tipo_comunicacion = new tipo_comunicacion(resultado.getString(1), resultado.getString(2));
			}
		}			

		return tipo_comunicacion;

	}


}
