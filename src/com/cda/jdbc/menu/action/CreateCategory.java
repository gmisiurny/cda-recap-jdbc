package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import com.cda.jdbc.dao.ICategoryDAO;
import com.cda.jdbc.daosql.CategoryDAOImpl;
import com.cda.jdbc.data.Category;
import com.cda.jdbc.ihm.Ihm;

final class CreateCategory extends Action {
	private static final int ID = 1;
	private static final String DESC = "Cr�er une cat�gorie de pi�ce";
	private ICategoryDAO categoryDAO;
	
	protected CreateCategory() {
		super(ID, DESC);
		this.categoryDAO = new CategoryDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la cat�gorie de la pi�ce � ajouter ?");
		String label = Ihm.IHM_INS.readWord();
		Category category = new Category(label);
		this.categoryDAO.save(category);
		return Boolean.TRUE;
	}
}