<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ��Ż�� ����</title>
</head>
<body>
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
 
 <div class="makeTeamdiv">
	<p>ȸ��Ż�� �Ϸ� �Ǿ����ϴ�.</p>
	<a href="<%= request.getContextPath() %>" class="makeTeam">�������� ���ư���</a>
</div>
</body>
</html>