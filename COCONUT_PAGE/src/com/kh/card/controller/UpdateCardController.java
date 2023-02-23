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
 * Servlet implementation class UpdateCardController
 */
@WebServlet("/update.kan")
public class UpdateCardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		String cardContent = request.getParameter("cardContent");
		String dewDate = request.getParameter("dewDate");
		System.out.println(dewDate);
		int cardPriority = Integer.parseInt(request.getParameter("cardPriority"));
		
		Kanban c = new Kanban(cardNo, cardContent, dewDate, cardPriority);
		
		int result = new KanbanService().updateCard(c);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
