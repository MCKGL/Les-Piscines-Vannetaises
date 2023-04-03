package controller;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SceneControllerPayement extends SceneController {

	int numeroCarte;
	int crypto;
	
	@FXML
	Pane PpayeR;
	@FXML
	Label Lsomme, LerreurPaye;
	@FXML
	DatePicker Date;
	@FXML
	Button Bpayer;
	@FXML
	TextField TnumCarte, TnomCarte, Tcrypt;
	
	//affiche la somme envoyé en argument dans le label Lsomme (la fonction est appelé dans Abonnement et les détails de cours (SceneControllerReservation)
	public void recupererSomme(String prix) {
		Lsomme.setText("Somme : "+prix);
	}
	
	//vérifie si les champs de paiement sont correctement renseignés
	/*public void payer(ActionEvent event) {

		try {
			numeroCarte = Integer.parseInt(TnumCarte.getText());
			crypto = Integer.parseInt(Tcrypt.getText());
			LocalDate date = Date.getValue();
			
			if (TnomCarte.getText() != "" && date != null ) {
				LerreurPaye.setVisible(false);
				PpayeR.setVisible(true);
			}else {
				LerreurPaye.setText("Entrez un nom de carte et une date valide");
				LerreurPaye.setVisible(true);
			}
			
		}
		catch(NumberFormatException e) {
			LerreurPaye.setText("Numéro carte invalide");
			LerreurPaye.setVisible(true);
		}
		catch(Exception e) {
			LerreurPaye.setText("Erreur de paiement");
			LerreurPaye.setVisible(true);
		}
	}*/
	
	public void payer(ActionEvent event) {
	    try {
	        numeroCarte = Integer.parseInt(TnumCarte.getText());
	        crypto = Integer.parseInt(Tcrypt.getText());
	        LocalDate date = Date.getValue();

	        if (TnomCarte.getText() != "" && date != null && date.isAfter(LocalDate.now())) {
	            LerreurPaye.setVisible(false);
	            PpayeR.setVisible(true);
	        } else {
	            LerreurPaye.setText("Entrez un nom de carte et une date valide");
	            if (date != null && date.isBefore(LocalDate.now())) {
	                LerreurPaye.setText("Sélectionnez une date valide");
	            }
	            LerreurPaye.setVisible(true);
	        }

	    } catch(NumberFormatException e) {
	        LerreurPaye.setText("Numéro carte invalide");
	        LerreurPaye.setVisible(true);
	    } catch(Exception e) {
	        LerreurPaye.setText("Erreur de paiement");
	        LerreurPaye.setVisible(true);
	    }
	}
	
}
