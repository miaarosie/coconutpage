<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.approval.model.vo.Approval" %>
<%
	ArrayList<Approval> sList = (ArrayList<Approval>)request.getAttribute("sList");
	ArrayList<Approval> rList = (ArrayList<Approval>)request.getAttribute("rList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
	/* --------------------------------------------------------------------- */
    /* main 영역 */
    .main {
        padding: 70px 100px;
    }

    /* --------------------------------------------------------------------- */
    /* main-top */
    #main-top {
        margin-bottom: 30px;
    }

    #main-top>input {
        width: 200px;
        height: 50px;
        font-size: 25px;
        background-color: rgb(110, 76, 68);
        color: white;
        border-radius: 5px;
        border: none;
    }

        
    /* --------------------------------------------------------------------- */
    /* main-middle */
    .main-middle input[type=button] {
        width: 200px;
        height: 40px;
        background-color: rgb(110, 76, 68);
        color: white;
        border-radius: 5px;
        border: none;
        margin-bottom: 10px;
    }

    #tb {
        width: 100%;
        text-align: center;
        vertical-align: middle;
        background-color: white;
        margin-bottom: 30px;
    }

    #tb a { color: black; }
        
    #tb>thead { background-color: lightgray; }

	#tb>tbody>tr>td :hover{
		text-decoration: underline;
		cursor: pointer;
	}

    /* --------------------------------------------------------------------- */
    /* 모달창 */
    .modal-header { padding-top: 25px;}

    #modal-body-left {
        height: 170px;
        padding: 20px;
        line-height: 40px;
        width: 90%;
        margin-left: 23px;
    }

    #modal-title, #modal-btn {
        width: 100px;
        height: 50px;
        background-color: rgb(110, 76, 68);
        color: white;
        border-radius: 5px;
        border: none;
    }

    #modal-btn-no {
        width: 100px;
        height: 50px;
        background-color: lightgray;
        border-radius: 5px;
        border: none;
    }
    #modal-body-left ul {
        list-style-type: none;
        padding-left: 40px;
    }

    #doc-title {
        height: 30px;
        width: 120px;
    }

    #modal-form>.btn {
        float: right;
        margin: 5px 20px 15px 0px;
    }

    #tb>tbody>tr:hover {
        background: rgb(244, 244, 244);
        cursor: pointer;
    }
    
