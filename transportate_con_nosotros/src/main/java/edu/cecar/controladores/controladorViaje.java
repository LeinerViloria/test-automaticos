package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.asignacion;
import edu.cecar.modelos.ruta;
import edu.cecar.modelos.viaje;

public class controladorViaje {

	public boolean guardarViaje(viaje viaje) throws SQLException{		
				
		boolean flag=false;			
		
		viaje viajeBD = getViaje(viaje.getIdentificacion());
		ruta ruta = getRuta(viaje.getCodigo_ruta());
		asignacion asignacion = getAsignacion(viaje.getIdentificacion_asignacion());
		
		if(viajeBD==null && ruta!=null && asignacion!=null) {				
			
			String cadenaViaje = "'"+viaje.getIdentificacion()+"', '"+viaje.getIdentificacion_asignacion()+"', '"+viaje.getCodigo_ruta()+"', '"+viaje.getFecha()+"'/";
			
			String sql = "Call gestionarinserciondinamica(?,?)";
			
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
			
			ejecutarProcedimiento.setString(1, "viajes");
			ejecutarProcedimiento.setString(2, cadenaViaje);
			ejecutarProcedimiento.execute();
			flag=true;
		}					
		
		return flag;
		
	}
	
	public viaje getViaje(String identificacion_ruta) throws SQLException {

		String sql = "select v.identificacion, v.identificacion_asignaciones, v.codigo_ruta, v.fecha "
				+ "from viajes v, asignaciones a, rutas r "
				+ "where v.identificacion = '"+identificacion_ruta+"' AND v.identificacion_asignaciones=a.identificacion AND v.codigo_ruta=r.codigo ";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		viaje viaje = null;				

		while(resultado.next()) {
			if(viaje==null) {
				viaje = new viaje(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getDate(4));
			}
		}			

		return viaje;

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
	
	public asignacion getAsignacion(String identificador) throws SQLException {

		String sql = "select identificacion "
				+ "from asignaciones "
				+ "where identificacion = '"+identificador+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		asignacion asignacion = null;				

		while(resultado.next()) {
			if(asignacion==null) {
				asignacion = new asignacion();
				asignacion.setIdentificacion(resultado.getString(1));
			}
		}			

		return asignacion;

	}


}
