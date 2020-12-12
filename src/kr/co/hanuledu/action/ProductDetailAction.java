package kr.co.hanuledu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.dao.ProductDAO;
import kr.co.hanuledu.dto.ProductDTO;

public class ProductDetailAction implements Action {
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "product/product_detail.jsp";
		String p_code = request.getParameter("p_code");
		
		// 비지니스 로직
		ProductDAO pDao = ProductDAO.getInstance();
		ProductDTO detailProduct  = pDao.detailProduct(p_code);
		
		request.setAttribute("detailProduct", detailProduct);
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		return forward;
	}
}

/*
 * if(loginMember.size()!=0) { System.out.println("바보"); }
 */