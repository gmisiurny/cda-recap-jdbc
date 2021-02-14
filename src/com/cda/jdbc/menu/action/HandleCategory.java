package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cda.jdbc.dao.ICategoryDAO;
import com.cda.jdbc.daosql.CategoryDAOImpl;
import com.cda.jdbc.data.Category;
import com.cda.jdbc.ihm.Ihm;
import com.cda.jdbc.ihm.WrongInputException;

final class HandleCategory extends Action {
	private static final int ID = 3;
	private static final String DESC = "Gérer les catégories de pièce";
	private ICategoryDAO categoryDAO;
	private static final Logger logger = LoggerFactory.getLogger(HandleCategory.class);
	
	protected HandleCategory() {
		super(ID, DESC);
		this.categoryDAO = new CategoryDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Quelle action voulez-vous effectuer ?");
		IHM_INS.display("1) Créer une catégorie");
		IHM_INS.display("2) Lire une catégorie");
		IHM_INS.display("3) Éditer une catégorie");
		IHM_INS.display("4) Supprimer une catégorie");
		IHM_INS.display("5) Revenir au menu précédent");
		int choice = 0;
		try {
			choice = IHM_INS.readNaturalNb();
		} catch (WrongInputException e) {
			logger.error("Erreur " + e);
			IHM_INS.display("Mauvaise saisie !");
		}
		switch (choice) {
		case 1:
			IHM_INS.display("Comment s'appelle la catégorie à ajouter ?");
			String label = Ihm.IHM_INS.readWord();
			Category category = new Category(label);
			this.categoryDAO.save(category);
			break;
		case 2:
			IHM_INS.display("Comment s'appelle la catégorie de la pièce à consulter ?");
			String label02 = Ihm.IHM_INS.readWord();
			System.out.println(this.categoryDAO.findByName(label02));
			break;
		case 3:
			IHM_INS.display("Comment s'appelle la catégorie dont le nom est à modifier ?");
			String ancienLabel = Ihm.IHM_INS.readWord();
			IHM_INS.display("Par quel nom doit-on le modifier ?");
			String nouveauLabel = Ihm.IHM_INS.readWord();
			this.categoryDAO.update(ancienLabel,nouveauLabel);
			break;
		case 4:
			IHM_INS.display("Comment s'appelle la catégorie de la pièce à supprimer ?");
			String label03 = Ihm.IHM_INS.readWord();
			this.categoryDAO.remove(label03);
			break;
		default:
			break;
		}
		return Boolean.TRUE;
	}
}