package modele.metier;

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
	private int dureeValidite;
	private int nbreEntreeFormule;
	private String label;
	private String type;
	private boolean active;
	
	/**
	 * Constructeur global
	 * 
	 * @param idFormule
	 * @param type
	 * @param dureeValidite
	 * @param prixFormule
	 * @param nbreEntreeFormule
	 */
	public Formule(int idFormule, String type, String label, int dureeValidite, int prixFormule, int nbreEntreeFormule, boolean active) {
		super();
		this.idFormule = idFormule;
		this.type = type;
		this.label = label;
		this.dureeValidite = dureeValidite;
		this.prixFormule = prixFormule;
		this.nbreEntreeFormule = nbreEntreeFormule;
		this.active = active;
	}

	/**
	 * Constructeur sans l'ID
	 * 
	 * @param type
	 * @param dureeValidite
	 * @param prixFormule
	 * @param nbreEntreeFormule
	 */
	public Formule(String type, String label, int dureeValidite, int prixFormule, int nbreEntreeFormule) {
		super();
		this.type = type;
		this.dureeValidite = dureeValidite;
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

	public long getDureeValidite() {
		return dureeValidite;
	}

	public void setDureeValidite(int dureeValidite) {
		this.dureeValidite = dureeValidite;
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
	
	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Formule [idFormule=" + idFormule + ", prixFormule=" + prixFormule + ", dureeValidite=" + dureeValidite
				+ ", nbreEntreeFormule=" + nbreEntreeFormule + ", label=" + label + ", type=" + type + "]";
	}

	public String getNbEntreeToString() {
		return String.valueOf(nbreEntreeFormule);
	}
	
	public String getPrixToString() {
		return String.valueOf(prixFormule + "€");
	}
	
	public String getDurreeValiditeToString() {
		int dureeValiditeString = 0;
		String rep;
		if (dureeValidite >= 30) {
			dureeValiditeString = dureeValidite/30;
			rep = String.valueOf(dureeValiditeString+" mois");
		} else {
			dureeValiditeString = dureeValidite;
			rep = String.valueOf(dureeValiditeString+" jours");
		}
		return rep;
	}
	
	public String getActiveToString() {
		String rep = "Oui";
		if (active == false) {
			rep = "Non";
		}else {
			rep = "Oui";
		}
		return rep;
	}

}
