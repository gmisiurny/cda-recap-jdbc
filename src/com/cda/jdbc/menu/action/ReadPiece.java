package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.daosql.PieceDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class ReadPiece extends Action {
	private static final int ID = 6;
	private static final String DESC = "Voir une pièce";
	private IPieceDAO pieceDAO;
	
	protected ReadPiece() {
		super(ID, DESC);
		this.pieceDAO = new PieceDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la pièce à consulter ?");
		String label = Ihm.IHM_INS.readSentence();
		IHM_INS.display(this.pieceDAO.findByName(label).toString());
		return Boolean.TRUE;
	}
}
