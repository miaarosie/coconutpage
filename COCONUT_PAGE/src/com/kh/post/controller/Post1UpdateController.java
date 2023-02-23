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
import com.kh.post.model.service.Post1Service;
import com.kh.post.model.vo.Post1;
import com.kh.post.model.vo.Post1File;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class Post1UpdateController
 */
@WebServlet("/wagleUpdate.po")
public class Post1UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post1UpdateController() {
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
		
			request.setCharacterEncoding("UTF-8");
			
			if(ServletFileUpload.isMultipartContent(request)) {
				
				int maxSize = 10 * 1024 * 1024;
				String savePath = request.getSession().getServletContext().getRealPath("/resources/post_upfiles/");
				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				int postNo = Integer.parseInt(multiRequest.getParameter("pno"));
				String postTitle = multiRequest.getParameter("title");
				String postContent = multiRequest.getParameter("content");
				
				Post1 p = new Post1();
				p.setPostNo(postNo);
				p.setPostTitle(postTitle);
				p.setPostContent(postContent);
				
				Post1File pf = null;
				if(multiRequest.getOriginalFileName("reUpfile") != null) {
					
					pf = new Post1File();
					
					pf.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
					pf.setChangeName(multiRequest.getFilesystemName("reUpfile"));
					
					pf.setFilePath("resources/post_upfiles/");
					
					if(multiRequest.getParameter("originFileNo") != null) {
						pf.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					} else {
						pf.setPostNo(postNo);
					}
				}
				
				 int result = new Post1Service().updatePost1(p, pf);
				
				 if(result > 0) {
					
					 if(multiRequest.getParameter("originFileName") != null 
							 && multiRequest.getOriginalFileName("reUpfile") != null) {
						 
						 new File(savePath + multiRequest.getParameter("originFileName")).delete();
					 }
					 
					 request.getSession().setAttribute("alertMsg", "게시글이 성공적으로 수정되었습니다.");
					 response.sendRedirect(request.getContextPath()+ "/wagleDetail.po?pno="+postNo);
				 
				 } else {
					
					 request.setAttribute("errorMsg", "게시글 수정 실패");
				 
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
