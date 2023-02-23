<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/locales-all.js"></script>

<script class="cssdesk" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js" type="text/javascript"></script>

    <style>
     html, body {    overflow: hidden;    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;    font-size: 14px;  }

     #calendar {

      height: 700px;
      width : 1250px;
      margin : 5% 10%;
      zoom:125%!important;
      
     }
     

    </style>
<title>COCONUT PAGE</title>
</head>
<body>
  <div class="wrap">
     <%@ include file="../common/header.jsp" %>
          <div class="container">
            <%@ include file="../common/sidebar.jsp" %>

                <!-- 실제 content 영역 -->

                <!-- 글쓰는창-->
                <div class="container2">
                     <div id='calendar'></div>
                     
                         
                </div>

                <script>
                
                document.addEventListener('DOMContentLoaded', function() {
                	var calendarEl = document.getElementById('calendar');
                	var calendar = new FullCalendar.Calendar(calendarEl, {
                		initialView : 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
                   		
	
                		headerToolbar : { // 헤더에 표시할 툴 바
                			start : 'prev next today',
                			center : 'title',
                			end : 'dayGridMonth,dayGridWeek'
                		},
                		titleFormat : function(date) {
                			return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
                		},
                		//initialDate: '2021-07-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
                		selectable : true, // 달력 일자 드래그 설정가능
                		droppable : true,
                		editable : true,
                		nowIndicator: true, // 현재 시간 마크
                		locale: 'ko', // 한국어 설정
                    // plugins: ['dayGrid', 'interaction'],
                    
                   
                    
                  /*
                  dateClick: function(date) {
                      var title = prompt('ww');
                      var startdate = date.dateStr;
                      calendar.addEvent({
                        title : title,
                        start: startdate,
                        allday : true
                      })
                    },
                  */
                  
                  
                  
                  
                  select: function (date) {
                    // alert(info.dateStr);
                    var title = prompt('일정명을 입력해주세요 :');
                    // title 값이 있을때, 화면에 calendar.addEvent() json형식으로 일정을 추가
                        if (title) {
                        	var startDate = moment(date.start).format('YYYY/MM/DD hh:mm');
                        	var endDate = moment(date.end).format('YYYY/MM/DD hh:mm');
                        	$.ajax({
                        		type : "GET",
                        		url : "insert.cal",
                        		data : {empNo : <%=e.getEmpNo()%>,
                        				title : title,
                        				start : startDate,
                        				end : endDate },
                        		success : function() {
                        			alert("일정등록에 성공했습니다.");
                        			
                        			window.location.reload();
                        			
                        			
                        		},
                        		error : function() {
                        			
                        			console.log("Calendar insert ajax Failure");
                        		}
                        		
                        		
                        	});
                        
                        }
                        calendar.unselect()
                      },
                      
                      
                      
                      eventClick: function(arg) {
                    	// 있는 일정 클릭시,
                    	
                    	var title = arg.event.title;  
                    	  
                        if (confirm('이 일정을 삭제하시겠습니까?')) {
                        	
                          $.ajax({
                        	  type : "GET",
                        		url : "delete.cal",
                        		data : {empNo : <%=e.getEmpNo()%>,
                        				id : arg.event.id
                        				 },
                        		success : function(result) {
                        			console.log(result);
                        			if(result > 0) {
	                          			alert("일정이 삭제되었습니다.");
	                          			arg.event.remove();    					
	                          			
                        			}
                        		}
                        	  
                          });
                          
                        } else {
                        	return false;
                        }
                      },
                      
                      eventDrop : function(arg) {
                    	  var startDate = moment(arg.event.start).format('YYYY/MM/DD HH:mm');
                    	  var endDate = moment(arg.event._instance.range.end).format('YYYY/MM/DD HH:mm');
                    	  
                    	  
                    	  var title = arg.event.title;
                    	  console.log(startDate);
                    	  console.log(endDate)
                    	  
                    	  
                    	  if(confirm("일정을 수정 하시겠습니까?")) {
                    		
                    		  $.ajax({
                   			  	type : "GET",
                          		url : "update.cal",
                          		data : {empNo : <%=e.getEmpNo()%>,
                          				title : title,
                          				start : startDate,
                          				end : endDate },
                          		success : function(result) {
                          			console.log(result);
                          			if(result > 0) {
	                          			alert("일정변경에 성공했습니다.");
	                          			window.location.reload();		
	                          			
                          			}
                          		},
                   				error : function() {
                           			
                           			console.log("Calendar update ajax Failure");
                           		}
                          		});
                          		
                    		  } else {
                    			  return false;
                    		  }
                    		  
                    	  }
                      
                	});
                	

                    
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
                    
                    
                    
                    $(function() {
                    	selectCalendar();
                    })
                    
                	
                	
                	calendar.render();
                });
                </script>
            </div>
  </div>
</body>
</html>
