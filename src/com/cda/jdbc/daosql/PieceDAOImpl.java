package com.cda.jdbc.daosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.data.Piece;
import com.cda.jdbc.ihm.Ihm;

public class PieceDAOImpl implements IPieceDAO {
	private static final Logger logger = LoggerFactory.getLogger(PieceDAOImpl.class);
	@Override
	public Piece save(Piece o) {
		//Faire un round sur price
		
		return null;
	}

	@Override
	public Piece findByName(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String oldLabel, String newLabel) {
		// TODO Auto-generated method stub

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
