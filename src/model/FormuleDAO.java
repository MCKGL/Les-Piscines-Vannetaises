package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.Formule;

public class FormuleDAO extends DAO<Formule> {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs de base de donnée
	 * 
	 * Noms des champs d'une ligne
	 * 
	 * Instance existante si oui sauvegardée
	 */
	private final String CLE_PRIMAIRE = "id_formule";
	private final String TABLE = "formule";

	private final String ID_FORMULE = "id_formule";
	private final String PRIX = "prix";
	private final String VALIDITEE = "duree_validite";
	private final String NB_ENTREE = "nb_entree";
	private final String LABEL = "label";
	private final String TYPE = "type";


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
			String requete = "INSERT INTO " + TABLE + " ( " + PRIX + ", " + VALIDITEE + ", " + NB_ENTREE + ", " + LABEL
					+", "+TYPE+") VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setFloat(1, formule.getPrix());
			pst.setInt(2, formule.getDuree());
			pst.setInt(3, formule.getNbreEntree());
			pst.setString(4, formule.getLabel());
			pst.setString(5, formule.getType());

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
			String requete = "UPDATE " + TABLE + " SET  " + PRIX + " = ?, " + VALIDITEE + " = ?, " + NB_ENTREE + " = ?, "
					+ LABEL +" = ?, "+TYPE+" = ? WHERE " + ID_FORMULE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setFloat(1, formule.getPrix());
			pst.setInt(2, formule.getDuree());
			pst.setInt(3, formule.getNbreEntree());
			pst.setString(4, formule.getLabel());
			pst.setString(5, formule.getType());
			pst.setInt(6, formule.getIdFormule());

			pst.executeUpdate();
			succes = true;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Formule read(int idFormule) {
		Formule formule = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = " + idFormule + ";";
			ResultSet rs = Connexion.executeQuery(requete);
			if (rs.next()) {
				int id = rs.getInt(ID_FORMULE);
				float prix = rs.getFloat(PRIX);
				int validitee = rs.getInt(VALIDITEE);
				int nbEntree = rs.getInt(NB_ENTREE);
				String label = rs.getString(LABEL);
				String type = rs.getString(TYPE);
				
				formule = new Formule(id, prix, validitee, nbEntree, label, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formule;
	}
	// -----------------------------------------------------------------------------------------------------------------------

}
