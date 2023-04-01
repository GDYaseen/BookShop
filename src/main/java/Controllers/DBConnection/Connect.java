package Controllers.DBConnection;

import java.sql.*;

public class Connect{
    public static Connection con=null;
    static{
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/BookShop", "root", "");
			System.out.println("Connection established");
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static Connection getInstance(){
        return con;
    }
}