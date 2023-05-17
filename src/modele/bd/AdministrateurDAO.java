   package modele.bd;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modele.metier.Employe;
import modele.metier.InfoAdministrateur;

public class AdministrateurDAO extends DAO<InfoAdministrateur> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private static final String CLE_PRIMAIRE = "id_admin";
	private static final String TABLE = "info_admin";
	private static final String MDP = "mdp";
	private static final String ID = "identifiant";
	private final String EMPLOYE = "id_employe";
	

	private static AdministrateurDAO instance=null;
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * @return instance;
	 */
	public static AdministrateurDAO getInstance(){
		if (instance==null){
			instance = new AdministrateurDAO();
		}
		return instance;
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	private AdministrateurDAO() {
		super();
	}

	@Override
	public boolean create(InfoAdministrateur admin) {
		boolean succes = true;
		try {

			String requete = "INSERT INTO " + TABLE + " ( " +CLE_PRIMAIRE+", "+ MDP + ", "+ ID + ", " + EMPLOYE + ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, admin.getIdAdmin());
			pst.setString(2, admin.getMdp());
			pst.setString(3, admin.getIdConnect());
			pst.setInt(4, admin.getIdEmployee().getIdEmployee());

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	@Override
	public boolean update(InfoAdministrateur admin) {
		boolean succes = true;
		try {
			String requete = "UPDATE " + TABLE + " SET " + MDP + " = ?, " + ID + " = ?, " + EMPLOYE + " = ? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, admin.getMdp());
			pst.setString(2, admin.getIdConnect());
			pst.setInt(3, admin.getIdEmployee().getIdEmployee());
			pst.setInt(4, admin.getIdAdmin());

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	@Override
	public boolean delete(InfoAdministrateur admin) {
		boolean succes = true;
		int idAdmin = admin.getIdAdmin();
		try {
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdmin);

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public InfoAdministrateur read(int idAdmin) {
		InfoAdministrateur admin = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdmin);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String mdp = rs.getString(MDP);
			String idConnect = rs.getString(ID);
			int idEmployee = rs.getInt(EMPLOYE);
			Employe employee = EmployeDAO.getInstance().read(idEmployee);
					
			admin = new InfoAdministrateur(mdp, idConnect, employee);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	//lire par identifiant de connexion
	public InfoAdministrateur readByIdentifiant(String identifant) {
		InfoAdministrateur admin = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + ID + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, identifant);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String mdp = rs.getString(MDP);
			int idEmployee = rs.getInt(EMPLOYE);
			Employe employee = EmployeDAO.getInstance().read(idEmployee);
					
			admin = new InfoAdministrateur(mdp, identifant, employee);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

}
