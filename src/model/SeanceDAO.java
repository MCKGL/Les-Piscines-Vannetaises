package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import controller.Cours;
import controller.Seance;

public class SeanceDAO extends DAO<Seance> {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private final String CLE_PRIMAIRE = "id_seance";
	private final String TABLE = "seance";

	private final String ID_SEANCE = "id_seance";
	private final String DATE = "date";
	private final String HEURE_DEBUT = "heure_debut";
	private final String HEURE_FIN = "heure_fin";
	private final String PRIX = "prix_unitaire";
	private final String NBRE_PLACE = "nombre_place_reserve";
	private final String ID_COURS = "id_cours";

	private static SeanceDAO instance = null;

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * 
	 * @return instance;
	 */
	public static SeanceDAO getInstance() {
		if (instance == null) {
			instance = new SeanceDAO();
		}
		return instance;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean create(Seance seance) {
		boolean succes = true;
		try {
			/**
			 * Création d'un tuple dans la base de donnée
			 * 
			 * TODO : il faut ajouter la séance dans la liste du cours Dans la BD il faut
			 * mettre à jour la table d'association
			 */

			String requete = "INSERT INTO " + TABLE + " (" + DATE + ", " + HEURE_DEBUT + ", " + HEURE_FIN + ", " + PRIX
					+ ", " + NBRE_PLACE + ", " + ID_COURS + ") VALUES (?,?,?,?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			// On associe les ? avec une valeur
			pst.setObject(1, seance.getDate());
			pst.setObject(2, seance.getHeureDebut());
			pst.setObject(3, seance.getHeureFin());
			pst.setInt(4, seance.getPrix());
			pst.setInt(5, seance.getNbrePlace());
			pst.setInt(6, seance.getIdCours());

			// On exécute la requete
			pst.executeUpdate();

			// On récupère l'id généré par la base de donnée et on le stock dans idCours du
			// cours
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				seance.setIdSeance(rs.getInt(1));
			}

			// On stock l'objet administrateur dans le tableau "donnees"
			donnees.put(seance.getIdSeance(), seance);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Seance seance) {
		boolean succes = true;
		int id = seance.getIdSeance();
		try {
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();

			// On supprimer l'objet adresse dans le tableau "donnees"
			donnees.remove(id);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Seance seance) {
		boolean succes = true;
		try {
			String requete = "UPDATE " + TABLE + " SET  " + DATE + " = ?, " + HEURE_DEBUT + " = ?, " + HEURE_FIN
					+ " = ?, " + PRIX + " = ?, " + NBRE_PLACE + " = ?, " + ID_COURS +" = ? WHERE " + CLE_PRIMAIRE
					+ " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, seance.getDate());
			pst.setObject(2, seance.getHeureDebut());
			pst.setObject(3, seance.getHeureFin());
			pst.setInt(4, seance.getPrix());
			pst.setInt(5, seance.getNbrePlace());
			pst.setInt(6, seance.getIdCours());
			pst.setInt(7, seance.getIdSeance());
			

			pst.executeUpdate();

			// Mise à jour de l'objet adresse dans le tableau "donnees"
			donnees.put(seance.getIdSeance(), seance);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	/**
	 * Le cours doit être affecté ensuite
	 * La séance créer contient tout sauf le cours
	 */
	@Override
	public Seance read(int id) {
		Seance seance = null;
		try {
			// Requête
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + "= ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();

			// On demande le premier résultat de la requête
			rs.next();

			// On associe les valeurs des champs à des variables
			LocalDate date = rs.getDate(DATE).toLocalDate();
			LocalTime timeD = rs.getTime(HEURE_DEBUT).toLocalTime();
			LocalTime timeF = rs.getTime(HEURE_FIN).toLocalTime();
			int prix = rs.getInt(PRIX);
			int nbrePlace = rs.getInt(NBRE_PLACE);
			int idCours = rs.getInt(ID_COURS);

			// Création d'un objet à partir des valeurs récupérées de la base de donnée
			seance = new Seance(id, date, timeD, timeF, prix, nbrePlace, idCours);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seance;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Récupérer toutes les séances en fonction d'un cours
	 * @param Cours cours
	 * @return liste<Seance>
	 */
	public List<Seance> readByCours(Cours cours) {
		List<Seance> liste = new ArrayList<Seance>();
		int idCours = cours.getIdCours();
		Seance seance = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + ID_COURS + " = ?;";
			PreparedStatement pst;
			pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idCours);
			pst.execute();

			ResultSet rs = pst.getResultSet();
			System.err.println("readTable : "+ rs.next());
			while (rs.next()) {
				int id = rs.getInt(ID_SEANCE);
				System.err.println("while :"+id);
				seance = SeanceDAO.getInstance().read(id);
				liste.add(seance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	// -----------------------------------------------------------------------------------------------------------------------

}
