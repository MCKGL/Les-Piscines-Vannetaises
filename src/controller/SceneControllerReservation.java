package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneControllerReservation extends SceneController  {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	Pane PentreeSimple, Pcours, Pabonnement, Pdetail;
	@FXML
	Button BentreeSimple, Bcours, Bfermer, Babonnement, Baquabike, Bperfectionnement, BpayeSolo, BpayeDuo, BpCours;
	@FXML
	TextArea Tdetail, TaboSolo, TaboDuo;
	@FXML
	Label LabelTest, Lsomme, Lprix, LaboDuo, LaboSolo;
	
	
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
	
	public void afficherCours(ActionEvent event) {
		PentreeSimple.setVisible(false);
		Pcours.setVisible(true);
		Pabonnement.setVisible(false);
	}
	
	//afficher description db et prix bd
	public void afficherAbonnement(ActionEvent event) {
		PentreeSimple.setVisible(false);
		Pcours.setVisible(false);
		Pabonnement.setVisible(true);
		
		TaboSolo.setText("jfksjfkjsdgkvsdhg hufshgk hkjehfkjhd hdskjhsdjkfhsjdkhg hkjshfjshfshfjsdhkjs hkjehf hsfjkh hshfjkh zehfhgjk fksjfkjsdgkvsdhg hufshgk hkjehfkjhd hdskjhsdjkfhsjdkhg hkjshfjshfshfjsdhkjs hkjehf hsfjkh hshfjkh zehfhgjk");
		TaboDuo.setText("jfksjfkjsdgkvsdhg hufshgk hkjehfkjhd hdskjhsdjkfhsjdkhg hkjshfjshfshfjsdhkjs hkjehf hsfjkh hshfjkh zehfhgjk fksjfkjsdgkvsdhg hufshgk hkjehfkjhd hdskjhsdjkfhsjdkhg hkjshfjshfshfjsdhkjs hkjehf hsfjkh hshfjkh zehfhgjk");
		LaboSolo.setText("RelierBDprixSolo");
		LaboDuo.setText("RelierBDprixDuo");
	
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
	
	//afficher les détails du cours sélectionné. Il faudra faire coincider les id des boutons avec la base de donnée
	public void afficherDetailCours(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String nom = btn.getId();
		Label lb = LabelTest;
		Pdetail.setVisible(true);
		
		// TODO : il faudra remplacer le champs String ci-dessous par la requête :
		//String requete = "SELECT description FROM cours WHERE nom = ?";
		//PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		//pst.setString(1, cours.getDescription());
		//lb.setText(requete);
		
		lb.setText("detail de..." + nom);

	}
	
	//Aller à la page Payement en injectant le contenu du Label Prix dans la page payement selon le cours ou l'abonnement choisit :
	public void switchToPayement(ActionEvent event) throws IOException {
			if(event.getSource() == BpCours) {
				String prix = Lprix.getText();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererSomme(prix);
			}
			
			if(event.getSource() == BpayeSolo) {
				String prix = LaboSolo.getText();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererSomme(prix);
			}
			
			if(event.getSource() == BpayeDuo) {
				String prix = LaboDuo.getText();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Payement.fxml"));	
				root = loader.load();	
				SceneControllerPayement scenePaye = loader.getController();
				scenePaye.recupererSomme(prix);
			}
		
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	
		}
	
}
