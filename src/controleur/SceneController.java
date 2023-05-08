package controleur;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
// ------------------------------Changement de pages--------------------------------------------
	
	//Aller à la page Acceuil 
	public void allerAccueil(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Accueil.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Aller à la page Reservation
	public void allerReservation(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Reservation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Aller à la page Information
	public void allerInformation(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Information.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Aller à la page Identification
	public void allerIdentification(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Identification.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void allerAdmin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Administrateur.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
