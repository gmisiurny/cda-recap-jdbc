package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.daosql.BrandDAOImpl;
import com.cda.jdbc.data.Brand;
import com.cda.jdbc.ihm.Ihm;
import com.cda.jdbc.ihm.WrongInputException;

final class HandleBrand extends Action {
	private static final int ID = 4;
	private static final String DESC = "Gérer les marques de voiture";
	private IDAO<Brand> brandDAO;
	private static final Logger logger = LoggerFactory.getLogger(HandleBrand.class);
	
	protected HandleBrand() {
		super(ID, DESC);
		this.brandDAO = new BrandDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Quelle action voulez-vous effectuer ?");
		IHM_INS.display("1) Créer une marque");
		IHM_INS.display("2) Lire une marque");
		IHM_INS.display("3) Éditer une marque");
		IHM_INS.display("4) Supprimer une marque");
		IHM_INS.display("5) Revenir au menu marque");
		int choice = 0;
		try {
			choice = IHM_INS.readNaturalNb();
		} catch (WrongInputException e) {
			logger.error("Erreur " + e);
			IHM_INS.display("Mauvaise saisie !");
		}
		switch (choice) {
		case 1:
			Ihm.IHM_INS.display("Quelle est le nom de la marque ?");
			String label = IHM_INS.readWord();
			Brand brand = new Brand(label);
			this.brandDAO.save(brand);
			break;
		case 2:
			IHM_INS.display("Comment s'appelle la marque à consulter ?");
			String label02 = IHM_INS.readWord();		
			System.out.println((this.brandDAO.findByName(label02)));
			break;
		case 3:
			IHM_INS.display("Comment s'appelle la marque à éditer ?");
			String oldLabel = IHM_INS.readWord();
			IHM_INS.display("Comment voulez-vous la renommer ?");
			String newLabel = IHM_INS.readWord();
			this.brandDAO.update(oldLabel, newLabel);
			break;
		case 4:
			IHM_INS.display("Comment s'appelle la marque à supprimer ?");
			String label03 = IHM_INS.readWord();
			this.brandDAO.remove(label03);
			break;
		default:
			break;
		}
		return Boolean.TRUE;
	}
}
