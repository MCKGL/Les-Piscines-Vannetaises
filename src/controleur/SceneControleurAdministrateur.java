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

public class SceneControleurAdministrateur extends ControleurInterfaceAdmin {

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

	private void creerFormule() {
		Formule formule = new Formule(null, null, 0, 0, 0, true);
		String label = textFieldLabelForm.getText();
		String type = textFieldTypeCoursForm.getText();
		String prixText = textFieldPrixForm.getText();
		String dureeValText = textFieldDureeValForm.getText();
		String nbEntreeText = textFieldNbEntreeForm.getText();

		if (!label.isEmpty() && !type.isEmpty() && !prixText.isEmpty() && !dureeValText.isEmpty() && !nbEntreeText.isEmpty()) {
			try {
				int prix = Integer.parseInt(prixText);
				int dureeVal = Integer.parseInt(dureeValText);
				int nbEntree = Integer.parseInt(nbEntreeText);

				formule.setLabel(label);
				formule.setType(type);
				formule.setPrixFormule(prix);
				formule.setDureeValidite(dureeVal);
				formule.setNbreEntreeFormule(nbEntree);
				formule.setActive(true);

				FormuleDAO formuleDAO = FormuleDAO.getInstance();
				formuleDAO.create(formule);
			} catch (NumberFormatException e) {
				System.out.println("Les champs de prix, durée de validité et nombre d'entrées doivent contenir uniquement des chiffres");
			}
		}else {
			System.out.println("Veuillez remplir tous les champs");
		}
	}

	private void creerSeance() {
		// erreur : L'instruction doit être exécutée avant de pouvoir obtenir des résultats. L'erreur n'empêche pas la création d'une séance...
		// je n'ai pas trouvé à quoi c'était dû
		Seance seance = new Seance(null, 0, null, null); 
		LocalDate date = datePickerSeance.getValue();
		String heureText = textFieldHeureSeance.getText();
		String minuteText = textFieldMinSeance.getText();
		String dureeText = textFieldDureeSeance.getText();
		String prenomProf = (String) choiceBoxProf.getValue();
		String nomCours = (String) choiceBoxCours.getValue();

		if (date != null && !heureText.isEmpty() && !minuteText.isEmpty() && !dureeText.isEmpty() && prenomProf != null && nomCours != null) {
			try {
				int heure = Integer.parseInt(heureText);
				int minute = Integer.parseInt(minuteText);
				int duree = Integer.parseInt(dureeText);

				LocalDateTime dateHeureMin = LocalDateTime.of(date, LocalTime.of(heure, minute));

				ProfesseurDAO profDAO = ProfesseurDAO.getInstance();
				InfoProfesseur prof = profDAO.readByPrenom(prenomProf);

				CoursDAO coursDAO = CoursDAO.getInstance();
				Cours cours = coursDAO.readByName(nomCours);

				seance.setDate(dateHeureMin);
				seance.setDuree(duree);
				seance.setIdProf(prof);
				seance.setIdCours(cours);

				SeanceDAO seanceDAO = SeanceDAO.getInstance();
				seanceDAO.create(seance);
			} catch (NumberFormatException e) {
				System.out.println("Les champs d'heure, minute et durée doivent contenir uniquement des chiffres");
			}
		} else {
			System.out.println("Veuillez remplir tous les champs");
		}
	}

	private void creerCours() {
		Cours cours = new Cours(null, null, null);
		String nom = textFieldNomCours.getText();
		String description = textFieldDescCours.getText();

		if (!nom.isEmpty() && !description.isEmpty()) {
			//récupération de la formule correspondant au cours
			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			Formule formule = formuleDAO.readByTypes("Cours");

			cours.setNom(nom);
			cours.setDescription(description);
			cours.setFormule(formule);

			CoursDAO coursDAO = CoursDAO.getInstance();
			coursDAO.create(cours);
		} else {
			System.out.println("Veuillez remplir tous les champs");
		}
	}

	// Créer une nouvelle formule / cours / seance (en fonction du bouton cliqué)
	public void creerNouvelleEntree(ActionEvent event) {
		if(event.getSource() == boutonCreerFormule) {
			creerFormule();
		}
		if(event.getSource() == boutonCreerSeance) {
			creerSeance();
		}
		if(event.getSource() == boutonCreerCours) {
			creerCours();
		}
	}

