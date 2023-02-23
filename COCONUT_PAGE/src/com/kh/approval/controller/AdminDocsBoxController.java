package com.kh.approval.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.approval.model.service.AdminApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class AdminDocsBoxController
 */
@WebServlet("/admin.docs")
public class AdminDocsBoxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDocsBoxController() {
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
			
			// 전달값 뽑기
			String searchText = "";
			if (request.getParameter("searchText") != null) {
				searchText = request.getParameter("searchText").trim();
			}
			
			int docStmt = 5;
			if (request.getParameter("docStmt") != null) {
				docStmt = Integer.parseInt(request.getParameter("docStmt"));
			}
			
			// 페이징 처리
			int listCount = 0;
			
			if (searchText == null || searchText.isEmpty()) { // 검색어 없을 경우
				
				listCount = new AdminApprovalService().selectListCount(docStmt);
				
			} else { // 검색어 있을 경우
				
				listCount = new AdminApprovalService().selectSearchListCount(docStmt, searchText);
			}
			
			// 페이징 변수
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int pageLimit = 5;
			int boardLimit = 10;
			int maxPage = (int)Math.ceil((double)listCount / boardLimit);
			int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
			int endPage = startPage + pageLimit - 1;
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			
			// PageInfo 객체로 가공
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Approval> list = null;
			
			if (searchText == null || searchText.isEmpty()) {
				list = new AdminApprovalService().selectApprovalList(pi, docStmt);
						
			} else {
				list = new AdminApprovalService().selectSearchApprovalList(pi, docStmt, searchText);
			}
			
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);
			request.setAttribute("searchText", searchText);
			request.setAttribute("docStmt", docStmt);
			
			// 관리자 문서함으로 포워딩
			request.getRequestDispatcher("views/approval/adminApprovalDocsBox.jsp").forward(request, response);
		
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
