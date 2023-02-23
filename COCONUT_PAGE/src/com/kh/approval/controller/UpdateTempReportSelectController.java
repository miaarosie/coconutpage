package com.kh.approval.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.approval.model.service.TempApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UpdateTempReportSaveController
 */
@WebServlet("/update.tsre")
public class UpdateTempReportSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTempReportSelectController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg1", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			// 임시저장한 문서를 다시 임시저장할 경우
			// post 방식, multipart/form-data
			request.setCharacterEncoding("UTF-8");

			if (ServletFileUpload.isMultipartContent(request)) {
				int maxSize = 10 * 1024 * 1024;
				String savePath = request.getSession().getServletContext().getRealPath("/resources/approval_upfiles/");
				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				String aprId = multiRequest.getParameter("aprId");
				String userId = multiRequest.getParameter("userId");
				String title = multiRequest.getParameter("aprTitle");
				String aprContent = multiRequest.getParameter("aprContent");
				int oriFileChk = Integer.parseInt(multiRequest.getParameter("oriFileChk"));
				String beforeChangeFileName = multiRequest.getParameter("bfChangeFileName");
				String beforeOriFileName = multiRequest.getParameter("bfOriFileName");

				// VO 가공
				Approval a = new Approval();
				a.setAprId(aprId);
				a.setEmpNo(userId);
				a.setAprTitle(title);
				a.setAprContent(aprContent);
				a.setOriginName(beforeOriFileName);
				a.setChangeName(beforeChangeFileName);

				// 첨부파일 값 뽑기
				if (multiRequest.getOriginalFileName("upfile") != null) {
					String originName = multiRequest.getOriginalFileName("upfile");
					String changeName = multiRequest.getFilesystemName("upfile");

					a.setOriginName(originName);
					a.setChangeName(changeName);
				} else {
					if (oriFileChk == 1) {
						a.setOriginName(null); // 원본명
						a.setChangeName(null); // 수정명
					}
				}

				// 서비스 요청
				int result = new TempApprovalService().updateTempReport(a);

				if (result > 0) {

					// 새로운 파일이 있고 기존 파일이 있는 경우 기존파일을 삭제
					if (multiRequest.getOriginalFileName("upfile") != null && beforeChangeFileName != null) {
						new File(savePath + beforeChangeFileName).delete();
					}

					// 새로운 파일 없고 기존파일 있는데 지웠다.
					if (oriFileChk == 1) {
						new File(savePath + beforeChangeFileName).delete();
					}

					request.getSession().setAttribute("alertMsg", "보고서 임시저장에 성공했습니다.");
					response.sendRedirect(request.getContextPath() + "/temp.docs?currentPage=1");
				} else {
					// 파일 삭제
					if (multiRequest.getOriginalFileName("upfile") != null) {
						// 업데이트 실패했고 첨부파일이 있는경우 새로운 첨부파일 삭제
						new File(savePath + a.getChangeName()).delete();
					}

					request.getSession().setAttribute("alertMsg", "보고서  임시저장에 실패했습니다.");
					response.sendRedirect(request.getContextPath() + "/temp.docs?currentPage=1");
				}
			}

		}
	}

	/*
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
