package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.Cours;
import controller.Employee;
import controller.Formule;
import controller.InfoProfesseur;
import controller.Seance;



public class CoursDAO extends DAO<Cours> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 * 
	 */
	private final String CLE_PRIMAIRE = "id_cours";
	private final String TABLE = "cours";
	private final String TABLE_COURS_PROF = "asso_prof_cours";
	
	private final String NOM = "nom";
	private final String DESCRIPTION = "description";
	private final String FORMULE = "id_formule";
	
	private final String ACTIF = "actif";
 
	private static CoursDAO instance = null;

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur privé
	 */
	public CoursDAO() {
		super();
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Instance unique pour se connecter
	 * 
	 * @return instance;
	 */
	public static CoursDAO getInstance() {
		if (instance == null) {
			instance = new CoursDAO();
		}
		return instance;
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Création d'un tuple cours dans la base de donnée
	 */
	@Override
	public boolean create(Cours cours) {
		boolean succes = true;
		try {
			/**
			 * Création d'un tuple dans la base de donnée cours
			 */
			String requete = "INSERT INTO " + TABLE + " (" + NOM + ", " + DESCRIPTION + ","+FORMULE+") VALUES (?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			// On associe les ? avec une valeur
			pst.setString(1, cours.getNom());
			pst.setString(2, cours.getDescription());
			pst.setInt(3, cours.getFormule().getIdFormule());			

			// On exécute la requete
			pst.executeUpdate();

			// On récupère l'id généré par la base de donnée et on le stock dans idCours du cours
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				cours.setIdCours(rs.getInt(1));
			}

			// On stock l'objet administrateur dans le tableau "donnees"
			donnees.put(cours.getIdCours(), cours);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Supprimer un cours de la base de donnée 17/01/2023 Fonctionnel
	 */
	@Override
	public boolean delete(Cours cours) {
		boolean succes = true;
		int id = cours.getIdCours();
		try {
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();

			// On supprimer l'objet administrateur du tableau "donnees"
			donnees.remove(id);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Mise à jour de la base de donnée 17/01/2023 Fonctionnel
	 */
	@Override
	public boolean update(Cours cours) {
		boolean succes = true;
		try {
			String requete = "UPDATE " + TABLE + " SET  " + NOM + " = ?, " + DESCRIPTION + " = ?, " + FORMULE
					+ " = ? WHERE " + CLE_PRIMAIRE
					+ " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, cours.getNom());
			pst.setString(2, cours.getDescription());
			pst.setInt(3, cours.getFormule().getIdFormule());
			pst.setInt(4, cours.getIdCours());

			pst.executeUpdate();

			// Mise à jour de l'objet adresse dans le tableau "donnees"
			donnees.put(cours.getIdCours(), cours);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	/**
	 * Lecture d'un cours dans la base de donnée
	 * Pour éviter la boucle infini entre séance et cours : 
	 * Etape 1 : création de cours avec une liste de séance vide
	 * Etape 2 : lecture des séances une par une sans le cours
	 * Etape 3 : Affectation du cours au séance
	 * Etape 4 : Affectation des séances au cours
	 */
	@Override
	public Cours read(int id) {
		Cours cours = null;
		try {
			// Requête dans la table cours
			String requeteCours = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + "= ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requeteCours);
			pst.setInt(1, id);
			pst.execute();
			
			// On demande le premier résultat de la requête
			ResultSet rsCours = pst.getResultSet();
			rsCours.next();
			
			// On associe les valeurs des champs à des variables
			String nom = rsCours.getString(NOM);
			String description = rsCours.getString(DESCRIPTION);
			int idFormule = rsCours.getInt(FORMULE);
			Formule formule = FormuleDAO.getInstance().read(idFormule);
			
			// Création d'un objet à partir des valeurs récupérées de la base de donnée
			cours = new Cours(id, nom, description, formule);
			
			cours.setListeSeances(SeanceDAO.getInstance().readByCours(cours));		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cours;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Savoir si un tuple existe par rapport aux paramètres et non à l'id
	 */
	public boolean existe(int id) {
		Boolean result = false;
		try {
			// Requête pour chercher une adresse correspondante
			String requete = "SELECT COUNT(*) FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id;
			
			// On execute la requête préparé et on récupère le jeu de donnée sous forme d'objet
			ResultSet rs = Connexion.executeQuery(requete);
			
			// Si il existe -> On sélectionne le premier jeu de donnée récupéré si il y en a plusieurs
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean existe(Cours cours) {
		boolean result = false;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + NOM + " = ? AND "
					+ " = ?";

			// Préparation de la requête
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, cours.getNom());

			// On execute la requête préparé
			pst.execute();

			// On récupère le jeu de donnée sous forme d'objet
			ResultSet rs = pst.getResultSet();
			// On sélectionne le premier jeu de donnée récupéré si il y en a plusieurs
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Récupérer l'id adresse d'un tuple dans la base de donnée
	 * 
	 * @param adresse;
	 * @return id adresse;
	 */
	public int readIdCours(Cours cours) {
		int idCours = 0;
		try {

			// Requête pour chercher une adresse correspondante
			String requete = "SELECT * FROM " + TABLE + " WHERE " + NOM + " = ? AND "+ " = ? AND "
					+ " = ?";

			// Préparation de la requête
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, cours.getNom());

			// On execute la requête préparé
			pst.execute();

			// On récupère le jeu de donnée sous forme d'objet
			ResultSet rs = pst.getResultSet();
			// On sélectionne le premier jeu de donnée récupéré si il y en a plusieurs
			if (rs.next()) {
				idCours = rs.getInt(CLE_PRIMAIRE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idCours;
	}
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Connaître l'état d'un cours : Actif/Inactif (1/0)
	 * @param seance
	 * @return
	 */
	public boolean readActif(Cours cours) {
		boolean result = false;
		try {
			// Requête
			String requete = "SELECT actif FROM cours WHERE "+CLE_PRIMAIRE+"= ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, cours.getIdCours());
			// Exécution de la requête
			pst.execute();
			// Récupération des données
			ResultSet rs = pst.getResultSet();
			// Premier jeu de donnée récupéré et récupération de son état "actif"
			rs.next();
			int num = rs.getInt("actif");
			// Connaître l'état et le renvoyer en boolean
			if (num == 0) {
				result = false;
			} else if (num == 1){
				result = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
// ---------- Lire toutes les entrées de la table cours	
	public List<Cours> readAll() {
	    List<Cours> coursList = new ArrayList<>();
	    try {
	        String requete = "SELECT * FROM " + TABLE;
	        PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt(CLE_PRIMAIRE);
	            String nom = rs.getString(NOM);
	            String description = rs.getString(DESCRIPTION);
				int idFormule = rs.getInt(FORMULE);
				Formule formule = FormuleDAO.getInstance().read(idFormule);
	            
	            Cours cours = new Cours(id, nom, description, formule);
	            cours.setListeSeances(SeanceDAO.getInstance().readByCours(cours));
	            
	            coursList.add(cours);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return coursList;
	}
	
	
	// Table d'association Cours professeur
	
	/**
	 * Trouver les professeurs associés à un cours
	 * @param id
	 * @return array liste de professeur
	 */
	public List<Employee> readAllByIdCours(int id) {
		List<Employee> listeProf = new ArrayList<Employee>();
		try {
			String requete = "SELECT * FROM " + TABLE_COURS_PROF + " WHERE id_cours = " + id;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id_prof = rs.getInt("id_professeur");
				listeProf.add(EmployeeDAO.getInstance().read(id_prof));
			}
			
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
		return listeProf;
	}
	
	/**
	 * Trouver les cours associés à un professeur
	 * @param id
	 * @return array liste de cours
	 */
	public List<Cours> readAllByIdProf(int id) {
		List<Cours> listeCours = new ArrayList<Cours>();
		try {
			String requete = "SELECT * FROM " + TABLE_COURS_PROF + " WHERE id_professeur = " + id;
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id_cours = rs.getInt("id_cours");
				listeCours.add(CoursDAO.getInstance().read(id_cours));
			}
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
		return listeCours;
	}
	
	/**
	 * Creer une ligne dans la table d'association cours professeur
	 * @param idProf
	 * @param idCours
	 * @return boolean
	 */
	public boolean createAssoCoursProf(int idCours, int idProf) {
		boolean success = false;
		try {
			Cours cours = CoursDAO.getInstance().read(idCours);
			Employee employee = EmployeeDAO.getInstance().read(idProf);
			if (cours != null && employee != null) {
				String requete = "INSERT INTO " + TABLE_COURS_PROF + " (id_professeur, id_cours) VALUES (?,?)";
				PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
				pst.setInt(1, idProf);
				pst.setInt(2, idCours);
				pst.execute();
				success = true;
			}
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
		return success;
	}
	
	/**
	 * Supprimer une ligne dans la table d'ssociation cours professeur
	 * @param idProf
	 * @param idCours
	 * @return boolean
	 */
	public boolean deleteAssoCoursProf(int idCours, int idProf) {
		boolean success = false;
		try {
			String requete = "DELETE FROM " + TABLE_COURS_PROF + " WHERE id_professeur = ? AND id_cours = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idProf);
			pst.setInt(2, idCours);
			pst.execute();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
}
