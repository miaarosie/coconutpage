package com.kh.approval.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.approval.model.service.ApprovalService;
import com.kh.approval.model.vo.Approver;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class ApprovalYnUpdateController
 */
@WebServlet("/update.ayn")
public class ApprovalYnUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovalYnUpdateController() {
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
			request.setCharacterEncoding("UTF-8");

			String aprType = request.getParameter("aprType");
			String aprYN = request.getParameter("apryn");
			String aprId = request.getParameter("aid");
			String aprComment = request.getParameter("aprComment");

			Approver ap = new Approver();
			ap.setAprId(aprId);
			ap.setAprYN(aprYN);
			ap.setAprComment(aprComment);
			ap.setEmpNo(((Employee) request.getSession().getAttribute("loginEmp")).getEmpNo());

			int result = new ApprovalService().updateAprYN(ap);

			if (result > 0) {
				request.getSession().setAttribute("alertMsg", "결재 완료하였습니다.");
				response.sendRedirect(request.getContextPath() + "/rcvapr.dc?aprType=" + aprType + "&aprId=" + aprId+"&route=1");
			} else {
				request.getSession().setAttribute("alertMsg", "결재내역을 업데이트하지 못했습니다.");
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
		doGet(request, response);
	}

}
