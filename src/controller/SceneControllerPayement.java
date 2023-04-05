package controller;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.BilletDAO;
import model.CoursDAO;
import model.FormuleDAO;
import model.PiscineDAO;

public class SceneControllerPayement extends SceneController {

	int numeroCarte;
	int crypto;
	private BilletDAO billetDAO;
	
	@FXML
	Pane PpayeR;
	@FXML
	Label Lsomme, Lrecap, LerreurPaye, LnomPiscine, LrecapFormule, LcodeClient;
	@FXML
	DatePicker Date;
	@FXML
	Button Bpayer;
	@FXML
	TextField TnumCarte, TnomCarte, Tcrypt;
	
	//affiche la somme envoyé en argument dans le label Lsomme (la fonction est appelé dans Abonnement et les détails de cours (SceneControllerReservation)
	public void recupererInfo(String prix, String detailFormule, String detailCouF) {
		Lsomme.setText("A payer : "+prix);
		LrecapFormule.setText(detailCouF);
		Lrecap.setText(detailFormule);
	}
	
	
	public void genererBillet() {
		Billet billet = new Billet(crypto, crypto, null, null);
		
		// récupèration de l'id Piscine en fonction du nom de la piscine
		String NomPiscine = LnomPiscine.getText();
		PiscineDAO piscineDAO = new PiscineDAO();
		Piscine piscine = piscineDAO.readByName(NomPiscine);
		//int id_piscine = piscine.getIdPiscine();
		
		// récupération de l'id de la formule
		String TypeFormule = LrecapFormule.getText();
		FormuleDAO formuleDAO = new FormuleDAO();
		Formule formule = formuleDAO.readByTypes(TypeFormule);
		//int id_formule = formule.getIdFormule();
		
		// récupération du nombre d'entrée initiale
		int nbEntree = formule.getNbreEntreeFormule();
		
		// TODO creation code unique
		int code = 1234567;
		
		// Créer un objet billet avec les valeurs récupérées
		billet.setCode(code);
		billet.setNbreEntreeRestante(nbEntree);
		billet.setPiscine(piscine);
		billet.setFormule(formule);
		
		billetDAO = new BilletDAO();
	    billetDAO.create(billet);
	    
	    // va permettre d'afficher le code au client (il faut convertir en String)
	    String codeClient = String.valueOf(code);
	    LcodeClient.setText(codeClient);
		
	}
	
	public void payer(ActionEvent event) {
	    try {
	        numeroCarte = Integer.parseInt(TnumCarte.getText());
	        crypto = Integer.parseInt(Tcrypt.getText());
	        LocalDate date = Date.getValue();

	        if (TnomCarte.getText() != "" && date != null && date.isAfter(LocalDate.now())) {
	            LerreurPaye.setVisible(false);
	            PpayeR.setVisible(true);
	            genererBillet();
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
