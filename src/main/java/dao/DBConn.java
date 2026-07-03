package dao;
import java.sql.*;

public class DBConn {
    
    public static Connection connDB(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            return conn;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
