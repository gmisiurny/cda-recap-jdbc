package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.daosql.PieceDAOImpl;

final class DisplayAvailablePiecesPerModel extends Action {
	private static final int ID = 22;
	private static final String DESC = "Lister le nombre et la somme totale des pièces disponibles (pas encore vendues) par modèle de véhicule.";
	private IPieceDAO pieceDAO;

	protected DisplayAvailablePiecesPerModel() {
		super(ID, DESC);
		this.pieceDAO = new PieceDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Voulez-vous exporter le fichier ? \nNon \nExcel \nHtml ");
		String choice = IHM_INS.readWord();
		this.pieceDAO.listAvailablePiecePerModel(choice);
		return Boolean.TRUE;
	}
}
