package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.daosql.PieceDAOImpl;
import com.cda.jdbc.ihm.WrongInputException;

final class UpdatePiece extends Action {
	private static final int ID = 7;
	private static final String DESC = "Éditer une pièce";
	private IPieceDAO pieceDAO;
	private static final Logger logger = LoggerFactory.getLogger(UpdatePiece.class);

	protected UpdatePiece() {
		super(ID, DESC);
		pieceDAO = new PieceDAOImpl();
	}

	@Override
	public boolean execute() {
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
		return Boolean.TRUE;
	}
}
