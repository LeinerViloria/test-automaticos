package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.medio_transporte;

public class controladorMedioTransporte {
	
	public boolean guardarMedioTransporte(medio_transporte medio_transporte) throws SQLException{		
		
		//Para verificar que no exista
		boolean flag=false;
		medio_transporte medioBD = getMedioTransporte(medio_transporte.getCodigo());
		
		if(medioBD==null) {

			String sql = "Call gestionarinserciondinamica(?,?)";
	
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
	
			ejecutarProcedimiento.setString(1, "medios_transportes");
			ejecutarProcedimiento.setString(2, "'"+medio_transporte.getCodigo()+"', "+"'"+medio_transporte.getNombre()+"'/");
			ejecutarProcedimiento.execute();
			flag=true;
		}
		
		return flag;
		
	}
	
	public medio_transporte getMedioTransporte(String identificador) throws SQLException {

		String sql = "select codigo, nombre "
				+ "from medios_transportes "
				+ "where codigo = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		medio_transporte medio_transporte = null;				

		while(resultado.next()) {
			if(medio_transporte==null) {
				medio_transporte = new medio_transporte(resultado.getString(1), resultado.getString(2));
			}
		}			

		return medio_transporte;

	}

}
