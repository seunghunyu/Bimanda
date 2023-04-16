<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원정보수정 성공</title>
	
</head>
<body>
<div id="header">
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
 </div>
 <br/><br/><br/><br/><br/><br/>
 
 <div class="makeTeamdiv">
	<p>회원정보 수정이 완료 되었습니다.</p>
	<a href="<%= request.getContextPath() %>" class="makeTeam">메인으로 돌아가기</a>
</div>
</body>
</html>