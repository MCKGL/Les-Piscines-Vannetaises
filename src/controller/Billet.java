package controller;

import java.time.LocalDateTime;
import java.util.UUID;

public class Billet {
	/**
	 * Attributs
	 * 
	 * ID Date d'achat Date de péremption Code Nombre d'entrée restante
	 */
	private int code;
	private LocalDateTime dateAchat;
	private LocalDateTime datePeremption;
	private int nbreEntreeRestante;
// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * TODO
	 */
	private Formule formule = null;
	private Seance seance = null;

//	private Bassin bassin;
//	private Zone zone;
//	private LigneEau ligneEau;

// -----------------------------------------------------------------------------------------------------------------------	
	public Billet(int code, LocalDateTime dateAchat, LocalDateTime datePeremption, int nbreEntreeRestante) {
		super();
		this.code = code;
		this.dateAchat = dateAchat;
		this.datePeremption = datePeremption;
		this.nbreEntreeRestante = nbreEntreeRestante;
	}

	public Billet(int code, LocalDateTime dateAchat, int nbreEntreeRestante) {
		super();
		this.code = code;
		this.dateAchat = dateAchat;
		this.nbreEntreeRestante = nbreEntreeRestante;
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Getter Setter
	 *
	 * @return
	 */

	public void setDateAchat(LocalDateTime dateAchat) {
		this.dateAchat = dateAchat;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public LocalDateTime getDateAchat() {
		return dateAchat;
	}

	public void setDateBillet(LocalDateTime dateBillet) {
		this.dateAchat = dateBillet;
	}

	public LocalDateTime getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(LocalDateTime datePeremption) {
		this.datePeremption = datePeremption;
	}

	public int getNbreEntreeRestante() {
		return nbreEntreeRestante;
	}

	public void setNbreEntreeRestante(int nbreEntreeRestante) {
		this.nbreEntreeRestante = nbreEntreeRestante;
	}
// -----------------------------------------------------------------------------------------------------------------------
	public UUID genererCode() {
		UUID code = UUID.randomUUID();
		return code;
	}
// -----------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Billet [code=" + code + ", dateAchat=" + dateAchat + ", datePeremption=" + datePeremption
				+ ", nbreEntreeRestante=" + nbreEntreeRestante + ", formule=" + formule + ", seance=" + seance + "]";
	}
}

