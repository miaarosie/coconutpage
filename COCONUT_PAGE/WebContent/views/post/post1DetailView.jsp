<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.post.model.vo.*"%>
    
<%
	Post1 p = (Post1)request.getAttribute("p");
	Post1File pf = (Post1File)request.getAttribute("pf");
	Like l = (Like)request.getAttribute("l");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COCONUT PAGE</title>
<style>
    #thingsdetail {
        border-radius: 7px;
        padding: 100px;
        width: 1000px;
        height: auto+100px;
        margin-top: 70px;
        margin-left: 425px;
        background-color: rgb(244, 244, 244);
        margin-bottom: 70px;
        padding-top : 50px;
        overflow:hidden;
    }

    .contentarea {
        background-color : white;
        padding: 5px;
        border-radius: 10px;
        border: 3px transparent;
        width: 800px;
        height : 700px;
    }
            
    .comment {
     margin-top: 30px;
     width : 800px;
    }

    
    .com_div1 tr { 
        padding: 5px; 
        border: solid 1px white; 
        background-color: white; 
        font-size: 9pt; 
        min-height : 30px; 
        height : auto;
        width : 800px; 
        margin : auto;
        box-sizing : border-box;
    }
    .com_div3 { 
        display: inline-block; 
        padding: 5px; 
        border: solid 1px white; 
        background-color: white;
        font-size: 9pt; 
        height : 100px;
        width : 800px;
        margin-top: 40px;
    }

	.com { 
        background-color: white; 
        width: 100px; 
        padding: 3px; 
        font-size: 9pt; 
        border : solid 1px white;
	}
  
    .btn-post { 
        background-color: rgb(110, 76, 68); 
        color : white; 
        width: 45px; 
        padding: 3px; 
        border: solid 1px white;  
        font-size: 10pt; 
        border-radius: 4px;
        margin-top: 5px;
    }
            
    .btn-man { 
        background-color: rgb(110, 76, 68); 
        color : white; 
        width: 65px; 
        height: 40px;
        padding: 3px; 
        border: solid 2px white;  
        font-size: 15pt; 
        border-radius: 4px;
        text-align : center;
    }

    .upper a:hover {
        text-decoration: none !important;
        color : white;
    }

    #like_con, #like_con button { 
        width : 105px;
        margin : auto;
        background-color: white;
        border-radius: 10px;
        color :rgb(110, 76, 68);
        font-size: large;
        font-weight: bold;
        text-align: center;
    }
            
    #like_con img, #like_con button {
        display: block;
        margin: auto;
        background-color: white;
    }
   
    
    #commentArea tr {
        height : 45px;
    }
            
    .commentName, .commentContent {
        font-size : 12pt;
    }

    #things_content {
        border : none;
        width : 100%;
        text-align: center;
        background-color : white;

    }
    .txtArea {
        height : 90%;
        width : 85%;
    }

    .main {
    	overflow:auto;
    }

                
