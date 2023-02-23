package com.kh.post.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.MyFileRenamePolicy;
import com.kh.employee.model.vo.Employee;
import com.kh.post.model.service.ShareService;
import com.kh.post.model.vo.Share;
import com.kh.post.model.vo.ShareAttachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ShareEnrollController
 */
@WebServlet("/insert.share")
public class ShareEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareEnrollController() {
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
			
			int maxSize = 10 * 1024* 1024;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/share_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			Share s = new Share();
			
			s.setEmp_no(multiRequest.getParameter("empNo"));
			s.setPostTitle(multiRequest.getParameter("shareTitle"));
			s.setPostContent(multiRequest.getParameter("shareContent"));
			
			
			ArrayList<ShareAttachment> list = new ArrayList<>();
			
			for(int i =1; i<=4; i++) {
				
				String key = "file"+i;
				
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
					
					list.add(at);
				}
			}
			
			int result = new ShareService().insertSharePost(s, list);
			
			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "성공적으로 업로드 되었습니다.");
				response.sendRedirect(request.getContextPath()+ "/list.share?currentPage=1");
			} else {
				
				
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
