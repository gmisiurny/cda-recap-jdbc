package com.cda.jdbc.menu.action;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import java.util.List;

import com.cda.jdbc.dao.IAnnualSalesDAO;
import com.cda.jdbc.daosql.AnnualSalesDAOImpl;
import com.cda.jdbc.data.AnnualSales;


final class AnnualSells extends Action {
	private static final int ID = 23;
	private static final String DESC = "Les chiffres d'affaires annuels listés par année, en décroissant par année";
	private IAnnualSalesDAO annualSalesDAO;

	protected AnnualSells() {
		super(ID, DESC);
		this.annualSalesDAO=new AnnualSalesDAOImpl();
	}

	@Override
	public boolean execute() {
		IHM_INS.display("Chiffre d'affaire par année");
		List<AnnualSales> allSales = annualSalesDAO.getAllSalesPerYear();
		if (allSales.isEmpty()) {
			System.out.println("Aucun pays dans la BDD");
		} else {
			allSales.stream().forEach(e -> {
				System.out.println("  >  " + e);
			});
		}
		return Boolean.TRUE;
	}
}
