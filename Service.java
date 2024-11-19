package ServiceManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Service {
	int serv_id ;
	int duration ;
	String serv_name ;
	float serv_cost ;
	String desc ;
	public Service(int serv_id ,
	int duration ,
	String serv_name ,
	float serv_cost ,
	String desc) {
		this. serv_id = serv_id ;
		this. duration = duration;
		this. serv_name = serv_name ;
		this. serv_cost = serv_cost ;
		this. desc = desc ;
	}
	public static void enterServ() {
		String url = "jdbc:mysql://localhost:3306/cm";
		 String user = "root";
		 String password = "Mugilan205!";
		 Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(url, user, password) ){
        	PreparedStatement pstmt = con.prepareStatement(
        	        "INSERT INTO Services (service_name, service_cost, duration, description) VALUES (?, ?, ?, ?)");
        	System.out.println("\n--- Enter Service Details ---\n");	                    	    
    	    System.out.println("Enter service name: ");
    	    sc.nextLine(); 
    	    pstmt.setString(1, sc.nextLine()); 
    	    System.out.println("Enter duration: ");
    	    pstmt.setInt(3, sc.nextInt());
    	    sc.nextLine();
    	    System.out.println("Enter the description: ");
    	    pstmt.setString(4, sc.nextLine()); 
    	    System.out.println("Enter service cost: ");
    	    pstmt.setInt(2, sc.nextInt());
    	    pstmt.executeUpdate();	                    	    
    	    System.out.println("Service details uploaded successfully!...");
             
            }
            catch (SQLException e) {
           	 e.printStackTrace();
            
	}
}
	
	
}
	
