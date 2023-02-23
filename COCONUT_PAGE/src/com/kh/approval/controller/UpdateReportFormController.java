package com.kh.approval.controller;

import java.io.File;
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

import com.kh.approval.model.service.TempApprovalService;
import com.kh.approval.model.vo.Approval;
import com.kh.approval.model.vo.Approver;
import com.kh.approval.model.vo.Referrer;
import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.oreilly.servlet.MultipartRequest;

/**
 * 
 */
@WebServlet("/update.re")
public class UpdateReportFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateReportFormController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//임시저장할 파일을 기안 상신
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg1", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			// post 방식 ,multipart/form -data
			request.setCharacterEncoding("UTF-8");
			if (ServletFileUpload.isMultipartContent(request)) {
				// 전송파일 용량

				int maxSize = 10 * 1024 * 1024;
				// 저장할 서버의 실 경로
				String savePath = request.getSession().getServletContext().getRealPath("/resources/approval_upfiles/");

				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				// 값 뽑기
				String aprId = multiRequest.getParameter("aprId");
				String aprTitle = multiRequest.getParameter("aprTitle");
				String aprContent = multiRequest.getParameter("aprContent");
				String empNo = multiRequest.getParameter("empNo");
				String comment = multiRequest.getParameter("comment");
				int oriFileChk = Integer.parseInt(multiRequest.getParameter("oriFileChk"));
				String beforeChangeFileName = multiRequest.getParameter("bfChangeFileName");
				String beforeOriFileName = multiRequest.getParameter("bfOriFileName");

				// 결재라인 모달에서 가져오는 값들
				String aprList = multiRequest.getParameter("aprList");
				String ccList = multiRequest.getParameter("ccList");

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
				Approval ap = new Approval();
				ap.setAprId(aprId);
				ap.setAprTitle(aprTitle);
				ap.setAprContent(aprContent);
				ap.setEmpNo(empNo);
				ap.setAprComment(comment);
				ap.setOriginName(beforeOriFileName);
				ap.setChangeName(beforeChangeFileName);

				// 첨부파일 값 뽑기
				if (multiRequest.getOriginalFileName("upfile") != null) {
					String originName = multiRequest.getOriginalFileName("upfile");
					String changeName = multiRequest.getFilesystemName("upfile");

					ap.setOriginName(originName);
					ap.setChangeName(changeName);

				} else {
					if (oriFileChk == 1) {
						ap.setOriginName(null); // 원본명
						ap.setChangeName(null); // 수정명
					}
				}

				int result = new TempApprovalService().updateReportForm(ap, apList, refList);

				if (result > 0) {
					
					// 새로운 파일이 있고 기존 파일이 있는 경우 기존파일을 삭제
					if (multiRequest.getOriginalFileName("upfile") != null && beforeChangeFileName != null) {
						new File(savePath + beforeChangeFileName).delete();
					}

					// 새로운 파일 없고 기존파일 있는데 지웠다.
					if (oriFileChk == 1) {
						new File(savePath + beforeChangeFileName).delete();
					}
					
					request.getSession().setAttribute("alertMsg", "보고서를 상신하였습니다.");
					request.setAttribute("a", ap);

					response.sendRedirect(request.getContextPath() + "/send.docs?currentPage=1");

				} else {
					
					// 파일 삭제
					if (multiRequest.getOriginalFileName("upfile") != null) {
						// 업데이트 실패했고 첨부파일이 있는경우 새로운 첨부파일 삭제
						new File(savePath + ap.getChangeName()).delete();
					}
					
					request.setAttribute("alertMsg", "임시 저장 실패");

					response.sendRedirect(request.getContextPath()+ "/temp.docs?currentPage=1");
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
