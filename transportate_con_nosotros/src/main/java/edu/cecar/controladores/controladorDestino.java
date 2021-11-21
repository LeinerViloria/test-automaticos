package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.destino;


public class controladorDestino {
	
	public void guardarDestino(destino destino) throws SQLException{		

		String sql = "Call gestionarDestinos(?,?)";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);

		ejecutarProcedimiento.setString(1, destino.getId());
		ejecutarProcedimiento.setString(2, destino.getNombre());
		ejecutarProcedimiento.execute();
		
	}
	
	public destino getDestino(String identificador) throws SQLException {

		String sql = "select identificacion, nombre "
				+ "from destinos d "
				+ "where d.identificacion = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		destino destino = null;				

		if (destino == null) {
			while(resultado.next()) {
				destino = new destino(resultado.getString(1), resultado.getString(2));
			}
		}				

		return destino;

	}

	
	public destino getUltimoDestino() throws SQLException {
		String sql = "select identificacion from destinos limit 1";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);


		ResultSet resultado = ejecutarProcedimiento.executeQuery();
		resultado.next();			
		
		return getDestino(resultado.getString(1));
	}

}
