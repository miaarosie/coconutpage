package com.kh.calendar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.calendar.model.service.CalendarService;
import com.kh.calendar.model.vo.Calendar;

/**
 * Servlet implementation class CalendarDeleteContorller
 */
@WebServlet("/delete.cal")
public class CalendarDeleteContorller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarDeleteContorller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		int schdNo = Integer.parseInt(request.getParameter("id"));
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		
		Calendar c = new Calendar();
		
		c.setEmpNo(empNo);
		c.setSchdNo(schdNo);
		
		int result = new CalendarService().deleteCalendar(c);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
