package controller;

public class Professeur {
// -----------------------------------------------------------------------------------------------------------------------
	private int idProfesseur;
	private String specialites;
	private boolean professeur;
	
// -----------------------------------------------------------------------------------------------------------------------
	
	public Professeur(int idProfesseur, String specialites) {
		super();
		this.idProfesseur = idProfesseur;
		this.specialites = specialites;
	}
	
// -----------------------------------------------------------------------------------------------------------------------
	
	public boolean isProfesseur() {
		return professeur;
	}

	public void setProfesseur(boolean professeur) {
		this.professeur = professeur;
	}

	public int getIdProfesseur() {
		return idProfesseur;
	}

	public void setIdProfesseur(int idProfesseur) {
		this.idProfesseur = idProfesseur;
	}

	public String getSpecialites() {
		return specialites;
	}

	public void setSpecialites(String specialites) {
		this.specialites = specialites;
	}

	@Override
	public String toString() {
		return "Professeur [idProfesseur=" + idProfesseur + ", specialite=" + specialites + "]";
	}

}
