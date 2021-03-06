/**
 * 
 */

function getCate_num(){
	$.ajax({
		url : '/soju/getCateNum.do',
		data : {"art_num":art_num,
				"cate_name":cate_name},
		type : 'post',
		success : function(res) {
			cate_num = res.cate_num;
			$('input[name=cate_num]').val(cate_num);
			printContent();
		},
		error : function (xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

//피드 화면에 출력
function printContent() {
	$.ajax({
		url : '/soju/FeedPrint.do',
		data : {"cate_num" : cate_num,
				"user_id" : user_id},
		type : 'post',
		success : function (res) {
			str = "";
			$.each(res,function(i,v){
				str += `<article class="post" idx="${v.num}">`;
				str += `<div>`;
				str += `<div class="myprofile">`;
				str += `<img src="/soju/profile.do?fileName=${v.profile}">`;
				str += `</div>`;
				str += `<div class="ppost">`;
				str += `<h5>`;
				str += `<span id="writer">${v.nick}</span>`;
				str	+= `<div style="display:inline-block; float : right;">`;
				if(user_id==v.id){
				str += `<button type="button" idx="${v.num}" name="dele" class="sbut delete">삭제</button>`;
				str += `<button type="button" idx="${v.num}" name="modi" class="sbut modify" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modifymodal">수정</button>`;
				}
				str += `</div>`
				str += `</h5>`;
				str += `</div>`;
				str += `<div class="ppost date">`;
				str += `<span>${v.date}</span>`;
				str += `</div>`;
				str += `<br>`;
				str += `<div id="cont" idx="${v.num}" style ="cursor: pointer;">`;
				
				if(v.files.length > 1){
					str += `<div id="${v.id}" class="carousel slide" data-ride="carousel">`;
					
					//<!-- Indicators -->
					str += `<ul class="carousel-indicators">`;
					$.each(v.files, function(i, v) {
						str += `<li data-target="#${v.id}" data-slide-to="${i}"`
							if(i==0) str += `class="active"`
						str	+= `></li>`;
					})
					str += `</ul>`;
					str += `<div class="carousel-inner">`;
					$.each(v.files, function(i, v) {
						//<!-- The slideshow -->
						str += `<div class="carousel-item`
							if(i==0) str += ` active`
						str += `">`;
						
						str += `<img src="/soju/imageView.do?fileName=${v}&fileType=photo" class="items">`;
						str += `</div>`;
					})
					str += `</div>`;
					//<!-- Left and right controls -->
					str += `<a class="carousel-control-prev" href="#${v.id}" data-slide="prev">`;
					str += `<span class="carousel-control-prev-icon "></span>`;
					str += `</a>`;
					str += `<a class="carousel-control-next" href="#${v.id}" data-slide="next">`;
					str += `<span class="carousel-control-next-icon"></span>`;
					str += `</a>`;
					str += `</div>`;
				}else if(v.files.length == 1){
					
					$.each(v.files, function(i, v) {
						
						str += `<img src="/soju/imageView.do?fileName=${v}&fileType=photo" class="items">`;

					})
					
				}
				str += `<p id="cont1">${v.cont}</p>`;
				str += `</div>`;
				str += `<hr>`;
//				str += `<div class="fgood">`;
//				str += `<label for="fg" style="cursor: pointer;"><img class="good" src="../images/good.png"></label>`;
//				str += `<input id="fg" name="ini" type="button" style="display: none;">`;
//				str += `</div>`;
				str += '<div class="artReact">';
		        str += '<div class="artReactLike">';
		        str += '<label aria-disabled="false" role="button">';
		        str += '<input class="reactLikeCheck" name="bdlike" type="checkbox">';
		        str += '<img class="likeBtn';
		            if(v.on=="on") str += ' on"';
		            else str += '"';
		        str +=   ' src="/soju/images/star.png">';
		        str += '<span class="reactCount">'+ v.bd_like +'</span>';
		        str += '</label> ';
		        str += '</div>';
//		        str += '<div class="artReactReply">';
//		        str += '<label aria-disabled="false" role="button">';
//		        str += '<input class="reactReplyCheck" type="checkbox">';
//		        str += '<img class="replBtn" src="../images/reply.png">';
//		        str += '<span class="reactCount">'+ v.bd_reply_cnt +'</span>';
//		        str += '</label> ';
//		        str += '</div>';
		        str += '</div>';
				str += `<hr><br>`;
				str += `<div>`;
				str += `<button type="button" class="listup sbut" name= "list" idx="${v.num}">댓글 보기</button>`;
				str += `</div>`;
				str += `<br><br>`;
				str += `<div class="myprofile reprofile">`;
				str += `<img src="">`;
				str += `</div>`;
				str += `<form>`;
				str += `<textarea class="content reply" placeholder="댓글을입력해주세요."></textarea>`;
				str += `<button type="button" idx="${v.num}" class="sbut complete" name="replyupload" disabled>게시</button>`;
				str += `</form>`;
				str += `</div>`;
				str += `<br><br>`;
				str += `<div class="rbox ${v.num}" idx="${v.num}">`;
				str += `</div>`;	
				str += `</article>`;
				
			})
			$('.start').html(str);
			$('.reprofile img').attr('src', "/soju/profile.do?fileName=" + user_profile);
		},
		error : function (xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

function getArtist(){
	$.ajax({
		url : '/soju/getArtist.do',
		data : {"art_num":art_num},
		type : 'post',
		success : function(res) {
			$('.aname p').text(res.art_name);
			$('.wever').text(res.sub_cnt);
			$('.aprofile img').attr('src', "/soju/profile.do?fileName="+res.art_photo);
			getCate_num();
		},
		error : function (xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

//피드 등록
function feedUpload() {
	$.ajax({
		url : '/soju/FeedUpload.do',
		data : fdata,
		type : 'post',
		success : function (res) {
//			alert(res.sw);
			if(res.sw=="성공") {
				
				$('.pwrite').val("");
				printContent();
			}
		},
		error : function (xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

//피드 삭제
function deleteContent() {
	$.ajax({
		url : '/soju/FeedDelete.do',
		data : { "num" : idx },
		type : 'post',
		success : function(res) {
//			alert(res.sw);
			if(res.sw=="성공"){
				printContent();
			}
		},
		error : function(xhr) {
			alert(xhr.status);
		},
		dataType : 'json'
	})
}

//피드 수정
function modifyContent() {
	$.ajax({
		url : '/soju/FeedModify.do',
		data : {"modc":modicon, "num":idx},
		type : 'post',
		success : function(res) {
			if(res.sw=="성공"){

				//모달창에서 수정된 값 가져오기
				area = $('#modifymodal textarea').val();
				area = area.replace(/\r/g, "").replace(/\n/g, "<br>");
				
				//화면에 출력하기
				$(post).find('#cont').html(area);
				
				//모달창에 입력내용 지우기
				$('#modifymodal .marea').text("");
				
				//모달창 닫기
				$('#modifymodal').modal('hide');
				
//				printContent();
			}
		},
		error : function(xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
	
}


//댓글 등록
function insertReply(loadbut) { //loadbut임의로 지정
	$.ajax({
		url : '/soju/ReplyInsert.do',
		data : reply,
		type : 'post',
		success : function(res) {
			if(res.sw=="성공"){
				$('.reply').val("");
				replyList(loadbut);//댓글리스트 출력
			}
		}, error : function(xhr) {
			alert(xhr.status);
		},
		dataType : 'json'
	})
}

//댓글 리스트 출력
function replyList(loadbut) {
	$.ajax({
		url : '/soju/ReplyList.do',
		type : 'get',
		data : {"bd_num" : idx,
				"user_id" : user_id},
		success : function(res) {
			rcode = "";
//			code ="";
			if(res<1){
				str = `<span class="replybox">등록된 댓글이 없습니다.</span>`;
				$('.'+idx).html(str);
			}else{
				$.each(res, function(i, v) {
					rcode += `<div id="rep">`;
					rcode += `<div class="myprofile">`;
					rcode += `<img src="/soju/profile.do?fileName=${v.profile}">`;
					rcode += `</div>`;
					rcode += `<div id="rb">`;
				    rcode += `<span class="replybox">
				    			<span class="rwr">${v.nick}</span>&nbsp&nbsp
				    			<span class="rcon" idx="${v.rpnum}">${v.cont}</span>
				    		  </span><br>`;
				    rcode += `<div class="rsum" >`;
				    rcode += `<span class="rdate">·&nbsp${v.date}&nbsp&nbsp·&nbsp</span>`;
				    rcode += '<div class="artReact" style="display:inline-block">';
			        rcode += '<div class="artReactLike">';
			        rcode += '<label aria-disabled="false" role="button">';
			        rcode += '<input class="reactLikeCheck" name="rplike" type="checkbox">';
			        rcode += '<img class="likeBtn';
			            if(v.on=="on") rcode += ' on"';
			            else rcode += '"';
			        rcode +=  ' src="/soju/images/good.png" style="width:20px;">';
			        rcode += '<span class="reactCount">'+ v.like +'</span>';
			        rcode += '</label> ';
			        rcode += '</div>';
				    rcode += `</div>`;
				    rcode += `<div style="display:inline-block; float : right;">`;
				    if(user_id==v.id){
				    	rcode += `<button type="button" idx="${v.rpnum}" name="rdele" class="sbut rdelete">삭제</button>`;
				    	//rcode += `<button type="button" idx="${v.rpnum}" name="rmodi" class="sbut rmodify" class="btn btn-info btn-lg" data-toggle="modal" data-target="#rmodifymodal">수정</button>`;
				    }
				    rcode += `</div>`
					rcode += `</div>`
					rcode += `</div>`;
				    rcode += `</div>`;
//						code += `${v.bdnum}`;
			})
				$('.'+ idx).html(rcode);
			}
			
		},
		error : function(xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

//댓글 삭제
function deleteReply(del) {
	$.ajax({
		url : '/soju/DeleteReply.do',
		type : 'get',
		data : {"rp_num" : idx},
		success : function(res) {
				$(del).parents('#rep').remove();
		},
		error : function(xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
		
	})
}

//상세보기 (글)
function detailFeed() {
	$.ajax({
		url:'/soju/Detailfeed.do',
		type : 'post',
		data : {"bd_num" : idx},
		success : function(res) {
			
		},
		error : function(xhr) {
			alert(xhr.status)
		}
	})
}

function bd_like(btn){
	$.ajax({
		url:'/soju/bd_like.do',
		type : 'post',
		data : {"bd_num" : bn,
				"user_id" : user_id},
		success : function(res) {
			if(res.sw=="성공"){
				cnt = $(btn).next().next();
				$(cnt).text(parseInt($(cnt).text()) + 1);
			}
		},
		error : function(xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

function bd_likeD(btn){
	$.ajax({
		url:'/soju/bd_likeD.do',
		type : 'post',
		data : {"bd_num" : bn,
				"user_id" : user_id},
		success : function(res) {
			if(res.sw=="성공"){
				cnt = $(btn).next().next();
				$(cnt).text(parseInt($(cnt).text()) - 1);
			}
		},
		error : function(xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

function rp_like(btn){
	$.ajax({
		url:'/soju/rp_like.do',
		type : 'post',
		data : {"rp_num" : rn,
				"user_id" : user_id},
		success : function(res) {
			if(res.sw=="성공"){
				cnt = $(btn).next().next();
				$(cnt).text(parseInt($(cnt).text()) + 1);
			}
		},
		error : function(xhr) {
			alert(xhr.status)
		},
		dataType : 'json'
	})
}

function rp_likeD(btn){
	$.ajax({
		url:'/soju/rp_likeD.do',
		type : 'post',
		data : {"rp_num" : rn,
				"user_id" : user_id},
		success : function(res) {
			if(res.sw=="성공"){
				cnt = $(btn).next().next();
				$(cnt).text(parseInt($(cnt).text()) - 1);
			}
		},
		error : function(xhr) {
			alert(xhr.status)
		}
		,dataType : 'json'
	})
}

/*//댓글 수정
function modifyReply() {
	$.ajax({
		url : '/soju/ModifyReply.do',
		data : mreply,
		type : 'post',
		success : function(res) {
			//alert(res.sw)
		},
		error : function(xhr) {
			alert(xhr.statustext)
		},
		dataType : 'json'
	})
	
}*/