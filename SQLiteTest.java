package softwarepulse.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class SQLiteTest {

	private static Connection con;
	private static boolean hasData = false;
	
	public ResultSet izpisStevilke() throws ClassNotFoundException, SQLException{
		
		if(con == null) {
			getConnection();
		}
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT stevilka, tip From telefonske");
		return res;
		}
	
	private void getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SQLiteTelefonske.sqlite");
		initialise();
	}
	
	private void initialise() throws SQLException {
		if (!hasData) {
			hasData = true;
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='telefonske'");
			
			if(!res.next()) {
			
			System.out.println("Kreirana je tabela telefonske.");
			Statement states2 = con.createStatement();
			states2.execute ("CREATE TABLE telefonske (id integer,"
					+ "stevilka varchar(30)," + "tip varchar(30),"
					+ "primary key (id));");
			
			System.out.println("Vpišite telefonsko številko:");
			
			Scanner s = new Scanner(System.in);
			
			String stevilka = s.nextLine();
			
			String tip = "";
			
			/*mobilnik T2*/
			if(stevilka.matches("38664[0-9]{6}")) {
				System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - T2");
				tip = "mobilna";
			}/*mobilnik A1*/
			else if(stevilka.matches("386(30|40|68|69){1}[0-9]{6}")) {
				System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - A1");
				tip = "mobilna";
			}/*Telekom*/
			else if(stevilka.matches("386(31|41|51|71|65){1}[0-9]{6}")){
				System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - Telekom");
				tip = "mobilna";
			}/*Telemah*/
			else if(stevilka.matches("38670[0-9]{6}")){
				System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - Telemah");
				tip = "mobilna";
			}/*Stacionarna stevilka*/
			else if(stevilka.matches("386(1|2|3|4|5|7){1}[0-9]{7}")){
				System.out.println("Vpisana stevilka je:" + stevilka + " Stacionarna številka");
				tip = "stacionarna";
			}
			else{
				System.out.println("NAPAKA !!!! - Vpisali ste:" + stevilka);
				stevilka="";
			}
			
			PreparedStatement  prep = con.prepareStatement ("INSERT INTO telefonske values(?,?,?);");
			prep.setString(2, stevilka);
			prep.setString(3, tip);
			prep.execute();
			

			}
			
		}
	}
	
	public void dodajStevilko(String stevilka, String tip) throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO telefonske values (?,?,?);");
		prep.setString(2, stevilka);
		prep.setString(3, tip);
		prep.execute();
		}
	
	public void test() throws SQLException {
		
		
		System.out.println("Vpišite telefonsko številko:");
		
		Scanner s = new Scanner(System.in);
		
		String stevilka = s.nextLine();
		
		String tip = "";
		
		/*mobilnik T2*/
		if(stevilka.matches("38664[0-9]{6}")) {
			System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - T2");
			tip = "mobilna";
		}/*mobilnik A1*/
		else if(stevilka.matches("386(30|40|68|69){1}[0-9]{6}")) {
			System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - A1");
			tip = "mobilna";
		}/*Telekom*/
		else if(stevilka.matches("386(31|41|51|71|65){1}[0-9]{6}")){
			System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - Telekom");
			tip = "mobilna";
		}/*Telemah*/
		else if(stevilka.matches("38670[0-9]{6}")){
			System.out.println("Vpisana stevilka je:" + stevilka + " Mobilna številka operate - Telemah");
			tip = "mobilna";
		}/*Stacionarna stevilka*/
		else if(stevilka.matches("386(1|2|3|4|5|7){1}[0-9]{7}")){
			System.out.println("Vpisana stevilka je:" + stevilka + " Stacionarna številka");
			tip = "stacionarna";
		}
		else{
			System.out.println("NAPAKA !!!! - Vpisali ste:" + stevilka);
			stevilka="";
		}
		
		
		PreparedStatement  prep = con.prepareStatement ("INSERT INTO telefonske values(?,?,?);");
		prep.setString(2, stevilka);
		prep.setString(3, tip);
		prep.execute();
		
		}
	
	public void izbrisStevilke() throws SQLException {
		
		System.out.println("Vnesite stevilko za izbris:");
		
		Scanner iz = new Scanner(System.in);
		String stevilkaZaIzbris = iz.nextLine();
		
		PreparedStatement st = con.prepareStatement("DELETE FROM telefonske WHERE stevilka = " + stevilkaZaIzbris + ";");
        st.executeUpdate(); 
        
        System.out.println("Številka " +stevilkaZaIzbris+ " je izbrisana!!");
	}
	
	public void izbrisVsehStevilke() throws SQLException {
		
		
		PreparedStatement st = con.prepareStatement("DELETE FROM telefonske;");
        st.executeUpdate(); 
        
        System.out.println("Vse številke so izbrisane!!");
	}
	public void izpisStevilke1() throws SQLException{
		System.out.println("Vnesite stevilko za izpis:");
		
		Scanner izp = new Scanner(System.in);
		String stevilkaZaIzpis = izp.nextLine();
		
		Statement state = con.createStatement();
		ResultSet res2 = state.executeQuery("SELECT stevilka, tip FROM telefonske WHERE stevilka =" +stevilkaZaIzpis+";");
		while (res2.next()) {
		System.out.println("Izpis posamezne številke " +res2.getString("stevilka")+ " - " + res2.getString("tip"));
		}
	}
	
}
