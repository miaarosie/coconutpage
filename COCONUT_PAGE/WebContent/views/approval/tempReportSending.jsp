<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.kh.employee.model.vo.Employee" %>
    <%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
    <%@page import ="com.kh.approval.model.vo.Approval" %>
<%
	
   SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
   Date date = new Date(System.currentTimeMillis()); 
   
%>
<%Approval a = (Approval)request.getAttribute("a");%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- 임시저장한 일반보고서 작성 폼 -->
<title>COCONUT PAGE</title>

<style>

.main {
	padding: 70px 100px;
}

/* --------------------------------------------------------------------- */
/* 검색조건, 입력, 검색 버튼 */
#search {
	height: 50px;
}

#search-filter, #search-text, #search-btn {
	height: 100%;
}

#search-filter {
	width: 200px;
}

#search-filter option[value=""] {
	display: none;	
}

#search-text {
	width: 500px;
	display: inline;
}

#search-btn {
	width: 100px;
}

/* --------------------------------------------------------------------- */
#main-left {
	width: 80%;
	height: 850px;
	float: left;
	padding-right: 50px;
}

.thead {
	width: 20%;
	background-color: #f4f4f4;
	text-align: center;
}

#title {
	width: 1100px;
}

#text-title {
	width: 1100px;
	height: 40px;
}

#textarea {
	width: 1100px;
	height: 500px;
	resize: none;
}

/* --------------------------------------------------------------------- */
.title {
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
	color: white;
    padding: 5px 10px;
    border-radius : 5px;
    border: none; 
    font-size: 18px;
    font-weight: 700;
}

.up-btn1 {
	position : absolute;
	right:140px;
	bottom : 20px;
	background-color: rgb(110, 76, 68);
	color: white;
	width: 100px;
	height: 50px;
	padding: 3px;
	border: solid 1px white;
	font-size: 13pt;
	border-radius: 4px;
}

.up-btn2 {
	position : absolute;
	right:20px;
	bottom : 20px;
	background-color: rgb(110, 76, 68);
	color: white;
	width: 100px;
	height: 50px;
	padding: 3px;
	border: solid 1px white;
	font-size: 16pt;
	border-radius: 4px;
}


#a-title {
	background-color: #f4f4f4;
	text-align: center;
}

#f-upload {
	background-color: #f4f4f4;
	height: 132px;
}

            input[type="number"]::-webkit-outer-spin-button,
            input[type="number"]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
            }


            #main-right {
                padding: 10px;
                height: 850px;
                float: left;
                width: 20%;
                border: 1px solid lightgray;
                background-color: white;
            }

            /* --------------------------------------------------------------------- */
 
            .aprline_myprofile {
                border: 1px solid black;
                margin: 10px;
                border-radius: 5px;
                padding: 10px;
                overflow-y: auto
            }  
            
            #aprline {
            	height: 300px;
                border: 1px solid black;
                margin: 10px;
                border-radius: 5px;
                padding: 10px;
                overflow-y: auto;
            
            }
            #ccline {
        	    height: 300px;
                border: 1px solid black;
                margin: 10px;
                border-radius: 5px;
                padding: 10px;
                overflow-y: auto;
            }
            
            .aprline_profile {
				overflow : hidden;
				height: 80px;
            } 
            .aprline_profile>div {
            	float: left;
            	height : 100%;
            }
             .aprline_profile>div:nth-child(1) {
            	width:30%;
            	position: relative;
            } 
            .aprline_profile>div:nth-child(2) {
            	width:70%;
            }
            
            .aprline_profile img {
              background-color :  rgb(110, 76, 68); 
              position: absolute;
			  margin: auto;
			  top: 0;
			  bottom: 0;
			  left: 0;
			  right: 0;
			  width: 57px;
			  height:57px;       	 
            }    
    		
    		.aprline_profile h6 {
    			height: 50%; 
    			margin: 0;
    			padding-left : 10px;
    			line-height: 37px;
    		}
    		.aprline_profile h6:nth-child(1) {
    			line-height: 50px;
    		}
    		.aprline_profile h6:nth-child(2) {
    			line-height: 30px;
    		}
    		
    		#aprline_header {
    			overflow : hidden;
    		}
    	
        	 #aprline_header>span {
        	 	 display : inline-block;
            	 font-size: 18pt; 
                 font-weight: 800;
                 margin-top: 3px;           
            }
    
            .profile-addbtn {
                float: right;
                padding: 5px 10px;
                border-radius : 5px;
                border: none;                 
                background-color: rgb(110, 76, 68); 
                font-size: 18px;
                color : white;  
                font-weight: 700;
            }

       
            input[type=file] {
                width: 220px;
            }
            
             #report_enroll-form {
           		width: 100%;
                height: 100%;
            }
            
            #reportenroll_tb {
            	height: 100%;
            	background-color: white;
           		border: 1px solid lightgray;
            }
            
</style>

