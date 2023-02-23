package com.kh.employee.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.service.MemberService;
import com.kh.employee.model.vo.Employee;
import com.kh.employee.model.vo.Member;

/**
 * Servlet implementation class EmplistUpdateController
 */
@WebServlet("/emp.up")
public class EmplisInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmplisInsertController() {
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
		} else if (emp.getManagerYN().equals("N")) {
			session.setAttribute("alertMsg", "접근 권한이 없습니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			// 1. 인코딩 변경(post방식)
			request.setCharacterEncoding("UTF-8");

			// 2. 전달 값들을 뽑아서 기록하기
			int empNo = Integer.parseInt(request.getParameter("empNo"));
			String empName = request.getParameter("empName");
			String gender = request.getParameter("gender");
			String empNum = request.getParameter("empNum1") + "-" + request.getParameter("empNum2");
			String deptCode = request.getParameter("deptCode");
			String jobCode = request.getParameter("jobCode");
			String email = request.getParameter("email");
			String empPwd = request.getParameter("empPwd");
			String tel = request.getParameter("tel");
			String phone = request.getParameter("phone");
			String empAddress = request.getParameter("empAddress");
			String managerYN = request.getParameter("managerYN");

			String eDate = request.getParameter("enrollDate"); // 입사일자 날짜형식 변환
			Date enrollDate = Date.valueOf(eDate);

			// 3.멤버 변수로 담아서 객체 생성

			Member m = new Member();

			m.setEmpNo(empNo);
			m.setEmpName(empName);
			m.setGender(gender);
			m.setEmpNum(empNum);
			m.setDeptCode(deptCode);
			m.setJobCode(jobCode);
			m.setEmail(email);
			m.setEmpPwd(empPwd);
			m.setTel(tel);
			m.setPhone(phone);
			m.setEmpAddress(empAddress);
			m.setManagerYn(managerYN);
			m.setEnrollDate(enrollDate);

			

			// 4.서비스단으로 요청

			int result = new MemberService().insertMember(m);

			// 응답페이지 지정
			if (result > 0) {
				request.setAttribute("member", m);
				request.getSession().setAttribute("alertMsg", "사원이 등록되었습니다.");
				response.sendRedirect(request.getContextPath() + "/List.do?currentPage=1");

			} else {
				request.getSession().setAttribute("alertMsg", "사원 등록 실패");
				response.sendRedirect(request.getContextPath() + "/List.do?currentPage=1");
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
