package com.kh.employee.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.service.MemberService;
import com.kh.employee.model.vo.Employee;
import com.kh.employee.model.vo.Member;

//사원 키워드로 검색하는 서블릿
/**
 * Servlet implementation class EmpListController
 */
@WebServlet("/emplist.do")
public class EmpListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			int listCount;
			int currentPage;
			int pageLimit;
			int boardLimit;

			int maxPage;
			int startPage;
			int endPage;

			String find = request.getParameter("find"); // 검색조건
			String option = request.getParameter("option"); // 키워드

			if (find != null && find.equals("EMP_NO")) {
				listCount = new MemberService().selectListCountbyEmpNo(option);

			} else if (find != null && find.equals("EMP_NAME")) {

				listCount = new MemberService().selectListCountbyEmpName(option);
			} else if (find != null && find.equals("DEPT_NAME")) {

				listCount = new MemberService().selectListCountbyDeptName(option);

			} else if (find != null && find.equals("JOB_NAME")) {
				listCount = new MemberService().selectListCountbyJobName(option);
			} else {
				listCount = new MemberService().selectListCount();
			}

			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			pageLimit = 5;
			boardLimit = 10;

			maxPage = (int) Math.ceil((double) listCount / boardLimit);
			startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
			endPage = startPage + pageLimit - 1;

			if (endPage > maxPage) {
				endPage = maxPage;
			}

			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

			ArrayList<Member> optionList = new MemberService().showList(option, pi, find);

			request.setAttribute("pi", pi);
			request.setAttribute("find", find);
			request.setAttribute("option", option);
			request.setAttribute("optionList", optionList);

			request.getRequestDispatcher("views/employee/searchKeywordMember.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
