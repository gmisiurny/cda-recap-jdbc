package com.cda.jdbc.daosql;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
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
		List<Brand> brand = new ArrayList<>();
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Brand");
				ResultSet result = ps.executeQuery();
				logger.info("Ajout des marques à la liste");
				while (result.next()) {
					brand.add(new Brand(result.getInt("id"), result.getString("label")));
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				IHM_INS.display("Une erreur est survenue !");
			}
		}
		return brand;
	}

	@Override
	public void remove(String label) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Brand WHERE label = ?;");
				ps.setString(1, label);
				ResultSet result = ps.executeQuery();
				if (!result.next()) {
					logger.warn("La catégorie " + label + " n'existe pas");
					IHM_INS.display("La catégorie " + label + " n'existe pas");
				} else {
					try {
						ps = c.prepareStatement("DELETE FROM Brand WHERE label =?;");
						ps.setString(1, label);
						ps.executeUpdate();
						logger.info("Suppression de la marque " + label + " dans la table Brand");
						IHM_INS.display("Suppression de la marque " + label + " dans la table Brand");
					} catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur " + sqle);
						IHM_INS.display(
								"Impossible de supprimer la marque, veuillez avant tout supprimer les véhicules qui y sont associés");
					}
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				IHM_INS.display("Une erreur est survenue");
			}
		}
	}

	@Override
	public void update(String oldLabel, String newLabel) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Brand WHERE label = ?;");
				PreparedStatement ps2 = c.prepareStatement("SELECT * FROM Brand WHERE label = ?;");
				ps.setString(1, oldLabel);
				ResultSet result = ps.executeQuery();
				ps2.setString(1, newLabel);
				ResultSet result2 = ps2.executeQuery();
				if (!result.next()) {
					logger.warn("La marque " + oldLabel + " n'existe pas");
					IHM_INS.display("La marque " + oldLabel + " n'existe pas");
				} else if (result2.next()) {
					logger.warn("La marque " + newLabel + " existe déjà");
					IHM_INS.display("La marque " + newLabel + " existe déjà");
				} else {
					try {
						ps = c.prepareStatement("UPDATE Brand SET label =? WHERE label=?;");
						ps.setString(1, newLabel);
						ps.setString(2, oldLabel);
						ps.executeUpdate();
						logger.info("Modification de " + oldLabel + " en " + newLabel + " dans la table Brand");
						IHM_INS.display("Modification de " + oldLabel + " en " + newLabel + " dans la table Brand");
					} catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("Erreur " + sqle);
						IHM_INS.display(
								"Impossible de supprimer la marque, veuillez avant tout supprimer les véhicules qui y sont associés");
					}
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				IHM_INS.display("Une erreur est survenue");
			}
		}
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

	@Override
	public int getId(String label) {
		int idBrand=-1;
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement statement = c.prepareStatement("SELECT * FROM Brand WHERE label = ?;");
				statement.setString(1, label);
				ResultSet result = statement.executeQuery();
				logger.info("Récupération de l'id de la marque");
				if (result.next()) {
					idBrand=result.getInt(1);
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return idBrand;
	}
}
