package model.DAO;

import model.Articolo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArticoloDAO {
	
	public void insert(Articolo a) throws SQLException {
		
		Connection dbConnection = null;
		java.sql.PreparedStatement cmd = null;
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			
			Class.forName(driver);
			
			//Creiamo una stringa di connessione
			String url = "jdbc:mysql://localhost:3306/Magazzino?useUnicode=true&useJDVCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			//Connessione tramite id e password del notrso db		
			dbConnection = DriverManager.getConnection(url,"root","roar");	
			//non iseriamo il parametro codie dato che è un auto incremtn e lo farà im automatico sql
			String updateTableSQL= "INSERT INTO Articolo(descrizione,quantita) VALUES(?,?)";
			cmd = dbConnection.prepareStatement(updateTableSQL);
			//inseriamo il valore del primo parametro(descrizione)
			cmd.setString(1, a.getDescrizione());
			//insermiamo il valore del secondo parametro(quantita)
			cmd.setInt(2, a.getQuantita());
			//eseguiamo il codice sql
			cmd.executeUpdate();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			if(cmd!=null) {
				cmd.close();
			}
			if(dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	public void update(Articolo a)throws SQLException {
		
		Connection dbConnection = null;
		java.sql.PreparedStatement cmd = null;
	
		try {
			String driver = "com.mysql.jdbc.Driver";
			
			Class.forName(driver);
			 
			
			String url = "jdbc:mysql://localhost:3306/Magazzino?useUnicode=true&useJDVCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			dbConnection = DriverManager.getConnection(url,"root","roar");	
			
			String updateTableSQL= "UPDATE Articolo set descrizione = ?, quantita = ? WHERE codice = ? ";
			cmd = dbConnection.prepareStatement(updateTableSQL);
		
			cmd.setString(1, a.getDescrizione());
			
			cmd.setInt(2, a.getQuantita());
			
			cmd.setInt(3, a.getCodice());
			
			cmd.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(cmd!=null) {
				cmd.close();
			}
			if(dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	public void delete(int codice)throws SQLException {
		
		Connection dbConnection = null;
		java.sql.PreparedStatement cmd = null;
	
		try {
			String driver = "com.mysql.jdbc.Driver";
			
			Class.forName(driver);
			 
			
			String url = "jdbc:mysql://localhost:3306/Magazzino?useUnicode=true&useJDVCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			dbConnection = DriverManager.getConnection(url,"root","roar");	
			
			String updateTableSQL= "DELETE FROM Articolo WHERE codice = ? ";
			cmd = dbConnection.prepareStatement(updateTableSQL);
		
			cmd.setInt(1, codice);
			
			cmd.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(cmd!=null) {
				cmd.close();
			}
			if(dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	public List<Articolo> findCode() {
		
		List<Articolo> lista = new ArrayList<>();
		
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
		
		
		String url = "jdbc:mysql://localhost:3306/Magazzino?useUnicode=true&useJDVCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		Connection dbConnection = DriverManager.getConnection(url, "root", "roar");
				
		String query = "SELECT * FROM Articolo WHERE codice=?";
		
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		
		
		
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
		
		while(esiste){
			Articolo a = new Articolo();
			int idc;
			idc= res.getInt("codice");
			
			System.out.println(idc);
			System.out.println(res.getInt("descrizione"));
			System.out.println(res.getInt("quantita"));
			
			a.setCodice(idc);
			a.setDescrizione(res.getString("descrizione"));
			a.setQuantita(res.getInt("quantita"));
			lista.add(a);
			
			esiste = res.next();
		}
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return lista;
	}
	
}
