<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀원모집</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />
<div class="section-header">
          <h3 class="section-title">댓글수정</h3>
          <span class="section-divider"></span>
        
        <br/>
        </div>
        <div class="writeform" style="margin-top: 20px;">
	<form:form modelAttribute="requestVo" id="request-form" name="requestForm" method="POST"
				action="${pageContext.servletContext.contextPath}/recruit/req/modify">
			
			<input type="hidden" name="reqNo" value="${vo.reqNo}">
			<input type="hidden" name="no" value="${vo.no}">
			<input type="hidden" name="userNo" value="${authUser.userNo}">
			
			<label for="content">내용</label>
			<textarea name="content" cols=60 rows=1 >${vo.content}</textarea><br><br/><br/>
			<br/>
   			
   			<div class="makeTeamdiv" style="margin-left:35%;">
	   			<input class="makeTeam" type="submit" value="수정하기">
	   			<a class="makeTeam" href="<%= request.getContextPath() %>/recruit/detail/${vo.no}">취소</a>
   			</div>
   	</form:form>
   	</div>
</body>
</html>