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
import com.cda.jdbc.ihm.Ihm;

public class VehicleDAOImpl implements IVehicleDAO {
	private static final Logger logger = LoggerFactory.getLogger(VehicleDAOImpl.class);

	@Override
	public Vehicle save(Vehicle vehicle) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Vehicule WHERE numberPlate = ?");
				ps.setString(1, vehicle.getNumberPlate());
				ResultSet result = ps.executeQuery();
				if (vehicle.getIdBrand() < 0) {
					IHM_INS.display("La marque n'existe pas dans la BDD");
				} else if (vehicle.getIdModel() < 0) {
					IHM_INS.display("Le modèle n'existe pas dans la BDD");
				} else if (!result.next()) {
					ps = c.prepareStatement("INSERT INTO Vehicule VALUES (?,?,?,?); ");
					ps.setString(1, vehicle.getNumberPlate());
					ps.setInt(2, vehicle.getYearProduct());
					ps.setInt(3, vehicle.getIdModel());
					ps.setInt(4, vehicle.getIdBrand());
					ps.executeUpdate();
					IHM_INS.display("Véhicule ajouté à la BDD");
					logger.info("Véhicule créé");
				} else {
					logger.warn("Véhicule déjà dans la BDD");
					IHM_INS.display("Véhicule déjà dans la BDD");
				}
			} catch (SQLException e) {
				logger.error("erreur " + e);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Vehicle findByName(String numberPlate) {
		Connection c = MyConnection.getConnection();
		Vehicle vehicle = new Vehicle();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Vehicule WHERE numberPlate=?;",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, numberPlate);
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
	public void remove(String numberPlate) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Vehicule WHERE numberPlate = ?;");
				ps.setString(1, numberPlate);
				ResultSet result = ps.executeQuery();
				if (!result.next()) {
					logger.warn("Le véhicule " + numberPlate + " n'existe pas");
					IHM_INS.display("Le véhicule " + numberPlate + " n'existe pas");
				} else {
					try {
						ps = c.prepareStatement("DELETE FROM Vehicule WHERE numberPlate =?;");
						ps.setString(1, numberPlate);
						ps.executeUpdate();
						logger.info("Suppression de " + numberPlate + " dans la table Vehicule");
						IHM_INS.display("Suppression de " + numberPlate + " dans la table Vehicule");
					}
					// Si au moins une vente est liée au véhicule , alors impossible de le
					// supprimer, on
					// renverra le message suivant
					catch (SQLIntegrityConstraintViolationException sqle) {
						logger.error("erreur " + sqle);
						IHM_INS.display(
								"Impossible de supprimer le véhicule, veuillez avant tout supprimer les ventes qui y sont associées");
					}
				}
			} catch (SQLException e) {
				logger.error("erreur " + e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(String oldnumberPlate, String newNumberPlate) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getId(String nom) {
		return -1;
	}

	@Override
	public void piecesPerCategoryForMostRecentVehicules() {
		String request = "SELECT v.numberPlate \"Immatriculation\", v.yearProduct \"Année de production\", p.price \"Prix\", r.reference \"Référence\", r.quantity \"Quantité\", c.label \"Catégorie\"\r\n"
				+ "FROM Vehicule v \r\n" + "JOIN Vehicule_Piece vp ON v.numberPlate = vp.numberPlate \r\n"
				+ "JOIN Piece p ON vp.idPiece = p.idPiece \r\n"
				+ "JOIN Reference r ON p.idReference = r.idReference \r\n"
				+ "JOIN Category c ON p.idCategory = c.idCategory \r\n" + "WHERE r.quantity != 0\r\n"
				+ "ORDER BY v.yearProduct DESC\r\n" + "LIMIT 3;";
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(request);
				ResultSet resultat = ps.executeQuery();
				while (resultat.next()) {
					String numberPlate = resultat.getString("Immatriculation");
					String yearProduct = resultat.getString("Année de production");
					float price = resultat.getFloat("Prix");
					String reference = resultat.getString("Référence");
					int quantity = resultat.getInt("Quantité");
					String category = resultat.getString("Catégorie");
					String res = "\nImmatriculation: " + numberPlate + "\nAnnée de production: " + yearProduct
							+ "\nPrix: " + price + "\nPrix: " + price + "\nRéférence: " + reference + "\nQuantité: "
							+ quantity + "\nCategorie: " + category;
					IHM_INS.display(res);
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de la récupérations des données");
			}
		}
	}

}
