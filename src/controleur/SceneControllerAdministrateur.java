package controleur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.bd.CoursDAO;
import modele.bd.EmployeDAO;
import modele.bd.FormuleDAO;
import modele.bd.ProfesseurDAO;
import modele.bd.SeanceDAO;
import modele.metier.Cours;
import modele.metier.Employe;
import modele.metier.Formule;
import modele.metier.InfoProfesseur;
import modele.metier.Seance;

public class SceneControllerAdministrateur extends SceneController {

	private ObservableList<InfoProfesseur> empData = FXCollections.observableArrayList();
	private ObservableList<Seance> seanceData = FXCollections.observableArrayList();
	private ObservableList<Formule> formuleData = FXCollections.observableArrayList();
	private ObservableList<Cours> coursData = FXCollections.observableArrayList();

	@FXML
	private TextField textFieldAdmin, textFieldPrixForm, textFieldDureeValForm, textFieldNbEntreeForm, textFieldLabelForm, textFieldTypeCoursForm, textFieldNomCours, textFieldDescCours, textFieldHeureSeance, textFieldMinSeance, textFieldDureeSeance;
	@FXML
	private PasswordField passwordFieldAdmin;
	@FXML
	private Pane paneConnexion, paneAlert, paneMajSeance, paneMajCours, paneMajFormule;
	@FXML
	private TableView<InfoProfesseur> tableProf;
	@FXML
	private Label labelDateSeance, labelHeureSeance, labelDureeSeance, labelProfSeance, labelCoursSeance;
	@FXML
	private TableColumn<InfoProfesseur, String> nom, prenom, mail, dateNaissance;
	@FXML
	private TableView<Seance> tableSeance;
	@FXML
	private TableColumn<Seance, String> jour, fin, typeCours, prof;
	@FXML
	private TableView<Formule> tableFormule;
	@FXML
	private TableColumn<Formule, String> type, prix, dureeVal, nbEntree, label, active;
	@FXML
	private Button boutonAffEmploye, boutonAffSeance, boutonAffFormule, boutonAffCours, boutonSupprimer, boutonConfimSupp, boutonInfirmSupp, boutonAjouter, boutonCreerFormule, boutonCreerSeance, boutonCreerCours, boutonSuppCours, boutonUpdCours, boutonSuppSeance, boutonUpdSeance, boutonSuppFormule, boutonUpdFormule;
	@FXML
	private TableView<Cours> tableCours;
	@FXML
	private TableColumn<Cours, String> nomCours, description;
	@FXML
	private DatePicker datePickerSeance;
	@FXML
	private ChoiceBox<String> choiceBoxProf, choiceBoxCours;

	public void connexion(ActionEvent event) {
		try { 
			int mdp = Integer.parseInt(passwordFieldAdmin.getText());
			String nom = textFieldAdmin.getText();
			// TODO créer méthode loggin à appeller ici

			if(mdp == 123456 && nom.equals("Maurice")) {
				paneConnexion.setVisible(true);
				afficherTableProf();
			}else {
				System.out.println("Mot de passe ou nom utilisateur incorrect");
			}
		}
		catch(Exception e) {
			System.out.println("Le code saisi est incorrect");
			e.printStackTrace();
		}
	}

	public void deconnexion(ActionEvent event) {
		paneConnexion.setVisible(false);
	}


	public ObservableList<InfoProfesseur> getEmpData() {
		return empData;
	}

	public ObservableList<Seance> getSeanceData() {
		return seanceData;
	}

	public ObservableList<Formule> getFormuleData() {
		return formuleData;
	}

	public ObservableList<Cours> getCoursData() {
		return coursData;
	}

