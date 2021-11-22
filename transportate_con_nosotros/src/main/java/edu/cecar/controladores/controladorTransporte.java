package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.medio_transporte;
import edu.cecar.modelos.transporte;

public class controladorTransporte {

	public boolean guardarTransporte(transporte transporte) throws SQLException{		
		
		//Para verificar si el empleado se guardó o no
		boolean flag=false;
		
		transporte transporteBD = getTransporte(transporte.getPlaca());
		medio_transporte medio_transporte = getMedioTransporte(transporte.getCodigo_transporte());
		
		if(transporteBD==null && medio_transporte!=null) {

			String sql = "Call gestionarinserciondinamica(?,?)";
	
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
			
			String datos = "'"+transporte.getPlaca()+"', '"+transporte.getCodigo_transporte()+"'/";
	
			ejecutarProcedimiento.setString(1, "transportes");
			ejecutarProcedimiento.setString(2, datos);
			ejecutarProcedimiento.execute();
			flag=true;
		}
		
		return flag;
		
	}
	
	public transporte getTransporte(String placa) throws SQLException {

		String sql = "select placa, codigo_medios_transporte "
				+ "from transportes t, medios_transportes mt "
				+ "where t.codigo_medios_transporte=mt.codigo AND placa = '"+placa+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		transporte transporte = null;				

		while(resultado.next()) {
			if(transporte==null) {
				transporte = new transporte(resultado.getString(1), resultado.getString(2));
			}
		}			

		return transporte;

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
