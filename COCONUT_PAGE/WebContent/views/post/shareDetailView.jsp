<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.post.model.vo.*, java.util.ArrayList" %>    
    
<%
	Share s = (Share)request.getAttribute("s");
	ArrayList<ShareAttachment> list = (ArrayList<ShareAttachment>)request.getAttribute("list");
	Like l = (Like)request.getAttribute("l");
%>    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->

    <title>COCONUT PAGE</title>
    

    <style>
       

        .share_container {

            width : 1000px;
            background-color: rgb(244, 244,244);
            margin : auto;
            margin-top : 50px;
            height : auto;
            border-radius: 7px;
            padding-bottom:100px;
            margin-bottom: 70px;
        }

        .share_header {
            padding-top : 25px;
            margin-left : 25px;
            margin-right : 25px;

        }
        .share_header button:hover {
            background-color: rgb(110, 76, 68);
        }

        .left_table{
            
            float:left;
        }

        /* 글씨크기 */
        .share_title {
            font-size : 50px;
        }
        .share_writer {
            font-size : 30px;
        }
        

        #share_content {
            width : 450px;
            height : 318px;
            border-radius: 10px;
        }

        #like_con, #like_con button { 
                width : 105px;
                /* height : 105px; */
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
                /* margin-top : 15px; */
                
            }

            #comwrite {
                background-color: white;
                width: 800px;
                height: 200px;
                margin: auto;
                border-radius: 10px;
                border: 3px solid rgb(244, 244, 244);
            }

            .comment {
                margin-top: 30px;
                margin-left:7%;
                margin-right :7%;
            }
            .com_div1 tr { 
                /* display: inline-block;  */
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

         	.com_div1 {
         		width : 827px;
         	}

            .com_div3 { 
                /* display: inline-block;  */
                padding: 5px; 
                border: solid 1px white; 
                background-color: white;
                font-size: 9pt; 
                height : 100px;
                width : 827px;
                margin-top: 40px;

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
            
            #commentArea tr {
            	height : 45px;
            }
            
            .commentName, .commentContent {
            
            	font-size : 12pt;
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

            <!-- 고정 사이드바 -->
           
            <%@ include file="../common/sidebar.jsp" %> 

            <!-- 실제 content 영역 -->
            <div class="main">
                <div class="share_container">

                    <div class="share_header">

                        <div class="upper">
                            <input type="button" value="목록" class="btn btn-primary btn2"  onclick="history.back()">
                            
                            
                            <% if (e.getEmpName().equals(s.getEmp_no()) || e.getManagerYN().equals("Y")) { %>
	                            <input type="button" value="삭제" onclick="deleteCon()" class="btn btn-primary btn2" style="float:right;  margin-left:25px;">
	                            <input type="button" onclick="location.href='<%=contextPath%>/updateForm.share?pno=<%=s.getPostNo() %>'" value="수정" class="btn btn-primary btn2" style="float:right;">
                            <% } %>
                        </div>
                        <br clear="both;">

                        <script>
                            function deleteCon() {
                                if(window.confirm("게시글을 삭제하시겠습니까?")) {

                                    location.href='<%=contextPath%>/delete.share?pno=<%=s.getPostNo()%>';
                                }
                                
                            }

                        </script>
                        
                        <div class="pic_container" style="margin-left: 15px;">
                            <table class="left_table" style="margin-right : 15px;">
                                <tr>
                                    <td width="450px" height="360px" colspan="3"><img src="<%=contextPath%>/<%=list.get(0).getFilePath() + list.get(0).getChangeName()%>" width="450px" height="360px" onclick="window.open(this.src)" style="cursor : pointer;"></td>
                                    
                                </tr>
                                <tr>
                                    <% for (int i = 1; i < list.size(); i++) { %>
                                    	<td width="150px" height="120px"><img src="<%=contextPath%>/<%=list.get(i).getFilePath() + list.get(i).getChangeName()%>" width="150px" height="120px" onclick="window.open(this.src)" style="cursor : pointer;"></td>
                                    <% } %>
                                </tr>
                            </table>
                            <table class="left_table" >
                                <tr>
                                    <td width="300px;" class="share_title" colspan="7"><%= s.getPostTitle() %></td>
                                </tr>
                                <tr>
                                    <td class="share_writer" colspan="2"><%=s.getEmp_no() %></td>
                                    <td colspan="5">(<%=s.getDeptName() %>) </td>
                                </tr>
                                <tr>
                                    <td width="50"><img src="resources/images/clock.png" width="30" alt=""></td>
                                    <td><%=s.getRegDate() %></td>
                                    <td width="50"><img src="resources/images/comment.png" width="33" alt=""></td>
                                    <td id='commentCount'></td>
                                    <td width="40"></td>
                                    <td width="50"><img src="resources/images/eye.png" width="33" alt=""></td>
                                    <td><%=s.getViewCount() %></td>

                                </tr>
                                <tr>
                                    <td colspan="7">
                                        <textarea name="" id="share_content" style="background-color : white;" disabled cols="30" rows="40" style="resize:none;" readonly height="300"><%=s.getPostContent() %></textarea>

                                    </td>
                                </tr>
                                

                            </table>
                        </div>
                        <br clear="both">

                        <!-- 좋아요 -->

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

                        </script>
                    


                        <!-- 댓글창-->

                        <div class="comment">
                            <div class="com_div1">
                                <table align="center" id="commentArea">
                                	<tbody>
	                                	
	                                 </tbody>
                                </table> 
                            
                                <div class="com_div3" align="center">
                                    
                                        <textarea name="" id="comment" cols="110" rows="5" name="com" placeholder="댓글창" style="resize:none; outline:none; border:none;" onkeypress="onChange();" class="txtArea" maxlength="500"></textarea><span id="count">0</span> / 500
                                        <input type="button" onclick="insertReply();" value="등록" class="btn-post" style="float: right; margin-top: 25px;">
                                    
                                    </div>    
                                    <br> 
                                    <script>
                                        var parentNo;
                                        
                                    	$(function(e) { 
                                            selectReplyList(); 
                                            countLike();
                                            
                                            setInterval(countLike, 500);
                                            
                                            
                                            
                                            
                                            
                                            
                                            $(".com_div1").on("click", ".comm", function(e){
                                            	parentNo=$(e.currentTarget).children().first().val();
                                                $(e.currentTarget).next('.nested_reply_container').toggle();
                                                
                                            })
                                            
                                            /*
	                                        var re_re = "<tr id='nested_reply_container'>"+
	                                            "<td width='90'></td>"+
	                                            "<td colspan='4'><div><textarea name='' class='nested_reply' id='nested_reply' cols='80' rows='5' name='com' placeholder='대댓글남기기' style='resize:none; outline:none; border:none;' onkeypress='onChange();' class='txtArea' maxlength='500'></textarea></td>"+
	                                            "<td><input type='button' value='등록' class='btn-primary' onclick='insertNestedReply();' style='float: right; width:45px; font-size:10pt; border-radius:4px; height:28px; margin-top: 25px;'>"+
	                                            "</div></td></tr>"
	
	                                            $(".com_div1").on("click",".comm", function(e) {
	                                                $(e.currentTarget).after(re_re);
                                                    parentNo=$(e.currentTarget).children().first().val();

	                                            });
	                                            
                                            */
	                                        
                                        })

                                        function insertLike() {
                                    		
                                    		$.ajax({
                                    			
                                    			url : "linsert.po",
                                    			data : { pno : <%= s.getPostNo() %> },
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
                                    			
                                    			url : "ldelete.po",
                                    			data : { pno : <%= s.getPostNo() %>},
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
                                    			
                                    			url : "likecount.po",
                                    			data : {pno : <%=s.getPostNo()%>},
                                    			success : function (result) {
                                    				
                                    				$("#countLike").text(result);
                                    				
                                    			},
                                    			error : function() {
                                    				console.log("좋아요 count ajax failure");
                                    			}
                                    			
                                    		});
                                    	}
                                    
                                    	function insertReply() {

                                            $.ajax({
                                                url : "rinsert.po",
                                                data : { 
	                                                content : $("#comment").val(),
                                                	pno : <%=s.getPostNo()%>   // 수정하셔야되요!
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
                                    			url : "rlist.po", 
                                    			data : {pno : <%=s.getPostNo()%>},  // 수정하셔야되요!
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
		                                                                    <% if (e.getEmpName().equals(s.getEmp_no()) || e.getManagerYN().equals("Y")) { %>
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
	   	                                                         			<% if (e.getEmpName().equals(s.getEmp_no()) || e.getManagerYN().equals("Y")) { %>
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
                                    			url : "nrinsert.po",
                                    			data : {
                                    				pno : <%=s.getPostNo()%>,
                                    				parent : parentNo,
                                    				nestContent : $(".nested_reply"+parentNo).val()
                                    			},
                                    			type : "post",
                                    			success : function(result) {
                                    				
                                    				if(result > 0) {
                                    					selectReplyList();
                                    					
                                    				}
                                    			},
                                    			error : function() {
                                    				console.log("대댓글 작성 ajax failure");
                                    			}
                                    			
                                    		});
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

                                        
                                    

                                        
                                        function onChange() {
                                            var key = window.event.keyCode;
                                            
                                            // enter키 처리
                                            if (key === 13) {
                                                document.getElementsByClassName("txtArea").value = document.getElementsByClassName("txtArea").value + "<br>*";
                                                
                                                return false;
                                            }
                                            else {
                                                return true;
                                            }
            
                                        }
                                        $(function() {
					
                                            $("#comment").keyup(function() {

                                                $("#count").text($(this).val().length);
                                            });

                                        });
                            


                                    </script>
                            </div>           
                        </div>  
                        
                    </div>


                </div>
            </div>
            
	<script>
    $(function() {
       $("#header_menu").html("<div><a href='<%=contextPath%>/wagleList.po?currentPage=1'>코코넛 와글와글</a></div>"+
                               "<div><a href='<%=contextPath%>/thingsList.po?currentPage=1' >경조사</a></div>"+
                               "<div><a href='<%=contextPath%>/list.share?currentPage=1'  style='color:#20c997'>나눔해요</a></div>");
	});


 </script>
            

</div>
</body>
</html>