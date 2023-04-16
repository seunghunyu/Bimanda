<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일목록</title>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function delete_confirm(){
//	삭제할 것인지 확인
//	confirm
	var result = confirm("정말로 삭제하시겠습니까?")
	if (result) {
	//	확인
		return true;
	} else {
		return false;
	}
}
</script>
</head>
<body>
** 파일 업로드/다운로드 ** <br>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<table>
	<tr>
		<th>파일명</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach items="${files}" var="file">
	<tr>
		<td>${file}</td>
    	<td>
    		<a href="${pageContext.servletContext.contextPath}/team/download/${file}">다운로드</a>
    	</td>
    	<td>
    		<a href="${pageContext.servletContext.contextPath}/team/delete/${file}"
    			onclick="return delete_confirm()">삭제</a>
    	</td>
    <tr>
    </c:forEach>
</table>

<form:form enctype="multipart/form-data" modelAttribute="uploadFile" >
업로드할 파일 선택 : <br>
<input type="file" name="file">
<form:errors path="file" cssStyle="color:red" />
<br><Br>
<input type="submit" value="전송">
</form:form>
</body>
</html>