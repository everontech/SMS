package kr.go.police.sms;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.SMSUtil;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	문자 발송 처리 action
 */
public class SmsSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ajax처리이기때문에 utf-8로변환한다. 
		request.setCharacterEncoding("utf-8");		
		// 발송관련 dao
		SmsDAO dao = new SmsDAO();
		// 발송문자정보를 담을 bean
		ArrayList<SMSBean> list = new ArrayList<SMSBean>();
		// 내 전화번호
		String fromPhone = (String)request.getParameter("my_phone_num");
		// 발송 메세지
		String message = (String)request.getParameter("message");
	//	message = new String(message.getBytes("utf-8"), "euc-kr");
//		System.out.println(message);		
		// 발송 수신할 전화번호
		String callback = (String)request.getParameter("callback");		
		// 전송 모드
		String flag = (String)request.getParameter("send_type");		
		// 예약 여부
		boolean reserved = request.getParameter("reserved").toString().equals("true");		
		// 예약일
		String reservedDate =  (String)request.getParameter("reserved_datetime");
		if(request.getParameter("reserved_datetime") == null){
			reservedDate = "";
		}
		// 유저 정보
		HttpSession sessoin = request.getSession();
		// 유저 인덱스
		int userIndex = Integer.valueOf(sessoin.getAttribute("index").toString());
		// 유저 아이디
		String id = sessoin.getAttribute("id").toString();
		
		// 발송할 전화번호들 가져오기
		String tmpNums = (String)request.getParameter("call_to_nums");
		System.out.println(tmpNums);
		String[] toTels = tmpNums.split(",");
		// set을 이용하여 중복 전화번호는 제거한다.		
		Set<String> hs = new HashSet<String>();
		for(String tel : toTels ){
			hs.add(tel);
		}
		// 고유 시퀸스값을 얻기 위한 년월일시분초
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
		String yMdHms = sdf.format(new Date());
		Iterator<String> it = hs.iterator();
		SMSBean data;
		int count = 0;
    	ServletContext context = request.getServletContext();
    	String logPath = (String)context.getInitParameter("logFilesPath");		
    	String logMsg;
		while(it.hasNext()){
			// 발송 정보 담기
			data = new SMSBean();
			String seq = yMdHms + String.format("%04d",  ++count);
			data.setIndex(Long.valueOf(seq));
			data.setToPhone(it.next());
			data.setFromPhone(fromPhone);
			data.setMessage(message);
			data.setCallback(callback);
			data.setFlag(flag);			
			data.setId(id);
			data.setReserved(reserved);
			data.setReserveDate(reservedDate);
			data.setUserIndex(userIndex);
			// 로그파일에 기록
			logMsg = "문자 발송\t-SEQ- : " + data.getIndex() +  "\t-USER_ID- : " + data.getId() + 
					 	"\t-To- : " + data.getToPhone() + 	"\t-From- : " + data.getFromPhone() +
					 	"\t-Callback- : " + data.getCallback() + "\t-Mode- : "
					 	+ (data.getFlag().equalsIgnoreCase("s")?"SMS":"MMS");
			// 로그 기록
			SMSUtil.writerToLogFile(logPath, logMsg);
			
			list.add(data);
		}
		
		// 발송 대기 추가 갯수 얻기
		int addCount = dao.addSmsSendList(list);
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println(addCount);
		out.close();
		
		return null;			

	}

}
