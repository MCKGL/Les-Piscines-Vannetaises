package model;

import controller.Adresse;

public class Piscine {

	private int idPiscine;
	private String nom;
	private int idAdresse;
	
	
	
	// Attribut Adresse
		private Adresse adresse = null;
	

	public Piscine( String nom, Adresse adresse){
		super();
		//this.idPiscine = idPiscine;
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public Piscine( int id_piscine, String nom, int idAdresse) {
	super();
	this.idPiscine = id_piscine;
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
	

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Piscine [idPiscine=" + idPiscine + ", nom=" + nom + "]";
	}
		
		
	
	
}
