package movieRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieRecordDAO {
	private Connection conn;
	
	public MovieRecordDAO() {
		String url = "jdbc:mariadb://localhost:3306/movie";
		String user = "root";
		String password = "123456";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		}
		
	}
	
	public void insertRecords(String title, String date, String rate, String comm) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into movie_record (title, date, rate, comm) values (?, ?, ?, ?);";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, date);
			pstmt.setString(3, rate);
			pstmt.setString(4, comm);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	        System.out.println("[에러] : " + e.getMessage());
		} finally {
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
	}
	
	public void deleteRecords(String title) {
		PreparedStatement pstmt = null;

		try {
			String sql = "delete from movie_record where title = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		}finally {
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
	}
	
	public ArrayList<MovieRecordDTO> recordList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MovieRecordDTO> movieRecords = new ArrayList<MovieRecordDTO>();
		
		try {
			String sql = "select title, date, rate, comm from movie_record";
			pstmt = this.conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieRecordDTO to = new MovieRecordDTO();
				to.setTitle(rs.getString("title"));
				to.setDate(rs.getString("date"));
				to.setRate(rs.getString("rate"));
				to.setComm(rs.getString("comm"));
				movieRecords.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
		
		return movieRecords;
							
	}
	
	public void updateRecords(String title, String date, String rate, String comm) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update movie_record set date = ?, rate = ?, comm = ? where title = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, rate);
			pstmt.setString(3, comm);
			pstmt.setString(4, title);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
	}
		
	
}
