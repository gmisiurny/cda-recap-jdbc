package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IVehicleDAO;
import com.cda.jdbc.daosql.VehicleDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class DeleteVehicule extends Action {
	private static final int ID = 20;
	private static final String DESC = "Supprimer un véhicule";
	private IVehicleDAO vehicleDAO;
	
	protected DeleteVehicule() {
		super(ID, DESC);
		vehicleDAO = new VehicleDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le véhicule à supprimer ?");
		String label = Ihm.IHM_INS.readWord();
		this.vehicleDAO.remove(label);
		return Boolean.TRUE;
	}
}
