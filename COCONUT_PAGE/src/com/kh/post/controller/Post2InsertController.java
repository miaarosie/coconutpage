package com.kh.post.controller;

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
import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.Post2Service;
import com.kh.post.model.vo.Post2;
import com.kh.post.model.vo.Post2File;
import com.oreilly.servlet.MultipartRequest;


/**
 * Servlet implementation class Post2InsertController
 */
@WebServlet("/thingsInsert.po")
public class Post2InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post2InsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginEmp");
		if (emp == null) {
			session.setAttribute("alertMsg1", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
		
			// post테이블에 insert
			// 첨부파일이 있을경우 post_file 테이블에  insert
			// 뽑아야할 값 : title : 제목 / upfile : 첨부파일 / content : 내용
	 
			
			request.setCharacterEncoding("UTF-8");
			
			if(ServletFileUpload.isMultipartContent(request)) {
				int maxSize = 10 * 1024* 1024;
				
				// 첨부파일 저장할  경로지정 
				String savePath = request.getSession().getServletContext().getRealPath("/resources/post_upfiles/");
				
				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				// 뽑아야할 값 제목/내용
				String postTitle = multiRequest.getParameter("title");
				String postContent = multiRequest.getParameter("content");
				String postWriter = multiRequest.getParameter("empNo"); 
	
				Post2 p = new Post2();
				
				p.setPostTitle(postTitle);
				p.setPostContent(postContent);
				p.setEmpNo(postWriter); 
				
				Post2File pf = null;
				
				if(multiRequest.getOriginalFileName("upfile") != null) {
					pf = new Post2File();
					
					pf.setOriginName(multiRequest.getOriginalFileName("upfile"));
					pf.setChangeName(multiRequest.getFilesystemName("upfile"));
					pf.setFilePath("resources/post_upfiles/");
				}
				 int result = new Post2Service().insertPost2(p, pf);
				 
				 if(result>0) {
					 request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
					 response.sendRedirect(request.getContextPath()+"/thingsList.po?currentPage=1");
				 } else {
					 if(pf != null) {
						 new File(savePath + pf.getChangeName()).delete();
					 }
					 request.setAttribute("errorMsg", "게시글 작성 실패");
				 }
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
