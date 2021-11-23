package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.pasaje;
import edu.cecar.modelos.viaje;

public class controladorPasaje {
	
	public boolean guardarPasaje(pasaje pasaje) throws SQLException{		
				
		boolean flag=false;								
		
		pasaje pasajeBD = getPasaje(pasaje.getTiquete());
		empleado empleado = getEmpleado(pasaje.getCedula_funcionario());
		viaje viaje = getViaje(pasaje.getIdentificacion_viaje());		
		
		if(pasajeBD==null && empleado!=null && viaje!=null) {				
			int precio = getPrecio(viaje.getIdentificacion());
			
			String cadenaAsignacion = "'"+pasaje.getTiquete()+"', '"+pasaje.getIdentificacion_viaje()+"', '"+pasaje.getCedula_funcionario()+"', sysdate(), "+precio+"/";
			
			String sql = "Call gestionarinserciondinamica(?,?)";
			
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
			
			ejecutarProcedimiento.setString(1, "pasajes");
			ejecutarProcedimiento.setString(2, cadenaAsignacion);
			ejecutarProcedimiento.execute();
			flag=true;
		}				
		
		return flag;
		
	}
	
	private int getPrecio(String viaje) throws SQLException {
		int precio = 0;
		String sql = "select r.precio "
				+ "from viajes v, rutas r "
				+ "where v.codigo_ruta=r.codigo AND v.identificacion='"+viaje+"' ";				
		
		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		
		
		while(resultado.next()) {
			if(precio==0) {
				precio=resultado.getInt(1);
			}
		}
		
		return precio;
	}

	public pasaje getPasaje(String identificacion) throws SQLException {

		String sql = "select p.tiquete, p.identificacion_viaje, p.cedula_funcionario, p.fecha_venta, p.precio "
				+ "from pasajes p, viajes v, empleados e "
				+ "where p.identificacion_viaje=v.identificacion AND p.cedula_funcionario = e.cedula AND p.tiquete='"+identificacion+"' ";				
		
		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		pasaje pasaje = null;				

		while(resultado.next()) {
			if(pasaje==null) {
				pasaje = new pasaje(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getDate(4), resultado.getInt(5));
			}
		}			

		return pasaje;

	}
	
	public empleado getEmpleado(String identificador) throws SQLException {

		String sql = "select cedula "
				+ "from empleados e, tipos_empleados te "
				+ "where cedula = '"+identificador+"' AND e.codigo_tipo_empleado=te.codigo AND te.nombre='funcionario'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		empleado empleado = null;				

		while(resultado.next()) {
			if(empleado==null) {
				empleado = new empleado();
				empleado.setCedula(resultado.getString(1));
			}
		}			

		return empleado;

	}

	public viaje getViaje(String identificador) throws SQLException {

		String sql = "select v.identificacion, v.codigo_ruta "
				+ "from viajes v, asignaciones a, rutas r "
				+ "where v.identificacion = '"+identificador+"' AND v.identificacion_asignaciones=a.identificacion AND v.codigo_ruta=r.codigo ";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		viaje viaje = null;				

		while(resultado.next()) {
			if(viaje==null) {
				viaje = new viaje();
				viaje.setIdentificacion(resultado.getString(1));
				viaje.setCodigo_ruta(resultado.getString(2));
			}
		}			

		return viaje;

	}
}
