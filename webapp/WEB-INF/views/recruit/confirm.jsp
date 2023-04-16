<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀원확인</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function checkTeam(btn){
	if (btn.form.teamName.value.trim().length==0){
		alert("팀명을 입력해주세요")
		return;
	}
	//jQuery Ajax 수행
	$.ajax({
		url: "${pageContext.servletContext.contextPath}/recruit/name/check",
		type: "post",
		data:{teamName: btn.form.teamName.value},
		success: function(result){
			if (result == "fail") {
				alert("이미 있는 이름입니다.")
			} else {
				document.confirmForm.submit()
			}
		},
		error: function(xhr, status, error){
			alert(status);
			alert(error);
		}
	});
}
</script>
</head>

<body>
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
	    	<div class="section-header">
          <h3 class="section-title">팀원확인</h3>
          <span class="section-divider"></span>
        
        <br/>
	    	</div>
	    	
	<div class="contest-list">
		<form:form method="POST" id="confirmForm" name="confirmForm"
			action="${pageContext.servletContext.contextPath}/recruit/team/check">
			<input type="hidden" name="userNo" value="${authUser.userNo}">
			<c:forEach items="${nlist}" var="no">
				<input type="hidden" name="userNo" value="${no}">
			</c:forEach>
			<table class="table">		
					<thead>
						<tr>
							<th>아이디</th>
							<th>이름</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach items="${vlist}" var="vo">
							<tr>
								<td>${vo.userId}</td>
								<td>${vo.userName}</td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
			<br/><br/>
			<div class="writeform">
				<label for="teamName">팀명</label>
				<input type="text" name="teamName"><br>
				
				<input type="button" class="makeTeam" style="margin-left:18%;" value="팀만들기" onclick="checkTeam(this)">
			</div>
		</form:form>
		</div>
	
</body>
</html>