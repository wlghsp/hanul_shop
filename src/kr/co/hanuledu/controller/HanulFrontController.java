package kr.co.hanuledu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.hanuledu.action.Action;
import kr.co.hanuledu.action.ActionForward;
import kr.co.hanuledu.action.BoardAddAction;
import kr.co.hanuledu.action.BoardDeleteAction;
import kr.co.hanuledu.action.BoardDownAction;
import kr.co.hanuledu.action.BoardFormAction;
import kr.co.hanuledu.action.BoardListAction;
import kr.co.hanuledu.action.BoardModifyAction;
import kr.co.hanuledu.action.BoardReplyAction;
import kr.co.hanuledu.action.BoardReplyFormAction;
import kr.co.hanuledu.action.ContractAction;
import kr.co.hanuledu.action.FindZipNumAction;
import kr.co.hanuledu.action.IdCheckFormAction;
import kr.co.hanuledu.action.ImagePreViewAction;
import kr.co.hanuledu.action.IndexAction;
import kr.co.hanuledu.action.JoinAction;
import kr.co.hanuledu.action.JoinFormAction;
import kr.co.hanuledu.action.LoginAction;
import kr.co.hanuledu.action.LoginFormAction;
import kr.co.hanuledu.action.LogoutAction;
import kr.co.hanuledu.action.ProductDetailAction;
import kr.co.hanuledu.action.BoardViewAction;

/**
 * Servlet implementation class HanulFrontController
 */
@WebServlet("/HanulFrontController")
public class HanulFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HanulFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 깨짐 방지 (post방식)
		request.setCharacterEncoding("UTF-8");
		
		Action action = null;
		ActionForward forward = null;
		
		String uri= request.getRequestURI();
		String ctx= request.getContextPath();
		String command = uri.substring(ctx.length());
		
		System.out.println("URI : "+ uri);
		System.out.println("CTX : "+ ctx);
		System.out.println("COMMAND : "+ command);
		
		//----------------액션 매핑 ----------------
		if (command.equals("/index.hanul")) {
			action = new IndexAction();
			forward = action.excute(request, response);
		} else if (command.equals("/login_form.hanul")) {
			action = new LoginFormAction();
			forward = action.excute(request, response);
		} else if (command.equals("/login.hanul")) {
			action = new LoginAction();
			forward = action.excute(request, response);
		} else if (command.equals("/contract.hanul")) {
			action = new ContractAction();
			forward = action.excute(request, response);
		} else if (command.equals("/join_form.hanul")) {
			action = new JoinFormAction();
			forward = action.excute(request, response);
		} else if (command.equals("/join.hanul")) {
			action = new JoinAction();
			forward = action.excute(request, response);
		} else if (command.equals("/logout.hanul")) {
			action = new LogoutAction();
			forward = action.excute(request, response);
		} else if (command.equals("/id_check_form.hanul")) {
			action = new IdCheckFormAction();
			forward = action.excute(request, response);
		} else if (command.equals("/find_zip_num.hanul")) {
			action = new FindZipNumAction();
			forward = action.excute(request, response);
		} else if (command.equals("/product_detail.hanul")) {
			action = new ProductDetailAction();
			forward = action.excute(request, response);
		} 
		//--------------- 게시판 액션 맵핑 ---------------
		if (command.equals("/boardList.hanul")) {
			action = new BoardListAction();
			forward = action.excute(request, response);
		} else if (command.equals("/boardView.hanul")) {
			action = new BoardViewAction();
			forward = action.excute(request, response);
		} else if (command.equals("/boardForm.hanul")) {
			action = new BoardFormAction();
			forward = action.excute(request, response);
		} else if (command.equals("/boardAdd.hanul")) {
			action = new BoardAddAction();
			forward = action.excute(request, response);
		} else if (command.equals("/imagePreView.hanul")) {
			action = new ImagePreViewAction();
			forward = action.excute(request, response);
		} else if (command.equals("/boardModify.hanul")) {
			action = new BoardModifyAction();
			forward = action.excute(request, response);
		} else if (command.equals("/boardDelete.hanul")) {
			action = new BoardDeleteAction();
			forward = action.excute(request, response);
		}  else if (command.equals("/boardReplyForm.hanul")) {
			action = new BoardReplyFormAction();
			forward = action.excute(request, response);
		} else if (command.equals("/boardReply.hanul")) {
			action = new BoardReplyAction();
			forward = action.excute(request, response);
		} else if (command.equals("/boardDown.hanul")) {
			action = new BoardDownAction();
			forward = action.excute(request, response);
		} 
		
		//----------------공통 분기 작업 ----------------
		if (forward !=null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	}

}
