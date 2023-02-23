<%@page import="java.text.NumberFormat"%>
<%@page import="com.kh.approval.model.vo.DocSpent"%>
<%@page import="com.kh.approval.model.vo.Approver"%>
<%@page import="com.kh.approval.model.vo.Referrer"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.employee.model.vo.Employee, com.kh.approval.model.vo.Approval" %>
<%
	int aprType = (int)request.getAttribute("aprType");
	Approval a = (Approval)request.getAttribute("a");
	Employee wemp = (Employee)request.getAttribute("e"); // 기안자
	ArrayList<Approver> al = (ArrayList<Approver>)request.getAttribute("al");
	ArrayList<Referrer> rl = (ArrayList<Referrer>)request.getAttribute("rl");
	int aprState = (int)request.getAttribute("aprState");
	int route = (int)request.getAttribute("route");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
     <style>
        .wrap {
            height : 1080px;
        }
        /* --------------------------------------------------------------------- */        
     
        /* 윤곽선용 */
        body * { 
            box-sizing : border-box;
            /* border : 1px solid black; */
        }
        
        /* --------------------------------------------------------------------- */
        /* main 영역 */
        .main {
            padding: 0 100px;
        }

        /* --------------------------------------------------------------------- */
 
        #main-left {
            width: 78%;
            height: 850px;
            float: left;
            padding-right: 50px;
        }
        
        #apr_btn_dv>button {
    	    float: right;
    	    width: 120px;
    	    height : 50px;
        	vertical-align: middle;
        	background-color: rgb(110, 76, 68); 
        	border-radius : 5px;
            font-size: 18px;
            color : white;  
            font-weight: 700;
            margin-top: 15px;
        }
        
        #apr_btn_dv>button:hover {
    	    background-color: #562B08;
			color: #FFFF00;	  
			font-size: 19px;    
			font-weight: 800;  
        }
        
        
        #aprdoc_view_tb {
            background-color: white;
            width: 100%;
            height: 100%;
            border: 1px solid lightgray;
            position: relative;
        }
        #aprdoc_view_tb tr:nth-child(1) {
        	height: 10%;
        }
         #aprdoc_view_tb tr:nth-child(2) {
        	height: 10%;
        }
         #aprdoc_view_tb tr:nth-child(3) {
        	height: 70px;
        }
         #aprdoc_view_tb tr:nth-child(4) {
        	height: 70%;
        }

		#aprdoc_view_tb th {
			background-color:  #f4f4f4;
			vertical-align: middle;
		    padding: 0;
		    width: 15%; 
		    text-align: center;
		    font-size: 20px;
   		    font-weight: 700;
		}
		#aprdoc_view_tb td {
			vertical-align: middle;
		    padding: 0 15px;
		    font-size: 18px;
   		    font-weight: 600;
		}
		#aprdoc_view_tb .aprdoc_content_tb {
			vertical-align: baseline;
			padding: 20px 15px;
		}

		#docspent_dv {
			height: 100%;
			overflow: hidden;
			
		}

	
		.file_download>span {
			display: inline-block;
			margin-top:8px;
		}	
		
		.file_download>a {
			float:right;
			padding: 10px;
			background-color: rgb(110, 76, 68); 
			color: white;
			border: none;
			border-radius: 10px;
			text-decoration: none;
		}
		.file_download>a:hover {
			background-color: #594545;
			color: #FFF9B0;	
			text-decoration: none;
		}
		

        /* --------------------------------------------------------------------- */

        #main-right {
            padding: 10px;
            height: 850px;
            float: left;
            width: 22%;
            border: 1px solid lightgray;
            background-color: white;
            margin-top: 80px;
        }

        /* --------------------------------------------------------------------- */
		 /* -결재라인 부분---------- */
        .profile-title {
            font-size: 20px;
            padding-left: 10px;       
        }        
        
        .aprline_myprofile {
            border: 1px solid black;
            margin: 5px 10px 10px 10px;
            border-radius: 5px;
            padding: 5px 10px;
            overflow-y: auto
        }  
        
        #aprline {
        	height: 265px;
            border: 1px solid black;
            margin: 5px 10px 10px 10px;
            border-radius: 5px;
            padding: 5px 10px;
            overflow-y: auto;
        
        }
        #ccline {
    	    height: 265px;
            border: 1px solid black;
            margin: 5px 10px 10px 10px;
            border-radius: 5px;
            padding: 5px 10px;
            overflow-y: auto;
        }
        
        .aprline_profile {
			height: 83px;
			position: relative;
        } 
        .aprline_profile>div {
        	float: left;
        	height : 100%;
        }
         .aprline_profile>div:nth-child(1) {
        	width:25%;
        	position: relative;
        } 
        .aprline_profile>div:nth-child(2) {
        	width:55%;
        }
        .aprline_profile>div:nth-child(3) {
        	width:20%;
        	position: relative;
        }
        
        .aprline_profile img {
          position: absolute;
		  margin: auto;
		  top: 0;
	  	  bottom: 0;
		  left: 0;
	 	  right: 0;
	 	  width: 50px;
		  height:50px;     	
	 	  background-color: rgb(110, 76, 68);
        }    
		
		.aprline_profile h6:nth-child(1) {
			height: 50%; 
			margin: 0;
			line-height: 57px;
			overflow: hidden;
		}
			
		.aprline_profile h6:nth-child(2) {
			height: 50%; 
			margin: 0;
			line-height: 27px;
		}
		
		.aprline_profile span {
			width: 50px;
			height: 30px;
			padding: 5px 10px;
        	background-color: gray; 
        	border-radius : 5px;
            font-size: 13px;
            color : white;  
            font-weight: 600;	
            border:none;
            position: absolute;
			margin: auto;
			top: 0;
		  	bottom: 0;
			left: 0;
		 	right: 0;
		 	cursor: not;  
		 	text-align: center;
		}
		.aprline_profile .aprread {
			background-color: #562B08; 
		}
		
		.aprline_profile .aprrefuse {
			background-color: #E0144C; 
		
		}
		.aprline_profile .aprok {
			background-color: #3D8361; 
		}
	
		
		.aprline_profile i {
			position: absolute;
			bottom: -9px;
		    left: 50%;
		    font-size: 12px;
		    transform: translate(-50%, 0);
		}
		
		
		#aprline_header {
			overflow : hidden;
		}
		#aprline_header+hr {
			margin: 10px 0;
		}
	
    	 #aprline_header>span {
    	 	 display : inline-block;
        	 font-size: 18pt; 
             font-weight: 800;
             margin-top: 3px;           
        }

        .apr_Status_btn {
            float: right;
           	width: 95px;
           	height: 37px;
           	line-height : 37px;
            border-radius : 5px;
            border: none;                 
            background-color: rgb(110, 76, 68); 
            font-size: 17px;
            color : white;  
            font-weight: 700;
        }
        .apr_Status_btn:hover {
            font-size: 20px;
            color : #FFFF00;  
            font-weight: 700;
        }
        
        
        #apr_btn_dv {
        	height: 80px;
        }
        
        /* --------------------- */
        /* 결재 반려 모달 */
   
		  
		#aprYN-Modal .modal-content {
		    background-color: #f4f4f4;
		    position: relative;
		    border-radius: 20px;
		    width:500px;
		    margin: 350px auto;
		}
		#aprYN-Modal .modal-body {
			margin: 80px auto 50px;
			text-align: center;
			font-size: 30px;
            font-weight: 700;
			
		}
		#aprYN-Modal .modal-footer {
			margin: 0 auto 20px;
		}
		#aprYN-Modal .modal-footer>button {
    	    width: 140px;
    	    height : 60px;
        	vertical-align: middle;
        	background-color: rgb(110, 76, 68); 
        	border-radius : 10px;
            font-size: 20px;
            color : white;  
            font-weight: 600;
            margin: 0 10px;
            border:none;
		}
		#aprYN-Modal .modal-footer>button:nth-child(1) {
        	background-color: lightgray; 
        	color: #E0144C;

		}
		#aprYN-Modal .modal-footer>button:nth-child(1):hover {
    		font-size: 23px;
            font-weight: 800;
            color: #CF0A0A;
            border: 5px solid #CF0A0A;

		}
		#aprYN-Modal .modal-footer>button:nth-child(2):hover {
    		font-size: 23px;
            font-weight: 800;
            color: #FFFF00;	  
            border: 5px solid black;
		}
		
		#aprcomment-Modal .modal-content {
		    background-color: lightgray;
		    border-radius: 20px;
		    width:500px;
		    height: 400px;
		    margin: 170px auto;
		    margin-left:500px;
		    padding: 20px;
		}
		#aprcomment-Modal .modal-body {
			background-color: white;
		}
		#aprcomment-Modal form {
			width: 100%;
			height: 100%;
		}
		
		#aprcomment-Modal textarea {
			width: 100%;
			height: 80%;
			border-radius: 5px; 
			font-size: 20px;
			border : none;
			outline: none;
			resize: none;
		}
    	
    	#aprcomment-Modal button {
    		width: 80px;
    	    height : 50px;
        	vertical-align: middle;
        	background-color: rgb(110, 76, 68); 
        	border-radius : 10px;
            font-size: 20px;
            color : white;  
            font-weight: 600;
            float:right;
            border:none;
    	}
    	#aprcomment-Modal button:hover {
            font-size: 22px;
            color : #FFFF00;  
            font-weight: 700;
    	}

		    	    
    </style>
