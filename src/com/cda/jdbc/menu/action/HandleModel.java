package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.daosql.ModelDAOImpl;
import com.cda.jdbc.data.Model;
import com.cda.jdbc.ihm.Ihm;
import com.cda.jdbc.ihm.WrongInputException;

final class HandleModel extends Action {
	private static final int ID = 5;
	private static final String DESC = "Gérer les modéles de voiture";
	private IDAO<Model> modelDAO;
	private static final Logger logger = LoggerFactory.getLogger(HandleModel.class);
	
	protected HandleModel() {
		super(ID, DESC);
		this.modelDAO = new ModelDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Quelle action voulez-vous effectuer ?");
		IHM_INS.display("1) Créer un modele");
		IHM_INS.display("2) Lire un modele");
		IHM_INS.display("3) Éditer un modele");
		IHM_INS.display("4) Supprimer un modele");
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
			IHM_INS.display("Comment s'appelle le modèle à créer ?");
			String label = Ihm.IHM_INS.readWord();
			Model model = new Model(label);
			this.modelDAO.save(model);
			break;
		case 2:
			IHM_INS.display("Comment s'appelle le modèle à consulter ?");
			String label02 = Ihm.IHM_INS.readWord();		
			System.out.println((this.modelDAO.findByName(label02)));
			break;
		case 3:
			IHM_INS.display("Comment s'appelle le modèle à éditer ?");
			String oldLabel = Ihm.IHM_INS.readWord();
			IHM_INS.display("Comment voulez-vous la renommer ?");
			String newLabel = Ihm.IHM_INS.readWord();
			this.modelDAO.update(oldLabel, newLabel);
			break;
		case 4:
			IHM_INS.display("Comment s'appelle le modèle à supprimer ?");
			String label03 = IHM_INS.readWord();
			this.modelDAO.remove(label03);
			break;
		default:
			break;
		}
		return Boolean.TRUE;
	}
}
