package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import service.Cours;
import service.Seance;

public class SeanceDAO extends DAO<Seance> {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private final String CLE_PRIMAIRE = "id_seance";
	private final String TABLE = "seance";

	private final String JOUR_SEMAINE = "jour_semaine";
	private final String HEURE_DEBUT = "heure_debut";
	private final String HEURE_FIN = "heure_fin";
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

			if (CoursDAO.getInstance().existe(seance.getCours())) {
				int id = CoursDAO.getInstance().readIdCours(seance.getCours());
				seance.getCours().setIdCours(id);
				
			} else {
				CoursDAO.getInstance().create(seance.getCours());
				int id = CoursDAO.getInstance().readIdCours(seance.getCours());
				seance.getCours().setIdCours(id);
			}

			String requete = "INSERT INTO " + TABLE + " (" + HEURE_DEBUT + ", " + HEURE_FIN + ", " + ID_COURS + ", " + JOUR_SEMAINE + ") VALUES (?,?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			// On associe les ? avec une valeur
			pst.setObject(1, seance.getHeureDebut());
			pst.setObject(2, seance.getHeureFin());
			pst.setInt(3, seance.getCours().getIdCours());
			pst.setString(4, seance.getJourSemaine());

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
			String requete = "UPDATE " + TABLE + " SET  " + HEURE_DEBUT + " = ?, " + HEURE_FIN + " = ?, " + ID_COURS + " = ?, " + JOUR_SEMAINE + " = ? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, seance.getHeureDebut());
			pst.setObject(2, seance.getHeureFin());
			pst.setInt(3, seance.getCours().getIdCours());
			pst.setInt(4, seance.getIdSeance());
			pst.setString(5, seance.getJourSemaine());

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
			LocalTime timeD = rs.getTime(HEURE_DEBUT).toLocalTime();
			LocalTime timeF = rs.getTime(HEURE_FIN).toLocalTime();
			String jourSemaine = rs.getString(JOUR_SEMAINE);
			int idCours = rs.getInt(ID_COURS);
			Cours cours = CoursDAO.getInstance().read(idCours);

			// On ne récupère pas le cours correspondant à la séance pour éviter une boucle infini
			//Cours cours = CoursDAO.getInstance().read(idCours);

			// Création d'un objet à partir des valeurs récupérées de la base de donnée
			seance = new Seance(id, jourSemaine, timeD, timeF, cours);

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
				int id = rs.getInt(CLE_PRIMAIRE);
				System.err.println("while :"+id);
				seance = SeanceDAO.getInstance().read(id);
				seance.setCours(cours);
				liste.add(seance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	// -----------------------------------------------------------------------------------------------------------------------
	
	public List<Seance> readAll() {
		List<Seance> seances = new ArrayList<>();

		try {
			String requete = "SELECT * FROM " + TABLE + ";";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.execute();
			ResultSet rs = pst.getResultSet();

			while (rs.next()) {
				int id = rs.getInt(CLE_PRIMAIRE);
				LocalTime timeD = rs.getTime(HEURE_DEBUT).toLocalTime();
				LocalTime timeF = rs.getTime(HEURE_FIN).toLocalTime();
				String jourSemaine = rs.getString(JOUR_SEMAINE);
				int idCours = rs.getInt(ID_COURS);
				Cours cours = CoursDAO.getInstance().read(idCours);

				Seance seance = new Seance(id, jourSemaine, timeD, timeF, cours);

				seances.add(seance);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return seances;
	}

}
