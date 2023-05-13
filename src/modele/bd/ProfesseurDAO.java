package modele.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.metier.Employe;
import modele.metier.InfoProfesseur;

public class ProfesseurDAO extends DAO<InfoProfesseur> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private static final String CLE_PRIMAIRE = "id_prof";
	private static final String TABLE = "info_prof";
	private static final String SPECIALITES = "specialite";
	private final String EMPLOYEE = "id_employe";

	private static ProfesseurDAO instance=null;
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * @return instance;
	 */
	public static ProfesseurDAO getInstance(){
		if (instance==null){
			instance = new ProfesseurDAO();
		}
		return instance;
	}
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	private ProfesseurDAO() {
		super();
	}
// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean create(InfoProfesseur prof) {
		boolean succes = true;
		try {

			String requete = "INSERT INTO " + TABLE + " ( " +CLE_PRIMAIRE+", "+ SPECIALITES + ", " + EMPLOYEE + ") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, prof.getIdProf());
			pst.setString(2, prof.getSpecialite());
			pst.setInt(3, prof.getIdEmployee().getIdEmployee());

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                prof.setIdProf(rs.getInt(1));
            }
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	@Override
	public boolean update(InfoProfesseur prof) {
		boolean succes = true;
		try {
			String requete = "UPDATE " + TABLE + " SET " + SPECIALITES + " = ?, " + EMPLOYEE + " = ? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, prof.getSpecialite());
			pst.setInt(2, prof.getIdEmployee().getIdEmployee());
			pst.setInt(3, prof.getIdProf());

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	@Override
	public boolean delete(InfoProfesseur prof) {
		boolean succes = true;
		int idProf = prof.getIdProf();
		try {
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idProf);

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public InfoProfesseur read(int idProf) {
		InfoProfesseur prof = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idProf);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String spes = rs.getString(SPECIALITES);
			int idEmployee = rs.getInt(EMPLOYEE);
			Employe employee = EmployeDAO.getInstance().read(idEmployee);
					
			prof = new InfoProfesseur(idProf, spes, employee);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prof;
	}
	
	public List<InfoProfesseur> readAll() {
	    List<InfoProfesseur> professeurs = new ArrayList<>();
	    try {
	        String requete = "SELECT * FROM " + TABLE;
	        PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            int idProf = rs.getInt(CLE_PRIMAIRE);
	            String spes = rs.getString(SPECIALITES);
	            int idEmployee = rs.getInt(EMPLOYEE);
	            Employe employee = EmployeDAO.getInstance().read(idEmployee);
	            InfoProfesseur professeur = new InfoProfesseur(spes, employee);
	            professeur.setIdProf(idProf);
	            professeurs.add(professeur);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return professeurs;
	}
	
	public InfoProfesseur readByPrenom(String prenom) {
	    InfoProfesseur prof = null;
//	    String TABLE2 = "employe";
//	    String COLONNE1 = "info_prof.id_employe";
//	    String COLONNE2 = "employe.id_employe";
//	    String COLONNE = "id.employe";
	    //TODO sécurisé requête
	    try {
	        String requete = "SELECT info_prof.*, employe.* FROM employe JOIN info_prof ON employe.id_employe = info_prof.id_employe WHERE prenom = ?";
	        PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
	        pst.setString(1, prenom);
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()){
	            String spes = rs.getString(SPECIALITES);
	            int idProf = rs.getInt(CLE_PRIMAIRE);
	            Employe employee = EmployeDAO.getInstance().read(rs.getInt("id_employe"));
	            prof = new InfoProfesseur(idProf, spes, employee);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return prof;
	}
	
}
