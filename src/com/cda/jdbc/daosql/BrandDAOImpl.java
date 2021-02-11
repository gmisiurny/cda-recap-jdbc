package com.cda.jdbc.daosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IBrandDAO;
import com.cda.jdbc.data.Brand;
import com.cda.jdbc.ihm.Ihm;

public class BrandDAOImpl implements IBrandDAO {
	private static final Logger logger = LoggerFactory.getLogger(BrandDAOImpl.class);
	@Override
	public Brand save(Brand brand) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				String request = "SELECT * FROM Brand WHERE label=?;";
				PreparedStatement prepStatement = c.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
				prepStatement.setString(1, brand.getLabel());
				ResultSet result = prepStatement.executeQuery();
				if (result.next() == false) {
					PreparedStatement ps = c.prepareStatement("insert into Brand (label) values (?); ",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, brand.getLabel());
					ps.executeUpdate();
					ResultSet resultat = ps.getGeneratedKeys();
					if (resultat.next()) {
						logger.info("Marque créée !");
						brand.setId(resultat.getInt(1));
						return brand;
					}
				} else {
					logger.warn("Marque déjà dans la BDD");
					Ihm.IHM_INS.display("Marque déjà dans la BDD");
				}
			} catch (SQLException e) {
				logger.error("erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de l'insertion en BDD");
			}
		}
		return null;
	}

	@Override
	public List<Brand> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String nomOuID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String nomOuID) {
		// TODO Auto-generated method stub

	}

	@Override
	public Brand findByName(String label) {
		Connection c = MyConnection.getConnection();
		Brand brand = new Brand();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Brand WHERE label=?;",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("Marque consultée");
					brand.setId(resultat.getInt(1));
					brand.setLabel(resultat.getString(2));
					return brand;
				}
			
			} catch (SQLException e) {
				logger.error("erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de l'affichage des marques");
			}
		}
		return brand;
	}
}
