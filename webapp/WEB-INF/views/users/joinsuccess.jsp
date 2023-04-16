<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>
<div id="header">
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
 </div>
 <br/><br/><br/><br/><br/><br/>
    <div class="makeTeamdiv">
	    <p>"감사합니다. 회원 가입이 정상적으로 처리되었습니다."</p>
	    <a href="<%= request.getContextPath() %>" class="makeTeam">메인으로 돌아가기</a>
    </div>
</body>
</html>