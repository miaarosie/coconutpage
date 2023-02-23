package com.kh.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.service.MemberService;
import com.kh.employee.model.vo.Employee;
import com.kh.employee.model.vo.Member;

@WebServlet("/detailView.me")
public class EmployeeDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeDetailViewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee empp = (Employee) session.getAttribute("loginEmp");

		if (empp == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}  else {

			String empNo = request.getParameter("empNo");

			Member emp = new MemberService().selectMember(empNo);

			int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 검색조건
			String find = request.getParameter("find"); // 검색조건
			String option = request.getParameter("option"); // 키워드

			if (emp != null) {
				request.setAttribute("emp", emp);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("find", find);
				request.setAttribute("option", option);

				request.getRequestDispatcher("views/employee/detail.jsp").forward(request, response);
			}
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
