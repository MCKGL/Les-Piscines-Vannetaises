package controller;

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
	private float prix;
	private int duree; // stock 1-12 
	private int nbreEntree;
	private String label;
	private String type;
	

	/**
	 * 
	 * @param idFormule
	 * @param prixFormule
	 * @param dureeEnSeconde
	 * @param nbreEntree
	 * @param label
	 * @param type
	 */
	public Formule(int idFormule, float prixFormule, int dureeEnMois, int nbreEntree, String label, String type) {
		super();
		this.idFormule = idFormule;
		this.prix = prixFormule;
		this.duree = dureeEnMois;
		this.nbreEntree = nbreEntree;
		this.label = label;
		this.type = type;
	}
	
	/**
	 * 
	 * @param prixFormule
	 * @param dureeEnSeconde
	 * @param nbreEntree
	 * @param label
	 * @param type
	 */
	public Formule(float prixFormule, int dureeEnMois, int nbreEntree, String label, String type) {
		super();
		this.prix = prixFormule;
		this.duree = dureeEnMois;
		this.nbreEntree = nbreEntree;
		this.label = label;
		this.type = type;
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

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getNbreEntree() {
		return nbreEntree;
	}

	public void setNbreEntree(int nbreEntree) {
		this.nbreEntree = nbreEntree;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	/**
	 * METHODES
	 */

}
