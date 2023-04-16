<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀생성 실패</title>
</head>
<body>
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
	<div class="makeTeamdiv">
		<p>팀 생성에 실패했습니다.</p>
		<div class="back">
			<a href="<%= request.getContextPath() %>" class="makeTeam">메인으로 돌아가기</a>
		</div>
	</div>
</body>
</html>