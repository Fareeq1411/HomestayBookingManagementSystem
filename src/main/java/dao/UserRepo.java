package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserRepo {
    
    public static User checkLogin(String email, String password){
        try{
            Connection conn = DBConn.connDB();
            
            String sql = "SELECT email FROM users WHERE email=? and password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if(!rs.next()){
                User user = new User();
                user.setId(rs.getString("id"));

                return user;
            }else{
                return null;
            }

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
