package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ksiazka;
import model.User;
import util.CommonUtils;
import util.DBUtil;

public class KsiazkaDao {
	
	private static final String TAG = "KsiazkaDAO";
	private Connection mConnection;
	
	public KsiazkaDao(){
		this.mConnection = DBUtil.getConnection();
	}
	
	public List<Ksiazka> getAllBooks(){
		ArrayList<Ksiazka> result = new ArrayList();
		try {
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from BOOKS");
			while (rs.next()){
				Ksiazka ksiazka = new Ksiazka();
				ksiazka.setId(rs.getInt("ID"));
				ksiazka.setTytul(rs.getString("TYTUL"));
				ksiazka.setISBN(rs.getString("ISBN"));
				ksiazka.setRokWydania(rs.getInt("ROK_WYDANIA"));
				ksiazka.setOpis(CommonUtils.clobToString(rs.getClob("OPIS")));
				ksiazka.setURLOkladki(rs.getString("URL_OKLADKI"));
				result.add(ksiazka);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TAG + "METHOD getAllBooks result count" + result.size());
		return result;
	}
	
	public boolean addNewBook(Ksiazka book){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("INSERT INTO BOOKS(TYTUL, ISBN, ROK_WYDANIA, OPIS, URL_OKLADKI) "
							+ "VALUES (?, ?, ?, ?, ?)");
			//statement.setString(1, Ksiazka.TABLE_NAME);
			statement.setString(1, book.getTytul());
			statement.setString(2, book.getISBN());
			statement.setInt(3, book.getRokWydania());
			statement.setString(4,  book.getOpis());
			statement.setString(5, book.getURLOkladki());
			
			int rowInserted = statement.executeUpdate();
			if(rowInserted > 0) {
				System.out.println("A new Book has been created successfully");
				return true;
			} else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/** Should not happen **/
		System.err.println(TAG + "Unknown error in addNewBook Method");
		return false;
	}
	
	/** 
	 * Returns book by given ID
	 * @param id
	 * @return
	 */
	public Ksiazka getBookById(int id){
		final String SQLStatement = "select * from BOOKS WHERE ID=" + id;
		try{
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery(SQLStatement);
			if (rs.next()){
				Ksiazka ksiazka = new Ksiazka();
				ksiazka.setId(rs.getInt("ID"));
				ksiazka.setTytul(rs.getString("TYTUL"));
				ksiazka.setISBN(rs.getString("ISBN"));
				ksiazka.setRokWydania(rs.getInt("ROK_WYDANIA"));
				ksiazka.setOpis(CommonUtils.clobToString(rs.getClob("OPIS")));
				ksiazka.setURLOkladki(rs.getString("URL_OKLADKI"));
				return ksiazka;
			}
		} catch (SQLException err){
			err.printStackTrace();
		}

		/** Should not happen **/
		System.err.println(TAG + "Unknown error in getBookById Method");
		return null;
	}

	/**
	 * Updates existing user 
	 * @param user
	 * @return
	 */
	public boolean updateBook(Ksiazka book){
		try {
			
			PreparedStatement statement = 
					mConnection.prepareStatement("UPDATE BOOKS SET TYTUL=?, ISBN=?, ROK_WYDANIA=?, OPIS=?, URL_OKLADKI=? "
							+ "WHERE ID=?");
			statement.setString(1, book.getTytul());
			statement.setString(2, book.getISBN());
			statement.setInt(3, book.getRokWydania());
			statement.setString(4,  book.getOpis());
			statement.setString(5, book.getURLOkladki());
			statement.setInt(6, book.getId());
		
			
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("An exisiting user has been updated successfully");
				return true;
			} else return false;
		} catch (SQLException err){
			err.printStackTrace();
		}
		/** Should not happen **/
		System.err.println(TAG + "Unknown error in updateBook Method");
		return false;
	}
	
	/** 
	 * Removes book by given ID
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(int bookId){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("DELETE FROM BOOKS WHERE ID=?");
			statement.setInt(1,  bookId);
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("An exisiting user has been updated successfukly");
				return true;
			} else return false;
		} catch (Exception err){
			err.printStackTrace();
		}
		/** Should not happen **/
		System.err.println(TAG + "Unknown error in deleteBook Method");
		return false;
	}
}
