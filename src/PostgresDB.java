/**
 * 
 * @author Neil Mackenzie
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB extends Db{

	public static void dbConnect ( String url, String username, String password, String dataBaseType, boolean read_only ) throws SQLException, VTLError
	{
		Connection 	newConnection ;
		
		try {	
			if ( DbConnection == null )
				DriverManager.registerDriver( new org.postgresql.Driver() );
			// Sys.println ( "Creating new connection ************") ;

			if ( Connected ) 
				disconnectDb ( ) ;
			/** try {
				String pureUrl = url.substring(url.indexOf("@")+1);
				url = "jdbc:oracle:thin:@" + pureUrl.trim() ; 
				username = username.trim().toUpperCase () ;
				password = password.trim() ; 
			}
			catch (Exception e) {
				VTLError.RunTimeError( e . toString () ) ;
			}*/

			newConnection = DriverManager.getConnection( url, username, password) ;	
			
		    DbConnection = newConnection ;
		    DbConnection.setAutoCommit ( false ) ;
		    sql_statement = DbConnection.createStatement () ;
		    Connected = true ;
		}
		catch ( SQLException e ) {
			e.printStackTrace();
			VTLError . SqlError ( e . toString () ) ;
			return ;
		}
		
		db_url = url ;
		db_username = username ;
		db_password = password ;
		db_readonly = read_only ;
	  
		if ( read_only )
			DbConnection.setReadOnly ( true ) ;
	}
	
	
}
