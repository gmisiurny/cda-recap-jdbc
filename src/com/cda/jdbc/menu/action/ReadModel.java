package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import com.cda.jdbc.dao.IModelDAO;
import com.cda.jdbc.daosql.ModelDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class ReadModel extends Action{
	private static final int ID = 10;
	private static final String DESC = "Voir un modéle de voiture";
	private IModelDAO modelDAO;
	
	protected ReadModel() {
		super(ID, DESC);
		this.modelDAO = new ModelDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à consulter ?");
		String label = Ihm.IHM_INS.readWord();		
		System.out.println((this.modelDAO.findByName(label)));
		return Boolean.TRUE;
	}
}
