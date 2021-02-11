package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class DeleteVehicule extends Action {
	private static final int ID = 19;
	private static final String DESC = "Supprimer un v�hicule";
	
	protected DeleteVehicule() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le v�hicule � supprimer ?");
		// Ecrire le code pour supprimer le v�hicule
		return Boolean.TRUE;
	}
}
