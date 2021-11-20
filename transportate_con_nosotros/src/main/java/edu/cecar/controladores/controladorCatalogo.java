package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.catalogo_viaje;
import edu.cecar.modelos.ruta;

public class controladorCatalogo {

	public void guardarCatalogo(catalogo_viaje catalogo_viaje) throws SQLException {


		String cadenaruta = "";

		for (ruta ruta : catalogo_viaje.getRuta()) 

			cadenaruta +=  "'"+ruta.getCodigo()+"',-,'" + ruta.getIdentificacion_origen() + "','" +
					ruta.getIdentificacion_destino() +"','"+
					ruta.getFormaRuta() + "'," +
					ruta.getPrecio()+"/";
		
		System.out.println(cadenaruta);


		String sql = "Call gestionarCatalogos(?)";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);
		
		ejecutarProcedimiento.setString(1, cadenaruta);		
		ejecutarProcedimiento.execute();

	}


	public catalogo_viaje getCatalogo(int identificador) throws SQLException {

		String sql = "select r.codigo, r.identificacion_catalogos, identificacion_origen, identificacion_destino, forma_ruta, precio "
				+ "from catalogos_viajes c, rutas r "
				+ "where c.identificacion = r.identificacion_catalogos "
				+ "and c.identificacion = ?";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);

		ejecutarProcedimiento.setInt(1, identificador);

		ResultSet resultado = ejecutarProcedimiento.executeQuery();

		catalogo_viaje catalogo_viaje = null;
		List<ruta> rutas = new ArrayList<ruta>();


		while (resultado.next()) {


			if (catalogo_viaje == null) {

				catalogo_viaje = new catalogo_viaje();
				//compraEncabezado.setTotal(resultado.getInt(3));
			}

			
			ruta ruta = new ruta();
			ruta.setCodigo(resultado.getString(1));
			ruta.setIdentificacion_catalogo(resultado.getInt(2));
			ruta.setIdentificacion_origen(resultado.getString(3));
			ruta.setIdentificacion_destino(resultado.getString(4));
			ruta.setFormaRuta(resultado.getString(5));
			ruta.setPrecio(resultado.getInt(6));
			rutas.add(ruta);
			

		}

		catalogo_viaje.setRuta(rutas);

		return catalogo_viaje;

	}


	public catalogo_viaje getUltimoCatalogo() throws SQLException {

		String sql = "select max(identificacion) from catalogos_viajes";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);


		ResultSet resultado = ejecutarProcedimiento.executeQuery();
		resultado.next();
		return getCatalogo(resultado.getInt(1));

	}

}


