package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class CreateVehicule extends Action {
	private static final int ID = 17;
	private static final String DESC = "Cr�er un v�hicule";
	
	protected CreateVehicule() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le v�hicule � cr�er ?");
		// Ecrire le code pour cr�er
		return Boolean.TRUE;
	}
}
