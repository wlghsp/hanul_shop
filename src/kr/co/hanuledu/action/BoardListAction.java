package kr.co.hanuledu.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.dao.BoardDAO;
import kr.co.hanuledu.dto.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url ="board/boardList.jsp";
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		
		
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
		
//		Map<String, Integer> pagingMap = new HashMap<>();
//		pagingMap.put("section", section);
//		pagingMap.put("pageNum", pageNum);
		
		Map<String, Object> boardParmMap = new HashMap<>(); //Object로 string이나 int 값도 받을 수 있게 함. 
		boardParmMap.put("section", section);
		boardParmMap.put("pageNum", pageNum);
		boardParmMap.put("searchType", searchType);
		boardParmMap.put("searchKeyword", searchKeyword);
		
		
		System.out.println("section ===> " + section);
		System.out.println("pageNum ===> " + pageNum);
		System.out.println("searchType ===> " + searchType);
		System.out.println("searchKeyword ===> " + searchKeyword); //테스트용 출력은 쓰레기이므로 향후 삭제 요망 
		
//		List<BoardDTO> boardList = bDao.boardList();
		List<BoardDTO> selAllBoardList = bDao.selAllBoardList(boardParmMap);
		int boardListAllCnt = bDao.boardListAllCnt(boardParmMap);
		
	
		Map<String, Object> boardInfo = new HashMap<>();
		boardInfo.put("selAllBoardList", selAllBoardList);
		boardInfo.put("boardListAllCnt", boardListAllCnt);
		boardInfo.put("section", section);
		boardInfo.put("pageNum", pageNum);
		boardInfo.put("searchType", searchType);
		boardInfo.put("searchKeyword", searchKeyword);
	
		
		request.setAttribute("boardInfo", boardInfo);

//		request.setAttribute("selAllBoardList", selAllBoardList);
//		request.setAttribute("boardListAllCnt", boardListAllCnt);
//		request.setAttribute("section", section);
//		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath(url);
		forward.setRedirect(false);
		
		return forward;
	}

}

/*if (boardList.isEmpty()) {
url ="board/board_fail.jsp";
System.out.println("List is empty");
}*/