package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class UpdateVehicule extends Action {
	private static final int ID = 18;
	private static final String DESC = "�diter un v�hicule";
	
	protected UpdateVehicule() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le v�hicule � �diter ?");
		// Ecrire le code pour �diter le v�hicule
		return Boolean.TRUE;
	}
}
