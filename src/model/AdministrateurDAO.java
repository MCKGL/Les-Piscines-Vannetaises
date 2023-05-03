   package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.Employee;
import service.InfoAdministrateur;

public class AdministrateurDAO extends DAO<InfoAdministrateur> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private static final String CLE_PRIMAIRE = "id_admin";
	private static final String TABLE = "info_admin";
	private static final String MDP = "mdp";
	private final String EMPLOYEE = "id_employe";
	

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

			String requete = "INSERT INTO " + TABLE + " ( " +CLE_PRIMAIRE+", "+ MDP + ", " + EMPLOYEE + ") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, admin.getIdAdmin());
			pst.setString(2, admin.getMdp());
			pst.setInt(3, admin.getIdEmployee().getIdEmployee());

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
			String requete = "UPDATE " + TABLE + " SET " + MDP + " = ?, " + EMPLOYEE + " = ? WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, admin.getMdp());
			pst.setInt(2, admin.getIdEmployee().getIdEmployee());
			pst.setInt(3, admin.getIdAdmin());

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
			int idEmployee = rs.getInt(EMPLOYEE);
			Employee employee = EmployeeDAO.getInstance().read(idEmployee);
					
			admin = new InfoAdministrateur(mdp, employee);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

}
