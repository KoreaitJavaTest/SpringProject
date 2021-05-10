
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
function UpdateUserInfo(idx,level,id,password,gender,phone,point,email) {
//	console.log(id);
	$('#userId').val(id);
	$('#userPw').val(password);
	$('#userEmail').val(email);
	$('#userPh').val(phone);
	$('#userPoint').val(point);
	$('#userPh').val(phone);
	if(level==0){
		$('#consumer').attr('selected','selected')
	}
	if(level==1){
		$('#seller').attr('selected','selected')
	}
	if(level==2){
		$('#admin').attr('selected','selected')
	}

}	

