 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date(System.currentTimeMillis());
%>   
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
        <title>COCONUT PAGE</title>
        <style>

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
    
            
            #enroll_form_tb {
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
             .profile-title {
                font-size: 20px;       
            }        
            
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
            .btn-plus{
                margin-left: 20px;
                font-size: 13pt; 
                font-weight: bold;
                
            }
            #ap-btn, #save-btn {
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

            #using-detail{
                overflow-y: scroll;
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
             input[type=file] {
                width: 220px;
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

                        <form id="enroll-form" action="<%= contextPath %>/spentForm.ex" method="post" enctype="multipart/form-data" >
    					<%@ include file="appLineModal.jsp" %>
                        <table class="table" id="enroll_form_tb">
    
                            <tr>
                                <th class="thead">신청일자 / <br> 신청자</th>
                                <td>
                                    <input type="date" name="enrollDate" id="enrollDate" value="<%= formatter.format(date) %>" disabled />
                                    <input type="text" name="userName" value="<%= e.getEmpName() %>" disabled>
                                    <input type="hidden" id="userId" name="userId" value="<%= e.getEmpNo() %>">
                                </td>
                            </tr>
    
                            <tr>
                                <th id="thead-title" >제목</th>
                                <td>
                                    <input type="text" placeholder="보고서 예시 제목" name="title" id="title" style="border:1px solid gray;" required maxlength="33">
                                </td>
                            </tr>
                            <tr>
                                <th class="thead">총사용금액</th>
                                <td>
                                <input type="number" id="spent" value="0" size="25px" style="border:1px solid gray;">
                                </td>
                            </tr>
                            
                            <tr>
                                <th class="thead" id="thead-list">파일첨부</th>
                                <td>
                                    <input type="file" name="upfile">
                                     <a onclick="removeFile();" class="btn btn-outline-secondary btn-sm" style="margin-left: 10px;">X</a>
                                </td>
                            </tr> 
    
                            <tr>
                                <th class="thead">비고</th>
                                <td>
                                    <textarea name="spentHow" id="spentHow" class="textarea2" cols="10" rows="5" style="border:1px solid gray; resize: none;" maxlength="1000" required></textarea>
                                </td>
                            </tr>
                            <tr id="table-list">
                                <th class="thead">내역</th>
                                <td>                                    
                                    <ul id="spent-list">
                                        <input type="button" value="내역 추가" id="spent-btn"  data-toggle="modal" data-target="#myModal">
                                    </ul>    
                                    <br>
                                    <br><br>
                                    <div id="pos-re">
                                        <table class="table" style="text-align:center;" id="spent-tb">
                                            <thead id="spent-tb-thead">
                                                <td>사용일자</td>
                                                <td>지출구분</td>
                                                <td>사업자명</td>
                                                <td>사업자번호</td>
                                                <td>금액</td>
                                            </thead>
        
                                            <tbody id="spentList">
                                                
                                            </tbody>
        
                                        </table>
                                    </div>
                                    <div id="pos-ab">
                                        <input type="submit" value="결재신청" id="ap-btn" onclick="return aprInsert(1)">
                                        <input type="submit" value="임시저장" id="save-btn" onclick="return aprInsert(2)">
                                    </div>                                   
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
								<img src="resources/images/icons8-user-beige-96.png" 	class="rounded-circle"  >    					
    					   <%	} else {  %>
		    					<img src="resources/profile_upfiles/<%=loginEmp.getProfile() %>" class="rounded-circle" >
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
    
        <!-- The Modal -->
        <!-- 함수부분-->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header" style="text-align: center;">
                    <h4 class="">상세내역</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    
                    
                       <!-- Modal body -->
                       <div class="modal-body">
                           <table style="margin-left: 100px;">
                               <tr>
                                   <th>일자</th>
                                   <td><input type="date" id="spentDate" name="spentDate" placeholder="사용일자" required></td>
                               </tr>
                               <tr>
                                   <th>지출구분</th>
                                   <td id="history" value="지출구분" >
                                       <select name="history" id="spentName" required>
                                           <option value="식대" style="width: 20px" selected>식대</option>
                                           <option value="교통비" style="width: 20px">교통비</option>
                                           <option value="기타" style="width: 20px">기타</option>
                                       </select>
                                   </td>
                               </tr>
                               <tr>
                                   <th>사업자명</th>
                                   <td><input type="text" id="corpName" placeholder="사업자명" name="corpName" maxlength="5" required></td>
                               
                               </tr>
                               <tr>
                                   <th>사업자번호</th>
                                   <td><input type="text" id="corpNo" placeholder="사업자번호" name="corpNo" maxlength="11" required></td>
                               
                               </tr>
                               <tr>
                                   <th>금액</th>
                                   <td><input type="text" id="spentMoney" placeholder="금액" name="spentMoney" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required></td>
                               
                               </tr>
                           </table>
                       </div>
                       <!-- Modal footer -->
                       <div class="modal-footer">
                       <button id="regibtn" onclick="return newRegisterCheck();" type="button" class="btn btn-secondary" data-dismiss="modal" style="background-color: rgb(110, 76, 68);">저장</button>
                       </div>
                </div>
            </div>
        </div>

      
    <script>        
        var result = 0;
        var count = 0;

        function newRegister(){
        
            if (count > 3) {
                alert("지출결의서 내역은 4개까지만 추가할 수 있습니다.");
                document.getElementById("regibtn").onclick = "";
                $("#spent-btn").attr("disabled", "true");

                return false;

            } else {

                count++;
            }

            var spentDate=$("#spentDate").val();
            var history=$("#history option:selected").text();
            var corpName=$("#corpName").val();
            var corpNo=$("#corpNo").val();
            var spentMoney=$("#spentMoney").val();
        
            var newTr = document.createElement("tr");
            var newTd1 = document.createElement("td");
            var newTd2= document.createElement("td");            
            var newTd3 = document.createElement("td");
            var newTd4 = document.createElement ("td");                                                                                                                               
            var newTd5 = document.createElement("td");

            newTd1.appendChild(document.createTextNode(spentDate));
            newTd2.appendChild(document.createTextNode(history));
            newTd3.appendChild(document.createTextNode(corpName));
            newTd4.appendChild(document.createTextNode(corpNo));
            newTd5.appendChild(document.createTextNode(spentMoney));

            newTr.appendChild(newTd1);
            newTr.appendChild(newTd2);
            newTr.appendChild(newTd3);
            newTr.appendChild(newTd4);
            newTr.appendChild(newTd5);
        
            $("#spentList").append(newTr);

            var input1 = document.createElement("input");
            input1.setAttribute("type", "hidden");
            input1.setAttribute("name", "spentDate"+count);
            input1.setAttribute("value", spentDate);
            input1.setAttribute("id", "spentDate"+count);

            var input2 = document.createElement("input");
            input2.setAttribute("type", "hidden");
            input2.setAttribute("name", "history"+count);
            input2.setAttribute("value", history);

            var input3 = document.createElement("input");
            input3.setAttribute("type", "hidden");
            input3.setAttribute("name", "corpName"+count);
            input3.setAttribute("value", corpName);

            var input4 = document.createElement("input");
            input4.setAttribute("type", "hidden");
            input4.setAttribute("name", "corpNo"+count);
            input4.setAttribute("value", corpNo);

            var input5 = document.createElement("input");
            input5.setAttribute("type", "hidden");
            input5.setAttribute("name", "spentMoney"+count);
            input5.setAttribute("value", spentMoney);

            $("#spentList").append(input1);
            $("#spentList").append(input2);
            $("#spentList").append(input3);
            $("#spentList").append(input4);
            $("#spentList").append(input5);

            result += parseInt(spentMoney);

            $("#spent").val(result);
			if ($("#spentList tr").length > 0) {
				
				$("#ap-btn").attr("disabled", false);
				$("#save-btn").attr("disabled", false);
			}

        }

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
				$("#enroll-form").attr("action", "<%=contextPath%>/spentForm.ts");  
			}
		}
		
		$(function() {
			
			if ($("#spentList tr").length == 0) {
				
				$("#ap-btn").attr("disabled", true);
				$("#save-btn").attr("disabled", true);
			}
		});
		
        function removeFile() {

            $("input[name=upfile]").val("");
            $("#file-name").text("");
        }
		
	</script>
	
	<script>
		function newRegisterCheck() {
			
			if (($("#spentDate").val().length == 0) || ($("#corpName").val().length == 0) || 
					($("#corpNo").val().length) == 0 || ($("#spentMoney").val().length == 0)) {
				alert("필수 입력 사항이 비어있습니다.");
				return false;
			} else {
				
			newRegister();
			}
		}
     </script>
	
	
	
     	                <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap' >전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1'  >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1'  >임시저장함</a></div>"
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'  >전체문서조회</a></div>"
            		<%}%>
            );
    });
   </script>
	
    </body>
    </html>