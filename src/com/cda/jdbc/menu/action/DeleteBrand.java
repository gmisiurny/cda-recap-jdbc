package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.daosql.BrandDAOImpl;
import com.cda.jdbc.data.Brand;
import com.cda.jdbc.ihm.Ihm;

final class DeleteBrand extends Action {
	private static final int ID = 16;
	private static final String DESC = "Supprimer une marque de voiture";
	private IDAO<Brand> brandDAO;
	
	protected DeleteBrand() {
		super(ID, DESC);
		this.brandDAO = new BrandDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle la marque à supprimer ?");
		String label = Ihm.IHM_INS.readWord();
		this.brandDAO.remove(label);
		return Boolean.TRUE;
	}
}
