<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ���������� ����</title>
	
</head>
<body>
<div id="header">
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
 </div>
 <br/><br/><br/><br/><br/><br/>
 
 <div class="makeTeamdiv">
	<p>ȸ������ ������ �Ϸ� �Ǿ����ϴ�.</p>
	<a href="<%= request.getContextPath() %>" class="makeTeam">�������� ���ư���</a>
</div>
</body>
</html>