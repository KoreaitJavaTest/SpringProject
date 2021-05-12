<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.form-horizontal .control-label {
    text-align: left;
    padding-top:0px;
}
</style>

<div class="modal fade" id="UserVo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">User Update</h4>
      </div>
      <div class="modal-body">
      
    <form class="form-horizontal" action="AdminUserUpdate" method="post">"
<fieldset>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-12 control-label userUpdate" for="pname">회원 ID</label>  
  <div class="col-md-12">
  <input id="userId" name="userId" type="text" placeholder="" class="form-control input-md" readonly="readonly"> 
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-12 control-label userUpdate" for="DVFCZ">회원 비밀번호</label>  
  <div class="col-md-12">
  <input id="userPw" name="userPw" type="text" placeholder="" class="form-control input-md" required="required">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-12 control-label userUpdate" for="KKKO">Email</label>  
  <div class="col-md-12">
  <input id="userEmail" name="userEmail" type="text" placeholder="" class="form-control input-md" required="" readonly="readonly">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-12 control-label userUpdate" for="CCC">P.H</label>  
  <div class="col-md-12">
  <input id="userPh" name="userPh" type="text" placeholder="" class="form-control input-md" required="required">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-12 control-label userUpdate" for="YIHK">Point</label>  
  <div class="col-md-12">
  <input id="userPoint" name="userPoint" type="text" placeholder="" class="form-control input-md" required="required">
  </div>
<!--   <div class="col-md-7"> -->
<!--   <input id="userPoint" name="userPoint" type="text" placeholder="" class="form-control input-md" required=""> -->
<!--   </div> -->
    
</div>

<!-- Text input-->
<!-- <div class="form-group"> -->
<!--   <label class="col-md-12 control-label userUpdate" for="BJKSS">프로필 사진</label>   -->
<!--   <div class="col-md-12"> -->
<!--    	<input type="file">  -->
<!--   </div> -->
<!-- </div> -->

<div class="form-group">
  <label class="col-md-12 control-label userUpdate" for="PTYPE">권한</label>
  <div class="col-md-12">
    <select id="userLevel" name="userLevel" class="form-control">
      <option id="consumer" value="0">소비자</option>
      <option id="seller" value="1">판매자</option>
      <option id="admin" value="2">관리자</option>
    </select>
  </div>
</div>

<!-- Button (Double) -->
<div class="form-group">
  <label class="col-md-12 control-label " for="button1idFFF"></label>
  <div class="col-md-8">
    <button id="button1idFFF" name="button1idFFF" type="submit" class="btn btn-success">UPDATE</button>
    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
  </div>
</div>
</fieldset>
</form>
      </div>
    </div>
  </div>
</div>