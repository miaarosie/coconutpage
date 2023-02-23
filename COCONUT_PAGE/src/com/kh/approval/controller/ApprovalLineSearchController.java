package com.kh.approval.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.approval.model.service.ApprovalService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class ApprovalLineSearchController
 */
@WebServlet("/search.apline")
public class ApprovalLineSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovalLineSearchController() {
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
			String keyword = request.getParameter("keyword");
			String type = request.getParameter("type");

			ArrayList<Employee> eList = new ArrayList<Employee>();
			if (type.equals("dept")) {
				eList = new ApprovalService().searchAprLineEListByDept(keyword);
			} else {
				eList = new ApprovalService().searchAprLineEListByName(keyword);
			}

			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(eList, response.getWriter());
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
