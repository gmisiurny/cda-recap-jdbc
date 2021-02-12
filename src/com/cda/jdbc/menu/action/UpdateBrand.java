package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.daosql.BrandDAOImpl;
import com.cda.jdbc.data.Brand;
import com.cda.jdbc.ihm.Ihm;

final class UpdateBrand extends Action {
	private static final int ID = 15;
	private static final String DESC = "Éditer une marque de voiture";
	private IDAO<Brand> brandDAO;
	
	protected UpdateBrand() {
		super(ID, DESC);
		this.brandDAO = new BrandDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la marque à éditer ?");
		String oldLabel = Ihm.IHM_INS.readWord();
		IHM_INS.display("Comment voulez-vous la renommer ?");
		String newLabel = Ihm.IHM_INS.readWord();
		this.brandDAO.update(oldLabel, newLabel);
		return Boolean.TRUE;
	}
}
