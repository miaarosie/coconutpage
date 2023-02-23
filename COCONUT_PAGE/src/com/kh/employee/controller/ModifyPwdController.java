package com.kh.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.service.ModifyEmpInfoService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class ModifyPwdController
 */
@WebServlet("/pwd.em")
public class ModifyPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyPwdController() {
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
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			// post 방식 : 인코딩 설정
			request.setCharacterEncoding("UTF-8");

			// 값 꺼내기
			String empPwd = request.getParameter("userPwd");
			String newPwd = request.getParameter("newPwd");

			int empNo = Integer.parseInt(request.getParameter("empNo"));

			// VO 로 가공
			Employee e = new Employee();
			e.setEmpNo(empNo);
			e.setEmpPwd(empPwd);

			// Service 단으로 전달하면서 결과값 받기
			Employee updateEmp = new ModifyEmpInfoService().modifyPwd(e, newPwd);

			// 결과값에 따라 처리하기
			if (updateEmp == null) {
				request.getSession().setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");
				response.sendRedirect(request.getContextPath());
			} else {
				request.getSession().setAttribute("loginEmp", updateEmp);
				request.getSession().setAttribute("alertMsg", "비밀번호 변경에 성공했습니다.");

				response.sendRedirect(request.getContextPath());
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
