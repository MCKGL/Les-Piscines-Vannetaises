package controleur;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.bd.CoursDAO;
import modele.bd.FormuleDAO;
import modele.bd.ProfesseurDAO;
import modele.bd.SeanceDAO;
import modele.metier.Cours;
import modele.metier.Formule;
import modele.metier.InfoProfesseur;
import modele.metier.Seance;

public class SceneControllerAdministrateur extends SceneController {

	private ObservableList<InfoProfesseur> empData = FXCollections.observableArrayList();
	private ObservableList<Seance> seanceData = FXCollections.observableArrayList();
	private ObservableList<Formule> formuleData = FXCollections.observableArrayList();
	private ObservableList<Cours> coursData = FXCollections.observableArrayList();

	@FXML
	private TextField textFieldAdmin;
	@FXML
	private PasswordField passwordFieldAdmin;
	@FXML
	private Pane paneConnexion, paneMaJ, paneAlert;
	@FXML
	private TextArea textAreaLigneSelect;
	@FXML
	private Label labelTitreMaj;
	@FXML
	private TableView<InfoProfesseur> tableProf;
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
	private Button boutonAffEmploye, boutonAffSeance, boutonAffFormule, boutonAffCours, boutonSupprimer, boutonConfimSupp, boutonInfirmSupp;
	@FXML
	private TableView<Cours> tableCours;
	@FXML
	private TableColumn<Cours, String> nomCours, description;

	public void connexion(ActionEvent event) {
		try { 
			int mdp = Integer.parseInt(passwordFieldAdmin.getText());
			String nom = textFieldAdmin.getText();
			// TODO créer méthode loggin à appeller ici

			if(mdp == 123456 && nom.equals("Maurice")) {
				paneConnexion.setVisible(true);
				tableProf.setVisible(false);

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

	public void fermerPaneMaJ(ActionEvent event) {
		paneMaJ.setVisible(false);
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
			paneMaJ.setVisible(true);
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
		if (formule != null && tableFormule.isVisible()) {
			if(formule.getType().equals("Cours")) {
				paneAlert.setVisible(true);
			}else {
				formule.setActive(false);
				FormuleDAO formuleDAO = FormuleDAO.getInstance();
				formuleDAO.update(formule);
			    // Mettre à jour le tableau
			    int index = formuleData.indexOf(formule);
			    if (index != -1) {
			        formuleData.set(index, formule);
			    }
			}
		}else if (cours != null && tableCours.isVisible()) {
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
		    
		    // On supprime les cours APRES les seances, sans quoi il y aura un soucis avec l'id cours
			coursDAO.delete(cours);
			coursData.remove(cours);
		    
		}else if (seance != null && tableSeance.isVisible()) {
			SeanceDAO seanceDAO = SeanceDAO.getInstance();
			seanceDAO.delete(seance);
			seanceData.remove(seance);
		}
		paneMaJ.setVisible(false);
	}

	// permet de sélectionner une tupple
	public void selectionLigne(MouseEvent event) {
		// Récupérer la ligne sélectionnée
		Seance seance = tableSeance.getSelectionModel().getSelectedItem();
		Formule formule = tableFormule.getSelectionModel().getSelectedItem();
		Cours cours = tableCours.getSelectionModel().getSelectedItem();
		if (formule != null && tableFormule.isVisible()) {
			labelTitreMaj.setText("Formule");
			textAreaLigneSelect.setText("Type de formule : "+formule.getType()+"\n"
					+ "Prix de la formule : "+formule.getPrixToString()+"\n"
					+ "Durée de la validité de la fomule : "+formule.getDurreeValiditeToString()+"\n"
					+ "Nombre d'entrées initial : "+formule.getNbEntreeToString()+"\n"
					+ "Description : "+formule.getLabel());
		}else if (cours != null && tableCours.isVisible()) {
			labelTitreMaj.setText("Cours");
			textAreaLigneSelect.setText("Type de cours : "+cours.getNom()+"\n"
					+ "Description : "+cours.getDescription());
		}else if (seance != null && tableSeance.isVisible()) {
			labelTitreMaj.setText("Séance");
			textAreaLigneSelect.setText("Date : "+seance.toStringDate()+"\n"
					+ "Durée : "+seance.toStringDuree()+"\n"
					+ "Type du cours : "+seance.getIdCours().getNom()+"\n"
					+ "Prof en charge : "+seance.getIdProf().getIdEmployee().getPrenom());
		}
		paneMaJ.setVisible(true);
	}


}