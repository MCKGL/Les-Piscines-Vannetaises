package controller;

import java.util.ArrayList;
import java.util.List;

public class Formule {
	/**
	 * Attributs
	 * 
	 * id de la formule
	 * Le type de formule (nom)
	 * durée de la formule en seconde
	 * prix de la formule
	 * nombre d'entrée de la formule
	 */
	private int idFormule;
	private String type;
	private long dureeEnSeconde;
	private int prixFormule;
	private int nbreEntreeFormule;
	private List<Seance> listeSeance = new ArrayList<Seance>();
	
	/**
	 * Constructeur global
	 * 
	 * @param idFormule
	 * @param type
	 * @param dureeEnSeconde
	 * @param prixFormule
	 * @param nbreEntreeFormule
	 */
	public Formule(int idFormule, String type, long dureeEnSeconde, int prixFormule, int nbreEntreeFormule) {
		super();
		this.idFormule = idFormule;
		this.type = type;
		this.dureeEnSeconde = dureeEnSeconde;
		this.prixFormule = prixFormule;
		this.nbreEntreeFormule = nbreEntreeFormule;
	}

	/**
	 * Constructeur sans l'ID
	 * 
	 * @param type
	 * @param dureeEnSeconde
	 * @param prixFormule
	 * @param nbreEntreeFormule
	 */
	public Formule(String type, long dureeEnSeconde, int prixFormule, int nbreEntreeFormule) {
		super();
		this.type = type;
		this.dureeEnSeconde = dureeEnSeconde;
		this.prixFormule = prixFormule;
		this.nbreEntreeFormule = nbreEntreeFormule;
	}
	/**
	 * Getter 
	 * Setter
	 */
	public int getIdFormule() {
		return idFormule;
	}

	public void setIdFormule(int idFormule) {
		this.idFormule = idFormule;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getDureeEnSeconde() {
		return dureeEnSeconde;
	}

	public void setDureeEnSeconde(long dureeEnSeconde) {
		this.dureeEnSeconde = dureeEnSeconde;
	}

	public int getPrixFormule() {
		return prixFormule;
	}

	public void setPrixFormule(int prixFormule) {
		this.prixFormule = prixFormule;
	}

	public int getNbreEntreeFormule() {
		return nbreEntreeFormule;
	}

	public void setNbreEntreeFormule(int nbreEntreeFormule) {
		this.nbreEntreeFormule = nbreEntreeFormule;
	}
	
	/**
	 * METHODES
	 */

}
