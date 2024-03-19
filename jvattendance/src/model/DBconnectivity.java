package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnectivity {
    private static Connection con=null;

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3307/jvproject";
            String user="root";
            String pass="";
            con=DriverManager.getConnection(url,user,pass);
            return con;
        }
        catch(ClassNotFoundException | SQLException e){
            throw new SQLException("DB Connection failed.");
        }
    }
}
