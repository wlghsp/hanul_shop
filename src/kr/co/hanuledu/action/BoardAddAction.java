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

public class BoardAddAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDTO bDto = new BoardDTO();
		BoardDAO bDao = new BoardDAO();
		
		Map<String, String> boardMap = FileUpload.upload(request, response);
		
		int articleNo = bDao.getNewArticleNo();
		String title = boardMap.get("subject");
		String content = boardMap.get("content");
		String fileName = boardMap.get("fileName");
		String savePath = boardMap.get("savePath");
		String id = boardMap.get("id");
		String lock_post = boardMap.get("lock_post");
		
		bDto.setId(id);
		bDto.setSubject(title);
		bDto.setContent(content);
		bDto.setFileName(fileName);
		bDto.setRef(articleNo + 1);
		bDto.setRe_step(0);
		bDto.setRe_level(1);
		bDto.setLock_post(lock_post);
		int result = bDao.create(bDto);
		int seq = bDto.getArticleNo();
		
		if (fileName != null && fileName.length() != 0) {
			File srcFile = new File(savePath + "\\" + "temp" + "\\" + fileName); //원문 소스 	
			File destDir = new File(savePath + "\\" + (seq)); //원격지 
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true); //복사가 아닌 잘라내기  srcFile에서 destDir로 
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		if (result > 0) {
			pw.print("<script>"+"alert('新しい投稿が登録されました。');" + "location.href='" + "boardList.hanul';"+ "</script>");
		} else {
			pw.print("<script>"+"alert('投稿の登録に失敗しました。');" + "history.go(-1);"+ "</script>");
		}
		
		return null;
		
	}

}
