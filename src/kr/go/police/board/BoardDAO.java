package kr.go.police.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * 공지사항, 게시판 관련
 */
public class BoardDAO {
	
	DataSource dataSource;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs, rs1;
	
	public BoardDAO(){
		try{
			Context initCtx = new InitialContext();
			Context envCtx=(Context)initCtx.lookup("java:comp/env");
			dataSource = (DataSource)envCtx.lookup("jdbc/smsConn");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	/**
	 * 게시판 글수
	 * @return
	 */
	public int getNoticeListCount(){
		int count = 0;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT count(*) FROM board ");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getNoticeListCount 에러 : " +  e.getMessage());
		}finally{
			connClose();
			
		}
		return count;
	}

	/**
	 * 게시물 자세히 보기
	 * @return
	 */
	public BoardBean getDetail(int index){
		BoardBean data = null;		
		try {
			conn = dataSource.getConnection();
			// 최근 순서대로
			pstmt = conn.prepareStatement("SELECT * FROM board WHERE f_index = " + index);
			rs = pstmt.executeQuery();
			
			if(rs.next())	{
			    data = new BoardBean();	
			    data.setIndex(rs.getInt("f_index"));
				data.setTitle(rs.getString("f_title"));
				data.setContent(rs.getString("f_content"));
			    data.setNotice(rs.getBoolean("f_notice"));
			    data.setViewCount(rs.getInt("f_view_count"));
			    data.setParentIndex(rs.getInt("f_parent_index"));
				data.setFilename(rs.getString("f_filename"));
				data. setRegisterName(rs.getString("f_reg_user"));
				data.setRegDate(rs.getString("f_reg_date"));
				data.setModiDate(rs.getString("f_modi_date"));
				data.setRegUserIndex(rs.getInt("f_reg_user_index"));
				
				System.out.println(data.getRegisterName());
  			}		

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getNoticeList 에러 : " + e.getMessage());
		}finally{
			connClose();
		}
		return data;
	}
	
