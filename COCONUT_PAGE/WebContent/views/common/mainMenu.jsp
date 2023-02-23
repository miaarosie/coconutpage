<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
  <title>COCONUT PAGE</title>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/locales-all.js"></script>

  <script class="cssdesk" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js" type="text/javascript"></script>
  

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
  .header {
    height: 100px;
    width : 100%; 
    border-bottom : 1px solid black;     
    box-sizing: border-box;      
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

  .btn-primary  {
    background-color: rgb(110, 76, 68);
    border : none;
  }
  .btn-primary:hover {
    background-color: rgb(110, 76, 68)!important;
    border : none!important;

  }

  .btn:focus, .btn-primary:focus{

    box-shadow: none!important;
  }
  

  /* --------------------------------------------------------------------- */
  /* header 가운데 메뉴바 */

  #header_menu, #header_menu * {

    color: rgb(110, 76, 68);
    font-weight: 400;
    font-size: 30px;
    width : 300px;
    height : 100%;
    padding-top : 20px;
    text-align: center;
    padding-right : 100px;
  }


  

  /* 윤곽선용 */
  body * { 
    box-sizing : border-box;
    /* border : 1px solid black; */
  }

  th {
  	border-top : none!important;
  }


  /* --------------------------------------------------------------------- */
  /* mainMenu */
  #mainMenuWrap {
    width : 1720px;
    height : 850px;
    margin : 50px 40px;
    vertical-align: middle;
    background-color : rgb(244, 244, 244);
    border-radius : 10px;

  }

  .mainMenuContainer {
    /* border : 1px solid black; */
    float : left;
    /* width : 500px; */
    height : 800px;
    margin : 20px 35px;
  }
  
  #mainMenuWrap>div>div{
  	border-radius : 5px;
  }

  /* ------------------------------------------------------------------------ */
  /* maindiv_1 */

  #mainDiv_1{
    width : 589px;
  }

  #notice, #news, #calendarDiv {
    border : 1px solid black;
    width : 589px;
    /* height : 350px; */
    box-sizing: border-box;
    /* border-radius: 10px; */
    background-color: white;
  }

  #calendarDiv {

    height : 365px;
  }

  #calendarDiv * {
    box-sizing: border-box;
    
  }
  #calendarTD {
    padding-left : 3px;
    padding-right : 3px;

  }

  #notice {
    margin-bottom : 30px;
    height : 400px;
  }

  #news {
    height : 270px;
    margin-bottom : 20px;
    overflow : hidden;
  }

  /* ------------------------------------------------------------------------- */
  /* mainDiv_2 */

  #mainDiv_2 {
    width : 400px;
  }

  #post, #kanvan {
    width : 400px;
    border : 1px solid black;
    box-sizing: border-box;
    background-color: white;
  }
  #post {

    height : 400px;
    margin-bottom: 100px;;
  }

  #kanvan {

    height : 300px;
  }

  /* -------------------------------------------------------------------------- */
  /* mainDiv_3 */

  #mainDiv_3 {
    width : 500px;

  }

  #mainDiv_3>div {
    background-color: white;
    border : 1px solid black;
    width : 508px;
    box-sizing: border-box;
  }

  #approval {
    height : 230px;
    margin-bottom: 20px;

  }
  
  #news * {
  
  	width : 500px!impotant;
  }

  
     
  #forecast_embed * {
  	zoom: 70%!important;
  }
  
  #weatherApi {
  	-ms-zoom: 0.8;
        -moz-transform: scale(0.8);
        -moz-transform-origin: 0 0;
        -o-transform: scale(0.8);
        -o-transform-origin: 0 0;
        -webkit-transform: scale(0.8);
        -webkit-transform-origin: 0 0;
  }
  
  #weather {
  	height : 250px;
  }
  #weather * {
  	zoom:70%!important;
  }
      
      
      
    
    #calendar {
    	width :460px;
 		height :225px;   	
    	zoom:125%!important;
    	
    }
    
    #calendar * {
    	font-size : 8pt;
    	font-weight : 100;
    }
    
    #postArea a, #noticeArea a {
    	text-decoration: none;
      color : black;
    }
    
    .fc .fc-col-header-cell-cushion {
    	padding : 0px;
    }
    
    
      
  </style>
