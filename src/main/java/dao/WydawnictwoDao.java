package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ksiazka;
import model.Wydawnictwo;
import util.DBUtil;

@Deprecated
public class WydawnictwoDao {
	

	
	private static final String TAG = "WydawnictwoDAO";
	private Connection mConnection;
	
	public WydawnictwoDao(){
		this.mConnection = DBUtil.getConnection();
	}
	
	
	public List<Wydawnictwo> getAllPublishers(){
		ArrayList<Wydawnictwo> result = new ArrayList<Wydawnictwo>();
		try {
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from " + Wydawnictwo.TABLE_NAME);
			while (rs.next()){
				Wydawnictwo wyd = new Wydawnictwo();
				wyd.setId(rs.getInt("ID"));
				wyd.setNazwa(rs.getString("NAZWA"));
				wyd.setAdres(rs.getString("ADRES"));
				wyd.setTelefon(rs.getString("TELEFON"));
				wyd.setEmail(rs.getString("EMAIL"));
				wyd.setStronaWWW(rs.getString("STRONA_WWW"));
				result.add(wyd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TAG + "METHOD getAllPublishers result count" + result.size());
		return result;
	}


	public boolean addNewPublisher(Wydawnictwo publisher){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("INSERT INTO ?(NAZWA, ADRES, TELEFON, EMAIL, STRONA_WWW) "
							+ "VALUES (?, ?, ?, ?, ?)");
			statement.setString(1, Wydawnictwo.TABLE_NAME);
			statement.setString(2, publisher.getNazwa());
			statement.setString(3, publisher.getAdres());
			statement.setString(4, publisher.getTelefon());
			statement.setString(5, publisher.getEmail());
			statement.setString(6, publisher.getStronaWWW());
			
			int rowInserted = statement.executeUpdate();
			if(rowInserted > 0) {
				System.out.println("A new Publisher has been created successfully");
				return true;
			} else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/** Should not happen **/
		System.err.println(TAG + "Unknown error in addNewPublisher Method");
		return false;
	}
	
	/** 
	 * Returns Publisher by given ID
	 * @param id
	 * @return
	 */
	public Wydawnictwo getBookById(int id){
		final String SQLStatement = "select * from " + Wydawnictwo.TABLE_NAME + " WHERE ID=" + id;
		try{
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery(SQLStatement);
			if (rs.next()){
				Wydawnictwo wyd = new Wydawnictwo();
				wyd.setId(rs.getInt("ID"));
				wyd.setNazwa(rs.getString("NAZWA"));
				wyd.setAdres(rs.getString("ADRES"));
				wyd.setTelefon(rs.getString("TELEFON"));
				wyd.setEmail(rs.getString("EMAIL"));
				wyd.setStronaWWW(rs.getString("STRONA_WWW"));
				return wyd;
			}
		} catch (SQLException err){
			err.printStackTrace();
		}

		/** Should not happen **/
		System.err.println(TAG + "Unknown error in getPublisherById Method");
		return null;
	}
	
	/**
	 * Updates existing Publisher 
	 * @param user
	 * @return
	 */
	public boolean updateBook(Wydawnictwo publisher){
		try {
			
			PreparedStatement statement = 
					mConnection.prepareStatement("UPDATE ? SET NAZWA=?, ADRES=?, TELEFON=?, EMAIL=?, STRONA_WWW=? WHERE ID=?");
			statement.setString(1, Wydawnictwo.TABLE_NAME);
			statement.setString(2, publisher.getNazwa());
			statement.setString(3, publisher.getAdres());
			statement.setString(4, publisher.getTelefon());
			statement.setString(5, publisher.getEmail());
			statement.setString(6, publisher.getStronaWWW());
			statement.setInt(7, publisher.getId());
			
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("An exisiting publisher has been updated successfukly");
				return true;
			} else return false;
		} catch (SQLException err){
			err.printStackTrace();
		}
		/** Should not happen **/
		System.err.println(TAG + "Unknown error in updatePublisher Method");
		return false;
	}
	
	/**
	 * Deletes given publisher
	 * @param publisher
	 * @return
	 */
	public boolean deletePublisher(Wydawnictwo publisher){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("DELETE FROM ? WHERE ID=?");
			statement.setString(1, Wydawnictwo.TABLE_NAME);
			statement.setInt(2,  publisher.getId());
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("An exisiting publisher has been deleted successfukly");
				return true;
			} else return false;
		} catch (Exception err){
			err.printStackTrace();
		}
		/** Should not happen **/
		System.err.println(TAG + "Unknown error in deletePublisher Method");
		return false;
	}
}
