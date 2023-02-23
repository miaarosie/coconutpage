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
import com.kh.post.model.service.ShareService;
import com.kh.post.model.vo.Share;

/**
 * Servlet implementation class ShareSearchList
 */
@WebServlet("/shareSearch.share")
public class ShareSearchList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareSearchList() {
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
		
			int listCount;
			int currentPage =  Integer.parseInt(request.getParameter("currentPage"));
			int pageLimit;
			int boardLimit;
			int maxPage;
			int startPage;
			int endPage;
			String searchField = request.getParameter("searchField");
			String searchText = request.getParameter("searchText");
			
			listCount = new ShareService().searchListCount(searchField, searchText);
			
			pageLimit = 5;
			
			boardLimit = 10;
			
			maxPage = (int)(Math.ceil((double)listCount/boardLimit));
			
			startPage = (currentPage -1) / pageLimit * pageLimit + 1;
			
			endPage = startPage + pageLimit -1;
			
			if(endPage > maxPage) {
				
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(listCount,currentPage,pageLimit, boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Share> list = new ShareService().selectShareList(pi, searchField, searchText);
			
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);
			request.setAttribute("searchField", searchField);
			request.setAttribute("searchText", searchText);
			
			request.getRequestDispatcher("views/post/shareSearchList.jsp").forward(request, response);
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
