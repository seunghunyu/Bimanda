<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="UTF-8">
<title>아이디어게시판 글쓰기</title>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function ideaCheckForm(btn){
	// 아이디 필드 체크
	if (btn.form.boardTitle.value.trim().length==0){
		alert("제목을 입력해주세요")
		return;
	}else if(btn.form.boardContent.value.trim().length==0){
		alert("내용을 입력해주세요")
		return;
	}else {
		document.ideawriteaction.submit()
	}
}
</script>
<style>
body{
	background:#eff5f5;
}
</style>
</head>
<body>

	<br/>
	<div class="section-header">
          <h3 class="section-title">글쓰기</h3>
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
		<div class="writeform">
			<form modelAttribute="boardVo" method="post" action="${pageContext.servletContext.contextPath }/community/writeaction" name="ideawriteaction">
			<input type="hidden" name="userNo" value=${authUser.userNo }><br>	
				<input type="hidden" name="userId" value="${authUser.userId}"><br>
			 	<input type="hidden" name="boardCategory" value="아이디어공유"><br>
			 	<label for="userName" class="control-label" >제목</label>
				<input type="text" name="boardTitle" class="form-control" style="width:119%;">
				<label for="userName" class="control-label">내용</label>
				<textarea name="boardContent" id="summernote" class="form-control" cols=60 rows=5></textarea><br><br/><br/>
				<div class="makeTeamdiv" style="margin-left:17%;">
					<input class="makeTeam" type="button" value="작성" onclick="ideaCheckForm(this)">
				</div>
			</form>
		</div>
		<script type="text/javascript">
		   	$(document).ready(function() {
		        $('#summernote').summernote({
		        	placeholder: '내용을 입력해주세요',
			        tabsize: 2,
			        width: 800,
			        height: 300,
			        focus: true
		        });
		   });
	    </script>

</body>
</html>