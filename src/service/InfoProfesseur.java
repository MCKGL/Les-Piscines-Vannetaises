package service;

public class InfoProfesseur {

	private int idProf;
	private String specialite;
	private Employee idEmployee;
	
	public InfoProfesseur(String specialite, Employee idEmployee) {
		super();
		this.specialite = specialite;
		this.idEmployee = idEmployee;
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

	public Employee getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Employee idEmployee) {
		this.idEmployee = idEmployee;
	}

	@Override
	public String toString() {
		return "InfoProfesseur [idProf=" + idProf + ", specialite=" + specialite + ", idEmployee=" + idEmployee + "]";
	}

}
