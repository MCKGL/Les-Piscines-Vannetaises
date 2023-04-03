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
	
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur avec ID
	 * @param idAdmin
	 * @param mdp
	 * @param hashage
	 */
	public InfoAdministrateur(String mdp) {
		super();
		this.mdpHasher = mdp;
	}

	
// -----------------------------------------------------------------------------------------------------------------------
	


	public String getMdpHash() {
		return mdpHasher;
	}

	public void setMdp(String mdp) {
		this.mdpHasher = mdp;
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
		return "Administrateur [ mdp=" + mdpHasher + "]";
	}

}

