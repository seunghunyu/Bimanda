<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일확인</title>
</head>
<body>
<h3>upload 된 파일</h3>
파일명 : ${filename}
<br>
<a href="upload">뒤로</a>
<a href="<%= request.getContextPath() %>/team/teammain">팀 페이지</a>
</body>
</html>