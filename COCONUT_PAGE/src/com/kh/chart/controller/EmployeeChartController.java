package com.kh.chart.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.chart.model.service.ChartService;

/**
 * Servlet implementation class EmployeeChartController
 */
@WebServlet("/emp.ch")
public class EmployeeChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeChartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 퇴사자현황
		HashMap<Integer, Integer> leaveList = new ChartService().leaveByMonth();
		
		// 입사자현황
		HashMap<Integer, Integer> enrollList = new ChartService().enrollByMonth();
		
		// 총재직자명수 (달마다)
		// HashMap<Integer, Integer> totalList = new ChartService().totalByMonth();
		
		request.setAttribute("leaveList", leaveList);
		request.setAttribute("enrollList", enrollList);
		// request.setAttribute("totalByMonth", totalByMonth);
		
		request.getRequestDispatcher("views/chart/employeeChart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
