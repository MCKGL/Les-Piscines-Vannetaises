package modele.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modele.metier.Cours;
import modele.metier.InfoProfesseur;
import modele.metier.Seance;

public class SeanceDAO extends DAO<Seance> {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private final String CLE_PRIMAIRE = "id_seance";
	private final String TABLE = "seance";
	private final String DATE = "date";
	private final String DUREE = "duree";
	private final String ID_COURS = "id_cours";
	private final String ID_PROF = "id_prof";

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

	@Override
	public boolean create(Seance seance) {
		boolean succes = true;
		try {
			String requete = "INSERT INTO " + TABLE + " ("+ DATE + ", "+ DUREE + ", "+ ID_COURS + ", " + ID_PROF + ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, seance.getDate());
			pst.setInt(2, seance.getDuree());
			pst.setInt(3, seance.getIdCours().getIdCours());
			pst.setInt(4, seance.getIdProf().getIdProf());

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
			//Marche sans et sinon fait erreur.
//			ResultSet rs = pst.getGeneratedKeys();
//			if (rs.next()) {
//				seance.setIdSeance(rs.getInt(1));
//			}
			succes = true;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Seance seance) {
		boolean succes = true;
		int idSeance = seance.getIdSeance();
		try {
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idSeance);
			pst.executeUpdate();

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
			String requete = "UPDATE " + TABLE + " SET  "+DATE + " = ?, " + DUREE + " = ?, " + ID_COURS + " = ?, " + ID_PROF + " = ?   WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, seance.getDate());
			pst.setInt(2, seance.getDuree());
			pst.setInt(3, seance.getIdCours().getIdCours());
			pst.setInt(4, seance.getIdProf().getIdProf());
			pst.setInt(5, seance.getIdSeance());

			pst.executeUpdate();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Seance read(int id) {
		Seance seance = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			LocalDateTime date = rs.getTimestamp(DATE).toLocalDateTime();
			int duree = rs.getInt(DUREE);
			int idProf = rs.getInt(ID_PROF);
			InfoProfesseur prof = ProfesseurDAO.getInstance().read(idProf);
			int idCours = rs.getInt(ID_COURS);
			Cours cours = CoursDAO.getInstance().read(idCours);

			seance = new Seance(date, duree, prof, cours);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seance;
	}
	
	public List<Seance> readAll() {
		List<Seance> seances = new ArrayList<>();
		try {
			String requete = "SELECT * FROM " + TABLE;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(CLE_PRIMAIRE);
				LocalDateTime date = rs.getTimestamp(DATE).toLocalDateTime();
				int duree = rs.getInt(DUREE);
				int idProf = rs.getInt(ID_PROF);
				InfoProfesseur prof = ProfesseurDAO.getInstance().read(idProf);
				int idCours = rs.getInt(ID_COURS);
				Cours cours = CoursDAO.getInstance().read(idCours);

				Seance seance = new Seance(id, date, duree, prof, cours);
				seances.add(seance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seances;
	}
	
	public List<Seance> readAllByCoursId(int idCours) {
	    List<Seance> seances = new ArrayList<>();
	    try {
	        String requete = "SELECT * FROM " + TABLE + " WHERE " + ID_COURS + " = ?";
	        PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
	        pst.setInt(1, idCours);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt(CLE_PRIMAIRE);
	            LocalDateTime date = rs.getTimestamp(DATE).toLocalDateTime();
	            int duree = rs.getInt(DUREE);
	            int idProf = rs.getInt(ID_PROF);
	            InfoProfesseur prof = ProfesseurDAO.getInstance().read(idProf);
	            Cours cours = CoursDAO.getInstance().read(idCours);

	            Seance seance = new Seance(id, date, duree, prof, cours);
	            seances.add(seance);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return seances;
	}

}