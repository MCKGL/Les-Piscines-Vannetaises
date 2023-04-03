package controller;


import java.time.LocalDate;
import java.time.LocalTime;


public class Seance {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private int idSeance;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private String jourSemaine;
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
	public Seance(int idSeance, String jourSemaine, LocalTime heureDebut, LocalTime heureFin, Cours cours) {
		super();
		this.idSeance = idSeance;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.cours = cours;
		this.jourSemaine = jourSemaine;
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
	public Seance(String jourSemaine, LocalTime heureDebut, LocalTime heureFin, Cours cours) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.cours = cours;
		this.jourSemaine = jourSemaine;
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

	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
	public String getJourSemaine() {
		return jourSemaine;
	}
	public void setJourSemaine(String jourSemaine) {
		this.jourSemaine = jourSemaine;
	}
	
	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", jourSemaine=" + jourSemaine + " heureDebut=" + heureDebut + ", heureFin="+ heureFin + "]\n";
	}
	
}
