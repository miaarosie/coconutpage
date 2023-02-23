<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap, java.util.Iterator"%>
<%@ page import="com.kh.chart.model.dao.ChartDao "%>
<%
	HashMap<Integer, Integer> leaveList = (HashMap<Integer, Integer>)request.getAttribute("leaveList");
	HashMap<Integer, Integer> enrollList = (HashMap<Integer, Integer>)request.getAttribute("enrollList");

	Iterator<Integer> leaveKeys = leaveList.keySet().iterator();
	Iterator<Integer> enrollKeys = enrollList.keySet().iterator();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/emn178/chartjs-plugin-labels/src/chartjs-plugin-labels.js"></script>
<style>
        body {
            background-color: rgb(253, 255, 240);
        }
        .wrap {
            
            margin : auto;
            width : 1920px;
            height : 1080px;
            /* border: 1px solid black; */
            
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
        /* header 가운데 메뉴바 */

        #header_menu, #header_menu {
            color: rgb(110, 76, 68);
            font-weight: 400;
            font-size: 30px;
            width : 300px;
            height : 100%;
            padding-top : 20px;
            text-align: center;
        }



        /* --------------------------------------------------------------------- */
        /* header 우측 */
       
        #profile, #logout {
            height : 100px;
            /* border : 1px solid black; */
            box-sizing: border-box;
        }

        #profile {
            width : 100px;
        }

        #logout {
            width : 170px;
            text-align: center;
            padding-top :30px

        }

        /* --------------------------------------------------------------------- */
        /* 사이드 바 버튼 div */

        .side {
            width : 100%;
            height : 100px;
            /* border : 1px solid black; */
            box-sizing: border-box;
            text-align: center;
            padding-top : 10px;
            color : rgb(253, 255, 240);
        }

        /* 사이드 바 버튼 */
        .side>img {
            width: 60px;
            height : 60px;
            
        }

        

        /* 윤곽선용 */
        body { 
            box-sizing : border-box;
            /* border : 1px solid black; */
        }
        /* --------------------------------------------------------------------- */

        #header_menu {
            padding-left: 10%;
        }

        #header_menu ul {
            list-style-type: none;
            display: inline;
        }
        
        .navbar li {
            padding-top: 5px;
            width: 200px;
            float: left;
        }
        #header_menu {

            font-weight: 400;
            font-size: 30px;
            width: 300px;
            height: 100%;
            padding-top: 25px;
            text-align: center;
            padding-right: 100px;
        }

        #header_menu a {
            color:rgb(110, 76, 68);
        }

        /* --------------------------------------------------------------------- */
        /* main 영역 */
        .main {
            padding: 70px 100px;
        }

        /* --------------------------------------------------------------------- */
        /*검색 버튼 */
        #search { 
            height: 40px; 
			width: 500px;
            margin-top : 30px;
            text-align: center;
			align: center;
        }

        #search-filter, #search-text, #search-btn { height: 100%; }

        #search-filter { width: 200px; }

        #search-filter option[value=""] { display: none; }

        #search-text {
            width: 500px;
            display: inline;
        }

        #search-btn { 
            width: 80px; 
            border : 1px solid rgb(201, 201, 201);
            background-color: rgb(110, 76, 68);
            color : white;
            border-radius: 5px;
            
        }
        
        /* --------------------------------------------------------------------- */
        /* 메인 */
        .list {
            width : 80%;
            margin : auto;

        }
        #list-btn {
            width: 100%;
            padding: 10px;
        }
        #list-btn>input {
            float: right;
            height: 50px;
            width: 100px;
            background-color: rgb(110, 76, 68);
            color: white;
            border-radius: 5px;
            border: none;
            margin-bottom: 20px;
            font-size: 20px;
        }
        
        #tb {
            width: 100%;
            height: 600px;
            text-align: center;
            vertical-align: middle;
            background-color: white;
            margin-bottom: 30px;
        }
        
        #tb a { color: black; }
        
        #tb>thead { background-color: lightgray; }
        
        .pagination {
            width : 300px;
            margin : auto;
        }
        .pagination a {
            color:rgb(110, 76, 68);
        }
        #active-page {
            background-color: rgb(110, 76, 68);
            color: white;
        }


        /* 글쓰기 버튼 */
        #write_btn {
			position: relative;
			left: 95%;
            background-color: rgb(110, 76, 68);
            height: 40px;
            width: 80px;
            border-radius: 6px;
            border: rgb(110, 76, 68);
            color: white;
            margin-bottom: 8px;
			
        }

        #write_btn:hover {
            background-color: rgb(205, 162, 152);
        }
		a { text-decoration: none;
			color: white;}
		
		rect:first-child, rect:last-child {
			fill-opacity : 0.01 !important;
		}
		
		#chart_div {
			margin-top : 50px;
			margin-left : 30px;
		}

		.chart_inner {
			background-color : pink;
			cursor : pointer;
			border : 1px solid white;
		}
		
		path {
		fill-opacity : 0.8;
		border : 1px solid;
		}
		
		text {
		font-size:17px;
		}
	
		text x {
		font-size:67px;
		}

