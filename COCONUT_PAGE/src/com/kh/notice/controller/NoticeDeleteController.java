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
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/del.no")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeDeleteController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		Notice n = new NoticeService().selectNoticeByNo(noticeNo);

		HttpSession session = request.getSession();

		if (((Employee) session.getAttribute("loginEmp")).getEmpNo() != n.getEmpNo()) {
			session.setAttribute("alertMsg", "게시글 작성자만 삭제할 수 있습니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			int result = new NoticeService().deleteNotice(noticeNo);

			if (result > 0) {
				session.setAttribute("alertMsg", "공지사항을 삭제하였습니다.");
				response.sendRedirect(
						request.getContextPath() + "/noList.board?boardNo=" + n.getNoticeType() + "&currentPage=1");
			} else {
				session.setAttribute("alertMsg", "공지사항 삭제하지 못했습니다.");
				response.sendRedirect(
						request.getContextPath() + "/noList.board?boardNo=" + n.getNoticeType() + "&currentPage=1");
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
