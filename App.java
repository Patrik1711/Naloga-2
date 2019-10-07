package softwarepulse.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		SQLiteTest test = new SQLiteTest();
		
		ResultSet rs = null;
		
		try {
			rs = test.izpisStevilke();
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getString("stevilka")+ " - " + rs.getString("tip"));
				}
			}else {
				System.out.println("\n Tabela je prazna !!");
			}
			
		}
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		
		int i=0;
		do {
			System.out.println("\n Želite nadaljevarti? \n \n Vnesite: \n \n 1 - Vnesite novo številko \n 2 - za izhod \n "
					+ "3 - Za brisanje vseh podatkov \n 4 - za izbris posamezne številke \n 5 - Izpis vseh številk "
					+ "\n 6 - Izpis iskane številke");
			
			Scanner p = new Scanner(System.in);
			String odgovor = p.nextLine();
			
			if(odgovor.matches("1")) {
				
				test.test();
				
			} 
			else if(odgovor.matches("2")){
				System.out.println("Hvala in lep dan.");
				 if (rs != null) { rs.close(); }
				 i=1;
			}
			else if(odgovor.matches("3")){
				System.out.println("Želite res izbrisati vse številke? Potem vpišite - DA ");
				
				Scanner p2 = new Scanner(System.in);
				String odgovor1 = p2.nextLine();
				
				if(odgovor1.matches("DA")) {
					test.izbrisVsehStevilke();
					
					}else{
						System.out.println("Preklicali ste izbris vseh stevilk");
					}
				}
				 
			
			else if(odgovor.matches("4")){
								
				
				test.izbrisStevilke();
				
				
				rs = test.izpisStevilke();
				while (rs.next()) {
				System.out.println(rs.getString("stevilka")+ " - " + rs.getString("tip"));
				}
				
				
				}else if(odgovor.matches("5")){
				
				rs = test.izpisStevilke();
				if (rs != null) {
					while (rs.next()) {
						System.out.println(rs.getString("stevilka")+ " - " + rs.getString("tip"));
					}
				}else {
					System.out.println("\n Tabela je prazna !!");
				}
				}else if(odgovor.matches("6")){
					
					
					test.izpisStevilke1();
				}
				else {
					System.out.println("Napaèen vnos! Vnesite eno izmed navedenih številk.");
				}

			}
			while (i < 1);
		
	}
}

