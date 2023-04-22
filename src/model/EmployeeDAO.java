package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.Adresse;
import controller.Employee;
import controller.InfoAdministrateur;
import controller.InfoProfesseur;

public class EmployeeDAO extends DAO<Employee> {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private final String CLE_PRIMAIRE = "id_employe";
	private final String TABLE = "employe";

	private final String NOM = "nom";
	private final String PRENOM = "prenom";
	private final String NOM_MEMBRE = "pseudo";
	private final String EMAIL = "mail";
	private final String DATE = "date_naissance";
	private final String ID_ADRESSE = "id_adresse";
	private final String ACTIF = "actif";

	private static EmployeeDAO instance=null;

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	public EmployeeDAO() {
		super();
	}
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * @return instance;
	 */
	public static EmployeeDAO getInstance(){
		if (instance==null){
			instance = new EmployeeDAO();
		}
		return instance;
	}
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Création d'un employé dans la base de donnée
	 * Création d'une adresse associé dans la base de donnée
	 * Création (possible) d'un administrateur associé dans la base de donnée
	 * Création (possible) d'un professeur associé dans la base de donnée
	 */
	@Override
	public boolean create(Employee employee) {
		boolean succes=true;
		try {
			/** 
			 * Création de l'adresse dans la base de données avant celle de l'employé
			 * Vérifier que l'adresse (read sur son id) n'existe pas déjà
			 * Si elle existe récupérer l'adresse correspondante
			 * Sinon créer la ligne dans la base de donnée
			*/
			if (AdresseDAO.getInstance().existe(employee.getAdresse())) {
				int id = AdresseDAO.getInstance().readIdAdresse(employee.getAdresse());
				employee.getAdresse().setIdAdresse(id);
			} else {
				AdresseDAO.getInstance().create(employee.getAdresse());
				int id = AdresseDAO.getInstance().readIdAdresse(employee.getAdresse());
				employee.getAdresse().setIdAdresse(id);
			}

			// Création de l'employé dans la base de donnée
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+PRENOM+", "+NOM_MEMBRE+" , "+EMAIL+", "+DATE+", "+ID_ADRESSE+", "+ACTIF+") VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, employee.getNom());
			pst.setString(2, employee.getPrenom());
			pst.setString(3, employee.getNomMembre());
			pst.setString(4, employee.getEmail());
			pst.setObject(5, employee.getDateNaissanceSQL());
			pst.setInt(6, employee.getAdresse().getIdAdresse());
			pst.setInt(7, 1);
			// Rendre l'employer actif
			employee.setActif(true);
			// Mise à jour de la base de donnée
			pst.executeUpdate();
			
			// Récupérer la clef identifiante générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				employee.setIdEmployee(rs.getInt(1));
			}

			// Si l'employé est un admin stocké l'idEmployee dans idAdmin
			// Lancer la création d'une ligne dans la table administrateur
			if (employee.isAdmin()) {
				//employee.getAdministrateur().setIdAdmin(employee.getIdEmployee());
				AdministrateurDAO.getInstance().create(employee);
			}

			// Si l'employé est un admin stocké l'idEmployee dans idProfesseur
			// Lancer la création d'une ligne dans la table professeur
			if (employee.isProf()) {
				//employee.getProfesseur().setIdProfesseur(employee.getIdEmployee());
				ProfesseurDAO.getInstance().create(employee);
			}
			/**
			 * Cache
			 */
			donnees.put(employee.getIdEmployee(), employee);
			
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Suppression d'un employé dans la base de donnée
	 * Suppression d'une adresse associé dans la base de donnée
	 * Suppression (possible) d'un administrateur associé dans la base de donnée
	 * Suppression (possible) d'un professeur associé dans la base de donnée
	 */
	@Override
	public boolean delete(Employee employee) {
		boolean succes=true;
		int id = employee.getIdEmployee();
		try {

			// Si l'employé est administrateur supprimer la ligne dans la table administrateur
			if (employee.isAdmin()) {
				AdministrateurDAO.getInstance().delete(employee);
			}

			// Si l'employé est professeur supprimer la ligne dans la table professeur
			if (employee.isProf()) {
				ProfesseurDAO.getInstance().delete(employee);
			}

			// Supprimer dans la table employee l'employee séléctionné
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();

			// Supprimer dans la table employé l'adresse associée
			AdresseDAO.getInstance().delete(employee.getAdresse());

			// Retirer l'employée du tableau "donnees"
			donnees.remove(id);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Mise à jour d'une ligne employé dans la base de donnée
	 * Mise à jour d'une adresse associé dans la base de donnée
	 * Mise à jour (possible) d'un administrateur associé dans la base de donnée
	 * Mise à jour (possible) d'un professeur associé dans la base de donnée
	 */
	@Override
	public boolean update(Employee employee) {
		boolean succes=true;
		int id = employee.getIdEmployee();
		try {
			// Mettre à jour la table administrateur associé à un employé
			if (employee.isAdmin()) {
				if (AdministrateurDAO.getInstance().readInfoAdministrateur(id) != null) {
					System.err.println("Administrateur existant !");
					AdministrateurDAO.getInstance().update(employee);										
				} else {
					System.err.println("Administrateur doit être créé !");
					AdministrateurDAO.getInstance().create(employee);
				}
			}

			// Mettre à jour la table professeur associé à un employé
			if (employee.isProf()) {
				if (ProfesseurDAO.getInstance().readInfoProfesseur(id) != null) {
					System.err.println("Professeur existant !");
					ProfesseurDAO.getInstance().update(employee);					
				}else {
					System.err.println("Professeur doit être créé !");
					ProfesseurDAO.getInstance().create(employee);
				}
			}

			// Mettre à jour la table employee
			String requete = "UPDATE "+TABLE+" SET  "+NOM+" = ?, "+PRENOM+" = ?,"+NOM_MEMBRE+" = ?, "+EMAIL+" = ?, "+DATE+" = ?, "+ID_ADRESSE+" = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1, employee.getNom());
			pst.setString(2, employee.getPrenom());
			pst.setString(3, employee.getNomMembre());
			pst.setString(4, employee.getEmail());
			pst.setObject(5, employee.getDateNaissanceSQL());
			pst.setInt(6, employee.getAdresse().getIdAdresse());
			pst.setInt(7, employee.getIdEmployee());
			pst.executeUpdate() ;

			// Mettre à jour la table adresse dont dépent l'employé
			AdresseDAO.getInstance().update(employee.getAdresse());

//			// TODO FINIR VERIFIER // Supprimer (refait au dessus)
//			// Si l'employé est un admin
//			// Lancer la création d'une ligne dans la table administrateur
//			if (employee.isAdmin()) {
//				AdministrateurDAO.getInstance().update(employee);
//			}
//
//			// Si l'employé est un professeur
//			// Lancer la création d'une ligne dans la table professeur
//			if (employee.isProf()) {
//				ProfesseurDAO.getInstance().update(employee);
//			}
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Lecture d'un employé dans la base de donnée
	 * Lecture d'une adresse associé dans la base de donnée
	 * Lecture (possible) d'un administrateur associé dans la base de donnée
	 * Lecture (possible) d'un professeur associé dans la base de donnée
	 */
	@Override
	public Employee read(int idEmployee) {
		Employee employee = null;
		InfoAdministrateur admin = null;
		InfoProfesseur professeur = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+idEmployee+";";
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			String prenom = rs.getString(PRENOM);
			String nomMembre = rs.getString(NOM_MEMBRE);
			String email = rs.getString(EMAIL);
			LocalDate date = rs.getDate(DATE).toLocalDate();
			int idAdresse = rs.getInt(ID_ADRESSE);

			// Lire la table adresse associé à un employé
			Adresse adresse = AdresseDAO.getInstance().read(idAdresse);
			
			// Instancier la classe employee
			employee=new Employee(idEmployee, nom , prenom , nomMembre , email , date, adresse);

			// Lire la table administrateur associé à un employé
			if (AdministrateurDAO.getInstance().existe(idEmployee)) {
				admin = AdministrateurDAO.getInstance().readInfoAdministrateur(idEmployee);
				employee.setInfoAdministrateur(admin);
			}
			
			// Lire la table professeur associé à un employé
			if (ProfesseurDAO.getInstance().existe(idEmployee)) {
				System.err.println("Debug read : "+ProfesseurDAO.getInstance().existe(idEmployee));
				professeur = ProfesseurDAO.getInstance().readInfoProfesseur(idEmployee);
				employee.setInfoProfesseur(professeur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	
	public List<Employee> readAll() {
	    List<Employee> employees = new ArrayList<>();
	    try {
	        String requete = "SELECT * FROM " + TABLE;
	        ResultSet rs = Connexion.executeQuery(requete);
	        while (rs.next()) {
	            int idEmployee = rs.getInt(CLE_PRIMAIRE);
	            String nom = rs.getString(NOM);
	            String prenom = rs.getString(PRENOM);
	            String nomMembre = rs.getString(NOM_MEMBRE);
	            String email = rs.getString(EMAIL);
	            LocalDate date = rs.getDate(DATE).toLocalDate();
	            int idAdresse = rs.getInt(ID_ADRESSE);

	            // Lire la table adresse associé à un employé
	            Adresse adresse = AdresseDAO.getInstance().read(idAdresse);

	            // Instancier la classe employee
	            Employee employee = new Employee(idEmployee, nom , prenom , nomMembre , email , date, adresse);

	            // Lire la table administrateur associé à un employé
	            if (AdministrateurDAO.getInstance().existe(idEmployee)) {
	                InfoAdministrateur admin = AdministrateurDAO.getInstance().readInfoAdministrateur(idEmployee);
	                employee.setInfoAdministrateur(admin);
	            }

	            // Lire la table professeur associé à un employé
	            if (ProfesseurDAO.getInstance().existe(idEmployee)) {
	                InfoProfesseur professeur = ProfesseurDAO.getInstance().readInfoProfesseur(idEmployee);
	                employee.setInfoProfesseur(professeur);
	            }

	            employees.add(employee);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return employees;
	}
	
	
}


