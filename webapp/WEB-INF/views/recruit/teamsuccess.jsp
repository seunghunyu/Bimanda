<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀생성 성공</title>
</head>
<body>
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
	    <div class="makeTeamdiv">
          <h3>팀이 생성되었습니다.</h3>
          <h5>팀 페이지에서 팀원들과 협업을 시작해보세요!</h5>
          <div class="back">
	    <ul style="float: left; list-style:none;">    
			<li><a href="<%= request.getContextPath() %>/">메인으로</a></li>
			<li><a href="<%= request.getContextPath() %>/team/teammain">팀 페이지</a></li>
		</ul>
		</div>
		</div>
</body>
</html>