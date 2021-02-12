package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import com.cda.jdbc.dao.IModelDAO;
import com.cda.jdbc.daosql.ModelDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class UpdateModel extends Action {
	private static final int ID = 11;
	private static final String DESC = "Éditer un modéle de voiture";
	private IModelDAO modelDAO;
	
	protected UpdateModel() {
		super(ID, DESC);
		this.modelDAO = new ModelDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à éditer ?");
		String oldLabel = Ihm.IHM_INS.readWord();
		IHM_INS.display("Comment voulez-vous la renommer ?");
		String newLabel = Ihm.IHM_INS.readWord();
		this.modelDAO.update(oldLabel, newLabel);
		return Boolean.TRUE;
	}
}
