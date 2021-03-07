package profiles;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import java.awt.geom.RectangularShape;
import java.sql.Blob;

import java.util.ArrayList; 

import java.sql.ResultSet;

public class ProfileDAO {
	private final String url; 
	private final String username; 
	private final String password;
	public ProfileDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	} 
	
	private Connection getConnection() throws SQLException {
		final String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, username, password);
	}
	public Profile getProfile(int id) throws SQLException{
		final String sql = "SELECT * FROM profiles WHERE id = ?";
		
		Profile profile = null;
		Connection conn = getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery(); 
		
		if(rs.next()) {
			String fname = rs.getString("first_name");
			String lname = rs.getString("last_name");
			String college = rs.getString("college_name");
			String highschool = rs.getString("highschool_name");
			UseDate dob = new UseDate(rs.getDate("dob").getTime());
			String email = rs.getString("email_address");
			long phone_number = rs.getLong("phone_number");
			Blob blob = rs.getBlob("profile_pic");
				byte[] data = blob.getBytes(1, (int) blob.length());
			UseDate last_login = new UseDate(rs.getDate("last_login").getTime());
			UseDate last_profile_modified = new UseDate(rs.getDate("last_profile_modified").getTime());
			
			profile = new Profile(id, fname, lname, college, highschool, dob, email, phone_number, data, last_login, last_profile_modified);
		}
		rs.close(); 
		pstmt.close(); 
		conn.close();
		return profile;
	}
	public Profile getProfile(String email_address) throws SQLException{
		final String sql = "SELECT * FROM profiles WHERE email_address = ?";
		
		Profile profile = null;
		Connection conn = getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email_address);
		ResultSet rs = pstmt.executeQuery(); 
		
		if(rs.next()) {
			int id = rs.getInt("id");
			String fname = rs.getString("first_name");
			String lname = rs.getString("last_name");
			String college = rs.getString("college_name");
			String highschool = rs.getString("highschool_name");
			UseDate dob = new UseDate(rs.getDate("dob").getTime());
			String email = rs.getString("email_address");
			long phone_number = rs.getLong("phone_number");
			Blob blob = rs.getBlob("profile_pic");
				byte[] data = blob.getBytes(1, (int) blob.length());
			UseDate last_login = new UseDate(rs.getDate("last_login").getTime());
			UseDate last_profile_modified = new UseDate(rs.getDate("last_profile_modified").getTime());
			
			profile = new Profile(id, fname, lname, college, highschool, dob, email, phone_number, data, last_login, last_profile_modified);
		}
		rs.close(); 
		pstmt.close(); 
		conn.close();
		return profile;
	}
	public boolean verifyProfilePassword(Profile profile, String inputtedPassword) throws SQLException{
		final String sql = "SELECT * FROM userpass WHERE user_id = ?";
		
		Connection conn = getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, Integer.toString(profile.getID()));
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String password = rs.getString("pass");
			return password.equals(inputtedPassword);	
		}
		return false; 
			
	}
	public List<Profile> getProfiles() throws SQLException{
		final String sql = "SELECT * FROM profiles ORDER BY id ASC";
		
		List<Profile> profiles = new ArrayList<>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String fname = rs.getString("first_name");
			String lname = rs.getString("last_name");
			String college = rs.getString("college_name");
			String highschool = rs.getString("highschool_name");
			UseDate dob = new UseDate(rs.getDate("dob").getTime());
			String email = rs.getString("email_address");
			long phone_number = rs.getLong("phone_number");
			Blob blob = rs.getBlob("profile_pic");
				byte[] data = blob.getBytes(1, (int) blob.length());
			UseDate last_login = new UseDate(rs.getDate("last_login").getTime());
			UseDate last_profile_modified = new UseDate(rs.getDate("last_profile_modified").getTime());
			
			profiles.add(new Profile(id, fname, lname, college, highschool, dob, email, phone_number, data, last_login, last_profile_modified));
		}
		rs.close();
		stmt.close();
		conn.close();
		
		return profiles;
	}
	
	/*public List<Profile> getProfiles(String s, boolean isName) throws SQLException{
		final String sql; 
		if(isName) {
			sql = "SELECT * FROM products WHERE name = ? ORDER BY product_id ASC";
		}else {
			sql = "SELECT * FROM products WHERE brand = ? ORDER BY product_id ASC";
		}
		
		List<Profile> products = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, s);
		ResultSet rs = pstmt.executeQuery(); 
		
		while(rs.next()) {
			int id = rs.getInt("product_id");
			String name = rs.getString("name");
			double unit_price = rs.getDouble("unit_price");
			String brand = rs.getString("brand");
			String description = rs.getString("description");
			int stock = rs.getInt("stock");
			
			products.add(new Profile(id, name, unit_price, brand, description, stock));
		}
		rs.close(); 
		pstmt.close(); 
		conn.close(); 
		
		return products;
	}
	public List<Profile> getProfiles(double price) throws SQLException{
		final String sql = "SELECT * FROM products WHERE unit_price = ? ORDER BY product_id ASC";
		
		List<Profile> products = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setDouble(1, price);
		ResultSet rs = pstmt.executeQuery(); 
		
		while(rs.next()) {
			int id = rs.getInt("product_id");
			String name = rs.getString("name");
			double unit_price = rs.getDouble("unit_price");
			String brand = rs.getString("brand");
			String description = rs.getString("description");
			int stock = rs.getInt("stock");
			
			products.add(new Profile(id, name, unit_price, brand, description, stock));
		}
		rs.close(); 
		pstmt.close(); 
		conn.close(); 
		
		return products;
		
		
	}*/
	public boolean insertProfile(Profile profile, String password) throws SQLException{
		final String sql = "INSERT INTO profiles "
		+ "(first_name, last_name, college_name, highschool_name, dob, email_address, phone_number, profile_pic, last_login, last_profile_modified)"
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 		
		Connection conn = getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, profile.getFirst_name());
		pstmt.setString(2, profile.getLast_name());
		pstmt.setString(3, profile.getCollege_name());
		pstmt.setString(4, profile.getHighschool_name());
		pstmt.setDate(5, new Date(profile.getDob().getMillis()));
		pstmt.setString(6, profile.getEmail_address());
		pstmt.setLong(7, profile.getPhone_number());
		pstmt.setBlob(8, new SerialBlob(profile.getProfilePic())); 
		pstmt.setDate(9, new Date(profile.getLast_login().getMillis()));
		pstmt.setDate(10, new Date(profile.getLast_profile_modified().getMillis()));

		insertUserPass(profile, password);
		int affected = pstmt.executeUpdate(); 
		pstmt.close(); 
		conn.close();
		
		return affected == 1; 
	}

	public boolean updateProfile(Profile profile) throws SQLException {
		final String sql = "UPDATE profiles SET first_name = ?, last_name = ?, college_name = ?, highschool_name = ?, dob = ?, email_address = ?, phone_number = ?, profile_pic = ?, last_login = ?, last_profile_modified = ?";
		Connection conn = getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, profile.getFirst_name());
		pstmt.setString(2, profile.getLast_name());
		pstmt.setString(3, profile.getCollege_name());
		pstmt.setString(4, profile.getHighschool_name());
		pstmt.setDate(5, new Date(profile.getDob().getMillis()));
		pstmt.setString(6, profile.getEmail_address());
		pstmt.setLong(7, profile.getPhone_number());
		pstmt.setBlob(8,  new SerialBlob(profile.getProfilePic())); 
		pstmt.setDate(9, new Date(profile.getLast_login().getMillis()));
		pstmt.setDate(10, new Date(profile.getLast_profile_modified().getMillis()));
		int affected = pstmt.executeUpdate();
		
		pstmt.close(); 
		conn.close();
		
		
		return affected == 1; 
		
	}
	
	public boolean deleteProfile(Profile profile) throws SQLException {
		final String sql = "DELETE FROM products WHERE id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, profile.getID());
		int affected = pstmt.executeUpdate();
		
		return affected == 1; 
		
	}
	public boolean insertUserPass(Profile profile, String password) throws SQLException {
		final String sql = "INSERT INTO userpass "+ "(user_id, pass)"+"VALUES(?, ?)";
		Connection conn = getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, profile.getID());
		pstmt.setString(2, password);
		
		int affected = pstmt.executeUpdate(); 
		pstmt.close(); 
		conn.close(); 
		return affected ==1;
		
	}
}
