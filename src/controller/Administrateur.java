package controller;

public class Administrateur {
	
// -----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * ATTRIBUTS
	 * 
	 * id administrateur 
	 * mot de passe 
	 * hashage
	 */
	private int idAdmin;
	private String mdp;
	private String hashage;
	private boolean admin = false;
	
// -----------------------------------------------------------------------------------------------------------------------
	
	public Administrateur(int idAdmin, String mdp, String hashage) {
		super();
		this.idAdmin = idAdmin;
		this.mdp = mdp;
		this.hashage = hashage;
	}
	//Salut 

	public Administrateur(String mdp, String hashage) {
		super();
		this.mdp = mdp;
		this.hashage = hashage;
	}
	
// -----------------------------------------------------------------------------------------------------------------------
	
	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getHashage() {
		return hashage;
	}

	public void setHashage(String hashage) {
		this.hashage = hashage;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean estAdmin) {
		this.admin = admin;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Methodes
	 */
	
// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Administrateur [idAdmin=" + idAdmin + ", mdp=" + mdp + ", hashage=" + hashage + "]";
	}

}
