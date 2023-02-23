package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class NoticeEnrollFormController
 */
@WebServlet("/noList.wt")
public class NoticeEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeEnrollFormController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		request.setAttribute("boardNo", boardNo);
		HttpSession session = request.getSession();
		Employee loginEmployee = (Employee) session.getAttribute("loginEmp");

		if (loginEmployee == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else if (loginEmployee.getManagerYN().equals("Y")) {
			request.getRequestDispatcher("views/notice/noticeEnrollForm.jsp").forward(request, response);
		} else {
			session.setAttribute("alertMsg", "접근 권한이 없습니다.");
			response.sendRedirect(request.getContextPath() + "/noList.board?boardNo=" + boardNo + "&currentPage=" + currentPage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
