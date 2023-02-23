package com.kh.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeUpdateController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if (ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 10 * 1024 * 1024;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/notice_upfiles/");

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			int noticeNo = Integer.parseInt(multiRequest.getParameter("nno"));
			String noticeTitle = multiRequest.getParameter("noticeTitle");
			String noticeContent = multiRequest.getParameter("noticeContent");
			int oriFileChk = Integer.parseInt(multiRequest.getParameter("oriFileChk"));

			Notice n = new NoticeService().selectNoticeByNo(noticeNo);

			String beforeChangeFileName = n.getChangeFile();

			n.setNoticeTitle(noticeTitle);
			n.setNoticeContent(noticeContent);

			// 새로운 첨부파일이 있는지 검사
			if (multiRequest.getOriginalFileName("noticeFile") != null) {
				n.setOriginFile(multiRequest.getOriginalFileName("noticeFile")); // 원본명
				n.setChangeFile(multiRequest.getFilesystemName("noticeFile")); // 수정명
			} else {
				// 새로운 첨부파일 없고 기존파일이 있거나 없다.
				// 1. 기존파일 있으면 notice 변경할 필요 없다. 그대로 업데이트 될 예정
				// 2. 기존 파일 없으면 파일 이름 null이기 때문에 그대로 둔다.
				// null일 경우 업데이트가 제대로 되면 else 구문 삭제

				// 새로운 파일 없고 기존파일 있는데 지웠다.
				if (oriFileChk == 1) {
					n.setOriginFile(null); // 원본명
					n.setChangeFile(null); // 수정명
				}
			}

			HttpSession session = request.getSession();
			int result = new NoticeService().updateNotice(n);

			if (result > 0) {
				// 새로운 파일이 있고 기존 파일이 있는 경우 기존파일을 삭제
				if (multiRequest.getOriginalFileName("noticeFile") != null && beforeChangeFileName != null) {
					new File(savePath + beforeChangeFileName).delete();
				}

				// 새로운 파일 없고 기존파일 있는데 지웠다.
				if (oriFileChk == 1) {
					new File(savePath + beforeChangeFileName).delete();
				}
				session.setAttribute("alertMsg", "공지사항을 업데이트했습니다.");
				response.sendRedirect(
						request.getContextPath() + "/noList.board?boardNo=" + n.getNoticeType() + "&currentPage=1");
			} else {
				if (multiRequest.getOriginalFileName("noticeFile") != null) {
					// 업데이트 실패했고 첨부파일이 있는경우 새로운 첨부파일 삭제
					new File(savePath + n.getChangeFile()).delete();
				}
				session.setAttribute("alertMsg", "공지사항 등록 실패");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
