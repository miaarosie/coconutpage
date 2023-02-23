package com.kh.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.service.MemberService;
import com.kh.employee.model.vo.Employee;
import com.kh.employee.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class Userprofilecontroller
 */
@WebServlet("/profile.up")
public class Userprofilecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");

		if (emp == null) {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {

			request.setCharacterEncoding("UTF-8");

			if (ServletFileUpload.isMultipartContent(request)) {

				// 1. 전달된 파일에 대한 정보 먼저 지정 (전송파일 용량제한, 저장할 파일의 물리적인 경로)
				// 1_1. 용량제한
				int maxSize = 10 * 1024 * 1024;

				// 1_2. 저장할 파일의 물리적인 경로
				String savePath = request.getSession().getServletContext().getRealPath("resources/profile_upfiles/");

				// 2. 전달된 파일명 수정 작업 후 서버에 업로드 + MultipartRequest 타입으로 변환
				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());
				// 3. DB에 전달할 값 뽑기

				int empNo = Integer.parseInt((multiRequest.getParameter("empNo")));
				Member m = new Member();
				m.setEmpNo(empNo);

				int currentPage = Integer.parseInt(multiRequest.getParameter("currentPage")); // 검색조건
				String find = multiRequest.getParameter("find"); // 검색조건
				String option = multiRequest.getParameter("option"); // 키워드

				// 4. 첨부파일 키값
				// 파일 수정명
				if (multiRequest.getOriginalFileName("file1") != null) {
					m.setProfile(multiRequest.getFilesystemName("file1")); // 수정명 // 실제 서버에 업로드된 파일명

				}


				// 5.Service 단으로 요청 후 결과받기
				int result = new MemberService().insertThumbnailmMember(m);

				// 결과에 따른 응답페이지 지정
				if (result > 0) {

					request.setAttribute("member", m);
					request.getSession().setAttribute("alertMsg", "사진이 업로드 되었습니다.");

					// request.getRequestDispatcher(request.getContextPath() +
					// "/detailView.me?empNo=" +empNo ).forward(request, response);

					Employee loginEmp = (Employee) (request.getSession().getAttribute("loginEmp"));
					loginEmp.setProfile(multiRequest.getFilesystemName("file1"));
					request.getSession().setAttribute("loginEmp", loginEmp);
					response.sendRedirect(request.getContextPath() + "/detailView.me?empNo=" + empNo + "&currentPage="
							+ currentPage + "&find=" + find + "&option=" + option);

				} else {
					request.getSession().setAttribute("alertMsg", "사진 업로드를 실패하였습니다.");
					response.sendRedirect(request.getContextPath() + "/detailView.me?empNo=" + empNo + "&currentPage="
							+ currentPage + "&find=" + find + "&option=" + option);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
