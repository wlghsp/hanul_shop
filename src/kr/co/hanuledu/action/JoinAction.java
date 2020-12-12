package kr.co.hanuledu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.dao.MemberDAO;
import kr.co.hanuledu.dto.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/login.jsp";
		MemberDAO mDao = MemberDAO.getInstance();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String fullAddress= addr1+ addr2;
		
		String phone = request.getParameter("phone");
		String zipNum = request.getParameter("zipNum");
		MemberDTO mDto = new MemberDTO();
		mDto.setId(id);
		mDto.setPwd(pwd);
		mDto.setName(name);
		mDto.setEmail(email);
		mDto.setAddress(fullAddress);
		mDto.setPhone(phone);
		mDto.setZip_num(zipNum);
		mDao.insertMember(mDto);		
		
				
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		return forward;
	}

}
