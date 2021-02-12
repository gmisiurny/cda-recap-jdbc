package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.daosql.PieceDAOImpl;


final class DeletePiece extends Action {
	private static final int ID = 8;
	private static final String DESC = "Supprimer une pièce";
	private IPieceDAO pieceDAO;

	protected DeletePiece() {
		super(ID, DESC);
		this.pieceDAO = new PieceDAOImpl();
	}

	@Override
	public boolean execute() {
		String label = "";
		do {
			IHM_INS.display("Comment s'appelle la pièce à Supprimer ?");
			label = IHM_INS.readSentence();
		} while (!this.pieceDAO.isInDatabase(label));
		this.pieceDAO.remove(label);
		return Boolean.TRUE;
	}
}
