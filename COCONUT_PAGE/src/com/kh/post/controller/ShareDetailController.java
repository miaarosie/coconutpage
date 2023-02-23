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
import com.kh.post.model.vo.Like;
import com.kh.post.model.vo.Share;
import com.kh.post.model.vo.ShareAttachment;

/**
 * Servlet implementation class ShareDetailController
 */
@WebServlet("/detail.share")
public class ShareDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareDetailController() {
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
			
			int result = new ShareService().increaseCount(postNo);
			
			int empNo = ((Employee)request.getSession().getAttribute("loginEmp")).getEmpNo();
			
			if(result > 0) {
				
				Share s = new ShareService().selectPost(postNo);
				
				ArrayList<ShareAttachment> list = new ShareService().selectShareAttachmentList(postNo);
				 
				Like l = new ShareService().selectLike(postNo, empNo);
				
				request.setAttribute("s", s);
				request.setAttribute("list", list);
				request.setAttribute("l", l);
				
				
				request.getRequestDispatcher("views/post/shareDetailView.jsp").forward(request, response);
			} else {
				
				
				
			}
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
