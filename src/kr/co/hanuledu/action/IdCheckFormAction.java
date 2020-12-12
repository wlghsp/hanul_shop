package kr.co.hanuledu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.hanuledu.dao.MemberDAO;
import kr.co.hanuledu.dao.ProductDAO;
import kr.co.hanuledu.dto.ProductDTO;

public class IdCheckFormAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/idcheck.jsp";
		
		String userId = request.getParameter("id").trim();
		MemberDAO mDao =MemberDAO.getInstance();
		int message = mDao.confirmID(userId);
		
		System.out.println(message); //아이디 중복 시 1, 아이디 사용가능 시 -1  

		JSONObject jobj = new JSONObject();
		jobj.put("message", message);
		jobj.put("id", userId);
		
		// JSON 객체에 담은 데이터를 다시 호출한 페이지로 전송 
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jobj);
		
		return null;
		
//		request.setAttribute("message", message);
//		request.setAttribute("userId", userId);
//		ProductDAO pDao = ProductDAO.getInstance();
//		List<ProductDTO> newProductList = pDao.listNewProduct();
		
		
		
		// 처리 방식 1
		//[{"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠}, {"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠},{"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠},{"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠}]
//		JSONObject jsonObject = new JSONObject();
//		JSONArray jsonArray = new JSONArray();
//		
//		for (ProductDTO str : newProductList) {
//			JSONObject data = new JSONObject();
//			data.put("p_name", str.getP_name());
//			data.put("p_price2", str.getP_price2());
//			data.put("p_img", str.getP_img());
//			
//			jsonArray.add(data);
//		}
//		jsonObject.put("products", jsonArray);
//		System.out.println(jsonObject);
		// 처리 방식 2
		//{"products":[{"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠}, {"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠},{"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠},{"p_price2":50000, "p_img":"w2.jpg","p_name":"크로커다일부츠}]}
		
//		
//		ActionForward forward = new ActionForward();
//		forward.setPath(url);
//		forward.setRedirect(false);
//		
//		return forward;
		
	}

}
