package com.cda.jdbc.daosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IDAO;
import com.cda.jdbc.data.Reference;
import com.cda.jdbc.ihm.Ihm;

public class RefDAOImpl implements IDAO<Reference> {
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
	public int getId(String label) {
		Connection c = MyConnection.getConnection();
		int id = -1;
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT id FROM Reference WHERE label=?;");
				ps.setString(1, label);
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					logger.info("Reference récupérée");
					id = resultat.getInt(1);
				}
			} catch (SQLException e) {
				logger.error("Erreur " + e);
				Ihm.IHM_INS.display("Erreur lors de la récupération de la référence");
			}
		}
		return id;
	}
}
