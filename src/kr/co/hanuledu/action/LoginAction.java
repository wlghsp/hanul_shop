package kr.co.hanuledu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.hanuledu.dao.MemberDAO;
import kr.co.hanuledu.dto.MemberDTO;

public class LoginAction implements Action {
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/login_fail.jsp";
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		HttpSession session = request.getSession(); //로그인 정보 유지용 
		MemberDAO mDao = MemberDAO.getInstance();
		MemberDTO mDto = mDao.getMember(id);
		
		if (mDto != null) {
			if(mDto.getPwd().equals(pwd)) {
				session.setAttribute("loginUser", mDto);
				url = "index.hanul";
			}
		} 
		
		
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		return forward;
	}
}

/*
 * if(loginMember.size()!=0) { System.out.println("바보"); }
 */