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
import com.cda.jdbc.ihm.Ihm;



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
				if (!result.next()) {
					ps = c.prepareStatement("INSERT INTO Category (label) VALUES (?); ",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, category.getLabelCategory());
					ps.executeUpdate();
					ResultSet resultat = ps.getGeneratedKeys();
					if (resultat.next()) {
						logger.info("Cat�gorie cr��");
						category.setIdCategory(resultat.getInt(1));
						return category;
					}
				} else {
					logger.warn("Cat�gorie d�j� dans la BDD");
					IHM_INS.display("Cat�gorie d�j� dans la BDD");
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
				logger.info("Ajout des cat�gories � la liste");
				while (result.next()) {
					category.add(new Category(result.getInt("idCategory"), result.getString("label")));
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return category;
	}

	@Override
	public void remove(String label) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Category WHERE label = ?;");
				ps.setString(1,label);
				ResultSet result = ps.executeQuery();
				if (!result.next()) {
					logger.warn("La cat�gorie " + label + " n'existe pas");
					IHM_INS.display("La cat�gorie " + label + " n'existe pas");
				} else {
					try {
						ps = c.prepareStatement("DELETE FROM Category WHERE label =?;");
						ps.setString(1, label);
						ps.executeUpdate();
						logger.info("Suppression de " + label + " dans la table Category");
						IHM_INS.display("Suppression de " + label + " dans la table Category");
					}
					// Si au moins une pi�ce est li�e � la cat�gorie , alors impossible de la supprimer, on
					// renverra le message suivant
					catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur "+sqle);
						IHM_INS.display(
								"Impossible de supprimer la cat�gorie, veuillez avant tout supprimer les pi�ces qui y sont associ�es");
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
				PreparedStatement ps2 = c.prepareStatement("SELECT * FROM Category WHERE label = ?;");
				ps.setString(1,oldLabel);
				ResultSet result = ps.executeQuery();
				ps2.setString(1,newLabel);
				ResultSet result2 = ps2.executeQuery();
				if (!result.next()) {
					logger.warn("La cat�gorie " + oldLabel + " n'existe pas");
					IHM_INS.display("La cat�gorie " + oldLabel + " n'existe pas");
				}else if(result2.next()){
					logger.warn("La cat�gorie " + newLabel + " existe d�j�");
					IHM_INS.display("La cat�gorie " + newLabel + " existe d�j�");
				} else {
					try {
						ps = c.prepareStatement("UPDATE Category SET label =? WHERE label=?;");
						ps.setString(1, newLabel);
						ps.setString(2, oldLabel);
						ps.executeUpdate();
						logger.info("Modification de " + oldLabel + " en "+newLabel+" dans la table Category");
						IHM_INS.display("Modification de " + oldLabel + " en "+newLabel+" dans la table Category");
					}
					// Si au moins une pi�ce est li�e � la cat�gorie , alors impossible de la supprimer, on
					// renverra le message suivant
					catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur "+sqle);
						IHM_INS.display(
								"Impossible de supprimer la cat�gorie, veuillez avant tout supprimer les pi�ces qui y sont associ�es");
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
					logger.info("Cat�gorie consult�e");
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

	@Override
	public int getId(String label) {
		int idCategory = -1;
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement statement = c.prepareStatement("SELECT * FROM Category WHERE label = ?;");
				statement.setString(1, label);
				ResultSet result = statement.executeQuery();
				logger.info("R�cup�ration de l'id de la cat�gorie");
				if (result.next()) {
					idCategory = result.getInt(1);
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return idCategory;
	}

	@Override
	public boolean isInDatabase(String label) {
		boolean isIn = false;
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Category WHERE label=?;");
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("La cat�gorie existe en BDD");
					isIn = true;
				} else {
					Ihm.IHM_INS.display("Cette cat�gorie n'existe pas");
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de la v�rification de la pr�sence de la cat�gorie");
			}
		}
		return isIn;		
	}
}
