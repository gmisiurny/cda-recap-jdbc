package com.cda.jdbc.daosql;

import static com.cda.jdbc.ihm.Ihm.IHM_INS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cda.jdbc.dao.IPieceDAO;
import com.cda.jdbc.data.Piece;
import com.cda.jdbc.export.ExcelExport;
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
				IHM_INS.display(
						"Impossible de supprimer la pièce, veuillez avant tout supprimer les ventes qui y sont associées");
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

	@Override
	public void listAvailablePiecePerModel(String choice) {
		String request = "SELECT m.label, p.label, r.quantity, p.price, p.price*r.quantity total\r\n"
				+ "FROM Piece p\r\n" + "JOIN Reference r ON p.idReference = r.idReference\r\n"
				+ "JOIN Piece_Vehicule pv ON p.idPiece = pv.idPiece \r\n"
				+ "JOIN Vehicule v ON pv.numberPlate = v.numberPlate \r\n" + "JOIN Model m ON m.idModel = v.idModel;";
		Connection c = MyConnection.getConnection();
		if (c != null) {
			switch (choice) {
			case "Non":
				noExport(request, c);
				break;
			case "Excel":
				excelExport(request, c);
				break;
			case "Html":
				htmlExport(request, c);
				break;
			default:
				break;
			}
		}	
	}

	private void htmlExport(String request, Connection c) {
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(request);
			ResultSet resultat = ps.executeQuery();
			int i = 0;
			Map<String, Object[]> data = new TreeMap<>();
			ArrayList<Map<String, Object[]>> list = new ArrayList<>();
			while(resultat.next()) {
				i++;
				String modelLabel = resultat.getString("m.label");
				String pieceLabel = resultat.getString("p.label");
				int quantity = resultat.getInt("r.quantity");
				float price = resultat.getFloat("p.price");
				float total = resultat.getFloat("total");
				String res = "\nModele: " + modelLabel + "\nPièce: " + pieceLabel + "\nQuantité: " + quantity
						+ "\nPrix: " + price + "\nTotal: " + total;
				IHM_INS.display(res);
				data.put(String.valueOf(i), new Object[] { modelLabel, pieceLabel, quantity, price, total });
			}
			list.add(data);
			Velocity.init();
			Template template = Velocity.getTemplate("templates/index.vm");
			Writer writer = null;
			VelocityContext context = new VelocityContext();
			Collection<Object[]> collec = data.values();
			context.put("prdList", collec);
			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("exportAvailablePiecePerModelToHTMLFile.html"), "utf-8"));
				template.merge(context, writer);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			logger.error("Erreur " + e);
			Ihm.IHM_INS.display("Erreur lors de la récupérations des données");
		}
		
		
	}

	private void noExport(String request, Connection c) {
		try {
			PreparedStatement ps = c.prepareStatement(request);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				String modelLabel = resultat.getString("m.label");
				String pieceLabel = resultat.getString("p.label");
				int quantity = resultat.getInt("r.quantity");
				float price = resultat.getFloat("p.price");
				float total = resultat.getFloat("total");
				String res = "\nModele: " + modelLabel + "\nPièce: " + pieceLabel + "\nQuantité: " + quantity
						+ "\nPrix: " + price + "\nTotal: " + total;
				IHM_INS.display(res);
			}
		} catch (SQLException e) {
			logger.error("Erreur " + e);
			Ihm.IHM_INS.display("Erreur lors de la récupérations des données");
		}
	}	

	private void excelExport(String request, Connection c) {
		try {
			PreparedStatement ps = c.prepareStatement(request);
			ResultSet resultat = ps.executeQuery();
			ExcelExport excExp = new ExcelExport();
			Map<String, Object[]> data = excExp.getData();
			data.put("1", new Object[] { "Modele", "Pièce", "Quantité", "Prix", "Total" });
			int i = 1;
			while (resultat.next()) {
				i++;
				String modelLabel = resultat.getString("m.label");
				String pieceLabel = resultat.getString("p.label");
				int quantity = resultat.getInt("r.quantity");
				float price = resultat.getFloat("p.price");
				float total = resultat.getFloat("total");
				String res = "\nModele: " + modelLabel + "\nPièce: " + pieceLabel + "\nQuantité: " + quantity
						+ "\nPrix: " + price + "\nTotal: " + total;
				IHM_INS.display(res);
				data.put(String.valueOf(i), new Object[] { modelLabel, pieceLabel, quantity, price, total });
				Set<String> keyset = data.keySet();
				int rownum = 0;
				for (String key : keyset) {
					Row row = excExp.getSheet().createRow(rownum++);
					Object[] objArr = data.get(key);
					int cellnum = 0;
					for (Object obj : objArr) {
						Cell cell = row.createCell(cellnum++);
						if (obj instanceof String) {								
							cell.setCellValue((String) obj);
						} else if (obj instanceof Float) {
							cell.setCellValue((Float) obj);
						}
						else if (obj instanceof Integer) {								
							cell.setCellValue((Integer) obj);
						}
					}
				}
				try {
					FileOutputStream out = new FileOutputStream(
							new File("exportAvailablePiecePerModelToExcelFile.xlsx"));
					excExp.getWorkbook().write(out);
					out.close();
					System.out
							.println("exportAvailablePiecePerModelToExcelFile.xlsx written successfully on disk.");
				} catch (Exception e) {
					logger.error("Erreur " + e);
					IHM_INS.display("Une erreur est survenue lors de l'export Excel !");
				}
			}

		} catch (SQLException e) {
			logger.error("Erreur " + e);
			Ihm.IHM_INS.display("Erreur lors de la récupérations des données");
		}
	}
}
