package model;

import controller.Adresse;

public class Piscine {

	private int idPiscine;
	private String nom;
	private int idAdresse;
	private Adresse adresse;
	
	
	
	

	public Piscine(int idPiscine, String nom, int idAdresse){
		super();
		this.idPiscine = idPiscine;
		this.nom = nom;
		this.idAdresse = idAdresse;
	
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
	
	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Piscine [idPiscine=" + idPiscine + ", nom=" + nom + ", idAdresse=" + idAdresse + "]";
	}
		
		
	
	
}
