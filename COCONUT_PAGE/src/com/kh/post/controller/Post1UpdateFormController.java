package com.kh.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.Post1Service;
import com.kh.post.model.vo.Post1;
import com.kh.post.model.vo.Post1File;

/**
 * Servlet implementation class Post1UpdateFormController
 */
@WebServlet("/wagleUpdateForm.po")
public class Post1UpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post1UpdateFormController() {
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
			
				Post1Service pService = new Post1Service();
				
				int postNo = Integer.parseInt(request.getParameter("pno"));
			
				Post1 p = pService.selectPost1(postNo);


				Post1File pf = pService.selectPost1File(postNo);
				
				request.setAttribute("p", p);
				request.setAttribute("pf", pf);
				
				
				request.getRequestDispatcher("views/post/post1UpdateForm.jsp").forward(request, response);
				
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
