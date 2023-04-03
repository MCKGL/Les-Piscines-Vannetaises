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
	private int prixFormule;
	private long dureeValidite;
	private int nbreEntreeFormule;
	private String label;
	private String type;
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
	public Formule(int idFormule, String type, String label, long dureeEnSeconde, int prixFormule, int nbreEntreeFormule) {
		super();
		this.idFormule = idFormule;
		this.type = type;
		this.label = label;
		this.dureeValidite = dureeEnSeconde;
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
	public Formule(String type, String label, long dureeEnSeconde, int prixFormule, int nbreEntreeFormule) {
		super();
		this.type = type;
		this.dureeValidite = dureeEnSeconde;
		this.prixFormule = prixFormule;
		this.nbreEntreeFormule = nbreEntreeFormule;
		this.label = label;
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
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public long getDureeEnSeconde() {
		return dureeValidite;
	}

	public void setDureeEnSeconde(long dureeEnSeconde) {
		this.dureeValidite = dureeEnSeconde;
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
