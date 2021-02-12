package com.cda.jdbc.daosql;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cda.jdbc.dao.IVehicleDAO;
import com.cda.jdbc.data.Vehicle;


public class VehicleDAOImpl implements IVehicleDAO {
	private static final Logger logger = LoggerFactory.getLogger(VehicleDAOImpl.class);

	@Override
	public Vehicle save(Vehicle vehicle) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Vehicle WHERE label = ?");
				ps.setString(1,vehicle.getNumberPlate());
				ResultSet result = ps.executeQuery();
				if (!result.next()) {
					ps = c.prepareStatement("INSERT INTO Vehicle VALUES (?,?,?,?); ");
					ps.setString(1, vehicle.getNumberPlate());
					ps.setInt(2, vehicle.getYearProduct());
					ps.setInt(3, vehicle.getIdModel());
					ps.setInt(4, vehicle.getIdBrand());
					ps.executeUpdate();
					ResultSet resultat = ps.getGeneratedKeys();
					if (resultat.next()) {
						logger.info("Véhicule créé");
						return vehicle;
					}
				} else {
					logger.warn("Véhicule déjà dans la BDD");
					IHM_INS.display("Véhicule déjà dans la BDD");
				}
			} catch (SQLException e) {
				logger.error("erreur "+e);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Vehicle findByName(String label) {
		Connection c = MyConnection.getConnection();
		Vehicle vehicle = new Vehicle();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Vehicle WHERE label=?;",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("Vehicle consultée");
					vehicle.setNumberPlate(resultat.getString(1));
					vehicle.setYearProduct(resultat.getInt(2));
					vehicle.setIdModel(resultat.getInt(3));
					vehicle.setIdBrand(resultat.getInt(4));
					return vehicle;
				}
			
			} catch (SQLException e) {
				logger.error("erreur " + e);
				IHM_INS.display("Erreur lors de l'affichage des marques");
			}
		}
		return vehicle;
	}

	@Override
	public void remove(String label) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Vehicle WHERE label = ?;");
				ps.setString(1,label);
				ResultSet result = ps.executeQuery();
				if (result.next() == false) {
					logger.warn("Le véhicule " + label + " n'existe pas");
					IHM_INS.display("Le véhicule " + label + " n'existe pas");
				} else {
					try {
						ps = c.prepareStatement("DELETE FROM Vehicle WHERE label =?;");
						ps.setString(1, label);
						ps.executeUpdate();
						logger.info("Suppression de " + label + " dans la table Vehicle");
						IHM_INS.display("Suppression de " + label + " dans la table Vehicle");
					}
					// Si au moins une vente est liée au véhicule , alors impossible de le supprimer, on
					// renverra le message suivant
					catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur "+sqle);
						IHM_INS.display(
								"Impossible de supprimer le véhicule, veuillez avant tout supprimer les ventes qui y sont associées");
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
		// TODO Auto-generated method stub
	}

	@Override
	public int getId(String nom) {
		return -1;
	}

}
