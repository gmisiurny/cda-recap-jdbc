package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cda.jdbc.dao.IBrandDAO;
import com.cda.jdbc.dao.IModelDAO;
import com.cda.jdbc.dao.IVehicleDAO;
import com.cda.jdbc.daosql.BrandDAOImpl;
import com.cda.jdbc.daosql.ModelDAOImpl;
import com.cda.jdbc.daosql.VehicleDAOImpl;
import com.cda.jdbc.data.Vehicle;
import com.cda.jdbc.ihm.Ihm;
import com.cda.jdbc.ihm.WrongInputException;

final class HandleVehicule extends Action {
	private static final int ID = 2;
	private static final String DESC = "Gérer les véhicules";
	private IVehicleDAO vehicleDAO;
	private IModelDAO modelDAO;
	private IBrandDAO brandDAO;
	private static final Logger logger = LoggerFactory.getLogger(HandleVehicule.class);
	
	protected HandleVehicule() {
		super(ID, DESC);
		this.vehicleDAO=new VehicleDAOImpl();
		this.modelDAO=new ModelDAOImpl();
		this.brandDAO=new BrandDAOImpl();
	}

	@Override
	public boolean execute(){
		IHM_INS.display("Quelle action voulez-vous effectuer ?");
		IHM_INS.display("1) Créer un véhicule");
		IHM_INS.display("2) Lire un véhicule");
		IHM_INS.display("3) Supprimer un véhicule");
		IHM_INS.display("4) Revenir au menu précédent");
		int choice = 0;
		try {
			choice = IHM_INS.readNaturalNb();
		} catch (WrongInputException e) {
			logger.error("Erreur " + e);
			IHM_INS.display("Mauvaise saisie !");
		}
		switch (choice) {
		case 1:
			create();
			break;
		case 2:
			IHM_INS.display("Comment s'appelle le véhicule à consulter ?");
			String label = Ihm.IHM_INS.readWord();
			System.out.println(this.vehicleDAO.findByName(label));
			break;
		case 3:
			IHM_INS.display("Comment s'appelle le véhicule à supprimer ?");
			String label02 = Ihm.IHM_INS.readWord();
			this.vehicleDAO.remove(label02);
			break;
		default:
			break;
		}
		return Boolean.TRUE;
	}

	private void create() {
		IHM_INS.display("Quelle est la plaque d'immatriculation du véhicule ?");
		String numberPlate = Ihm.IHM_INS.readWord();
		int yearProduct;
		do {
			IHM_INS.display("Quelle est l'année de construction du véhicule ?");
			try {
				yearProduct = Ihm.IHM_INS.readNaturalNb();
			} catch (WrongInputException e) {
				yearProduct = 0;
				IHM_INS.display("Mauvaise saisie");
				logger.error("erreur"+e);
			}
		} while(yearProduct < 1);
		IHM_INS.display("Quelle est le modèle du véhicule ?");
		String labelModel = Ihm.IHM_INS.readWord();
		int idModel = modelDAO.getId(labelModel);
		IHM_INS.display("Quelle est la marque du véhicule ?");
		String labelBrand = Ihm.IHM_INS.readWord();
		int idBrand = brandDAO.getId(labelBrand);
		Vehicle vehicle = new Vehicle(numberPlate,yearProduct,idModel,idBrand);
		this.vehicleDAO.save(vehicle);
	}
}
