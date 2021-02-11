package com.cda.jdbc.exec;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import java.util.TreeMap;
import com.cda.jdbc.ihm.WrongInputException;
import com.cda.jdbc.menu.action.Action;
import com.cda.jdbc.menu.action.IAllActions;

public class Program {
	public static void main(String[] args) {
		TreeMap<Integer, Action> actions = new TreeMap<>();
		addAction(actions, IAllActions.EXIT);
		addAction(actions, IAllActions.CREATE_CATEGORY);
		addAction(actions, IAllActions.READ_CATEGORY);
		addAction(actions, IAllActions.UPDATE_CATEGORY);
		addAction(actions, IAllActions.DELETE_CATEGORY);
		addAction(actions, IAllActions.CREATE_PIECE);
		addAction(actions, IAllActions.READ_PIECE);
		addAction(actions, IAllActions.UPDATE_PIECE);
		addAction(actions, IAllActions.DELETE_PIECE);
		addAction(actions, IAllActions.CREATE_MODEL);
		addAction(actions, IAllActions.READ_MODEL);
		addAction(actions, IAllActions.UPDATE_MODEL);
		addAction(actions, IAllActions.DELETE_MODEL);		
		addAction(actions, IAllActions.CREATE_BRAND);
		addAction(actions, IAllActions.READ_BRAND);
		addAction(actions, IAllActions.UPDATE_BRAND);
		addAction(actions, IAllActions.DELETE_BRAND);		
		addAction(actions, IAllActions.CREATE_VEHICULE);
		addAction(actions, IAllActions.READ_VEHICULE);
		addAction(actions, IAllActions.UPDATE_VEHICULE);
		addAction(actions, IAllActions.DELETE_VEHICULE);
		addAction(actions, IAllActions.SOLD_PIECE);
		addAction(actions, IAllActions.DISPLAY_AVAILABLE_PIECES);
		addAction(actions, IAllActions.ANNUAL_SELLS);
		addAction(actions, IAllActions.MOST_RECENT_VEHICULES);
		boolean isContinue;
		int vActionSaisie = IAllActions.ACTION_NOT_FOUND.getId();
		do {
			IHM_INS.display("\n*******************\nSaisissez une action");
			for (Action action : actions.values()) {
				IHM_INS.display("\t" + action.getId() + ")- " + action.getDescription());
			}
			try {
				vActionSaisie = IHM_INS.readNaturalNb();
			} catch (WrongInputException e) {
				vActionSaisie = IAllActions.ACTION_NOT_FOUND.getId();
			}
			isContinue = actions.getOrDefault(vActionSaisie, IAllActions.ACTION_NOT_FOUND).execute();
		} while (isContinue);
	}

	private static void addAction(TreeMap<Integer, Action> actions, Action action) {
		actions.put(action.getId(), action);
	}
}
