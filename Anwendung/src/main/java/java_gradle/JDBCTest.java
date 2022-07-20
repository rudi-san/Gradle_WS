package java_gradle;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.*;

public class JDBCTest {

	private static Logger 			logger	= LogManager.getLogger(JDBCTest.class);
	private static Connection 		con		= null;
	private static Statement		stmt	= null;
	private static final String		DBURL 	= "jdbc:ucanaccess://C:\\Users\\Rudi\\Documents\\TestDb.accdb";
	private static final String		SQL		= "select vorname, nachname, gebdatum, schuhgroesse from TestTabelle01 " +
											  "where ID in ";
	public static void main(String[] args) {

//		logger.addAppender(new ConsoleAppender(new SimpleLayout()));
//		logger.setLevel(Level.WARN);
		System.out.println("logger.isWarnEnabled()="+logger.isWarnEnabled());
		System.out.println("logger.isInfoEnabled()="+logger.isInfoEnabled());
		int[] zeilen = { 1 };
		List<String[]> list = getEntries(zeilen);
		System.out.println(Arrays.toString(list.get(0)));
	}
	
	public static boolean connect () {
		if (con==null) {
			try {
				logger.info	("DB-Connection wird aufgebaut");
				con 		= DriverManager.getConnection(DBURL);
				stmt 		= con.createStatement(); 
			} catch (SQLException e) {
				logger.fatal(e.getMessage());
				return false;
			}
			return true;
		}
		else {
			return true;	
		}

	}
	
	public static List<String[]> getEntries (int[] zeilen) {
		
		List<String[]> list		= new ArrayList<>();
		if (connect()) {
			String inClause = "(";
			boolean first 	= true;
			for (int i : zeilen) {
				inClause 		+= (first) ? i : ","+i;
				first			= false;
			}
			inClause		+= ")";
			try {
				ResultSet rs = stmt.executeQuery(SQL + inClause);
				while (rs.next())	{
					String[] erg	= new String[4];
					for (int i=0;i<erg.length;++i) 	
						erg[i]		= rs.getString(i+1);
					list.add		(erg);
				}
			} catch (SQLException e) {
				logger.fatal(e.getMessage());
			}
		}
		return list;
	}

}
