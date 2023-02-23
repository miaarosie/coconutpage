package com.kh.post.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.ShareService;
import com.kh.post.model.vo.Share;
import com.kh.post.model.vo.ShareAttachment;

/**
 * Servlet implementation class ShareUpdateFormController
 */
@WebServlet("/updateForm.share")
public class ShareUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareUpdateFormController() {
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
		
		
			int postNo = Integer.parseInt(request.getParameter("pno"));
			
			ArrayList<ShareAttachment> list = new ShareService().selectShareAttachmentList(postNo);
			
			Share s = new ShareService().selectPost(postNo);
			
			request.setAttribute("list", list);
			request.setAttribute("s", s);
			
			request.getRequestDispatcher("views/post/shareUpdateForm.jsp").forward(request, response);
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
