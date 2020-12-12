package kr.co.hanuledu.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.dao.BoardDAO;
import kr.co.hanuledu.util.FileUpload;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String> boardMap = FileUpload.upload(request, response);
		String originalFileName = boardMap.get("originalFileName");
		String savePath = boardMap.get("savePath");
		//화면에서 글번호 취득 
		int articleNo = Integer.parseInt(boardMap.get("articleNo"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		//취득한 글 번호를 통해 해당 게시물 삭제
		
		System.out.println("BoardDeleteAction originalFileName===>>>" + originalFileName);
		System.out.println("BoardDeleteAction savePath===>>>" + savePath);
		System.out.println("BoardDeleteAction articleNo===>>>" + articleNo);
		
		int result = bDao.delete(articleNo);
		if (originalFileName != null && originalFileName.length() != 0) {
			File oldFile = new File(savePath + "\\" + articleNo + "\\" + originalFileName);
			File destDir = new File(savePath + "\\" + (articleNo));
			oldFile.delete();
			destDir.delete();
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		if (result > 0) {
			pw.print("<script>"+"alert('投稿を削除しました。');" + "location.href='" + "boardList.hanul';"+ "</script>");
		} else {
			pw.print("<script>"+"alert('投稿の削除に失敗しました。');" + "history.go(-1);"+ "</script>");
		}
	
		return null;
		
	}

}
