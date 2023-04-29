package service;

import java.time.LocalDateTime;
import java.util.UUID;

public class Billet {
	/**
	 * Attributs
	 * 
	 * ID Date d'achat Date de péremption Code Nombre d'entrée restante
	 */
	private int code;
	private int nbreEntreeRestante;
	private Formule formule;
	private Piscine piscine;

// -----------------------------------------------------------------------------------------------------------------------	
	public Billet(int code, int nbreEntreeRestante, Formule formule, Piscine piscine) {
		super();
		this.code = code;
		this.nbreEntreeRestante = nbreEntreeRestante;
		this.formule = formule;
		this.piscine = piscine;
	}

// -----------------------------------------------------------------------------------------------------------------------
	/**
	 * Getter Setter
	 *
	 * @return
	 */


	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public Formule getFormule() {
		return formule;
	}

	public void setFormule(Formule formule) {
		this.formule = formule;
	}

	public Piscine getPiscine() {
		return piscine;
	}

	public void setPiscine(Piscine piscine) {
		this.piscine = piscine;
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
		return "Billet [code=" + code + ", nbreEntreeRestante=" + nbreEntreeRestante + ", formule=" + formule + ", piscine=" + piscine + "]";
	}
}

