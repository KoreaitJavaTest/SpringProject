var voIdx = '${vo.RE_idx}'	//해당게시글의 번호
var CoIdx = 0;				//삭제하기 버튼누른후 초기화될 댓글번호
var flag = "${commentUpdate}"


$(document).ready(function() {
	if(flag=="update"){
		alert('댓글이 성공적으로 수정 되었습니다');
	}
	  $('#media').carousel({
	    pause: true,
	    interval: false,
	  });
$('.dropdown-toggle').dropdown();

// $('commentUpdate').click(function(this) {
// 	console.log(this.children().eq(0).val());
// })
	});
function commentCheck() {
	var session_id = '${sessionScope.session_id}';
	var context = $('#context').val();
	
// 	console.log(context.trim().length);
	if(session_id==''){
		alert('로그인 후 작성해주세요.');
		location.href='LoginViewDo'
		return false;
	}else if(context.trim().length==0){
		alert('댓글을 작성해 주세요.');
		return false;
	}
	return true;

}
function commentUpdateForm(obj) {
	var idx =  obj.children[0].value;
	var userId = obj.children[1].value;
	var content = obj.children[2].value;
	if(userId.trim()=='${sessionScope.session_id}'){
		$('#'+idx+'').empty();
		$('#'+idx+'').append("<td colspan=3><input type='text' name='content2' value='"+content+"' style='width:1100px;margin-top: 4px;'/>"+
								"</td>");
		$('#'+idx+'').append("<td>"+
							"<input type='button' class='btn btndefault test' value='수정하기' onclick='test("+idx+")'></td>");

	}else{
		alert('댓글 작성자가 아닙니다!');
	}
}

function test(idx) {
	var Commentidx = idx;		//댓글번호
	var content = $('input[name=content2]').val();
	var PageIdx = '${vo.RE_idx}'
	location.href = 'updateComment?Commentidx='+Commentidx+'&content='+content+'&idx='+PageIdx;
	
}
function idxCommit(idx) {			//idx : 댓글 번호 idx 
	CoIdx =idx;						//댓글 삭제하기 누름동시에 버튼초기화
}
function commentDelete() {
	console.log("삭제할 댓글 글번호 : "+CoIdx);
	console.log("댓글을 가진 게시글번호 : "+voIdx);
	location.href = 'deleteComment?idx='+voIdx+'&commentIdx='+CoIdx;
}//AJAX로 하자!

function like(flag){
	var flag=flag;
// 	console.log(flag);
	$.ajax({
		type:"POST",
		url:"./likeCheck",
		data:{
			userId:"${sessionScope.session_id}",
			idx:"${vo.RE_idx}",
			checkFlag:flag
		},
		dataType : "json",
		success: function(meg){
// 			alert(meg);
			location.reload();
		},error: function(meg){
			alert(meg);
		}
	});
}
$(function() {
	$('#ReViewSearch').click(function() {
		var searchName = $("select option:selected").val();
		var searchText = $('input[name=searchText]').val();
		if(searchText.trim().length==0){
			alert('검색어를 입력해 주세요');
		}else{
			location.href='ReViewSearch?searchName='+searchName+'&searchText='+searchText;
		}
	
	})
})