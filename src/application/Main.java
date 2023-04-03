package application;

import java.time.LocalDate;

import controller.Adresse;
import controller.Employee;
import controller.InfoAdministrateur;
import controller.InfoProfesseur;
import model.EmployeeDAO;
import model.Piscine;
import model.PiscineDAO;

public class Main {


	public static void main(String[] args) {
		
		
//------------------------------------------------read un employé ---ok-au 03/04-------------------------------------------
//		Employee employee = EmployeeDAO.getInstance().read(27);
//		System.out.println(employee);
		
		//---------------------------creation d'un employee----ok au 03/04-----------------------------------------------------------
//		 String nom = "pocholle300";
//		 String prenom = "erwan300";
//		 String nomMembre = "epo300";
//		 String email = "epo300@gmail.com";
//		 LocalDate dateN = LocalDate.parse("1975-12-11");
//		 String ville = "Lauzach300";
//		 int codePostal = 56000; 
//		 String rue = "Michel300";
//		 int numero = 300;
//		 InfoAdministrateur admin = null; // new InfoAdministrateur("essaimdp30", "essaihash30");
//		 InfoProfesseur prof = null; // new InfoProfesseur("appr30");
//		 Adresse adresse = new Adresse(ville, codePostal, rue, numero);
//		 Employee empl = new Employee(nom, prenom, nomMembre, email, dateN, adresse, admin, prof);
//		 EmployeeDAO.getInstance().create(empl);
//		 System.out.println(empl);
		
		
//-------------------------maj d'un employé---ok-03/04-------------------------------	
//		 Employee employee = EmployeeDAO.getInstance().read(28);
//		 String ville = "Lauzach400";
//		 int codePostal = 56200; 
//		 String rue = "erwan";
//		 int numero = 2;
//		 int id_adresse = employee.getAdresse().getIdAdresse();
//		 Adresse adr = new Adresse(id_adresse, ville, codePostal, rue, numero);
//		 employee.setAdresse(adr);
//		 EmployeeDAO.getInstance().update(employee);
//		 System.out.println(employee);	

		
		
//------------------maj d'un employee en admin-----ao au 03/04----------------------------------------------------		
		
//		 Employee employee = EmployeeDAO.getInstance().read(27);
//		 employee.setInfoAdministrateur(new InfoAdministrateur("essai400", "hashage300"));
//		 EmployeeDAO.getInstance().update(employee);
//		 System.out.println(employee);	
		
//------------------maj d'un employee en prof-----ok au 03/04----------------------------------------------------		
		
//		 Employee employee = EmployeeDAO.getInstance().read(28);
//		 employee.setInfoProfesseur(new InfoProfesseur("prout_dansleau"));
//		 EmployeeDAO.getInstance().update(employee);
//		 System.out.println(employee);			 
		
//------------------supprimer un employee--ok au 03/04------------------------------------------		
		
//		Employee employee = EmployeeDAO.getInstance().read(27);
//		EmployeeDAO.getInstance().delete(employee);
//		System.out.println(employee);	
		
//--------------------------------------creer un billet---ok au 03/04-------------------------------------------		
		 
//		 LocalDateTime direct = java.time.LocalDateTime.now();
//		 LocalDateTime date_achat = direct;
//		 LocalDateTime directFin = date_achat.plusDays(20);
//		 LocalDateTime datePeremption = directFin;
//		 int nbreRestante = 15;
//		 Billet billet = new Billet(300,date_achat, datePeremption, nbreRestante);
//		 BilletDAO.getInstance().create(billet);
//		 System.out.println(billet);
//		 Connexion.fermer();

//--------------------------------------------creation d'une piscine---------------------------------------
	
		 
		 String ville = "surzur300";
		 int codePostal = 56300; 
		 String rue = "surzur300";
		 int numero = 300;
		 String nom = "piscine300";
		 Adresse adresse = new Adresse(ville, codePostal, rue, numero);
		 Piscine piscine = new Piscine( nom, adresse);
		 PiscineDAO.getInstance().create(piscine);
		 System.out.println(piscine);
		
		
	}
}