</head>
<body>

	<div class="wrap">
		<%@ include file="../common/header.jsp"%>

		<div class="container">
			<%@ include file="../common/sidebar.jsp"%>


			<div class="main">

				<div id="main-left">
					<form id="report_enroll-form" action="<%=request.getContextPath()%>/update.re" method="post" enctype="multipart/form-data">
					<input type="hidden" name ="empNo" value=<%=e.getEmpNo()%>>
					<input type="hidden" name="aprId" value="<%= a.getAprId() %>">
					
					<%@ include file="appLineModal.jsp" %>
					
						<table class="table" id="reportenroll_tb" >
		
							<tr>
								<td class="thead">신청일자/ <br>신청자</td>
								<td style="vertical-align: middle;"><input type="date" name="updateDate" id="enrollDate" value="<%= formatter.format(date) %>" disabled />
								 <input type="text" value="<%=e.getEmpName()%>"
								id="text-name" disabled>
								<input type="hidden" id="userId" name="userId" value="<%= e.getEmpNo() %>">
								
							</td>
						
							</tr>

							<tr>
								<td id="a-title">제목</td>
								<td><input type="text" placeholder="보고서 예시 제목" id="title" name="aprTitle"
									style="border: transparent" value="<%=a.getAprTitle()%>" required>
								</td>
							</tr>

							<tr>
								<td class="thead">기안내용</td>
								<td><textarea name="aprContent" required  id="textarea" cols="30" rows="10" style="border: transparent" required><%=a.getAprContent()%></textarea>
								</td>
							</tr>
							<tr>
								<td class="thead" id="f-upload">파일업로드</td>
								<td style="position:relative;">
								 <% if (a.getOriginName() == null) { %>
                                    <input type="file" name="upfile" id="upfile" onchange="loadFile(this);">
                                <% } else { %>
                                    <input type="file" name="upfile" id="upfile" onchange="loadFile(this);" value="<%= a.getOriginName() %>">
                                    <a download="<%= a.getOriginName() %>" href="<%= contextPath %>/resources/approval_upfiles/<%= a.getChangeName() %>" id="file-name">
                                        <%= a.getOriginName() %>
                                    </a>
                                     <a onclick="removeOriFile();" class="btn btn-outline-secondary btn-sm" style="margin-left: 10px;">X</a>
                                <% } %>
                                <input type="hidden" name="oriFileChk" id="oriFileChk" value="0">
                                <input type="hidden" name="bfChangeFileName" value="<%= a.getChangeName() %>">
                                <input type="hidden" name="bfOriFileName" value="<%= a.getOriginName() %>">
								
									<input type="submit" id="f-btn1" value="임시저장" class="up-btn1"  onclick="return aprInsert(2)"> 
									<input type="submit" id="f-btn2" value="기안상신" class="up-btn2" onclick="return aprInsert(1)">
								</td>
							</tr>
						</table>
					</form>

				</div>

				<div id="main-right">
    
    
    					<div id="aprline_header">
    					 <span>결재선 지정</span>
    					 <input type="button" value="추가 +"  class="profile-addbtn" data-toggle="modal" data-target="#approval-Line-Modal">
    					</div>
                        <hr>
    
                        <div class="aprline_myprofile">
                            <div class="profile-title">기안자</div>
                            <div class="aprline_profile">
	                            <div>
	                       <%  if (loginEmp.getProfile() == null) { %>
								<img src="resources/images/icons8-user-beige-96.png" class="rounded-circle"  >    					
    					   <%	} else {  %>
		    					<img src="resources/profile_upfiles/<%=loginEmp.getProfile() %>"class="rounded-circle" >
    					  <%  } %>
	                            </div>
	                            <div>
	                                <h6><%=loginEmp.getEmpName() %></h6>
	                                <h6><%=loginEmp.getJobCode() %> | <%=loginEmp.getDeptCode() %></h6>
	                            </div>
                            </div>
                        </div>
                        <div id="aprline">
                         <div class="profile-title">결재자</div>
                      	  
                        </div>
                        <div id="ccline">
                            <div class="profile-title">참조자</div>                            
                            
                        </div>
                    </div>
                
			</div>
		</div>
	</div>
	
	<script>
	function aprInsert(type) {
		var aprList = [];
		var ccList = [];
		
		$("input[class='aprline_list']").each(function() {
			aprList.push($(this).val());
		});
		
		$("input[class='aprline_list2']").each(function() {
			ccList.push($(this).val());
		});
		
		// 결재자 참조자 리스트 제출용 input 에 담기
		$("input[name='aprList']").val(aprList.join(" "));
		$("input[name='ccList']").val(ccList.join(" "));
		
		if(type == 1) {
			if(aprList.length < 1) {
        	    alert("본인 제외 최소 한명 이상의 결재자가 필요합니다.");        		
        		return false;	
        	}
		}else {
			$("#report_enroll-form").attr("action", "<%=contextPath%>/update.tsre");  
		}
	}
	

	 
	 var oriFileChk = 0;    
	    function loadFile(inputFile) {
	        if (inputFile.files.length == 1) {
	            oriFileChk = 1;
	    		$("#oriFileChk").val(1);
	    		$("#f-upload").next().children("a").remove();    		
	    		$("#f-upload").next().append("<a onclick='removeFile();' class='removebtn btn btn-outline-secondary btn-sm' style='margin-left: 10px;''>X</a>");
	        }else {
	        	$("#f-upload").next().children(".removebtn").remove(); 
	        }
	    }

	    // 기존 파일 지우는 함수
	    function removeOriFile() {        
	        oriFileChk = 1;
			$("#oriFileChk").val(1);		
			$("#f-upload").next().children("a").remove();
	        
	    }
	    
	 // 새로 추가한 파일 지우는 함수
	    function removeFile() {        
			$("#f-upload").next().children("a").remove();        
			
			var agent = navigator.userAgent.toLowerCase();
			//파일초기화 크로스 브라우징 
			if ((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) { 
				   $("#upfile").replaceWith($("#upfile").clone(true));
			
			} else{ //크롬 파폭 
				   $("#upfile").val("");
			}    
		}
	
	</script>
	
	
	     	                <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap' >전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1'  >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' style='color:#20c997' >임시저장함</a></div>"
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'>전체문서조회</a></div>"
            		<%}%>
            );
            $("input[name=aprTitle]").keydown(function() {
                if (event.keyCode === 13) {
                    event.preventDefault();
                }
            });
    });
   </script>

</body>
</html>