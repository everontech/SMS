package kr.go.police;

public class SMSUtil {

	/**
	 * 핵사를 바이트로 변환
	 * @param hex
	 * @return
	 */
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}
		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer
					.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	/**
	 * 바이트를 핵사로 변환
	 * @param ba
	 * @return
	 */
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}


	/**
	 * 바이트에서 스트링으로 변환
	 * @param byteInput
	 * @return
	 */
	public static String getStringFromByte(final byte[] byteInput) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteInput.length; i++) {
			// Processing Korean
			if ((byteInput[i] & 0x80) == 0x80) {
				byte[] byteKorean = new byte[2];
				byteKorean[0] = byteInput[i];
				byteKorean[1] = byteInput[i++];
				sb.append(new String(byteKorean));
			} else if ((byteInput[i] & 0xFF) == 0) {

			} else {
				sb.append((char) byteInput[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 게시판 페이지네이션 만들기
	 * @param count
	 * 		게시물 총갯수
	 * @param currentPage
	 * 		현재 페이지
	 * @param pageSize
	 * 		페이지 크기
	 * @param url
	 * 		링크 주소
	 * @param params
	 * 		url 파라미터 
	 * @return
	 * 		페이지네이션 html
	 */
	public static String makePagiNation(int count, int currentPage, int pageSize, String url, String params){
		StringBuffer sb = new StringBuffer();
		String currentClass = "";		// 현재 페이지 강조 클래슨
		// 페이지 갯수
		int pageCount = count / pageSize + (count % pageSize ==0?0:1);
		// 시작페이지
		int startPage = (int)(currentPage /10) * 10 + 1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		// 파라미터가 있으면
		if(params != null && params.length() > 0){
			params = "&" + params; 		
		}else{
			params = "";
		}
		sb.append("<div class=\"page\">");
		// 현재페이지에서 이전 10개 페이지가 있으면 10개페이지 앞으로		
		if(startPage > 10){
			sb.append("<a href=\"" +url +  "?page=" + (startPage - 10) +  params + "\"><img src=\"images/notice/page_prev_btn.gif\" /></a>\r\n");
		}
		
		// 이전페이지
		if(currentPage > 1){
			sb.append("<a href=\"" +url +  "?page=" + (currentPage - 1) +  params + "\"><img src=\"images/notice/page_prev_btn.gif\" /></a>\r\n");
		}
		
		// 페이지 목록처리
		for(int i= startPage; i <= endPage; i++){
			currentClass = (currentPage == i)?"class=\"current\" ":"";//	현재 페이지이면 강조
			sb.append("<a  href=\"" +url +  "?page=" +  i + params + "\"><span  " + currentClass + ">" + i + "</spa></a>");
		}
		
		// 다음 페이지가 있으면 다음 페이지를 보여준다.
		if(currentPage < endPage){
			sb.append("<a href=\"" +url +  "?page=" + (currentPage + 1) +  params + "\"><img src=\"images/notice/page_next_btn.gif\" /></a>\r\n");
		}		
		
		// 현재페이지에서 10개페이지가 넘으면 10개뒤로
		if(endPage < pageCount){
			sb.append("<a href=\"" +url +  "?page=" +  (startPage + 10) + "\"><img src=\"images/notice/page_next_btn.gif\" /></a>" );
		}
		sb.append("</div>");
		
		return sb.toString();
	}
}
