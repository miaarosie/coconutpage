package com.kh.chart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.approval.model.service.ApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.chart.model.service.ChartService;
import com.kh.chart.model.vo.Chart;

/**
 * Servlet implementation class ApprovalChartController
 */
@WebServlet("/appro.ch")
public class ApprovalChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalChartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Chart> approList = new ChartService().selectApprovalList();
				
		request.setAttribute("approList", approList);

		// System.out.println(approList);	
		request.getRequestDispatcher("views/chart/approvalChart.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
