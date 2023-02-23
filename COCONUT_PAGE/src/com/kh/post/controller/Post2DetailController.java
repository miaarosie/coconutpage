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
import com.kh.post.model.vo.Like;
import com.kh.post.model.vo.Post2;
import com.kh.post.model.vo.Post2File;

/**
 * Servlet implementation class Post2DetailController
 */
@WebServlet("/thingsDetail.po")
public class Post2DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post2DetailController() {
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
			
			int result = new Post2Service().increaseCount(postNo);
			int empNo = ((Employee)request.getSession().getAttribute("loginEmp")).getEmpNo();
			
			if(result>0) {
				Post2 p = new Post2Service().selectPost2(postNo);
				Post2File pf = new Post2Service().selectPost2File(postNo);
				Like l = new Post2Service().selectLike(postNo, empNo);
				
				request.setAttribute("p", p);
				request.setAttribute("pf", pf);
				request.setAttribute("l", l);
				
				request.getRequestDispatcher("views/post/post2DetailView.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMsg", "게시글 상세조회 실패");
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
