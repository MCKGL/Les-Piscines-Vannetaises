package controleur;

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

/**
 * La class ControleurInterfaceAdmin va proposer les méthodes d'affichages de l'espace admin afin de soulager un peu la SceneControleurAdministrateur
 * Elle ne contiendra pas les méthodes de gestions des formules / cours / seance (create, update, delete), juste l'affichage.
 * ControleurInterfaceAdmin est extends par SceneControleurAdministrateur
 * ControleurInterfaceAdmin extends SceneController afin de récupérer les méthodes générales à toute l'application
 * "protected" pour permettre à la class fille d'user des méthodes
 * "public" quand les méthodes vont aussi concerner Administrateur.fxml
 */

public class ControleurInterfaceAdmin extends SceneControleur {
	
	protected ObservableList<InfoProfesseur> empData = FXCollections.observableArrayList();
	protected ObservableList<Seance> seanceData = FXCollections.observableArrayList();
	protected ObservableList<Formule> formuleData = FXCollections.observableArrayList();
	protected ObservableList<Cours> coursData = FXCollections.observableArrayList();
	
	@FXML
	protected TextField textFieldAdmin, textFieldPrixForm, textFieldDureeValForm, textFieldNbEntreeForm, textFieldLabelForm, textFieldTypeCoursForm, textFieldNomCours, textFieldDescCours, textFieldHeureSeance, textFieldMinSeance, textFieldDureeSeance;
	@FXML
	protected PasswordField passwordFieldAdmin;
	@FXML
	protected Pane paneConnexion, paneAlert, paneMajSeance, paneMajCours, paneMajFormule;
	@FXML
	protected TableView<InfoProfesseur> tableProf;
	@FXML
	protected Label labelDateSeance, labelHeureSeance, labelDureeSeance, labelProfSeance, labelCoursSeance;
	@FXML
	protected TableColumn<InfoProfesseur, String> nom, prenom, mail, dateNaissance;
	@FXML
	protected TableView<Seance> tableSeance;
	@FXML
	protected TableColumn<Seance, String> jour, fin, typeCours, prof;
	@FXML
	protected TableView<Formule> tableFormule;
	@FXML
	protected TableColumn<Formule, String> type, prix, dureeVal, nbEntree, label, active;
	@FXML
	protected Button boutonAffEmploye, boutonAffSeance, boutonAffFormule, boutonAffCours, boutonSupprimer, boutonConfimSupp, boutonInfirmSupp, boutonAjouter, boutonCreerFormule, boutonCreerSeance, boutonCreerCours, boutonSuppCours, boutonUpdCours, boutonSuppSeance, boutonUpdSeance, boutonSuppFormule, boutonUpdFormule;
	@FXML
	protected TableView<Cours> tableCours;
	@FXML
	protected TableColumn<Cours, String> nomCours, description;
	@FXML
	protected DatePicker datePickerSeance;
	@FXML
	protected ChoiceBox<String> choiceBoxProf, choiceBoxCours;
	
	public void connexion(ActionEvent event) {
		try { 
			String mdp = passwordFieldAdmin.getText();
			String nom = textFieldAdmin.getText();
			// TODO créer méthode loggin à appeller ici

			if(mdp.equals("toto") && nom.equals("admin")) {
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
	protected void afficherTableProf() {
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

	protected void afficherTableSeance() {
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

	protected void afficherTableFormule() {
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

	protected void afficherTableCours() {
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
	
	// permet de sélectionner une tupple et d'afficher son interface Maj Supp / Update
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
	
	public void fermerPaneMaj(ActionEvent event) {
		// effacer données saisie ou affiché à la fermeture des panes
		textFieldPrixForm.clear();
		textFieldDureeValForm.clear();
		textFieldNbEntreeForm.clear();
		textFieldLabelForm.clear();
		textFieldTypeCoursForm.clear();
		textFieldNomCours.clear();
		textFieldDescCours.clear();
		labelDateSeance.setText("");
		labelDureeSeance.setText("");
		labelProfSeance.setText("");
		labelCoursSeance.setText("");
		
		paneMajSeance.setVisible(false);
		paneMajFormule.setVisible(false);
		paneMajCours.setVisible(false);
	}
	
}
