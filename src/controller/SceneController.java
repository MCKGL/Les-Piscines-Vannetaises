package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CoursDAO;

public class SceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
// ------------------------------Changement de pages--------------------------------------------
	
	//Aller à la page Acceuil 
	public void switchToHome(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Aller à la page Reservation
	public void switchToRervation(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Reservation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Aller à la page Information
	public void switchToInformation(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Information.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Aller à la page Identification
	public void switchToIdentification(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Identification.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToAdmin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/vue/Administrateur.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
