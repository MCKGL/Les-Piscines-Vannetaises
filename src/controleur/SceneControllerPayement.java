package controleur;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modele.bd.BilletDAO;
import modele.bd.FormuleDAO;
import modele.bd.PiscineDAO;
import modele.metier.Billet;
import modele.metier.Formule;
import modele.metier.Piscine;

public class SceneControllerPayement extends SceneController {

	int numeroCarte;
	int crypto;
	private BilletDAO billetDAO;
	
	@FXML
	Pane panePayer;
	@FXML
	Label labelSomme, labelRecapTypeFormule, labelErreurPaye, labelNomPiscine, labelRecapFormule, labelCodeClient;
	@FXML
	DatePicker datePicker;
	@FXML
	Button boutonPayer;
	@FXML
	TextField textFieldNumCarte, textFieldNomCarte, textFieldCrypt;
	
	//affiche la somme envoyé en argument dans le label Lsomme (la fonction est appelé dans Abonnement et les détails de cours (SceneControllerReservation)
	public void recupererInfo(String prix, String detailFormule, String detailCouF) {
		labelSomme.setText("A payer : "+prix);
		labelRecapFormule.setText(detailCouF);
		labelRecapTypeFormule.setText(detailFormule);
	}
	
	
	public void genererBillet() {
		Billet billet = new Billet(crypto, crypto, null, null);
		
		// récupèration de l'id Piscine en fonction du nom de la piscine
		String NomPiscine = labelNomPiscine.getText();
		PiscineDAO piscineDAO = new PiscineDAO();
		Piscine piscine = piscineDAO.readByName(NomPiscine);
		//int id_piscine = piscine.getIdPiscine();
		
		// récupération de l'id de la formule
		String TypeFormule = labelRecapFormule.getText();
		FormuleDAO formuleDAO = new FormuleDAO();
		Formule formule = formuleDAO.readByTypes(TypeFormule);
		//int id_formule = formule.getIdFormule();
		
		// récupération du nombre d'entrée initiale
		int nbEntree = formule.getNbreEntreeFormule();
		
		// TODO creation code unique
		int code = 12345678;
		
		// Créer un objet billet avec les valeurs récupérées
		billet.setCode(code);
		billet.setNbreEntreeRestante(nbEntree);
		billet.setPiscine(piscine);
		billet.setFormule(formule);
		
		billetDAO = new BilletDAO();
	    billetDAO.create(billet);
	    
	    // va permettre d'afficher le code au client (il faut convertir en String)
	    String codeClient = String.valueOf(code);
	    labelCodeClient.setText(codeClient);
		
	}
	
	public void payer(ActionEvent event) {
	    try {
	        numeroCarte = Integer.parseInt(textFieldNumCarte.getText());
	        crypto = Integer.parseInt(textFieldCrypt.getText());
	        LocalDate date = datePicker.getValue();

	        if (textFieldNomCarte.getText() != "" && date != null && date.isAfter(LocalDate.now())) {
	            labelErreurPaye.setVisible(false);
	            panePayer.setVisible(true);
	            genererBillet();
	        } else {
	            labelErreurPaye.setText("Entrez un nom de carte et une date valide");
	            if (date != null && date.isBefore(LocalDate.now())) {
	                labelErreurPaye.setText("Sélectionnez une date valide");
	            }
	            labelErreurPaye.setVisible(true);
	        }

	    } catch(NumberFormatException e) {
	        labelErreurPaye.setText("Numéro carte invalide");
	        labelErreurPaye.setVisible(true);
	    } catch(Exception e) {
	        labelErreurPaye.setText("Erreur de paiement");
	        labelErreurPaye.setVisible(true);
	    }
	}
	
}
