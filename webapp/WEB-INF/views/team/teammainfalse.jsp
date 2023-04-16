<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실패</title>
</head>
<body>
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
<div class="makeTeamdiv">
<div class="back">
	<p>팀이없으니 팀을생성하세요! </p>
	<a href="<%= request.getContextPath() %>" class="makeTeam">메인으로 돌아가기</a>
	</div>
</div>
</body>
</html>