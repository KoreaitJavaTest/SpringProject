
function userDelete(id) {
	var flag = confirm('회원정보를 정말로 삭제 하시겠습니까? \n삭제후 복구 되지  않습니다.')
	if (flag) {
		$.ajax({
			type:"POST",
			url:"./AdminUserDelete",
			data:{
				userId:id,
			},
			success: function(meg){
	 			alert(meg);
				location.reload();
			},error: function(meg){
				alert(meg);
			}
		});
	} else {
		alert('잘 생각 했어!')
	}
	
}

