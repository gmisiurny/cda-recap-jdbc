package com.cda.jdbc.daosql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cda.jdbc.ihm.Ihm;

public class MyConnection {
	private static final Logger logger = LoggerFactory.getLogger(MyConnection.class);
	private static Connection connexion = null;

	private MyConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties appProps = new Properties();
			appProps.load(MyConnection.class.getResourceAsStream("/db.properties"));
			connexion = DriverManager.getConnection(appProps.getProperty("url"),
													appProps.getProperty("username"),
													appProps.getProperty("password"));
		}
		catch (Exception e) {
			logger.error("Erreur : " + e);
			e.printStackTrace();
			Ihm.IHM_INS.display("Connexion à la BDD échouée");
		}
	}

	public static Connection getConnection() {
		if (connexion == null) {
			new MyConnection();
		}
		return connexion;
	}

	public static void stop() {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				logger.error("erreur : " + e);
				Ihm.IHM_INS.display("La déconnexion à la BDD a échouée");
			}
		}
	}
}
