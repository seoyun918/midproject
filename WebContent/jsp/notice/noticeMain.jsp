<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>So? Would You?</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="<%= request.getContextPath() %>/js/notice/notice.js"></script>
  <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%
	int type = (Integer)session.getAttribute("type");
%>
<style>
  body{
  	margin : 10px;
  	background : white;
  }

  .panel-heading{
  	padding : 15px;
  }
  .panel-default>.panel-heading {
    color: #333;
    background-color: white;
    
    color: inherit;
	text-decoration: none;
}
  .panel-title a{
	color: inherit;
	text-decoration: none;
  }
  .p1{
  	width : 80%;
  	padding : 5px;
  	margin : 3px;
  	float : left;
  }
  .p2{
  	width : 17%;
  	padding : 5px;
  	margin : 2px;
  	float : right;
  }
  .p3{
  	clear : both;
  }
  .p4{
  	
  }
  input[name=reply]{
  	height : 46px;
  	vertical-align: top;
  }
  .subsub{
  	font-weight: bold;
  }
  .subdate{
  	color: rightgray;
  	
  }
  .pager{
  	width : 100px;
  	float : left;
  	margin : 5px;
  	margin-left: 5%;
  }
  .repl{
  	border : 1px solid black;
  	background: #f1f2fa;
  	margin : 1px;
  	padding : 5px;
  }
  #modifyform{
  	display: none;
  }
  label{
  	display : inline-block;
  	width: 60px;
  	height: 30px;
  }
  wd{
 	text-align: right;
  }
  #writeMadal, #modifyModal{
  	display: none;
  }
  #write{
  	margin-left: 720px; 
  	margin-bottom: 10px;
  	background: none;
  	border : 1px solid lightgray;
  	color : #878787;
  }
  #pageList{
  	margin-left: 300px;
  }
  .pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}

.pagination a.active {
  background-color: #2C7CE6;
  color: white;
}

.pagination a:hover:not(.active) {background-color: #ddd;}

.titlename{
	font-size: 20px;
	font-weight: bold;
	line-height: 50px;
	color: #4f4f4f;
	margin-left: 10px;
}
.p2 .action{
  	margin-bottom: 10px;
  	background: none;
  	border : 1px solid lightgray;
  	color : #878787;
}
</style>
  
<script>

currentPage=1;

$(function(){
	
	<%-- alert("type??? "+ <%= type %>); --%>
	if(<%= type %> == 4){
		noticeList(currentPage);
		
	}else{
		noticeList2(currentPage);
	}
	
	//???????????????
	  $('#list').on('click', '.action', function(){
		  idx = $(this).attr('idx');
		  name = $(this).attr('name');
		  //alert(name + ", " + idx);
		  
		  
		  if(name=="modify"){
			  //alert(idx + "?????? ?????? ???????????????.");
			  //????????? - modal??? ??????
			  $('#modifyModal').modal('show');
			  
			  //list ???????????? modal?????? ????????? ?????? ????????????
			  panel = $(this).parents('.panel');
			  title = $(panel).find('a').text();  //??????
			  //wr = $(panel).find(".wr").text();  //?????????
			  //em = $(panel).find(".em").text();  //??????
			  p3 = $(panel).find(".listp3").html();  //?????? -<br>?????? ???????????? ?????? 
			  text = p3.replace(/<br>/g, "\n");

			
			  //modal?????? ????????? ?????? ??????
			  $('#modifyModal input[name=ntc_title]').val(title);
			  //$('#modifyModal input[name=writer]').val(wr);
			 // $('#modifyModal input[name=mail]').val(em);
			  $('#modifyModal textarea[name=ntc_cont]').val(text);
			  
			  $('#modifyModal input[name=ntc_num]').val(idx); //[????????? ??????????????? ??????????????????~!! ???????????????~! ]
			  
			  
			  
			  
		  }else if(name=="delete"){
			  //alert(idx + "?????? ?????? ???????????????.");
			  
			  deleteNotice(); //db?????? - ??? ????????? ??????
			  
		  }//if???

	  }) //??????????????? ??? 
	  
	  
	  //??????????????? ????????? ?????????
	  $('#pageList').on('click', '.paging', function(){
		  currentPage = $(this).text().trim();
		  noticeList(currentPage);
	  })

	  
	  
	  //????????? ?????????
	  $('#write').on('click', function(){
		  //????????? ???
		  $('#writeModal').modal('show');
		  
	  })
	  
	  //????????? ??? ?????? ??????
	  $('#send').on('click', function(){
		  //[???????????? ????????????!]
		  fdata = $('.form1').serializeArray();
		  console.log(fdata);
		  
		  //????????? ??????
		  saveNotice();
		  
	  })
	  
	  
	  //??? ?????? ??? ?????? ??????
	  $('#mod').on('click', function(){
		  //????????? ????????? ????????????
		  fdata = $('.form2').serializeArray();
		  console.log(fdata);
		  
		  modifyNotice();
		  
		  
	  })
			  
})//function

</script>
</head>

<body>

<% if(type==1){ %>
<br><br><br><br>

<%}else{ %>
<h2 class="titlename">????????????</h2> 
<%} %>


	<div id="modifyform">
		<textarea rows="5" cols="50"></textarea>
		<input type="button" value="??????" id="btnok">
		<input type="button" value="??????" id="btnreset">
	</div>



	<div id="list"></div>
<% 
switch(type){
case 4: 
%>		
	<br><br>
	<input id="write" type="button" value="?????????" >
<% 
break; 
} 
%>	
	<div id="pageList"> </div>



<!-- ????????? ??? Modal -->
<div id="writeModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">?????????</h4>
      </div>

      <div class="modal-body">
      
		<form class="form1">
		
			<label>??????</label>
			<input class="intxt" type="text" name="ntc_title">
				<br>
			
			<label>??????</label><br>
			<textarea class="intxt" name="ntc_cont" rows="10" cols="50"></textarea>
				<br>
				
			<input type="button" value="??????" id="send">
				<br>
		</form>
		
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div><!-- ????????? ??? Modal ??? -->







<!-- ????????? ?????? ??? Modal --------------------------------------------------------------------------------- -->
<div id="modifyModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">?????????</h4>
      </div>
      <div class="modal-body">
      
		<form class="form2">
			<input type="hidden" name="ntc_num">
			
			<label>??????</label>
			<input class="intxt" type="text"  name="ntc_title">
				<br>
				
			<label>??????</label><br>
			<textarea class="intxt" name="ntc_cont" rows="10" cols="50"></textarea>
				<br>
				
			<input type="button" value="??????" id="mod">
				<br>
		</form>
		
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div><!-- ????????? ?????? ??? Modal ??? -->



</body>

</html>