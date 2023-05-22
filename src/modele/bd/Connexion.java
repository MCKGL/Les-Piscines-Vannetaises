package modele.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Connexion {
	private static Connection connect = null;

	private static final String SQL_SERVER = "localhost\\SQLEXPRESS";
	private static final String BASE_DE_DONNEES = "bdd_piscine";
	private static final String ID = "MCG";
	private static final String MDP = "toto";

	/**
	 * Patron de conception Singleton
	 * @return l'instance unique de connexion
	 */
	public static Connection getInstance() {
		if (connect==null) {
			try { 
				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser(ID);
				ds.setPassword(MDP);
				ds.setServerName(SQL_SERVER);
				ds.setDatabaseName(BASE_DE_DONNEES);
				connect = ds.getConnection();
				System.out.println("connect�");
			}
			catch (SQLException e){
				System.out.println("Echec de la tentative de connexion : " + e.getMessage() + e.getStackTrace()) ;
			}
		}
		return connect;
	}
	
	private Connexion() {
		super();
	}

	public static ResultSet executeQuery(String requete){
		Statement st = null ;
		ResultSet rs = null;
		//System.out.println("requete = "+requete);
		try{
			st = Connexion.getInstance().createStatement() ;
			rs = st.executeQuery(requete) ;
		}catch(SQLException e){
			System.out.println("Echec de la tentative d'ex�cution de requete : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return rs;
	}

	/**
	 * Une m�thode statique qui permet de faire une mise � jour d'une table (INSERT / UPDATE / DELETE)
	 * @param requete
	 * @return
	 */
	public static boolean executeUpdate(String requete){
		boolean succes = true;
		//System.out.println(requete);
		Statement st = null ;
		try{
			st = Connexion.getInstance().createStatement() ;
			st.executeUpdate(requete) ;
		}catch(SQLException e){
			succes = false;
			System.out.println("Echec de la tentative d'ex�cution de Mise � Jour : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return succes;
	}

	/**
	 * Fermeture de la connexion au SGBD SQL ServerExpress
	 */
	public static void fermer()
	{
		try
		{
			getInstance().close();
			System.out.println("deconnexion ok");
		}
		catch (SQLException e)
		{
			connect=null;
			System.out.println("echec de la fermeture");
		}
	}

	public static List<Integer> getLesIds(String attribut, String table, String clauseWhere) {
		String requete = "SELECT DISTINCT "+attribut+" FROM "+table;
		if (clauseWhere!=null) {
			requete += " WHERE "+clauseWhere;
		}		
		ResultSet rs = Connexion.executeQuery(requete);
		List<Integer> liste = new ArrayList<Integer>();
		try {
			while (rs.next()) {
			int id = rs.getInt(attribut);
			liste.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
}
