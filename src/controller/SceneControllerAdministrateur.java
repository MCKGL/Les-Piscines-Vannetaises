package controller;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.ProfesseurDAO;
import model.SeanceDAO;
import service.InfoProfesseur;
import service.Seance;

public class SceneControllerAdministrateur extends SceneController {

	/*TODO
	 * Relier l'identification à la bd admin
	 * Supprimer un Prof
	 * Update un Prof
	 * Créer un prof
	 */
	private ObservableList<InfoProfesseur> empData = FXCollections.observableArrayList();
	private ObservableList<Seance> seanceData = FXCollections.observableArrayList();
	
    @FXML
    TextField TFadmin;
    @FXML
    PasswordField PFadmin;
    @FXML
    Pane Pconnect, Pdetail;
    @FXML
    private TableView<InfoProfesseur> TableProf;
    @FXML
	private TableColumn<InfoProfesseur, String> nom;
	@FXML
	private TableColumn<InfoProfesseur, String> prenom;
	@FXML
	private TableColumn<InfoProfesseur, String> mail;
	@FXML
	private TableColumn<InfoProfesseur, String> date_naissance;
    @FXML
    private TableView<Seance> TableSeance;
    @FXML
	private TableColumn<Seance, String> jour;
	@FXML
	private TableColumn<Seance, String> fin;
	@FXML
	private TableColumn<Seance, String> typeCours;
    @FXML
	private TableColumn<Seance, String> prof;
	@FXML
	Button BaffEmployee, BaffSeance;

	   public void connexion(ActionEvent event) {
			try { 
				int mdp = Integer.parseInt(PFadmin.getText());
				String nom = TFadmin.getText();
				// TODO créer méthode loggin à appeller ici

				if(mdp == 123456 && nom.equals("Maurice")) {
					Pconnect.setVisible(true);
					TableProf.setVisible(false);
					
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
	
	   
	   public ObservableList<InfoProfesseur> getEmpData() {
			return empData;
		}
	   
	   public ObservableList<Seance> getSeanceData() {
			return seanceData;
		}
	   
	   public void afficherData(ActionEvent event) {
		   if(event.getSource() == BaffEmployee) {
			   TableProf.setVisible(true);
			   TableSeance.setVisible(false);
			   nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().getNom()));
			   prenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().getPrenom()));
			   mail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().getMail()));
			   date_naissance.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmployee().toStringDateNaissance()));
		   
		   
			   ProfesseurDAO professeurDAO = ProfesseurDAO.getInstance();
			   List<InfoProfesseur> profs = professeurDAO.readAll();
			   empData.clear();
			   empData.addAll(profs);
			   TableProf.setItems(empData);
			   System.out.println(empData);
		   }
		   if(event.getSource() == BaffSeance) {
			   TableProf.setVisible(false);
			   TableSeance.setVisible(true);
			   jour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
			   fin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDuree()));
			   typeCours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCours().getNom()));
			   prof.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProf().getIdEmployee().getPrenom()));
			   
			   SeanceDAO seanceDAO = SeanceDAO.getInstance();
			   List<Seance> seances = seanceDAO.readAll();
			   seanceData.clear();
			   seanceData.addAll(seances);
			   TableSeance.setItems(seanceData);
			   System.out.println(seanceData);
			   
		   }
		   
	   }
	   
	   // permet de sélectionner une tupple
	   public void Ontupple(MouseEvent event) {
		    // Récupérer la ligne sélectionnée
		    Seance seance = TableSeance.getSelectionModel().getSelectedItem();
		    if (seance != null) {
		    	Pdetail.setVisible(true);
		    }
		}
		
	
}