</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
	<div class="wrap">
		<%@ include file="../common/header.jsp" %>
		
		<div class="container">
			<%@ include file="../common/sidebar.jsp" %>
			
			<div class="main">
				<div id="chart_div" style="display: inline-block; align-items: center; justify-content: center; height: 100vh;">	
				<div class="chart_inner">
	 <script>
	 
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      
      // 없는달만 추려내는 역할의 선언적 함수
      function removeMonth(month) { 
    	  // month : 현재 조회데이터가 있는 달이 들어있는 배열 
    	  // 예) 1, 3월에만 조회데이터가 있다 => month = [1, 3]
    	  
			// 없는달을 추려내는과정 시작 ----------------------------------------------
			var compare = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
			
			for(var i = 0; i < compare.length; i++) {
	
				for(var j = 0 ; j < month.length; j++) {
					
					if(month[j] == compare[i]) {
						compare.splice(i, 1);
						i--;
						break;
					}
				}
			}
			
			// console.log(compare); // 없는달만 들어있음
			// 없는달을 추려내는과정 끝 ----------------------------------------------
    	  
    	  return compare;
      }
   
   // 차트 부분
   function drawChart() {
	   
	   var arr = [
	          ['월별 퇴사/입사 (명)', '퇴사자', '입사자']
	        ];
		
	   var content1 = []; // 퇴사자
	   var month = [];
		<% while(leaveKeys.hasNext()){ %>
		
			    <% int key = leaveKeys.next(); %>
			    	month.push(<%= key %>);
					content1.push([<%= key %>, <%= leaveList.get(key) %>]);
		<% } %>
		
		var compare = removeMonth(month);
		
		for(var i = 0; i < compare.length; i++) {
			
			content1.push([compare[i], 0]);
		}
		
		for(var i = 0; i < content1.length; i++) {
			
			if(content1[i][0] < 10) {
				content1[i][0] = String('0' + content1[i][0]);
			} else {
				content1[i][0] = String(content1[i][0]);
			}
		}
		// ============ 여기까지 퇴사자 리스트 만들기 ============

	    var content2 = []; // 입사자
		month = [];
		<% while(enrollKeys.hasNext()){ %>
		
			    <% int key = enrollKeys.next(); %>
			    	month.push(<%= key %>);
					content2.push([<%= key %>, <%= enrollList.get(key) %>]);
		<% } %>
		
		compare = removeMonth(month);
		
		for(var i = 0; i < compare.length; i++) {
			
			content2.push([compare[i], 0]);
		}
		
		for(var i = 0; i < content2.length; i++) {
			
			if(content2[i][0] < 10) {
				content2[i][0] = String('0' + content2[i][0]);
			} else {
				content2[i][0] = String(content2[i][0]);
			}
		}
		// ============ 여기까지 입사자 리스트 만들기 ============
		
		content1.sort();
		content2.sort();
		
		console.log(content1);
		console.log(content2);
		
		for(var i = 0; i < content1.length; i++) {
			content1[i][2] = content2[i][1];
		}
		
		console.log(content1); // [월, 퇴사자명수, 입사자명수]
		
		arr = arr.concat(content1);	
		
        var data = google.visualization.arrayToDataTable(arr);

        var options = {
           chart: {
            title: '단위(명)',
            subtitle: '기간 : 2022.01 - 2022.12',

          },
         
          bars: 'vertical',
          vAxis: {format: 'decimal'},
          height: 700,
          width: 1500,
          colors: ['#92CC7F', '#6E4C44', '#ECF0D5']
        };
        
       
        
        
        var chart = new google.charts.Bar(document.getElementById('chart_div'));
		
       
        
        chart.draw(data, google.charts.Bar.convertOptions(options));

      }
		</script>	
	<script>
	    $(function() {
	       $("#header_menu").html("<div><a href='<%=contextPath%>/emp.ch' style='color:#20c997'>사원현황</a></div>"+
	                               "<div><a href='<%=contextPath%>/appro.ch'>결재현황</a></div>");
		});
	    

		
	 </script>
				</div>
		 	</div>
		</div>
	</div>
</div>
	

</body>
</html>