	public void majFormule() {
		Formule formule = tableFormule.getSelectionModel().getSelectedItem();
		String label = textFieldLabelForm.getText();
		String type = textFieldTypeCoursForm.getText();
		String prixText = textFieldPrixForm.getText();
		String dureeValText = textFieldDureeValForm.getText();
		String nbEntreeText = textFieldNbEntreeForm.getText();

		if (!label.isEmpty() && !type.isEmpty() && !prixText.isEmpty() && !dureeValText.isEmpty() && !nbEntreeText.isEmpty()) {
			try {
				int prix = Integer.parseInt(prixText);
				int dureeVal = Integer.parseInt(dureeValText);
				int nbEntree = Integer.parseInt(nbEntreeText);

				formule.setLabel(label);
				formule.setType(type);
				formule.setPrixFormule(prix);
				formule.setDureeValidite(dureeVal);
				formule.setNbreEntreeFormule(nbEntree);
				formule.setActive(true);

				FormuleDAO formuleDAO = FormuleDAO.getInstance();
				formuleDAO.update(formule);
				
				// Mettre à jour le tableau
				int index = formuleData.indexOf(formule);
				if (index != -1) {
					formuleData.set(index, formule);
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Les champs de prix, durée de validité et nombre d'entrées doivent contenir uniquement des chiffres");
			}
		}else {
			System.out.println("Veuillez remplir tous les champs");
		}
	}

	public void majSeance() {
		Seance seance = tableSeance.getSelectionModel().getSelectedItem();
		LocalDate date = datePickerSeance.getValue();
		String heureText = textFieldHeureSeance.getText();
		String minuteText = textFieldMinSeance.getText();
		String dureeText = textFieldDureeSeance.getText();
		String prenomProf = (String) choiceBoxProf.getValue();
		String nomCours = (String) choiceBoxCours.getValue();

		if (date != null && !heureText.isEmpty() && !minuteText.isEmpty() && !dureeText.isEmpty() && prenomProf != null && nomCours != null) {
			try {
				int heure = Integer.parseInt(heureText);
				int minute = Integer.parseInt(minuteText);
				int duree = Integer.parseInt(dureeText);

				LocalDateTime dateHeureMin = LocalDateTime.of(date, LocalTime.of(heure, minute));

				ProfesseurDAO profDAO = ProfesseurDAO.getInstance();
				InfoProfesseur prof = profDAO.readByPrenom(prenomProf);

				CoursDAO coursDAO = CoursDAO.getInstance();
				Cours cours = coursDAO.readByName(nomCours);

				seance.setDate(dateHeureMin);
				seance.setDuree(duree);
				seance.setIdProf(prof);
				seance.setIdCours(cours);

				SeanceDAO seanceDAO = SeanceDAO.getInstance();
				seanceDAO.update(seance);
				
				// Mettre à jour le tableau
				int index = seanceData.indexOf(seance);
				if (index != -1) {
					seanceData.set(index, seance);
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Les champs d'heure, minute et durée doivent contenir uniquement des chiffres");
			}
		} else {
			System.out.println("Veuillez remplir tous les champs");
		}
	}

	public void majCours() {
		Cours cours = tableCours.getSelectionModel().getSelectedItem();
		String nom = textFieldNomCours.getText();
		String description = textFieldDescCours.getText();

		if (!nom.isEmpty() && !description.isEmpty()) {
			//récupération de la formule correspondant au cours
			FormuleDAO formuleDAO = FormuleDAO.getInstance();
			Formule formule = formuleDAO.readByTypes("Cours");

			cours.setNom(nom);
			cours.setDescription(description);
			cours.setFormule(formule);

			CoursDAO coursDAO = CoursDAO.getInstance();
			coursDAO.update(cours);
			
			// Mettre à jour le tableau
			int index = coursData.indexOf(cours);
			if (index != -1) {
				coursData.set(index, cours);
			}
			
		} else {
			System.out.println("Veuillez remplir tous les champs");
		}
	}

	public void mettreAJourSelection(ActionEvent event) {		
		if(event.getSource() == boutonUpdFormule) {
			majFormule();
		}
		if(event.getSource() == boutonUpdSeance) {
			majSeance();
		}
		if(event.getSource() == boutonUpdCours) {
			majCours();
		}
	}

}