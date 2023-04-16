<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${bVo.boardTitle }</title>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function checkEmpty(btn) {
	if (btn.form.commentContent.value.trim().length==0){
		alert("내용을 입력해주세요")
		return;
	}
	else {
		document.commentForm.submit();
	}
}
</script>
<script type="text/javascript">
function delete_confirm(){
//	삭제할 것인지 확인
//	confirm
	var result = confirm("정말로 삭제하시겠습니까?")
	if (result) {
	//	확인
		return true;
	} else {
		return false;
	}
}
</script>
</head>
<body>

<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	
	<div class="section-header">
          <h3 class="section-title">${bVo.boardTitle }</h3>
          <span class="section-divider"></span>
        
        <br/>
        </div>
        <div class="commu-cate-list">
			<ul>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/noticeboard">공지사항</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/jisikboard">질문게시판</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/freeboard">자유게시판</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/ideaboard">아이디어게시판</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/reviewboard">공모전후기</a></li> 
			</ul>
	</div>
        <div class="contest-list">
    <table class="table">
    	<thead>
    		<tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
    			<th class="text-left">작성자: ${bVo.userVo.userName }&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;${bVo.regDate }&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;조회: ${bVo.hit_cnt }</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr class="zxc">
    			<td class="content2">${bVo.boardContent }</td>
    		</tr>
    	</tbody>
    
    </table>
    <div class="margin"></div>
    
	<h6>댓글 목록</h6>
	<table class="table">
		<tbody>
			<c:forEach items="${clist }" var="clist">
			<tr>
				<td class="text-left">${clist.userVo.userName }(${clist.userVo.userId })&nbsp;&nbsp;${clist.regDate }<br/>${clist.commentContent }</td>
				<td class="buttons">
				<c:if test="${not empty authUser}">
					<c:if test="${authUser.userNo == clist.userNo}">
						<button type="button" rel="tooltip" title="수정" class="btn btn-success btn-simple btn-xs"
							onclick="location.href='<%= request.getContextPath() %>/community/comment/modify/${clist.commentNo}'">
							<i class="fa fa-edit"></i>
						</button>
					</c:if>
				</c:if>
				</td>
				<td class="buttons">
				<c:if test="${not empty authUser}">
					<c:if test="${authUser.userNo == clist.userNo}">
						<a href="<%= request.getContextPath() %>/community/comment/delete/${clist.commentNo}" 
							rel="tooltip" title="삭제" class="btn btn-danger btn-simple btn-xs"
							onclick="return delete_confirm()">
                    		<i class="fa fa-times"></i>
                		</a>
					</c:if>
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="">
	<c:if test="${!empty authUser }">
	<form method="post" action="<%=request.getContextPath() %>/community/commentswrite"
		name="commentForm">
		<input type="hidden" name="boardNo" value="${bVo.boardNo}">
		<input type="hidden" name="userNo" value="${authUser.userNo}">
		<div class="makeTeamdiv2">
			<input type="text" class="form-control" name="commentContent" placeholder="입력하세요" style="margin-top:10%;">
	   			<input class="makeTeam" type="button" value="댓글쓰기" onclick="checkEmpty(this)" style="margin-left:0%; margin-top: 3%;">
   			</div>
	</form>
	</c:if>
	<div class="back">
   				<ul>
	   				<li><a class="makeTeam" style="color: #fff;" href="${pageContext.servletContext.contextPath }/community/ideaboard">글 목록</a></li>
	   			</ul>
   			</div>
	</div>
	</div>
</body>
</html>