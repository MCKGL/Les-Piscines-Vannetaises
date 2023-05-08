package modele.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.metier.Cours;
import modele.metier.Formule;



public class CoursDAO extends DAO<Cours> {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 * 
	 */
	private final String CLE_PRIMAIRE = "id_cours";
	private final String TABLE = "cours";
	private final String NOM = "nom";
	private final String DESCRIPTION = "description";
	private final String FORMULE = "id_formule";

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
			String requete = "INSERT INTO " + TABLE + " (" + NOM + ", " + DESCRIPTION + ", " + FORMULE + ") VALUES (?,?,?)";
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
			String requete = "UPDATE " + TABLE + " SET  " + NOM + " = ?, " + DESCRIPTION + " = ?, " + FORMULE + " = ? WHERE " + CLE_PRIMAIRE+ " = ?";
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
			int idformule = rsCours.getInt(FORMULE);
			Formule formule = FormuleDAO.getInstance().read(idformule);
			
			// Création d'un objet à partir des valeurs récupérées de la base de donnée
			cours = new Cours(id, nom, description, formule);	
			
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
			String requete = "SELECT * FROM " + TABLE + " WHERE " + NOM + " = ?";

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
	            int idformule = rs.getInt(FORMULE);
				Formule formule = FormuleDAO.getInstance().read(idformule);
	            
	            Cours cours = new Cours(id, nom, description, formule);
	            coursList.add(cours);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return coursList;
	}

	public Cours readByName(String nom) {
		Cours cours = null;
		try {
			// Requête dans la table cours
			String requeteCours = "SELECT * FROM " + TABLE + " WHERE " + NOM + "= ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requeteCours);
			pst.setString(1, nom);
			pst.execute();
			
			// On demande le premier résultat de la requête
			ResultSet rsCours = pst.getResultSet();
			rsCours.next();
			
			// On associe les valeurs des champs à des variables
			String description = rsCours.getString(DESCRIPTION);
			int idformule = rsCours.getInt(FORMULE);
			Formule formule = FormuleDAO.getInstance().read(idformule);
			
			// Création d'un objet à partir des valeurs récupérées de la base de donnée
			cours = new Cours(nom, description, formule);
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cours;
	}
	

}
