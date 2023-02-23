<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.employee.model.vo.Employee"
%>
<%
	ArrayList<String> deptList = (ArrayList<String>) request.getAttribute("deptList");
	ArrayList<Employee> eList = (ArrayList<Employee>) request.getAttribute("eList");
	Employee loginEmp = (Employee)session.getAttribute("loginEmp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>

    body * {
      box-sizing: border-box;
    }
    /* approval line Modal */
    #approval-Line-Modal {
      background-color: rgba(253, 255, 240, 0.7);
    }

    #approval-Line-Modal .modal-dialog {
      min-width: 1350px;
      max-width: 1350px;
    }

    #approval-Line-Modal .modal-content {
      background-color: white;
      position: relative;
      border: 1px solid #f4f4f4;
      border-radius: 5px;
    }

    /* 모달 header */

    #approval-Line-Modal .modal-header {
      padding: 10px 30px;
      border: 0;
      font-size: 20px;
      color: gray;
    }

    /* 모달 body */
    #approval-Line-Modal .modal-body {
      height: 800px;
      padding: 0px 30px;
    }

    #approval-Line-Modal .modal-body .row {
      height: 100%;
    }
    
    #approval-Line-Modal .modal-body .col-lg-4 {
      height: 100%;
      overflow: hidden;
    }
    
	#app-search-area {
      height: 8%;
      overflow: hidden;
      padding: 0 0 10px;
    }
    
    #apr_search_rst_dv {
      height: 15%;
      overflow: auto;
      display:none;
      padding: 0 0 10px;
    }
    #apr_search_result {
      height: 100%;
      overflow: auto;
      border-radius: 5px;
      border:1px solid black;
    }
    

    
    
    #coco-person-list {
      height: 81%;
      border: 1px solid black;
      border-radius: 5px;
      overflow: auto;
    }
	#person_order {
	  width: 100%;
      height: 11%;
      overflow: auto;

    }
    
	#person_order>span {
      display : inline-block;
      padding: 5px 8px;
      margin: 5px 8px;
      background-color: #6e4c44;
      border-radius: 5px;
      color: #fdfff0;
    }
    

 	 /* search bar */
    #app-search-area>div {
      float: left;
      height: 100%;
    }

    #app-search-area>div:nth-child(1) {
      width: 30%;
      margin-right: 7px;
    }

    #app-search-area>div:nth-child(2) {
      width: 58%;
      border: 1px solid black;
      border-radius: 5px 0 0 5px;
      position: relative;
      overflow: hidden;
    }
	
	#app-search-area .fa-times {
		position: absolute;
		right: 10px;
		top : 13px;
	    font-size: 20px;
	    color: black;
	    cursor: pointer;
	}

    #app-search-area>div:nth-child(3) {
      width: 10%;
      cursor: pointer;
      background-color: #6e4c44;
      text-align: center;
      border-radius: 0 5px 5px 0;
      overflow: hidden;
    }

    #app-search-area input {
      width: 90%;
      height: 100%;
      padding-left: 10px;
      outline: none;
      border: none;
    }

    #app-search-area .fa-search {
      font-size: 25px;
      line-height: 50px;
      color: #fdfff0;
    }

	/*결재선 검색 결과 css*/
	
	#apr_search_result>div {
		overflow: hidden;		
		margin:5px 0;
	}
	#apr_search_result>div>div {
		float: left;
	}
	#apr_search_result>div>div:nth-child(1) {
		padding: 5px 10px;
		text-align: right;
		width: 49%;
	}
	#apr_search_result>div>div:nth-child(2) {
		width: 51%;
	}

    /*input 은 숨겨주기*/
    #apr_search_result input {
      display: none;
    }

    /*input 바로 다음의 label*/
    #apr_search_result input+label {
      cursor: pointer;
    }

    #apr_search_result label {
      margin: 0;
      padding: 5px 10px;
    }

    #apr_search_result input:checked+label {
      background-color: #6e4c44;
      border-radius: 5px;
       padding: 5px 10px;
      color: #fdfff0;
    }
    
    #apr_search_result label:hover {
	  background-color: #7a574e;
      border-radius: 5px;
       padding: 5px 10px;
      color: #fdfff0;
	}



    /* ******* */
    /* 코코 조직도 */

    #coco-person-list {
      padding-top: 5px;
    }

    #coco-person-list>img {
      width: 30px;
      vertical-align: -5px;
      margin-left: 20px;
    }

    #coco-person-list>span {
      font-size: 25px;
      font-weight: 700;
    }

    #coco-tree,
    #coco-tree ul {
      margin: 0;
      padding: 0;
      list-style: none;
      position: relative
    }

    #coco-tree {
      margin-left: 30px;
      font-size: 20px;
    }

    #coco-tree li {
      padding: 0 1em;
      margin-bottom: 5px;
      line-height: 2em;
      color: black;
      font-weight: 700;
      position: relative;
    }
    #coco-tree>li>span {
	  cursor: pointer;
	}

    #coco-tree:before {
      content: "";
      display: block;
      width: 0;
      position: absolute;
      top: 0;
      bottom: 0;
      left: 0;
      border-left: 1px solid
    }

    #coco-tree>li:before {
      content: "";
      display: block;
      width: 12px;
      height: 0;
      border-top: 1px solid;
      margin-top: -1px;
      position: absolute;
      top: 1em;
      left: 0
    }

    #coco-tree ul {
      margin-left: 20px;
      font-size: 18px;
      display : none;
    }

    #coco-tree ul:before {
      content: "";
      display: block;
      width: 0;
      position: absolute;
      top: 0;
      bottom: 0;
      left: 0;
      border-left: 1px solid
    }

    #coco-tree>li:last-child:before {
      background: #fff;
      height: auto;
      top: 1em;
      bottom: 0
    }

    #coco-tree ul li:before {
      content: "";
      display: block;
      width: 10px;
      height: 0;
      border-top: 1px solid;
      margin-top: -1px;
      position: absolute;
      top: 1em;
      left: 0
    }
    
    #coco-tree ul li:last-child:before {
      background: #fff;
      height: auto;
      top: 1em;
      bottom: 0
    }	

    /*coco 체크박스 css*/
    /*input 은 숨겨주기*/
    #coco-tree input {
      display: none;
    }

    /*input 바로 다음의 label*/
    #coco-tree input+label {
      cursor: pointer;
    }

    #coco-tree label {
      margin: 0;
      padding: 0 5px;
    }

    #coco-tree input:checked+label {
      background-color: #6e4c44;
      border-radius: 5px;
      padding: 0 5px;
      color: #fdfff0;
    }
    
    #coco-tree label:hover:not(.colist_me) {
	  background-color: #7a574e;
      border-radius: 5px;
      padding: 0 5px;
      color: #fdfff0;
	}
	.colist_me {
      background-color: #6e4c44;
      border-radius: 5px;
      padding: 0 5px;
      color: #fdfff0;
    }
    

    /* ******* */
    /* 결재자 참조자 리스트 */
    #approval-Line-Modal .col-lg-8 {
      height: 100%;
    }

    #approval-Line-Modal .table-area {
      height: 85%;
      padding-bottom: 10px;

    }

    #approval-Line-Modal .table-div {
      border-radius: 10px;
      overflow: auto;
      border: 1px solid black;
    }

    #approval-Line-Modal .table-div:nth-child(1) {
      height: 56%;
      margin-bottom: 20px;
    }

    #approval-Line-Modal .table-div:nth-child(2) {
      height: 41%;
    }
    
	/* 스크롤바 숨기기 리스트 */
	#approval-Line-Modal .table-div {
	    -ms-overflow-style: none; /* IE and Edge */
	    scrollbar-width: none; /* Firefox */
	}
	#approval-Line-Modal .table-div::-webkit-scrollbar {
	    width: none; /* Chrome, Safari, Opera*/
	}



    #approval-Line-Modal thead {
      height: 20%;
    }
     #approval-Line-Modal tbody {
   	  height: 80%;
    }

    #approval-Line-Modal th,
    #approval-Line-Modal td {
      vertical-align: middle;
      text-align: center;
      border-bottom: 1px solid black;
      padding: 0;
    }

    #approval-Line-Modal th {
      background-color: #e6e6e6;
      padding: 10px 0;
      font-size: 20px;
      font-weight: 700;
    }

    #approval-Line-Modal td {
      padding: 10px 0;
      font-size: 20px;
      font-weight: 500;
    }

    #approval-Line-Modal .modal-body .fa-trash-o {
      color: black;
      cursor: pointer;
    }
	

    #app-textarea {
      padding-top: 15px;
      height: 15%;
    }

    #approval-Line-Modal textarea {
      resize: none;
      border-radius: 5px;
      width: 100%;
      height: 100%;
      padding: 10px;
      outline: none;

    }

    /* 모달 footer */
    #approval-Line-Modal .modal-footer {
      border: 0;
      width: 100%;
      padding: 0px 30px;
      overflow: hidden;
    }

    #approval-Line-Modal .modal-footer>div {
      width: 100%;
      margin: 15px 0;
    }

    #approval-Line-Modal .modal-footer button {
      background-color: #6e4c44;
      color: #f4f4f4;
      border: none;
      border-radius: 5px;
    }

    #app-cc-area {
      text-align: center;
    }

    #app-cc-area>button {
      padding: 10px 15px;
      margin: 10px;
      font-size: 20px;
      font-weight: 800;
    }

    #approval-Line-Modal .modal-footer .col-lg-8 {
      text-align: right;
      padding: 0;
    }

    #approval-Line-Modal .modal-footer .col-lg-8>button {
      padding: 10px 25px;
      margin-left: 10px;
      font-size: 20px;
      font-weight: 900;
    }

    /* select custom */
    .selectBox {
      position: relative;
      width: 100%;
      height: 100%;
      border-radius: 4px;
      padding-right: 10px;
      border: 2px solid black;
      overflow: hidden;
    }

    .selectBox .select {
      width: 100%;
      height: inherit;
      background: transparent;
      border: 0 none;
      outline: 0 none;
      padding: 0 5px;
      position: relative;

      box-sizing: border-box;
    }

    .selectBox .select option {
      background: white;
      color: black;
      padding: 3px 0;
      font-size: 16px;
    }

    /* ********* */
  </style>
