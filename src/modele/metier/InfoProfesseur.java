package modele.metier;

public class InfoProfesseur {

	private int idProf;
	private String specialite;
	private Employe employe;
	
	public InfoProfesseur(int idProf, String specialite, Employe employe) {
	    super();
	    this.idProf = idProf;
	    this.specialite = specialite;
	    this.employe = employe;
	}
	
	public InfoProfesseur(String specialite, Employe employe) {
		super();
		this.specialite = specialite;
		this.employe = employe;
	}

	public int getIdProf() {
		return idProf;
	}

	public void setIdProf(int idProf) {
		this.idProf = idProf;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public Employe getIdEmployee() {
		return employe;
	}

	public void setIdEmployee(Employe idEmployee) {
		this.employe = idEmployee;
	}

	@Override
	public String toString() {
		return "InfoProfesseur [idProf=" + idProf + ", specialite=" + specialite + ", idEmployee=" + employe + "]";
	}

}
