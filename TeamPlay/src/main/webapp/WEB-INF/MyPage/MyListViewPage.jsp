<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<c:set var="list" value="${reViewList.list}"/>
<jsp:useBean id="date" class="java.util.Date"/>
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<!--  메뉴바는 include로 , 메뉴바,메인글테두리 css는 header에 저장되어있음 -->
<!--  메인글 시작 입니다 여기서부터 -->

<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>
                <!-- 메인표시글 시작 -->

   <div class="container">
              <table id="mytable" class="table table-bordred table-striped table-hover">
                   
                   <thead>
                                <!-- 					<td align="center">글번호</td> -->
<!-- 					<td align="center">제목</td> -->
<!-- 					<td align="center">글쓴이</td> -->
<!-- 					<td align="center">작성일</td> -->
<!-- 					<td align="center">조회수</td> -->
                   <th><input type="checkbox" id="checkall" /></th>
                   <th>글번호</th>
                    <th style="width: 70px;">글쓴이</th>
                     <th style="text-align: center;">제목</th>
                     <th style="width: 146px; text-align: center;">작성일</th>
                     <th style="width: 66px;">조회수</th>
                      <th>Edit</th>
                      
                       <th>Delete</th>
                   </thead>
    <tbody>
	    <c:choose>
			<c:when test="${list.size()==0}">
					<tr>
						<td colspan="5"><marquee>테이블에 저장된 글이 없습니다.</marquee></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list}">
						<tr style="cursor: pointer;">
							<td><input type="checkbox" class="checkthis" /></td>
							<td align="center">${vo.RE_idx}</td>
							<td align="center">${vo.RE_userId}</td>
							<td align="center" onclick="location.href='ReHitUp.nhn?idx=${vo.RE_idx}&currentPage=${reViewList.currentPage}'">${vo.RE_title}</td>
								<td align="center">
								<c:if test="${date.year == vo.RE_writeDate.year && date.month == vo.RE_writeDate.month && date.date == vo.RE_writeDate.date}">
									<fmt:formatDate value="${vo.RE_writeDate}" pattern="a h:mm" />
								</c:if> 
								<c:if test="${date.year != vo.RE_writeDate.year || date.month != vo.RE_writeDate.month || date.date != vo.RE_writeDate.date}">
									<fmt:formatDate value="${vo.RE_writeDate}" pattern="yyyy.MM.dd(E)" />
								</c:if>
							</td>
							<td align="center">${vo.RE_hit}</td>
							<td align="center">
						    	<p data-placement="top" data-toggle="tooltip" title="Edit">
							    	<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" onclick="location.href='ReViewUpdate.nhn?idx=${vo.RE_idx}'">
							    		<span class="glyphicon glyphicon-pencil"></span>
							    	</button>
						    	</p>
					    	</td>
					    	<td align="center">
						    	<p data-placement="top" data-toggle="tooltip" title="Delete">
							    	<button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" onclick="location.href='ReViewDeleteOK.nhn?idx=${vo.RE_idx}&flag=mypage'" >
							    		<span class="glyphicon glyphicon-trash"></span>
							    	</button>
					    		</p>
	    					</td>
						</tr>
					</c:forEach>
				</c:otherwise>
		</c:choose>
	    <tr>
			<td colspan="8" align="center">
				<c:if test="${reViewList.currentPage > 1}">
					<input class="button button1" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'" />
				</c:if> 
				<c:if test="${reViewList.currentPage <= 1}">
					<input class="button button2" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다." />
				</c:if> 
				<c:if test="${reViewList.startPage > 1 }">
					<input class="button button1" type="button" value="이전" title="이전 10페이지로 이동" onclick="location.href='?currentPage=${reViewList.currentPage - ReViewList.pageSize}'" />
				</c:if> 
				<c:if test="${reViewList.startPage <= 1 }">
					<input class="button button2" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다." />
				</c:if>
				<c:forEach var="i" begin="${reViewList.startPage}" end="${reViewList.endPage}" step="1">
					<c:if test="${reViewList.currentPage == i}">
						<input class="button button2" type="button" value="${i}" disabled="disabled" />
					</c:if>
					<c:if test="${reViewList.currentPage != i}">
						<input class="button button1" type="button" value="${i}" onclick="location.href='?currentPage=${i}'" />
					</c:if>
				</c:forEach> 
					<c:if test="${reViewList.endPage < reViewList.totalPage}">
					<input class="button button1" type="button" value="다음" title="다음 10페이지로 이동" onclick="location.href='?currentPage=${reViewList.endPage + 1}'"/>
				</c:if> 
				<c:if test="${reViewList.endPage >= reViewList.totalPage}">
					<input class="button button2" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다." />
				</c:if> 
				<c:if test="${reViewList.currentPage < reViewList.totalPage}">
					<input class="button button1" type="button" value="맨뒤" title="마지막 페이지로 이동" onclick="location.href='?currentPage=${reViewList.totalPage}'" />
				</c:if> 
				<c:if test="${reViewList.currentPage >= reViewList.totalPage}">
					<input class="button button2" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다." />
				</c:if>
			</td>
		</tr>
    </tbody>
    <jsp:include page="/WEB-INF/ReView/ReViewModal.jsp"></jsp:include>
</table>
</div>
        

			            <!-- 메인표시글 끝 -->
<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>
<div>
	<jsp:include page="../Layout/footer.jsp"></jsp:include>
</div>

</html>