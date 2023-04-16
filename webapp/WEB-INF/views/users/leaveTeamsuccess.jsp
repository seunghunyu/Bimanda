<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 탈퇴 성공</title>
</head>
<body>
<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
<div class="makeTeamdiv">
	<p>팀 탈퇴가 완료 되었습니다.</p>
	<a href="<%= request.getContextPath() %>" class="makeTeam">메인으로 돌아가기</a>
</div>
</body>
</html>