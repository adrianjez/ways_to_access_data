package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection mConnection = null;
	
	//SINGLENTON
	private DBUtil(){}
	
	public static Connection getConnection() {
        if (mConnection != null)
            return mConnection;
        else {
            try {
            	Class.forName("oracle.jdbc.driver.OracleDriver");
            	mConnection = DriverManager.getConnection(
    					"jdbc:oracle:thin:@oracle4.icis.pcz.pl:1521:oracle4", "JEZADR",
    					"Adrian");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
				e.printStackTrace();
			}
            return mConnection;
        }
    }
}
