package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modele.bd.BilletDAO;
import modele.metier.Billet;
import javafx.scene.layout.Pane;

public class SceneControleurIdentification extends SceneControleur {
	
	@FXML 
    TextField textFieldNumBillet; 		//champ de saisie pour le code
    @FXML
    TextArea textAreaDetailBillet;
    @FXML
    Pane paneDetailBillet;
    
    public void rechercher(ActionEvent event) {
		try { 
			int id = Integer.parseInt(textFieldNumBillet.getText());
			Billet billet = BilletDAO.getInstance().read(id);

			if(billet != null) {
				paneDetailBillet.setVisible(true);
				//System.out.println("Billet : " + billet);
				textAreaDetailBillet.setText("Code : "+billet.getCode()+"\n"
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
		paneDetailBillet.setVisible(false);
	}
    
}
