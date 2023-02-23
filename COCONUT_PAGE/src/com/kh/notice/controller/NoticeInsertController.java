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
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.noti")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeInsertController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		if(ServletFileUpload.isMultipartContent(request)) {
			//전송되는 파일을 처리할 작업 내용
			// 전송파일의 용량 제한
			// 정수타입 => int, byte 단위의 값을 기술해야함
			// => 10Mbyte 로 제한
			int maxSize = 10*1024*1024; 
			// 전달된 파일을 저장할 서버의 실 경로 지정
			
			// application 내장객체 얻어내기 : request.getSession().getServletContext()
			String savePath = request.getSession().getServletContext().getRealPath("/resources/notice_upfiles/");
			// => 맨처음 / 가 의미하는것 : WebContent 
			// => 마지막 / 가 의미하는것 : 해당폴더 내부 
			
			// 전달된 파일명 수정 및 서버에 업로드 작업
			/*
			 * HttpServletRequest 타입을 MultipartRequest 타입으로 변환
			 * MultipartRequest xxx = new MultipartRequest(request객체, 저장할 폴더의 경로, 용량제한, 인코딩값, 파일명을 수정시켜주는객체);
			 * => 위의 매개변수 생성자로 생성
			 * => 위 구문 한줄만으로 넘어온 첨부파일들이 해당폴더에 업로드됨
			 * => MultipartRequest 타입은 cos.jar 에서 제공하는 클래스
			 * cos.jar 라이브러리
			 * com.oreilly.servlet의 약자
			 * 다운로드 링크 : http://www.servlets.com
			 * =>파일업로드 기능을 구현하려면 필수적으로 필요함!!
			 * 
			 * 사용자가 올린 파일은 보통 파일명을 수정해서 저장하게끔 되어있음
			 * 
			 * => 같은 파일명이 있을 경우 덮어씌워질수도 있고, 한글/특수문자/띄어쓰기가 포함된 파일명일 경우
			 * 	  서버에 따라 문제가 발생할 가능성이 있음
			 * => 기본적으로 파일명 수정 작업을 해주는 객체를 cos.jar에서 제공해줌
			 * 	  DefaultFileRenamePolicy 객체
			 *    (내부적으로 rename() 메소드가 호출되면서 파일명이 수정됨, 
			 *    기본적으로 동일한 파일명이 이미 존재할 경우 뒤에 카운팅된 숫자를 붙여서 업로드됨)
			 *    예) aaa.jpg, aaa1.jpg, aaa2.jpg, ...
			 *    
			 * => 하지만 우리 입맛대로 절대 파일명이 안겹치게끔 rename 해볼것임!!
			 */	
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// DB에 기록할 데이터를 뽑아서 vo 객체에 담기
			
			int boardNo = Integer.parseInt(multiRequest.getParameter("boardNo"));
			int empNo = Integer.parseInt(multiRequest.getParameter("empNo"));
			String noticeTitle = multiRequest.getParameter("noticeTitle");
			String noticeContent = multiRequest.getParameter("noticeContent");
			
			Notice n = new Notice();
			n.setNoticeType(boardNo);
			n.setEmpNo(empNo);
			n.setNoticeTitle(noticeTitle);
			n.setNoticeContent(noticeContent);
			
			//첨부파일이 있는지 검사
			if(multiRequest.getOriginalFileName("noticeFile") != null) {
				//첨부파일 있을 경우 해당 첨부파일 원본파일명 리턴 없다면 null 반환
				n.setOriginFile(multiRequest.getOriginalFileName("noticeFile")); // 원본명
				n.setChangeFile(multiRequest.getFilesystemName("noticeFile")); // 수정명
			}
			
			HttpSession session = request.getSession();
			
			int result = new NoticeService().insertNotice(n);
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath()+"/noList.board?boardNo="+boardNo+"&currentPage=1");
			}else {
				if(multiRequest.getOriginalFileName("noticeFile") != null) {
					new File(savePath + n.getChangeFile()).delete();
				}
				session.setAttribute("alertMsg", "공지사항 등록 실패");
				response.sendRedirect(request.getContextPath()+"/noList.board?boardNo="+boardNo+"&currentPage=1");
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
