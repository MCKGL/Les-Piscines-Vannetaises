package model;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.Adresse;
import controller.Piscine;

public class PiscineDAO extends DAO<Piscine> {

	private final String CLE_PRIMAIRE = "id_piscine";
	private final String TABLE = "piscine";
	private final String NOM = "nom";
	private final String ADRESSE = "id_adresse";
	
	private static PiscineDAO instance = null;
	
	public PiscineDAO() {
		super();
	}
	
	public static PiscineDAO getInstance() {
		if (instance == null) {
			instance = new PiscineDAO();
		}
		return instance;
	}

	@Override
	public boolean create(Piscine piscine) {
		boolean succes = true;
		try {
			String requete = "INSERT INTO " + TABLE + " (" + NOM + ", "+ ADRESSE +") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			// On associe les ? avec une valeur
			pst.setString(1, piscine.getNom());
			pst.setInt(2, piscine.getAdresse().getIdAdresse());

			// On exécute la requete
			pst.executeUpdate();

			// On récupère l'id généré par la base de donnée et on le stock dans idPiscine
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				piscine.setIdPiscine(rs.getInt(1));
			}

			donnees.put(piscine.getIdPiscine(), piscine);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
		
	@Override
	public boolean delete(Piscine piscine) {
		boolean succes = true;
		int id = piscine.getIdPiscine();
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

	@Override
	public boolean update(Piscine piscine) {
		boolean succes = true;
		try {
			String requete = "UPDATE " + TABLE + " SET  " + NOM + " = ?, " + ADRESSE + " = ? WHERE " + CLE_PRIMAIRE+ " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, piscine.getNom());
			pst.setInt(2, piscine.getAdresse().getIdAdresse());

			pst.executeUpdate();

			// Mise à jour de l'objet adresse dans le tableau "donnees"
			donnees.put(piscine.getIdPiscine(), piscine);

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Piscine read(int id) {
		Piscine piscine = null;
		try {
			// Requête dans la table cours
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + "= ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();
			
			// On demande le premier résultat de la requête
			ResultSet rs = pst.getResultSet();
			rs.next();
			
			// On associe les valeurs des champs à des variables
			String nom = rs.getString(NOM);
			int idAdresse = rs.getInt(ADRESSE);
			Adresse adresse = AdresseDAO.getInstance().read(idAdresse);
			
			// Création d'un objet à partir des valeurs récupérées de la base de donnée
			piscine = new Piscine(id, nom, adresse);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return piscine;
	}
	
}
