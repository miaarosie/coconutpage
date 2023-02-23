<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.post.model.vo.*"%>
    
<%
	Post2 p = (Post2)request.getAttribute("p");
	Post2File pf = (Post2File)request.getAttribute("pf");
%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>

	form {
    	border-radius: 7px;
        padding: 100px;
        width: 1200px;
        height: 800px;
        margin : auto;
        margin-top : 100px;
        background-color: rgb(244, 244, 244);
        padding-top : 50px;
    }

    .write_title {
        margin-top : 30px;
        border-bottom: 2px solid #d2d2d2;
    }

    .title_input {
        width: 725px;
        height: 40px;
        margin-right: 10px;
        border: none;
        font-size: 30px;
        line-height: 24px;
        background-color: transparent;
	}

    /* 글내용 */
    .content_area {
        width : 1000px;
        height : 400px;
        border-radius: 5px;
        margin-top: 40px;
        font-size : 20px;
        border : solid 2px  rgb(201, 201, 201);
    }

    /* 글내용 텍스트 */
    #contentText {
        background-color: transparent;
        margin : auto;
        width : 100%;
        height : 100%;
        border : none;
        
    }

   .insertFile {
        margin-top: 20px;
        

    }
    
    #write_post_addFile_list{
        position : relative;
        left : 7%;
    }
    #write_post_addFile_detail{
        margin : 10px;
    }
    #write_post_addFile_detail td{
        height : 30px;
    }
    .file_image{
        width : 30px;
    }
    .file_name{
        width : 700px;
        background-color: rgb(244, 244, 244);
    }
    .file_size{
        width : 60px;
    }

    #write_post_addFile > form{
        position : relative;
        left : 60px;
        line-height : 80px;
    }

    #write_post_addFile > form{
        font-size : 30px;
        color : rgb(91, 88, 88);
        line-height : 4;
    }
     
    
    /* 취소버튼 */     
    .up-btn1 {
		background-color: rgb(110, 76, 68);
		color: white;
		width: 90px;
    	height: 50px;
    	padding: 3px;
        border: solid 1px white;
        font-size: 15pt;
        border-radius: 10px;
        margin-left: 810px;
        cursor:pointer;
        }   

	/* 등록버튼 */
    .up-btn2 {
        background-color: rgb(110, 76, 68);
        color: white;
        width: 90px;
        height: 50px;
        padding: 3px;
        border: solid 1px white;
        font-size: 16pt;
        border-radius: 10px;
        cursor:pointer;
    }
    .up-btn1:hover{
        font-weight:600;
    }
    .up-btn2:hover{
        font-weight:600;
    }

       
	/* 모달창 */
	.modal-content {
        width : 600px;
        height : 400px;
        margin : auto;
        border-radius: 10px;
        border : none;
    }
	.modal-body {
    	height: 250px;
        width : 600px;
        font-size : 30px;
        font-weight: bold;
        text-align: center;
        padding-top: 140px;
    }
    /* 모달창 버튼 */
    .btn-btn-danger {
        width : 80px;
        height: 50px;
        font-size : 20px;
        border-radius: 5px;
        border : none;
        background-color: rgb(110, 76, 68); 
        color: white;
    }
            
</style>
</head>
<body>
	<div class="wrap">
		<%@ include file="../common/header.jsp" %>
		
		<div class="container">
			<%@ include file="../common/sidebar.jsp" %>
			
			<div class="main">
			
				<form id="post2Update-form" action="<%=contextPath %>/thingsUpdate.po" method="post" enctype="multipart/form-data">
							<input type="hidden" name="pno" value="<%= p.getPostNo() %>">
							
							<div class="write_form">
								<div class="write_title"> <!--제목입력-->
									<input type="text" name="title" autocomplete="off" required value="<%= p.getPostTitle() %>" class="title_input" style="font-weight: 400;">
								</div>
								
	                            <div class="content_area">
	                                <textarea id="contentText" name="content" style="resize: none;"><%=p.getPostContent() %></textarea>
	                            </div>
	
                                <div id="count">
                                    <span>0</span> / 1000
                               </div>
    
                                <script>
                                    $(function() {
                                        $('.content_area>textarea').keyup(function () {		
                                            $("#count>span").text($(this).val().length);  			
                                        });
                                    });
                                </script>

	                            <div class="insertFile">
	                            	<% if(pf != null) { %>
	                            		<%= pf.getOriginName() %>
	                                	<input type="hidden" name="originFileNo" value="<%= pf.getFileNo() %>">
	                                	<input type="hidden" name="originFileName" value="<%= pf.getChangeName() %>">
	                                <% } %>
	                                <input type="file" name="reUpfile" accept="image/*">
	                            </div>
	                           
								<div class="upload_btn">  
									<button type="reset" class="up-btn1" onclick="history.back()">취소</button>
									<button type="submit" class="up-btn2">수정</button>
								</div>      
	
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			
	<script>
    $(function() {
       $("#header_menu").html("<div><a href='<%=contextPath%>/wagleList.po?currentPage=1' >코코넛 와글와글</a></div>"+
                               "<div><a href='<%=contextPath%>/thingsList.po?currentPage=1' style='color:#20c997'>경조사</a></div>"+
                               "<div><a href='<%=contextPath%>/list.share?currentPage=1'>나눔해요</a></div>");
	});


 </script>


</body>
</html>