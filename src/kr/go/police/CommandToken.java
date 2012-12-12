package kr.go.police;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Token 생성 및 비교처리 클래스
 */
public class CommandToken {
	public static String set(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		long systime = System.currentTimeMillis();
		byte[] time = new Long(systime).toString().getBytes();
		byte[] id = session.getId().getBytes();
		String token = "";

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(id);
			md5.update(time);
			token = toHex(md5.digest());
			// req.setAttribute("TOKEN",token);
			session.setAttribute("token", token);
		} catch (Exception e) {
			System.err.println("MD5 Diguests를 요청하였습니다.");
		}
		return token;
	}

	public static boolean isValid(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		String requestToken = req.getParameter("token");
		String sessionToken = (String) session.getAttribute("token");

		if (requestToken == null || sessionToken == null)
			return false;
		else
			return requestToken.equals(sessionToken);
	}

	private static String toHex(byte[] digest) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < digest.length; i++)
			buf.append(Integer.toHexString((int) digest[i] & 0x00ff));
		return buf.toString();
	}
}