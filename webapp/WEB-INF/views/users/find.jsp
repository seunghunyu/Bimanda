<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function finalcheckform(btn){
	//var aN='${authNumber}';
	if (btn.form.authNumber.value.trim().length==0){
		alert("인증번호를 입력해주세요");
		return;
	}else if (btn.form.userName.value.trim().length==0){
		alert("이름을 입력해주세요")
		return;
	}else if (btn.form.userPhone.value.trim().length==0){
		alert("핸드폰번호를 입력해주세요")
		return;
	}

	//jQuery Ajax 수행
	$.ajax({
		url: "${pageContext.servletContext.contextPath}/users/auth",
		type: "post",
		data:{userName: btn.form.userName.value,
			  userPhone: btn.form.userPhone.value,
			  authNumber: btn.form.authNumber.value
			  },
		success: function(res){
			if (res!=null){
				$('#checkMsg3').html(res)
				
			}else {
				$('#checkMsg3').html("<p>본인확인 실패 인증번호를 확인해주세요</p>")	
			}
		},
		error: function(xhr, status, error){
			alert(status);
			alert(error);
		}
	});
	
}
function authcheckform(btn){
	// 아이디 필드 체크
	var aN;
	if (btn.form.userName.value.trim().length==0){
		alert("이름을 입력해주세요")
		return;
	}else if (btn.form.userPhone.value.trim().length==0){
		alert("핸드폰번호를 입력해주세요")
		return;
	}
	$.ajax({
		url: "${pageContext.servletContext.contextPath}/users/sendSms",
		type: "post",
		dataType: "json",
		data:{userName: btn.form.userName.value,
			  userPhone: btn.form.userPhone.value
			  },
		success: function(result){
			if (result != null){
				$('#checkMsg').html("<p>전송성공</p>")
				$('#checkMsg2').html(result)
			
			}else if(result == "fail"){
				$('#checkMsg').html("<p>전송실패</p>")
				$('#checkMsg2').html("<p>이름/핸드폰번호를 확인해주세요</p>")
				
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
<div id="header">
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
 </div>
 <br/><br/><br/><br/>
 
 
 <div class="col-sm-2">
            <div class="form-group" >
		<h3>아이디 비밀번호찾기</h3>			
		<form method="POST" action="<%=request.getContextPath() %>/users/auth"	name="auth" > <!-- 인증번호 받기  -->
  		<div class="form-row">
  		<label for="이름" class="control-label">이름</label>
   		<input type="text" name="userName" class="form-control"><br>
   		
   		<label for="핸드폰번호" class="control-label">핸드폰번호</label>
   		<input type="text" name="userPhone" class="form-control"><br>
   		회원정보에 등록한 휴대전화 번호와 입력한 휴대전화 번호가 같아야, 인증번호를 받을 수 있습니다(-없이 입력하세요).
   		<br/>
   		</div>
	   		<div class="makeTeamdiv">
		   		<input type="button" value="인증번호받기" onclick="authcheckform(this)"
		   		class="makeTeam">
		   	</div>
	   	<br/>
   	    <div id="checkMsg"></div>
		<div id="checkMsg2"></div><br/>
		<div class="form-row">
   	    <label for="인증번호" class="control-label">인증번호</label>
   	    <input type="text" name="authNumber" class="form-control"><br>
   	    <!--  input type="hidden" name="check" value="false"-->
		</div>
		 <div class="makeTeamdiv">
			<input type="button" value="확인" onclick="finalcheckform(this)"
			class="makeTeam">
		</div>
</form>
<div id="checkMsg3"></div>
</div>
</div>

		
</body>
</html>