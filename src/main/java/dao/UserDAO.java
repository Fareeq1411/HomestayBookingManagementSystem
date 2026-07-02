package dao;

import java.sql.*;

import model.Guest;
import DBConnection.DBConnection;

public class UserDAO {
	public boolean registerGuest(Guest guest) {

	    boolean success = false;

	    try {

	        Connection conn =
	                DBConnection.getConnection();

	        String sql =
	        "INSERT INTO GUEST(GUESTID,GUESTNAME,GUESTEMAIL,GUESTPHONENUMBER,GUESTPASSWORD) VALUES(GUEST_SEQ.NEXTVAL,?,?,?,?)";

	        PreparedStatement ps =
	                conn.prepareStatement(sql);

	        ps.setString(1, guest.getGuestName());
	        ps.setString(2, guest.getGuestEmail());
	        ps.setString(3, guest.getGuestPhoneNumber());
	        ps.setString(4, guest.getGuestPassword());

	        success = ps.executeUpdate() > 0;

	        conn.close();

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return success;
	}
	public String login(String email, String password) {

	    try {

	        Connection conn =
	                DBConnection.getConnection();

	        // Check Guest

	        String guestSql =
	        "SELECT * FROM GUEST WHERE GUESTEMAIL=? AND GUESTPASSWORD=?";

	        PreparedStatement guestPs =
	        conn.prepareStatement(guestSql);

	        guestPs.setString(1, email);
	        guestPs.setString(2, password);

	        ResultSet guestRs =
	        guestPs.executeQuery();

	        if(guestRs.next()) {

	            conn.close();
	            return "GUEST";
	        }

	        // Check Staff

	        String staffSql =
	        "SELECT STAFFROLES FROM STAFF WHERE STAFFEMAIL=? AND STAFFPASSWORD=?";

	        PreparedStatement staffPs =
	        conn.prepareStatement(staffSql);

	        staffPs.setString(1, email);
	        staffPs.setString(2, password);

	        ResultSet staffRs =
	        staffPs.executeQuery();

	        if(staffRs.next()) {

	            String role =
	            staffRs.getString("STAFFROLES");

	            conn.close();

	            return role;
	        }

	        conn.close();

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}