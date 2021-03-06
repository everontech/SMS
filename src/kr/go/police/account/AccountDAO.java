package kr.go.police.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.go.police.CommonCon;
import kr.go.police.aria.Aria;

/**
 * 계정 (login, regsiter etc)관련 Dao
 */
public class AccountDAO extends CommonCon {
	DataSource dataSource;
	public ResultSet rs;
	public PreparedStatement pstmt;
	public Connection conn;
	
	// 로그인 여부 상태값
	public final static int CHECK_OK = 1;
	public final static int ERROR_ID = -1;
	public final static int CHECK_PWD = -2;
	
	public AccountDAO(){
		dataSource = getDataSource();
		if(dataSource == null){
			return;
		}
	}
	
	/**	 
	 * 로그인 처리
	 * @return
	 */
	protected boolean loginUser(String id, String pwd) {
		try{
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM user_info WHERE f_id = ? AND f_password =password(?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//	정상 로그인이라면 접속시간 업데이트처리
				sql = "UPDATE user_info SET f_visit_date = now()" +
						" WHERE f_id = ? AND f_password =password(?) ";
				pstmt = conn.prepareStatement(sql);				
				pstmt.setString(1, id);		
				pstmt.setString(2, pwd);				
				pstmt.executeUpdate();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("loginUser 에러 : " + e.getMessage());
		}finally{
			connClose();
		}
		return false;
	}
	
	/**
	 * 회원가입 처리
	 * 가입시 사용승인은 미승인으로 처리한후
	 * 추후 관리자가 승인변경처리후 사용할수 있도록 한다.
	 * 회원을 가입하면 기본문자함 그룹 및 주소록 기본 그룹도 생성한다.
	 * @return
	 */
	protected boolean joinUser(UserBean data){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO user_info ( f_id, f_password, f_grade, f_name, f_phone1, f_deptname, f_email, " + 
								" f_approve, f_reg_date, f_psname, f_visit_date) VALUES (?, password(?), ?, ?, ?, ?, ?, 'n', now(), ?, now() )";
			
			Aria aria = Aria.getInstance();	
			// 이름 ,전화번호, 이메일
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getId());	
			pstmt.setString(2, data.getPwd());	
			pstmt.setString(3, data.getGrade());					
			pstmt.setString(4, aria.encryptByte2HexStr(data.getName()));			
			pstmt.setString(5, aria.encryptByte2HexStr(data.getPhone1()));
			pstmt.setString(6, data.getDeptName());	
			pstmt.setString(7,  aria.encryptByte2HexStr(data.getEmail()));	
			pstmt.setString(8, data.getPsName());				
			// update
			result = pstmt.executeUpdate();
			
			// 회원가입 실패면
			if(result <= 0){
				return false;
			}
			
			//  문자함 기본그룹 생성 하기
			sql = "INSERT INTO message_group (f_user_index, f_group) VALUES( " +
					"(SELECT f_index FROM user_info WHERE f_id = ?), '기본그룹' )";		
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, data.getId());	
			pstmt.executeUpdate();
			//  주소록 기본그룹 생성 하기
			sql = "INSERT INTO address_book_group (f_user_index, f_group) VALUES( " +
					"(SELECT f_index FROM user_info WHERE f_id = ?), '기본그룹' )";		
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, data.getId());	
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("joinUser 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}

	/**
	 * 사용자 정보 변경
	 * @param data
	 * @return
	 *	수정처리 여부
	 */
	protected boolean modifyMyInfo(UserBean data){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE user_info SET f_name = ?, f_password = password(?), f_phone1 = ?," +
								" f_email = ?, f_grade = ?,  f_psname = ?, f_approve = 'n',  f_deptname = ?, f_class = ? WHERE f_index = ?";
			pstmt = conn.prepareStatement(sql);
			Aria aria = Aria.getInstance();			// 암호화 처리
			pstmt.setString(1, aria.encryptByte2HexStr(data.getName()));
			pstmt.setString(2, data.getPwd());				
			pstmt.setString(3, aria.encryptByte2HexStr(data.getPhone1()));
			pstmt.setString(4, aria.encryptByte2HexStr(data.getEmail()));	
			pstmt.setString(5, data.getGrade());		
			pstmt.setString(6, data.getPsName());		
			pstmt.setString(7, data.getDeptName());					
			pstmt.setInt(8, data.getUserClass());
			pstmt.setInt(9, data.getIndex());	
			
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("modifyUserInfo 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}	
	
	/**
	 * 관리자모드에서 사용자 정보 변경
	 * @param data
	 * @return
	 *	수정처리 여부
	 */
	protected boolean modifyUserInfoFromAdmin(UserBean data){
		Aria aria = Aria.getInstance();	
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE user_info SET" +
								" f_name = ?," +
								" f_deptname = ?," +
								" f_grade = ?," +								
								" f_phone1 = ?," +
								" f_email = ?, " +
								" f_class = ?, " +
								" f_approve = ? " +								
								" WHERE f_index = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aria.encryptByte2HexStr(data.getName()));
			pstmt.setString(2, data.getDeptName());		
			pstmt.setString(3, data.getGrade());			
			pstmt.setString(4, aria.encryptByte2HexStr(data.getPhone1()));			
			pstmt.setString(5, aria.encryptByte2HexStr(data.getEmail()));	
			pstmt.setInt(6, data.getUserClass());
			pstmt.setString(7, data.isApprove()?"y":"n");
			pstmt.setInt(8, data.getIndex());
			
			// update
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("modifyUserInfoFromAdmin 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}		
	
	/**
	 * 사용자 승인 처리
	 * @return
	 */

	protected boolean changeApproveUser(UserBean data, boolean approve){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			// 사용자가 계급이나 기타 정보를 부정확하게 입력할수 있으므로 관리자가 승인 처리를 하면서
			// 사용자 정보를 변경하여 저장할수 있도록 한다.
			String sql = "UPDATE user_info SET f_grade = ?, f_phone1 = ?, f_deptname = ?, f_psname = ?, " +
								"f_arrpove = ?, f_send_limit = ?, f_class = ? WHERE f_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, data.getGrade());					
			pstmt.setString(2, data.getPhone1());
			pstmt.setString(3, data.getDeptName());	
			pstmt.setString(4, data.getPsName());	
			pstmt.setString(5, data.getPsName());				
			pstmt.setString(6, approve?"y":"n");
			pstmt.setInt(7, data.getUserClass());						
			pstmt.setString(8, data.getId());				
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("approveUser 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}	
	
	/**
	 * 유저 아이디 중복 체크
	 * @return
	 */
	protected boolean checkDupleUserId(String userId){
		try{
			conn = dataSource.getConnection();
			String sql = "SELECT count(*) FROM user_info WHERE f_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(1) > 0){
					return false;
				}else{
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("checkDupleUserId 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
		return false;
	}
	

	/**
	 *  사용자 삭제
	 * @param index
	 * 	사용자 인덱스
	 * @return
	 * 	삭제 여부
	 */
	protected boolean deleteUser(int index){
		int result = 0;
		try{
			conn = dataSource.getConnection();
			String sql = "DELETE FROM user_info WHERE f_index = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("deleteUser 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}	
	
	/**
	 *	유저수구하기
	 * @return
	 * 	유저수
	 */
	protected int getUserListCount(){
		int count = 0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT count(*) FROM user_info ");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getUserListCount 에러 : " +  e.getMessage());
		}finally{
			connClose();
		}
		return count;
	}
	
	
	/**
	 * 	신규 가입 유저 목록수
	 */
	public int getRecentJoinUserSize(){
		int size = 0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT count(*) as cnt FROM user_info " +
					" WHERE f_reg_date > SUBDATE(now(), 7) ");
			rs = pstmt.executeQuery();
			if(rs.next())	{
				size =  rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getRecentJoinUserSize 에러 : " + e.getMessage());
		}finally{
			connClose();
		}
		return size;
	}	
	
	/**
	 * 	인덱스로 유저세부 정보 가져오기
	 * @param index
	 * @return
	 */
	protected UserBean getUserDetail(int index){
		UserBean data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user_info WHERE f_index = ? ");
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			
			if(rs.next())	{
				Aria aria = Aria.getInstance();					
				// 사용자정보의 모든 세부 정보를 가져온다.
			    data = new UserBean();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  	
			    //data.setDeptCode(deptCode);
			    data.setEmail(aria.encryptHexStr2DecryptStr(rs.getString("f_email")));
			    data.setMonthSend(rs.getInt("f_month_send"));
			    data.setPhone1(aria.encryptHexStr2DecryptStr(rs.getString("f_phone1")));			    
			    data.setRegDate(rs.getString("f_reg_date"));
			    data.setApprove(rs.getString("f_approve").equalsIgnoreCase("y"));
			    data.setTotalSendCount(rs.getInt("f_total_send"));
			    data.setMonthSendLimit(rs.getInt("f_send_limit"));
			    data.setName(aria.encryptHexStr2DecryptStr(rs.getString("f_name")));
			    data.setPsName(rs.getString("f_psname"));
			    data.setGrade(rs.getString("f_grade"));
			    data.setDeptName(rs.getString("f_deptname"));
			    data.setUserClass(rs.getInt("f_class"));
			    System.out.println(data.getName());
			}		
			
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getUserDetail 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
}	

	/**
	 *	사용자 계정 승인 여부 확인
	 * @param id
	 * @return
	 */
	public boolean checkApprove(String id) {
		try{
			conn = dataSource.getConnection();
			String sql = "SELECT f_approve FROM user_info WHERE f_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equalsIgnoreCase("y"))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("checkDupleUserId 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
		return false;
	}

	/**
	 * 	아이디로 사용자 정보 가져오기
	 * @param id
	 * @return
	 */
	public UserBean getUserInfo(String id) {
		UserBean data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user_info WHERE f_id = ? ");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())	{
				Aria aria = Aria.getInstance();	
				// 사용자정보의 모든 세부 정보를 가져온다.
			    data = new UserBean();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  	
			    //data.setDeptCode(deptCode);
			    data.setEmail(aria.encryptHexStr2DecryptStr(rs.getString("f_email")));
			    data.setMonthSend(rs.getInt("f_month_send"));
			    data.setPhone1(aria.encryptHexStr2DecryptStr(rs.getString("f_phone1")));
			    data.setRegDate(rs.getString("f_reg_date"));
			    data.setApprove(rs.getString("f_approve").equalsIgnoreCase("y"));
			    data.setTotalSendCount(rs.getInt("f_total_send"));
			    data.setMonthSendLimit(rs.getInt("f_send_limit"));
			    data.setName(aria.encryptHexStr2DecryptStr(rs.getString("f_name")));
			    data.setPsName(rs.getString("f_psname"));
			    data.setGrade(rs.getString("f_grade"));
			    data.setDeptName(rs.getString("f_deptname"));
			    data.setUserClass(rs.getInt("f_class"));
			}		
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getUserInfo 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}
	
	/**
	 *	미승인 유저수구하기
	 * @return
	 * 	유저수
	 */
	protected int getArvListCount(){
		int count = 0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT count(*) FROM user_info where f_approve='n'");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getArvListCount 에러 : " +  e.getMessage());
		}finally{
			connClose();
		}
		return count;
	}
	
	/**
	 *	휴면유저수구하기
	 * @return
	 * 	유저수
	 */
	protected int getQuserListCount(){
		int count = 0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT count(*) FROM user_info WHERE" +
					" f_visit_date < NOW()- interval 3 month");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getQuserListCount 에러 : " +  e.getMessage());
		}finally{
			connClose();
		}
		return count;
	}
	

	/**
	 * 유저 목록 가져오기
	 * @param search
	 * @param start
	 * 		시작 번호
	 * @param end
	 * 		마지막 번호
	 * @return
	 */
	protected List<UserBean> getUserList(final String search, int start, int end){
		List<UserBean> list = new ArrayList<UserBean>();
		UserBean data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user_info WHERE (f_id like ? OR f_name like ?) ORDER BY f_index DESC LIMIT ?, ? ");
			pstmt.setString(1, "%" + search + "%");	
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, start -1);	
			pstmt.setInt(4, end);			
			rs = pstmt.executeQuery();
			Aria aria = Aria.getInstance();	
			while(rs.next())	{
				// 인덱스, 아이디, 이름, 경찰서명, 계급, 부서명, 등급
			    data = new UserBean();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  			    
			    data.setName(aria.encryptHexStr2DecryptStr(rs.getString("f_name")));
			    data.setPsName(rs.getString("f_psname"));
			    data.setGrade(rs.getString("f_grade"));
			    data.setDeptName(rs.getString("f_deptname"));
			    data.setUserClass(rs.getInt("f_class"));
			    data.setApprove(rs.getString("f_approve").equalsIgnoreCase("y"));			    
			    data.setPhone1(aria.encryptHexStr2DecryptStr(rs.getString("f_phone1")));
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
	 * 미승인 유저 목록 가져오기
	 * @param search
	 * @param start
	 * 		시작 번호
	 * @param end
	 * 		마지막 번호
	 * @return
	 */	
	protected List<UserBean> getArvList(final String search, int start, int end){
		List<UserBean> list = new ArrayList<UserBean>();
		UserBean data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user_info WHERE (f_approve='n') AND" +
					" (f_id like ? OR f_name like ?) ORDER BY f_index DESC LIMIT ?, ?");
			pstmt.setString(1, "%" + search + "%");	
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, start -1);	
			pstmt.setInt(4, end);			
			rs = pstmt.executeQuery();
			Aria aria = Aria.getInstance();	
			while(rs.next())	{
				// 인덱스, 아이디, 이름, 경찰서명, 계급, 부서명, 등급
			    data = new UserBean();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  			    
			    data.setName(aria.encryptHexStr2DecryptStr(rs.getString("f_name")));
			    data.setPsName(rs.getString("f_psname"));
			    data.setGrade(rs.getString("f_grade"));
			    data.setDeptName(rs.getString("f_deptname"));
			    data.setUserClass(rs.getInt("f_class"));
			    data.setRegDate(rs.getString("f_reg_date"));			    
			    data.setApprove(rs.getString("f_approve").equalsIgnoreCase("y"));			    
			    data.setPhone1(aria.encryptHexStr2DecryptStr(rs.getString("f_phone1")));
				list.add(data);
  			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getArvList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}
	}
	/**
	 * 휴면계정 목록 가져오기
	 * @param search
	 * @param start
	 * 		시작 번호
	 * @param end
	 * 		마지막 번호
	 * @return
	 */	
	protected List<UserBean> getQuserList(final String search, int start, int end){
		List<UserBean> list = new ArrayList<UserBean>();
		UserBean data = null;	
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user_info WHERE " +
					" f_visit_date < NOW() - interval 3 month AND (f_id like ? OR f_name like ?) " +
					" ORDER BY f_index DESC LIMIT ?, ?");
			pstmt.setString(1, "%" + search + "%");	
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, start -1);	
			pstmt.setInt(4, end);			
			rs = pstmt.executeQuery();
			Aria aria = Aria.getInstance();	
			while(rs.next())	{
				// 인덱스, 아이디, 이름, 경찰서명, 계급, 부서명, 등급
			    data = new UserBean();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  			    
			    data.setName(aria.encryptHexStr2DecryptStr(rs.getString("f_name")));
			    data.setPsName(rs.getString("f_psname"));
			    data.setGrade(rs.getString("f_grade"));
			    data.setDeptName(rs.getString("f_deptname"));
			    data.setUserClass(rs.getInt("f_class"));
			    data.setVisitDate(rs.getString("f_visit_date"));			    
			    data.setPhone1(aria.encryptHexStr2DecryptStr(rs.getString("f_phone1")));
				list.add(data);
  			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getQuserList 에러 : " + e.getMessage());
			return null;
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
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}		

}
