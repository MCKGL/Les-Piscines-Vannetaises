package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BilletDAO;
import service.Billet;
import javafx.scene.layout.Pane;

public class SceneControllerIdentification extends SceneController{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML 
    TextField codeField, Tnumid; 		//champ de saisie pour le code
    @FXML 
    Button searchButton;  		// Bouton pour lancer la recherche
    @FXML
    AnchorPane Administrateur;  //variable pour la page Administrateur
    @FXML
    TextArea TdetailBillet;
    @FXML
    Pane PdetailBillet;
    
	//----------------------Essaye avec un Try----------------------------------//

    
    /*public void handleSearchButton(ActionEvent event) {
		// On utilise un Try pour gérer l'exeption 
		try { 
			// si la valeur dans le champ texte est égal à ex:"123456"
			if(Tnumid.getText().equals("123456")) {
				//alors on change de page avec notre methode de changement de scene
				root = FXMLLoader.load(getClass().getResource("/vue/Administrateur.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}else {
				//sinon on a un message (pour le moment - TO DO) 
				System.out.println("Code incorrect, veuillez réessayer.");
			}
		}
		catch(Exception e) {
			System.out.println("ERREUR");
			//e.printStackTrace();
        }
	}*/
    
    public void rechercher(ActionEvent event) {
		try { 
			int id = Integer.parseInt(Tnumid.getText());
			Billet billet = BilletDAO.getInstance().read(id);

			if(billet != null) {
				PdetailBillet.setVisible(true);
				//System.out.println("Billet : " + billet);
				TdetailBillet.setText("Code : "+billet.getCode()+"\n"
									+ "Nature de l'achat : "+billet.getFormule().getType()+"\n"
									+ "Nombre d'entrées restantes : "+billet.getNbreEntreeRestante()+"\n"
									+ "Prix : "+billet.getFormule().getPrixFormule()+"\n"
									+ "Acheté à : "+billet.getPiscine().getNom());
			}else {
				System.out.println("Aucun billet trouvé avec le code : " + id);
			}
		}
		catch(Exception e) {
			System.out.println("Le code saisi est incorrect");
			e.printStackTrace();
        }
	}
    
	public void fermerDetail(ActionEvent event) {
		PdetailBillet.setVisible(false);
	}
    
}
