package kr.co.hanuledu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.dao.AddressDAO;
import kr.co.hanuledu.dto.AddressDTO;

public class FindZipNumAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url ="member/findZipNum.jsp";
		
		String dong = request.getParameter("dong");
		
		if (dong != null && dong.trim().equals("") == false) {
			AddressDAO aDao = AddressDAO.getInstance();
			ArrayList<AddressDTO> addressList = aDao.selectAddressByDong(dong);
			
			request.setAttribute("addressList", addressList);
		}
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		
		return forward;
	}

}