</style>
</head>
<body>
	<div class="wrap">
		<%@ include file="../common/header.jsp" %>
		
		<div class="container">
			<%@ include file="../common/sidebar.jsp" %>
			
			<div class="main">
				<div id="thingsdetail">
				
                    <div class="upper">
                        <input type="button" value="목록" class="btn-man" onclick="location.href='<%= contextPath %>/wagleList.po?currentPage=1';" style="float : left">
	                    <% if(e.getEmpName().equals(p.getEmpNo())) { %>
		                    <a href="<%= contextPath %>/wagleUpdateForm.po?pno=<%=p.getPostNo() %>" class="btn-man" style="float : right">수정</a>
		                    <a href="#" class="btn-man" style="float : right" data-toggle="modal" data-target="#deleteForm">삭제</a>
						<% } %>
                    </div>
                    <br clear="both"><br>
                    <h3><%= p.getPostTitle() %></h3>
                    </br>
                    <table width="100%">
                    	<td width="5%"><img src="resources/images/writer.png" width="25" alt=""></td>
                    	<td width="30%"><%= p.getEmpNo() %>(<%= p.getDeptName() %>)</td>
                        <td width="5%"><img src="resources/images/clock.png" width="30" alt=""></td>
                        <td width="21%"><%= p.getRegDate() %></td>
                        <td width="7%"><img src="resources/images/comment.png" width="33" alt="">&nbsp;&nbsp;&nbsp;</td>
                        <td id='commentCount' width="12%"></td>
                        <td width="6%"><img src="resources/images/eye.png" width="33" alt="">&nbsp;&nbsp;</td> 
                        <td width="14%"><%= p.getViewCount() %></td>
                    </table>
                    <br/> 
                    <br/>
                    
                    <table class="contentarea" style="text-align : center;"> 
                    	<tr>
	                    	<th><textarea id="things_content" rows="12" disabled style="resize:none;" readonly><%= p.getPostContent() %></textarea><th>
	                    </tr>
                        <tr>
                            <th>
                                <% if(pf != null) {%>
                                    <img src="<%=contextPath%>/<%= pf.getFilePath() + pf.getChangeName() %>" align="center" width="400px" >
                                <% } %>
                            </th>
                        </tr>
                    </table>
                    
                    <!--좋아요 -->
                     <br><br>
                        <div id="like_con">
                            <% if(e.getEmpNo() == l.getEmpNo()) { %>
	                            <img class="like" id="like" onclick="deleteLike();" src="<%=contextPath %>/resources/images/coco_like.png"  width="50px" alt="">
	                            <img class="like" id="unlike" onclick="insertLike();" src="<%=contextPath %>/resources/images/coco_unlike.png" style="display : none;" width="50px" alt="">
                            <% } else { %> 
	                            <img class="like" id="unlike" onclick="insertLike();" src="<%=contextPath %>/resources/images/coco_unlike.png"   width="50px" alt="">
	                            <img class="like" id="like" onclick="deleteLike();" src="<%=contextPath %>/resources/images/coco_like.png" style="display : none;" width="50px" alt="">
                            <% } %>
                            <span id="countLike"></span>
                        </div>

                        <script>
                        
                            $(function () {
                                countLike();
                                $("#like_con").on("click",".like",function(){
                                    if ($("#like").css("display") == "none") { 
                                        $("#like").show();
                                        $("#unlike").hide();
                                    } else {
                                        $("#like").hide();
                                        $("#unlike").show();
                                    }
                                });
                            })
                            
                            function insertLike() {
                               $.ajax({		
                                   url : "waglelinsert.po",
                                    	data : { pno : <%= p.getPostNo() %> },
                                    	success : function (result) {
                                    		countLike();
                                    	},
                                    	error : function() {
                                    		console.log("좋아요 insert ajax failure");
                                    	}
                               }); 		
                            }
                                    	
                            function deleteLike() {
                            	$.ajax({
                            		url : "wagleldelete.po",
                                    data : { pno : <%= p.getPostNo() %>},
                                    success : function (result) {
                                    	countLike();
                                    	},
                                    error : function() {
                                    	console.log("좋아요 delete ajax failure");
                                    }
                                });
                            }
                            
                            function countLike() {
                            	$.ajax({
                            		url : "waglelikecount.po",
                            		data : {pno : <%=p.getPostNo()%>},
                            		success : function (result) {
                            			console.log(result);
                            			$("#countLike").text(result);
                            		},
                            		error : function() {
                            			console.log("좋아요 count ajax failure");
                            			}
                                });
                           }
                        </script>
                        
                        <!--댓글 -->
                        <div class="comment">
                            <div class="com_div1">
                                <table align="center" id="commentArea">
                                	<tbody>
	                                </tbody>
                                </table> 
                            
                            <div class="com_div3" align="center">
                                <textarea name="" id="comment" cols="110" rows="5" name="com" placeholder="댓글을 입력해주세요." style="resize:none; outline:none; border:none;" onkeypress="onChange();" class="txtArea" maxlength="500"></textarea>
                                <span id="count">0</span> / 500
                                <input type="button" onclick="insertReply();" value="등록" class="btn-post" style="float: right; margin-top: 25px;">
                            </div>

                        <script>
                        $(function() {
                            $("#comment").keyup(function() {
                                $("#count").text($(this).val().length);
                            });
                        });
                        
                        var parentNo;
                        
                    	$(function() { 
                            selectReplyList(); 
                            countLike();
                            
                            setInterval(countLike, 500);
                           
                            $(".com_div1").on("click",".comm", function(e) {
                   		       	$(e.currentTarget).next('.nested_reply_container').toggle();
                                parentNo=$(e.currentTarget).children().first().val();
                            });
                           
                        });
                        
                        function insertReply() {
                    		$.ajax({
                    			url : "walgeRinsert.po",
                    			data : {
                    				content : $("#comment").val(),
                    				pno : <%=p.getPostNo()%>
                    			},
                    			type : "post",
                    			success : function (result) {
                    				if(result>0) {
                    					selectReplyList();
                    					$("#comment").val("");
                    				}
                    			},
                    			error : function() {
                    				colsole.log("댓글작성 ajax failure")
                    			}
                    		});
                    	}
                    	
                    	function selectReplyList() {
                    		
                    		$.ajax({
                    			url : "wagleRlist.po", 
                    			data : {pno : <%=p.getPostNo()%>},
                    			success : function(list) {
                    				var result = "";

                                    $("#commentCount").text(list.length)
                    				for(var i in list) {
                    					if(list[i].parentReply == "0") {
                    						result += "<tr class='comm comm"+ list[i].replyNo +"'>"
                    				 	    + 		"<input type='hidden' value='"+ list[i].replyNo +"'>"
                    				 	    +		"<td width='220' colspan='2' class='commentName'  style='padding-left : 5px;' >" + list[i].empNo+"(" + list[i].deptName + ")</td>"
                                            //+       "<td width='150' colspan='2'>(" + list[i].deptName + "ㅇㅇ)</td>"
                                            +       "<td width='450px' style='overflow-y:visible;' class='commentContent'>" + list[i].replyContent + "</td>"
                                            +		"<td width='70'>작성시간 :</td>"
                                            +       "<td width='80'>" + list[i].regDate + "</td>"
                                                    <% if (e.getEmpName().equals(p.getEmpNo()) || e.getManagerYN().equals("Y")) { %>
                                            +            "<td><input type='button' value='삭제' class='btn-post' style='float: right' onclick='deleteReply("+ list[i].replyNo +")'></td>"
                                               		<% } %>
                                            + "</tr>"
                                            + "<tr class='nested_reply_container' style='display:none;'>"+
                                            
                                            "<td colspan='100%' align='center'><textarea cols='80' rows='5' name='com' placeholder='대댓글남기기' style='resize:none; outline:none; border:none;' onkeypress='onChange();' class='txtArea nested_reply"+ list[i].replyNo +"' maxlength='500'></textarea>"+
                                            "<input type='button' value='등록' class='btn-primary' onclick='insertNestedReply();' style='float: right; width:45px; font-size:10pt; border-radius:4px; height:28px; margin-top: 25px;'>"+
                                            "</tr>"
            					} else {
									
                       				 result += "<tr class='comm'>"
                       				 	    + 		"<input type='hidden' value='"+ list[i].replyNo +"'>"
                       				 	    +		"<td width='30' style='padding-left:20px;'>ㄴ</td>"
                           				 	+		"<td width='220' class='commentName' style='padding-left : 5px;' >" + list[i].empNo+"(" + list[i].deptName + ")</td>"
                                               // +       "<td width='150'>(" + list[i].deptName + "ㅇㅇ)</td>"
                                               +       "<td width='450px' style='overflow-y:visible;' class='commentContent'>" + list[i].replyContent + "</td>"
                                               +		"<td width='70'>작성시간 :</td>"
                                               +       "<td width='80'>" + list[i].regDate + "</td>"
                                            			<% if (e.getEmpName().equals(p.getEmpNo()) || e.getManagerYN().equals("Y")) { %>
                                            +         "<td><input type='button' value='삭제' class='btn-post' style='float: right' onclick='deleteReply("+ list[i].replyNo +")'></td>"
                                               		<% } %>
                                               + "</tr>"
                    					}
                    				}
                    				
                    				$(".com_div1>table tbody").html(result);
                    			},
                    		
                    			error : function () {
                    				console.log("댓글리스트 조회 ajax failure");
                    			}
                    		});
                    	}
                    	
                    	function insertNestedReply() {
                    		
                    		$.ajax({
                    			url : "wagleNRinsert.po",
                    			data : {
                    				pno : <%=p.getPostNo()%>,
                    				parent : parentNo,
                    				nestContent : $(".nested_reply"+parentNo).val()
                    			},
                    			type : "post",
                    			success : function(result) {
                    				
                    				if(result > 0) {
                    					selectReplyList();
                    					$("#nested_reply_container").remove();
                    				}
                    			},
                    			error : function() {
                    				console.log("대댓글 작성 ajax failure");
                    			}
                    		});
                    	}
                    	
                        function onChange() {
                            var key = window.event.keyCode;
                            // enter키 처리
                            if (key === 13) {
                                document.getElementsByClassName("txtArea").value = document.getElementsByClassName("txtArea").value + "\n*";
                                return false;
                            }
                            else {
                                return true;
                            }
                        }

                        function deleteReply(rNo) {
                        	
                    		var replyNo = rNo
                    	 
                        	if(window.confirm("정말로 삭제하시겠습니까?")) {
                        				                                       		
                        		 	
                                     $.ajax({
                                         url : "deleteReply.po",
                                         data : {
                                             rno : replyNo
                                         },
                                         success : function (result) {
                                             
                                         	if(result > 0) {

                                                 selectReplyList();
                                             }

                                         },
                                         error: function() {
                                             console.log("댓글 delete ajax failure");
                                         }

                                     });

                                }
                    	 
                    	
                    	}
                        </script>

                                <!--게시글삭제 모달창-->
                                <!-- The Modal -->
                                <div class="modal" id="deleteForm">
                                    <div class="modal-dialog">
                                    <div class="modal-content">
                                        <!-- Modal Header -->
                                        <div class="modal-header">
                                        <h4 class="modal-title">게시글삭제</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </div>
                                
                                        <!-- Modal body -->
                                        <div class="modal-body">
                                            <b>게시글을 삭제하시겠습니까?<br>
                                                <form action="<%= contextPath %>/wagleDelete.po?pno=<%= p.getPostNo() %>" method="post">
                                                    <button type="submit" class="btn-man" style="float: right">확인</button>
                                                </form>
                                            </b>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                            </div>
	                    </div>
	
			
<script>
    $(function() {
       $("#header_menu").html("<div><a href='<%=contextPath%>/wagleList.po?currentPage=1' style='color:#20c997'>코코넛 와글와글</a></div>"+
                               "<div><a href='<%=contextPath%>/thingsList.po?currentPage=1'>경조사</a></div>"+
                               "<div><a href='<%=contextPath%>/list.share?currentPage=1'>나눔해요</a></div>");
	});


 </script>
	            </div>
            </div>
         </div>
    </div>



</body>
</html>