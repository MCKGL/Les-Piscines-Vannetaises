package controller;


import java.time.LocalDate;
import java.time.LocalTime;


public class Seance {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private int idSeance;
	private LocalDate date;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private int prix;
	private int nbrePlace;
	private Cours cours;
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructeur global
	 * @param idSeance
	 * @param date
	 * @param heureDebut
	 * @param heureFin
	 * @param prix
	 * @param nbrePlace
	 * @param cours
	 */
	public Seance(int idSeance, LocalDate date, LocalTime heureDebut, LocalTime heureFin, int prix, int nbrePlace, Cours cours) {
		super();
		this.idSeance = idSeance;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.prix = prix;
		this.nbrePlace = nbrePlace;
		this.cours = cours;
	}
	/**
	 * Controleur sans ID
	 * @param date
	 * @param heureDebut
	 * @param heureFin
	 * @param prix
	 * @param nbrePlace
	 * @param cours
	 */
	public Seance(LocalDate date, LocalTime heureDebut, LocalTime heureFin, int prix, int nbrePlace, Cours cours) {
		super();
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.prix = prix;
		this.nbrePlace = nbrePlace;
		this.cours = cours;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Getter
	 * Setter
	 */
	public int getIdSeance() {
		return idSeance;
	}
	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}
	public LocalTime getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getNbrePlace() {
		return nbrePlace;
	}
	public void setNbrePlace(int nbrePlace) {
		this.nbrePlace = nbrePlace;
	}
	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin="+ heureFin + ", prix=" + prix + ", nbrePlace=" + nbrePlace + "]\n";
	}
	
}
