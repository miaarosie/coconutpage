package com.kh.employee.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.service.LoginService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login.em")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// post 방식 : 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 필요한 값 뽑기
		int empNo = Integer.parseInt(request.getParameter("userId"));
		String empPwd = request.getParameter("userPwd");
		String saveId = request.getParameter("saveId");
		
		if (saveId != null && saveId.equals("y")) {
			
			Cookie cookie = new Cookie("saveId", String.valueOf(empNo));
			cookie.setMaxAge(1 * 24 * 60 * 60); // 유효기간 하루
			response.addCookie(cookie);
			
		} else {
			
			Cookie cookie = new Cookie("saveId", String.valueOf(empNo));
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		// VO 객체로 가공
		Employee e = new Employee();
		e.setEmpNo(empNo);
		e.setEmpPwd(empPwd);
		
		// Service 단으로 VO를 보내면서 요청
		Employee loginEmp = new LoginService().loginEmployee(e);
		
		// 결과값에 따라 응답화면 지정
		if (loginEmp == null) {
			
			request.getSession().setAttribute("alertMsg1", "로그인에 실패했습니다.");				
			
		} else {
			
			if (loginEmp.getLeaveDate() != null) {
				// session 객체 생성
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg1", "퇴직자는 로그인할 수 없습니다.");
				
			} else {				
				// session 객체 생성
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg", "성공적으로 로그인되었습니다.");
				session.setAttribute("loginEmp", loginEmp);
			}
			
		}
		
		response.sendRedirect(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
