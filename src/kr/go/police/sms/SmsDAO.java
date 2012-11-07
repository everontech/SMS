package kr.go.police.sms;

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

import kr.co.police.CommonCon;
import kr.go.police.account.UserBean;
import kr.go.police.board.BoardBean;


/**
 * 문자 관리 Dao
 */
public class SmsDAO extends CommonCon {
	DataSource dataSource;
	
	public SmsDAO(){
		dataSource = getDataSource();
		if(dataSource == null){
			return;
		}
	}
	
	
	protected List<Message> getMyMessage(int userIndex, int groupIndex){
		List<Message> list = new ArrayList<Message>();
		Message data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM message WHERE f_user_index =?  ");
			pstmt.setInt(1, userIndex);
			//pstmt.setInt(2, groupIndex);			
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
				// 인덱스, 등록아이디, 제목, 내용, 그룹인덱스, 그룹명
			    data = new Message();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  		
			    data.setTitle(rs.getString("f_message_title"));			    
			    data.setMessage(rs.getString("f_message_text"));
			    data.setGroupIndex(rs.getString("f_group_index"));
			    data.setGroup(rs.getString("f_message_group"));
	
				list.add(data);
			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getMyMessage 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}
	

	/**
	 * 예약 내역 리스트 가져오기
	 * @param userIndex
	 * 	유저 인덱스
	 * @param pae
	 * 	페이지
	 * @param limit
	 *  한페이지수
	 * @return
	 */
	protected List<SMS> getReservedList(int userIndex, int page, int limit){
		List<SMS> list = new ArrayList<SMS>();
		SMS data = null;		
		int startRow = (page -1 ) * 10 +1;		// 시작 번호
		int endRow = startRow + limit -1;		// 끝 번호
		try {
			conn = dataSource.getConnection();
			// 해당 유저의 예약된 문자내역만 가져온다.
			pstmt = conn.prepareStatement("SELECT * FROM send_sms_info " +
					"WHERE f_reserve_date != null " +
					"AND f_user_index = ? " +
					"ORDER BY f_index DESC " +
					"LIMIT ?, ?");
			pstmt.setInt(1, userIndex);			
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);			
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
			    data = new SMS();	
			    data.setId(rs.getString("f_id"));
			    data.setMessage(rs.getString("f_message"));
			    data.setToPhone(rs.getString("f_to_phone"));
			    data.setFromPhone(rs.getString("f_from_phone"));			    
			    data.setReserveDate(rs.getString("f_reserve_date"));	
			    data.setUserIndex(rs.getInt("f_user_index"));	
			    
				list.add(data);
  			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getReservedList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
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