</head>
<body>

  <div class="wrap">
    <%@ include file="header.jsp" %>
      
    <div class="container">
        
      <%@ include file="sidebar.jsp" %>

      <!-- 실제 content 영역 -->
      <div class="main">
        <div id="mainMenuWrap">
          <div class="mainMenuContainer" id="mainDiv_1">

              <!-- 공지사항 div -->
              <div id="notice">
                  <table class="table">
                      <thead>
                          <tr><th colspan="2"><button type="button" onclick="location.href='<%=contextPath%>/noList.board?boardNo=1&currentPage=1'" class="btn btn-light">공지사항</button></th></tr>
                      </thead>
                      <tbody id="noticeArea">
                         
                       </tbody>
                  </table>
              </div>

              <!-- 업계 뉴스 크롤링 -->
              <!-- 달력 -->
              <div id="calendarDiv">
                  <table class="table">
                      <thead>
                          <tr><th><button type="button" onclick="location.href='<%=contextPath%>/calendar.ca'" class="btn btn-primary" id="app1">일정</button>
                          	
                          </th>
                          </tr>
                      </thead>
                      <tbody>
                      	<tr>
                      		<td id="calendarTD"><div id='calendar'></div></td>
                      	</tr>
                      	
                      </tbody>
                  </table>
              </div>
              
              
          </div>

          <div class="mainMenuContainer" id="mainDiv_2">
              <!-- 게시판 -->
              <div id="post">
                  <table class="table">
                      <thead>
                          <tr>
                          <th colspan="2"><button type="button" class="btn btn-primary" onclick="mainWagleAjax();" id="wagle">코코넛와글와글</button>
                              <button type="button" class="btn btn-light" onclick="mainShareAjax();" id="share">나눔해요</button>
                          </th>   
                          </tr>
                          
                      </thead>
                      <tbody id="postArea">
                          
                        </tbody>
                  </table>
              </div>

              <!-- 테이블 조절 스크립트 -->
              <script>
                  $(function() {
                      
                      $(".wrap").on("click", "#wagle", function() {

                          $(this).removeClass("btn-light").addClass("btn-primary");
                          $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                          $("#share").css("backgroundColor", "#f8f9fa").css("color","black");
                          $("#share").removeClass("btn-primary").addClass("btn-light");                            
    
                      });


                      $(".wrap").on("click", "#share", function() {

                          $(this).removeClass("btn-light").addClass("btn-primary");
                          $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                          $("#wagle").css("backgroundColor", "#f8f9fa").css("color","black");
                          $("#wagle").removeClass("btn-primary").addClass("btn-light");
                          
                      });

                  });

              </script>



              <div id="kanvan">
                  <table class="table">
                      <thead>
                          <tr><th colspan='2'><button type="button" class="btn btn-primary" onclick="mainTodoListAjax();" id="kanvan_going">예정</button>
                              <button type="button" class="btn btn-light" onclick="mainProgressListAjax();" id="kanvan_will">진행 중</button>
                          </th>   
                          </tr>
                      </thead>
                      <tbody id="kanbanArea">
                         
                      </tbody>
                  </table>
              </div>

              <!-- 테이블 조절 스크립트 -->
              <script>
                  $(function() {
                      
                      $(".wrap").on("click", "#kanvan_going", function() {

                          $(this).removeClass("btn-light").addClass("btn-primary");
                          $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                          $("#kanvan_will").css("backgroundColor", "#f8f9fa").css("color","black");
                          $("#kanvan_will").removeClass("btn-primary").addClass("btn-light");                                        
      
                      });


                      $(".wrap").on("click", "#kanvan_will", function() {

                          $(this).removeClass("btn-light").addClass("btn-primary");
                          $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                          $("#kanvan_going").css("backgroundColor", "#f8f9fa").css("color","black");
                          $("#kanvan_going").removeClass("btn-primary").addClass("btn-light");
                          
                      });

                  });

              </script>
          </div>

          <div class="mainMenuContainer" id="mainDiv_3">
              <div id="approval">
                  <table class="table">
                      <thead>
                          <tr><th colspan="2"><button type="button" class="btn btn-primary" onclick="mainAppDocReceiveAjax();" id="app_receive">받은문서함</button>
                              <button type="button" class="btn btn-light" onclick="mainAppDocSendAjax();" id="app_send">상신문서함</button>
                          </th>   
                          </tr>
                      </thead>
                      <tbody id="appArea">
                                                    
                      </tbody>
                  </table>

                  <script>
                      $(function() {
                          
                          $(".wrap").on("click", "#app_receive", function(e) {
                              e.currentTarget
                              $(this).removeClass("btn-light").addClass("btn-primary");
                              $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                              $("#app_send").css("backgroundColor", "#f8f9fa").css("color","black");
                              $("#app_send").removeClass("btn-primary").addClass("btn-light");                                        
          
                          });


                          $(".wrap").on("click", "#app_send", function() {

                              $(this).removeClass("btn-light").addClass("btn-primary");
                              $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                              $("#app_receive").css("backgroundColor", "#f8f9fa").css("color","black");
                              $("#app_receive").removeClass("btn-primary").addClass("btn-light");                                        
                              
                          });

                      });

                  </script>

              </div>


			  <div id="news">
                  <table class="table">
                      <thead>
                          <tr><th><button type="button" onclick="window.open('https://dailywebtoon.com')" class="btn btn-light">업계뉴스</button></th></tr>
                      </thead>
                      <tbody>
                          <%@ include file="newsCrawler.jsp" %>
                        </tbody>
                  </table>
              </div>

              
              
              <script>
              document.addEventListener('DOMContentLoaded', function() {
                	var calendarEl = document.getElementById('calendar');
                	var calendar = new FullCalendar.Calendar(calendarEl, {
                		initialView : 'dayGridWeek', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
                   
                		//initialDate: '2021-07-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
                		// 현재 시간 마크
                		locale: 'ko',
                		contentHeight : 500})
              	
              	
              	function selectCalendar() {
                    	$.ajax({
                    		type : "GET",
                    		url : "list.cal",
                    		data : {empNo : <%=e.getEmpNo()%>},
                    		
                    		success: function (response) {
                    			
                    			
                    			for(var i in response) {
                    				calendar.addEvent({
                    					id : response[i]["schdNo"],
                    					title : response[i]["schdTitle"],
                    					start : response[i]["startDate"],
                    					end : response[i]["endDate"],
                    					color : '#' + Math.round(Math.random() * 0xffffff).toString(16),
                    					eventClassNames: 'dataList'
                    				})
                    				
                    			}
                    			
                    			
                    		},
                    		error : function() {
                    			console.log("calendar list ajax failure");
                    		}
                    	})
                    	
                    	
                    }
                	calendar.render();
                	
                	$(function() {
                		selectCalendar();	
                	})
                	
                	
                	
              	  });
              </script>
              
              
              <div id="weather">
                     <iframe style="box-sizing: border-box;" id="weatherApi" width="900px;" height="380px;" src="https://forecast.io/embed/#lat=37.5662&lon=126.9785&name=서울&color=#6E4C44&font=arial&units=si" frameborder="0"></iframe>
              </div>
              
             
				
                 
              </div>
          </div>

        </div>
      </div>

    </div>
    
    
    <!-- 메인메뉴 ajax 파트-->
    <script>
    
    	$(function() {
    		
    		mainNoticeAjax();
    		mainAppDocReceiveAjax();
    		mainTodoListAjax();
    		mainWagleAjax();
    	})
    	var sharePostNo = 3;
    	function mainShareAjax() {
    		
    		$.ajax({
    			url : "main.share",
    			data : {pno : sharePostNo},
    			success : function (shareList) {
    				
    				var shareResult = "";
    				
    				for (var i in shareList) {
    					
    		       shareResult += "<tr>"
    						   
    						   +  	"<td width='20'>" + shareList[i].postNo + "</td>"
    						   +  	"<td><a href='<%=contextPath%>/detail.share?pno="+shareList[i].postNo+"'>" + shareList[i].postTitle + "</a></td>"
    							
    						   +  "</tr>"
    					
    				}
    				
    				$("#postArea").html(shareResult);
    				
    			}, 
    			error : function(){
    				console.log("메인메뉴 나눔 ajax failure");
    			}
    			
    		});
    	}
    	
    	var waglePostNo = 1;
		function mainWagleAjax() {
    		
    		$.ajax({
    			url : "main.wagle",
    			data : {pno : waglePostNo},
    			success : function (wagleList) {
    				
    				var wagleResult = "";
    				
    				for (var i in wagleList) {
    					
    		       wagleResult += "<tr>"
    						   
    						   +  	"<td width='20'>" + wagleList[i].postNo + "</td>"
    						   +  	"<td><a href='<%=contextPath%>/wagleDetail.po?pno="+wagleList[i].postNo+"'>" + wagleList[i].postTitle + "</a></td>"
    							
    						   +  "</tr>"
    					
    				}
    				
    				$("#postArea").html(wagleResult);
    				
    			}, 
    			error : function(){
    				console.log("메인메뉴 와글와글 ajax failure");
    			}
    			
    		});
    	}
    	
    	
    	function mainNoticeAjax() {
    		
    		var noticeType= 1;
    		$.ajax({
    			
    			url : "main.notice",
    			data : {noticeType : noticeType},
    			success : function (noticeList) {
    				var noticeResult = "";
    				
    				for (var i in noticeList) {
    					
    	    		       noticeResult += "<tr>"
    	    						   
    	    						   +  	"<td width='20'>" + noticeList[i].noticeNo + "</td>"
    	    						   +  	"<td><a href='<%=contextPath%>/det.no?nno="+noticeList[i].noticeNo+"&bno=1&cup=1'>" + noticeList[i].noticeTitle + "</a></td>"
    	    							
    	    						   +  "</tr>"
    	    					
    	    				}
    	    				
    	    				$("#noticeArea").html(noticeResult);
    	    				
    				
    			},
    			error : function() {
    				console.log("메인메뉴 공지 ajax failure");
    			}
    			
    		});
    		
    		
    	}
    	
    	function mainAppDocReceiveAjax() {
    		
    		$.ajax({
    			url : "main.receive",
    			data : { empno : <%= e.getEmpNo() %> },
    			success : function(receiveList) {
					var receiveResult = "";
    				
    				for (var i in receiveList) {
    					
    	    		       receiveResult += "<tr>"
    	    						   
    	    		       			   +	"<td width='120'>" + receiveList[i].docType + "</td>" 
    	    						   +  	"<td>" + receiveList[i].aprTitle + "</td>"
    	    						   
    	    							
    	    						   +  "</tr>"
    	    					
    	    				}
    	    				
    	    				$("#appArea").html(receiveResult);
    				
    			},
    			error : function() {
    				
    				console.log("메인메뉴 받은문서함 ajax failure");
    			}
    			
    		});
    		
    	}
    	
    	
    	function mainAppDocSendAjax() {
    		
    		$.ajax({
    			
    			url : "main.send",
    			data : { empno : <%= e.getEmpNo() %> },
    			success : function(sendList) {
    				var sendResult = "";
    				
    				for (var i in sendList) {
    					
 	    		       sendResult += "<tr>"
 	    						   
 	    		       			   +	"<td width='120'>" + sendList[i].docType + "</td>" 
 	    						   +  	"<td>" + sendList[i].aprTitle + "</td>"
 	    						   
 	    							
 	    						   +  "</tr>"
 	    					
 	    				}
 	    				
 	    				$("#appArea").html(sendResult);
    				
    			},
    			error : function() {
    				console.log("메인메뉴 상신문서함 ajax failure")
    			}
    			
    			
    		});
    		
    	}
    	
    	
    	function mainTodoListAjax() {
    		
    		$.ajax({
    			
    			url : "main.todo",
    			data : {empNo : <%=e.getEmpNo()%>},
    			success : function(toDoList) {
    				
					var toDoResult = "";
    				
    				for (var i in toDoList) {
    					
 	    		       toDoResult += "<tr>"
 	    						   
 	    		       			  +		"<td width='250'>" + toDoList[i].cardTitle + "</td>" 
 	    						  +  	"<td>" + toDoList[i].dewDate + "</td>"
 	    						   
 	    							
 	    						  +  "</tr>"
 	    					
 	    				}
 	    				
 	    				$("#kanbanArea").html(toDoResult);
    				
    			}, 
    			error : function() {
    				console.log("메인메뉴 ToDoList ajax failure");
    			}
    			
    		});
    		
    		
    	}
    	
    	function mainProgressListAjax() {
    		
    		$.ajax({
    			
    			url : "main.progress",
    			data : {empNo : <%=e.getEmpNo()%>},
    			success : function(progressList) {
    				
					var progressResult = "";
    				
    				for (var i in progressList) {
    					
 	    		       progressResult += "<tr>"
 	    						   
 	    		       			  +		"<td width='250'>" + progressList[i].cardTitle + "</td>" 
 	    						  +  	"<td>" + progressList[i].dewDate + "</td>"
 	    						   
 	    							
 	    						  +  "</tr>"
 	    					
 	    				}
 	    				
 	    				$("#kanbanArea").html(progressResult);
    				
    			}, 
    			error : function() {
    				
    				console.log("메인메뉴 ProgressList ajax failure");
    			}
    			
    		});
    		
    	}
    	
    	
    
    
    </script>
    
    
    
    
    
  

</body>
</html>