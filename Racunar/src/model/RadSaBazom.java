package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.ConnectionEvent;
import javax.swing.JOptionPane;

import klasa.Racunar;

public class RadSaBazom {
	private static RadSaBazom rsb;
	
	private RadSaBazom() {}
	
	public static RadSaBazom getInstanceOf() {
		if(rsb == null) {
			rsb = new RadSaBazom();
		}
		return rsb;
	}
	
	private Statement statement = null;
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private void connection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/racunar", "root", "");
	}
	
	public ArrayList<Racunar> vratiListuRacunara() throws ClassNotFoundException, SQLException{
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		
		connection();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunari`");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		
		close();
		return lista;
	}
	
	public ArrayList<Racunar> vratiListuRacunaraOpadajuce() throws ClassNotFoundException, SQLException{
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		
		connection();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunari` ORDER BY cena  DESC");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		
		close();
		return lista;
	}
	
	public ArrayList<Racunar> vratiListuNovihRacunara() throws ClassNotFoundException, SQLException{
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		
		connection();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunari` WHERE nov !=0");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		
		close();
		return lista;
	}
	
	public ArrayList<Racunar> vratiListuPolovnihRacunara() throws ClassNotFoundException, SQLException{
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		
		connection();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunari` WHERE nov=0");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		
		close();
		return lista;
	}
	
	public ArrayList<Racunar> vratiListuRacunaraRastuce() throws ClassNotFoundException, SQLException{
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		
		connection();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunari` ORDER BY cena  ASC");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		
		close();
		return lista;
	}
//	SELECT * FROM `racunari` WHERE cena > 300
	
	public ArrayList<Racunar> vratiListuRacunaraKojiSuSkupljiOd(int iznos) throws ClassNotFoundException, SQLException{
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		String iznosS = String.valueOf(iznos);
		connection();
		preparedStatement = connect.prepareStatement("SELECT * FROM `racunari` WHERE cena > " +  iznosS);
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		
		close();
		return lista;
	}
	
	
	public void unosRacunaraUBazu(Racunar r) throws ClassNotFoundException, SQLException {
		connection();
		preparedStatement = connect.prepareStatement("INSERT INTO `racunari`(`vrsta`, `cena`, `nov`) VALUES (?, ?, ?)");
		preparedStatement.setString(1, r.getVrsta());
		preparedStatement.setDouble(2, r.getCena());
		preparedStatement.setBoolean(3, r.isNov());
		preparedStatement.execute();
		
		close();
	}
	
	public int insertRacunarLastInsertID(Racunar r) throws ClassNotFoundException, SQLException {
		connection();
		preparedStatement = connect.prepareStatement("INSERT INTO `racunari`( `vrsta`, `cena`, `nov`) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, r.getVrsta());
		preparedStatement.setDouble(2, r.getCena());
		preparedStatement.setBoolean(3, r.isNov());
		
		resultSet = preparedStatement.getResultSet();
		ResultSet keys = preparedStatement.getGeneratedKeys();
		keys.next();
		
		int id = keys.getInt(1);
		close();
		return id;
	}
	
	public void updateRacunar(Racunar r) throws ClassNotFoundException, SQLException {
		connection();
		preparedStatement = connect.prepareStatement("UPDATE `racunari` SET `vrsta`= ?,`cena`= ?,`nov`= ? WHERE id= ?");
		preparedStatement.setString(1, r.getVrsta());
		preparedStatement.setDouble(2, r.getCena());
		preparedStatement.setBoolean(3, r.isNov());
		preparedStatement.setInt(4, r.getId());
		
		preparedStatement.execute();
		
		close();
	}
	
	public void brisanjeRacunaraIzBaze(int id) throws ClassNotFoundException, SQLException {
		connection();
		preparedStatement = connect.prepareStatement("DELETE FROM `racunari` WHERE id = ?");
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		close();
	}
	
	private void close() {
		try {
		if(connect != null) {
			connect.close();
		}
		if(resultSet != null) {
			resultSet.close();
		}
		if(statement != null) {
			statement.close();
		}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Doslo je do greske! " + e.getMessage());
		}
		
	}

}
