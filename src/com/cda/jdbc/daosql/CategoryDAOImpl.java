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

import com.cda.jdbc.dao.ICategoryDAO;
import com.cda.jdbc.data.Category;



public class CategoryDAOImpl implements ICategoryDAO{
	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	@Override
	public Category save(Category category) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Category WHERE label = ?");
				ps.setString(1,category.getLabelCategory());
				ResultSet result = ps.executeQuery();
				if (result.next() == false) {
					ps = c.prepareStatement("INSERT INTO Category (label) VALUES (?); ",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, category.getLabelCategory());
					ps.executeUpdate();
					ResultSet resultat = ps.getGeneratedKeys();
					if (resultat.next()) {
						logger.info("Catégorie créé");
						category.setIdCategory(resultat.getInt(1));
						return category;
					}
				} else {
					logger.warn("Catégorie déjà dans la BDD");
					IHM_INS.display("Catégorie déjà dans la BDD");
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> category = new ArrayList<>();
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Category");
				ResultSet result = ps.executeQuery();
				logger.info("Ajout des catégories à la liste");
				while (result.next()) {
					category.add(new Category(result.getInt("id"), result.getString("label")));
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return category;
	}

	@Override
	public void remove(String nomOuID) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Category WHERE label = ?;");
				ps.setString(1,nomOuID);
				ResultSet result = ps.executeQuery();
				if (result.next() == false) {
					logger.warn("La catégorie " + nomOuID + " n'existe pas");
					IHM_INS.display("La catégorie " + nomOuID + " n'existe pas");
				} else {
					try {
						ps = c.prepareStatement("DELETE FROM Category WHERE label =?;");
						ps.setString(1, nomOuID);
						ps.executeUpdate();
						logger.info("Suppression de " + nomOuID + " dans la table Category");
						IHM_INS.display("Suppression de " + nomOuID + " dans la table Category");
					}
					// Si au moins une pièce est liée à la catégorie , alors impossible de la supprimer, on
					// renverra le message suivant
					catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur "+sqle);
						IHM_INS.display(
								"Impossible de supprimer la catégorie, veuillez avant tout supprimer les pièces qui y sont associées");
					}
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(String oldLabel, String newLabel) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Category WHERE label = ?;");
				ps.setString(1,oldLabel);
				ResultSet result = ps.executeQuery();
				if (result.next() == false) {
					logger.warn("La catégorie " + oldLabel + " n'existe pas");
					IHM_INS.display("La catégorie " + oldLabel + " n'existe pas");
				} else {
					try {
						ps = c.prepareStatement("UPDATE Category SET label =? WHERE label=?;");
						ps.setString(1, newLabel);
						ps.setString(2, oldLabel);
						ps.executeUpdate();
						logger.info("Modification de " + oldLabel + " en "+newLabel+" dans la table Category");
						IHM_INS.display("Modification de " + oldLabel + " en "+newLabel+" dans la table Category");
					}
					// Si au moins une pièce est liée à la catégorie , alors impossible de la supprimer, on
					// renverra le message suivant
					catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur "+sqle);
						IHM_INS.display(
								"Impossible de supprimer la catégorie, veuillez avant tout supprimer les pièces qui y sont associées");
					}
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
				
	}

	@Override
	public Category findByName(String label) {
		Connection c = MyConnection.getConnection();
		Category category = new Category();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Category WHERE label=?;",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("Catégorie consultée");
					category.setIdCategory(resultat.getInt(1));
					category.setLabelCategory(resultat.getString(2));
					return category;
				}
			
			} catch (SQLException e) {
				logger.error("erreur " + e);
				IHM_INS.display("Erreur lors de l'affichage des marques");
			}
		}
		return category;
	}
}
