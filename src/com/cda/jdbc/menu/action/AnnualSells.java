package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class AnnualSells extends Action {
	private static final int ID = 23;
	private static final String DESC = "Les chiffres d'affaires annuels listés par année, en décroissant par année";

	protected AnnualSells() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("");
		// Les chiffres d'affaires annuels listés par année, en décroissant par année
		return Boolean.TRUE;
	}
}
