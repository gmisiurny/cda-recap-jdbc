package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IBrandDAO;
import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.daosql.BrandDAOImpl;
import com.cda.jdbc.data.Brand;
import com.cda.jdbc.ihm.Ihm;

final class ReadBrand extends Action {
	private static final int ID = 14;
	private static final String DESC = "Consulter une marque de voiture";
	private IBrandDAO brandDAO;
	
	protected ReadBrand() {
		super(ID, DESC);
		this.brandDAO = new BrandDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à consulter ?");
		String label = Ihm.IHM_INS.readWord();		
		System.out.println((this.brandDAO.findByName(label)));
		return Boolean.TRUE;
	}
}
