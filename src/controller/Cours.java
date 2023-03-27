package controller;

import java.util.ArrayList;
import java.util.List;

public class Cours {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private int idCours;
	private String nom;
	private String description;
	private int nbreSeance;
	private String trancheAge;
	private String type;
	private int nbrePersonne;
	
	private int actif = 1;
	private List<Seance> listeSeances = new ArrayList<Seance>();

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
	public Cours(int idCours, String nom, String description, int nbreSeance, String trancheAge, String type,
			int nbrePersonne) {
		super();
		this.idCours = idCours;
		this.nom = nom;
		this.description = description;
		this.nbreSeance = nbreSeance;
		this.trancheAge = trancheAge;
		this.type = type;
		this.nbrePersonne = nbrePersonne;
	}

	public Cours(String nom, String description, int nbreSeance, String trancheAge, String type, int nbrePersonne) {
		super();
		this.nom = nom;
		this.description = description;
		this.nbreSeance = nbreSeance;
		this.trancheAge = trancheAge;
		this.type = type;
		this.nbrePersonne = nbrePersonne;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbreSeance() {
		return nbreSeance;
	}

	public void setNbreSeance(int nbreSeance) {
		this.nbreSeance = nbreSeance;
	}

	public String getTrancheAge() {
		return trancheAge;
	}

	public void setTrancheAge(String trancheAge) {
		this.trancheAge = trancheAge;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNbrePersonne() {
		return nbrePersonne;
	}

	public void setNbrePersonne(int nbrePersonne) {
		this.nbrePersonne = nbrePersonne;
	}


	public List<Seance> getListeSeances() {
		return listeSeances;
	}

	public void setListeSeances(List<Seance> listSeances) {
		this.listeSeances = listSeances;
	}
	
	public int getActif() {
		return actif;
	}

	public void setActif(int actif) {
		this.actif = actif;
	}

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
		return "Cours [idCours=" + idCours + ", nom=" + nom + ", description=" + description + ", nbreSeance="
				+ nbreSeance + ", trancheAge=" + trancheAge + ", type=" + type + ", nbrePersonne=" + nbrePersonne
				+ ", coursDispensee=" + listeSeances + "]";
	}
}
