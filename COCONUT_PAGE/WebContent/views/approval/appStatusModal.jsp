<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>

<style>
/* 결재 진행 상황 페이지 */
.main {
	position: relative;
}

#AprStatus-Modal .modal-dialog {
	min-width: 1600px;
	max-width: 1600px;
	height: 880px;
	margin: 100px auto;
}

#AprStatus-Modal .modal-content {
	border-radius: 20px;
	width: 100%;
	height: 100%;
}

#approval-state {
	width: 100%;
	height: 100%;
	position: relative;
}
#approval-state>button {
	position:absolute;
	bottom:20px;
	right:15px;
	background-color: #6e4c44;
	padding: 4px 30px;
	font-size: 20px;
	font-weight: 900;
	color: #fdfff0;
}

#approval-state-1, #approval-state-3 {
	height: 6%;
	background-color: #6e4c44;
	color: #f4f4f4;
	font-size: 25px;
	font-weight: 700;
	line-height: 53px;
	padding-left: 70px;
}

#approval-state-2, #approval-state-4 {
	background-color: #fff;
	height: 44%;
	overflow-x: auto;
	white-space: nowrap;
	margin: 0 auto;
	text-align: center;
	font-size: 0px;
	position: relative;
}

.approval-list, .cc-list {
	font-size: 15px;
	display: inline-block;
	width: 20%;
	height: 98%;
	position: relative;
}

.cc-list {
	width: 17%;
	height: 85%;
}

.approval-list>i {
	font-size: 90px;
	font-weight: 500;
	position: absolute;
	top: 28px;
	right: -27px;
}

.approval-list>img, .cc-list>img {
	background-color: rgb(110, 76, 68);
	width: 96px;
	height: 96px;
	cursor: pointer;
	margin: 28px;
}

.approval-list>span, .cc-list>span {
	display: block;
	width: 60%;
	padding: 4px;
	font-size: 20px;
	font-weight: 700;
	border-radius: 10px;
	margin: 0 auto 15px;
	border: 2px solid grey;
	
}

	.cc-list>span {
		padding: 3px;
		font-size: 18px;
		margin: 0 auto 10px;
	}

	.approval-list>span:nth-of-type(3) {
		background-color: #f4f4f4;
	}
	
	.approval-list>span:nth-of-type(4) {
		width: 75%;
	}
	
	.cc-list>span:nth-of-type(4) {
		width: 88%;
	}

	
		#approval-state-2 .aprread {
			background-color: #562B08; 
			color: white;
		}
		
		.approval-list  .aprrefuse {
			background-color: #E0144C; 
			color: red;
		
		}
		#approval-state-2 .aprok,  .cc-list .aprok {
			background-color: #3D8361;
			color: white;
			border:none;
		}
		.cc-list .aprwait {
			background-color: #f4f4f4;
		}

	/*  미니프로필 결재의견  모달*/

	 #profile_aprcomment_Modal .modal-dialog {
            min-width: 750px;
            max-width: 750px;
            margin: 200px auto;
        }

        #profile_aprcomment_Modal .modal-content {
            border-radius: 20px;
            width: 100%;
            overflow: hidden;
            background-color: rgb(224, 224, 224);
        }

        .modal-body {
            padding: 20px 30px;

        }

        #profile_aprcomment_Modal img {
            display: inline-block;
            background-color: #6e4c44;
            margin: 30px 20px 20px 20px;
            width: 100px;
            height: 100px;
        }

        #profile_aprcomment_Modal span {
            vertical-align: -30px;
            font-size: 30px;
            color: #6e4c44;
            font-size: 30px;
            font-weight: 700;
        }

        #profile_aprcomment_Modal table {
            color: rgb(253, 255, 240);
            height: 80%;

        }

        #profile_aprcomment_Modal th,
        #profile_aprcomment_Modal td {
            vertical-align: middle;
            padding: 0;
        }

        #profile_aprcomment_Modal th {
            background-color: #6e4c44;
            width: 16%;
            font-size: 16px;
            font-weight: 500;
            padding: 8px 20px;
            color: white;
        }

        #profile_aprcomment_Modal td {
            width: 34%;
            font-size: 16px;
            font-weight: 600;
            background-color: white;
            padding: 8px 20px;
            color: black;
        }

        #profile_aprcomment_Modal h5 {
            margin: 10px 0;
        }

        #profile_aprcomment_Modal .modal-body>div:last-child {
            border: 1px solid black;
            background-color: white;
            height: 100px;  
            padding: 10px;
            border-radius: 5px;
        }
		/* ------------------------- */
</style>
</head>


