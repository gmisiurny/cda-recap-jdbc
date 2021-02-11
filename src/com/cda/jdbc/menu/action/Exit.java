package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

final class Exit extends Action{
	private static final int ID = 0;
	private static final String DESC = "Quitter le programme";
	
	protected Exit() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("A bientôt !");
		return Boolean.FALSE;
	}
}
