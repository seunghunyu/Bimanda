<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" 
	rel="stylesheet" 
	href="<c:url value="/css/main.css" />"/>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
</script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function checkform2(btn){
	// 아이디 필드 체크
	if (btn.form.userId.value.trim().length==0){
		alert("아이디를 입력해주세요")
		return;
	}else if (btn.form.userPassword.value.trim().length==0){
		alert("비밀번호를 입력해주세요")
		return;
	}
	
	//jQuery Ajax 수행
	$.ajax({
		url: "${pageContext.servletContext.contextPath}/users/checkform",
		type: "get",
		dataType: "json",
		data:{userId: btn.form.userId.value,
			userPassword: btn.form.userPassword.value},
		success: function(response){
			if (response.data == "exists"){
				document.checkForm.submit()
			}else{
				$('#checkMsg').html("<p>로그인 실패</p>")
				$('#checkMsg2').html("<p>아이디/비밀번호를 확인해주세요</p>")
				btn.form.check.value = "false"
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
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	
	<div class="col-sm-2">
           
             <form method="POST" action="<%=request.getContextPath() %>/users/login"
    	name="checkForm">
    	<div class="form-group">
               <div class="form-row">
               	<label for="username" class="control-label">아이디</label>
    			<input type="text" name="userId" class="form-control" id="userId" placeholder="아이디">
    			<br/>
    			</div>
    			</div>
    			<div class="form-group">
    			<label for="password" class="control-label">비밀번호</label>
	    		<input type="password" name="userPassword" class="form-control" id="userPassword" placeholder="패스워드">
	    		<br/>
	    		</div>
                   <div id="checkMsg"></div>                                                                     
	    		<div id="checkMsg2"></div>
	    		<input type="hidden" name="check" value="false">
               <br/>
               <div class="makeTeamdiv">
                <input type="button" value="로그인" onclick="checkform2(this)" class="makeTeam">
           		</div>
             </form>
        <div class="form-group">
	    	<ul>
		      	<li style="list-style:none;"><a href="<%= request.getContextPath() %>/users/find">
		      		ID/PW 찾기
		      		</a></li>
		      		
		    	<li style="list-style:none;"><a href="<%= request.getContextPath() %>/users/join">
		    		회원가입
		    	</a></li>
	    	</ul>
    	</div>
    	</div>
    	<script src="${pageContext.servletContext.contextPath }/js/material.min.js"></script>
</body>
</html>
