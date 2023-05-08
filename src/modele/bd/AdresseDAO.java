package modele.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modele.metier.Adresse;

public class AdresseDAO extends DAO<Adresse> {
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 * 
	 */
	private static final String CLE_PRIMAIRE = "id_adresse";
	private static final String TABLE = "adresse";

	private static final String VILLE = "ville";
	private static final String CODE_POSTAL = "code_postal";
	private static final String RUE = "nom_rue";
	private static final String NUMERO = "num_rue";
	

	private static AdresseDAO instance=null;
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * @return instance
	 */
	public static AdresseDAO getInstance(){
		if (instance==null){
			instance = new AdresseDAO();
		}
		return instance;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	private AdresseDAO() {
		super();
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Création d'une adresse dans la base de donnée
	 */
	public boolean create(Adresse adr) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+VILLE+", "+CODE_POSTAL+", "+RUE+", "+NUMERO+") VALUES (?,?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, adr.getVille());
			pst.setInt(2, adr.getCodePostal());
			pst.setString(3, adr.getRue());
			pst.setInt(4, adr.getNumero());
			pst.executeUpdate();
			
			//Récupérer la clef générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				adr.setIdAdresse(rs.getInt(1));
			}
			
			// On stock l'objet adresse dans le tableau donnees
			donnees.put(adr.getIdAdresse(), adr);

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
	public boolean delete(Adresse adr) {
		boolean succes=true;
		int id = adr.getIdAdresse();
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
	public boolean update(Adresse adr) {
		boolean succes=true;
		try {
			String requete = "UPDATE "+TABLE+" SET  "+VILLE+" = ?, "+CODE_POSTAL+" = ?, "+RUE+" = ?, "+NUMERO+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1, adr.getVille());
			pst.setInt(2, adr.getCodePostal());
			pst.setString(3, adr.getRue());
			pst.setInt(4, adr.getNumero());
			pst.setInt(5, adr.getIdAdresse());
			pst.executeUpdate();
			
			// Mise à jour de l'objet adresse dans le tableau "donnees"
			donnees.put(adr.getIdAdresse(), adr);
			
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
	public Adresse read(int id) {
		Adresse adresse = null;
		try {
		String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id+";";
		ResultSet rs = Connexion.executeQuery(requete);
		rs.next();
		String ville =rs.getString(VILLE);
		int codePostal =rs.getInt(CODE_POSTAL);
		String rue =rs.getString(RUE);
		int numero =rs.getInt(NUMERO);
		
		adresse=new Adresse(id, ville, codePostal ,rue , numero);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Savoir si une adresse existe a partir d'un id
	 * @param id
	 * @return boolean
	 */
	public boolean existe(int id) {
		Boolean result = false;
		try {
			// Requête pour chercher une adresse correspondante
			String requete = "SELECT COUNT(*) FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id;
			
			// On execute la requête préparé et on récupère le jeu de donnée sous forme d'objet
			ResultSet rs = Connexion.executeQuery(requete);
			
			// Si il existe -> On sélectionne le premier jeu de donnée récupéré si il y en a plusieurs
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		}
	/**
	 * 
	 * @param adresse
	 * @return
	 */
	public boolean existe(Adresse adresse) {
		Boolean result = false;
		try {
			// Requête pour chercher une adresse correspondante
			String requete = "SELECT * FROM "+TABLE+" WHERE "+VILLE+" = ? AND "+CODE_POSTAL+" = ? AND "+RUE+" = ? AND "+NUMERO+" = ?;";
			
			// Préparation de la requête
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, adresse.getVille());
			pst.setInt(2, adresse.getCodePostal());
			pst.setString(3, adresse.getRue());
			pst.setInt(4, adresse.getNumero());
			
			// On execute la requête préparé
			pst.execute();
			
			// On récupère le jeu de donnée sous forme d'objet
			ResultSet rs = pst.getResultSet();
			// Si il existe -> On sélectionne le premier jeu de donnée récupéré si il y en a plusieurs
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Récupérer l'id adresse d'un tuple dans la base de donnée
	 * @param adresse;
	 * @return id adresse;
	 */
	public int readIdAdresse(Adresse adresse) {
		int idAdresse = 0;
		try {
			
			// Requête pour chercher une adresse correspondante
			String requete = "SELECT * FROM "+TABLE+" WHERE "+VILLE+" = ? AND "+CODE_POSTAL+" = ? AND "+RUE+" = ? AND "+NUMERO+" = ?;";
			
			// Préparation de la requête
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, adresse.getVille());
			pst.setInt(2, adresse.getCodePostal());
			pst.setString(3, adresse.getRue());
			pst.setInt(4, adresse.getNumero());
			
			// On execute la requête préparé
			pst.execute();
			
			// On récupère le jeu de donnée sous forme d'objet
			ResultSet rs = pst.getResultSet();
			// On sélectionne le premier jeu de donnée récupéré si il y en a plusieurs
			rs.next();
			idAdresse = rs.getInt(CLE_PRIMAIRE);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return idAdresse;
	}
}
