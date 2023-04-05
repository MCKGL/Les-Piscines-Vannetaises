package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SceneControllerAdministrateur extends SceneController {

	/*TODO
	 * Relier l'identification à la bd admin
	 * Afficher la liste des profs sur le pane
	 * Supprimer un Prof
	 * Update un Prof
	 * Créer un prof
	 */
	
    @FXML
    TextField TFadmin;
    @FXML
    PasswordField PFadmin;
    @FXML
    Pane Pconnect;

	   public void connexion(ActionEvent event) {
			try { 
				int mdp = Integer.parseInt(PFadmin.getText());
				String nom = TFadmin.getText();
				// TODO appeller instance admin

				if(mdp == 123456 && nom.equals("Maurice")) {
					Pconnect.setVisible(true);
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
		   Pconnect.setVisible(false);
	   }
	
	
	
}
