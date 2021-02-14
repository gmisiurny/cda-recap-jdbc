package com.cda.jdbc.exec;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.ihm.WrongInputException;
import com.cda.jdbc.menu.action.Action;
import com.cda.jdbc.menu.action.IAllActions;

public class Program {
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	public static void main(String[] args) {
		TreeMap<Integer, Action> actions = new TreeMap<>();
		addAction(actions, IAllActions.EXIT);
		addAction(actions, IAllActions.HANDLE_PIECE);	
		addAction(actions, IAllActions.HANDLE_VEHICULE);
		addAction(actions, IAllActions.HANDLE_CATEGORY);
		addAction(actions, IAllActions.HANDLE_BRAND);	
		addAction(actions, IAllActions.HANDLE_MODEL);
		addAction(actions, IAllActions.SOLD_PIECE);
		addAction(actions, IAllActions.DISPLAY_AVAILABLE_PIECES_PER_MODEL);
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
				logger.error("Erreur: " + e);
				vActionSaisie = IAllActions.ACTION_NOT_FOUND.getId();
			}
			isContinue = actions.getOrDefault(vActionSaisie, IAllActions.ACTION_NOT_FOUND).execute();
		} while (isContinue);
	}

	private static void addAction(TreeMap<Integer, Action> actions, Action action) {
		actions.put(action.getId(), action);
	}
}