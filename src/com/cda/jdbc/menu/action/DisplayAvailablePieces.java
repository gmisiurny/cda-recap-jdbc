package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class DisplayAvailablePieces extends Action {
	private static final int ID = 21;
	private static final String DESC = "Lister le nombre et la somme totale des pi�ces disponibles (pas encore vendues) par mod�le de v�hicule.";

	protected DisplayAvailablePieces() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("");
		// Ecrire le code pour Lister le nombre et la somme totale des pi�ces disponibles(pas encore vendues) par mod�le de v�hicule
		return Boolean.TRUE;
	}
}