package com.kh.approval.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.approval.model.service.AdminApprovalService;
import com.kh.approval.model.service.ApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.Approver;
import com.kh.approval.model.vo.DocSpent;
import com.kh.approval.model.vo.Referrer;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class DetailAdminDocsBoxController
 */
@WebServlet("/admin.spt")
public class DetailAdminDocsBoxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailAdminDocsBoxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg1", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
		
		
			// 지출결의서 상세조회
			// 요청시 전달값 뽑기
			String aprId = request.getParameter("aprId");
			
			// 서비스단으로 요청
			Approval a = new ApprovalService().selectApprovalById(aprId); // 결재문서
			Employee e = new ApprovalService().selectEmployee(Integer.parseInt(a.getEmpNo())); // 기안 작성자
			
			ArrayList<DocSpent> list = new AdminApprovalService().selectDocSpentList(aprId);
					
			ArrayList<Approver> aprList = new ApprovalService().selectAprListById(aprId); // 결재자 리스트
			ArrayList<Referrer> refList = new ApprovalService().selectRefListById(aprId); // 참조자 리스트
			
			request.setAttribute("a", a);
			request.setAttribute("e", e);
			request.setAttribute("list", list);
			request.setAttribute("al", aprList);
			request.setAttribute("rl", refList);
					
			// 상세조회 페이지로 포워딩
			request.getRequestDispatcher("views/approval/detailAdminDocsBox.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
