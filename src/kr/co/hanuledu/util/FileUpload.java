package kr.co.hanuledu.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpload {

	public static Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 업로드 파일을 저장할 path를 설정(파일 시스템 방식)
		String savePath = "D:\\Study_Servlet_Jsp\\hanulshop\\WebContent\\images\\board";
		Map<String, String> boardMap = new HashMap<String, String>();
		String encoding = "UTF-8";

		// 글 이미지 저장 폴더에 대해 파일 객체를 생성
		File currentDirPath = new File(savePath);

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 버퍼내의 데이터를 보존하는 임시영역을 지정
		factory.setRepository(currentDirPath);

		// 한번에 메모리에 저장할 사이즈 설정
		factory.setSizeThreshold(10 * 1024 * 1024);

		// 파일 데이터를 취득하는 클래스
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// 보내진 데이터를 FileItem 오브젝트에 분할하고 List콜렉션으로 반환.
			List<FileItem> items = upload.parseRequest(request);

			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = items.get(i);

				if (fileItem.isFormField()) { // 파일이 아니 일반적인 입력 파라미터일 경우
					boardMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\"); // 윈도우 기반

						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/"); // 유닉스, 리눅스 기반
						}

						// 첨부한 파일을 먼저 temp 폴더에 업로드
						String fileName = fileItem.getName().substring(idx + 1);

						boardMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
			}

			boardMap.put("savePath", savePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardMap;
	}
}
