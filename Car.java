package ServiceManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Car {
  	int car_id ;
	int cust_id;
	int year;
	String licen_plt;
	String make;
	String model;
	String vin;
	
	Car(	int car_id ,
	int cust_id,
	  int year,
	    String licen_plt,
	      String make,
	        String model,
	          String vin){
		
		this.car_id=car_id ;
		this.cust_id = cust_id;
		this.licen_plt=licen_plt;
		this.make = make;
		this.model = model ;
		this.year=year;
		this.vin= vin;

	}
	public static void enterCar() {
		String url = "jdbc:mysql://localhost:3306/cm";
		 String user = "root";
		 String password = "Mugilan205!";
		 Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(url, user, password) ){
        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO cars ( license_plate, make, model, year, vin)  VALUES (  ?, ?,?,?,?)");
        
       	  System.out.println("\n---Enter Car details----\n");	 
             System.out.println("Enter License no :\n");
             pstmt.setString(1, sc.next());
             System.out.println("Enter make :\n");
             pstmt.setString(2, sc.next());
             System.out.println("Enter model :\n");
             pstmt.setString(3, sc.next());
             System.out.println("Enter year :\n");
             pstmt.setInt(4, sc.nextInt());

             pstmt.setString(5, "1HGCM82633A789012");
            
             pstmt.executeUpdate();
             
            }
            catch (SQLException e) {
           	 e.printStackTrace();
            
	}
}
}
