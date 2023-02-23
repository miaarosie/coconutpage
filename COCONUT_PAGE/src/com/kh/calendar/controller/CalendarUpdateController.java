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
 * Servlet implementation class CalendarUpdateController
 */
@WebServlet("/update.cal")
public class CalendarUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		String schdTitle = request.getParameter("title");
		String startDate = request.getParameter("start");
		String endDate = request.getParameter("end");
		
		
		Calendar c = new Calendar();
		
		c.setEmpNo(empNo);
		c.setSchdTitle(schdTitle);
		c.setStartDate(startDate);
		c.setEndDate(endDate);
		
		int result = new CalendarService().updateCalendar(c);
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
