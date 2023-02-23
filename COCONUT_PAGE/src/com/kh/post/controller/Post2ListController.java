package com.kh.post.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.Post2Service;
import com.kh.post.model.vo.Post2;

/**
 * Servlet implementation class Post2ListController
 */
@WebServlet("/thingsList.po")
public class Post2ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post2ListController() {
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
			
			String keyword =  request.getParameter("keyword");
			String searchword = request.getParameter("searchword");
			
			
			int listCount; 
			int currentPage; 
			int pageLimit;
			int postLimit;
			
			int maxPage;
			int startPage;
			int endPage;
			
			if (keyword != null && keyword.equals("title")) {
				listCount = new Post2Service().selectListCount(searchword);
			} else if (keyword != null && keyword.equals("writer")) {
				listCount = new Post2Service().selectListCountName(searchword);
			} else {
				listCount = new Post2Service().selectListCount();
			}
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			pageLimit = 5;
			postLimit = 10;
			
			maxPage = (int)Math.ceil((double)listCount / postLimit);
			startPage = (currentPage-1)/pageLimit*pageLimit+1;
			endPage = startPage + pageLimit-1;
		
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, postLimit, maxPage, startPage, endPage);
			
			ArrayList<Post2> list = new Post2Service().selectList(pi, keyword, searchword);
			request.setAttribute("pi", pi);
			request.setAttribute("btn_link", "경조사");
			
			list = new Post2Service().selectList(pi, keyword, searchword);
			if(keyword != null && searchword != null) {
				list = new Post2Service().selectList(pi, keyword, searchword);
			} else {
				list = new Post2Service().selectList(pi);
			}
			if(list != null) {
				request.setAttribute("keyword", keyword);
				request.setAttribute("searchword", searchword);
				request.setAttribute("pi", pi);
				request.setAttribute("list", list);
				request.getRequestDispatcher("views/post/post2ListView.jsp").forward(request, response);
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
