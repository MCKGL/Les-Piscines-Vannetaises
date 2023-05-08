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
	private Pane paneEntreeSimple, paneCours, paneAbonnement, paneDetail;
	@FXML
	private Button boutonEntreeSimple, boutonCours, boutonFermer, boutonAbonnement, boutonAquabike, boutonPayerSolo, boutonPayerDuo, boutonPayerCours;
	@FXML
	private Label labelDetail, labelPrix, labelAboDuo, labelAboSolo, labelCoursSelect, labelDetailSolo, labelDetailDuo;
	@FXML
	private ChoiceBox<String> choiceBoxCours;
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		FormuleDAO formuleDAO = FormuleDAO.getInstance();
		Map<String, Button> boutons = new HashMap<>();

		boutons.put("Abonnement Solo", boutonPayerSolo);
		boutons.put("Abonnement Duo", boutonPayerDuo);
		boutons.put("Cours", boutonCours);
		boutons.put("Entree libre", boutonEntreeSimple);

		for (Map.Entry<String, Button> entry : boutons.entrySet()) {
		    Formule formule = formuleDAO.readByTypes(entry.getKey());
		    if (!formule.getActive()) {
		        entry.getValue().setDisable(true);
		    } else {
		        entry.getValue().setDisable(false);
		    }
		}
	}
	
	public void afficherEntreeSimple(ActionEvent event) {
		paneEntreeSimple.setVisible(true);
		paneCours.setVisible(false);
		paneAbonnement.setVisible(false);
	}
	
	// a l'initialisation du Pane "cours", Le ChoiceBox récupère les données de la bd
	public void afficherCours(ActionEvent event) {
		paneEntreeSimple.setVisible(false);
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
		paneEntreeSimple.setVisible(false);
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
		paneEntreeSimple.setVisible(false);
		paneCours.setVisible(false);
		paneAbonnement.setVisible(false);
	}
	
	//ferme la page détail (retour choix de cours)
	public void fermerDetail(ActionEvent event) {
		paneDetail.setVisible(false);
	}
	
	
	//Aller à la page Payement en injectant le contenu du Label Prix dans la page payement selon le cours ou l'abonnement choisit :
	public void allerPayement(ActionEvent event) throws IOException {
			if(event.getSource() == boutonPayerCours) {
				String prix = labelPrix.getText();
				String detail = labelCoursSelect.getText();
				String detailCouF = "Cours";
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererInfo(prix, detail, detailCouF);
			}
			
			if(event.getSource() == boutonPayerSolo) {
				String prix = labelAboSolo.getText();
				String detail = boutonPayerSolo.getText();
				String detailCouF = "Abonnement Solo";
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererInfo(prix, detail, detailCouF);
			}
			
			if(event.getSource() == boutonPayerDuo) {
				String prix = labelAboDuo.getText();
				String detail = boutonPayerDuo.getText();
				String detailCouF = "Abonnement Duo";
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererInfo(prix, detail, detailCouF);
			}
		
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	
		}
	
}
