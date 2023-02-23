<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.post.model.vo.*" %>

<%
	ArrayList<ShareAttachment> list = (ArrayList<ShareAttachment>)request.getAttribute("list");
	Share s = (Share)request.getAttribute("s");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <title>COCONUT PAGE</title>

   
    <style>
   
        #write_post{
            margin : 5% 10% 3% 15%;
            width : 65%;
            height : 85%;
            float : left;
            background-color: rgb(244, 244, 244);
            margin-top:20px;
        }


/*main틀*/
        #write_post > div {
            border-width : 5px;
            width : 100%;
        }
        #write_post_title{
            height : 15%;
        }
        .write_post_addFile{
            height : 10%;
        }
        #write_post_content{
            height : 60%;
        }


/*title*/
        #write_post_title_input{
            width : 90%;
            height : 50px;
            margin : 90px 40px 0px 50px;
            border : none;
            border : 1px solid black;
            background-color : rgb(244, 244, 244);
            border : none;
            border-bottom : 1px solid black;
            font-size : 40px;
        }

/*content*/
        #post_content_input{
            position : relative;
            left : 60px;
            width : 90%;
            height : 370px;
            
            background-color : rgb(244, 244, 244);
            border : 3px solid rgb(0,0,0,0.1);

            font-size : 23px;
        }

/*addFile*/
        .write_post_addFile {
            margin-top : 10px; 
            margin-bottom : 10px;
            float:left;
        }
        .write_post_addFile :hover {
            cursor: pointer;
        }
        .write_post_addFile_list{
            position : relative;
            left : 7%;
        }
        .write_post_addFile_detail{
            
            margin : 10px;
        }
        .write_post_addFile_detail td{
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

        .write_post_addFile > form{
            position : relative;
            left : 60px;
            line-height : 80px;
        }

        .write_post_addFile > form{
            font-size : 30px;
            color : rgb(91, 88, 88);

            line-height : 4;
        }

/*cancel_enroll_button*/
        #write_post_btn > form > button{
            width : 120px;
            height : 60px;
            margin : 20px;
            position : relative;
            left : 68%;
            
            border : none;
            background-color : rgb(110, 76, 68);
            color : rgb(253, 255, 240);

            font-size : 20px;
            border-radius: 5px;
        }

        button:hover{
            cursor:pointer;
        }


        #header_menu {
            font-weight: 400;
            font-size: 30px;
            width: 300px;
            height: 100%;
            padding-top: 25px;
            text-align: center;
            padding-right: 100px;
            text-decoration: none;
        }

        #header_menu a {
            color:   rgb(110, 76, 68);
            text-decoration: none
        }

        .main {
            margin: auto;
            margin-bottom : 100px;
        }


</style>

