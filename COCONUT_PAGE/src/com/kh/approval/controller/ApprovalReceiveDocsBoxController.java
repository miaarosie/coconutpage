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
 * Servlet implementation class ApprovalReceiveDocsBoxController
 */
@WebServlet("/rcv.docs")
public class ApprovalReceiveDocsBoxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovalReceiveDocsBoxController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg1", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			ApprovalService as = new ApprovalService();

			// 로그인한 회원이 받은 리스트만 조회되도록
			Employee loginEmp = (Employee) request.getSession().getAttribute("loginEmp");
			int empNo = loginEmp.getEmpNo();

			// 검색어
			String searchText = request.getParameter("searchText");

			// 필터
			int docStmt = Integer.parseInt(request.getParameter("docStmt"));

			// 전체 글 개수 total
			int listCount = 0;

			if (searchText == null || searchText.isEmpty()) {
				// 기본 전체 리스트 조회
				listCount = as.totalApprovalReceive(empNo, docStmt);
			} else {
				listCount = as.totalSearchApprovalReceive(empNo, docStmt, searchText);
				request.setAttribute("searchText", searchText);
			}

			// 현재 페이지 번호 pageNo
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// 한페이지에 게시물 개수 pageSize
			int boardLimit = 10;

			// 전체 페이지 개수 계산 pageCount
			int maxPage = listCount / boardLimit + (listCount % boardLimit == 0 ? 0 : 1);

			// 한 페이지에 보여줄 페이지 블럭 pageBlock
			int pageLimit = 5;

			// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
			int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;

			// 한 페이지에 보여줄 페이지 블럭 끝 번호 계산
			int endPage = startPage + pageLimit - 1;
			if (endPage > maxPage) {
				endPage = maxPage;
			}

			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

			ArrayList<Approval> aList = null;

			if (searchText == null || searchText.trim().isEmpty()) {
				// 문서 리스트 service 단에서 불러오기
				aList = as.selectApprovalReceiveList(pi, empNo, docStmt);
			} else {
				aList = as.selectSearchApprovalReceiveList(pi, empNo, searchText, docStmt);
			}

			// 값 넘기기
			request.setAttribute("pi", pi);
			request.setAttribute("aList", aList);
			request.setAttribute("docStmt", docStmt);
			request.getRequestDispatcher("views/approval/approvalReceiveDocsBox.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
