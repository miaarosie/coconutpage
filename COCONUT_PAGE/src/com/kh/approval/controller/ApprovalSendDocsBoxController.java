package com.kh.approval.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.approval.model.service.ApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class approvalSendDocsBoxController
 */
@WebServlet("/send.docs")
public class ApprovalSendDocsBoxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalSendDocsBoxController() {
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
		
			ApprovalService as = new ApprovalService();
			
			// 로그인한 회원이 작성한 리스트만 조회되도록
			Employee loginEmp = (Employee)request.getSession().getAttribute("loginEmp");
			int empNo = loginEmp.getEmpNo();
					
			String searchText = "";
			if (request.getParameter("searchText") != null) {			
				searchText = request.getParameter("searchText").trim();
			}
			
			int docStmt = 5;
			if (request.getParameter("docStmt") != null) {
				docStmt = Integer.parseInt(request.getParameter("docStmt"));
			}
			
			// 페이징처리
			// 검색처리마다 게시글 개수가 달라져야 함
			int listCount = 0;  // 총 게시글 개수
			
			if (searchText == null || searchText.isEmpty()) {
				// 기본 전체 리스트 조회
				switch (docStmt) {
				case 1: 
				case 2: 
				case 3:
				case 4: listCount = as.selectListCount(empNo, docStmt); break;
				default: 
					listCount = as.selectListCount(empNo); break;
				}
			} else {
				// 검색어 포함 전체 리스트 조회
				switch (docStmt) {
				case 1: 
				case 2: 
				case 3:
				case 4: listCount = as.selectListSearchCount(empNo, docStmt, searchText);
				default: 
					listCount = as.selectListSearchCount(empNo, searchText);
				}
				
			}
			
			int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 현재 요청한 페이지
			int pageLimit = 5; // 페이징바의 페이지 최대 개수
			int boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 개수
			int maxPage = (int)Math.ceil((double)listCount / boardLimit); // 마지막 페이지 (총 페이지)
			int startPage = (currentPage - 1) / pageLimit * pageLimit + 1; // 각 페이징바의 시작 페이지
			int endPage = startPage + pageLimit - 1; // 페이지 하단에 보여질 페이지의 끝 수
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			
			// PageInfo 객체로 가공
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Approval> list = null;
			
			if (searchText == null || searchText.trim().isEmpty()) {
				// 문서 리스트 service 단에서 불러오기
				switch (docStmt) {
				case 1: 
				case 2: 
				case 3:
				case 4: list = as.selectApprovalList(pi, empNo, docStmt); break;
				default: 
					list = as.selectApprovalList(pi, empNo); break;
				}
				
			} else {
				switch (docStmt) {
				case 1: 
				case 2: 
				case 3:
				case 4: list = as.selectApprovalSearchList(pi, empNo, searchText, docStmt); break;
				default: 
					list = as.selectApprovalSearchList(pi, empNo, searchText); break;
				}
			}
			
			// 값 넘기기
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);
			request.setAttribute("searchText", searchText);
			request.setAttribute("docStmt", docStmt);
			
			// 상신문서함으로 포워딩
			request.getRequestDispatcher("views/approval/approvalSendDocsBox.jsp").forward(request, response);
		
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
