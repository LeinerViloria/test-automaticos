package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.asignacion;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.transporte;

public class controladorAsignacion {
	
	public boolean guardarAsignacion(asignacion asignacion) throws SQLException{		
		
		//Para verificar si la combinacion del empleado y el numero ya existe o no
		boolean flag=false;								
		
		asignacion asignacionBD = getAsignacion(asignacion.getIdentificacion());
		empleado empleado = getEmpleado(asignacion.getCedula_empleado());
		transporte transporte = getTransporte(asignacion.getPlaca_transporte());
		
		if(asignacionBD==null && empleado!=null && transporte!=null) {				
			
			String cadenaAsignacion = "'"+asignacion.getIdentificacion()+"', '"+asignacion.getCedula_empleado()+"', sysdate(), '"+asignacion.getPlaca_transporte()+"'/";
			
			String sql = "Call gestionarinserciondinamica(?,?)";
			
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
			
			ejecutarProcedimiento.setString(1, "asignaciones");
			ejecutarProcedimiento.setString(2, cadenaAsignacion);
			ejecutarProcedimiento.execute();
			flag=true;
		}				
		
		return flag;
		
	}
	
	public asignacion getAsignacion(String identificacion) throws SQLException {

		String sql = "select a.identificacion, a.cedula_empleado, a.fecha, a.placa_transporte "
				+ "from asignaciones a, empleados e, transportes t "
				+ "where a.cedula_empleado=e.cedula AND a.placa_transporte=t.placa AND a.identificacion='"+identificacion+"' ";				
		
		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		asignacion asignacion = null;				

		while(resultado.next()) {
			if(asignacion==null) {
				asignacion = new asignacion(resultado.getString(1), resultado.getString(2), resultado.getDate(3), resultado.getString(4));
			}
		}			

		return asignacion;

	}
	
	public empleado getEmpleado(String identificador) throws SQLException {

		String sql = "select cedula "
				+ "from empleados e, tipos_empleados te "
				+ "where cedula = '"+identificador+"' AND e.codigo_tipo_empleado=te.codigo AND te.nombre='conductor'";				

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

	public transporte getTransporte(String identificador) throws SQLException {

		String sql = "select t.placa "
				+ "from transportes t, medios_transportes mt "
				+ "where t.placa = '"+identificador+"' AND t.codigo_medios_transporte=mt.codigo";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		transporte transporte = null;				

		while(resultado.next()) {
			if(transporte==null) {
				transporte = new transporte();
				transporte.setPlaca(resultado.getString(1));
			}
		}			

		return transporte;

	}
}
