package com.kh.approval.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.approval.model.service.TempApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class TempApprovalSendBoxController
 */
@WebServlet("/temp.docs")
public class TempApprovalSendBoxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TempApprovalSendBoxController() {
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

			// 로그인한 회원이 작성한 글만 볼 수 있도록
			Employee loginEmp = (Employee) request.getSession().getAttribute("loginEmp");
			int empNo = loginEmp.getEmpNo();

			String searchText = "";
			if (request.getParameter("searchText") != null) {
				searchText = request.getParameter("searchText").trim();
			}

			// 페이징 처리
			int listCount = 0;

			if (searchText == null || searchText.isEmpty()) {
				// 기본 전체 리스트 조회
				listCount = new TempApprovalService().selectListCount(empNo);
			} else {
				// 검색어 리스트 조회
				listCount = new TempApprovalService().selectSearchListCount(empNo, searchText);
			}

			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int pageLimit = 5;
			int boardLimit = 10;
			int maxPage = (int) Math.ceil((double) listCount / boardLimit); // 마지막 페이지 (총 페이지)
			int startPage = (currentPage - 1) / pageLimit * pageLimit + 1; // 각 페이징바의 시작 페이지
			int endPage = startPage + pageLimit - 1; // 페이지 하단에 보여질 페이지의 끝 수
			if (endPage > maxPage) {
				endPage = maxPage;
			}

			// PageInfo 객체로 가공
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

			ArrayList<Approval> list = null;

			if (searchText == null || searchText.isEmpty()) {

				list = new TempApprovalService().selectApprovalList(pi, empNo);
			} else {

				list = new TempApprovalService().selectSearchApprovalList(pi, empNo, searchText);
			}

			request.setAttribute("searchText", searchText);
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);

			request.getRequestDispatcher("views/approval/tempApprovalSendBox.jsp").forward(request, response);

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
