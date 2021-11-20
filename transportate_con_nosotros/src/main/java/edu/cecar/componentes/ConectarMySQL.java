/**
 * Clase: ConecterMySQL
 * 
 * @version  0.1
 * 
 * @since 19-10-2005
 * 
 * @autor Ing.  Jhon Mendez
 *
 * Copyrigth: JASoft
 */


package edu.cecar.componentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * Esta clase permite conectar a una base de datos Mysql y ademas ejecutar sentencias DML
 */


final public class ConectarMySQL {

	//** Declaracion de variables
	private static Connection conexion;

	protected static Statement sentencia;


	/** 
	 *Constructor general que se conecta a la base de datos dependiendo de los parametros
	 *
	 *@param servidorNombre Nombre del servidor o direccion IP
	 *@param nombreBD  nombre de la base de datos
	 *@param usuario Usuario autorizado
	 *@param  password
	 *
	 */

	public ConectarMySQL(String servidorNombre,String nombreBD,String usuario,String password) throws Exception{

		//** Se carga el driver para conectarse a la base de datos

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		}catch  (Exception e) {

			System.out.println("Error"+e);
		}


		// Se conecta a la base de datos
		// Se crea un URL hacia la maquina y la base de datos
		String url= "jdbc:mysql://" + servidorNombre + "/" + nombreBD; 

		//se crea la conexion a la base de datos
		conexion=DriverManager.getConnection(url,usuario,password);
		

		//conexion.setAutoCommit(false);

	}

	//Otros metodos


	
	public static Connection getConexion() {

		return conexion;
	}




	/** 
	 * Permite hacer los cambios permanentes en la BD
	 *
	 */

	public void commit() throws Exception {

		conexion.commit();
	}



	/** 
	 * Permite deshacer  cambios en la BD antes del ultimo commit
	 *
	 */

	public void rollback() {

		try {

			conexion.rollback();

		} catch (Exception e) {

			System.out.println("Error "+ e);
		}
	}


}


