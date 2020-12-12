package kr.co.hanuledu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//추상 메소드 몸체가 없어 중괄호 없음.
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException;

}
