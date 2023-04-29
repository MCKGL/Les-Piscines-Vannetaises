package service;

public class InfoAdministrateur {
	
	private int idAdmin;
	private String mdp;
	private Employee idEmployee;
	
	public InfoAdministrateur(String mdp, Employee idEmployee) {
		super();
		this.mdp = mdp;
		this.idEmployee = idEmployee;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Employee getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Employee idEmployee) {
		this.idEmployee = idEmployee;
	}

	@Override
	public String toString() {
		return "InfoAdministrateur [idAdmin=" + idAdmin + ", mdp=" + mdp + ", idEmployee=" + idEmployee + "]";
	}
	
	
}

