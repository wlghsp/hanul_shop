package kr.co.hanuledu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.dao.ProductDAO;
import kr.co.hanuledu.dto.ProductDTO;

public class IndexAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "index.jsp"; 
		
		//비지니스 로직 
		ProductDAO pDao = ProductDAO.getInstance();
	
		List<ProductDTO> listNewProduct =pDao.listNewProduct();
		List<ProductDTO> listBestProduct =pDao.listBestProduct();
	
		request.setAttribute("listNewProduct", listNewProduct); //해당 객체를 set해서 담아준다. 
		request.setAttribute("listBestProduct", listBestProduct);
		
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		
		return forward;
	}

}


/*for (int i = 0; i < listNewProduct.size(); i++) {
	System.out.println(listNewProduct.get(i).getP_code() +","+ listNewProduct.get(i).getP_name() +","+ listNewProduct.get(i).getP_price2() +","+ listNewProduct.get(i).getP_img());
}
for (ProductDTO productDTO : listBestProduct) {
	System.out.println(productDTO.getP_code()+","+productDTO.getP_name()+","+productDTO.getP_price2()+","+productDTO.getP_img());
}*/