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

import kr.co.police.CommonCon;
import kr.go.police.board.BoardBean;


/**
 * 계정 (login, regsiter etc)관련 Dao
 */
public class AccountDAO extends CommonCon {
	
	// 로그인 여부 상태값
	public final static int CHECK_OK = 1;
	public final static int ERROR_ID = -1;
	public final static int CHECK_PWD = -2;
	
	DataSource dataSource;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs, rs1;
	
	public AccountDAO(){
		dataSource = getDataSource();
		if(dataSource == null){
			return;
		}
	}
/**
 * DROP TABLE IF EXISTS `sms2`.`user_info`;
CREATE TABLE  `sms2`.`user_info` (
  `f_index` int(10) unsigned NOT NULL auto_increment COMMENT '인덱스',
  `f_id` varchar(45) NOT NULL COMMENT '유저아이디',
  `f_password` varchar(255) NOT NULL COMMENT '유저비밀번호',
  `f_grade` varchar(30) NOT NULL COMMENT '등급',
  `f_name` varchar(20) NOT NULL COMMENT '이름',
  `f_phone1` varchar(20) NOT NULL COMMENT '전화번호1',
  `f_phone2` varchar(20) default NULL COMMENT '전화번호2',
  `f_deptname` varchar(30) NOT NULL COMMENT '부서명',
  `f_email` varchar(100) NOT NULL COMMENT '이메일',
  `f_class` tinyint(3) unsigned NOT NULL default '1' COMMENT '등급',
  `f_approve` char(1) NOT NULL default 'n' COMMENT '승인여부',
  `f_reg_date` datetime NOT NULL COMMENT '등록날짜',
  `f_approve_date` datetime default NULL COMMENT '승인날짜',
  `f_send_limit` int(10) unsigned NOT NULL default '0' COMMENT '월별전송제한수',
  `f_total_send` int(10) unsigned NOT NULL default '0' COMMENT '총전송수',
  `f_month_send` int(10) unsigned default '0' COMMENT '이달의전송수',
  PRIMARY KEY  (`f_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='유저정보테이블';
 * 
 */
	
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
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("loginUser 에러 : " + e.getMessage());
		}finally{
			connClose(rs, pstmt, conn);
		}
		
		return false;
	}
	
	/**
	 * 회원가입 
	 * @return
	 */
	protected boolean joinUser(UserBean data){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO user_info ( f_id, f_password, f_grade, f_name, f_phone1, f_deptname, f_email, " + 
								" f_approve, f_reg_date, f_psname) VALUES (?, password(?), ?, ?, ?, ?, ?, 'n', now(), ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getId());	
			pstmt.setString(2, data.getPwd());	
			pstmt.setString(3, data.getGrade());					
			pstmt.setString(4, data.getName());			
			pstmt.setString(5, data.getPhone1());
			pstmt.setString(6, data.getDeptName());	
			pstmt.setString(7, data.getEmail());	
			pstmt.setString(8, data.getPsName());				
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("joinUser 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose(rs, pstmt, conn);
		}
	}

	/**
	 * 사용자 정보 변경
	 * @param data
	 * @return
	 *	수정처리 여부
	 */
	protected boolean modifyUserInfo(UserBean data){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE user_info SET f_name = ?, f_password = password(?), f_phone1 = ?," +
								" f_email = ?, f_grade = ? WHERE f_index = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getPwd());				
			pstmt.setString(3, data.getPhone1());
			pstmt.setString(4, data.getEmail());	
			pstmt.setString(5, data.getGrade());		
			pstmt.setInt(5, data.getIndex());					
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("modifyUserInfo 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose(rs, pstmt, conn);
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
			connClose(rs, pstmt, conn);
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
				return false;
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("approveUser 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose(rs, pstmt, conn);
		}
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
			String sql = "DELTE FROM user_info WHERE f_index = ? ";
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
			connClose(rs, pstmt, conn);
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
			connClose(rs, pstmt, conn);
		}
		return count;
	}
	
	/**
	 * 	유저 목록 가져오기
	 * @param page
	 * 	페이지
	 * @param limit
	 * 	가져올수
	 * @param search
	 * 	검색어
	 * @param userClass
	 * 	등급
	 * @param psName
	 * 경찰서
	 * @return
	 */
	protected List<UserBean> getUserList(int page, int limit, final String search,
				int userClass, String psName){
		List<UserBean> list = new ArrayList<UserBean>();
		UserBean data = null;		
		int startRow = (page -1 ) * 10 +1;		// 시작 번호
		int endRow = startRow + limit -1;		// 끝 번호
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user_info WHERE (f_id like ? OR f_name =?) LIMIT ?, ? ORDER BY f_notice, f_index DESC");
			pstmt.setString(1, "%" + search + "%");	
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);			
			pstmt.setString(5, search);				
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
				// 인덱스, 아이디, 이름, 경찰서명, 계급, 부서명, 등급
			    data = new UserBean();	
			    data.setIndex(rs.getInt("f_index"));
			    data.setId(rs.getString("f_id"));	  			    
			    data.setName(rs.getString("f_name"));
			    data.setPsName(rs.getString("f_psname"));
			    data.setGrade(rs.getString("f_grade"));
			    data.setDeptName(rs.getString("f_deptname"));
			    data.setUserClass(rs.getInt("f_class"));

				list.add(data);
  			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getUserList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose(rs, pstmt, conn);
		}
	}

}
