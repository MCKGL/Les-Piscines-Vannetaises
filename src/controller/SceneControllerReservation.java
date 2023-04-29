package controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CoursDAO;
import model.FormuleDAO;
import service.Cours;
import service.Formule;

public class SceneControllerReservation extends SceneController  {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private CoursDAO coursDAO;
	private FormuleDAO formuleDAO;
	
	@FXML
	Pane PentreeSimple, Pcours, Pabonnement, Pdetail;
	@FXML
	Button BentreeSimple, Bcours, Bfermer, Babonnement, Baquabike, Bperfectionnement, BpayeSolo, BpayeDuo, BpCours;
	@FXML
	TextArea Tdetail, TaboSolo, TaboDuo;
	@FXML
	Label LDetail, Lsomme, Lprix, LaboDuo, LaboSolo, LCoursSelect, LDetailSolo, LDetailDuo;
	@FXML
	ChoiceBox ChoiceBoxCours;
	
	//retourne le prix de la sélection
	public void recupererPrixBd() {
		//TODO relier à la base de donnée et retourner le prix des cours.
		//String requete = "SELECT prix FROM formule WHERE nom = ?"; etc
		//PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		//pst.setString(1, cours.getDescription());
		//lb.setText(requete);
	}
	
	public void afficherEntreeSimple(ActionEvent event) {
		PentreeSimple.setVisible(true);
		Pcours.setVisible(false);
		Pabonnement.setVisible(false);
	}
	
	// a l'initialisation du Pane "cours", Le ChoiceBox récupère les données de la bd
	public void afficherCours(ActionEvent event) {
		PentreeSimple.setVisible(false);
		Pcours.setVisible(true);
		Pabonnement.setVisible(false);
		
	    coursDAO = new CoursDAO();
	    List<Cours> listeCours = CoursDAO.getInstance().readAll();
	    ChoiceBoxCours.setValue(listeCours.get(0).getNom());
	    for (Cours cours : listeCours) {
	        ChoiceBoxCours.getItems().add(cours.getNom());
	    }
	}
	
	//afficher les détails du cours sélectionné. Il faudra faire coincider les id des boutons avec la base de donnée
	public void afficherDetailCours(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String nom = (String) ChoiceBoxCours.getValue();
		Label lb = LDetail;
		Label lp = Lprix;
		Label lc = LCoursSelect;
		Pdetail.setVisible(true);
		
		coursDAO = new CoursDAO();
	    Cours cours = coursDAO.readByName(nom);
		
	    lc.setText(nom);
		lb.setText(cours.getDescription());
		lp.setText(Integer.toString(cours.getFormule().getPrixFormule()) + "€");
	}
	
	//afficher description db et prix bd
	public void afficherAbonnement(ActionEvent event) {
		String AboSolo = "Abonnement Solo";
		String AboDuo = "Abonnement Duo";
		PentreeSimple.setVisible(false);
		Pcours.setVisible(false);
		Pabonnement.setVisible(true);
		
		formuleDAO = new FormuleDAO();
		Formule formuleSolo = formuleDAO.readByTypes(AboSolo);
		Formule formuleDuo = formuleDAO.readByTypes(AboDuo);
		
		LDetailSolo.setText(formuleSolo.getLabel());
		LDetailDuo.setText(formuleDuo.getLabel());
		LaboSolo.setText(Integer.toString(formuleSolo.getPrixFormule()) + "€");
		LaboDuo.setText(Integer.toString(formuleDuo.getPrixFormule()) + "€");
	
	}
	
	//ferme les panes 1er niveau
	public void retourReservation(ActionEvent event) {
		PentreeSimple.setVisible(false);
		Pcours.setVisible(false);
		Pabonnement.setVisible(false);
	}
	
	//ferme la page détail (retour choix de cours)
	public void fermerDetail(ActionEvent event) {
		Pdetail.setVisible(false);
	}
	
	
	//Aller à la page Payement en injectant le contenu du Label Prix dans la page payement selon le cours ou l'abonnement choisit :
	public void switchToPayement(ActionEvent event) throws IOException {
			if(event.getSource() == BpCours) {
				String prix = Lprix.getText();
				String detail = LCoursSelect.getText();
				String detailCouF = "Cours";
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererInfo(prix, detail, detailCouF);
			}
			
			if(event.getSource() == BpayeSolo) {
				String prix = LaboSolo.getText();
				String detail = BpayeSolo.getText();
				String detailCouF = "Abonnement Solo";
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererInfo(prix, detail, detailCouF);
			}
			
			if(event.getSource() == BpayeDuo) {
				String prix = LaboDuo.getText();
				String detail = BpayeDuo.getText();
				String detailCouF = "Abonnement Duo";
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Payement.fxml"));	
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
