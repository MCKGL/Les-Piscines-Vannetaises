package controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.SeanceDAO;
import service.Seance;

public class SceneControllerInformation extends SceneController implements Initializable {

	private ObservableList<Seance> seanceData = FXCollections.observableArrayList();

	@FXML
	private TableView<Seance> TableSeance;
	@FXML
	private TableColumn<Seance, String> jour;
	@FXML
	private TableColumn<Seance, String> debut;
	@FXML
	private TableColumn<Seance, String> fin;
	@FXML
	private TableColumn<Seance, String> typeCours;

	
	public void afficherData() {
		jour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJourSemaine()));
		debut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHeureDebut()));
		fin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHeureFin()));
		typeCours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCours().getNom()));

		SeanceDAO seanceDAO = SeanceDAO.getInstance();
		List<Seance> seances = seanceDAO.readAll();
		seanceData.clear();
		seanceData.addAll(seances);
		TableSeance.setItems(seanceData);
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		afficherData();
	}

}
