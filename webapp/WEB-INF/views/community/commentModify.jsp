<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글수정</title>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function checkempty(obj){
	if (obj.commentContent.value.trim().length==0){
		alert("내용을 입력해주세요")
	} else{
		return true;
	}
	return false;
}
</script>
</head>
<body>

<jsp:include page="/WEB-INF/views/includes/header.jsp" />
<div class="section-header">
          <h3 class="section-title">댓글수정</h3>
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
<div class="writeform" style="margin-top: 20px;">
<form:form modelAttribute="commentsVo" method="post" 
	action="${pageContext.servletContext.contextPath }/community/comment/modify" 
	name="modifyaction" onsubmit="return checkempty(this)">
	
	<input type="hidden" name="userNo" value="${authUser.userNo}">
	<input type="hidden" name="commentNo" value="${cVo.commentNo}">
 	<input type="hidden" name="boardNo" value="${cVo.boardNo}">
 	
	<label for="content">댓글</label>
	<textarea name="commentContent" cols=60 rows=1 >${cVo.commentContent}</textarea><br><br/><br/>
	
   	<div class="makeTeamdiv">
   		<input class="makeTeam" type="submit" value="수정하기">
   	</div>
</form:form>
</div>
</body>
</html>