package com.kh.approval.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.approval.model.service.ApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.Approver;
import com.kh.approval.model.vo.DocSpent;
import com.kh.approval.model.vo.Referrer;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class ApprovalDetailViewController
 */
@WebServlet("/rcvapr.dc")
public class ApprovalDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovalDetailViewController() {
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
			// 보고서인지 지결인지 타입
			int aprType = Integer.parseInt(request.getParameter("aprType"));
			String aprId = request.getParameter("aprId");
			int route = Integer.parseInt(request.getParameter("route"));

			ApprovalService as = new ApprovalService();
			// 일반 보고서 지결 공통 뽑아올 vo들
			Approval a = as.selectApprovalById(aprId); // 결재문서

			Employee e = as.selectEmployee(Integer.parseInt(a.getEmpNo())); // 기안 작성자

			ArrayList<Approver> aprList = as.selectAprListById(aprId); // 결재자 리스트
			ArrayList<Referrer> refList = as.selectRefListById(aprId); // 참조자 리스트


			// 결재 반려 버튼을 보이게 할지 말지 결정하는 변수
			int aprState = 0;

			for (int i = 0; i < aprList.size(); i++) {
				// 내가 결재자일경우
				if (emp.getEmpNo() == aprList.get(i).getEmpNo()) {
					// 열람일자 널일때만 업데이트
					if (aprList.get(i).getOpenDate() == null) {
						as.updateAprOpenDate(emp.getEmpNo(), aprId);
						String[] d = as.selectAppOpenDate(emp.getEmpNo(), aprId);

						aprList.get(i).setOpenDate(d[0]);
						aprList.get(i).setAprYN(d[1]);
					}
					// 내가 첫번째 결재자고 승인이나 반려 안했을경우
					if (i == 0 && (aprList.get(i).getAprYN().equals("1") || aprList.get(i).getAprYN().equals("2"))) {
						aprState = 1;
					} else if (i >= 1) {
						if (aprList.get(i - 1).getAprYN().equals("4")&& (aprList.get(i).getAprYN().equals("1") || aprList.get(i).getAprYN().equals("2"))) {
							// 내가 첫번째 결재자가 아니고 전 결재자가 승인 완료 했을때
							aprState = 1;
						}
					}
				}
			}
			
			// 내가 참조자일경우 열람일자 널일때만 업데이트
			for (Referrer ref : refList) {
				if (emp.getEmpNo() == ref.getEmpNo() && ref.getViewDate() == null) {
					as.updateRefViewDate(emp.getEmpNo(), aprId);
					String d = as.selectRefViewDate(emp.getEmpNo(), aprId);
					ref.setViewDate(d);
				}
			}

			request.setAttribute("aprType", aprType);
			request.setAttribute("a", a);
			request.setAttribute("e", e);
			request.setAttribute("al", aprList);
			request.setAttribute("rl", refList);
			request.setAttribute("aprState", aprState);
			request.setAttribute("route", route);

			// 지출결의서 일경우 지결 내역 뽑아와야함
			if (aprType == 2) {
				ArrayList<DocSpent> dsList = as.selectDSListById(aprId);
				request.setAttribute("dsl", dsList);
				request.getRequestDispatcher("views/approval/approvalSpentDetailView.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("views/approval/approvalDetailView.jsp").forward(request, response);
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
