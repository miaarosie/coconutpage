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
 * Servlet implementation class ModifyEmpInfoController
 */
@WebServlet("/info.em")
public class ModifyEmpInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyEmpInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post 방식 : 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기
		String nickName = request.getParameter("nickname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		Employee loginEmp = (Employee)request.getSession().getAttribute("loginEmp");
		
		int empNo = loginEmp.getEmpNo();
		
		// VO 객체로 가공
		Employee e = new Employee();
		e.setEmpNo(empNo);
		e.setNickName(nickName);
		e.setAddress(address);
		e.setPhone(phone);
		
		// Service 단으로 VO 전달하고 결과값 받기
		Employee updateEmp = new ModifyEmpInfoService().modifyInfo(e);
		
		// 결과값에 따라 화면 지정
		if (updateEmp == null) {
			
			System.out.println("정보 수정 실패");
			
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginEmp", updateEmp);
			session.setAttribute("alertMsg", "회원정보 수정에 성공했습니다.");
			
			response.sendRedirect(request.getContextPath());
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