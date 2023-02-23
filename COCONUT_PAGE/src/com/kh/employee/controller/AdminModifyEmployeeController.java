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

/**
 * Servlet implementation class AdminModifyEmployeeController
 */
@WebServlet("/Modify.admin")
public class AdminModifyEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminModifyEmployeeController() {
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
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else if (emp.getManagerYN().equals("N")) {
			session.setAttribute("alertMsg", "접근 권한이 없습니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			// 1. 인코딩 변경(post방식)
			request.setCharacterEncoding("UTF-8");

			response.getWriter().append("Served at: ").append(request.getContextPath());

			// 1. 전달 값 뽑기

			String deptCode = request.getParameter("deptCode");
			String jobCode = request.getParameter("jobCode");
			String empPwd = request.getParameter("empPwd");
			String tel = request.getParameter("tel");
			String managerYN = request.getParameter("managerYN");

			int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 검색조건
			String find = request.getParameter("find"); // 검색조건
			String option = request.getParameter("option"); // 키워드

//		Date leaveDate = null;
//		if (request.getParameter("leaveDate") != null) {
//		    
//		    String lDate = request.getParameter("leaveDate");//퇴사일자 날짜형식 변환
//		    leaveDate=Date.valueOf(lDate);
//		}
			int empNo = Integer.parseInt(request.getParameter("empNo"));

			// 객체 생성 후 전달 값 담기
			Member m = new Member();

			m.setDeptCode(deptCode);
			m.setJobCode(jobCode);
			m.setEmpPwd(empPwd);
			m.setTel(tel);
			m.setManagerYn(managerYN);
			m.setEmpNo(empNo);

			System.out.println(deptCode);
			System.out.println(jobCode);
			System.out.println(tel);
			System.out.println(managerYN);
			System.out.println(empNo);

			System.out.println("m : " + m);

			// 서비스단으로 요청
			int result = new MemberService().updateMemberInfo(m);

			// 응답페이지 지정
			if (result > 0) {
				request.setAttribute("member", m);
				request.getSession().setAttribute("alertMsg", "사원 정보가 수정 되었습니다.");
				response.sendRedirect(request.getContextPath() + "/detailView.me?empNo=" + empNo + "&currentPage="
						+ currentPage + "&find=" + find + "&option=" + option);
			} else {
				request.getSession().setAttribute("alertMsg", "사원 정보 수정에  실패했습니다.");
				response.sendRedirect(request.getContextPath() + "/detailView.me?empNo=" + empNo + "&currentPage="
						+ currentPage + "&find=" + find + "&option=" + option);
			}

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
