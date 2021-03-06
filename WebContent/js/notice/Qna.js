
//자주질문 수정
function modifyQna(){
	
	$.ajax({
		url : '/soju/QnaUpdate.do',
		data : fdata,
		type : 'post',
		success : function(res){
			//alert(res.sw);
			
			//화면수정 - modal창에 입력된 값 가져오기
			title = $('#modifyModal input[name=qna_title]').val();
			//writer = $('#modifyModal input[name=writer]').val();
			//mail = $('#modifyModal input[name=mail]').val();
			area = $('#modifyModal textarea').val();  //\r\n이 포함되어 있다 
			console.log(area);
			area = area.replace(/\r/g, "").replace(/\n/g, "<br>");
			
			//화면에 출력
			$(panel).find('a').text(title);  //제목
			//wr = $(panel).find(".wr listwr").text(writer); //작성자
			//em = $(panel).find(".em").text(mail); //메일
			p3 = $(panel).find(".listp3").html(area); //내용 -<br>태그 포함되어 있다 
			
			
			//모달 입력내용 지우기
			$('#modifyModal .intxt').text("");
			
			//모달창 닫기
			$('#modifyModal').modal('hide');
			
		},
		error : function(xhr){
			//alert("상태 : " + xhr.status);
		},
		dataType : 'json'
	})
}



//자주질문 삭제
function deleteQna(){
	$.ajax({
		url : '/soju/QnaDelete.do',
		type : 'post',
		data : {"num" : idx },
		success : function(res){
			//alert(res.sw);
			//currentPage와 totalPage를 비교
			if(res.sw == "성공"){ 			
				if(currentPage > res.tpage) currentPage = res.tpage;
	
				//다시 전체 리스트 중 currentPage의 리스트를 출력
				QnaList(currentPage);
			}
		},
		error : function(xhr){
			//alert("상태 : " + xhr.status);
		},
		dataType : 'json'
	})
}



//자주질문 글쓰기
function saveQna(){
	$.ajax({
		
		url : '/soju/QnaInsert.do',
		data : fdata,
		type : 'post',
		success : function(res){
			//alert(res.sw);
			//modal닫기
			$('#writeModal').modal('hide');
			
			//modal안의 입력내용 지우기
			$('.intxt').val("");
			
			QnaList(1);
			
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
			
		},
		dataType : 'json'
	})
}





//자주묻는질문 목록
function QnaList(cpage){
	
	$.ajax({
		url : '/soju/QnaList.do',
		data : { "page" : cpage },
		type : 'get',
		success : function(res){
			  code = '<div class="panel-group" id="accordion">';
			  $.each(res.datas, function(i, v){    

				   code += '<div class="panel panel-default">';
				   code += '<div class="panel-heading">';
				   code += '<h4 class="panel-title">';
				   code += '<a idx="'+ v.num +'" name="list" class="action"  data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.num + '"><div class="subsub">' + v.subject + '</div></a> ';
				   code += '</h4>';
				   code += '</div>';
				   code += '<div id="collapse' + v.num + '" class="panel-collapse collapse">';
				   code += '<div class="panel-body pbody">';
				   code += '<br>';
				   code += '<p class="p3 listp3">';
				   code += v.content;
				   code += '</p>';
				   
				   code += '<p class="p2">';
				   code += '<input idx="' + v.num + '" type="button" name="modify" class="action" value="수정">';
				   code += '<input idx="' + v.num + '" type="button" name="delete" class="action" value="삭제">';
				   code += '</p>'; 
				 
				   code += '</div>';
				   code += '</div>';
				   code += '</div>';
			  })
			  code += '</div>';
			  
			  $('#list').html(code);
			  
			  $('#pageList').empty(); //[비우기]
			  
			  
			  //페이지번호 만들기
			  pager = '<ul class="pagination">';
				  for(i=res.sp; i<=res.ep; i++){
					  if(currentPage == i){
						  pager += '<a class="active" href="#">'+ i +'</a>';
					  }else{
						  pager += '<a class="paging" href="#">'+ i +'</a>';
					  }
				  }
			  pager += '</ul>';
				  
			  $('#pageList').append(pager);
			  
			  
		  },
		error : function(xhr){
			//alert("상태 : " +xhr.status);
		},
		dataType : 'json'
	  })//ajax 끝	
	  
}//listPage function끝


//자주묻는질문 목록
function QnaList2(cpage){
	
	$.ajax({
		url : '/soju/QnaList.do',
		data : { "page" : cpage },
		type : 'get',
		success : function(res){
			  code = '<div class="panel-group" id="accordion">';
			  $.each(res.datas, function(i, v){    

				   code += '<div class="panel panel-default">';
				   code += '<div class="panel-heading">';
				   code += '<h4 class="panel-title">';
				   code += '<a idx="'+ v.num +'" name="list" class="action"  data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.num + '"><div class="subsub">' + v.subject + '</div></a> ';
				   code += '</h4>';
				   code += '</div>';
				   code += '<div id="collapse' + v.num + '" class="panel-collapse collapse">';
				   code += '<div class="panel-body pbody">';
				   code += '<br>';
				   code += '<p class="p3 listp3">';
				   code += v.content;
				   code += '</p>';
				 
				   code += '<p class="p2">';
				   code += '<input idx="' + v.num + '" type="hidden" name="modify" class="action" value="수정">';
				   code += '<input idx="' + v.num + '" type="hidden" name="delete" class="action" value="삭제">';
				   code += '</p>';
				   
				   code += '</div>';
				   code += '</div>';
				   code += '</div>';
			  })
			  code += '</div>';
			  
			  $('#list').html(code);
			  
			  $('#pageList').empty(); //[비우기]
			  
			  
			  //페이지번호 만들기
			  pager = '<ul class="pagination">';
				  for(i=res.sp; i<=res.ep; i++){
					  if(currentPage == i){
						  pager += '<a class="active" href="#">'+ i +'</a>';
					  }else{
						  pager += '<a class="paging" href="#">'+ i +'</a>';
					  }
				  }
			  pager += '</ul>';
				  
			  $('#pageList').append(pager);
			  
			  
		  },
		error : function(xhr){
			//alert("상태 : " +xhr.status);
		},
		dataType : 'json'
	  })//ajax 끝	
	  
}//listPage function끝

