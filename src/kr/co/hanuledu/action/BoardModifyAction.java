package kr.co.hanuledu.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import kr.co.hanuledu.dao.BoardDAO;
import kr.co.hanuledu.dto.BoardDTO;
import kr.co.hanuledu.util.FileUpload;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDTO bDto = new BoardDTO();
		BoardDAO bDao = new BoardDAO();
		
		Map<String, String> boardMap = FileUpload.upload(request, response);
		
		int articleNo = Integer.parseInt(boardMap.get("articleNo"));
		String subject = boardMap.get("subject");
		String content = boardMap.get("content");
		String originalFileName = boardMap.get("originalFileName");
		String fileName = boardMap.get("fileName");
		String savePath = boardMap.get("savePath");
		
		bDto.setArticleNo(articleNo);
		bDto.setSubject(subject);
		bDto.setContent(content);
		bDto.setFileName(fileName);
		
		int result = bDao.modify(bDto);
		
		if (fileName != null && fileName.length() != 0) {
			File srcFile = new File(savePath + "\\" + "temp" + "\\"+fileName); //원문 소스 	
			File destDir = new File(savePath + "\\" + (articleNo)); //원격지 
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true); //복사가 아닌 잘라내기  srcFile에서 destDir로 
			
			//기존 파일 삭제 
			File oldFile = new File(savePath+ "\\" + articleNo + "\\" + originalFileName);
			oldFile.delete();
			
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		if (result > 0) {
			pw.print("<script>"+"alert('修正しました。');" + "location.href='" + "boardList.hanul';"+ "</script>");
		} else {
			pw.print("<script>"+"alert('修正に失敗しました。');" + "history.go(-1);"+ "</script>");
		}
		
		return null;
	}

}
