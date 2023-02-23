package com.kh.approval.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.approval.model.service.TempApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.DocSpent;
import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UpdateTempSaveExpenseController
 */
// 임시저장에서 임시저장
@WebServlet("/update.ts")
public class UpdateTempSaveExpenseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTempSaveExpenseController() {
		super();
		// TODO Auto-generated constructor stub
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

				// HttpServletRequest => MultipartRequest

				// 전송파일 용량
				int maxSize = 10 * 1024 * 1024;

				// 저장할 서버의 실 경로
				String savePath = request.getSession().getServletContext().getRealPath("/resources/approval_upfiles/");

				// 전달된 파일 수정 및 서버에 업로드
				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				// 값 뽑기
				String aprId = multiRequest.getParameter("aprId");
				String userId = multiRequest.getParameter("userId");
				String title = multiRequest.getParameter("title");
				String spentHow = multiRequest.getParameter("spentHow");
				int oriFileChk = Integer.parseInt(multiRequest.getParameter("oriFileChk"));
				String beforeChangeFileName = multiRequest.getParameter("bfChangeFileName");
				String beforeOriFileName = multiRequest.getParameter("bfOriFileName");

				// 원래 있던 지결 내역
				int currentList = 0;

				if (multiRequest.getParameter("currentList") != null) {
					currentList = Integer.parseInt(multiRequest.getParameter("currentList"));
				}

				// VO 가공
				Approval a = new Approval();
				a.setAprId(aprId);
				a.setEmpNo(userId);
				a.setAprTitle(title);
				a.setAprContent(spentHow);
				a.setOriginName(beforeOriFileName);
				a.setChangeName(beforeChangeFileName);

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

						dclist.add(new DocSpent(spentDate, corpName, corpNo, history, spentMoney, aprId));
					}
				}

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
				int result = new TempApprovalService().updateTempAppDoc(a, dclist, currentList);

				if (result > 0) {

					// 새로운 파일이 있고 기존 파일이 있는 경우 기존파일을 삭제
					if (multiRequest.getOriginalFileName("upfile") != null && beforeChangeFileName != null) {
						new File(savePath + beforeChangeFileName).delete();
					}

					// 새로운 파일 없고 기존파일 있는데 지웠다.
					if (oriFileChk == 1) {
						new File(savePath + beforeChangeFileName).delete();
					}

					request.getSession().setAttribute("alertMsg", "지출결의서 임시저장에 성공했습니다.");
					response.sendRedirect(request.getContextPath() + "/temp.docs?currentPage=1");
				} else {

					// 파일 삭제
					if (multiRequest.getOriginalFileName("upfile") != null) {
						// 업데이트 실패했고 첨부파일이 있는경우 새로운 첨부파일 삭제
						new File(savePath + a.getChangeName()).delete();
					}

					request.getSession().setAttribute("alertMsg", "지출결의서 임시저장에 실패했습니다.");
					response.sendRedirect(request.getContextPath() + "/temp.docs?currentPage=1");
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
