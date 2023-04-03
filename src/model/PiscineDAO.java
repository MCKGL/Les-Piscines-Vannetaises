package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PiscineDAO extends DAO<Piscine> { 
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 * 
	 */
	private static final String CLE_PRIMAIRE = "id_piscine";
	private static final String TABLE = "piscine";
	private static final String NOM = "nom";
	private static final String ID_ADRESSE = "id_adresse";
	
	
	
	

	private static PiscineDAO instance=null;
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * @return instance
	 */
	public static PiscineDAO getInstance(){
		if (instance==null){
			instance = new PiscineDAO();
		}
		return instance;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	private PiscineDAO() {
		super();
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Création d'une adresse dans la base de donnée
	 */
	public boolean create(Piscine piscine) {
		boolean succes=true;
		try {
			
			if (AdresseDAO.getInstance().existe(piscine.getAdresse())) {
				int id = AdresseDAO.getInstance().readIdAdresse(piscine.getAdresse());
				piscine.getAdresse().setIdAdresse(id);
			} else {
				AdresseDAO.getInstance().create(piscine.getAdresse());
				int id = AdresseDAO.getInstance().readIdAdresse(piscine.getAdresse());
				piscine.getAdresse().setIdAdresse(id);
			}
			
			String requete = "INSERT INTO "+TABLE+" ("+NOM+" , "+ID_ADRESSE+") VALUES (?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, piscine.getNom());
			pst.setInt(2, piscine.getAdresse().getIdAdresse());
			pst.executeUpdate();
			
			//Récupérer la clef générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				piscine.setIdPiscine(rs.getInt(1));
			}
			
			// On stock l'objet adresse dans le tableau donnees
			donnees.put(piscine.getIdPiscine(), piscine);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Suppression d'une adresse dans la base de donnée
	 */
	public boolean delete(Piscine piscine) {
		boolean succes=true;
		int id = piscine.getIdPiscine();
		try {
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, id) ;
			pst.executeUpdate() ;
			
			// On supprimer l'objet adresse dans le tableau "donnees"
			donnees.remove(id);
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Mise à jour d'une adresse dans la base de donnée
	 */
	public boolean update(Piscine piscine) {
		boolean succes=true;
		try {
			String requete = "UPDATE "+TABLE+" SET  "+NOM+" = ?, "+ID_ADRESSE+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1, piscine.getNom());
			pst.setInt(2, piscine.getIdPiscine());
			pst.executeUpdate();
			
			// Mise à jour de l'objet adresse dans le tableau "donnees"
			donnees.put(piscine.getIdPiscine(), piscine);
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Lecture d'une adresse de la base de donnée dans la table adresse
	 */
	public Piscine read(int id) {
		Piscine piscine = null;
		try {
		String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id+";";
		ResultSet rs = Connexion.executeQuery(requete);
		rs.next();
		int id_piscine = rs.getInt(CLE_PRIMAIRE);
		String nom =rs.getString(NOM);
		int idAdresse = rs.getInt(ID_ADRESSE);
		
		piscine=new Piscine(id_piscine, nom, idAdresse);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return piscine;
	}


}
