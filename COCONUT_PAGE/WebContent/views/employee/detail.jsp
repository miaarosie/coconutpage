<%@page import="com.kh.employee.model.dao.MemberDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.employee.model.vo.Member"%>
<%@ page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.sql.Connection"%>
<%@page import= "java.sql.Date"%>
<%
int currentPage =  (int)request.getAttribute("currentPage");
String find = ((String)request.getAttribute("find") == null) ? "" : (String)request.getAttribute("find");
String option = ((String)request.getAttribute("option") == null) ? "" : (String)request.getAttribute("option");
%>

<% String directory = application.getRealPath("../common");
Connection conn = null;
int maxSize = 1024 * 1024 * 100;
String encoding = "UTT-8";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
/*전체 틀*/
#view_profile {
	/* margin: 5% 30% 0% 10%; */
	width: 75%;
	background-color: rgb(244, 244, 244);
	margin-left: 250px;
	margin-top: 50px;
	border-radius:25px;
	padding-top: 50px;
}	

/* #view_profile>div {
	position: relative;
	top: 13%;
} */
#view_profile_detail {
	height: 55%;
}

#view_profile_detail2 {
	width: 85%;
	height: 55%;
	margin-left: 90px;
}

#view_profile_btn {
	margin-left: 7%;
	width: 85%;
	height: 5%;
}

#login_mode_icon {
	width: 80px;
	height: 80px;
	border: 1px solid black;
	float: right;
	margin-top: 12%;
}

#profile_name {
	width: 300px;
	height: 80px;
	margin-top: 139px;
	font-size: 50px;
}

#view_profile_detail_table {
	
	margin: auto;

}


#view_profile_detail_table * {
	height: 70px;
	width: 245px;
	font-size: 20px;
	font-weight: normal;
	background-color: white;
	border: 2px solid lightgrey;
	text-align: center;
	margin-top: auto;
}


#view_profile_detail_table2 * {
	height: 70px;
	width: 245px;
	font-size: 20px;
	font-weight: normal;
	background-color: white;
	border: 2px solid lightgrey;
	text-align: center;
	margin-top: auto;
	margin: auto;
}

#view_profile_detail_table th {
	background-color: rgb(110, 76, 68);
	color: rgb(253, 255, 240);
	text-align: center;
}
#view_profile_detail_table2 th {
	background-color: rgb(110, 76, 68);
	color: rgb(253, 255, 240);
	text-align: center;
}

#view_profile_detail td>input {
	width: 100%;
	margin-top: 20px;
}
#view_profile_detail2 td input {
	box-sizing: border-box;
	width: 100%;
	height: 100%;
}



#emodify_Modal .modal-footer {
	padding: 20px 0;
}

#emodify_Modal .modal-footer button {
	border-radius: 5px;
	color: rgb(253, 255, 240);
   width: 90px;
	height: 40px;
	background-color:rgb(110, 76, 68);
	 border: none;
	 border-radius: 5px;
}
#emodify_Modal .modal-footer button:hover {
 	font-size: 18px;
	font-weight: 500;
	color: #3CCF4E;
}

#emodify_Modal .modal-footer .btn-secondary {
	background-color: gray;

}
#emodify_Modal .modal-footer .btn-secondary:hover {
 	font-size: 18px;
	font-weight: 500;
	color: black;
}

 #elist_btn {
  margin-top:20px;
  margin-left:191px;
  margin-bottom :50px;
   height: 50px;
   width: 90px;
   background-color: rgb(110, 76, 68);
   color: white;
   border-radius: 5px;
   border: none;
}
#elist_btn:hover {
	background-color: #553939;
	color: white;	
   	font-size: 18px;
	font-weight: 500;

}   

        
 #emod_btn {
  float:right;
  margin-top:20px;
  margin-right:191px;
   margin-bottom :50px;
   height: 50px;
   width: 125px;
   background-color: rgb(110, 76, 68);
   color: white;
   border-radius: 5px;
   border: none;
}
#emod_btn:hover {
	background-color: #553939;
	color: white;	
   	font-size: 18px;
	font-weight: 500;

}        

</style>

