package controller;

import java.util.ArrayList;
import java.util.List;

public class InfoProfesseur {
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Attributs
	 */
	private String specialites;
	private List<Cours> listeCours = new ArrayList<Cours>();

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * TODO
	 * 
	 * Constructeur
	 * 
	 * @param idProfesseur
	 * @param specialites
	 */
	public InfoProfesseur(String specialites) {
		super();
		this.specialites = specialites;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Getter Setter
	 */

	public String getSpecialites() {
		return specialites;
	}

	public void setSpecialites(String specialites) {
		this.specialites = specialites;
	}

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Professeur [ specialite=" + specialites + "]";
	}

}
