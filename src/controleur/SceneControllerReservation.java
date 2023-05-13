package controleur;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.bd.CoursDAO;
import modele.bd.FormuleDAO;
import modele.metier.Cours;
import modele.metier.Formule;

public class SceneControllerReservation extends SceneController implements Initializable  {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Pane paneCours, paneAbonnement, paneDetail;
	@FXML
	private Button boutonEntreeSimple, boutonCours, boutonFermer, boutonAbonnement, boutonAquabike, boutonPayerSolo, boutonPayerDuo, boutonPayerCours;
	@FXML
	private Label labelDetail, labelPrix, labelAboDuo, labelAboSolo, labelCoursSelect, labelDetailSolo, labelDetailDuo, labelPrixEntreeSimple;
	@FXML
	private ChoiceBox<String> choiceBoxCours;
	
	//méthode appelé à l'initialisation de la page (doit être implementé au niveau de la déclaration de Class)
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		// on va associer au bouton les types de formules
		FormuleDAO formuleDAO = FormuleDAO.getInstance();
		Map<String, Button> boutons = new HashMap<>();

		boutons.put("Abonnement Solo", boutonPayerSolo);
		boutons.put("Abonnement Duo", boutonPayerDuo);
		boutons.put("Cours", boutonCours);
		boutons.put("Entree libre", boutonEntreeSimple);

		// Si la formule concernée est inactive, rendre le bouton inactif
		for (Map.Entry<String, Button> entry : boutons.entrySet()) {
		    Formule formule = formuleDAO.readByTypes(entry.getKey());
		    if (!formule.getActive()) {
		        entry.getValue().setDisable(true);
		    } else {
		        entry.getValue().setDisable(false);
		    }
		}
		
        //methode pour l'implementation du prix d'un billet via le bouton entrée libre
        String type = "Entree libre";
        FormuleDAO formuleDao = FormuleDAO.getInstance();
        Formule entreSimple = formuleDao.readByTypes(type);
        labelPrixEntreeSimple.setText(Integer.toString(entreSimple.getPrixFormule()) + "€");
	}
	
	// a l'initialisation du Pane "cours", Le ChoiceBox récupère les données de la bd
	public void afficherCours(ActionEvent event) {
		paneCours.setVisible(true);
		paneAbonnement.setVisible(false);
		
	    List<Cours> listeCours = CoursDAO.getInstance().readAll();
	    choiceBoxCours.setValue(listeCours.get(0).getNom());
	    for (Cours cours : listeCours) {
	        choiceBoxCours.getItems().add(cours.getNom());
	    }
	}
	
	//afficher les détails du cours sélectionné.
	public void afficherDetailCours(ActionEvent event) {
		String nom = (String) choiceBoxCours.getValue();
		Label lb = labelDetail;
		Label lp = labelPrix;
		Label lc = labelCoursSelect;
		paneDetail.setVisible(true);
		
		CoursDAO coursDAO = CoursDAO.getInstance();
	    Cours cours = coursDAO.readByName(nom);
		
	    lc.setText(nom);
		lb.setText(cours.getDescription());
		lp.setText(Integer.toString(cours.getFormule().getPrixFormule()) + "€");
	}
	
	//afficher description db et prix bd
	public void afficherAbonnement(ActionEvent event) {
		String AboSolo = "Abonnement Solo";
		String AboDuo = "Abonnement Duo";
		paneCours.setVisible(false);
		paneAbonnement.setVisible(true);
		
		FormuleDAO formuleDAO = FormuleDAO.getInstance();
		Formule formuleSolo = formuleDAO.readByTypes(AboSolo);
		Formule formuleDuo = formuleDAO.readByTypes(AboDuo);
		
		labelDetailSolo.setText(formuleSolo.getLabel());
		labelDetailDuo.setText(formuleDuo.getLabel());
		labelAboSolo.setText(Integer.toString(formuleSolo.getPrixFormule()) + "€");
		labelAboDuo.setText(Integer.toString(formuleDuo.getPrixFormule()) + "€");
	
	}
	
	//ferme les panes 1er niveau
	public void retourReservation(ActionEvent event) {
		paneCours.setVisible(false);
		paneAbonnement.setVisible(false);
	}
	
	//ferme la page détail (retour choix de cours)
	public void fermerDetail(ActionEvent event) {
		paneDetail.setVisible(false);
	}
	
	
	//Aller à la page Payement en injectant le contenu du Label Prix dans la page payement selon le cours ou l'abonnement choisit :
	public void allerPayement(ActionEvent event) throws IOException {
		String prix = "";
		String detail = "";
		String detailCouF = "";
			if(event.getSource() == boutonPayerCours) {
				prix = labelPrix.getText();
				detail = labelCoursSelect.getText();
				detailCouF = "Cours";	
			}
			
			if(event.getSource() == boutonPayerSolo) {
				prix = labelAboSolo.getText();
				detail = boutonPayerSolo.getText();
				detailCouF = "Abonnement Solo";	
			}
			
			if(event.getSource() == boutonPayerDuo) {
				prix = labelAboDuo.getText();
				detail = boutonPayerDuo.getText();
				detailCouF = "Abonnement Duo";	
			}
            if(event.getSource() == boutonEntreeSimple) {
                prix = labelPrixEntreeSimple.getText();
                detail = boutonEntreeSimple.getText();
                detailCouF = "Entrée simple"; 
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Payement.fxml"));
            root = loader.load();	
			SceneControllerPayement scenePaye = loader.getController();
			scenePaye.recupererInfo(prix, detail, detailCouF);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	
		}
	
}
