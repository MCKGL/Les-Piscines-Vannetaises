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
	private String CLE_PRIMAIRE = "id_formule";
	private String TABLE = "formule";

	private String ID_FORMULE = "id_formule";
	private String TYPE = "type";
	private String LABEL = "label";
	private String VALIDITEE = "duree_validitee";
	private String PRIX = "prix_final";
	private String NBRE_ENTREE = "nombre_entree";

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
			String requete = "INSERT INTO " + TABLE + " ( " + TYPE + ", " + LABEL + ", " + VALIDITEE + ", " + PRIX + ", " + NBRE_ENTREE+ ") VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, formule.getType());
			pst.setLong(2, formule.getDureeEnSeconde());
			pst.setInt(3, formule.getPrixFormule());
			pst.setInt(4, formule.getNbreEntreeFormule());
			pst.setString(5, formule.getLabel());

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
			String requete = "UPDATE " + TABLE + " SET  " + TYPE + " = ?, " + LABEL + " = ?, " + VALIDITEE + " = ?, " + PRIX + " = ?, "
					+ NBRE_ENTREE + " = ? WHERE " + ID_FORMULE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, formule.getType());
			pst.setLong(2, formule.getDureeEnSeconde());
			pst.setInt(3, formule.getPrixFormule());
			pst.setInt(4, formule.getNbreEntreeFormule());
			pst.setInt(5, formule.getIdFormule());
			pst.setString(6, formule.getLabel());

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
			rs.next();
			int id = rs.getInt(ID_FORMULE);
			String type = rs.getString(TYPE);
			String label = rs.getString(LABEL);
			long validitee = rs.getLong(VALIDITEE);
			int prix = rs.getInt(PRIX);
			int nbre_entree = rs.getInt(NBRE_ENTREE);

			formule = new Formule(id, type, label, validitee, prix, nbre_entree);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formule;
	}
	// -----------------------------------------------------------------------------------------------------------------------

}
