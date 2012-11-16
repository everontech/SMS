package kr.go.police;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommonCon {

	public DataSource getDataSource() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			return (DataSource) envCtx.lookup("jdbc/smsConn");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return null;
		}
	}
	



}
