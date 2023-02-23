package com.kh.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.ShareService;
import com.kh.post.model.vo.Reply;

/**
 * Servlet implementation class AjaxNestedReplyInsertController
 */
@WebServlet("/nrinsert.po")
public class AjaxNestedReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxNestedReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");
		if (emp == null) {
			session.setAttribute("alertMsg1", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
		
			String replyContent = request.getParameter("nestContent");
			int parentReply = Integer.parseInt(request.getParameter("parent"));
			int postNo = Integer.parseInt(request.getParameter("pno"));
			int userNo = ((Employee)request.getSession().getAttribute("loginEmp")).getEmpNo();
			
			Reply r= new Reply();
			r.setPostNo(postNo);
			r.setParentReply(parentReply);
			r.setReplyContent(replyContent);
			r.setEmpNo(String.valueOf(userNo));
			
			int result = new ShareService().insertNestedReply(r);
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
