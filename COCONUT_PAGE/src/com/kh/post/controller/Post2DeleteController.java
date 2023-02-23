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

/**
 * Servlet implementation class Post2DeleteController
 */
@WebServlet("/thingsDelete.po")
public class Post2DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post2DeleteController() {
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
			int fileNo = 0;
			String fileNoString = request.getParameter("fno");
			if(fileNoString != null) {
				fileNo = Integer.parseInt(fileNoString);
			}
			int result = new Post2Service().deletePost2(postNo, fileNo);
			if(result >0) {
				response.sendRedirect(request.getContextPath() + "/thingsList.po?currentPage=1");
			} else {
				request.setAttribute("alertMsg", "게시글 삭제 실패");
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
