package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.comunicacion;
import edu.cecar.modelos.empleado;
import edu.cecar.modelos.ruta;
import edu.cecar.modelos.tipo_comunicacion;
import edu.cecar.modelos.tipo_empleado;

public class controladorComunicacion {
	
	public boolean guardarComunicacion(List<comunicacion> comunicacion) throws SQLException{		
		
		//Para verificar si la combinacion del empleado y el numero ya existe o no
		boolean flag=false;			
		
		int valido = 0;
		String cadenaComunicacion = "";
		
		for (comunicacion comunicacionOriginal : comunicacion) {
			comunicacion comunicacionBD = getComunicacion(comunicacionOriginal.getCedula_empleado(), comunicacionOriginal.getNumero());
			tipo_comunicacion tipo_comunicacion = getTipoComunicacion(comunicacionOriginal.getId_tipo());
			
			if(comunicacionBD==null && tipo_comunicacion!=null) {				
				
				cadenaComunicacion += "'"+comunicacionOriginal.getCedula_empleado()+"', '"+comunicacionOriginal.getId_tipo()+"', '"+comunicacionOriginal.getNumero()+"'/";
				
				valido++;				
			}
		}	
		
		if(valido==comunicacion.size()) {
			//Si entra aqui es porque no hay problema con los n elementos que 
			//vaya a insertar
			String sql = "Call gestionarinserciondinamica(?,?)";
			
			PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
					prepareStatement(sql);
			
			ejecutarProcedimiento.setString(1, "comunicaciones");
			ejecutarProcedimiento.setString(2, cadenaComunicacion);
			ejecutarProcedimiento.execute();
			flag=true;
		}
		
		return flag;
		
	}
	
	public comunicacion getComunicacion(String cedulaEmpleado, String numero) throws SQLException {

		String sql = "select cedula_empleado, id_tipo, numero "
				+ "from comunicaciones c, empleados e, tipos_comunicacion tc "
				+ "where c.cedula_empleado=e.cedula AND c.id_tipo = tc.identificacion AND c.cedula_empleado = '"+cedulaEmpleado+"' AND c.numero = '"+numero+"'";				

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);			

		ResultSet resultado = ejecutarProcedimiento.executeQuery();		

		comunicacion comunicacion = null;				

		while(resultado.next()) {
			if(comunicacion==null) {
				comunicacion = new comunicacion(resultado.getString(1), resultado.getString(2), resultado.getString(3));
			}
		}			

		return comunicacion;

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
