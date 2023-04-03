package application;
	
import controller.Formule;
import javafx.application.Application;
import javafx.stage.Stage;
import model.FormuleDAO;


public class Main extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	//test passage page à l'autre
//	@Override
//	public void start(Stage stage) {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
//			Scene scene = new Scene(root);
//			stage.setScene(scene);
//			stage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) {

//		launch(args);
//		String ville = "Vannes";
//		int codePostal = 56100;
//		String rue = "pouetpouet";
//		int numero = 68;
//		
//		Adresse adresse = new Adresse(ville, codePostal, rue, numero);
//		System.out.println(adresse);
		
//		 Employee employee = EmployeeDAO.getInstanceEmployeeDAO().read(1);
//		 System.out.println(employee);
//		 Connexion.fermer();
//		 Adresse adresse = AdresseDAO.getInstanceAdresseDAO().read(1);
//		 System.out.println(adresse);
//		 Connexion.fermer();
		
// ----------------------------------------------------------------------------------------------------------------------
//Test cours DAO
//-----------------------------------------------------------------------------------------------------------------------
//		String nom = "AquaGym";
//		String description = "Venez profitez de notre nouveau cours d'aquagym ";
//		int nbreSeance = 4;
//		String trancheAge = "16 - 70";
//		String type = "Remise en forme";
//		int nbrePersonne = 12;
//		Cours cours = new Cours(nom, description, nbreSeance, trancheAge, type, nbrePersonne);
//		
//		System.out.println(CoursDAO.getInstance().create(cours));
//		
//		nom = "AquaBike";
//		cours.setNom(nom);
//		description = "j'ai été modifiée";
//		cours.setDescription(description);
//		nbreSeance = 5;
//		cours.setNbreSeance(nbreSeance);
//		trancheAge = "40-50";
//		cours.setTrancheAge(trancheAge);
//		type = "Musculation";
//		cours.setType(type);
//		nbrePersonne = 10;
//		cours.setNbrePersonne(nbrePersonne);
		
//		System.out.println(CoursDAO.getInstance().update(cours));
//		System.out.println(CoursDAO.getInstance().read(cours.getIdCours()));
		
//		
//		Cours cours1 = CoursDAO.getInstance().read(20);
//		System.out.println(cours1);
//		System.out.println(CoursDAO.getInstance().delete(cours1));
		
// ----------------------------------------------------------------------------------------------------------------------
//Test table d'association cous prof
//-----------------------------------------------------------------------------------------------------------------------
//		CoursDAO coursDAO = CoursDAO.getInstance();
//		System.out.println(coursDAO.createAssoCoursProf(19, 46));
//		System.out.println(coursDAO.deleteAssoCoursProf(19, 46));
//		System.out.println(coursDAO.readAllByIdCours(19));
//		System.out.println("---");
//		System.out.println(coursDAO.readAllByIdProf(46));
// ----------------------------------------------------------------------------------------------------------------------
//Test table seance
//-----------------------------------------------------------------------------------------------------------------------
//		SeanceDAO seanceDAO = SeanceDAO.getInstance();
		/**
		 * Create
		 */
//		LocalDate date = LocalDate.of(2023, 3, 12);
//		LocalTime heureDebut = LocalTime.of(9, 00);
//		LocalTime heureFin = LocalTime.of(11, 00);
//		int prix = 15;
//		int nbrePlace = 6;
//		int idCours = 19;
//		Seance seance = new Seance(date, heureDebut, heureFin, prix, nbrePlace, idCours);
//		System.out.println(seanceDAO.create(seance));
		
		/**
		 * Delete
		 */
//		Seance seance = seanceDAO.read(1);
//		System.out.println(seanceDAO.delete(seance));
		
		/**
		 * Update
		 */
//		Seance seance = seanceDAO.read(2);
//		System.out.println(seance);
//		seance.setDate(LocalDate.now());
//		seance.setPrix(999);
//		System.out.println(seanceDAO.update(seance));
		
// ----------------------------------------------------------------------------------------------------------------------
//Test table Formule
//-----------------------------------------------------------------------------------------------------------------------
		FormuleDAO formuleDAO = FormuleDAO.getInstance();
		/**
		 * Create
		 */
		float prix = 11.99f;
		int duree = 500;
		int nbreEntree = 10;
		String label = "test label";
		String type = "test type";
		
		Formule formuleDO = new Formule(prix, duree, nbreEntree, label, type);
		
		System.out.println(FormuleDAO.getInstance().create(formuleDO));
		
//		UUID test = UUID.randomUUID();
//		System.out.println(test);
	}

@Override
public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub
	
}
}