</style>
</head>
<body>
	<div class="wrap">
		<%@ include file="../common/header.jsp" %>
		
		<div class="container">
			<%@ include file="../common/sidebar.jsp" %>
			
			<div class="main">
				<div id="main-top">
                    <input type="button" value="기안작성" data-toggle="modal" data-target="#myModal">
                </div>

                <div class="main-middle">
                
                    <div>
                        <input type="button" value="받은문서함" onclick="location.href='<%= contextPath %>/rcv.docs?docStmt=0&currentPage=1';">
                    </div>

                    <table id="tb" class="table rcvtb">
                        <thead>
                            <th>No</th>
                            <th>문서구분</th>
                            <th>문서번호</th>
                            <th>제목</th>
                            <th>신청일자</th>
                            <th>사원명</th>
                            <th>상태</th>
                        </thead>
    					 
                        <tbody>
                        <% if(! rList.isEmpty()) { %>
		                    <% for (int i=0; i<rList.size(); i++) { %>
		                        <tr>
		                            <td><%= i+1 %></td>
		                            <td><%= rList.get(i).getDocType() %></td>
		                            <td><a><%=  rList.get(i).getAprId() %></a></td>
		                            <td><%=  rList.get(i).getAprTitle() %></td>
		                            <td><%=  rList.get(i).getWriteDate() %></td>
		                            <td><%=  rList.get(i).getEmpNo() %></td>
		                            <td><%=  rList.get(i).getAprStatus() %></td>
		                        </tr>
		                   <% } %>
		                <% } else { %>
		                  		<tr>
                            	  <td colspan="7">현재 받은문서함이 비어있습니다.</td>
                            	</tr>		                
		                 <% } %>
                        </tbody>
                    </table>  
                </div>
               
                <div class="main-middle">

                    <div>
                        <input type="button" value="상신문서함" onclick="location.href='<%= contextPath %>/send.docs?currentPage=1';"> <!-- onclick 속성으로 함수 대입 예정 -->
                    </div>
                    
                    <table id="tb" class="table sendtb">
                        <thead>
                            <th>No</th>
                            <th>문서구분</th>
                            <th>문서번호</th>
                            <th>제목</th>
                            <th>신청일자</th>
                            <th>사원명</th>
                            <th>상태</th>
                        </thead>
    
                        <tbody>
                        <% if(! sList.isEmpty()) { %>
                            <% for (int i=0; i<sList.size(); i++) { %>
		                        <tr>
		                            <td><%= i+1 %></td>
		                            <td><%= sList.get(i).getDocType() %></td>
		                            <td><a><%=  sList.get(i).getAprId() %></a></td>
		                            <td><%=  sList.get(i).getAprTitle() %></td>
		                            <td><%=  sList.get(i).getUpdateDate() %></td>
		                            <td><%=  sList.get(i).getEmpNo() %></td>
                                    <td><%! String aprStatus = ""; %>
                                        <%switch (sList.get(i).getAprStatus()) {
                                        case "1": aprStatus = "접수"; break;
                                        case "2": aprStatus = "열람"; break;
                                        case "3": aprStatus = "반려"; break;
                                        case "4": aprStatus = "승인"; break;
                                        case "5" : aprStatus = "결재선미지정";
                                        }%>
                                        <%= aprStatus %></td>
		                        </tr>
		                   <% } %>
                       <% }else { %>
                 		<tr>
                  		  <td colspan="7">현재 받은문서함이 비어있습니다.</td>
                  		</tr>		                
            		   <% } %>
                        </tbody>
                    </table>

					
					<script>
					$(function() {				
			            $(".sendtb tbody tr").click(function() {			
			            	
							var ano = $(this).children().eq(2).text();
			            	
			            	if (ano == "") {
			            		alert(1);
			             		return false;
			             	}
			            	
			                if ($(this).children().eq(1).text() == '보고서') {				
			                	location.href = "<%= contextPath %>/rcvapr.dc?aprType=1&aprId=" + ano+"&route=2";
			                } else {				                   
			                    // 지출결의서 상세조회
			                    location.href = "<%= contextPath %>/send.spt?aprId=" + ano;
			                }
			            });
			           
			            //받은문서함 상세조회
			            $(".rcvtb tbody tr").click(function() {
			            	var ano = $(this).children().eq(2).text();
			            	
			            	if (ano == "") {
			             		return false;
			             	}
			            	
			                if ($(this).children().eq(1).text() == '보고서') {
			                	location.href = "<%= contextPath %>/rcvapr.dc?aprType=1&aprId=" + ano+"&route=1";
			                } else {
			                	location.href = "<%= contextPath %>/rcvapr.dc?aprType=2&aprId=" + ano+"&route=1";
			                }
			            });
			           
			        });
				    </script>

                </div>
				<!-- The Modal -->
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<h3>양식선택</h3>
							</div>
					
							<!-- Modal body -->
							<div id="modal-body-left">
								<h4>문서종류</h4>
								<ul>
									<li>
										<label><input type="radio" name="doc-type" checked value="보고서"> 보고서</label>
									</li>
									<li>
										<label><input type="radio" name="doc-type" value="지출결의서"> 지출결의서</label>
									</li>
								</ul>
							</div>

							<div class="modal-footer">
								<button type="button" onclick="formType();" class="btn" data-dismiss="modal" id="modal-btn">확인</button>
								<button type="button" class="btn" data-dismiss="modal" id="modal-btn-no">취소</button>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<script>
		function formType() {
			
			if ($("#modal-body-left input[name=doc-type]:checked").val() == '보고서') {				
				location.href = "<%= contextPath %>/form.rep";
			} else {
				
				location.href = "<%= contextPath %>/form.spt";
			}
		}
	</script>
	
    <script>
    $(function() {
       
            $("#header_menu").html("<div><a href='<%= contextPath%>/approvalList.ap' style='color:#20c997'>전자결재 홈</a></div>"+
                    "<div><a href='<%=contextPath%>/rcv.docs?docStmt=0&currentPage=1' >받은문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/send.docs?currentPage=1' >상신문서함</a></div>"+
                    "<div><a href='<%=contextPath%>/temp.docs?currentPage=1' >임시저장함</a></div>"                    		
                    <% if(e.getManagerYN().equals("Y")) {%>
                    +"<div><a href='<%=contextPath%>/admin.docs?currentPage=1'>전체문서조회</a></div>"
            		<%}%>
            );
 		  
    });
   </script>
	
	
</body>
</html>