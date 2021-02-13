package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IVehicleDAO;
import com.cda.jdbc.daosql.VehicleDAOImpl;

final class MostRecentVehicules extends Action {
	private static final int ID = 24;
	private static final String DESC = "Les 3 voitures les plus r�centes avec pour chaque voiture : le nombre des pi�ces (disponibles) par cat�gorie.";
	private IVehicleDAO vehiculeDAO;

	protected MostRecentVehicules() {
		super(ID, DESC);
		this.vehiculeDAO = new VehicleDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Les 3 voitures les plus r�centes avec pour chaque voiture : le nombre de pi�ces (disponibles) par cat�gorie.");
		this.vehiculeDAO.piecesPerCategoryForMostRecentVehicules();		
		return Boolean.TRUE;
	}
}