//
//
//
//package application;
//
//import java.util.UUID;
//
//public class Main {
////	@Override
////	public void start(Stage primaryStage) {
////		try {
////			Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
////			Scene scene = new Scene(root,400,400);
////			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
////			primaryStage.setScene(scene);
////			primaryStage.show();
////		} catch(Exception e) {
////			e.printStackTrace();
////		}
////	}
//
//	public static void main(String[] args) {
////		launch(args);
//		/**
//		 * Tests pour employée DAO
//		 */
//
////		String nom = "Nico";
////		String prenom = "Ngun";
////		String nomMembre = "NGY";
////		String email = "tetyyy@gmail.com";
////		LocalDate dateN = LocalDate.parse("1989-10-24");
////
////		String ville = "Lorrendst";
////		int codePostal = 56050;
////		String rue = "Motroiul";
////		int numero = 24;
////
////		String mdp = "14";
////		String hash = "Psfo";
////		String spec = "natation";
////
////		InfoAdministrateur admin = null;
////		InfoProfesseur prof = null;
////		
////		InfoProfesseur prof = new InfoProfesseur(spec);
////
////		Adresse adr = new Adresse(ville, codePostal, rue, numero);
////		Employee empl = new Employee(nom, prenom, nomMembre, email, dateN, adr, admin, prof);
////
////		AdresseDAO.getInstance().create(adr);
////		int num = AdresseDAO.getInstance().readIdAdresse(adr);
////
////		System.out.println(EmployeeDAO.getInstance().create(empl));
////
////		empl.getAdresse().setCodePostal(90000);
////
////		InfoAdministrateur admin1 = new InfoAdministrateur(mdp, hash);
////		empl.setInfoAdministrateur(admin1);
////		empl.setNom(nomMembre);
////
////		System.out.println(EmployeeDAO.getInstance().update(empl));
////
////		System.out.println(empl);
//
////		Employee employee = EmployeeDAO.getInstance().read(35);
////		employee.setPrenom("Erwan");
////		System.out.println(employee);
////		EmployeeDAO.getInstance().update(employee);
//
//		// -----------------------------------------------------------------------------------------------------------------------
//
//		/**
//		 * Test cours DAO
//		 */
////		String nom = "AquaGym";
////		String description = "Venez profitez de notre nouveau cours d'aquagym ";
////		int nbreSeance = 4;
////		String trancheAge = "16 - 70";
////		String type = "Remise en forme";
////		int nbrePersonne = 12;
////		
////		Cours cours = new Cours(nom, description, nbreSeance, trancheAge, type, nbrePersonne);
////		
////		System.out.println(CoursDAO.getInstance().create(cours));
////		
////		nom = "AquaBike";
////		cours.setNom(nom);
////		description = "j'ai été modifiée";
////		cours.setDescription(description);
////		nbreSeance = 5;
////		cours.setNbreSeance(nbreSeance);
////		trancheAge = "40-50";
////		cours.setTrancheAge(trancheAge);
////		type = "Musculation";
////		cours.setType(type);
////		nbrePersonne = 10;
////		cours.setNbrePersonne(nbrePersonne);
////		
////		System.out.println(CoursDAO.getInstance().update(cours));
////		System.out.println(CoursDAO.getInstance().read(cours.getIdCours()));
////		System.out.println(CoursDAO.getInstance().delete(cours));
////		
////		Cours cours = CoursDAO.getInstance().read(3);
////		System.out.println(cours);
////		System.out.println(CoursDAO.getInstance().readActif(cours));
////		
////
////		int test = CoursDAO.getInstance().readIdCours(cours);
////		System.out.println(test);
//		/**
//		 * Test seance DAO
//		 */
////		LocalDate date = LocalDate.of(2023, 01, 16);
////		LocalTime heureDebut = LocalTime.of(9, 0);
////		LocalTime heureFin = LocalTime.of(11, 0);
////		int prix = 15;
////		int nbrePlace = 10;
////		Cours cours;
//
//		// -----------------------------------------------------------------------------------------------------------------------
//
//		/**
//		 * Test seance DAO
//		 */
////		LocalDate date = LocalDate.of(2023, 3, 12);
////		LocalTime heureDebut = LocalTime.of(9, 00);
////		LocalTime heureFin = LocalTime.of(11, 00);
////		int prix = 15;
////		int nbrePlace = 6;
////		Cours coursSeance = CoursDAO.getInstance().read(5);
//
////		Seance seance = new Seance(date, heureDebut, heureFin, prix, nbrePlace, coursSeance);
////		System.out.println(SeanceDAO.getInstance().create(seance));
//
////		int idSeance = seance.getIdSeance();
////		seance = SeanceDAO.getInstance().read(idSeance);
////		System.out.println(seance);
//
////		Seance seance = SeanceDAO.getInstance().read(1);
//
//		// modification de variable
////		date = LocalDate.of(2022, 2, 11);
////		heureDebut = LocalTime.of(8, 30);
////		heureFin = LocalTime.of(10, 0);
////		prix = 20;
////		nbrePlace = 1;
//
//		// Modification de l'objet seance
////		seance.setDate(date);
////		seance.setHeureDebut(heureDebut);
////		seance.setHeureFin(heureFin);
////		seance.setPrix(prix);
////		seance.setNbrePlace(nbrePlace);
//
//		// Update dans la BD
////		SeanceDAO.getInstance().update(seance);
//
////		System.out.println(SeanceDAO.getInstance().delete(seance));
//
//		
//		/**
//		 * Formule DAO
//		 */
//
////		String type = "solo";
////		long dureeEnSeconde = 1546546;
////		int prixFormule = 50;
////		int nbreEntreeFormule = 10;
////		
////		Formule formule = new Formule(type, dureeEnSeconde, prixFormule, nbreEntreeFormule);
////		System.out.println(FormuleDAO.getInstance().create(formule));
////		
////		formule.setType("duo");
////		formule.setDureeEnSeconde(2156455);
////		formule.setPrixFormule(75);
////		formule.setNbreEntreeFormule(5);
////		
////		System.out.println(FormuleDAO.getInstance().update(formule));
//		
////		Formule formule = FormuleDAO.getInstance().read(1);
////		FormuleDAO.getInstance().delete(formule);
//		
//		/**
//		 * Billet DAO
//		 */
////		
////		LocalDateTime dateAchat = LocalDateTime.now();
////		LocalDateTime datePeremption = dateAchat.plusDays(30);
////		int nbreEntreeRestante = 1;
////		BilletDAO.getInstanceBilletDAO()
//		
////		Connexion.fermer();
//		
//		UUID test = UUID.randomUUID();
//		System.out.println(test);
//		
//		BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.UPCA);
//		generator.setCodeText("123ABC");
//		generator.save("output.png"); 
//
//	}
//}



