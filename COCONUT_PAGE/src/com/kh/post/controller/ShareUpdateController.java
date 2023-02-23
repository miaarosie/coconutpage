package com.kh.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.ShareService;
import com.kh.post.model.vo.Share;
import com.kh.post.model.vo.ShareAttachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ShareUpdateController
 */
@WebServlet("/update.share")
public class ShareUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareUpdateController() {
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
				
				String savePath = request.getSession().getServletContext().getRealPath("/resources/share_upfiles/");
				
				MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				int postNo = Integer.parseInt(multiRequest.getParameter("pno"));
				String postTitle = multiRequest.getParameter("shareTitle");
				String postContent = multiRequest.getParameter("shareContent");
				
				ArrayList<ShareAttachment> insertList = new ArrayList<>();
				ArrayList<ShareAttachment> updateList = new ArrayList<>();
				
				Share s = new Share();
				
				s.setPostNo(postNo);
				s.setPostTitle(postTitle);
				s.setPostContent(postContent);
				
				for(int i =1; i<=4; i++) {
					
					String key = "newFile"+i;
					
					if(multiRequest.getOriginalFileName(key) != null) {
						
						ShareAttachment at = new ShareAttachment();
						at.setOriginName(multiRequest.getOriginalFileName(key));
						at.setChangeName(multiRequest.getFilesystemName(key));
						at.setFilePath("resources/share_upfiles/");
						
						if(i == 1) { 
							at.setFileLevel(1);
							
						}else {
							at.setFileLevel(2);
						}
						
						if(multiRequest.getParameter("originFileNo"+i) != null) {
							
							at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo"+i)));
								
							updateList.add(at);
							
						} else {
							
							at.setPostNo(postNo);
							
							
							insertList.add(at);
						}
					}
				}
				
				int result = new ShareService().updateSharePost(s, updateList, insertList);
				
				
				if(result>0) {
				
					for(int i = 1; i<=4; i++) {
						
						if(multiRequest.getParameter("originFileName"+i) != null
								&& multiRequest.getOriginalFileName("newFile"+i) != null) {
									
									new File(savePath + multiRequest.getParameter("originFileName"+i)).delete();
								}
					}	
						request.getSession().setAttribute("alertMsg", "게시글이 성공적으로 수정되었습니다.");
						response.sendRedirect(request.getContextPath() + "/detail.share?pno="+s.getPostNo());
					
				} else {
					
					
					
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
