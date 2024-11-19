package ServiceManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
public class Customer {

	int cust_id ;
	String name ;
	String cont_no;
	String email ;
	String address; 
	Timestamp created_at ;
 public Customer(int cust_id ,
	String name ,
	String cont_no ,
	String email  ,
	String address ,
	Timestamp created_at) {
	     this.cust_id =cust_id;
		 this.name = name;
		 this.cont_no = cont_no ;
		 this.email = email ;
		 this.address = address ; 
		 this.created_at = created_at ;
	 
 }
 
 public static void enterCust() {
	 String url = "jdbc:mysql://localhost:3306/cm";
	 String user = "root";
	 String password = "Mugilan205!";
	 Scanner sc = new Scanner(System.in);
	 try(Connection con = DriverManager.getConnection(url, user, password) ){
	  PreparedStatement pstmt = con.prepareStatement("INSERT INTO customers ( name, contact_number, email, address) VALUES ( ?, ?, ?,?)");
   	  System.out.println("\n---Enter Customer details----\n");	 
         System.out.println("Enter Name :\n");
         pstmt.setString(1, sc.next());
         System.out.println("Enter Contact Number :\n");
         pstmt.setString(2, sc.next());
         System.out.println("Enter Email :\n");
         pstmt.setString(3, sc.next());
         System.out.println("Enter Adress :\n");
         pstmt.setString(4, sc.next());
         pstmt.executeUpdate();
         System.out.println("Enter car details :");
        }
        catch (SQLException e) {
       	 e.printStackTrace();
        }
 }


	

}
