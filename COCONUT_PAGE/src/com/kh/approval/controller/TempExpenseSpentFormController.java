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
import com.kh.approval.model.service.TempApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.DocSpent;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class TempExpenseSpentFormController
 */
@WebServlet("/temp.spt")
public class TempExpenseSpentFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempExpenseSpentFormController() {
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
			
			// 요청시 전달값 뽑기
			String aprId = request.getParameter("aprId");
			int empNo = ((Employee)request.getSession().getAttribute("loginEmp")).getEmpNo();
					
			// 서비스단으로 요청
			Approval a = new TempApprovalService().selectTempApproval(aprId, empNo);
			ArrayList<DocSpent> list = new TempApprovalService().selectTempDocSpent(aprId);
			
			// 조직도 데이터 불러오기
			ArrayList<String> deptList = new ApprovalService().selectDeptList();
			ArrayList<Employee> eList = new ApprovalService().selectEmployeeList();
	
			request.setAttribute("deptList", deptList);
			request.setAttribute("eList", eList);		
			request.setAttribute("a", a);
			request.setAttribute("list", list);
			
			// 포워딩
			request.getRequestDispatcher("views/approval/tempExpenseReportForm.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
