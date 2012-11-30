package kr.go.police.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	파일 다운로드 
 *	다운로드박스가 출력되게 처리
 */
public class FileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("euc-kr");				
		String fileName = request.getParameter("file");		// 파일명		
		// 한글 처리
		String enFileName = new String(fileName.getBytes("iso-8859-1"), "EUC-KR"); 
		System.out.println(enFileName);	
		// 파일경로
		String downloadPath = request.getServletContext().getRealPath("uploads");
		// 파일 path 
		String filePath = downloadPath + File.separator + enFileName;
		
		File file = new File(filePath);
		// 파일이 있는지 확인
		if(file.isFile() == false){
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해당 파일이 없습니다.');");			
			out.println("history.back(-1);");
			out.println("</script>");	
			out.close();		
			return null;
		}
		
		byte b[] = new byte[4096];		
		FileInputStream in = new FileInputStream(file);
		// 마임타입
		String mimeType = request.getServletContext().getMimeType(filePath);
		System.out.println(mimeType);
		// 지정되지 않은 파일 형식처리
		if(mimeType == null){
			mimeType = "application/octet-stream";
		}
		// 타입 설정
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "attachment; filename = " + fileName);
		ServletOutputStream out = response.getOutputStream();
		int len;
		// 바이트 배열 b의 0번부터 numRead까지 출력
		while((len = in.read(b, 0, b.length)) != -1){
			out.write(b, 0, len);
		}
		
		out.flush();
		out.close();
		in.close();

		return null;
	}

}

