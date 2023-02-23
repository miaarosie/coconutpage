package com.kh.card.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.card.model.service.KanbanService;
import com.kh.card.model.vo.Kanban;

/**
 * Servlet implementation class InsertCardController
 */
@WebServlet("/insert.kan")
public class InsertCardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cardTitle = request.getParameter("cardTitle");
		String cardContent = request.getParameter("cardContent");
		String dewDate = request.getParameter("dewDate");
		int cardPriority = Integer.parseInt(request.getParameter("cardPriority"));
		String empNo = request.getParameter("empNo");
		
		Kanban c = new Kanban(cardTitle, cardContent, dewDate, cardPriority, empNo);
		
		int result = new KanbanService().insertCard(c);
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
