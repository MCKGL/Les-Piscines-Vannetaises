package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Seance {
	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private int idSeance;
	private LocalDateTime date;
	private int duree;
	private Cours idCours;
	private InfoProfesseur idProf;
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
	public Seance(int idSeance, LocalDateTime date, int duree, InfoProfesseur prof, Cours cours) {
		super();
		this.idSeance = idSeance;
		this.date = date;
		this.duree = duree;
		this.idProf = prof;
		this.idCours = cours;
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
	public Seance(LocalDateTime date, int duree, InfoProfesseur prof, Cours cours) {
		super();
		this.date = date;
		this.duree = duree;
		this.idProf = prof;
		this.idCours = cours;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	
	public int getIdSeance() {
		return idSeance;
	}
	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public Cours getIdCours() {
		return idCours;
	}
	public void setIdCours(Cours idCours) {
		this.idCours = idCours;
	}
	public InfoProfesseur getIdProf() {
		return idProf;
	}
	public void setIdProf(InfoProfesseur idProf) {
		this.idProf = idProf;
	}
	
	public String toStringDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy HH:mm");
	    return date.format(formatter);
	}
	
	public String toStringDuree() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    LocalDateTime endTime = date.plusMinutes(duree);
	    return endTime.format(formatter);
	}
	
	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", date=" + date + ", duree=" + duree + ", id_cours=" + idCours
				+ ", id_prof=" + idProf + "]";
	}

}
