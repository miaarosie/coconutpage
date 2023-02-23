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

/**
 * Servlet implementation class AjaxeEmpInfoCheckController
 */
@WebServlet("/check.me")
public class AjaxeEmpInfoCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxeEmpInfoCheckController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else if (emp.getManagerYN().equals("N")) {
			session.setAttribute("alertMsg", "접근 권한이 없습니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			int checkEmpNo = Integer.parseInt(request.getParameter("checkEmpNo"));
			int count = new MemberService().noCheck(checkEmpNo);
			response.setContentType("text/html; charset=UTF-8");

			if (count > 0) {// 이미 사용중인 아이디가 있을 경우 => 사용 불가능

				response.getWriter().print("NNNNN");

			} else {// 존재하는 아이디가 없을 경우 =>사용 가능
				response.getWriter().print("NNNNY");
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
