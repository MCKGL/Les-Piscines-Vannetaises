package controleur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javafx.event.ActionEvent;
import modele.bd.CoursDAO;
import modele.bd.FormuleDAO;
import modele.bd.ProfesseurDAO;
import modele.bd.SeanceDAO;
import modele.metier.Cours;
import modele.metier.Formule;
import modele.metier.InfoProfesseur;
import modele.metier.Seance;

/**
 * SceneControllerAdministrateur extends ControleurInterfaceAdmin, cette dernière s'occupe de l'affichage.
 * Voir ControleurInterfaceAdmin.
 */

public class SceneControllerAdministrateur extends ControleurInterfaceAdmin {

	// Confirmation de la suppression.
	public void confirmSupp(ActionEvent event) {
		Formule formule = tableFormule.getSelectionModel().getSelectedItem();
		if(event.getSource() == boutonConfimSupp) {
			/*
			 * Si la formule type "cours" est supprimé, tous les cours aussi et les séances aussi.
			 * On commence par supprimer les seances, puis les cours, puis les formules. 
			 * Sans quoi on aura un soucis avec les clefs étrangères
			 */
			SeanceDAO seanceDAO = SeanceDAO.getInstance();
			List<Seance> seanceList = seanceDAO.readAll();
			for (Seance seance : seanceList) {
				seanceDAO.delete(seance);
				seanceData.remove(seance);
			}

			CoursDAO coursDAO = CoursDAO.getInstance();
			List<Cours> coursList = coursDAO.readAll();
			for (Cours cours : coursList) {
				coursDAO.delete(cours);
				coursData.remove(cours);
			}

			// On va avoir un problème ici : les billets contiennent une formule, on ne peut pas 
			// supprimer les formules sans invalider les billets. Création donc d'un champ active
			// dans la bd, le champ passera à false, désactivant la formule.
			formule.setActive(false);
			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			formuleDAO.update(formule);
			// Mettre à jour le tableau
			int index = formuleData.indexOf(formule);
			if (index != -1) {
				formuleData.set(index, formule);
			}

		}else {
			paneMajFormule.setVisible(true);
		}
		paneAlert.setVisible(false);
	}

	/* Lorsque l'admin clique sur supprimer, la ligne concernée est supprimé de la bd.
	 * Dans le cas où il supprime les formules de types Cours, il devra confirmer son choix
	 * puisque l'action supprimera aussi les cours possédant l'id de cette formule et les séances associées aux cours
	 * voir confirmSupp
	 */
	public void supprimerSelection(ActionEvent event) {
		Seance seance = tableSeance.getSelectionModel().getSelectedItem();
		Formule formule = tableFormule.getSelectionModel().getSelectedItem();
		Cours cours = tableCours.getSelectionModel().getSelectedItem();
		if (formule != null && event.getSource() == boutonSuppFormule) {
			if(formule.getType().equals("Cours")) {
				// les formules cours ont des dépendance, on va donc sécuriser l'action en propose un pane de validation de la demande
				paneAlert.setVisible(true);
			}else {
				// si la formule n'est pas un cours, on la désactive simplement (on ne surppime pas pour ne pas invalider les billets)
				formule.setActive(false);
				FormuleDAO formuleDAO = FormuleDAO.getInstance();
				formuleDAO.update(formule);
				// Mettre à jour le tableau
				int index = formuleData.indexOf(formule);
				if (index != -1) {
					formuleData.set(index, formule);
				}
			}
		// supprimer un cours
		}else if (cours != null && event.getSource() == boutonSuppCours) {
			CoursDAO coursDAO = CoursDAO.getInstance();

			// Il faut aussi supprimer les seances associées au cours, pour ça, on va aller chercher
			// l'Id du cours sélectionné et s'en servir pour supprimer les séances dont l'id cours correspondant
			int idCours = cours.getIdCours();

			SeanceDAO seanceDAO = SeanceDAO.getInstance();
			List<Seance> seanceList = seanceDAO.readAllByCoursId(idCours);
			for (Seance uneSeance : seanceList) {
				seanceDAO.delete(uneSeance);
				seanceData.remove(uneSeance);
			}

			// On supprime les cours APRES les seances, sans quoi il y aura un soucis avec l'id cours FK
			coursDAO.delete(cours);
			coursData.remove(cours);
		// supprimer seance
		}else if (seance != null && event.getSource() == boutonSuppSeance) {
			SeanceDAO seanceDAO = SeanceDAO.getInstance();
			seanceDAO.delete(seance);
			seanceData.remove(seance);
		}
		paneMajFormule.setVisible(false);
		paneMajSeance.setVisible(false);
		paneMajCours.setVisible(false);
	}
	
	// Créer une nouvelle formule / cours / seance (en fonction du bouton cliqué)
	public void creerNouvelleEntree(ActionEvent event) {
		if(event.getSource() == boutonCreerFormule) {
			Formule formule = new Formule(null, null, 0, 0, 0, true);
			String label = textFieldLabelForm.getText();
			String type = textFieldTypeCoursForm.getText();
			int prix = Integer.parseInt(textFieldPrixForm.getText());
			int dureeVal = Integer.parseInt(textFieldDureeValForm.getText());
			int nbEntree = Integer.parseInt(textFieldNbEntreeForm.getText()); // convertir en Int

			formule.setLabel(label);
			formule.setType(type);
			formule.setPrixFormule(prix);
			formule.setDureeValidite(dureeVal);
			formule.setNbreEntreeFormule(nbEntree);
			formule.setActive(true);

			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			formuleDAO.create(formule);
		}
		if(event.getSource() == boutonCreerSeance) {
			LocalDate date = datePickerSeance.getValue();
			int heure = Integer.parseInt(textFieldHeureSeance.getText());
			int minute = Integer.parseInt(textFieldMinSeance.getText());
			int duree = Integer.parseInt(textFieldDureeSeance.getText());
			String prenomProf = (String) choiceBoxProf.getValue(); // récupère nom
			String nomCours = (String) choiceBoxCours.getValue(); // récupère nom
			LocalDateTime dateHeureMin = LocalDateTime.of(date, LocalTime.of(heure, minute));

			CoursDAO coursDAO = CoursDAO.getInstance();
			Cours cours = coursDAO.readByName(nomCours);
			
			ProfesseurDAO profDAO = ProfesseurDAO.getInstance();
			InfoProfesseur prof = profDAO.readByPrenom(prenomProf);

			Seance seance = new Seance(dateHeureMin, duree, prof, cours);
			
			SeanceDAO seanceDAO = SeanceDAO.getInstance(); // L'instruction doit être exécutée avant de pouvoir obtenir des résultats. Ceci dit ça marche
			seanceDAO.create(seance);
			

		}
		if(event.getSource() == boutonCreerCours) {
			Cours cours = new Cours(null, null, null);
			String nom = textFieldNomCours.getText();
			String description = textFieldDescCours.getText();

			//récupération de la formule correspondant au cours
			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			Formule formule = formuleDAO.readByTypes("Cours");

			cours.setNom(nom);
			cours.setDescription(description);
			cours.setFormule(formule);

			CoursDAO coursDAO = CoursDAO.getInstance();
			coursDAO.create(cours);

		}
	}

}