	/**
	 * 게시물 목록 보기
	 * @return
	 */
	public List<BoardBean> getBoardList(int page, int limit){
		List<BoardBean> list = new ArrayList<BoardBean>();
		BoardBean data = null;		
		int startRow = (page -1 ) * 10 +1;		// 시작 번호
		int endRow = startRow + limit -1;		// 끝 번호
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM board LIMIT ?, ? ORDER BY f_notice, f_index DESC");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);			
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
			    data = new BoardBean();	
			    data.setIndex(rs.getInt("f_index"));
				data.setTitle(rs.getString("f_title"));
			    data.setNotice(rs.getBoolean("f_notice"));
			    data.setViewCount(rs.getInt("f_view_count"));
			    data.setParentIndex(rs.getInt("f_parent_index"));
				data.setFilename(rs.getString("f_filename"));
				data. setRegisterName(rs.getString("f_reg_user"));
				data.setRegDate(rs.getString("f_reg_date"));
				data.setModiDate(rs.getString("f_modi_date"));
				data.setRegUserIndex(rs.getInt("f_reg_user_index"));
				
				System.out.println(data.getRegisterName());
				list.add(data);
  			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getBoardList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}

	}	
	
	/**
	 * 공지사항 목록 보기
	 * @return
	 */
	public List<BoardBean> getNoticeList(){
		List<BoardBean> list = new ArrayList<BoardBean>();
		BoardBean data = null;		

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM board WHERE f_notice = 'y' ORDER BY f_index DESC");
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
			    data = new BoardBean();	
			    data.setIndex(rs.getInt("f_index"));
				data.setTitle(rs.getString("f_title"));
			    data.setViewCount(rs.getInt("f_view_count"));
			    data.setParentIndex(rs.getInt("f_parent_index"));
				data.setFilename(rs.getString("f_filename"));
				data. setRegisterName(rs.getString("f_reg_user"));
				data.setRegDate(rs.getString("f_reg_date"));
				data.setModiDate(rs.getString("f_modi_date"));
				data.setRegUserIndex(rs.getInt("f_reg_user_index"));
				
				System.out.println(data.getRegisterName());
				list.add(data);
  			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getNoticeList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}

	}		
	

	/**
	 *	게시물 댓글 목록 보기
	 * @param parentIndex
	 * 	부모 게시물 인덱스
	 * @return
	 */
	public List<BoardBean> getReplyList(int parentIndex){
		List<BoardBean> list = new ArrayList<BoardBean>();
		BoardBean data = null;		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM board WHERE f_parent_index = ? ORDER BY f_index DESC");
			pstmt.setInt(1, parentIndex);
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
			    data = new BoardBean();	
			    data.setIndex(rs.getInt("f_index"));
				data.setContent(rs.getString("f_content"));
			    data.setParentIndex(rs.getInt("f_parent_index"));
				data. setRegisterName(rs.getString("f_reg_user"));
				data.setRegDate(rs.getString("f_reg_date"));
				data.setRegUserIndex(rs.getInt("f_reg_user_index"));
				
				System.out.println(data.getRegisterName());
				list.add(data);
  			}		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getNoticeList 에러 : " + e.getMessage());
			return null;
		}finally{
			connClose();
		}

	}		
	
	/**
	 * 게시판 글 등록
	 * @return
	 * 	등록 여부
	 */
	public boolean insertBoard(BoardBean data){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO board ( f_title, f_content, f_notice, f_reg_user, f_filename, f_parent_index, " +
					"f_reg_date, f_reg_user_index, f_password) VALUES (?, ?, ?, ?, ?, ?, now(), ?, password(?) )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, data.getTitle());			
			pstmt.setString(3, data.getContent());	
			pstmt.setBoolean(4, data.isNotice());	
			pstmt.setString(5, data.getRegisterName());	
			pstmt.setInt(6, 0);	
			pstmt.setString(7, data.getRegDate());	
			pstmt.setInt(8, data.getRegUserIndex());	
			pstmt.setString(9, data.getPwd());	
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insertBoard 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}
	
	/**
	 * 게시판 글 삭제
	 * @return
	 * 	삭제 여부
	 */
	public boolean deleteBoard(int index, String password){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			// 글 삭제
			String sql = "DELETE  FROM board WHERE  f_index =  ? AND f_pwd = password(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			result = pstmt.executeUpdate();			
			
			// 삭제가 되었으면  부모글일경우 댓글이 있으면 댓글도 삭제
			if(result > 0){
				sql = "DELETE  FROM board WHERE  f_parent_index = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, index);
				pstmt.setString(2, password);			
				pstmt.executeUpdate();
			}

			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insertBoard 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}	

	/**
	 * 	조회수 업데이트
	 * @param index
	 * 		게시물 인덱스
	 */
	public void updateReadCount(int index){
		String sql = "UPDATE board set f_view_count = f_view_count + 1 WHERE f_index =  + index";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateReadCount 에러 : " + e.getMessage());			
		}finally{
			connClose();
		}
	}
	
	/**
	 * 글쓴이 체크
	 * @return
	 */
	public boolean checkWriteUser(int index, String regUser){
		String sql = "SELECT count(*) FROM board WHERE f_index = ? AND f_reg_user = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.setString(2, regUser);
			rs = pstmt.executeQuery();			
			rs.next();
			if(rs.getInt(1) == 1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateReadCount 에러 : " + e.getMessage());		
		}finally{
			connClose();
		}
		
		return false;
	}

	/**
	 *  댓글 달기
	 * @param data
	 * @param parentIndex
	 * 	부모 게시물 인덱스
	 * @return
	 * 	등록 여부
	 */
	public boolean replyBoard(BoardBean data, int parentIndex){
		int result = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO board ( f_title, f_content, f_notice, f_reg_user, f_filename, f_parent_index, " +
					"f_reg_date, f_reg_user_index, f_password) VALUES (?, ?, ?, ?, ?, ?, now(), ?, password(?) )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, "");								// 댓글에 제목은 사용안함
			pstmt.setString(3, data.getContent());	
			pstmt.setBoolean(4, false);	
			pstmt.setString(5, data.getRegisterName());	
			pstmt.setString(6, "");								// 댓글이므로 파일은 사용안함	
			pstmt.setInt(7, data.getParentIndex());	
			pstmt.setString(8, data.getRegDate());	
			pstmt.setInt(9, data.getRegUserIndex());	
			pstmt.setString(10, data.getPwd());	
			// update
			result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insertBoard 에러 : " + e.getMessage());
			return false;
		}finally{
			connClose();
		}
	}

	/**
	 * 리소스 반환
	 * 반환 순서대로 닫아준다.
	 */
	private void connClose() {
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){}
		}
		
		if(pstmt != null){
			try{
				pstmt.close();
			}catch(SQLException e){}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		
	} 
	
}
