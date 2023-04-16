<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link type="text/css" 
	rel="stylesheet" 
	href="<c:url value="/css/main.css" />"/>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function checkId(btn){
	// 아이디 필드 체크
	if (btn.form.userId.value.trim().length==0){
		alert("아이디를 입력해주세요")
		return;
	}
	
	//jQuery Ajax 수행
	$.ajax({
		url: "${pageContext.servletContext.contextPath}/users/idcheck",
		type: "get",
		dataType: "json",
		data:{userId: btn.form.userId.value},
		success: function(response){
			if (response.data == "exists"){
				$('#checkMsg3').html("<p>사용할 수 없는 아이디입니다.</p>")
				btn.form.check.value = "false"		
			}else{
				$('#checkMsg3').html("<p>사용할 수 있는 아이디입니다.</p>")
				btn.form.check.value = "true"
			}
		},
		error: function(xhr, status, error){
			alert(status);
			alert(error);
		}
	});
	
}
</script>
<script type="text/javascript">
function checkform(obj){
	if (obj.userName.value.trim().length==0){
		alert("이름을 입력해주세요")
	}else if (obj.userId.value.trim().length==0){
		alert("아이디를 입력해주세요")
	}else if (obj.check.value == "false") {
		alert("아이디 중복체크를 확인해주세요.")
	}else if (obj.userPassword.value.trim().length==0){
		alert("비밀번호를 입력해주세요")
	}else if (obj.userPhone.value.trim().length==0){
		alert("핸드폰번호를 입력해주세요")
	}else if (obj.locationNo.value.trim().length==0){
		alert("지역을 선택해주세요")
	}else if(obj.terms.checked == false){
		alert("약관에 동의를 해주세요")
	} else{
		return true;
	}
	return false;
}
</script>
</head>
<body>
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
   	
   	<div class="col-sm-2">
   		<form:form modelAttribute="userVo" id="join-form" name="joinForm" method="POST"
				action="${pageContext.servletContext.contextPath }/users/joinaction"
				onsubmit="return checkform(this)">
				<div class="form-row">
				<div class="form-group" >
   			<label for="userName" class="control-label">이름</label>
			<form:input path="userName" class="form-control" style="width:150%;"/>
			</div>
			<br/>
			<!-- TODO: validation -->   		
			<input type="hidden" name="check" value="false">
			<div class="form-group" >
			<label for="userId" class="control-label">아이디</label>
			<form:input path="userId" class="form-control" style="width:150%;"/>
			</div>
			</div>
			<br/>
			<div class="makeTeamdiv">
				<input type="button" value="id 중복체크" onclick="checkId(this)"
				class="makeTeam">
			</div>
			<br/>
			<div id="checkMsg3"></div>
			
			<div class="form-row">
			<div class="form-group" >
   			<label for="userPassword" class="control-label">비밀번호</label>
   			<input type="password" name="userPassword" class="form-control" style="width:150%;"/>
   			</div>
   			<br/>
   			<div class="form-group" >
   			<label for="userPhone" class="control-label">핸드폰번호</label>
   			<input type="text" name="userPhone" class="form-control" style="width:150%;"/>
   			</div>
   			
   			<div id="">
   			<br/>
   			<div class="form-group" >
	   		<label for="userSex" class="control-label">성별 &nbsp;&nbsp; </label>
    		<input type='radio' name='userSex' id="optionsRadios1" value='female' checked/>여성&nbsp;&nbsp;
  			<input type='radio' name='userSex' id="optionsRadios1" value='male' />남성
  			</div>
  			<br/><br/>
  			</div>
  			</div>
  			지역을 선택하세요
  			<select name="locationNo" class="form-control" style="width:80%;">
				<option value='' selected>------</option>
					<c:forEach items="${loList }" var="loList">
						<option  value="${loList.locationNo }">${loList.userLocation }</option>
					</c:forEach>	
			</select>
  			<br/><br/>
   			<input type="checkbox" name="terms" >서비스 약관에 동의합니다.
   			<br/><br/>
   			<div class="makeTeamdiv">
   				<input type="submit" value="회원가입하기" class="makeTeam">
   			</div>
   			<div class="margin"></div>
   		</form:form>
   		
   	</div>
   	
   	<script src="${pageContext.servletContext.contextPath }/js/material.min.js"></script>
</body>
</html>