</head>
<body>
      <!-- The Approval Line Modal -->
      <div class="modal fade" id="approval-Line-Modal"
      data-backdrop="static"
      data-keyboard="false">
        <div class="modal-dialog">
          <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
              *결재선 변경
            </div>
            <!-- Modal body -->
            <div class="modal-body">
              <div class="row">
                <div class="col-lg-4">
                  <div id="app-search-area">
                    <div class="selectBox">
                      <select name="fruits" class="select" id="aprSelbtn">
                        <option value="dept">부서명</option>
                        <option value="name" selected>사원명</option>
                      </select>
                    </div>
                    <div>
                      <input type="text" name="sch_keyword" id="sch_keyword" value="" placeholder="검색어 입력" >
                    </div>
                    <div onclick="search_person()"><i class="fa fa-search" aria-hidden="true" ></i></div>
                  </div>
                  <div id="apr_search_rst_dv">
                  <div id="apr_search_result">

                  </div>
                  </div>
                  <div id="coco-person-list">
                    <img src="resources/images/coco_like.png" alt="" />
                    <span>COCONUT PAGE</span>
                    <ul id="coco-tree">
                  <%  int countul = 1;
                  	  int countli = 1;
                  	   for(String d : deptList) { %>
	                   <li id="dept_count<%=countul%>"><span><%=d%></span>
	                   	<ul>
	                   <% for(Employee emp : eList) { 
	                 	 	 if(emp.getDeptCode().equals(d)) {	
	                 	 	 	if(emp.getEmpNo() != loginEmp.getEmpNo()) {	 %>
	                 	 	 	<li>
		                            <input type="checkbox" name="cocheck-list" id="per_count<%=countli%>" value="<%=emp.getEmpNo() %>" />
		                            <label for="per_count<%=countli%>"><%=emp.getEmpName() %> <%=emp.getJobCode() %></label>
                       			</li> 
	                 	 	 <%	}else {	%>
		                 	 	 <li>
		                            <label for="per_count<%=countli%>" class="colist_me"><%=emp.getEmpName() %> <%=emp.getJobCode() %></label>
	                       		</li> 
	                 	 	 <%	}
	                     countli+=1;
	                  		}
	                      } %>
	                    </ul>
                      </li>
                 <%  countul+=1;
              	  	 } %>
                    </ul>
                  </div>
                  <div id="person_order">                  	
                  </div>
                </div>

                <div class="col-lg-8">
                  <div class="table-area">
                    <div class="table-div">
                      <table class="table table-borderless">
                        <thead>
                          <tr>
                            <th colspan="6">결재자</th>
                          </tr>
                          <tr>
                            <th>순번</th>
                            <th style="width:230px">부서명</th>
                            <th>사원명</th>
                            <th>직급명</th>
                            <th>결재</th>
                            <th>삭제</th>
                          </tr>
                        </thead>
                        <tbody class="apr_tbody">		
                       	 <tr class="first_tr">
                            <td>1차</td>
                            <td><%=loginEmp.getDeptCode() %></td>
                            <td><%=loginEmp.getEmpName() %></td>
                            <td><%=loginEmp.getJobCode() %></td>
                            <td>결재</td>
                            <td><input type="hidden" class="ord_list" value="<%=loginEmp.getEmpNo() %>" /></td>
                          </tr>                          
                        </tbody>
                      </table>
                    </div>
                    <div class="table-div">
                      <table class="table table-borderless">
                        <thead>
                          <tr>
                            <th colspan="6">참조자</th>
                          </tr>
                          <tr>
                            <th>순번</th>
                            <th style="width:230px">부서명</th>
                            <th>사원명</th>
                            <th>직급명</th>
                            <th>결재</th>
                            <th>삭제</th>
                          </tr>
                        </thead>
                        <tbody class="cc_tbody">		                         
                        </tbody>
                      </table>
                    </div>
                  </div>
                  <div id="app-textarea">
                    <textarea name="tempcomment" maxlength="100" placeholder="상신자 의견을 입력하세요(100자이내)"></textarea>
                  </div>
                </div>
              </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
              <div class="row">
                <div id="app-cc-area" class="col-lg-4">
                  <button type="button" onclick="PersonSelect(1)">
                  	결재자추가
                  </button>
                  <button type="button" onclick="PersonSelect(2)">
              	      참조자추가
                  </button>
                </div>
                <div class="col-lg-8">
                  <button type="button" data-dismiss="modal" onclick="aprlineUpdate()">
              	      확인
                  </button>
                  <button type="button" data-dismiss="modal">
               	     취소
                  </button>
                </div>
              </div>
            </div>
        	<input type="hidden" name="aprList">
        	<input type="hidden" name="ccList">
        	<textarea name="comment" maxlength="100" style="display:none;"></textarea>
          </div>
        </div>
      </div>
	<script>
		var chkOrder = []; 
		$(document).on("change", "input[name='cocheck-list']", function() {
	  	    var value = $(this).val();              // value
	  	    var checked = $(this).prop('checked');  // checked 상태 (true, false)
	  	    var $label = $(this).next();            // find a label element
	  	 
	  	    if(checked) {
	  	    	if (chkOrder.indexOf(value) == -1) {
	  	    		chkOrder.push(value);
			  	  	$("#person_order").append("<span>"+$label.text().split(' ',1)+"</span><i class='fa fa-arrow-right' aria-hidden='true'></i>");
	  	        } else {
	  	        	alert("이미 선택한 인원입니다.");	  	
	  	        	$(this).prop('checked', false);
	  	        	return false;
	  	        }
	  	    }else {
	  	    	chkOrder.splice(chkOrder.indexOf(value),1);
  	    		var $str = $label.text().split(' ',1);
  	    		$("#person_order>span").each(function () {
  	    			if($(this).text()==$str) {
  	    				$(this).next().remove();
  	    				$(this).remove();		  	    	
  	    			}
  	    		});
	  	    }			  	     		  	    	
	  	});
		$(function() { // 자기 부서 슬라이드 다운
		$("#coco-tree>li>span").click(function() {
			if ($(this).siblings().is(":visible")) {
				$(this).siblings().slideUp();
			} else {
				$(this).siblings().slideDown();
			}
		});

		
			$("#coco-tree>li>span").each(function() {
				if ($(this).text() == "<%=loginEmp.getDeptCode()%>") {        		
        			 $(this).siblings().slideDown();
        		 }
        	 });
        });
        
        function PersonSelect(type)  {
        	if(chkOrder.length == 0) {
        	    alert("한명 이상을 체크하세요.");        		
        		return false;	
        	}
        	var overchk = false;      
        	if(type == 1 ) {
        		$("input[class=ord_list]").each(function(i, item) {
            		$.each(chkOrder, function(index, value) {  
            			if($(item).val()== value) {
            			  overchk = true;
           				  alert("중복되는 직원은 추가 할 수 없습니다..");     
           				  $("input[value="+$(item).val()+"]").click();
           				  return false;
            			}
            		});
            		if(overchk) 
                		return false;
            	}); 
        		$("input[class=ord_list2]").each(function(i, item) {
        			$.each(chkOrder, function(index, value) {  
        				if($(item).val()== value) {
              			  overchk = true;
             				  alert("참조자로 추가한 인원은 결재자가 될 수 없습니다.");     
             				  $("input[value="+$(item).val()+"]").click();
             				  return false;
              			}
        			});
        			if(overchk) 
                		return false;
        		});
        	}else {
        		$("input[class=ord_list2]").each(function(i, item) {
            		$.each(chkOrder, function(index, value) {  
            			if($(item).val()== value) {
            			  overchk = true;
           				  alert("중복되는 직원은 추가 할 수 없습니다..");     
           				  $("input[value="+$(item).val()+"]").click();
           				  return false;
            			}
            		});
            		if(overchk) 
                		return false;
            	});
        		
        		$("input[class=ord_list]").each(function(i, item) {
        			$.each(chkOrder, function(index, value) {  
        				if($(item).val()== value) {
              			  overchk = true;
             				  alert("결재자로 추가한 인원은 참조자가 될 수 없습니다.");     
             				  $("input[value="+$(item).val()+"]").click();
             				  return false;
              			}
        			});
        			if(overchk) 
                		return false;
        		});
        	}
        	
        	//전체 체크 해제
        	$('input[name="cocheck-list"]').each(function() {
        	    $(this).prop('checked', false);
        	});
        	
        	//순서 리스트 태그 전체 삭제 - span이랑 i
        	$("#person_order>*").remove();
        	
        	
        	// 검색 구역 초기화
        	$("#app-search-area").children().eq(1).children("i").remove();
        	$("#apr_search_rst_dv").slideUp();        
        	$("#apr_search_result").empty();
        	$("#sch_keyword").val(""); 			
 			$("#coco-person-list").animate({height: "81%"}, 500);
        	
         	if(overchk) {
         		chkOrder = [];
        		return false;
        	}
        	
        	var plist = chkOrder.join(" ");
        	chkOrder = [];
        	
			$.ajax({
				url : "sel.per",
				data : {plist : plist},
				type : "get",
				success : function(eList) {
					//1이면 결재자 추가 2면 참조자 추가
					if(type == 1 ) {
						var len = $(".apr_tbody>tr").length;
						var order = len+1; 
						
						$.each(eList, function(i) {
							var str = "";
							str += "<tr>"
								 +		"<td class='apr_order'>"+order+"차</td>"
								 +		"<td>"+eList[i].deptCode+"</td>"
								 +		"<td>"+eList[i].empName+"</td>"
								 +		"<td>"+eList[i].jobCode+"</td>"
								 +		"<td>결재</td>"
								 +		"<td><i class='fa fa-trash-o' aria-hidden='true' onclick=removeOrder(this,1)></i></td>"
								 +		"<input type='hidden' class='ord_list' value='"+eList[i].empNo+"'>"
								 +		"<input type='hidden' class='prof' value='"+eList[i].profile+"'>"
								 + "</tr>";
							 $(".apr_tbody").append(str);
							 order++;
						});
				  	} else {
						var len = $(".cc_tbody>tr").length;
						var order = len+1; 
						
						$.each(eList, function(i) {
							var str = "";
							str += "<tr>"
								 +		"<td>"+order+"차</td>"
								 +		"<td>"+eList[i].deptCode+"</td>"
								 +		"<td>"+eList[i].empName+"</td>"
								 +		"<td>"+eList[i].jobCode+"</td>"
								 +		"<td>결재</td>"
								 +		"<td><i class='fa fa-trash-o' aria-hidden='true' onclick=removeOrder(this,2)></i></td>"
								 +		"<input type='hidden' class='ord_list2' value='"+eList[i].empNo+"'>"
								 +		"<input type='hidden' class='prof' value='"+eList[i].profile+"'>"
								 + "</tr>";
							
							 $(".cc_tbody").append(str);
							 order++;
						});
					}
				},
				error : function() {
					console.log("실패");
				},
				complete : function() {

				}
			});
			
        }
        function removeOrder(el, type) {
        	el.parentNode.parentNode.remove();
        	if(type == 1) {
        		var len = 2;
        		$(".apr_tbody>tr").not(".first_tr").each(function () {
        			$(this).children().first().text(len+"차");
        			len++;
        		});
				
        	}else {
        		var len = 1;
				$(".cc_tbody>tr").each(function () {
					$(this).children().first().text(len+"차");
					len++;
        		});
        	}
        }
        
        function aprlineUpdate() {
        	$("textarea[name='comment']").val($("textarea[name='tempcomment']").val());
        	$("#aprline").empty();
        	$("#ccline").empty();
        	
        	var str = "";
			str += "<div class='profile-title'>결재자</div>"		
			$("#aprline").append(str);
        	
        	$(".apr_tbody>tr").not(".first_tr").each(function() {
        		var profile = "";
        		profile = $(this).children(".prof").val();

        		var img = "";
        		if (profile == "undefined") {
        			img = "<img src='resources/images/icons8-user-beige-96.png' 	class='rounded-circle'>";
        		}else {
        			img = "<img src='resources/profile_upfiles/"+profile+"' class='rounded-circle'>";
        		}
        		
				var str = "";
				str += "<div class='aprline_profile'>"
					 +		"<div>"
					 +		img
					 +		"</div>"
					 +		"<div>"
					 +		"<h6>"+$(this).children().eq(2).text()+"</h6>"
					 +		"<h6>"+$(this).children().eq(3).text()+" | "+$(this).children().eq(1).text()+"</h6>"
					 +		"<input type='hidden' class='aprline_list' value='"+$(this).children().eq(6).val()+"'>"
					 +		"</div>"
					 + "</div>";
				 $("#aprline").append(str);
			});
        	
        	var str = "";
			str += "<div class='profile-title'>참조자</div>"		
			$("#ccline").append(str);
        	
        	$(".cc_tbody>tr").each( function() {

        		var profile = "";
        		profile = $(this).children().eq(7).val();
        		
        		var img = "";
        		if (profile == "undefined") {
        			img = "<img src='resources/images/icons8-user-beige-96.png' 	class='rounded-circle'>";
        		}else {
        			img = "<img src='resources/profile_upfiles/"+profile+"' class='rounded-circle'>";
        		}
        		
				var str = "";
				str += "<div class='aprline_profile'>"
					 +		"<div>"
					 +		img
					 +		"</div>"
					 +		"<div>"
					 +		"<h6>"+$(this).children().eq(2).text()+"</h6>"
					 +		"<h6>"+$(this).children().eq(3).text()+" | "+$(this).children().eq(1).text()+"</h6>"
					 +		"<input type='hidden' class='aprline_list2' value='"+$(this).children().eq(6).val()+"'>"
					 +		"</div>"
					 + "</div>";
				 $("#ccline").append(str);
			});
        }
        
        // apr_search_result
        // coco-person-list
    
        function search_person() {
        	var keyword = $("#sch_keyword").val(); // 검색어
        	var type = $("#aprSelbtn").val();
        	
        	if(keyword == ""){
        		alert("검색어를 입력해주세요.");
        		$("#sch_keyword").focus();
        		return false;
        	}
        	$("#apr_search_result").empty();
        	$("#app-search-area").children().eq(1).children("i").remove();
        	$("#app-search-area").children().eq(1).append("<i 	class='fa fa-times' aria-hidden='true' onclick='search_exit()'></i>");             
			$("#apr_search_rst_dv").slideDown();      
			$("#coco-person-list").animate({height: "66%"}, 500);
			
        		$.ajax({
    				url : "search.apline",
    				data : {type : type,
    							keyword : keyword
    				},
    				type : "get",
    				success : function(eresult) {
    					var str = "";
    					if(eresult.length == 0) {
    						  str += "<div align='center' style='margin-top:20px;'><strong>검색 결과가 없습니다.</strong></div>";
    					}else {
    						$.each(eresult, function(i) {
  							  str += "<div>"
  									 +		"<div>"+eresult[i].deptCode+"</div>"
  									 +		"<div><input type='checkbox' name='cocheck-list' id='per_ser"+i+"' value='"+eresult[i].empNo+"' />"
  									 +		"<label for='per_ser"+i+"'>"+eresult[i].empName+" "+eresult[i].jobCode+"</label></div></div>";
  							
  							});		
    					}  
    					$("#apr_search_result").append(str);
    				},
    				error : function() {
    					console.log("실패");
    				},
    				complete : function() {

    				}
    			});
        
        }
        
        function search_exit() {
            $("#app-search-area").children().eq(1).children("i").remove();
            $("#apr_search_result").empty();
            $("#sch_keyword").val("");
			$("#apr_search_rst_dv").slideUp();        
			$("#coco-person-list").animate({height: "81%"}, 500);
       }

        $(function() {
        	  $("#sch_keyword").keydown(function() {
                  if (event.keyCode === 13) {
                      event.preventDefault();
                      search_person();
                  }
              });        	  
        });
        
      
        
	</script>
</body>
</html>