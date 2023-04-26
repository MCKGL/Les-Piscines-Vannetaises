package service;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Seance {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private int idSeance;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private String jourSemaine;
	private Cours id_cours;
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
		this.id_cours = cours;
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
		this.id_cours = cours;
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
		return id_cours;
	}
	public void setCours(Cours cours) {
		this.id_cours = cours;
	}
	
	public String getJourSemaine() {
		return jourSemaine;
	}
	public void setJourSemaine(String jourSemaine) {
		this.jourSemaine = jourSemaine;
	}
	
	public String toStringHeureDebut() {
	    return getHeureDebut().format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	public String toStringHeureFin() {
	    return getHeureFin().format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", jourSemaine=" + jourSemaine + " heureDebut=" + heureDebut + ", heureFin="+ heureFin + "]\n";
	}
	
}
