package utilities;

import java.sql.*;
import java.util.Random;
import dao.DBConn;
import model.User;

public class Utility {
    

    public static String generateId(String databaseName, String idColumnName){

        boolean isUnique = false;

        while(!isUnique){
            Random random = new Random();
            int id = 10000 + random.nextInt(90000);

            try{

                Connection conn = DBConn.connDB();
                String sql = "SELECT" + idColumnName +  "FROM" + databaseName + "WHERE" + idColumnName + "= ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, String.valueOf(id));

                ResultSet rs = stmt.executeQuery();

                if(!rs.next()){
                    isUnique = true;
                    return String.valueOf(id);
                }

            }catch(Exception e){
                continue;
            }
        }

        return null;

    }
}
