package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.ICategoryDAO;
import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.dao.IReferenceDAO;
import com.cda.jdbc.daosql.CategoryDAOImpl;
import com.cda.jdbc.daosql.PieceDAOImpl;
import com.cda.jdbc.daosql.RefDAOImpl;
import com.cda.jdbc.data.Piece;
import com.cda.jdbc.ihm.Ihm;
import com.cda.jdbc.ihm.WrongInputException;
import org.slf4j.Logger;

final class HandlePiece extends Action {
	private static final int ID = 1;
	private static final String DESC = "Gérer les pièces";
	private IPieceDAO pieceDAO;
	private IReferenceDAO refDAO;
	private ICategoryDAO categoryDAO;
	private static final Logger logger = LoggerFactory.getLogger(HandlePiece.class);

	protected HandlePiece() {
		super(ID, DESC);
		this.pieceDAO = new PieceDAOImpl();
		this.refDAO = new RefDAOImpl();
		this.categoryDAO = new CategoryDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Quelle action voulez-vous effectuer ?");
		IHM_INS.display("1) Créer une pièce");
		IHM_INS.display("2) Lire une pièce");
		IHM_INS.display("3) Éditer une pièce");
		IHM_INS.display("4) Supprimer une pièce");
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
			create();
			break;
		case 2:
			IHM_INS.display("Comment s'appelle la pièce à consulter ?");
			String label = Ihm.IHM_INS.readSentence();
			IHM_INS.display(this.pieceDAO.findByName(label).toString());
			break;
		case 3:
			update();
			break;
		case 4:
			String label02 = "";
			do {
				IHM_INS.display("Comment s'appelle la pièce à Supprimer ?");
				label02 = IHM_INS.readSentence();
			} while (!this.pieceDAO.isInDatabase(label02));
			this.pieceDAO.remove(label02);
			break;
		default:
			break;
		}
		return Boolean.TRUE;
	}

	private void update() {
		String oldLabel = "";
		int choice;
		float price;
		String newLabel = "";
		do {
			IHM_INS.display("Comment s'appelle la pièce à éditer ?");
			oldLabel = IHM_INS.readSentence();
		} while (!this.pieceDAO.isInDatabase(oldLabel));
		do {
			IHM_INS.display("Voulez-vous éditer le label (1), le prix (2) ou les deux (3) ?");
			try {
				choice = IHM_INS.readNaturalNb();
			} catch (WrongInputException e) {
				choice = 0;
				IHM_INS.display("Mauvaise saisie !");
				logger.error("Choix non valide", e);
			}
		} while (choice != 1 && choice != 2 && choice != 3);
		switch (choice) {
		case 1:

			do {
				IHM_INS.display("Comment voulez-vous la renommer ?");
				newLabel = IHM_INS.readSentence();
				if (this.pieceDAO.isInDatabase(newLabel)) {
					IHM_INS.display("Une pièce porte déjà ce nom !");
				}
			} while (this.pieceDAO.isInDatabase(newLabel));
			this.pieceDAO.update(oldLabel, newLabel);
			break;
		case 2:
			do {
				IHM_INS.display("Quel est le nouveau prix ?");
				try {
					price = IHM_INS.readFloatNb();
				} catch (WrongInputException e) {
					price = -1;
					IHM_INS.display("Mauvaise saisie !");
					logger.error("Mauvaise saisie utilisateur", e);
				}
			} while (price < 0);
			this.pieceDAO.updatePrice(oldLabel, price);
			break;
		case 3:
			do {
				IHM_INS.display("Comment voulez-vous la renommer ?");
				newLabel = IHM_INS.readSentence();
				if (this.pieceDAO.isInDatabase(newLabel)) {
					IHM_INS.display("Une pièce porte déjà ce nom !");
				}
			} while (this.pieceDAO.isInDatabase(newLabel));
			do {
				IHM_INS.display("Quel est le nouveau prix ?");
				try {
					price = IHM_INS.readFloatNb();
				} catch (WrongInputException e) {
					price = -1;
					IHM_INS.display("Mauvaise saisie !");
					logger.error("Mauvaise saisie utilisateur", e);
				}
			} while (price < 0);
			this.pieceDAO.updateLabelAndPrice(oldLabel, newLabel, price);
			break;
		default:
			break;
		}
	}

	private void create() {
		IHM_INS.display("Comment s'appelle la pièce à créer ?");
		String label = Ihm.IHM_INS.readSentence();
		if (!this.pieceDAO.isInDatabase(label)) {
			float price;
			do {
				IHM_INS.display("Quel est le prix de la pièce ? (Valeur positive)");
				try {
					price = Ihm.IHM_INS.readFloatNb();
				} catch (WrongInputException e) {
					price = 0;
					IHM_INS.display("Mauvaise saisie !");
					logger.error("Mauvaise saisie utilisateur", e);
				}
			} while (price < 1);
			String ref = "";
			do {
				IHM_INS.display("Quel est la reference associée ?");
				ref = Ihm.IHM_INS.readWord();
			} while (!this.refDAO.isInDatabase(ref));
			int idRef = refDAO.getId(ref);
			String category = "";
			do {
				IHM_INS.display("Quel est la categorie associée ?");
				category = Ihm.IHM_INS.readWord();
			} while (!this.categoryDAO.isInDatabase(category));
			int idCategory = categoryDAO.getId(category);
			Piece piece = new Piece(label, price, idCategory, idRef);
			this.pieceDAO.save(piece);
		} else {
			IHM_INS.display("Cette pièce existe déjà !");
		}
	}
}
