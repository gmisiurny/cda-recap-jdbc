package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import com.cda.jdbc.dao.IVehicleDAO;
import com.cda.jdbc.daosql.VehicleDAOImpl;
import com.cda.jdbc.ihm.Ihm;

final class ReadVehicule extends Action {
	private static final int ID = 18;
	private static final String DESC = "Consulter un véhicule";
	private IVehicleDAO vehicleDAO;
	
	protected ReadVehicule() {
		super(ID, DESC);
		this.vehicleDAO = new VehicleDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le véhicule à consulter ?");
		String label = Ihm.IHM_INS.readWord();
		System.out.println(this.vehicleDAO.findByName(label));
		return Boolean.TRUE;
	}
}
