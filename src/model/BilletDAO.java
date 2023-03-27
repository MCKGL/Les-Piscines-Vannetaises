package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import controller.Billet;

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

	private String DATE_ACHAT = "date_achat";
	private String DATE_PEREMPTION = "date_peremption";
	private String NBRE_ENTREE_RESTANTE = "nombre_entre_restante";

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
			LocalDateTime dateAchat = billet.getDateAchat();
			LocalDateTime datePeremption = billet.getDatePeremption();
			int nbreEntreeRestante = billet.getNbreEntreeRestante();

			String requete = "INSERT INTO " + TABLE + " ( " +CLE_PRIMAIRE+", "+ DATE_ACHAT + ", " + DATE_PEREMPTION +", "
					+ NBRE_ENTREE_RESTANTE + ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, code);
			pst.setObject(2, dateAchat);
			pst.setObject(3, datePeremption);
			pst.setInt(4, nbreEntreeRestante);

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
			String requete = "UPDATE " + TABLE + " SET  " + DATE_ACHAT + " = ?, " + DATE_PEREMPTION + " = ?, "+NBRE_ENTREE_RESTANTE + " = ? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, billet.getDateAchat());
			pst.setObject(2, billet.getDatePeremption());
			pst.setInt(3, billet.getNbreEntreeRestante());
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
			LocalDateTime date_achat = rs.getTimestamp(DATE_ACHAT).toLocalDateTime();
			LocalDateTime date_peremption = rs.getTimestamp(DATE_PEREMPTION).toLocalDateTime();
			int nbre_entree_restante = rs.getInt(NBRE_ENTREE_RESTANTE);

			billet = new Billet(code, date_achat, date_peremption, nbre_entree_restante);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return billet;
	}

}
