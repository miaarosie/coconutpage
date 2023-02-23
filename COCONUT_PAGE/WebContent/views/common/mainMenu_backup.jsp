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

  .btn-primary {
    background-color: rgb(110, 76, 68);
    border : none;
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

  /* --------------------------------------------------------------------- */
  /* mainMenu */
  #mainMenuWrap {
    width : 1720px;
    height : 900px;
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
    margin : 50px 35px;
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
    height : 300px;
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
    width : 500px;
    box-sizing: border-box;
  }

  #approval {
    height : 300px;
    margin-bottom: 30px;

  }

  
   
  .weatherBody {
      padding: 0;
      margin: 0;
      font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
      height: 225px;
      overflow: hidden;
    }

    .fe_container {
      width: 500px;
      font-weight: 300;
      color: #333;
      margin: 0 auto;
      height: 225px;
    }
    .weatherBody.hide_daily .fe_container { min-width: 200px;}

    .fe_container a { color: #333; }

    .fe_container .fe_title {
      position: relative;
      height: 32px;
      border-bottom: 2px solid #444;
    }

    .fe_container .fe_title .fe_location {
      position: absolute;
      left: 10px;
      bottom: 6px;
      font-size: 18px;
      font-weight: bold;
    }
    .weatherBody.hide_daily .fe_container .fe_title .fe_location { font-size: 14px; bottom: 8px; }
    .weatherBody.hide_daily .fe_container .fe_title .pre { display: none; }

    .fe_container .fe_title .fe_forecast_link {
      position: absolute;
      right: 10px;
      bottom: 8px;
      font-size: 14px;
    }

    .fe_container .fe_forecast {
      margin-top: 14px;
    }

    .fe_container .fe_currently {
      float: left;
      position: relative;
      width: 35%;
      height: 100%;
      font-size: 14px;
      text-align: center;
      padding-top: 5px;
    }
    .weatherBody.hide_daily .fe_container .fe_currently { width: 100%; }

    .fe_container #fe_current_icon {
      display: inline-block;
      width: 80px;
      height: 80px;
    }

    .fe_container .fe_currently .fe_temp {
      display: inline-block;
      position: relative;
      top: -15px;
      margin-left: 5px;
      font-size: 50px;
      font-weight: bold;
      text-align: center;
      line-height: 0.65em;
    }

    .fe_container .fe_currently .fe_temp .fe_dir {
      display: block;
      position: relative;
      left: -5px;
      display: block;
      font-size: 14px;
      font-weight: normal;
    }

    .fe_container .fe_currently .fe_summary {
      font-size: 18px;
      font-weight: bold;
    }

    .fe_container .fe_currently .fe_wind {
      font-size: 14px;
    }

    .fe_container .fe_daily {
      float: left;
      display: table;
      width: 65%;
      height: 100%;
      font-size: 14px;
      text-align: center;
    }
    .weatherBody.hide_daily .fe_container .fe_daily { display: none; }

    .fe_container .fe_daily .fe_day {
      display: table-cell;
      text-align: center;
    }

    .fe_container .fe_daily .fe_day .fe_label {
      font-weight: bold;
      display: inline-block;
      width: 100%;
    }

    .fe_container .fe_daily .fe_icon {
      display: inline-block;
      width: 26px;
      height: 26px;
      margin-top: 2px;
    }

    .fe_container .fe_daily .fe_day .fe_temp_bar {
      position: relative;
      width: 20px;
      margin: 18px auto 0;
      font-size: 12px;
      border-radius: 200px;
    }

    .fe_container .fe_daily .fe_day .fe_high_temp {
      position: absolute;
      width: 100%;
      top: -16px;
      left: 2px;
    }

    .fe_container .fe_daily .fe_day .fe_low_temp {
      position: absolute;
      width: 100%;
      bottom: -16px;
      left: 2px;
    }

    .fe_alert {
      position: absolute;
      top: 205px;
      text-align: center;
      width: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }

    .fe_alert a {
      text-decoration: underline;
      text-overflow: ellipsis;
      font-style: italic;
      font-weight: bold;
      text-decoration: underline;
      color: #A00;
    }

    .fe_alert a .fe_icon {
      font-size: 20px;
    }

    .fe_loading {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      line-height: 240px;
      text-align: center;
      font-size: 18px;
      font-weight: bold;
    }

    #fe_loading_icon {
      position: relative;
      top: 15px;
      left: -5px;
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
                          <th colspan="2"><button type="button" class="btn btn-primary" id="wagle">코코넛와글와글</button>
                              <button type="button" class="btn btn-light" onclick="mainShareAjax();" id="share">나눔해요</button>
                          </th>   
                          </tr>
                          
                      </thead>
                      <tbody id="postArea">
                          <tr>
                            <td>게시글번호</td>
                            <td>게시글제목</td>
                          </tr>
                          <tr>
                            <td>게시글2</td>
                          </tr>
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
                          <tr><th><button type="button" class="btn btn-primary" id="kanvan_going">진행중</button>
                              <button type="button" class="btn btn-light" id="kanvan_will">예정</button>
                          </th>   
                          </tr>
                      </thead>
                      <tbody>
                          <tr>
                              <td>수정사항1</td>
                          </tr>
                          <tr>
                              <td>수정사항2</td>
                          </tr>
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
                          <tr><th><button type="button" class="btn btn-primary" id="app1">미결함</button>
                              <button type="button" class="btn btn-light" id="app2">진행함</button>
                          </th>   
                          </tr>
                      </thead>
                      <tbody>
                          <tr>
                              <td>결재1</td>
                          </tr>
                          <tr>
                              <td>결재2</td>
                          </tr>
                      </tbody>
                  </table>

                  <script>
                      $(function() {
                          
                          $(".wrap").on("click", "#app1", function() {

                              $(this).removeClass("btn-light").addClass("btn-primary");
                              $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                              $("#app2").css("backgroundColor", "#f8f9fa").css("color","black");
                              $("#app2").removeClass("btn-primary").addClass("btn-light");                                        
          
                          });


                          $(".wrap").on("click", "#app2", function() {

                              $(this).removeClass("btn-light").addClass("btn-primary");
                              $(this).css("backgroundColor", "rgb(110, 76, 68)").css("border", "none").css("color","white");
                              $("#app1").css("backgroundColor", "#f8f9fa").css("color","black");
                              $("#app1").removeClass("btn-primary").addClass("btn-light");
                              
                          });

                      });

                  </script>

              </div>


			  <div id="news">
                  <table class="table">
                      <thead>
                          <tr><th><button type="button" onclick="location.href='https://dailywebtoon.com'" class="btn btn-light">업계뉴스</button></th></tr>
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
              
              
              
              
              <!-- 날씨 -->
              <div id="weatherBody" style="font-family: arial;">
   				 <div id="forecast_embed" class="fe_container">
    			    <div class="fe_title" style="display: block;"> <span class="fe_location"> <span><span class="pre">Weather for
                    </span>서울</span> </span> <span class="fe_forecast_link"> <span class="pre">More at </span><a
                 	   target="_blank" href="https://www.weather.go.kr/w/index.do">더 많은 날씨 정보</a> </span> </div>
     	   <div class="fe_forecast">
          	  <div class="fe_currently"> <canvas id="fe_current_icon" width="160" height="160"
                    style="width:80px; height:80px"></canvas>
                <div class="fe_temp">10° <span class="fe_dir">and rising</span></div>
                <div class="fe_summary">Clear</div>
                <div class="fe_wind">Wind: 3 m/s (NW)</div>
            </div>
            <div class="fe_daily">
                <div class="fe_day"> <span class="fe_label">Today</span> <canvas class="fe_icon" width="52" height="52"
                        style="width:26px; height:26px" id="fe_day_icon0"></canvas>
                    <div class="fe_temp_bar"
                        style="height: 55.5027px; top: 20.609px; background-color: rgb(246, 168, 166);"> <span
                            class="fe_high_temp">16°</span> <span class="fe_low_temp">6°</span> </div>
                </div>
                <div class="fe_day"> <span class="fe_label">Tue</span> <canvas class="fe_icon" width="52" height="52"
                        style="width:26px; height:26px" id="fe_day_icon1"></canvas>
                    <div class="fe_temp_bar"
                        style="height: 67.1702px; top: 14.8298px; background-color: rgb(246, 168, 166);"> <span
                            class="fe_high_temp">18°</span> <span class="fe_low_temp">5°</span> </div>
                </div>
                <div class="fe_day"> <span class="fe_label">Wed</span> <canvas class="fe_icon" width="52" height="52"
                        style="width:26px; height:26px" id="fe_day_icon2"></canvas>
                    <div class="fe_temp_bar"
                        style="height: 70.9322px; top: 10.359px; background-color: rgb(246, 168, 166);"> <span
                            class="fe_high_temp">18°</span> <span class="fe_low_temp">5°</span> </div>
                </div>
                <div class="fe_day"> <span class="fe_label">Thu</span> <canvas class="fe_icon" width="52" height="52"
                        style="width:26px; height:26px" id="fe_day_icon3"></canvas>
                    <div class="fe_temp_bar"
                        style="height: 65.5891px; top: 2.50798px; background-color: rgb(246, 168, 166);"> <span
                            class="fe_high_temp">20°</span> <span class="fe_low_temp">8°</span> </div>
                </div>
                <div class="fe_day"> <span class="fe_label">Fri</span> <canvas class="fe_icon" width="52" height="52"
                        style="width:26px; height:26px" id="fe_day_icon4"></canvas>
                    <div class="fe_temp_bar"
                        style="height: 52.613px; top: 0.763298px; background-color: rgb(246, 168, 166);"> <span
                            class="fe_high_temp">20°</span> <span class="fe_low_temp">10°</span> </div>
                </div>
                <div class="fe_day"> <span class="fe_label">Sat</span> <canvas class="fe_icon" width="52" height="52"
                        style="width:26px; height:26px" id="fe_day_icon5"></canvas>
                    <div class="fe_temp_bar"
                        style="height: 60.5731px; top: 6.10638px; background-color: rgb(246, 168, 166);"> <span
                            class="fe_high_temp">19°</span> <span class="fe_low_temp">8°</span> </div>
                </div>
                <div class="fe_day"> <span class="fe_label">Sun</span> <canvas class="fe_icon" width="52" height="52"
                        style="width:26px; height:26px" id="fe_day_icon6"></canvas>
                    <div class="fe_temp_bar"
                        style="height: 67.0066px; top: 3.87101px; background-color: rgb(246, 168, 166);"> <span
                            class="fe_high_temp">20°</span> <span class="fe_low_temp">7°</span> </div>
                </div>
            </div>
            <div style="clear:left"></div>
        </div>
        <div class="fe_alert" style="display:none"></div>
        <div class="fe_loading" style="display: none;"> <canvas id="fe_loading_icon" width="100" height="100"
                style="width:50px; height:50px"></canvas> Loading... </div>
    </div>
    <script type="text/javascript" async="" src="https://ssl.google-analytics.com/ga.js"></script><script src="/embed/embed.min.js?rel=1454000774782"></script>
  
    <script>
    window.onload = function() {
      var lat, lon, opts = {}, name
  
      // Parse hash params
      var param_strs = (''+window.location.hash).substr(1).split('&'),
          param_str, key, val,
          params = {}
  
      for(var i = param_strs.length; i--; ) {
        param_str = param_strs[i].split('=')
        if(!param_str || param_str.length != 2) continue
  
        key = $.trim(param_str[0]).toLowerCase()
        val = decodeURIComponent($.trim(param_str[1]))
  
        if(key == 'lat' || key == 'latitude')
          lat = +val
        else if(key == 'lon' || key == 'longitude')
          lon = +val
        else if(key == 'name')
          name = val
        else if(key == 'color')
          opts.color = val
        else if(key == 'text-color')
          opts.text_color = val
        else if(key == 'font')
          opts.font = val
        else if(key == 'font-face-name')
          opts.ff_name = val
        else if(key == 'font-face-url')
          opts.ff_url = val
        else if(key == 'units')
          opts.units = val.toLowerCase()
        else if(key == 'static-skycons')
          opts.static_skycons = true
        else if(key == 'hide-header')
          opts.hide_header = true
      }
  
      if(name)
        opts.title = '<span class="pre">Weather for </span>'+name
      else
        opts.title = 'Weather'
  
      if(lat == null || lon == null)
        opts.title = 'Invalid Location'
  
      if(!ForecastEmbed.unit_labels[opts.units])
        opts.units = 'us'
  
      var embed = new ForecastEmbed(opts)
      embed.elem.prependTo($('body'))
      embed.loading(true)
  
      forecast_request = $.get("/forecast?q="+lat+","+lon+"&units="+opts.units, function(f) {
        embed.build(f)
        embed.loading(false)
      })
    }
    </script>
  
    <script type="text/javascript">
  
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-27611241-6']);
      _gaq.push(['_setDomainName', 'forecast.io']);
      _gaq.push(['_trackPageview']);
  
      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();
  
    </script>
  
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
    	    						   +  	"<td><a href='<%=contextPath%>/det.no?nno="+noticeList[i].noticeNo+"'>" + noticeList[i].noticeTitle + "</a></td>"
    	    							
    	    						   +  "</tr>"
    	    					
    	    				}
    	    				
    	    				$("#noticeArea").html(noticeResult);
    	    				
    				
    			},
    			error : function() {
    				console.log("메인메뉴 공지 ajax failure");
    			}
    			
    		});
    		
    		
    	}
    	
    	
    
    
    </script>
    
    
    
    
    
  

</body>
</html>