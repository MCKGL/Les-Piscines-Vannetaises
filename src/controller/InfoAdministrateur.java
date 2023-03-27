package controller;

public class InfoAdministrateur {
	
// -----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * ATTRIBUTS
	 * 
	 * id administrateur 
	 * mot de passe 
	 * hashage
	 */
	private String mdpHasher;
	private String clefHashage;
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur avec ID
	 * @param idAdmin
	 * @param mdp
	 * @param hashage
	 */
	public InfoAdministrateur(String mdp, String hashage) {
		super();
		this.mdpHasher = mdp;
		this.clefHashage = hashage;
	}

	
// -----------------------------------------------------------------------------------------------------------------------
	


	public String getMdpHash() {
		return mdpHasher;
	}

	public void setMdp(String mdp) {
		this.mdpHasher = mdp;
	}

	public String getCleHashage() {
		return clefHashage;
	}

	public void setHashage(String hashage) {
		this.clefHashage = hashage;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Methodes
	 */
	public void modifierOffre() {
		
	}
	
// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Administrateur [ mdp=" + mdpHasher + ", hashage=" + clefHashage + "]";
	}

}

