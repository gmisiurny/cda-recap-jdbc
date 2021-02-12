package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class AnnualSells extends Action {
	private static final int ID = 23;
	private static final String DESC = "Les chiffres d'affaires annuels list�s par ann�e, en d�croissant par ann�e";

	protected AnnualSells() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("");
		// Les chiffres d'affaires annuels list�s par ann�e, en d�croissant par ann�e
		return Boolean.TRUE;
	}
}
