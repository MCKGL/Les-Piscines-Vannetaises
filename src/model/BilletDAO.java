package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import controller.Billet;
import controller.Formule;

public class BilletDAO extends DAO<Billet> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs de base de donnée
	 * 
	 * Noms des champs d'une ligne
	 * 
	 * Instance existante si oui sauvegardée
	 */
	private String CLE_PRIMAIRE = "code";
	private String TABLE = "billet";

	private String NBRE_ENTREE_RESTANTE = "nombre_entre_restante";
	
	private final String FORMULE = "id_formule";
	private final String PISCINE = "id_piscine";

	private static BilletDAO instance = null;
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Pâton de conception singleton Permet d'être sur de n'avoir qu'une instance de
	 * la classe
	 * 
	 * @return l'instance déjà ouverte si elle existe
	 */
	public static BilletDAO getInstance() {
		if (instance == null) {
			instance = new BilletDAO();
		}
		return instance;
	}
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * CRUD Create / Read / Update / Delete
	 */
	public boolean create(Billet billet) {
		boolean succes = true;
		try {
			int code = billet.getCode();
			int nbreEntreeRestante = billet.getNbreEntreeRestante();

			String requete = "INSERT INTO " + TABLE + " ( " +CLE_PRIMAIRE+", "+ NBRE_ENTREE_RESTANTE + ", " + FORMULE + ", " + PISCINE + ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, code);
			pst.setInt(2, nbreEntreeRestante);
			pst.setInt(3, billet.getFormule().getIdFormule());
			pst.setInt(4, billet.getPiscine().getIdPiscine()); // création class piscine

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			// TODO
//			donnees.put(billet.getCode(), billet);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	public boolean delete(Billet billet) {
		boolean succes = true;
		int code = billet.getCode();
		try {
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, code);
			pst.executeUpdate();
			
			// TODO
//			donnees.remove();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	public boolean update(Billet billet) {
		boolean succes = true;
		try {
			String requete = "UPDATE " + TABLE + " SET  "+NBRE_ENTREE_RESTANTE + " = ?, " + FORMULE + " = ?, " + PISCINE + " = ?   WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, billet.getNbreEntreeRestante());
			pst.setInt(2, billet.getFormule().getIdFormule());
			pst.setInt(3, billet.getPiscine().getIdPiscine()); // ATTENTE CREA
			pst.setInt(4, billet.getCode());

			pst.executeUpdate();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	public Billet read(int code) {
		Billet billet = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setFloat(1, code);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			int id = rs.getInt(CLE_PRIMAIRE);
			int nbre_entree_restante = rs.getInt(NBRE_ENTREE_RESTANTE);
			int idformule = rs.getInt(FORMULE);
			Formule formule = FormuleDAO.getInstance().read(idformule);
			int idpiscine = rs.getInt(PISCINE);
			Formule piscine = PiscineDAO.getInstance().read(idpiscine); // ATT CREATE

			billet = new Billet(code, nbre_entree_restante, formule, piscine);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return billet;
	}

}
