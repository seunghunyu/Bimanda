<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko-KR">
<head>

<meta charset="UTF-8">
<title>팀원모집</title>

<jsp:include page="/WEB-INF/views/includes/header.jsp" />
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
<script type="text/javascript">
function checkempty(obj){
	if (obj.title.value.trim().length==0){
		alert("제목을 입력해주세요")
	} else{
		return true;
	}
	return false;
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
          <h3 class="section-title">팀원모집 글쓰기</h3>
          <span class="section-divider"></span>
        </div>
        <br/><br/><br/><br/><br/>
        
        <div class="writeform">
		<form:form modelAttribute="recruitVo" id="recruit-form" name="recruitForm" method="POST"
					action="${pageContext.servletContext.contextPath}/recruit/write"
					onsubmit="return checkempty(this)">
	   			<label for="title" class="control-label">제목</label><br/>
				<form:input path="title" class="form-control" style="width:119%;"/>
				<input type="hidden" name="userNo" value="${authUser.userNo}"><br/><br/><br/>
				
				<label for="content" class="control-label">내용</label>
				<textarea name="content" id="summernote" class="form-control" cols=60 rows=5></textarea><br><br/><br/>
				
				<br/>
	   			<div class="makeTeamdiv" style="margin-left:35%;">
		   			<input class="makeTeam" type="submit" value="글쓰기">
		   			<a class="makeTeam" href="<%= request.getContextPath() %>/recruit/list">취소</a>
	   			</div>
	   	</form:form>
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