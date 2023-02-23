package com.kh.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.Post2Service;
import com.kh.post.model.vo.Post2;
import com.kh.post.model.vo.Post2File;

/**
 * Servlet implementation class Post2UpdateFormController
 */
@WebServlet("/thingsUpdateForm.po")
public class Post2UpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post2UpdateFormController() {
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
		
			Post2Service pService = new Post2Service();
			int postNo = Integer.parseInt(request.getParameter("pno"));
			Post2 p = pService.selectPost2(postNo);
			// 글번호, 제목, 부서명, 사원명, 작성일, 조회수, 내용
			Post2File pf = pService.selectPost2File(postNo);
			
			request.setAttribute("p", p);
			request.setAttribute("pf", pf);
			
			
			request.getRequestDispatcher("views/post/post2UpdateForm.jsp").forward(request, response);
			
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