</head>
<body>


	<div class="wrap">

		<%@ include file="../common/header.jsp"%>
		

		<div class="container">
			<%@ include file="../common/sidebar.jsp"%>


			<div class="main">
				<div id="view_profile">
					<%
						Member m = (Member) request.getAttribute("emp");
					%>

					<form action="<%=contextPath%>/profile.up" method="post"
						enctype="multipart/form-data"
						style="margin-top: 50px; margin-bottom: 50px;">
						<input type="hidden" name="empNo" value="<%= m.getEmpNo()%>">
						<table style="margin-left: 191px;">
							<tr style="margin-top: 200px;">
								<td><img width="150" height="150" id="empImg"
								<%if(m.getProfile()==null){%>
								src="resources/images/icons8-user-brown-96.png" class="rounded-circle">
								<% }else {%>
								src="resources/profile_upfiles/<%=m.getProfile()%>" class="rounded-circle"></td>
								<% }%>
							
								<td><h1 style="font-size: 4rem; margin-left: 5px;"><%=m.getEmpName()%></h1></td>
							</tr>
							<%if((e.getEmpNo())==(m.getEmpNo())){%>
							<tr>
								<td><input type="file" name="file1" onchange="loadImg(this,1);"
									 style="width:190px;" />
									<button type="button" onclick="removeFile();">X</button>
								</td>
							</tr>
							<tr>
								<td><button type="submit" class="profile-button">등록하기</button>
								
								</td>
							<% }%>
							</tr>
						</table>
						<input type="hidden" name="currentPage" value="<%= currentPage%>">
						<input type="hidden" name="find" value="<%= find%>">
						<input type="hidden" name="option" value="<%= option%>">		
						
					</form>
						<script>

							$(function() {
							$("#empImg").click(function() {
							$("#file1").click();
							});
							});
							
							function loadImg(inputFile, num) {
							

								if(inputFile.files.length == 1) { 
									var reader = new FileReader();

									reader.readAsDataURL(inputFile.files[0]);
									
									reader.onload = function(e) {

										if (num == 1){
											$("#empImg").attr("src", e.target.result);
										}
										
									};
								} else { // 선택된 파일이 사라졌을 경우

									// 미리보기 이미지를 사라지게 하기 => src 속성에 null 대입
									if (num == 1) {
										$("#empImg").attr("src", null);
										
									}
								}
							}
						</script>
					<div id="view_profile_detail" style="margin-top: 0px;">				
						<table id="view_profile_detail_table">
							<%
									if (m != null) {
								%>
							<tr>								
								<th width="20%">사번</th>
								<td><%=m.getEmpNo()%></td>
								<th width="20%">이름</th>
								<td><%=m.getEmpName()%></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><%=m.getEmail()%></td>
								<th>닉네임</th>
								<%
								String nickName = (m.getNickName() == null) ? "미설정" : m.getNickName();
								%>
								<td><%=m.getNickName()%></td>
							</tr>
							
							<tr>
								<th>핸드폰번호</th>
								<td><%=m.getPhone()%></td>
								<th>내선번호</th>
								<td><%=m.getTel()%></td>
							</tr>
							<tr>
								<th>소속부서</th>
								<td><%=m.getDeptCode()%></td>
								<th>직급</th>
								<td><%=m.getJobCode()%></td>
							</tr>
					<%if(e.getManagerYN().equals("Y")){%>
							<tr>
								<th>비밀번호</th>
								<td><%=m.getEmpPwd()%></td>
								<th>관리자여부</th>
								<td><%=m.getManagerYn()%></td>
							</tr>
							<tr>
								<th>입사일</th>
								<td><%=m.getEnrollDate()%></td>
								<th>퇴사일</th>			
									<%String inOfficeYN=(m.getLeaveDate()==null) ?"재직중":String.valueOf(m.getLeaveDate());%>
									<td><%=inOfficeYN%></td>
							</tr>
							<%
							}
							%>
							
							<%
							}
							%>
						</table>
					</div>
					<button type="button" onclick="location.href='<%= contextPath %>/emplist.do?currentPage=<%= currentPage %>&find=<%=find %>&option=<%=option %>';" id="elist_btn">목록</button>
					
				<%if(e.getManagerYN().equals("Y")){%>
				<button type="button" id="emod_btn"  data-toggle="modal"
				data-target="#emodify_Modal">사원정보수정</button>
				
				<%}%>
					
					
				</div>
			</div>
		</div> 
	</div>

		
		<!-- 사원정보 수정 모달 -->
		<div class="modal fade" id="emodify_Modal">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">
					<!-- Modal body -->
					<div class="modal-body">
						<div id="view_profile_detail2" style="margin-top: 100px; margin-bottom: 50px;">										
						<form  action="<%= contextPath %>/Modify.admin" id="modi_admin_form"  method="post">
							<input type="hidden" name="empNo" value="<%=m.getEmpNo() %>">	
							<table id="view_profile_detail_table2">
								<%
									if (m != null) {
									%>
								<tr>
									<th width="20%">사번</th>
									<td><%=m.getEmpNo()%></td>
									<th width="20%">이름</th>
									<td><%=m.getEmpName()%></td>
								</tr>
								<tr>
									<th>이메일</th>
									<td><%=m.getEmail()%></td>
									<th>닉네임</th>
									<%
									String nickName = (m.getNickName() == null) ? "미설정" : m.getNickName();
									%>
									<td><%=m.getNickName()%></td>
								</tr>
								
								<tr>
									<th>핸드폰</th>
									<td><%=m.getPhone()%></td>	
									<th>내선번호</th>
									<td><input type="text" name ="tel" value="<%=m.getTel()%>"></td>
								</tr>
								<tr>
									<th>소속부서</th>
									<%
									String deptCode = (m.getDeptCode() == null) ? "대표실" : m.getDeptCode();
									%>
									<td>
									<select name ="deptCode" required>
								<option selected value="1"><%=m.getDeptCode()%></option>
								<option value="2">웹콘텐츠 본부</option>
								<option value="16">콘텐츠 기획팀</option>
								<option value="17">콘텐츠 편집팀</option>
								<option value="15">고객서비스팀</option>
								<option value="1">경영본부</option>
								<option value="14">전략기획팀</option>
								<option value="11">인사팀</option>
								<option value="12">회계팀</option>
								<option value="13">홍보팀</option>
								<option value="3">마케팅 본부</option>
								<option value="18">퍼포먼스 마케팅팀</option>
								<option value="19">CRM 마케팅팀</option>
								<option value="20">콘텐츠 마케팅팀</option>
								<option value="4">영업본부</option>
								<option value="21">제휴 영업팀</option>
								<option value="22">페이지 영업팀</option>
								<option value="5">해외 본부</option>
								<option value="23">아시아 영업팀</option>
								<option value="24">미주영업팀</option>
								<option value="6">온라인 본부</option>
								<option value="26">온라인 개발팀</option>
								<option value="25">온라인 마케팅팀</option>
								<option value="27">온라인 기획팀</option>
							</select></td>
									<th>직급</th>
								<td>
								<select id="jobCode" name="jobCode" required>
								<option selected value="J7"><%=m.getJobCode()%></option>
								<option value="J7">사원</option>
								<option value="J6">대리</option>
								<option value="J5">과장</option>
								<option value="J4">차장</option>
								<option value="J3">팀장</option>
								<option value="J2">본부장</option>
								</select></td>
								</tr>
								<tr>
									<th>비밀번호</th>
									<td><input type="text" name = "empPwd" value="<%=m.getEmpPwd()%>"></td>
									<th>관리자여부</th>
									<td>
									<select name="managerYN" required>
									<option selected value="N"><%=m.getManagerYn()%></option>		
									<option value="Y">Y</option>
									<option value="N">N</option>
									</select>
									</td>									
								</tr>
								<tr>
									<th>입사일</th>
									<td><%=m.getEnrollDate()%></td>
									<th>퇴사일</th>
									<td><input type="Date" name = "leaveDate" value="<%=m.getLeaveDate()%>"></td>
								</tr>
								<%
								}
								%>

							</table>
						<input type="hidden" name="currentPage" value="<%= currentPage%>">
						<input type="hidden" name="find" value="<%= find%>">
						<input type="hidden" name="option" value="<%= option%>">		
							<!-- Modal footer -->					
							<div class="modal-footer">
							<button type="submit" >정보수정</button>
							<button type="submit"  onclick="return empresign()">퇴사처리</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal" >취소</button>
							</div>	
						</form>
		
						</div>	
				
					</div>
			
				</div>
		</div>
		
		<script>
		function empresign(type) {
				if($("input[name=leaveDate]").val() == "") {
					alert("퇴사일을 입력해주세요.");
					return false;
				}else {
					$("#modi_admin_form").attr("action", "<%=contextPath%>/resign.do");  		
				}		
		}		
		
		function removeFile() {
			 $("input[name=file1]").val("");
			 $("#empImg").attr("src", "resources/images/icons8-user-brown-96.png");
		}
		</script>

	
</body>
</html>