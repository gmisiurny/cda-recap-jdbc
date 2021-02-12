package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class MostRecentVehicules extends Action {
	private static final int ID = 24;
	private static final String DESC = "Les 3 voitures les plus r�centes avec pour chaque voiture : le nombre des pi�ces (disponibles) par cat�gorie.";

	protected MostRecentVehicules() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("");
		// Les 3 voitures les plus r�centes avec pour chaque voiture : le nombre de pi�ces (disponibles) par cat�gorie.
		return Boolean.TRUE;
	}
}
