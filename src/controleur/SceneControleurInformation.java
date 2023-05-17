package controleur;

import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modele.bd.SeanceDAO;
import modele.metier.Seance;

public class SceneControleurInformation extends SceneControleur implements Initializable {

	private ObservableList<Seance> seanceData = FXCollections.observableArrayList();

    @FXML
    private TableView<Seance> tableSeance;
    @FXML
	private TableColumn<Seance, String> jour, fin, typeCours, prof;
	
    //TODO : sélecteur cours + bouton qui affiche séances en fonction.
    
	public void afficherData() {
		jour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
		fin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDuree()));
		typeCours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCours().getNom()));
		prof.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProf().getIdEmployee().getPrenom()));

		SeanceDAO seanceDAO = SeanceDAO.getInstance();
		List<Seance> seances = seanceDAO.readAll();
		seanceData.clear();
		seanceData.addAll(seances);
		tableSeance.setItems(seanceData);		
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		afficherData();
	}

}
