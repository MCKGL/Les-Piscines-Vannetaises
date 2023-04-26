   package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.Employee;
import service.InfoAdministrateur;

public class AdministrateurDAO extends DAO<Employee> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private static final String CLE_PRIMAIRE = "id_admin";
	private static final String TABLE = "info_admin";

	private static final String MDP = "mdp";
	

	private static AdministrateurDAO instance=null;
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * @return instance;
	 */
	public static AdministrateurDAO getInstance(){
		if (instance==null){
			instance = new AdministrateurDAO();
		}
		return instance;
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	private AdministrateurDAO() {
		super();
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Création d'une ligne administrateur dans la base de donnée
	 */
	@Override
	public boolean create(Employee admin) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+CLE_PRIMAIRE+", "+MDP+") VALUES (?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, admin.getIdEmployee());
			pst.executeUpdate();
			
			// On stock l'objet administrateur dans le tableau "donnees"
			donnees.put(admin.getIdEmployee(), admin);
			
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Suppression d'une ligne administrateur dans la base de donnée
	 */
	@Override
	public boolean delete(Employee admin) {
		boolean succes=true;
		int id = admin.getIdEmployee();
		try {
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, id) ;
			pst.executeUpdate() ;
			
			// On supprimer l'objet administrateur du tableau "donnees"
			donnees.remove(id);
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Mise à jour d'une ligne administrateur dans la base de donnee
	 */
	@Override
	public boolean update(Employee admin) {
		boolean succes=true;
		try {
			String requete = "UPDATE "+TABLE+" SET  "+MDP+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(2, admin.getIdEmployee());
			pst.executeUpdate() ;
			
			// On supprimer l'objet administrateur du tableau "donnees"
			donnees.put(admin.getIdEmployee(), admin);
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Employee read(int idAdministrateur) {
		throw new IllegalArgumentException();
	}

	
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Lecture d'une ligne de la table administrateur de la base de donnée
	 */
	public InfoAdministrateur readInfoAdministrateur(int idAdministrateur) {
		InfoAdministrateur administrateur = null;
		try {
		String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+idAdministrateur+";";
		ResultSet rs = Connexion.executeQuery(requete);
		if (rs.next()) {
			String mdp = rs.getString(MDP);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrateur;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Savoir si un tuple existe
	 */
	public boolean existe(int id) {
		boolean result = false;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id;
			ResultSet rs = Connexion.executeQuery(requete);
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
