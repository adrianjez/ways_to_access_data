package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class CommonUtils {
	
	
	public static String clobToString(java.sql.Clob data)
	{
	    final StringBuilder sb = new StringBuilder();

	    try{
	        final Reader         reader = data.getCharacterStream();
	        final BufferedReader br     = new BufferedReader(reader);

	        int b;
	        while(-1 != (b = br.read())){
	            sb.append((char)b);
	        }

	        br.close();
	    }
	    catch (SQLException e){
	        e.printStackTrace();
	        return e.toString();
	    }
	    catch (IOException e){
	        e.printStackTrace();
	        return e.toString();
	    }
	    return sb.toString();
	}
}
