package ServiceManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;
import java.sql.SQLException;


public class CarManagement {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/cm";
		String user = "root";
		String password = "Mugilan205!";
		ArrayList <Car> CarList = new ArrayList<>();
		ArrayList <Service> ServList = new ArrayList<>();
		ArrayList <Customer> CustomerList = new ArrayList<>();
		ArrayList <MaintenanceRecord> MaintenList = new ArrayList<>();
		Scanner sc =  new Scanner(System.in);
		History hs= new History();
		try (Connection con = DriverManager.getConnection(url, user, password)){
			Statement st = con.createStatement();
	        ResultSet car = st.executeQuery("select * from cars");
	        while(car.next()) {
	        	int car_id = car.getInt("car_id");
	        	int cust_id = car.getInt("customer_id");
	        	int year = car.getInt("year" );
	        	String licen_plt = car.getString("license_plate");
	        	String make = car.getString("make");
	        	String model = car.getString("model"); 
	        	String vin = car.getString("vin");
	        	Car cr = new Car(car_id,cust_id,year,licen_plt,make,model,vin);
	        	CarList.add(cr);
	        	
	        }   
	        
	        ResultSet serv = st.executeQuery("select * from  services");
	        while(serv.next()) {
	        	int serv_id  = serv.getInt("service_id");
	        	int duration  = serv.getInt("duration");
	        	float serv_cost = serv.getFloat("service_cost")  ;
	        	String serv_name = serv.getString("service_name") ;
	        	String desc = serv.getString("description") ;
	        	Service  sr = new Service(serv_id,duration,serv_name,serv_cost,desc); 
	        	ServList.add(sr);
	        	
	        }
	        ResultSet mainten = st.executeQuery("select * from maintenancerecords ");
	         while (mainten.next()) {
	         int record_id = mainten.getInt("record_id");
	         int car_id = mainten.getInt("car_id");
	         int serv_id = mainten.getInt("service_id");
	         Date sch_date = mainten.getDate("scheduled_date");
	         Date complete_date = mainten.getDate("completion_date");
	         String status = mainten.getString("status");
	         String notes = mainten.getString("service_notes");
	         MaintenanceRecord rc = new MaintenanceRecord(record_id, car_id, serv_id, sch_date, complete_date, status, notes);
	         MaintenList.add(rc);
	         
	        }
	         
	         ResultSet cust = st.executeQuery("select * from  customers");
	         while (cust.next()) {
		        int cust_id = cust.getInt("customer_id");
		        String name = cust.getString("name") ;
		     	String cont_no = cust.getString("contact_number");
		     	String email = cust.getString("email");
		     	String address = cust.getString("address"); 
		     	Timestamp created_at = cust.getTimestamp("created_at") ;
		     	Customer cs = new Customer(cust_id ,name , cont_no, email , address,created_at );
		     	CustomerList.add(cs);
		         
		         }
	         int c ;
	         do {
	                System.out.println("\nCar Service Management ");
	                System.out.println("1. Add Car Service ");
	                System.out.println("2. Scheduled Services ");
	                System.out.println("3. Update Service ");
	                System.out.println("4. View service History ");
	                System.out.println("5. View Customer Details ");
	                System.out.println("0. Exit");
	                System.out.print("Enter your choice: ");
	                c = sc.nextInt();
	                

	                switch (c) {
	                    case 1:
	                             Customer.enterCust();
	                             Car.enterCar();
	                             Service.enterServ();
	                             MaintenanceRecord.enterMaint();
		                   
                       
	                        break;
	                    case 3:
	                    	 System.out.println("Status Update...\n");
	                    	 System.out.println("Enter service id :\n");
	                    	 int id = sc.nextInt();
	                    	 try( PreparedStatement pstmt = con.prepareStatement("UPDATE maintenancerecords SET status = ? ,completion_date = ? WHERE service_id = ?")){	
	                    		  
	                    		  pstmt.setString(1,"Completed");
	                    		  System.out.println("\nEnter the Completion date : \n");
	                    		  String dt =sc.next();
	                    		  pstmt.setString(2, dt);
	                              pstmt.setInt(3,id);
	                              int ru =pstmt.executeUpdate();
	                              if(ru>0) {
	                            	  System.out.println("Updation Sucessfull...");
	                            	  for(int i=0 ;i<ServList.size();i++) {
	                            		  if(id == ServList.get(i).serv_id) {
	                            			  try( PreparedStatement hist = con.prepareStatement("INSERT INTO History (service_log, service_id, status , date) VALUES  VALUES ( ?, ?, ?,?,?)")){	                         
	        		                        	    hist.setString(1, "Service Completed");
	        		                        	    hist.setInt(2, id);
	        		                        	    hist.setString(3,"Completed");
	        		                        	    hist.setString(4,dt);
	        		                    
	        		                             }
	        		                             catch (SQLException e) {
	        		                            	 
	        		                            	 e.printStackTrace();
	        		
	        		                             }
	                            		  }
	                            	  }
	                              }
	                              else {
	                            	  System.out.println("Updation Failed...");
	                              }

	                             }
	                             catch (SQLException e) {
	                            	 
	                            	 e.printStackTrace();
	
	                             }
	                    	 
	
	                        break;
	                    case 2:
	                    	
	                    	for(int i= 0;i<MaintenList.size();i++) {
	                    		if("Scheduled".equals(MaintenList.get(i).status)) {
	                    			 System.out.println("\nThe Sheduled services are : "
	 	            	        	 		+ "\ncar id : " + MaintenList.get(i).car_id + "\nScheduled date : " + MaintenList.get(i).sch_date + "\nNotes : "  + MaintenList.get(i).notes );
	                    		}
	                    	}
	                        break;
	                    case 4:
	                    	hs.viewHistory();
	                    	break ;
	                    case 5 :
	                    	for(int i=0 ;i < CustomerList.size();i++) {
	                    		System.out.println("\nName : "+CustomerList.get(i).name +
	                    				            "\nContact no : "+ CustomerList.get(i).cont_no+
	                    				            "\nEmail : "+ CustomerList.get(i).email + 
	                    				            "\nAdrress : " + CustomerList.get(i).address);
	                    	}
	                    	break ;
	                    case 0:
	                        System.out.println("Exiting application.");
	                        break;
	                    default:
	                        System.out.println("Invalid choice. Try again.");
	               
	                }
	            } while (c!= 0);
	     

	       
	       
	        
	        
	        

		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
