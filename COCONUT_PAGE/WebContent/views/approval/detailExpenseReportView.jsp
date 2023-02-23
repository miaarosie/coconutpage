<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="com.kh.approval.model.vo.*,java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%
	Approval a = (Approval)request.getAttribute("a");
	ArrayList<DocSpent> list = (ArrayList<DocSpent>)request.getAttribute("list");
%>
<%
	Employee wemp = (Employee)request.getAttribute("e"); // 기안자
	ArrayList<Approver> al = (ArrayList<Approver>)request.getAttribute("al");
	ArrayList<Referrer> rl = (ArrayList<Referrer>)request.getAttribute("rl");
	
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <title>COCONUT PAGE</title>
    
    <style>
    body {
        background-color: rgb(253, 255, 240);
    }
    .wrap {
        
        width : 1920px;
        height : 1080px;
        /* border: 1px solid black; */
        margin : auto;
    }

    .container {
        width : 1920px!important;
        height : 980px;
        margin : 0px!important;
        padding : 0px!important;
        max-width: none!important;
    }
    
    .container>div {
        height : 980px;
        float : left;
    }
    .sidebar {
        width: 100px;
        background-color: rgb(110, 76, 68);
    }
    .main {
        float : left;
        width : 1820px;
    
    }
    
    #logo {
        width : 100px;
        height : 100px;
    
    }
    
    #coconut_page {
        color: rgb(110, 76, 68);
        font-weight: bolder;
        font-size: 40px;
        width : 300px;
        height : 100%;
        padding-top : 20px;
        vertical-align: middle;
        
    }
    
    .header>div {
        float : left;
    }
    
    /* --------------------------------------------------------------------- */
    /* 버튼색깔 */
    
    .btn-primary {
        background-color: rgb(110, 76, 68);
        border : none;
    }
    
    
    /* --------------------------------------------------------------------- */
    /* header 가운데 메뉴바 */
    
   
    
    /* 윤곽선용 */
    body * { 
        box-sizing : border-box;
        /* border : 1px solid black; */
    }
    
    /* --------------------------------------------------------------------- */
        /* main 영역 */
        .main {
            padding: 70px 100px;
        }

        /* --------------------------------------------------------------------- */
        /* 검색조건, 입력, 검색 버튼 */
        #search { height: 50px; }

        #search-filter, #search-text, #search-btn { height: 100%; }

        #search-filter { width: 200px; }

        #search-filter option[value=""] { display: none; }

        #search-text {
            width: 500px;
            display: inline;
        }

        #search-btn { width: 100px; }

        /* --------------------------------------------------------------------- */
        #main-left {
            width: 80%;
            height: 850px;
            float: left;
            padding-right: 50px;
        }

        #enroll-form {
            width: 100%;
            height: 100%;
        }

        
        .table {
            background-color: white;
            height: 100%;
            border: 1px solid lightgray;
            position: relative;
        }

        #spent-tb {
            background-color: white;
            height: 60px;
        }

        .thead {
            width: 20%;
            background-color:  #f4f4f4;
            text-align: center;
        }
        

        #title {
            width: 100%;
            
            
        }
        #thead-title{
            background-color: #f4f4f4;
            text-align: center;
        }

    
        #thead-list{
            background-color: #f4f4f4;
            height: 50px;
        }


        #table-list {
            height: 450px;
        }



        #text-title {
            width: 1100px;
            height: 40px;
          
        
        }

        .textarea2 {
            width: 1100px;
            height: 100px;
        }

        #spent-tb-thead {
            background-color: #f4f4f4;
        }

        #spent-btn {
            width: 120px;
            height: 50px;
            margin-bottom: 10px;
            float: right;
            background-color: rgb(110, 76, 68);
            color: white;
            border-radius: 5px;
            border: none;
            margin-right:10px;
            font-weight: bold;
        }

        /* --------------------------------------------------------------------- */

        #main-right {
            padding: 10px;
            height: 850px;
            float: left;
            width: 20%;
            border: 1px solid lightgray;
            background-color: white;
        }


        /* --------------------------------------------------------------------- */


        .title{
            text-align: center;
           
        }
        .profile {
            border: 1px solid black;
            margin: 10px;
            border-radius: 5px;
            padding: 10px;
            height: 100px;
            overflow-y: scroll
        }

        .profile-title {
            font-size: 20px;
            display: inline-block;
           
        }        
        .profile-double {
            height: 200px;
        }

        .profile-pic {
            clear: both;
            width: 50px;
            float: left;
        }

        .profile-addbtn {
            float: right;
            width: 65px; 
            background-color: rgb(110, 76, 68); 
            color : white; 
            font-weight: bold;
        }

        #circle {
            box-sizing: border-box;
            border: 1px solid black;
            width: 40px;
            height: 40px;
            text-align: center;
            border-radius: 20px;
        }
        .up-btn1{
            background-color: rgb(110, 76, 68); 
                color : white; 
                width: 100px; 
                height: 50px;
                padding: 3px; 
                border: solid 1px white;  
                font-size: 13pt; 
                font-weight: bold;
                border-radius: 4px;
                margin-left: 500px;
        }
        .up-btn2{
            background-color: rgb(110, 76, 68); 
                color : white; 
                width: 100px; 
                height: 50px;
                padding: 3px; 
                border: solid 1px white;  
                font-size: 13pt; 
                font-weight: bold;
                border-radius: 4px;
                margin-left: 20px;
        }
        #ap-btn {
            float: right;
            font-size: 13pt;
            font-weight: bold;
            background-color: rgb(110, 76, 68); 
            color : white; 
            width: 120px;
            height: 50px;
            border-radius: 4px;
            border:transparent;
            margin-right:10px;
            margin-bottom: 10px;
        }


        input[type="number"]::-webkit-outer-spin-button,
        input[type="number"]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }

        #pos-ab {
            position: absolute;
            bottom: 10px;
            right: 10px;
        }

        #spent-tb {
            overflow: auto;
        }
    
           /* --------------------------------------------------------------------- */

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

                    <table class="table">

                        <tr>
                            <th class="thead">신청일자 / <br> 신청자</th>
                            <td>
                                <input type="text" name="enrollDate" id="enrollDate" disabled value="<%= a.getUpdateDate() %>" />
                                <input type="text" name="userName" value="<%= e.getEmpName() %>" disabled>
                            </td>
                        </tr>

                        <tr>
                            <th id="thead-title" >제목</th>
                            <td>
                            	<%= a.getAprTitle() %>
                                <!-- <input type="text" placeholder="보고서 예시 제목" name="title" id="title" style="border:1px solid gray;" required maxlength="33"> -->
                            </td>
                        </tr>
                        <tr>
                            <th class="thead">총사용금액</th>
                            <td>
                            <input type="number" id="spent" value="0" size="25px" style="border:1px solid gray;" disabled> 원
                            </td>
                        </tr>
                        
                        <tr>
                            <th class="thead" id="thead-list">파일첨부</th>
                            <td>
                                <% if (a.getOriginName() != null) { %>
                               		<a download="<%= a.getOriginName() %>" href="<%= contextPath %>/resources/approval_upfiles/<%= a.getChangeName() %>">
                               			<%= a.getOriginName() %>
                               		</a>
                               	<% } %>
                            </td>
                        </tr> 

                        <tr>
                            <th class="thead">비고</th>
                            <td>
                            	<textarea style="height: 100%; width: 100%; resize:none; background-color:white; color:black; border:none;" disabled><%= a.getAprContent() %></textarea>
                            </td>
                        </tr>
                        <tr id="table-list">
                            <th class="thead">내역</th>
                            <td>
                            	<br>
                                <div id="pos-re">
                                    <table class="table" style="text-align:center;" id="spent-tb">
                                        <thead id="spent-tb-thead">
                                        <tr>
                                            <td>사용일자</td>
                                            <td>지출구분</td>
                                            <td>사업자명</td>
                                            <td>사업자번호</td>
                                            <td>금액</td>
                                        </tr>
                                        </thead>
    
                                        <tbody id="spentList">
                                            <% if (!list.isEmpty()) { %>
	                                            <% for (DocSpent d : list) { %>
	                                            <tr>
	                                            	<td><%= d.getSpentDate() %></td>
	                                            	<td><%= d.getHistory() %></td>
	                                            	<td><%= d.getCorpName() %></td>
	                                            	<td><%= d.getCorpNo() %></td>
	                                            	<td><span class="smoney"><%= d.getSpentMoney() %></span></td>
	                                            </tr>
	                                            <% } %>
                                            <% } else { %>
                                            	<tr>
                                            		<td colspan="5">첨부된 내역이 존재하지 않습니다.</td>
                                            	</tr>
                                            <% } %>
                                        </tbody>
    
                                    </table>
                                </div>
                                <div id="pos-ab">
                                	<button id="ap-btn" onclick="history.back();">확인</button>
                                </div>

                               
                            </td>

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

					<%@ include file="appStatusModal.jsp" %>

            </div>
    
        </div>
    </div>
    
    <script>
    var result = 0;

    $(function() {
        
        $.each($(".smoney"), function(index, value) {

            var smoney = $(this).text();

            result += parseInt(smoney);
        });

        $("#spent").val(result);
    });	

    </script>
    
    <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap'>전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1' >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' >임시저장함</a></div>"                    		
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'  style='color:#20c997'>전체문서조회</a></div>"
            		<%}%>
            );
    });
   </script>
        
</body>
</html>