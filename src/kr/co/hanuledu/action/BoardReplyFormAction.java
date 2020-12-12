package kr.co.hanuledu.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board/boardReplyForm.jsp";
		
		// BoardReplyFormAction은 어느 클래스에서 출력문인지 확인코자 했음.
		System.out.println("BoardReplyFormAction ref ===>" + request.getParameter("ref"));	
		System.out.println("BoardReplyFormAction re_step ===>" +request.getParameter("re_step"));		
		System.out.println("BoardReplyFormAction re_level ===>" +request.getParameter("re_level"));		
		
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		//Integer null 값을 대체 함.
		Map<String, Integer> replyInfo = new HashMap<>(); //자바 1.7부터 HashMap 표기 간단하게 가능해짐. 
		
		replyInfo.put("ref", ref);
		replyInfo.put("re_step", re_step);
		replyInfo.put("re_level", re_level);
		
		request.setAttribute("replyInfo", replyInfo);
		
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		
		return forward;
	}

}
