package ServiceManagement;

import java.sql.* ;

public class History implements ServiceHistoryViewer {
	
	String url = "jdbc:mysql://localhost:3306/cm";
	String user = "root";
	String password = "Mugilan205!";
    
    public void viewHistory() {
   try( Connection con = DriverManager.getConnection(url, user, password)){
	   
   
    Statement st = con.createStatement();
    ResultSet his = st.executeQuery("select * from  History");
     while(his.next()) {
     	System.out.println("\nService Id : "+his.getInt("service_id")
     	                   + "\nLog :  "+his.getString("service_log")
     	                   + "\nStatus  :  "+his.getString("status")
     	                   + "\ndate :  "+his.getString("date"));
     }
    }
    
    catch(SQLException e) {
    	e.printStackTrace();
     }

}
}
