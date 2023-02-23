<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.kh.post.model.vo.Share, java.util.ArrayList, com.kh.common.model.vo.PageInfo" %>

<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Share> list = (ArrayList<Share>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	int listCount = pi.getListCount();
	int boardLimit = pi.getBoardLimit();
	
%>    
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>COCONUT PAGE</title>

    <style>
      


        /*썸네일*/
    
 		.container_1 {
 			width : 1820px;
 		}
        .write_button {
            width: 130px;
            height: 60px;
            font-size: 25px;
            color: white;
            background-Color:rgb(110, 76, 68);
            border: 2px solid rgb(110, 76, 68);
            border-radius: 5px;
            position:relative;
            left: 86%;
            top: 6%;            
            text-decoration : none;

        }
        .write_button:hover {
            text-decoration: none;
            color : white;
        }

         .thumb_space {
            background-color: lightgray;
            width: 1300px;
            position:relative;
            left: 10%;
            top:8%;
            padding-top: 10px;
            padding-bottom: 10px;
            border: 3px solid rgb(191, 180, 177);
            border-radius: 10px;
        }
    
        .thumbnail_img {
            width: 240px;
            height: 240px;
            
        }
        
        ul {
         	text-align:center; 
        }

        ul li {
            list-style: none;
            display:inline-block;
            text-align: center;
        }

        .thumbnail_name {
            text-decoration: none;
            color: black;
            text-align: center;
            display: inline;
            
            padding-top:10px;
            font-weight: 900;
        }

        
        .thumb_space_1 {
            padding-top: 10px;
        }

        .thumbnail {
            margin-top: 20px;
            margin-bottom: 20px;
            margin-right : 20px;
        }

        .find_btn_img {
            width: 30px;
            position: relative;
            display: inline;
            
        }

        .find_btn {
            width: 150px;
            height: 40px;
            background-color: rgb(110, 76, 68);
            position: relative;
            display: inline;
            top:115px;
            left:29%;
            border-radius: 10px;
            border: 1px solid rgb(192, 189, 189);
            vertical-align: 0.5px;
            margin-left : 15px;
        }

        .find_btn:hover {
            cursor: pointer;
        }

        .find_text {
            width: 600px;
            height: 50px;
            position: relative;
            display: inline;
            top:115px;
            left:29%;
            /*vertical-align: 0px;*/
            border-radius: 10px;
            border: 1px solid rgb(192, 189, 189);
        }
        .find_option {
            width : 100px;
            position : relative;
            display: inline;
            top : 115px;
            left:60%;
            border: 1px solid rgb(192, 189, 189);
        }

       

        .page-link {
            color: black;
            background-color: rgb(192, 189, 189);

        }

        
        .thumb_space_1 ul li a{
            color : rgb(110, 76, 68);
            text-decoration: none;
        }
        
      	.pagination {
      		display : block!important;
      	}
      	
      	.pagination button {
			color: rgb(110, 76, 68);
			cursor: pointer;
		}
		
		.pagination button:hover {
		  color: #3C2317;
		  background-color: #fafafa; 
		  border-color: #ccc;
		}
		
		
		#active-page {
			background-color: rgb(110, 76, 68);
			color: white;
		}
		
		.thumbnail:hover {
    
    		font-weight : 600;
    		
    	}
    	
    	
    	.thumbnail img {
 			 transition: all 0.2s linear;
		}
    	
  		.thumbnail:hover img {
		    transform: scale(1.2);
		    
		}
		
		.thumbnail_img {
			overflow: hidden;
		}
		
		.thumbnail_non {
			margin-top: 20px;
            margin-bottom: 20px;
            margin-right : 20px;
		}
		
		

    </style>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">

    <div class="wrap">
      
        <%@ include file="../common/header.jsp" %>


        <div class="container">

            <!-- 고정 사이드바 -->
            <%@ include file="../common/sidebar.jsp" %>


            <!-- 실제 content 영역 -->

                <div class="container_1">
                    <a href="<%=contextPath %>/enrollForm.share" class="write_button">글쓰기</a>
                    
                    <fieldset class="thumb_space" style="width: 1500px; height:630px;">
                        <div class="thumb_space_1">
                            <ul>
                            <%  int space = listCount % boardLimit; %>
                            	
                            	<% if(space !=0 && currentPage == maxPage) {%>
                            		<% for (Share s : list) { %>
                            			<li class="thumbnail">
                            				<a href="<%=contextPath%>/detail.share?pno=<%=s.getPostNo()%>">
                                        		<img src="<%=contextPath %>/<%=s.getTitleImg() %>" alt="" class="thumbnail_img"><br>
                                        		<%= s.getPostTitle() %>
                                       		</a>
	                                    </li>
    	                        	<% } %>	
    	                        	<% for (int i = 0 ; i<(boardLimit-space); i++) { %>
    	                        		<li class="thumbnail_none">
                                    		<img src="<%=contextPath %>/resources/images/coconut.png" alt="" class="thumbnail_img"><br>
                                    		<a value=" " style="color : lightgray;">.</a>
                                		</li>
    	                        	<% } %>
    	                        	
                            	<% } else { %>
                            		<% for (Share s : list) { %>
    	                        		<li class="thumbnail">
                            				<a href="<%=contextPath%>/detail.share?pno=<%=s.getPostNo()%>">
                                        	<img src="<%=contextPath %>/<%=s.getTitleImg() %>" alt="" class="thumbnail_img"><br>
                                        	<%=s.getPostTitle() %></a>
	                                    </li>
                                		
    	                        	<% } %>
                            	<% } %>
                            </ul>
                        </div>    
                    </fieldset>
                    
         
                    
                    <br style="clear:both;">
          	     <div>
                    <div>
                        <form method="get" name="searchValue" action="<%=contextPath %>/shareSearch.share">
                            <input type="hidden" name="currentPage" value="<%=currentPage%>"> 
                            <table align="center">
                                <tr>
                                    <td>
                                        <select name="searchField" class="find_option form-control">
                                            <option value="0">선택</option>
                                            <option value="postTitle">제목</option>
                                            <option value="empNo">작성자</option>
                                        </select>
                                    </td>
                    
                                    <td>
                                        <input type="text" class="find_text form-control" placeholder="검색어를 입력해주세요" name="searchText" maxlength="50">
                                    </td>
                                    <td>
                                        <button type="submit" class="find_btn">
                                            <img src="<%=contextPath %>/resources/images/search-icon.png" alt="" class="find_btn_img">
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
	              </div>
	                      <br><br><br><br><br><br><br>
                         <div align="center" class="pagination">
                   	     <% if(currentPage != 1) { %>
			             	<button onclick="location.href='<%=contextPath%>/list.share?currentPage=<%=currentPage-1%>'">&lt;</button>
			             <% } %>
			             
			             <% for(int p = startPage; p <= endPage; p ++) { %>
			             		<% if (p != currentPage) { %>
			             		
			             			<button onclick="location.href='<%= contextPath %>/list.share?currentPage=<%= p %>';"><%= p %></button>
			             			
			             		<% } else { %>
			             			<button id="active-page" disabled><%= p %></button>
			             		
			             		<% } %>
			             <% } %>
			             
			             <% if(currentPage != maxPage && maxPage != 0) { %>
			             	<button onclick="location.href='<%=contextPath%>/list.share?currentPage=<%=currentPage+1%>'">&gt;</button>
			             <% } %>
                            
                      </div>
                        




                  </div>


	   </div>    
 </div>
 
 
	<script>
    $(function() {
       $("#header_menu").html("<div><a href='<%=contextPath%>/wagleList.po?currentPage=1'>코코넛 와글와글</a></div>"+
                               "<div><a href='<%=contextPath%>/thingsList.po?currentPage=1' >경조사</a></div>"+
                               "<div><a href='<%=contextPath%>/list.share?currentPage=1' style='color:#20c997'>나눔해요</a></div>");
	});


 </script>

        
</body>
</html>