package ServiceManagement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class MaintenanceRecord {
	int record_id; 
	int car_id ;
	int serv_id ;
	Date sch_date ;
    Date complete_date ;
	String status ;
	String notes ;
	
	public MaintenanceRecord(int record_id , 
	int car_id  ,
	int serv_id  ,
	java.util.Date sch_date2  ,
	java.util.Date complete_date2 ,
	String status ,
	String notes) {
		this. record_id = record_id; 
		this. car_id  = car_id;
		this. serv_id  = serv_id;
		this. sch_date = (Date) sch_date2;
		this. complete_date = (Date)complete_date2 ;
		this. status = status;
		this. notes  = notes;
		
		
	}
	public static void enterMaint() {
		String url = "jdbc:mysql://localhost:3306/cm";
		 String user = "root";
		 String password = "Mugilan205!";
		 Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(url, user, password) ){
        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO maintenancerecords ( scheduled_date,  status, service_notes)  VALUES (  ?, ?,?)");
        	System.out.println("\n---Enter Record details----\n");	 		                              
            System.out.println("Enter the Scheduled date: \n");
            pstmt.setString(1,sc.next());		                            		                              
             System.out.println("Enter status :\n");
            pstmt.setString(2, sc.next());
            System.out.println("Enter service notes :\n");
            pstmt.setString(3, sc.next());
            
            pstmt.executeUpdate();
             
            }
            catch (SQLException e) {
           	 e.printStackTrace();
            
	}
}


}
