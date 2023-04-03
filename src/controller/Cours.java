package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cours {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private int idCours;
	private String nom;
	private String description;
	private Formule formule;
	
	//private int actif = 1;
	private List<Seance> listeSeances = new ArrayList<Seance>();
	private Map<Integer, Integer> listeCoursProfs = new HashMap<Integer, Integer>();

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * * Constructeur Cours
	 * 
	 * @param idCours
	 * @param nom
	 * @param description
	 * @param nbreSeance
	 * @param trancheAge
	 * @param type
	 * @param nbrePersonne
	 */
	public Cours(int idCours, String nom, String description, Formule formule) {
		super();
		this.idCours = idCours;
		this.nom = nom;
		this.description = description;
		this.formule = formule;
	}

	public Cours(String nom, String description, Formule formule) {
		super();
		this.nom = nom;
		this.description = description;
		this.formule = formule;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Getter Setter
	 */
	public int getIdCours() {
		return idCours;
	}

	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Formule getFormule() {
		return formule;
	}

	public void setFormule(Formule formule) {
		this.formule = formule;
	}

	public Map<Integer, Integer> getListeCoursProfs() {
		return listeCoursProfs;
	}

	public void setListeCoursProfs(Map<Integer, Integer> listeCoursProfs) {
		this.listeCoursProfs = listeCoursProfs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Seance> getListeSeances() {
		return listeSeances;
	}

	public void setListeSeances(List<Seance> listSeances) {
		this.listeSeances = listSeances;
	}
	
	/*public int getActif() {
		return actif;
	}

	public void setActif(int actif) {
		this.actif = actif;
	}*/

	// -----------------------------------------------------------------------------------------------------------------------
	public boolean add(Seance seance) {
		return this.listeSeances.add(seance);
	}
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Override 
	 */
	@Override
	public String toString() {
		return "Cours [idCours=" + idCours + ", nom=" + nom + ", description=" + description + ", "
				+ "coursDispensee=" + listeSeances + ", formule=" + formule + "]";
	}
}
