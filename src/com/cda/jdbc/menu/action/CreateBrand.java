package com.cda.jdbc.menu.action;

import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.daosql.BrandDAOImpl;
import com.cda.jdbc.data.Brand;
import com.cda.jdbc.ihm.Ihm;

final class CreateBrand extends Action {
	private static final int ID = 13;
	private static final String DESC = "Cr�er une marque de voiture";
	private IDAO<Brand> brandDAO;
	
	protected CreateBrand() {
		super(ID, DESC);
		this.brandDAO = new BrandDAOImpl();
	}

	@Override
	public boolean execute() {
<<<<<<< HEAD
		Ihm.IHM_INS.display("Quelle est le nom de la marque ?");
		String label = Ihm.IHM_INS.readWord();
		Brand brand = new Brand(label);
		this.brandDAO.save(brand);
=======
		IHM_INS.display("Comment s'appelle la mod�le � cr�er ?");
		// Ecrire le code pour cr�er la marque de voiture
>>>>>>> 1f1b0bb (Partie Category OK)
		return Boolean.TRUE;
	}
}
