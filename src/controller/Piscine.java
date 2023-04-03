package controller;

public class Piscine {

	private int idPiscine;
	private String nom;
	private Adresse adresse;
	
	
	public Piscine(int idPiscine, String nom, Adresse adresse) {
		super();
		this.idPiscine = idPiscine;
		this.nom = nom;
		this.adresse = adresse;
	}


	public int getIdPiscine() {
		return idPiscine;
	}


	public void setIdPiscine(int idPiscine) {
		this.idPiscine = idPiscine;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	@Override
	public String toString() {
		return "Piscine [idPiscine=" + idPiscine + ", nom=" + nom + ", adresse=" + adresse + "]";
	}
	
	
	
}
