<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	int idx =Integer.parseInt(request.getParameter("idx"));
	int currentPage = 1;
	String flag = request.getParameter("flag");
	try{
	currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}catch(Exception e){
		
	}
		
%>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function() {
	
$("#myModal").on("show", function() {    // wire up the OK button to dismiss the modal when shown
    $("#myModal a.btn").on("click", function(e) {
        console.log("button pressed");   // just as an example...
        $("#myModal").modal('hide');     // dismiss the dialog
    });
});
    
$("#myModal").on("hide", function() {    // remove the event listeners when the dialog is dismissed
    $("#myModal a.btn").off("click");
});
        
$("#myModal").on("hidden", function() {  // remove the actual elements from the DOM when fully hidden
    $("#myModal").remove();
});
        
$("#myModal").modal({                    // wire up the actual modal functionality and show the dialog
    "backdrop"  : "static",
    "keyboard"  : true,
    "show"      : true                     // ensure the modal is shown immediately
});
})

</script>
<jsp:include page="../Layout/header.jsp"></jsp:include>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- dialog body -->
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                해당 게시글을 삭제 하시겠습니까?<br>
                삭제이후 어떤 복구도 되지 않습니다
            </div>
            <!-- dialog buttons -->
            <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='ReViewDelete?idx=<%=idx%>&currentPage=<%=currentPage%>'">예</button>
            <% if(flag.equals("detail")){ %>
	        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='ReViewDetailSelect?idx=<%=idx%>&currentPage=<%=currentPage%>'">아니오</button>
			<%}else if(flag.equals("mypage")){ %>
	        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="history.back()">아니오</button>
			<%} %>
            </div>
        </div>
    </div>
</div>


<jsp:include page="../Layout/footer.jsp"></jsp:include>