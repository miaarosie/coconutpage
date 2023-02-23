package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.vo.Employee;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/det.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeDetailController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginEmp") == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			String keyword = request.getParameter("keyword");
			int currentPage = Integer.parseInt(request.getParameter("cup"));
			
			if(keyword == null) {
				request.setAttribute("keyword", "");
			}else {
				request.setAttribute("keyword", keyword);
			}
			
			
			int noticeNo = Integer.parseInt(request.getParameter("nno"));
			// 조회수 증가, 게시글 조회 서비스 2번 보내야함
			int result = new NoticeService().increaseViewCount(noticeNo);
			if (result > 0) {
				Notice n = new NoticeService().selectNoticeByNo(noticeNo);
				request.setAttribute("n", n);				
				request.setAttribute("currentPage", currentPage);;
				request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMsg", "공지사항 상세조회 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
