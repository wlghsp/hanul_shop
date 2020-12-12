package kr.co.hanuledu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.dao.BoardDAO;
import kr.co.hanuledu.dto.BoardDTO;

public class BoardViewAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board/boardView.jsp";
		//화면에서 글번호 취득 
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		String articleNoStr = Integer.toString(articleNo);
		BoardDAO bDao = BoardDAO.getInstance();
		//취득한 글 번호를 통해 해당 게시물 정보 취득 
		
		  //쿠키변수를 만들어서 값을 저장. 쿠키변수에 값이 있으면 조회수 증가 실행 하지 않음
		  boolean isGet=false;
		  Cookie[] cookies=request.getCookies();
		  if(cookies!=null){   
		   for(Cookie c: cookies){//    
			    //num쿠키가 있는 경우
			    if(c.getName().equals(articleNoStr)){
			     isGet=true; 
			    }
			   }
			   // num쿠키가 없는 경우
			   if(!isGet) {
			    bDao.updateReadCnt(articleNo);//조회수증가		    
			    Cookie c1 = new Cookie(articleNoStr, articleNoStr);
			    c1.setMaxAge(1*24*60*60);//하루저장
			    response.addCookie(c1);    
			   }
		  }
		
		BoardDTO selBoardView  = bDao.selBoardView(articleNo);
		
		
		System.out.println("제목: "+selBoardView.getSubject());
		//취득한 게시물 정보를 세팅 
		request.setAttribute("selBoardView", selBoardView);
		
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		return forward;
	}

}
