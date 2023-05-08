package modele.bd;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.metier.Adresse;
import modele.metier.Employe;

public class EmployeDAO extends DAO<Employe> {

	private final String CLE_PRIMAIRE = "id_employe";
	private final String TABLE = "employe";
	private final String NOM = "nom";
	private final String PRENOM = "prenom";
	private final String EMAIL = "mail";
	private final String DATE = "date_naissance";
	private final String ID_ADRESSE = "id_adresse";

	private static EmployeDAO instance=null;

	public EmployeDAO() {
		super();
	}

	public static EmployeDAO getInstance(){
		if (instance==null){
			instance = new EmployeDAO();
		}
		return instance;
	}

	@Override
	public boolean create(Employe employee) {
		boolean succes = true;
		try {

			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+PRENOM+", "+EMAIL+", "+DATE+", "+ID_ADRESSE+") VALUES (?,?,?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, employee.getNom());
			pst.setString(2, employee.getPrenom());
			pst.setString(3, employee.getMail());
			pst.setObject(4, employee.getDateNaissance());
			pst.setInt(5, employee.getIdAdresse().getIdAdresse());

			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Employe employee) {
		boolean succes = true;
		int id = employee.getIdEmployee();
		try {
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Employe employee) {
		boolean succes = true;
		try {
			String requete = "UPDATE "+TABLE+" SET  "+NOM+" = ?, "+PRENOM+" = ?, "+EMAIL+" = ?, "+DATE+" = ?, "+ID_ADRESSE+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, employee.getNom());
			pst.setString(2, employee.getPrenom());
			pst.setString(3, employee.getMail());
			pst.setObject(4, employee.getDateNaissance());
			pst.setInt(5, employee.getIdAdresse().getIdAdresse());

			pst.executeUpdate();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Employe read(int id) {
		Employe employee = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String nom = rs.getString(NOM);
			String prenom = rs.getString(PRENOM);
			String mail = rs.getString(EMAIL);
			Date dateNaissance = rs.getDate(DATE);
			int idAdresse = rs.getInt(ID_ADRESSE);
			Adresse adresse = AdresseDAO.getInstance().read(idAdresse);
	
			employee = new Employe(nom, prenom, mail, dateNaissance, adresse);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employe> readAll() {
	    List<Employe> employees = new ArrayList<>();
	    try {
	        String requete = "SELECT * FROM " + TABLE;
	        ResultSet rs = Connexion.executeQuery(requete);
	        while (rs.next()) {
	            int idEmployee = rs.getInt(CLE_PRIMAIRE);
	            String nom = rs.getString(NOM);
	            String prenom = rs.getString(PRENOM);
	            String email = rs.getString(EMAIL);
	            Date date = rs.getDate(DATE);
	            int idAdresse = rs.getInt(ID_ADRESSE);
	            Adresse adresse = AdresseDAO.getInstance().read(idAdresse);

	            Employe employee = new Employe(idEmployee, nom, prenom, email, date, adresse);
	            employees.add(employee);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return employees;
	}
	
}


