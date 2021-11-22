package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.destino;
import edu.cecar.modelos.detalle_ruta;
import edu.cecar.modelos.ruta;

public class controladorDetalleRuta {

	public boolean guardarRuta(List<detalle_ruta> detalle_ruta) throws SQLException{		
		
		//Para verificar si la combinacion del empleado y el numero ya existe o no
		boolean flag=false;			
		
		int valido = 0;
		String cadenaDetalle = "";
		
		for (detalle_ruta detalle_rutaOriginal : detalle_ruta) {
			detalle_ruta detalle_rutaBD = getDetalleRuta(detalle_rutaOriginal.getIdentificacion_ruta(), detalle_rutaOriginal.getCodigo_destino());
			ruta ruta = getRuta(detalle_rutaOriginal.getIdentificacion_ruta());
			destino destino = getDestino(detalle_rutaOriginal.getCodigo_destino());
			
			if(detalle_rutaBD==null && ruta!=null && destino!=null) {				
				
				cadenaDetalle += "'"+detalle_rutaOriginal.getIdentificacion_ruta()+"', '"+detalle_rutaOriginal.getCodigo_destino()+"'/";
				
				valido++;				
			}
		}	
		
		if(valido==detalle_ruta.size()) {
			//Si entra aqui es porque no hay problema con los n elementos que 
			//vaya a insertar
			String sql = "Call gestionarinserciondinamica(?,?)";
			
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
			
			ejecutarProcedimiento.setString(1, "detalles_ruta");
			ejecutarProcedimiento.setString(2, cadenaDetalle);
			ejecutarProcedimiento.execute();
			flag=true;
		}
		
		return flag;
		
	}
	
	public detalle_ruta getDetalleRuta(String identificacion_ruta, String codigo_destino) throws SQLException {

		String sql = "select identificacion_ruta, codigo_destino "
				+ "from detalles_ruta dr, rutas r, destinos d "
				+ "where dr.identificacion_ruta=r.codigo AND dr.codigo_destino = d.identificacion AND dr.identificacion_ruta = '"+identificacion_ruta+"' AND dr.codigo_destino = '"+codigo_destino+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		detalle_ruta detalle_ruta = null;				

		while(resultado.next()) {
			if(detalle_ruta==null) {
				detalle_ruta = new detalle_ruta(resultado.getString(1), resultado.getString(2));
			}
		}			

		return detalle_ruta;

	}
	
	public ruta getRuta(String identificador) throws SQLException {

		String sql = "select codigo "
				+ "from rutas "
				+ "where codigo = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		ruta ruta = null;				

		while(resultado.next()) {
			if(ruta==null) {
				ruta = new ruta();
				ruta.setCodigo(resultado.getString(1));
			}
		}			

		return ruta;

	}
	
	public destino getDestino(String identificador) throws SQLException {

		String sql = "select identificacion "
				+ "from destinos "
				+ "where identificacion = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		destino destino = null;				

		while(resultado.next()) {
			if(destino==null) {
				destino = new destino();
				destino.setId(resultado.getString(1));
			}
		}			

		return destino;

	}

}
