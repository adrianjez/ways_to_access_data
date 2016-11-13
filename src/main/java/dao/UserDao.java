package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DBUtil;

public class UserDao {
	
	private static final String TAG = "UserDAO";
	private Connection mConnection;
	
	public UserDao(){
		this.mConnection = DBUtil.getConnection();
	}
	
	public List<User> getAllUsers(){
		ArrayList<User> result = new ArrayList();
		try {
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from OSOBA order by 1");
			while (rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("ID"));
				user.setImie(rs.getString("IMIE"));
				user.setNazwisko(rs.getString("NAZWISKO"));
				user.setDataUrodzenia(rs.getDate("DATA_URODZENIA"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTelefon(rs.getString("TELEFON"));
				result.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TAG + "METHOD getAllUser result count" + result.size());
		return result;
	}
	
	public boolean addNewUser(User user){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("INSERT INTO OSOBA(IMIE, NAZWISKO, DATA_URODZENIA, EMAIL, TELEFON) "
							+ "VALUES (?, ?, ?, ?, ?)");
			statement.setString(1, user.getImie());
			statement.setString(2, user.getNazwisko());
			statement.setDate(3, new java.sql.Date(user.getDataUrodzenia().getTime()));
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getTelefon());
			
			int rowInserted = statement.executeUpdate();
			if(rowInserted > 0) {
				System.out.println("A new User has been created successfully");
				return true;
			} else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/** Should not happen **/
		System.err.println(TAG + "Unknown error in addNewUser Method");
		return false;
	}
	
	/** 
	 * Returns user by given ID
	 * @param id
	 * @return
	 */
	public User getUserById(int id){
		try{
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from OSOBA WHERE ID=" + id);
			if (rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("ID"));
				user.setImie(rs.getString("IMIE"));
				user.setNazwisko(rs.getString("NAZWISKO"));
				user.setDataUrodzenia(rs.getDate("DATA_URODZENIA"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTelefon(rs.getString("TELEFON"));
				return user;
			}
		} catch (SQLException err){
			err.printStackTrace();
		}

		/** Should not happen **/
		System.err.println(TAG + "Unknown error in getUserById Method");
		return null;
	}

	/**
	 * Updates existing user 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("UPDATE OSOBA SET IMIE=?, NAZWISKO=?, DATA_URODZENIA=?, EMAIL=?, TELEFON=?"
							+ "WHERE ID = ?");
			statement.setString(1, user.getImie());
			statement.setString(2, user.getNazwisko());
			statement.setDate(3, new java.sql.Date(user.getDataUrodzenia().getTime()));
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getTelefon());
			statement.setInt(6, user.getUserId());
			
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("An exisiting user has been updated successfukly");
				return true;
			} else return false;
		} catch (SQLException err){
			err.printStackTrace();
		}
		/** Should not happen **/
		System.err.println(TAG + "Unknown error in updateUser Method");
		return false;
	}
	
	
	public boolean deleteUser(int userId){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("DELETE FROM OSOBA WHERE ID=?");
			statement.setInt(1,  userId);
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("An exisiting user has been updated successfukly");
				return true;
			} else return false;
		} catch (Exception err){
			err.printStackTrace();
		}
		/** Should not happen **/
		System.err.println(TAG + "Unknown error in deleteUser Method");
		return false;
	}
	
}
