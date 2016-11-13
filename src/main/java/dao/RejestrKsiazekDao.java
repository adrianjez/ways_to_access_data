package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.BookTransaction;
import model.ExtendedBook;
import model.Ksiazka;
import model.User;
import util.CommonUtils;
import util.DBUtil;

public class RejestrKsiazekDao {

	private static final String TAG = "RejestrKsiazek";
	private Connection mConnection;
	private UserDao userDao;
	
	public RejestrKsiazekDao() {
		mConnection = DBUtil.getConnection();
		this.userDao = new UserDao();
	}
	
	public List<ExtendedBook> getAllBooks(){
		ArrayList<ExtendedBook> result = new ArrayList();
		try {
			Statement statement = mConnection.createStatement();
			String query = "SELECT  B.ID, B.TYTUL, B.ISBN, B.ROK_WYDANIA, B.OPIS, B.URL_OKLADKI,"
					+ "(SELECT COUNT(*) "
					+ "FROM BOOKSREGISTRY R "
					+ "WHERE R.ID_KSIAZKI = B.ID AND R.ZWROT IS NULL) AS \"COUNT_USERS\" "
							+ "FROM BOOKS B ORDER BY 1";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()){
				ExtendedBook ksiazka = new ExtendedBook();
				ksiazka.setId(rs.getInt("ID"));
				ksiazka.setTytul(rs.getString("TYTUL"));
				ksiazka.setISBN(rs.getString("ISBN"));
				ksiazka.setRokWydania(rs.getInt("ROK_WYDANIA"));
				ksiazka.setOpis(CommonUtils.clobToString(rs.getClob("OPIS")));
				ksiazka.setURLOkladki(rs.getString("URL_OKLADKI"));
				ksiazka.setCurrentUsers(rs.getInt("COUNT_USERS"));
				result.add(ksiazka);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TAG + "METHOD getAllExtendedBooks result count" + result.size());
		return result;
	}
	
	public boolean lendBook(int bookID, int userID){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("INSERT INTO BOOKSREGISTRY(ID_KSIAZKI, ID_OSOBY, WYDANO) "
							+ "VALUES (?, ?, ?)");
			statement.setInt(1, bookID);
			statement.setInt(2, userID);
			statement.setDate(3, new Date(System.currentTimeMillis()));
			
			int rowInserted = statement.executeUpdate();
			if(rowInserted > 0) {
				System.out.println("Book has been lent successfully");
				return true;
			} else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/** Should not happen **/
		System.err.println(TAG + "Unknown error in lendBook Method");
		return false;
	}
	
	
	public boolean returnBook(int bookID){
		try {
			PreparedStatement statement = 
					mConnection.prepareStatement("UPDATE BOOKSREGISTRY SET ZWROT=? "
							+ "WHERE ID_KSIAZKI = ? AND ZWROT IS NULL");
			statement.setDate(1, new Date(System.currentTimeMillis()));
			statement.setInt(2, bookID);
			
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("An exisiting book has been returned successfukly");
				return true;
			} else return false;
		} catch (SQLException err){
			err.printStackTrace();
		}
		/** Should not happen **/
		System.err.println(TAG + "Unknown error in updateUser Method");
		return false;
	}
	
	
	public ArrayList<BookTransaction> getListOfBookTransactions(int bookID){
		ArrayList<BookTransaction> result = new ArrayList<BookTransaction>();
		try {
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from BOOKSREGISTRY where ID_KSIAZKI =" + bookID + " order by 1");
			while (rs.next()){
				BookTransaction trans = new BookTransaction();
				trans.setId(rs.getInt("ID"));
				trans.setData_wydania(rs.getDate("WYDANO"));
				trans.setData_zwrotu(rs.getDate("ZWROT"));
				trans.setOsoba(this.userDao.getUserById(rs.getInt("ID_OSOBY")));
				result.add(trans);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TAG + "METHOD getListOfBookTransactions result count" + result.size());
		return result;
	}
	
	@Deprecated
	public boolean isBookTaken(int bookID){
		try {
			PreparedStatement statement =  mConnection.prepareStatement("SELECT COUNT(*) FROM BOOKSREGISTRY "
					+ "WHERE ID_KSIAZKI = ? AND ZWROT IS NULL");
			statement.setInt(1, bookID);
			ResultSet rs = statement.executeQuery();
			rs.next();
			int countOfRows = rs.getInt(1);
			return countOfRows > 0;
		} catch (SQLException err){
			err.printStackTrace();
		}
		return true;
	}
}
