package controller;

import java.sql.Date;
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
import javafx.scene.layout.Pane;
import model.EmployeeDAO;

public class SceneControllerAdministrateur extends SceneController {

	/*TODO
	 * Relier l'identification à la bd admin
	 * Afficher la liste des profs sur le pane
	 * Supprimer un Prof
	 * Update un Prof
	 * Créer un prof
	 */
	private ObservableList<Employee> empData = FXCollections.observableArrayList();
	
    @FXML
    TextField TFadmin;
    @FXML
    PasswordField PFadmin;
    @FXML
    Pane Pconnect;
    @FXML
    private TableView<Employee> TableProf;
    @FXML
	private TableColumn<Employee, String> nom;
	@FXML
	private TableColumn<Employee, String> prenom;
	@FXML
	private TableColumn<Employee, String> mail;
	@FXML
	private TableColumn<Employee, Date> date_naissance;
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
	
	   
	   public ObservableList<Employee> getEmpData() {
			return empData;
		}
	   
	   public void afficherData(ActionEvent event) {
		   if(event.getSource() == BaffEmployee) {
			   TableProf.setVisible(true);
			   nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
			   prenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
			   mail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
			   // TODO : Régler le soucis affichage date (problème de format)
			   //date_naissance.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateNaissanceSQL()));
		   
		   
			   EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
			   List<Employee> employees = employeeDAO.readAll();
			   empData.clear();
			   empData.addAll(employees);
			   TableProf.setItems(empData);
			   System.out.println(empData);
		   }
		   if(event.getSource() == BaffSeance) {
			   //TODO : afficher les seances 
		   }
		   
	   }
		
	
}
