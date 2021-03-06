package com.cda.jdbc.daosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IReferenceDAO;
import com.cda.jdbc.data.Reference;
import com.cda.jdbc.ihm.Ihm;

public class RefDAOImpl implements IReferenceDAO {
	private static final Logger logger = LoggerFactory.getLogger(RefDAOImpl.class);
	
	@Override
	public Reference save(Reference o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reference findByName(String label) {
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
	public int getId(String reference) {
		Connection c = MyConnection.getConnection();
		int id = -1;
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT id FROM Reference WHERE reference=?;");
				ps.setString(1, reference);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("Reference r�cup�r�e");
					id = resultat.getInt(1);
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de la r�cup�ration de la r�f�rence");
			}
		}
		return id;
	}

	@Override
	public boolean isInDatabase(String label) {
		boolean isIn = false;
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM Reference WHERE reference=?;");
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("La r�f�rence existe d�j� en BDD");
					isIn = true;
				} else {
					Ihm.IHM_INS.display("Cette r�f�rence n'existe pas");
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de la v�rification de la pr�sence de la r�f�rence");
			}
		}
		return isIn;
	}
}
