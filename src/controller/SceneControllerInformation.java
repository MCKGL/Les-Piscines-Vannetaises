package controller;

import java.util.List;
import java.util.ResourceBundle;
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
	private TableColumn<Seance, String> fin;
	@FXML
	private TableColumn<Seance, String> typeCours;
    @FXML
	private TableColumn<Seance, String> prof;

	
	public void afficherData() {
		   jour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
		   fin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDuree()));
		   typeCours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCours().getNom()));
		   prof.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProf().getIdEmployee().getPrenom()));

		SeanceDAO seanceDAO = SeanceDAO.getInstance();
		List<Seance> seances = seanceDAO.readAll();
		seanceData.clear();
		seanceData.addAll(seances);
		TableSeance.setItems(seanceData);
		//System.out.println(seanceData);
		System.out.println(seanceDAO.read(1).getDate());
		
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		afficherData();
	}

}