</head>
<body>
	   <div class="wrap">
            <%@ include file="../common/header.jsp" %>
              
            <div class="container">
                
              <%@ include file="../common/sidebar.jsp" %>
        
                <!-- 실제 content 영역 -->
                <div class="main">
                    <div id="main-left">
                     <div id="apr_btn_dv">
                     <button  onclick="history.back()" style="float: left;">목록</button>
                      <% if(aprState == 1) { %>
               		    <button data-toggle="modal" data-target="#aprYN-Modal">결재/반려</button>
                  	  <% } %>                    
                     </div>
                        <table class="table" id="aprdoc_view_tb">
                            <tr>
                                <th>신청일자 / <br> 신청자</th>
                                <td>
                                   	<%=a.getUpdateDate() %>&nbsp;<%=wemp.getEmpName() %>
                                </td>
                            </tr> 
                            <tr>
                                <th>제목</th>
                                <td>
                                   	<%=a.getAprTitle() %>
                                </td>
                            </tr>                                                        
                            <tr>
                                <th>첨부파일</th>
                                <td class="file_download">
                               <% if(a.getOriginName() == null) { %>
									<span>파일 없음</span>
								<% } else {%>
									<span><%=a.getOriginName() %></span>
									<a download="<%=a.getOriginName() %>" href="<%=contextPath %>/resources/approval_upfiles/<%=a.getChangeName()%>">다운로드</a>
								<% } %>	
                                </td>
                            </tr>     
                            <tr >
                                <th>내용</th>
                                <td class="aprdoc_content_tb"><textarea disabled style="resize:none; width:100%; height:100%; background-color:white; border:none; color:black;"><%=a.getAprContent() %></textarea></td>
                            </tr>                                                 
                        </table>
                    </div>
    
                    <div id="main-right">
    
    					<div id="aprline_header">
    					 <span>결재선</span>
    					 <input type="button" value="결재현황" class="apr_Status_btn" data-toggle="modal" data-target="#AprStatus-Modal">
    					</div>
                        <hr>
    					<div class="profile-title">기안자</div>
                        <div class="aprline_myprofile">
                            
                            <div class="aprline_profile">
	                            <div>
                           <%  if (wemp.getProfile() == null) { %>
								<img src="resources/images/icons8-user-beige-96.png" 	class="rounded-circle"  >    					
    					   <%	} else {  %>
		    					<img src="resources/profile_upfiles/<%=wemp.getProfile() %>" class="rounded-circle" >
    					  <%  } %>
	                            </div>
	                            <div>
	                                <h6><%=wemp.getEmpName() %></h6>
	                                <h6><%=wemp.getJobCode() %> | <%=wemp.getDeptCode() %></h6>
	                            </div>
                            </div>
                        </div>
                        <div class="profile-title">결재자</div>
                        <div id="aprline">                         
                      	   <% int chk =1;
                      	   	  for(int i = 0; i<al.size(); i++) { %>
                             <div class="aprline_profile">
	                            <div>
	                       <%  if (al.get(i).getProfile() == null) { %>
								<img src="resources/images/icons8-user-beige-96.png" 	class="rounded-circle"  >    					
    					   <%	} else {  %>
		    					<img src="resources/profile_upfiles/<%=al.get(i).getProfile() %>" class="rounded-circle" >
    					   <%  } %>	                            
	                            </div>
	                            <div>
	                            	<h6><%=al.get(i).getEmpName() %></h6>
	                                <h6><%=al.get(i).getJobName() %> | <%=al.get(i).getDeptName() %></h6>
	                            </div>
	                            <div>
                            	<% if(al.get(i).getAprYN().equals("1")) { %>
                          		   <span>접수</span>
	                            <% } else if(al.get(i).getAprYN().equals("2")) { %>
	                            	<span class="aprread">열람</span>
	                            <% } else if(al.get(i).getAprYN().equals("3")) {%>
	                          		<span class="aprrefuse">반려</span>
	                            <% } else { %>
	                             	<span class="aprok">승인</span>
	                            <% } %>
	                            </div>
	                       <% if(chk<al.size()) { %>
	                     	  <i class="fa fa-chevron-down" aria-hidden="true"></i>
	                       <% } %>	                            
                            </div>
                           <% chk++;
                           	  } %>
                        </div>
                        <div class="profile-title">참조자</div>  
                        <div id="ccline">                               
                            <% chk =1; 
                         	   for(Referrer r : rl) { %>
                             <div class="aprline_profile">
	                            <div>
	                       <%  if (r.getProfile() == null) { %>
								<img src="resources/images/icons8-user-beige-96.png" 	class="rounded-circle"  >    					
    					   <%	} else {  %>
		    					<img src="resources/profile_upfiles/<%=r.getProfile() %>" class="rounded-circle" >
    					   <%  } %>	                            
	                            </div>
	                            <div>
	                                <h6><%=r.getEmpName() %></h6>
	                                <h6><%=r.getJobName() %> | <%=r.getDeptName() %></h6>
	                            </div>
	                            <div>
                            	<% if(r.getViewDate()!= null) { %>
                          		   <span class="aprok">확인</span>
	                            <% } %>
	                            </div>	                            
                           <% if(chk<rl.size()) { %>
	                     	  <i class="fa fa-chevron-down" aria-hidden="true"></i>
	                       <% } %>	                            
                            </div>
                           <% chk++;
                           	  } %>                       
                        </div>
                    </div>
             
             		 <!-- The 결재 반려 버튼 Modal -->
			        <div class="modal" id="aprYN-Modal">
			            <div class="modal-dialog modal-lg">
			                <div class="modal-content">
			                    <!-- Modal body -->
			                    <div class="modal-body">
			                               	  결재하시겠습니까?
			                    </div>
			                    <!-- Modal footer -->
			                    <div class="modal-footer">
			                        <button type="button" onclick="aprsel('3')">반려</button>
			                        <button type="button" onclick="aprsel('4')">결재</button>
			                    </div>
			                </div>
			            </div>
			        </div>

			         <!-- 의견 작성 버튼 Modal -->
			        <div class="modal" id="aprcomment-Modal">
			            <div class="modal-dialog modal-lg">
			                <div class="modal-content">
			                    <!-- Modal body -->
			                    <div class="modal-body">
			                    	<form action="<%=contextPath%>/update.ayn" method="post" >
			                    	 <textarea name="aprComment" placeholder="의견을 입력해주세요."></textarea>
			                    	 <input type="hidden" name="aid" value="<%= a.getAprId() %>">
			                    	 <input type="hidden" name="aprType" value="<%= aprType %>">
			                    	 <input type="hidden" name="apryn" value="">
			                  	  	 <button type="submit">확인</button>
			                  	  	</form>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <%@ include file="appStatusModal.jsp" %>
			        
                </div>
            </div>
        </div>
        <script>
        	function aprsel(sel) {
        		$("input[name='apryn']").val(sel);
        		$("#aprcomment-Modal").modal("show");
        	}
        	
        	function aprStatus() {
        	  $("#AprStatus-Modal").modal("show");
        	}
        </script>
        
    <script>	
    $(function() {
   		var route =<%=route%>;
   		if(route == 1) {       			
   		  $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap' >전자결재 홈</a></div>"+
                  "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' style='color:#20c997'>받은문서함</a></div>"+
                  "<div><a href='<%=contextPath%>/send.docs?currentPage=1' >상신문서함</a></div>"+
                  "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' >임시저장함</a></div>"                    		
                  <% if(e.getManagerYN().equals("Y")) {%>
                  +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'>전체문서조회</a></div>"
          		<%}%>
          );       			
   		}else if(route == 2)  {
       		  $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap'>전자결재 홈</a></div>"+
                      "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                      "<div><a href='<%=contextPath%>/send.docs?currentPage=1' style='color:#20c997'>상신문서함</a></div>"+
                      "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' >임시저장함</a></div>"                    		
                      <% if(e.getManagerYN().equals("Y")) {%>
                      +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'>전체문서조회</a></div>"
              		<%}%>
              );
   		}else {
   			$("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap'>전자결재 홈</a></div>"+
                      "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                      "<div><a href='<%=contextPath%>/send.docs?currentPage=1' >상신문서함</a></div>"+
                      "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' >임시저장함</a></div>"                    		
                      <% if(e.getManagerYN().equals("Y")) {%>
                      +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1' style='color:#20c997'>전체문서조회</a></div>"
              		<%}%>
              );
   		}       		
});
   </script>

</body>
</html>