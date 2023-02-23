package com.kh.approval.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.approval.model.service.ApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReportTempInsetController
 */
@WebServlet("/report.ts")
public class ReportTempInsetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportTempInsetController() {
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

			request.setCharacterEncoding("UTF-8");
			// 2. 해당 요청이 multipart/form-data 형식인지 먼저 검사
			if (ServletFileUpload.isMultipartContent(request)) {

				int maxSize = 10 * 1024 * 1024;
				String savePath = request.getSession().getServletContext().getRealPath("/resources/approval_upfiles/");

				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				// 3. 전달값 뽑아서 가공하기
				String aprTitle = multiRequest.getParameter("aprTitle");
				String aprContent = multiRequest.getParameter("aprContent");
				String empNo = multiRequest.getParameter("empNo");

				Approval ap = new Approval();
				ap.setAprTitle(aprTitle);
				ap.setAprContent(aprContent);
				ap.setEmpNo(empNo);
				// 4. 첨부파일이 있다면 같이 보내기
				if (multiRequest.getOriginalFileName("upfile") != null) {
					String originName = multiRequest.getOriginalFileName("upfile");
					String changeName = multiRequest.getFilesystemName("upfile");
					ap.setOriginName(originName);
					ap.setChangeName(changeName);
				}

				int result = new ApprovalService().sendReportTemp(ap);

				if (result > 0) {
					request.getSession().setAttribute("alertMsg", "임시 저장 되었습니다.");
					request.setAttribute("ap", ap);
					response.sendRedirect(request.getContextPath() + "/temp.docs?currentPage=1");
				} else {
					request.getSession().setAttribute("alertMsg", "보고서 임시저장에 실패했습니다.");
					response.sendRedirect(request.getContextPath());
				}
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
