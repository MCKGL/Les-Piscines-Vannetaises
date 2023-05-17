package controleur;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

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

public class SceneControleurPayement extends SceneControleur {

	int numeroCarte;
	int crypto;
	
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
	
	// ici il reste des risques (très minime vue le context) d'avoir deux code identique. On peut faire yyMMddHHmmssSSS mais il faut transformer code en bigint (long en dao)
	public static int codeDate() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
	    Date date = new Date();
	    String dateString = dateFormat.format(date);
	    int code = Integer.parseInt(dateString);
	    return code;
	}
	
	public void genererBillet() {
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
		
		//creation de la date de peremption et conversion de la date en format SQL
		Date dateAuj = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateAuj);
		calendar.add(Calendar.DAY_OF_MONTH, formule.getDureeValidite());
		Timestamp datePeremptionSQL = new Timestamp(calendar.getTime().getTime());
		
		// creation code unique
		int code = codeDate();
		
		// Créer un objet billet avec les valeurs récupérées
		Billet billet = new Billet(code, nbEntree, datePeremptionSQL, formule, piscine);
	    
	    BilletDAO billetDAO = BilletDAO.getInstance();
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
