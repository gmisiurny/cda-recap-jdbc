package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

public class ActionNotFound extends Action{
	private static final int ID = 99;
	private static final String DESC = "Action introuvable";
	
	ActionNotFound() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("> Votre saisie est incorrecte.");
		return Boolean.TRUE;
	}
}
