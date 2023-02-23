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
 * Servlet implementation class ModifyResignController
 */
@WebServlet("/resign.do")
public class ModifyResignController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyResignController() {
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

			// 1. post 방식 : 인코딩 설정
			request.setCharacterEncoding("UTF-8");

			// 2. 값 꺼내기
			int empNo = Integer.parseInt(request.getParameter("empNo"));

			String lDate = request.getParameter("leaveDate");// 퇴사일자 날짜형식 변환
			Date leaveDate = Date.valueOf(lDate);

			int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 검색조건
			String find = request.getParameter("find"); // 검색조건
			String option = request.getParameter("option"); // 키워드

			// 3. VO로 변환

			Member m = new Member();
			m.setEmpNo(empNo);
			m.setLeaveDate(leaveDate);

			// 4. Service 단으로 전달하면서 결과값 받기
			int result = new MemberService().resignMember(m);
			System.out.println(leaveDate);
			System.out.println(empNo);

			// 결과값에 따라 처리하기
			if (result > 0) {
				request.setAttribute("member", m);
				request.getSession().setAttribute("alertMsg", "사원 퇴사처리 되었습니다.");
				response.sendRedirect(request.getContextPath() + "/detailView.me?empNo=" + empNo + "&currentPage="
						+ currentPage + "&find=" + find + "&option=" + option);
			} else {
				request.getSession().setAttribute("alertMsg", "퇴사처리에 실패했습니다.");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
