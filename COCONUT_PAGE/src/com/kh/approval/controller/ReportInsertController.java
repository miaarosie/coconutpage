package com.kh.approval.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.approval.model.service.ApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.Approver;
import com.kh.approval.model.vo.Referrer;
import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReportSendingController
 */
@WebServlet("/report.ex")
public class ReportInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg1", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			// 1. 인코딩 설정
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

				// 결재라인 모달에서 가져오는 값들
				String aprList = multiRequest.getParameter("aprList");
				String ccList = multiRequest.getParameter("ccList");
				String comment = multiRequest.getParameter("comment");

				// 결재자 리스트 객체 배열 만들기
				ArrayList<Approver> apList = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(aprList, " ");
				int j = 1;
				while (st.hasMoreTokens()) {
					apList.add(new Approver(Integer.parseInt(st.nextToken()), j));
					j++;
				}
				// 참조자 리스트 객체배열 만들기
				ArrayList<Referrer> refList = new ArrayList<>();

				st = new StringTokenizer(ccList, " ");

				while (st.hasMoreTokens()) {
					refList.add(new Referrer(Integer.parseInt(st.nextToken())));
				}

				Approval ap = new Approval();
				ap.setAprTitle(aprTitle);
				ap.setAprContent(aprContent);
				ap.setEmpNo(empNo);
				ap.setAprComment(comment);

				// 4. 첨부파일이 있다면 같이 보내기
				if (multiRequest.getOriginalFileName("upfile") != null) {
					String originName = multiRequest.getOriginalFileName("upfile");
					String changeName = multiRequest.getFilesystemName("upfile");

					ap.setOriginName(originName);
					ap.setChangeName(changeName);

				}
				int result = new ApprovalService().sendReport(ap, apList, refList);

				if (result > 0) {
					request.getSession().setAttribute("alertMsg", "결재 상신 되었습니다.");
					request.setAttribute("ap", ap);

					response.sendRedirect(request.getContextPath() + "/send.docs?currentPage=1");

				} else {
					request.setAttribute("errorMsg", "결재 상신 실패");

					request.getRequestDispatcher(request.getContextPath() + "/send.docs").forward(request, response);

				}

			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
