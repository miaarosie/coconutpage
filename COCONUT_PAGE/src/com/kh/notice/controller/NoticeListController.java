package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/noList.board")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeListController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시판 종류 (공지인지 자료실인지)
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 검색어
		String keyword = request.getParameter("keyword");
		// 전체 글 개수 total
		int listCount = 0;
		
		if (keyword == null || keyword.isEmpty()) {
			listCount = new NoticeService().totalNotice(boardNo);
		} else {
			listCount = new NoticeService().totalSearchNotice(boardNo, keyword);
			request.setAttribute("keyword", keyword);
		}
				
		// 현재 페이지 번호 pageNo
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 한페이지에 게시물 개수 pageSize
		int boardLimit = 10; 		
		// 전체 페이지 개수 계산 pageCount
		int maxPage = listCount / boardLimit + (listCount % boardLimit == 0 ? 0 : 1);
		// 한 페이지에 보여줄 페이지 블럭 pageBlock
		int pageLimit = 10;
		// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
		int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		// 한 페이지에 보여줄 페이지 블럭 끝 번호 계산
		int endPage = startPage + pageLimit - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// 검색어
		ArrayList<Notice> nList = null;
		if (keyword == null || keyword.isEmpty()) {
			nList = new NoticeService().selectNoticeList(boardNo, pi);
		} else {
			nList = new NoticeService().searchNoticeList(boardNo, pi, keyword);
		}
		pi.setListCount(listCount);
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("pi", pi);
		request.setAttribute("noticeList", nList);
		request.getRequestDispatcher("views/notice/noticeList.jsp").forward(request, response);
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
