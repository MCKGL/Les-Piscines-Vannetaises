package modele.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Formule;

public class FormuleDAO extends DAO<Formule> {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs de base de donnée
	 * 
	 * Noms des champs d'une ligne
	 * 
	 * Instance existante si oui sauvegardée
	 */
	private String CLE_PRIMAIRE = "id_formule";
	private String TABLE = "formule";

	private String ID_FORMULE = "id_formule";
	private String TYPE = "type";
	private String LABEL = "label";
	private String VALIDITE = "duree_validite";
	private String PRIX = "prix";
	private String NBRE_ENTREE = "nb_entree";
	private String ACTIVE = "active";

	private static FormuleDAO instance = null;

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * 
	 * @return instance;
	 */
	public static FormuleDAO getInstance() {
		if (instance == null) {
			instance = new FormuleDAO();
		}
		return instance;
	}
	// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean create(Formule formule) {
		boolean succes = false;
		try {
			String requete = "INSERT INTO " + TABLE + " ( " + TYPE + ", " + LABEL + ", " + VALIDITE + ", " + PRIX + ", " + NBRE_ENTREE+ ","
					+ ACTIVE + ") VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, formule.getType());
			pst.setLong(2, formule.getDureeValidite());
			pst.setInt(3, formule.getPrixFormule());
			pst.setInt(4, formule.getNbreEntreeFormule());
			pst.setString(5, formule.getLabel());
			pst.setBoolean(6, formule.getActive());

			// Mise à jour de la base de donnée
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				formule.setIdFormule(rs.getInt(1));
			}
			donnees.put(formule.getIdFormule(), formule);
			succes = true;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Formule formule) {
		boolean succes = false;
		int id = formule.getIdFormule();
		try {
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
			donnees.remove(id);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Formule formule) {
		boolean succes = false;
		try {
			String requete = "UPDATE " + TABLE + " SET  " + TYPE + " = ?, " + LABEL + " = ?, " + VALIDITE + " = ?, " + PRIX + " = ?, "
					+ NBRE_ENTREE + " = ?, " + ACTIVE + " = ? WHERE " + ID_FORMULE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, formule.getType());
			pst.setString(2, formule.getLabel());
			pst.setLong(3, formule.getDureeValidite());
			pst.setInt(4, formule.getPrixFormule());
			pst.setInt(5, formule.getNbreEntreeFormule());
			pst.setBoolean(6, formule.getActive());
			pst.setInt(7, formule.getIdFormule());
			

			pst.executeUpdate();
			succes = true;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Formule read(int id) {
		Formule formule = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + "= ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			String type = rs.getString(TYPE);
			String label = rs.getString(LABEL);
			int validite = rs.getInt(VALIDITE);
			int prix = rs.getInt(PRIX);
			int nbre_entree = rs.getInt(NBRE_ENTREE);
			boolean active = rs.getBoolean(ACTIVE);

			formule = new Formule(id, type, label, validite, prix, nbre_entree, active);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formule;
	}
	
	// ------------------------------------------------A voir si je garde----------------------------------------------------------
	public List<Formule> readAll() {
	    List<Formule> formuleList = new ArrayList<>();
	    Formule formule = null;
	    try {
	        String requete = "SELECT * FROM " + TABLE;
	        PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt(ID_FORMULE);
	            String type = rs.getString(TYPE);
	            String label = rs.getString(LABEL);
	            int validite = rs.getInt(VALIDITE);
	            int prix = rs.getInt(PRIX);
	            int nbre_entree = rs.getInt(NBRE_ENTREE);
	            boolean active = rs.getBoolean(ACTIVE);

	            formule = new Formule(id, type, label, validite, prix, nbre_entree, active);
	            formuleList.add(formule);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return formuleList;
	}
	
	// ------------------------------------------------------
	
	public Formule readByTypes(String types) {
		Formule formule = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + TYPE + " = '" + types + "';";
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			int id = rs.getInt(ID_FORMULE);
			String type = rs.getString(TYPE);
			String label = rs.getString(LABEL);
			int validite = rs.getInt(VALIDITE);
			int prix = rs.getInt(PRIX);
			int nbre_entree = rs.getInt(NBRE_ENTREE);
			boolean active = rs.getBoolean(ACTIVE);

			formule = new Formule(id, type, label, validite, prix, nbre_entree, active);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formule;
	}



}
