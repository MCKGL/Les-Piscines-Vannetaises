package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Employee;
import controller .InfoProfesseur;

public class ProfesseurDAO extends DAO<Employee> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private static final String CLE_PRIMAIRE = "id_prof";
	private static final String TABLE = "info_prof";
	
	private static final String SPECIALITES = "specialite";

	private static ProfesseurDAO instance=null;
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * @return instance;
	 */
	public static ProfesseurDAO getInstance(){
		if (instance==null){
			instance = new ProfesseurDAO();
		}
		return instance;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	private ProfesseurDAO() {
		super();
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Création d'une ligne professeur dans la base de donnée
	 */
	@Override
	public boolean create(Employee prof) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+CLE_PRIMAIRE+", "+SPECIALITES+") VALUES (?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, prof.getIdEmployee());
			pst.setString(2, prof.getInfoProfesseur().getSpecialites());
			pst.executeUpdate();
			
			
			donnees.put(prof.getIdEmployee(), prof);
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Suppression d'une ligne professeur dans la base de donnée
	 */
	@Override
	public boolean delete(Employee prof) {
		boolean succes=true;
		int id = prof.getIdEmployee();
		try {
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, id) ;
			pst.executeUpdate() ;
			donnees.remove(id);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Mise à jour d'une ligne professeur dans la base de donnee
	 */
	@Override
	public boolean update(Employee prof) {
		boolean succes=true;
		try {
			String requete = "UPDATE "+TABLE+" SET  "+SPECIALITES+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1, prof.getInfoProfesseur().getSpecialites());
			pst.setInt(2, prof.getIdEmployee());
			pst.executeUpdate() ;
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}
	
// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public Employee read(int id) {
		throw new IllegalArgumentException();
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Lecture d'un professeur de la base de donnée dans la table professeur
	 */
	public InfoProfesseur readInfoProfesseur(int idProfesseur) {
		InfoProfesseur professeur = null;
		try {
		String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+idProfesseur+";";
		ResultSet rs = Connexion.executeQuery(requete);
		rs.next();
		String specialites = rs.getString(SPECIALITES);
		professeur = new InfoProfesseur(specialites);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professeur;
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
