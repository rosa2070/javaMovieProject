package zipcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZipcodeDAO {
	private Connection conn;
	
	public ZipcodeDAO() {
		// TODO Auto-generated constructor stub
		String url = "jdbc:mariadb://localhost:3306/project";
		String user = "project";
		String password = "123456";

		try {
			Class.forName( "org.mariadb.jdbc.Driver" );
			this.conn = DriverManager.getConnection( url, user, password );
		} catch( ClassNotFoundException e ) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		} catch( SQLException e ) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		}	
	}
	
	// 시도
	public ArrayList<ZipcodeTO> listSido() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ZipcodeTO> sidos = new ArrayList<ZipcodeTO>();
		
		try {
			String sql = "select distinct sido from zipcode";
			pstmt = this.conn.prepareStatement( sql );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				ZipcodeTO to = new ZipcodeTO();
				to.setSido( rs.getString( "sido" ) );
				sidos.add( to );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
		
		return sidos;
	}
	
	// 구군
	public ArrayList<ZipcodeTO> listGugun( String sido ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ZipcodeTO> guguns = new ArrayList<ZipcodeTO>();
		
		try {
			String sql = "select distinct gugun from zipcode where sido=?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, sido);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipcodeTO to = new ZipcodeTO();
				to.setGugun(rs.getString("gugun"));
				guguns.add(to);
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
		
		return guguns;
	}
	
	// 동
	public ArrayList<ZipcodeTO> listDong( String sido, String gugun ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ZipcodeTO> dongs = new ArrayList<ZipcodeTO>();
		
		try {
			String sql = "select distinct dong from zipcode where sido =? and gugun=?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, sido);
			pstmt.setString(2, gugun);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipcodeTO to = new ZipcodeTO();
				to.setDong(rs.getString("dong"));
				dongs.add(to);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
		
		return dongs;
	}
	
	// 나머지
	public ArrayList<ZipcodeTO> listAddress( String sido, String gugun, String dong ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ZipcodeTO> lists = new ArrayList<ZipcodeTO>();
		
		try {
			String sql = "select zipcode, ri, bunji from zipcode where sido=? and gugun=? and dong=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sido);
			pstmt.setString(2, gugun);
			pstmt.setString(3, dong);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeTO to = new ZipcodeTO();
				to.setZipcode(rs.getString("zipcode"));
				to.setRi(rs.getString("ri"));
				to.setBunji(rs.getString("bunji"));
				
				lists.add(to);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] : " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}
		
		return lists;
	}
}
