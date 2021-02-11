package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.ICategoryDAO;
import com.cda.jdbc.daosql.CategoryDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class DeleteCategory extends Action {
	private static final int ID = 4;
	private static final String DESC = "Supprimer une catégorie de pièce";
	private ICategoryDAO categoryDAO;
	
	protected DeleteCategory() {
		super(ID, DESC);
		this.categoryDAO = new CategoryDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la catégorie de la pièce à supprimer ?");
		String label = Ihm.IHM_INS.readWord();
		this.categoryDAO.remove(label);
		return Boolean.TRUE;
	}
}
