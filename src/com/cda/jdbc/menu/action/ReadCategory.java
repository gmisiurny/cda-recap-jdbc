package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.ICategoryDAO;
import com.cda.jdbc.daosql.CategoryDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class ReadCategory extends Action {
	private static final int ID = 2;
	private static final String DESC = "Voir une catégorie de pièce";
	private ICategoryDAO categoryDAO;
	
	protected ReadCategory() {
		super(ID, DESC);
		this.categoryDAO = new CategoryDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la catégorie de la pièce à consulter ?");
		String label = Ihm.IHM_INS.readWord();
		System.out.println(this.categoryDAO.findByName(label));
		return Boolean.TRUE;
	}
}
