package service;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Employee {
// -----------------------------------------------------------------------------------------------------------------------

	private int idEmployee;
	private String nom;
	private String prenom;
	private String mail;
	private Date dateNaissance;
	private Adresse idAdresse;

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
	public Employee(int idEmployee, String nom, String prenom, String mail, Date dateNaissance, Adresse adresse) {
		super();
		this.idEmployee = idEmployee;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.dateNaissance = dateNaissance;
		this.idAdresse = adresse;
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
	public Employee(String nom, String prenom, String mail, Date dateNaissance, Adresse adresse) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.mail = mail;
	this.dateNaissance = dateNaissance;
	this.idAdresse = adresse;
}
// -----------------------------------------------------------------------------------------------------------------------

	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Adresse getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(Adresse idAdresse) {
		this.idAdresse = idAdresse;
	}
	
// -----------------------------------------------------------------------------------------------------------------------

	public String toStringDateNaissance() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    return sdf.format(dateNaissance);
	}
	
// -----------------------------------------------------------------------------------------------------------------------	

	@Override
	public String toString() {
		return "Employee [idEmployee=" + idEmployee + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail
				+ ", dateNaissance=" + dateNaissance + ", idAdresse=" + idAdresse + "]";
	}
	
	

}

