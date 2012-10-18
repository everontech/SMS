package kr.go.police.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * 계정 (login, regsiter etc)관련 Dao
 */
public class AccountDAO {
	
	DataSource dataSource;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs, rs1;
	
	public AccountDAO(){
		try{
			
			Context initCtx = new InitialContext();
			Context envCtx=(Context)initCtx.lookup("java:comp/env");
			dataSource = (DataSource)envCtx.lookup("jdbc/smsConn");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
		
	public List getPslist() throws SQLException {

//		 CommonCon의 getConnection Method를 통하여, DBCP에서 커넥션을 가져온다.
		Connection conn = dataSource.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		// 리턴될 데이터 값
		List list = new ArrayList();
		
		try {
			// JDBC에 날릴 쿼리문 작성
			StringBuffer strQuery = new StringBuffer();

			strQuery.append("SELECT * FROM t_pscode ORDER BY F_PSCODE");
			
			// Prepared Statment를 상속받은 LoggableStatment를 통하여 ? 대신 실제 인자값을 확인할 수 있다.
			pstmt = conn.prepareStatement(strQuery.toString() );

			System.out.println( strQuery.toString() );
			
			// 쿼리문을 실행시키자.
			rs = pstmt.executeQuery();
			
			// ResultSet Metat 데이터를 통하여, 컬럼의 갯수와, 컬럼의 이름을 가져오자.
			rsmd = rs.getMetaData();

			// 해당 Row수 만큼 루프를 돌자
			while( rs.next() ) {
				// 1개의 Row에 해당하는 컬럼들을 HashMap에 담을 객체
				HashMap hm = new HashMap();
				
				// 컬럼의 갯수만큼 루프를 돌면서, 컬럼명과 컬럼값을 HashMap 객체에 담는다.
				for(int i = 1 ; i <= rsmd.getColumnCount() ; i++){
					hm.put( rsmd.getColumnName(i), rs.getString(i) );
					System.out.println( rsmd.getColumnName(i) + "   "  +  rs.getString(i) );
				}
				
				// HashMap 객체에 담아진 1개의 Row를 List 객체에 다시 추가
				list.add(hm);

			}
					
		}catch(Exception e ) {
			System.out.println("getPslist() Error : " + e.getMessage() );
		}finally {
			// 다쓴 자원을 반환하자.
			if( rs != null)	try { rs.close(); }catch(Exception e){}
			if( pstmt != null) try { pstmt.close(); }catch(Exception e1){}
			if( conn != null) try { conn.close(); } catch(Exception e2) {}
		}
		
		return list;
	}
	
}
