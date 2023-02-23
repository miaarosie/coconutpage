package com.kh.approval.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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
import com.kh.approval.model.vo.DocSpent;
import com.kh.approval.model.vo.Referrer;
import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ExpenseSpentFormController
 */
@WebServlet("/spentForm.ex")
public class ExpenseSpentFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpenseSpentFormController() {
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

			// post 방식
			// multipart/form-data 방식이므로 MultipartRequest로 변환해서 뽑기

			request.setCharacterEncoding("UTF-8");

			// multipart/form-data 방식인지 먼저 검사
			if (ServletFileUpload.isMultipartContent(request)) {

				// HttpServletRequest => MultipartRequest
				// request 객체, 파일의 경로값, 파일의 용량제한, 인코딩형식, 파일명수정 객체

				// 전송파일 용량
				int maxSize = 10 * 1024 * 1024;

				// 저장할 서버의 실 경로
				String savePath = request.getSession().getServletContext().getRealPath("/resources/approval_upfiles/");

				// 전달된 파일 수정 및 서버에 업로드

				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				// 값 뽑기
				String userId = multiRequest.getParameter("userId"); // 신청자
				String title = multiRequest.getParameter("title"); // 제목
				String spentHow = multiRequest.getParameter("spentHow"); // 비고

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

				// VO 가공
				Approval a = new Approval();
				a.setEmpNo(userId);
				a.setAprTitle(title);
				a.setAprContent(spentHow);
				a.setAprComment(comment);

				// 지출결의서 내역
				ArrayList<DocSpent> dclist = new ArrayList<DocSpent>();

				for (int i = 1; i < 5; i++) {
					if (multiRequest.getParameter("spentDate" + i) != null) {

						String sdate = multiRequest.getParameter("spentDate" + i); // 사용일자
						Date spentDate = Date.valueOf(sdate);

						String history = multiRequest.getParameter("history" + i); // 지출구분
						String corpName = multiRequest.getParameter("corpName" + i); // 사업자명
						String corpNo = multiRequest.getParameter("corpNo" + i); // 사업자번호
						int spentMoney = Integer.parseInt(multiRequest.getParameter("spentMoney" + i)); // 금액

						dclist.add(new DocSpent(spentDate, corpName, corpNo, history, spentMoney));
					}
				}

				// 첨부파일 값 뽑기
				if (multiRequest.getOriginalFileName("upfile") != null) {

					String originName = multiRequest.getOriginalFileName("upfile");
					String changeName = multiRequest.getFilesystemName("upfile");

					a.setOriginName(originName);
					a.setChangeName(changeName);
				}

				// 서비스 요청
				int result = new ApprovalService().insertAppDoc(a, dclist, apList, refList);

				if (result > 0) { // 성공시 : 성공 알림, /send.docs 로 url 요청

					request.getSession().setAttribute("alertMsg", "지출결의서 상신에 성공했습니다.");
					response.sendRedirect(request.getContextPath() + "/send.docs?currentPage=1");

				} else { // 실패시 : 에러 알림, 메인으로 요청 (임시)

					// 파일 삭제
					if (a.getOriginName() != null) {
						new File(savePath + a.getChangeName()).delete();
					}

					request.getSession().setAttribute("alertMsg", "지출결의서 상신에 실패했습니다.");
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