</head>
<body>
    <div class="wrap">
        
        <%@ include file = "../common/header.jsp" %>

        <div class="container">

            <%@ include file = "../common/sidebar.jsp"%>

            <div class="main">
                <form action="<%=contextPath %>/update.share" method="post" enctype="multipart/form-data">
                
                <input type="hidden" name="pno" value="<%=s.getPostNo()%>">
                    <div id="write_post">
                        <div id="write_post_title">
                        
                            <input type="text" id="write_post_title_input" name="shareTitle" maxlength="50" value="<%=s.getPostTitle() %>" required>
                        
                        </div>
                                                
                        <div id="write_post_content" >
                        
                                <textarea name="shareContent" id="post_content_input" maxlength="1000"  style="resize:none;"><%= s.getPostContent() %></textarea>
                        
                        </div>

                        <div>
                            <table align="center">
                                <tr> <!-- 이미지 미리보기 영역 -->
                                    <th style="text-align:center;">썸네일</th>
                                    <div align="center">
                                        	<input type="hidden" name="originFileNo1" value="<%=list.get(0).getFileNo()%>">
                                        	<input type="hidden" name="originFileName1" value="<%=list.get(0).getChangeName()%>">
                                            <td width="450px" height="360px" colspan="3"><img id="titleImg" src="<%=contextPath%>/<%=list.get(0).getFilePath() + list.get(0).getChangeName()%>" width="450px" height="360px"></td>
                                        
                                    </div>
                                </tr>
                                <%-- 
                                <tr>
                                    <td width="450px" height="360px" colspan="3"><img src="<%=contextPath%>/<%=list.get(0).getFilePath() + list.get(0).getChangeName()%>" width="450px" height="360px"></td>
                                    
                                </tr>
                                <tr>
                                    <% for (int i = 1; i < list.size(); i++) { %>
                                    	<td width="150px" height="120px"><img src="<%=contextPath%>/<%=list.get(i).getFilePath() + list.get(i).getChangeName()%>" width="150px" height="120px"></td>
                                    <% } %>
                                </tr>
                                 --%>
                                
                                <tr>
                                    <th>상세이미지</th>
                                    
                                    <% if(list.size()>1) { %>
	                                    <% for (int i = 1; i < list.size(); i++) { %>
	                                    	<input type="hidden" name="originFileNo<%=i+1%>" value="<%=list.get(i).getFileNo()%>">
	                                    	<input type="hidden" name="originFileName<%=i+1%>" value="<%=list.get(i).getChangeName()%>">
	                                    	<td width="150px" height="120px"><img id="contentImg<%=i %>" src="<%=contextPath%>/<%=list.get(i).getFilePath() + list.get(i).getChangeName()%>" width="150px" height="120px"></td>
	                                    <% } %>
	                                    <% int space = 4-list.size(); %>
	                                    <% int size = list.size(); %>
	                                    <% for(int i=space; i >0; i--) { %>
	                                		<td><img id="contentImg<%=size++ %>" width="150px" height="120px"></td>    	
	                                    <% } %>
	                                <% } else { %>
	                                    <td><img id="contentImg1" width="150px" height="120px"></td>
	                                    <td><img id="contentImg2" width="150px" height="120px"></td>
	                                    <td><img id="contentImg3" width="150px" height="120px"></td>
                                    <% } %>
                                </tr>
                            </table>
                        </div>
                        <div id="file-area">
                            <input type="file" id="file1" name="newFile1" onchange="loadImg(this, 1);">
                            <input type="file" id="file2" name="newFile2" onchange="loadImg(this, 2);">
                            <input type="file" id="file3" name="newFile3" onchange="loadImg(this, 3);">
                            <input type="file" id="file4" name="newFile4" onchange="loadImg(this, 4);">
                        </div>

                        <script>
                            $(function () {
                                $("#file-area").hide();

                                $("#titleImg").click(function() {
                                    $("#file1").click();
                                });

                                $("#contentImg1").click(function() {
                                    $("#file2").click();
                                });

                                $("#contentImg2").click(function() {
                                    $("#file3").click();
                                });

                                $("#contentImg3").click(function() {
                                    $("#file4").click();
                                });
                            });
                                function loadImg(inputFile, num) {

                                    if(inputFile.files.length == 1) {

                                        var reader = new FileReader();

                                        reader.readAsDataURL(inputFile.files[0]);

                                        reader.onload = function(e) {

                                            switch(num) {
                                                case 1: $("#titleImg").attr("src", e.target.result); break;
                                                case 2: $("#contentImg1").attr("src", e.target.result); break;
                                                case 3: $("#contentImg2").attr("src", e.target.result); break;
                                                case 4: $("#contentImg3").attr("src", e.target.result); break;
                                            }
                                        };
                                    } else {
                                        
                                        switch(num) {
                                                case 1: $("#titleImg").attr("src", null); break;
                                                case 2: $("#contentImg1").attr("src", null); break;
                                                case 3: $("#contentImg2").attr("src", null); break;
                                                case 4: $("#contentImg3").attr("src", null); break;
                                            }
                                    }

                                }
                            
                        </script>

                        <br><br><br>
                        <div id="write_post_btn" align="center">
                        
                            <button type="submit" class="btn btn-primary btn-lg">수정</button>
                            <button type="button" class="btn btn-warning btn-lg" onClick="history.back()">취소</button>
                        </div>  
                        

                </form>
                    

                </div>
            </div>
        </div>
    </div>
    
	<script>
    $(function() {
       $("#header_menu").html("<div><a href='<%=contextPath%>/wagleList.po?currentPage=1'>코코넛 와글와글</a></div>"+
                               "<div><a href='<%=contextPath%>/thingsList.po?currentPage=1' >경조사</a></div>"+
                               "<div><a href='<%=contextPath%>/list.share?currentPage=1'  style='color:#20c997'>나눔해요</a></div>");
	});


 </script>

    
</body>
</html>