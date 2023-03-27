package controller;

import java.time.LocalDate;

public class Employee {
// -----------------------------------------------------------------------------------------------------------------------

	/**
	 * ATTRIBUT employee
	 * 
	 * id 
	 * employée 
	 * nom 
	 * prénom 
	 * email 
	 * date de naissance 
	 * idAdresse
	 * 
	 * ATTRIBUT Adresse
	 * 
	 * id adresse
	 * ville
	 * Code postal
	 * rue
	 * numéro
	 * 
	 * ATTRIBUTS administrateur
	 * 
	 * id administrateur 
	 * mot de passe 
	 * hashage
	 *  
	 * ATTRIBUT professeur
	 * 
	 * id professeur 
	 * specialite
	 */
	private int idEmployee;
	private String nom;
	private String prenom;
	private String nomMembre;
	private String email;
	private LocalDate dateNaissance;
	private boolean actif = false;

	// Attribut Adresse
	private Adresse adresse = null;
	// Attribut administrateur
	private InfoAdministrateur infoAdministrateur = null;
	//Attribut professeur
	private InfoProfesseur infoProfesseur = null;
	
// -----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Constructeur Read
	 * 
	 * @param idEmployee
	 * @param nom
	 * @param prenom
	 * @param nomMmembre
	 * @param email
	 * @param dateNaissance
	 */
	public Employee(int idEmployee, String nom, String prenom, String nomMembre, String email, LocalDate dateNaissance, Adresse adresse) {
		super();
		this.idEmployee = idEmployee;
		this.nom = nom;
		this.prenom = prenom;
		this.nomMembre = nomMembre;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
	}
	/**
	 * Constructeur Create
	 * 
	 * @param nom
	 * @param prenom
	 * @param nomMembre
	 * @param email
	 * @param dateNaissance
	 * @param adresse
	 * @param administrateur
	 * @param professeur
	 */
	public Employee(String nom, String prenom, String nomMembre, String email, LocalDate dateNaissance,
		Adresse adresse, InfoAdministrateur administrateur, InfoProfesseur professeur) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.nomMembre = nomMembre;
	this.email = email;
	this.dateNaissance = dateNaissance;
	this.adresse = adresse;
	this.infoAdministrateur = administrateur;
	this.infoProfesseur = professeur;
}
	// -----------------------------------------------------------------------------------------------------------------------
	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public LocalDate getDateNaissanceSQL() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNomMembre() {
		return nomMembre;
	}

	public void setNomMembre(String nomMembre) {
		this.nomMembre = nomMembre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public InfoProfesseur getInfoProfesseur() {
		return infoProfesseur;
	}
	public void setInfoProfesseur(InfoProfesseur infoProfesseur) {
		this.infoProfesseur = infoProfesseur;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public InfoAdministrateur getInfoAdministrateur() {
		return infoAdministrateur;
	}
	
	public void setInfoAdministrateur(InfoAdministrateur infoAdministrateur) {
		this.infoAdministrateur = infoAdministrateur;
	}
	public boolean isAdmin() {
		return this.infoAdministrateur != null;
	}
	
	public boolean isProf() {
		return this.infoProfesseur != null;
	}
	
	public boolean hasAdresse() {
		return this.adresse != null;
	}
// -----------------------------------------------------------------------------------------------------------------------

	
// -----------------------------------------------------------------------------------------------------------------------


	@Override
	public String toString() {
		return "Employee [idEmployee=" + idEmployee + ", nom=" + nom + ", prenom=" + prenom + ", nomMembre=" + nomMembre
				+ ", email=" + email + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", administrateur=" + infoAdministrateur + ", professeur=" + infoProfesseur
				+ "]";
	}

}
