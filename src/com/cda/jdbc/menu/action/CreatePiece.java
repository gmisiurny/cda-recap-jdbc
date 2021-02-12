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

final class CreatePiece extends Action {
	private static final int ID = 5;
	private static final String DESC = "Créer une pièce";
	private IPieceDAO pieceDAO;
	private IReferenceDAO refDAO;
	private ICategoryDAO categoryDAO;
	private static final Logger logger = LoggerFactory.getLogger(CreatePiece.class);
	
	protected CreatePiece() {
		super(ID, DESC);
		this.pieceDAO = new PieceDAOImpl();
		this.refDAO = new RefDAOImpl();
		this.categoryDAO = new CategoryDAOImpl();
	}

	@Override
	public boolean execute() {
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
		return Boolean.TRUE;
	}
}
