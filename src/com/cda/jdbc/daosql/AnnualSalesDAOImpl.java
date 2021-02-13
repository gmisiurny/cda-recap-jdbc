package com.cda.jdbc.daosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cda.jdbc.dao.IAnnualSalesDAO;
import com.cda.jdbc.data.AnnualSales;

public class AnnualSalesDAOImpl implements IAnnualSalesDAO {
	private static final Logger logger = LoggerFactory.getLogger("data.PaysDAOImpl");

	@Override
	public List<AnnualSales> getAllSalesPerYear() {
		List<AnnualSales> annualSales = new ArrayList<>();
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT YEAR(pv.sellDate)AS ANNEE_VENTE, sum(pv.soldQuantity*piece.price) AS CA\r\n"
						+ "FROM piece_vehicule pv\r\n"
						+ "INNER JOIN piece ON pv.idPiece = piece.idPiece\r\n"
						+ "GROUP BY ANNEE_VENTE ORDER BY ANNEE_VENTE DESC");
				ResultSet result = ps.executeQuery();
				logger.info("Ajout des pays à la liste");
				while (result.next()) {
					annualSales.add(new AnnualSales(result.getInt("ANNEE_VENTE"), result.getFloat("CA")));
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return annualSales;
	}
	
}
