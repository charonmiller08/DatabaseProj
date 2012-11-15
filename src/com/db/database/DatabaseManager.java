package com.db.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.db.ncsu.command.CommandArgument;

public class DatabaseManager {
	private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	

	// Put your oracle ID and password here
	private static final String user = "bijohnso";
	private static final String password = "brittchels1";

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;

	
	private static DatabaseManager dm = new DatabaseManager();
	static SimpleDateFormat format =
            new SimpleDateFormat("MM/dd/yy");
	
	private DatabaseManager()
	{
		initialize();	
	}
	
	
	
	public static boolean runPreparedStatement(String sql, CommandArgument[] args, boolean isSelect)
	{		
		boolean result = false;
		try {
			PreparedStatement stat =  connection.prepareStatement(sql);
			
			int i=1;
			for (CommandArgument arg : args)
			{
				if (arg.getType().equals("Int"))
				{
					stat.setInt(i, Integer.parseInt(arg.getValue().toString()));
					//sql = sql.replaceFirst(Pattern.quote("?"), arg.getValue().toString());
				}
				else if (arg.getType().equals("String"))
				{
					stat.setString(i, arg.getValue().toString());
					///sql = sql.replaceFirst(Pattern.quote("?"), "'"+arg.getValue().toString()+"'");
				}
				else if (arg.getType().equals("Float")) 
				{
					stat.setFloat(i, Float.parseFloat(arg.getValue().toString()));
					
				}
				else if (arg.getType().equals("Date"))
				{
					java.util.Date d = null;
					try {
						d = format.parse(arg.getValue().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					java.sql.Date d2 = new java.sql.Date(d.getTime());
					stat.setDate(i, d2);
					///sql = sql.replaceFirst(Pattern.quote("?"), "'"+arg.getValue().toString()+"'");
				}
				
				else System.out.println("UNKNOWN TYPE");
				i++;
			}
			if (!isSelect)
			{
				stat.executeUpdate();
				result = true;
			}
			else
			{
				ResultSet re = stat.executeQuery();
				ResultSetMetaData meta = re.getMetaData();
			    int cols = meta.getColumnCount();

			    // we dump the column information about the result set
		        System.out.print("Row Number\t");
			    for (i=1; i <= cols; i++)
			    {
			        System.out.print(meta.getColumnName(i)+ "\t");
			    }

			    // and finally, we dump the result set
			    int cnt = 1;			   
			    while(re.next())
			    {
			        System.out.print("\n"+cnt+"\t\t");
			        for (i=1; i <= cols; i++) {
			            System.out.print(re.getString(i)+"\t");
			        }
			        cnt++;
			    }
			    System.out.println();
			    result = true;
			    
			}
			stat.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

	
	
	
	private static void initialize() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

		

	private static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
