package kr.go.police.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import kr.go.police.CommonCon;
import kr.go.police.aria.Aria;

/**
 * 문자 전송, 내역 관리
 * 문자함 관련 Dao
 */
public class SmsDAO extends CommonCon {
	DataSource dataSource;
	public ResultSet rs;
	public PreparedStatement pstmt;
	public Connection conn;
	
	public SmsDAO(){
		dataSource = getDataSource();
		if(dataSource == null){
			return;
		}
	}
	
	/**
	 * 내 문자함 목록
	 * @param userIndex
	 * 	유저 인덱스
	 * @param groupIndex
	 * 	그룹 인덱스
	 * @return
	 * 메시지 리스트
	 */
	protected List<Message> getMyMessages(int userIndex, int groupIndex){
		List<Message> list = new ArrayList<Message>();
		Message data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM message WHERE" +
					" f_user_index =?  AND f_group_index = ? ");
			pstmt.setInt(1, userIndex);
			pstmt.setInt(2, groupIndex);
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
				// 인덱스, 등록아이디, 제목, 내용, 그룹인덱스, 그룹명
			    data = new Message();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  		
			    data.setTitle(rs.getString("f_message_title"));			    
			    data.setMessage(rs.getString("f_message_text"));
			    data.setGroupIndex(rs.getInt("f_group_index"));
			    data.setGroup(rs.getString("f_message_group"));
	
