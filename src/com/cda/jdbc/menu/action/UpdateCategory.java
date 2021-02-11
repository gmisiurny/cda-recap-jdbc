package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.ICategoryDAO;
import com.cda.jdbc.daosql.CategoryDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class UpdateCategory extends Action {
	private static final int ID = 3;
	private static final String DESC = "Éditer une catégorie de pièce";
	private ICategoryDAO categoryDAO;
	
	protected UpdateCategory() {
		super(ID, DESC);
		this.categoryDAO = new CategoryDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la catégorie dont le nom est à modifier ?");
		String ancienLabel = Ihm.IHM_INS.readWord();
		IHM_INS.display("Par quel nom doit-on le modifier ?");
		String nouveauLabel = Ihm.IHM_INS.readWord();
		this.categoryDAO.update(ancienLabel,nouveauLabel);
		return Boolean.TRUE;
	}
}