<body>
	<!-- Apr Status Modal -->
	<div class="modal" id="AprStatus-Modal" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="approval-state">
					<div id="approval-state-1">결재 진행 현황</div>
					<div id="approval-state-2">
						<div class="approval-list">
							<i class="fa fa-angle-double-right" aria-hidden="true"></i>
						<%  if (wemp.getProfile() == null) { %>
								<img src="resources/images/icons8-user-beige-96.png" 
										class="rounded-circle"  
										onclick="pcomModalOpen(<%=wemp.getEmpNo() %>,'<%=a.getAprComment() %>')" />    					
    					<%	} else {  %>
		    					<img src="resources/profile_upfiles/<%=wemp.getProfile() %>" 
										class="rounded-circle"  
										onclick="pcomModalOpen(<%=wemp.getEmpNo() %>,'<%=a.getAprComment() %>')" />
    					<%  } %>
								<span><%=wemp.getDeptCode() %></span>
							    <span><%=wemp.getEmpName() %>&nbsp;<%=wemp.getJobCode() %></span>
                            <span class="aprok">결재완료</span><span><%=a.getWriteDate() %></span>
						</div>
					<% for(int i = 0; i<al.size(); i++) { %>
						<div class="approval-list">
					<%	if(i!=(al.size()-1)) {	%>
							<i class="fa fa-angle-double-right" aria-hidden="true"></i>
					<%  }	
						    if (al.get(i).getProfile() == null) { %>
								<img src="resources/images/icons8-user-beige-96.png" 
										class="rounded-circle"  
										onclick="pcomModalOpen(<%=al.get(i).getEmpNo() %>,'<%=al.get(i).getAprComment() %>')" />    					
    				<%	} else {  %>
		    					<img src="resources/profile_upfiles/<%=al.get(i).getProfile() %>" 
										class="rounded-circle"  
										onclick="pcomModalOpen(<%=al.get(i).getEmpNo() %>,'<%=al.get(i).getAprComment() %>')" />
    				<%  } %>
							<span><%=al.get(i).getDeptName()%></span>
							<span><%=al.get(i).getEmpName()%>&nbsp;<%=al.get(i).getJobName() %></span>			
						   <% if(al.get(i).getAprYN().equals("1")) { %>
                        		<span>접수</span><span><%=al.get(i).getReceiveDate() %></span>
                           <% } else if(al.get(i).getAprYN().equals("2")) { %>
                           	<span class="aprread">열람</span><span><%=al.get(i).getOpenDate() %></span>
                           <% } else if(al.get(i).getAprYN().equals("3")) {%>
                         		<span class="aprrefuse">반려</span><span><%=al.get(i).getAprDate() %></span>
                           <% } else { %>
                            	<span class="aprok">결재완료</span><span><%=al.get(i).getAprDate() %></span>
                           <% } %>							
						</div>
					<% } %>						
					</div>
					<div id="approval-state-3">참조자 열람 현황</div>
					<div id="approval-state-4">
						<% for(int i = 0; i<rl.size(); i++) { %>
						<div class="cc-list">
					<% if (rl.get(i).getProfile() == null) { %>	
								<img src="resources/images/icons8-user-beige-96.png"
								class="rounded-circle" alt="profile img" onclick="pcomModalOpen2(<%=rl.get(i).getEmpNo() %>)" />		
    				<%	} else {  %>
    							<img src="resources/profile_upfiles/<%=rl.get(i).getProfile() %>"
								class="rounded-circle" alt="profile img" onclick="pcomModalOpen2(<%=rl.get(i).getEmpNo() %>)" />	
    				<%  } %>
						
							<span><%=rl.get(i).getDeptName()%></span>
							<span><%=rl.get(i).getEmpName()%>&nbsp;<%=rl.get(i).getJobName() %></span>			
						   <% if(rl.get(i).getViewDate()!= null) { %>
                        		<span class="aprok">확인</span><span><%=rl.get(i).getViewDate() %></span>
                           <% } else { %>
                            	<span class="aprwait">대기</span><span><%=a.getWriteDate() %></span>
                           <% } %>							
						</div>
					<% } %>					
					</div>
					<button type="button" class="btn" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	
	 <!-- 프로필 및 결재 의견 확인 버튼 Modal -->
    <div class="modal fade" id="profile_aprcomment_Modal">
        <div class=" modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <img src="resources/images/icons8-user-beige-96.png" class="rounded-circle"
                        alt="profile modal img" />
                    <span></span>
                    <table class="table ">
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script>
    	function pcomModalOpen(empno, aprcom) {
    		
    		var conpath = "<%=contextPath%>";

    		if (aprcom == "null") {
    			aprcom = "결재 의견이 없습니다.";
    		}

    		$.ajax({
    			url : "sel.prc",
    			data : {empno : empno},
    			type : "get",
    			success : function(e) {
    				$("#profile_aprcomment_Modal tbody").empty();
    				$("#profile_aprcomment_Modal .modal-body>h5").remove();
    				$("#profile_aprcomment_Modal .modal-body>div").remove();

    				if (typeof e.profile == "undefined" || e.profile == "null") {
    					$("#profile_aprcomment_Modal img").attr("src", conpath+"/resources/images/icons8-user-beige-96.png");  
    				}else {
    					$("#profile_aprcomment_Modal img").attr("src", conpath+"/resources/profile_upfiles/"+e.profile);  
    				}
   
    				$("#profile_aprcomment_Modal span").text(e.empName);
    				var nickName = e.nickName;
    				if (typeof nickName == "undefined") {
    					nickName = "";
    				}
    				var phone = e.phone;
    				if (typeof phone == "undefined") {
    					phone = "";
    				}
	
    				var str = "";
    				str +=   "<tr>"
						 +		"<th>사번</th>"
						 +		"<td>"+e.empNo+"</td>"
						 +		"<th>이메일</th>"
						 +		"<td>"+e.email+"@coco.com</td>"
						 +		"</tr>"
						 +		"<tr>"
						 +		"<th>부서</th>"
						 +		"<td>"+e.deptCode+"</td>"
						 +		"<th>직급</th>"
						 +		"<td>"+e.jobCode+"</td>"
						 +		"</tr>"
						 +		"<tr>"
						 +		"<th>닉네임</th>"
						 +		"<td>"+nickName+"</td>"
						 +		"<th>성별</th>"
						 +		"<td>"+e.gender+"</td>"
						 +		"</tr>"
						 +		"<tr>"
						 +		"<th>휴대폰</th>"
						 +		"<td>"+phone+"</td>"
						 +		"<th>내선번호</th>"
						 +		"<td>"+e.tel+"</td>"
						 +		"</tr>";		
    				$("#profile_aprcomment_Modal tbody").append(str);    				
    				
    				var str = "";
    				str +=   "<h5>결재자 의견</h5>"
						 +		"<div>"+aprcom+"</div>";
    				
					$("#profile_aprcomment_Modal .modal-body").append(str);
					$("#profile_aprcomment_Modal").modal("show");
    				
    			},
    			error : function() {
    				console.log("실패");
    			},
    			complet : function() {
    				
    			}
    		});
    	
    	}	
    	
    	
	function pcomModalOpen2(empno) {
    		
    		var conpath = "<%=contextPath%>";
    		
    		$.ajax({
    			url : "sel.prc",
    			data : {empno : empno},
    			type : "get",
    			success : function(e) {
    				$("#profile_aprcomment_Modal tbody").empty();
    				$("#profile_aprcomment_Modal .modal-body>h5").remove();
    				$("#profile_aprcomment_Modal .modal-body>div").remove();
    				
    				if (typeof e.profile == "undefined" || e.profile == "null") {
    					$("#profile_aprcomment_Modal img").attr("src", conpath+"/resources/images/icons8-user-beige-96.png");  
    				}else {
    					$("#profile_aprcomment_Modal img").attr("src", conpath+"/resources/profile_upfiles/"+e.profile);  
    				}
    				$("#profile_aprcomment_Modal span").text(e.empName);
    				
    				var nickName = e.nickName;
    				if (typeof nickName == "undefined") {
    					nickName = "";
    				}
    				var phone = e.phone;
    				if (typeof phone == "undefined") {
    					phone = "";
    				}
    				
    				var str = "";
    				str +=   "<tr>"
						 +		"<th>사번</th>"
						 +		"<td>"+e.empNo+"</td>"
						 +		"<th>이메일</th>"
						 +		"<td>"+e.email+"@coco.com</td>"
						 +		"</tr>"
						 +		"<tr>"
						 +		"<th>부서</th>"
						 +		"<td>"+e.deptCode+"</td>"
						 +		"<th>직급</th>"
						 +		"<td>"+e.jobCode+"</td>"
						 +		"</tr>"
						 +		"<tr>"
						 +		"<th>닉네임</th>"
						 +		"<td>"+nickName+"</td>"
						 +		"<th>성별</th>"
						 +		"<td>"+e.gender+"</td>"
						 +		"</tr>"
						 +		"<tr>"
						 +		"<th>휴대폰</th>"
						 +		"<td>"+phone+"</td>"
						 +		"<th>내선번호</th>"
						 +		"<td>"+e.tel+"</td>"
						 +		"</tr>";		
    				$("#profile_aprcomment_Modal tbody").append(str);    				
    				$("#profile_aprcomment_Modal").modal("show");
    				
    			},
    			error : function() {
    				console.log("실패");
    			},
    			complet : function() {
    				
    			}
    		});
    	
    	}	
    </script>
</body>
</html>