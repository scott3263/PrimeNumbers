package kr.co.applestar.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DBConnection {
	
	private static Logger logger = Logger.getLogger(DBConnection.class.getName());
	private Connection conn;
	
	private static final String USERNAME = "applestar";
	private static final String PASSWORD = "1235!@#%";
	private static final String URL = "jdbc:mysql://192.168.0.21:3306/applestar";
	
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pstmt = null;
	
	public DBConnection() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			st = conn.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			//logger.info("Database Connection fail! : " + e.getMessage());
						
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO: handle exception
			}
		}
	}
	
	public boolean insertNumber(double d, int term, String hashVal, int duration) {
		
		try {
			String sql = "insert into prime_numbers(prime_num, int_num, hash_value, calc_duration) values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1, d);
			pstmt.setInt(2, term);
			pstmt.setString(3, hashVal);
			pstmt.setInt(4, duration);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 0) {
				//System.out.println("Insert Fail");
				
			} else {
				return true ;
			}
			
		} catch (Exception e) {
			logger.info("Database Insertn fail! : " + d + " "  + e.getMessage());
		}
		return false;
	}
	
	public double getCurrentMaxPrimeNumber() {
		
		try {
			String sql = "SELECT max(prime_num) FROM applestar.prime_numbers";
			rs = st.executeQuery(sql);
			
			if (rs.next()) {
				//logger.info("getCurrentMaxPrimeNumber() : " + rs.getDouble(1));
				
				if (rs.getDouble(1) < 1) {
					return 1;
				} else {
					return rs.getDouble(1);	
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public double getMinNumberBiggerThenInput(double d) {
		try {
			String sql = "SELECT min(prime_num) FROM applestar.prime_numbers where prime_num > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, d);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				//logger.info("[" + d + "]보다 크면서 제일 작은 소수는 [" + rs.getDouble(1) + "]");
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 2;
	}
	
	

}
