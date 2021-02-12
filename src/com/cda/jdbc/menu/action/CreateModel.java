package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;
import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.data.Model;
import com.cda.jdbc.ihm.Ihm;

final class CreateModel extends Action {
	private static final int ID = 9;
	private static final String DESC = "Créer un modéle de voiture";
	private IDAO<Model> brandDAO;
	
	protected CreateModel() {
		super(ID, DESC);
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Comment s'appelle le modèle à créer ?");
		String label = Ihm.IHM_INS.readWord();
		Model model = new Model(label);
		this.brandDAO.save(model);
		return Boolean.TRUE;
	}
}
