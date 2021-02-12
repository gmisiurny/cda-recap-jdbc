package com.cda.jdbc.daosql;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.data.Piece;
import com.cda.jdbc.data.Piece;
import com.cda.jdbc.ihm.Ihm;

public class PieceDAOImpl implements IPieceDAO {
	private static final Logger logger = LoggerFactory.getLogger(PieceDAOImpl.class);

	@Override
	public Piece save(Piece piece) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO Piece (label, price, id_1, id_2) VALUES (?,?,?,?);",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, piece.getLabel());
				ps.setFloat(2, piece.getPrice());
				ps.setInt(3, piece.getIdReference());
				ps.setInt(4, piece.getIdCategory());
				ps.executeUpdate();
				ResultSet resultat = ps.getGeneratedKeys();
				if (resultat.next()) {
					logger.info("La pièce a bien été créée");
					return piece;
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Piece findByName(String label) {
		Connection c = MyConnection.getConnection();
		Piece piece = new Piece();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Piece WHERE label=?;",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("Piece consultée");
					piece.setIdPiece(resultat.getInt(1));
					piece.setLabel(resultat.getString(2));
					piece.setPrice(resultat.getFloat(3));
					piece.setIdCategory(resultat.getInt(4));
					piece.setIdReference(resultat.getInt(5));
					return piece;
				}
			} catch (SQLException e) {
				logger.error("erreur " + e);
				IHM_INS.display("Erreur lors de l'affichage de la piece");
			}
		}
		return piece;
	}

	@Override
	public void remove(String label) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("DELETE FROM Piece WHERE label =?;");
				ps.setString(1, label);
				ps.executeUpdate();
				logger.info("Suppression de la pièce " + label + " dans la table Piece");
				IHM_INS.display("Suppression de la pièce " + label + " dans la table Piece");
			} catch (SQLException sqle) {
				logger.error("erreur " + sqle);
				IHM_INS.display("Impossible de supprimer la pièce, veuillez avant tout supprimer les ventes qui y sont associées");
			}
		}
	}

	@Override
	public void update(String oldLabel, String newLabel) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("UPDATE Piece SET label =? WHERE label=?;");
				ps.setString(1, newLabel);
				ps.setString(2, oldLabel);
				ps.executeUpdate();
				logger.info("Modification de " + oldLabel + " en " + newLabel + " dans la table Piece");
				IHM_INS.display("Modification de " + oldLabel + " en " + newLabel + " dans la table Piece");
			} catch (SQLException sqle) {
				logger.error("Erreur " + sqle);
				IHM_INS.display("Une erreur est survenue lors de l'édition de la pièce en BDD");
			}
		}
	}

	@Override
	public void updatePrice(String label, float price) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("UPDATE Piece SET price =? WHERE label=?;");
				ps.setFloat(1, price);
				ps.setString(2, label);
				ps.executeUpdate();
				logger.info("Modification du prix de " + label + ", le nouveau prix est: " + price);
				IHM_INS.display("Modification du prix de " + label + ", le nouveau prix est: " + price);
			} catch (SQLException sqle) {
				logger.error("Erreur " + sqle);
				IHM_INS.display("Une erreur est survenue lors de l'édition du prix de la pièce en BDD");
			}
		}
	}

	@Override
	public void updateLabelAndPrice(String oldLabel, String newLabel, float price) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("UPDATE Piece SET label=?,price=? WHERE label=?;");
				ps.setString(1, newLabel);
				ps.setFloat(2, price);
				ps.setString(3, oldLabel);
				ps.executeUpdate();
				logger.info("Modification du label et du prix de " + oldLabel + "\nLe nouveau label est: " + newLabel
						+ "\nLe nouveau prix est: " + price);
				IHM_INS.display("Modification du label et du prix de " + oldLabel + "\nLe nouveau label est: "
						+ newLabel + "\nLe nouveau prix est: " + price);
			} catch (SQLException sqle) {
				logger.error("Erreur " + sqle);
				IHM_INS.display("Une erreur est survenue lors de l'édition en BDD");
			}
		}

	}

	@Override
	public int getId(String label) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Piece> getAllPiecePerCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInDatabase(String label) {
		boolean isIn = false;
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Piece WHERE label=?;");
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("La pièce existe déjà en BDD");
					isIn = true;
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de la vérification de la présence de la pièce");
			}
		}
		return isIn;
	}
}
