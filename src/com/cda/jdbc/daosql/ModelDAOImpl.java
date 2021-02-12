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
import com.cda.jdbc.dao.IModelDAO;
import com.cda.jdbc.data.Model;
import com.cda.jdbc.ihm.Ihm;

public class ModelDAOImpl implements IModelDAO {
	private static final Logger logger = LoggerFactory.getLogger(ModelDAOImpl.class);

	@Override
	public Model save(Model model) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				String request = "SELECT * FROM Model WHERE label=?;";
				PreparedStatement prepStatement = c.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
				prepStatement.setString(1, model.getLabel());
				ResultSet result = prepStatement.executeQuery();
				if (!result.next()) {
					PreparedStatement ps = c.prepareStatement("INSERT INTO Model (label) values (?); ",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, model.getLabel());
					ps.executeUpdate();
					ResultSet resultat = ps.getGeneratedKeys();
					if (resultat.next()) {
						logger.info("Modele créée !");
						model.setIdModel(resultat.getInt(1));
						return model;
					}
				} else {
					logger.warn("Modele déjà dans la BDD");
					Ihm.IHM_INS.display("Modele déjà dans la BDD");
				}
			} catch (SQLException e) {
				logger.error("erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de l'insertion en BDD");
			}
		}
		return null;
	}

	@Override
	public List<Model> getAll() {
		List<Model> model = new ArrayList<>();
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Model");
				ResultSet result = ps.executeQuery();
				logger.info("Ajout des models à la liste");
				while (result.next()) {
					model.add(new Model(result.getInt("id"), result.getString("label")));
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				IHM_INS.display("Une erreur est survenue !");
			}
		}
		return model;
	}

	@Override
	public void remove(String label) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Model WHERE label = ?;");
				ps.setString(1, label);
				ResultSet result = ps.executeQuery();
				if (!result.next()) {
					logger.warn("La catégorie " + label + " n'existe pas");
					IHM_INS.display("Le model " + label + " n'existe pas");
				} else {
					try {
						ps = c.prepareStatement("DELETE FROM Model WHERE label =?;");
						ps.setString(1, label);
						ps.executeUpdate();
						logger.info("Suppression du model " + label + " dans la table Model");
						IHM_INS.display("Suppression du model " + label + " dans la table Model");
					} catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur " + sqle);
						IHM_INS.display(
								"Impossible de supprimer le model, veuillez avant tout supprimer les véhicules qui y sont associés");
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
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Model WHERE label = ?;");
				PreparedStatement ps2 = c.prepareStatement("SELECT * FROM Model WHERE label = ?;");
				ps.setString(1, oldLabel);
				ResultSet result = ps.executeQuery();
				ps2.setString(1, newLabel);
				ResultSet result2 = ps2.executeQuery();
				if (!result.next()) {
					logger.warn("Le model " + oldLabel + " n'existe pas");
					IHM_INS.display("Le model " + oldLabel + " n'existe pas");
				} else if (result2.next()) {
					logger.warn("Le model " + newLabel + " existe déjà");
					IHM_INS.display("Le model " + newLabel + " existe déjà");
				} else {
					try {
						ps = c.prepareStatement("UPDATE Model SET label =? WHERE label=?;");
						ps.setString(1, newLabel);
						ps.setString(2, oldLabel);
						ps.executeUpdate();
						logger.info("Modification de " + oldLabel + " en " + newLabel + " dans la table Model");
						IHM_INS.display("Modification de " + oldLabel + " en " + newLabel + " dans la table Model");
					} catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("Erreur " + sqle);
						IHM_INS.display("Erreur lors de l'édition du modele en BDD");
					}
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				IHM_INS.display("Une erreur est survenue");
			}
		}
	}

	@Override
	public Model findByName(String label) {
		Connection c = MyConnection.getConnection();
		Model model = new Model();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Model WHERE label=?;",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("Model consulté");
					model.setIdModel(resultat.getInt(1));
					model.setLabel(resultat.getString(2));
					return model;
				}

			} catch (SQLException e) {
				logger.error("Erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de l'affichage des model");
			}
		}
		return model;
	}

	@Override
	public int getId(String label) {
		int idModel=-1;
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement statement = c.prepareStatement("SELECT * FROM Model WHERE label = ?;");
				statement.setString(1, label);
				ResultSet result = statement.executeQuery();
				logger.info("Récupération de l'id du modèle");
				if (result.next()) {
					idModel=result.getInt(1);
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return idModel;
	}
}
