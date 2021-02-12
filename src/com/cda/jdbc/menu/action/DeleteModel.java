package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.daosql.ModelDAOImpl;
import com.cda.jdbc.data.Model;
import com.cda.jdbc.ihm.Ihm;

final class DeleteModel extends Action {
	private static final int ID = 12;
	private static final String DESC = "Supprimer un mod�le de voiture";
	private IDAO<Model> modelDAO;
	
	protected DeleteModel() {
		super(ID, DESC);
		this.modelDAO = new ModelDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le mod�le � supprimer ?");
		String label = Ihm.IHM_INS.readWord();
		this.modelDAO.remove(label);
		return Boolean.TRUE;
	}
}