				list.add(data);
			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getMyMessages 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}

	/**
	 * 	선택한 문자함 메세지 보기
	 * @param index
	 * 		메시지 인덱스
	 * @return
	 */
	protected Message getMyMessage(int index){
		Message data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM message WHERE" +
					" f_index =?  ");
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				// 인덱스, 등록아이디, 제목, 내용, 그룹인덱스, 그룹명
			    data = new Message();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  		
			    data.setTitle(rs.getString("f_message_title"));			    
			    data.setMessage(rs.getString("f_message_text"));
			    data.setGroupIndex(rs.getInt("f_group_index"));
			    data.setGroup(rs.getString("f_message_group"));
			}		
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getMyMessage 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}	
	
	/**
	 * 내 문자함 목록
	 * @param userIndex
	 * 	유저 인덱스
	 * @param groupIndex
	 * 	그룹 인덱스
	 * @return
	 * 메시지 리스트
	 */
	/*
	public List<Message> getMessageGroupList(int userIndex){
		List<Message> list = new ArrayList<Message>();
		Message data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM message_group WHERE" +
					" f_user_index =? ORDER BY f_index ASC ");
			pstmt.setInt(1, userIndex);
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
				// 그룹인덱스, 그룹명
			    data = new Message();	
			    data.setGroupIndex(rs.getInt("f_index"));
			    data.setGroup(rs.getString("f_group"));
				list.add(data);
			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getMessageGroupList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}	
	*/
	

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
	protected List<SMSBean> getReservedList(int userIndex, int page, int limit){
		List<SMSBean> list = new ArrayList<SMSBean>();
		SMSBean data = null;		
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
			    data = new SMSBean();	
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


	/**
	 * 전체 문자 전송 내역
	 * @param page
	 * 	페이지
	 * @param limit
	 * 한페이지 목록수
	 * @param search
	 * 	검색어
	 * @param searchWhat
	 * 검색구분
	 * @return
	 */
	public List<SMSBean> getAllSmsList(int page, int limit, String search,
			String searchWhat) {
		List<SMSBean> list = new ArrayList<SMSBean>();
		SMSBean data = null;		
		int startRow = (page -1 ) * 10 +1;		// 시작 번호
		int endRow = startRow + limit -1;		// 끝 번호
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM send_sms_info WHERE" +
					" (f_id like ? OR f_phone like ?) ORDER BY f_index DESC LIMIT ?, ? ");
			pstmt.setString(1, "%" + search + "%");	
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);			
			rs = pstmt.executeQuery();
			Aria aria = Aria.getInstance();	
			while(rs.next())	{
				// 문자 내역을 담는다.
			    data = new SMSBean();	
			    /*
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  			    
			    data.setName(aria.encryptHexStr2DecryptStr(rs.getString("f_name")));
			    data.setPsName(rs.getString("f_psname"));
			    data.setGrade(rs.getString("f_grade"));
			    data.setDeptName(rs.getString("f_deptname"));
			    data.setUserClass(rs.getInt("f_class"));
			    data.setPhone1(aria.encryptHexStr2DecryptStr(rs.getString("f_phone1")));
			    */
				list.add(data);
				
  			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getUserList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}
	
	
	/**
	 * 내 문자함 그룹리스트
	 * @param userIndex
	 * 	유저 인덱스
	 * @return
	 */
	public List<Group> getMyGroupList(int userIndex) {
		List<Group> list = new ArrayList<Group>();
		Group data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM message_group WHERE" +
					" f_user_index = ? ORDER BY f_index ASC ");
			pstmt.setInt(1, userIndex);	
			rs = pstmt.executeQuery();
			while(rs.next()){
				// 문자함 그룹
			    data = new Group();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setGroup(rs.getString("f_group"));
				list.add(data);
  			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getMyGroupList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}	
	
	/**
	 * 내 문자함 추가
	 * @param msg
	 * 	message dto
	 */
	public boolean addMyMessage(Message msg) {
		int result = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO message ( f_id, f_group_index, f_message_group, f_message_title," +
					" f_message_text, f_user_index) VALUES (?, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msg.getId());					// 아이디
			pstmt.setInt(2, msg.getGroupIndex());			// 그룹 인덱스
			pstmt.setString(3, msg.getGroup());				// 그룹명
			pstmt.setString(4, msg.getTitle());				// 제목	
			pstmt.setString(5, msg.getMessage());			// 메세지	
			pstmt.setInt(6, msg.getUserIndex());			// 유저 인덱스	
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("addMyMessage 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}
	
	/**
	 * 	문자함 수정
	 * @param msg
	 * 	메시지 객체
	 * @return
	 */
	public boolean modifyMyMessage(Message msg) {
		int result = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE message  SET   f_message_title = ?, f_message_text = ? " +
					"WHERE f_index = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msg.getTitle());				// 제목	
			pstmt.setString(2, msg.getMessage());			// 메세지	
			pstmt.setInt(3, msg.getIndex());					//  인덱스	
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("modifyMyMessage 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}	
	
	

	/**
	 * 	메세지 삭제
	 * @param index
	 * 	메세지 인덱스
	 * @return
	 */
	public boolean delMessage(int index) {
		int result = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM message WHERE 1 =1 AND f_index = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);						// 메세지 인덱스
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("delMessage 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}		

	/**
	 * 	문자함 그룹 추가
	 * @param userIndex
	 * 		유저 인덱스
	 * @param groupName
	 * 		그룹명
	 * @return
	 */
	public boolean addGroup(int userIndex, String groupName) {
		int result = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO message_group ( f_user_index, f_group) VALUES (?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userIndex);							// 유저 인덱스
			pstmt.setString(2, groupName);					// 그룹 인덱스		

			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("addGroup 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}

	/**
	 *	그룹 삭제
	 * @param userIndex
	 * 		유저 인덱스
	 * @param groupIndex
	 * 		그룹 인덱스
	 * @return
	 */
	public boolean delGroup(int userIndex, int groupIndex) {
		int result = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM message_group WHERE 1 =1 AND" +
					" f_user_index = ? AND f_index = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userIndex);						// 유저 인덱스
			pstmt.setInt(2, groupIndex);					// 그룹 인덱스			

			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("delGroup 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}

	/**
	 * 	그룹 수정
	 * @param userIndex
	 * 		유저 인덱스
	 * @param groupIndex
	 * 		그룹 인덱스
	 * @param groupName
	 * 		그룹명
	 * @return
	 */
	public boolean modifyGroup(int userIndex, int groupIndex, String groupName) {
		int result = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE message_group SET f_group = ? WHERE 1 =1 AND" +
					" f_user_index = ? AND f_index = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupName);					// 그룹명			
			pstmt.setInt(2, userIndex);						// 유저 인덱스
			pstmt.setInt(3, groupIndex);					// 그룹 인덱스			

			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("modifyGroup 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}	
	
	/**
	 * 리소스 반환 반환 순서대로 닫아준다.
	 */
	public void connClose() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("rs 에러" + e.getMessage());				
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("pstmt 에러" + e.getMessage());
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("conn 에러" + e.getMessage());								
			}
		}
	}

	/**
	 * 내 기본 그룹 인덱스 가져오기
	 * @param userIndex
	 * 		유저 인덱스
	 * @return
	 */
	public int getMyBaseGroup(int userIndex) {
		int groupIndex = 0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM message_group WHERE" +
					" f_user_index = ? ORDER BY f_index ASC LIMIT 1 ");
			pstmt.setInt(1, userIndex);	
			rs = pstmt.executeQuery();
			if(rs.next()){
			    groupIndex = rs.getInt("f_index");
  			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getMyBaseGroup 에러 : " + e.getMessage());
		}finally{
			connClose();
		}
		
		return groupIndex;
	}
	
}