	// paramétre la table Prof en injectant les données dedans
	private void afficherTableProf() {
		tableProf.setVisible(true);
		tableSeance.setVisible(false);
		tableFormule.setVisible(false);
		tableCours.setVisible(false);
		nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().getNom()));
		prenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().getPrenom()));
		mail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().getMail()));
		dateNaissance.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().toStringDateNaissance()));

		ProfesseurDAO professeurDAO = ProfesseurDAO.getInstance();
		List<InfoProfesseur> profs = professeurDAO.readAll();
		empData.clear();
		empData.addAll(profs);
		tableProf.setItems(empData);
	}

	private void afficherTableSeance() {
		tableProf.setVisible(false);
		tableSeance.setVisible(true);
		tableFormule.setVisible(false);
		tableCours.setVisible(false);
		jour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
		fin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDuree()));
		typeCours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCours().getNom()));
		prof.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProf().getIdEmployee().getPrenom()));

		SeanceDAO seanceDAO = SeanceDAO.getInstance();
		List<Seance> seances = seanceDAO.readAll();
		seanceData.clear();
		seanceData.addAll(seances);
		tableSeance.setItems(seanceData);
	}

	private void afficherTableFormule() {
		tableProf.setVisible(false);
		tableSeance.setVisible(false);
		tableFormule.setVisible(true);
		tableCours.setVisible(false);
		type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
		prix.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrixToString()));
		dureeVal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDurreeValiditeToString()));
		nbEntree.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNbEntreeToString()));
		label.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLabel()));
		active.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActiveToString()));

		FormuleDAO formuleDAO = FormuleDAO.getInstance();
		List<Formule> formule = formuleDAO.readAll();
		formuleData.clear();
		formuleData.addAll(formule);
		tableFormule.setItems(formuleData);
	}

	private void afficherTableCours() {
		tableProf.setVisible(false);
		tableSeance.setVisible(false);
		tableFormule.setVisible(false);
		tableCours.setVisible(true);
		nomCours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
		description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

		CoursDAO coursDAO = CoursDAO.getInstance();
		List<Cours> cours = coursDAO.readAll();
		coursData.clear();
		coursData.addAll(cours);
		tableCours.setItems(coursData);
	}

	// méthode pour afficher les données en fonction du bouton cliqué
	public void afficherData(ActionEvent event) {
		if(event.getSource() == boutonAffEmploye) {
			afficherTableProf();
		}
		if(event.getSource() == boutonAffSeance) {
			afficherTableSeance();
		}
		if(event.getSource() == boutonAffFormule) {
			afficherTableFormule();
		}
		if(event.getSource() == boutonAffCours) {
			afficherTableCours();
		}
	}

	// Confirmation de la suppression.
	public void confirmSupp(ActionEvent event) {
		Formule formule = tableFormule.getSelectionModel().getSelectedItem();
		if(event.getSource() == boutonConfimSupp) {
			/*
			 * Si la formule type "cours" est supprimé, tous les cours aussi et les séances aussi.
			 * On commence par supprimer les seances, puis les cours, puis les formules. 
			 * Sans quoi on aura un soucis avec les clefs étrangères
			 */
			SeanceDAO seanceDAO = SeanceDAO.getInstance();
			List<Seance> seanceList = seanceDAO.readAll();
			for (Seance seance : seanceList) {
				seanceDAO.delete(seance);
				seanceData.remove(seance);
			}

			CoursDAO coursDAO = CoursDAO.getInstance();
			List<Cours> coursList = coursDAO.readAll();
			for (Cours cours : coursList) {
				coursDAO.delete(cours);
				coursData.remove(cours);
			}

			// On va avoir un problème ici : les billets contiennent une formule, on ne peut pas 
			// supprimer les formules sans invalider les billets. Création donc d'un champ active
			// dans la bd, le champ passera à false, désactivant la formule.
			formule.setActive(false);
			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			formuleDAO.update(formule);
			// Mettre à jour le tableau
			int index = formuleData.indexOf(formule);
			if (index != -1) {
				formuleData.set(index, formule);
			}

		}else {
			paneMajFormule.setVisible(true);
		}
		paneAlert.setVisible(false);
	}

	/* Lorsque l'admin clique sur supprimer, la ligne concernée est supprimé de la bd.
	 * Dans le cas où il supprime les formules de types Cours, il devra confirmer son choix
	 * puisque l'action supprimera aussi les cours possédant l'id de cette formule et les séances associées aux cours
	 * voir confirmSupp
	 */
	public void supprimerSelection(ActionEvent event) {
		Seance seance = tableSeance.getSelectionModel().getSelectedItem();
		Formule formule = tableFormule.getSelectionModel().getSelectedItem();
		Cours cours = tableCours.getSelectionModel().getSelectedItem();
		if (formule != null && event.getSource() == boutonSuppFormule) {
			if(formule.getType().equals("Cours")) {
				// les formules cours ont des dépendance, on va donc sécuriser l'action en propose un pane de validation de la demande
				paneAlert.setVisible(true);
			}else {
				// si la formule n'est pas un cours, on la désactive simplement (on ne surppime pas pour ne pas invalider les billets)
				formule.setActive(false);
				FormuleDAO formuleDAO = FormuleDAO.getInstance();
				formuleDAO.update(formule);
				// Mettre à jour le tableau
				int index = formuleData.indexOf(formule);
				if (index != -1) {
					formuleData.set(index, formule);
				}
			}
		// supprimer un cours
		}else if (cours != null && event.getSource() == boutonSuppCours) {
			CoursDAO coursDAO = CoursDAO.getInstance();

			// Il faut aussi supprimer les seances associées au cours, pour ça, on va aller chercher
			// l'Id du cours sélectionné et s'en servir pour supprimer les séances dont l'id cours correspondant
			int idCours = cours.getIdCours();

			SeanceDAO seanceDAO = SeanceDAO.getInstance();
			List<Seance> seanceList = seanceDAO.readAllByCoursId(idCours);
			for (Seance uneSeance : seanceList) {
				seanceDAO.delete(uneSeance);
				seanceData.remove(uneSeance);
			}

			// On supprime les cours APRES les seances, sans quoi il y aura un soucis avec l'id cours FK
			coursDAO.delete(cours);
			coursData.remove(cours);
		// supprimer seance
		}else if (seance != null && event.getSource() == boutonSuppSeance) {
			SeanceDAO seanceDAO = SeanceDAO.getInstance();
			seanceDAO.delete(seance);
			seanceData.remove(seance);
		}
		paneMajFormule.setVisible(false);
		paneMajSeance.setVisible(false);
		paneMajCours.setVisible(false);
	}

	// permet de sélectionner une tupple
	public void selectionLigne(MouseEvent event) {
		// Récupérer la ligne sélectionnée
		Seance seance = tableSeance.getSelectionModel().getSelectedItem();
		Formule formule = tableFormule.getSelectionModel().getSelectedItem();
		Cours cours = tableCours.getSelectionModel().getSelectedItem();
		if (formule != null && tableFormule.isVisible()) {
			paneMajFormule.setVisible(true);
			paneMajSeance.setVisible(false);
			paneMajCours.setVisible(false);
			boutonCreerFormule.setVisible(false);
			boutonSuppFormule.setVisible(true);
			boutonUpdFormule.setVisible(true);
			
			textFieldPrixForm.setText(formule.getPrixToString());
			textFieldDureeValForm.setText(formule.getDurreeValiditeToString());
			textFieldNbEntreeForm.setText(formule.getNbEntreeToString());
			textFieldLabelForm.setText(formule.getLabel());
			textFieldTypeCoursForm.setText(formule.getType());
			
		}else if (cours != null && tableCours.isVisible()) {
			paneMajFormule.setVisible(false);
			paneMajSeance.setVisible(false);
			paneMajCours.setVisible(true);
			boutonCreerCours.setVisible(false);
			boutonSuppCours.setVisible(true);
			boutonUpdCours.setVisible(true);
			
			textFieldNomCours.setText(cours.getNom());
			textFieldDescCours.setText(cours.getDescription());
			
		}else if (seance != null && tableSeance.isVisible()) {
			paneMajFormule.setVisible(false);
			paneMajSeance.setVisible(true);
			paneMajCours.setVisible(false);
			boutonCreerSeance.setVisible(false);
			boutonSuppSeance.setVisible(true);
			boutonUpdSeance.setVisible(true);
			
			labelDateSeance.setText(seance.toStringDate());
			labelDureeSeance.setText(seance.toStringDuree());
			labelProfSeance.setText(seance.getIdProf().getIdEmployee().getPrenom());
			labelCoursSeance.setText(seance.getIdCours().getNom());
		}
	}

	// affichage de l'interface de création de formules / cours / seances
	public void ajouterDonnee(ActionEvent event) {
		if(tableSeance.isVisible()) {
			paneMajSeance.setVisible(true);
			paneMajFormule.setVisible(false);
			paneMajCours.setVisible(false);
			boutonCreerSeance.setVisible(true);
			boutonSuppSeance.setVisible(false);
			boutonUpdSeance.setVisible(false);
			

			//Récupérer les valeurs pour le choiceBox Cours
			List<Cours> listeCours = CoursDAO.getInstance().readAll();
			choiceBoxCours.setValue(listeCours.get(0).getNom());
			for (Cours cours : listeCours) {
				choiceBoxCours.getItems().add(cours.getNom());
			}
			
			//Récupérer les valeurs pour le choiceBox Prof
			List<Employe> listeEmploye = EmployeDAO.getInstance().readAll();
			choiceBoxProf.setValue(listeEmploye.get(0).getPrenom());
			for (Employe employes : listeEmploye) {
				choiceBoxProf.getItems().add(employes.getPrenom());
			}
			
		}
		if(tableFormule.isVisible()) {
			paneMajSeance.setVisible(false);
			paneMajFormule.setVisible(true);
			paneMajCours.setVisible(false);
			boutonCreerFormule.setVisible(true);
			boutonSuppFormule.setVisible(false);
			boutonUpdFormule.setVisible(false);
		}
		if(tableCours.isVisible()) {
			paneMajSeance.setVisible(false);
			paneMajFormule.setVisible(false);
			paneMajCours.setVisible(true);
			boutonCreerCours.setVisible(true);
			boutonSuppCours.setVisible(false);
			boutonUpdCours.setVisible(false);
		}
	}

	// Créer une nouvelle formule / cours / seance (en fonction du bouton cliqué)
	public void creerNouvelleEntree(ActionEvent event) {
		if(event.getSource() == boutonCreerFormule) {
			Formule formule = new Formule(null, null, 0, 0, 0, true);
			String label = textFieldLabelForm.getText();
			String type = textFieldTypeCoursForm.getText();
			int prix = Integer.parseInt(textFieldPrixForm.getText());
			int dureeVal = Integer.parseInt(textFieldDureeValForm.getText());
			int nbEntree = Integer.parseInt(textFieldNbEntreeForm.getText()); // convertir en Int

			formule.setLabel(label);
			formule.setType(type);
			formule.setPrixFormule(prix);
			formule.setDureeValidite(dureeVal);
			formule.setNbreEntreeFormule(nbEntree);
			formule.setActive(true);

			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			formuleDAO.create(formule);
		}
		if(event.getSource() == boutonCreerSeance) {
			LocalDate date = datePickerSeance.getValue();
			int heure = Integer.parseInt(textFieldHeureSeance.getText());
			int minute = Integer.parseInt(textFieldMinSeance.getText());
			int duree = Integer.parseInt(textFieldDureeSeance.getText());
			String prenomProf = (String) choiceBoxProf.getValue(); // récupère nom
			String nomCours = (String) choiceBoxCours.getValue(); // récupère nom
			LocalDateTime dateHeureMin = LocalDateTime.of(date, LocalTime.of(heure, minute));

			CoursDAO coursDAO = CoursDAO.getInstance();
			Cours cours = coursDAO.readByName(nomCours);
			
			ProfesseurDAO profDAO = ProfesseurDAO.getInstance();
			InfoProfesseur prof = profDAO.readByPrenom(prenomProf);

			Seance seance = new Seance(dateHeureMin, duree, prof, cours);
			
			SeanceDAO seanceDAO = SeanceDAO.getInstance(); // L'instruction doit être exécutée avant de pouvoir obtenir des résultats. Ceci dit ça marche
			seanceDAO.create(seance);
			

		}
		if(event.getSource() == boutonCreerCours) {
			Cours cours = new Cours(null, null, null);
			String nom = textFieldNomCours.getText();
			String description = textFieldDescCours.getText();

			//récupération de la formule correspondant au cours
			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			Formule formule = formuleDAO.readByTypes("Cours");

			cours.setNom(nom);
			cours.setDescription(description);
			cours.setFormule(formule);

			CoursDAO coursDAO = CoursDAO.getInstance();
			coursDAO.create(cours);

		}
	}

	public void fermerPaneMaj(ActionEvent event) {
		paneMajSeance.setVisible(false);
		paneMajFormule.setVisible(false);
		paneMajCours.setVisible(false);
	}

}