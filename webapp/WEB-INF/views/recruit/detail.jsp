<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀원모집글</title>
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
<script type="text/javascript">
function checkReq(btn){
	if (btn.form.content.value.trim().length==0){
		alert("내용을 입력해주세요")
		return;
	} 
	//jQuery Ajax 수행
	$.ajax({
		url: "${pageContext.servletContext.contextPath}/recruit/req/check",
		type: "post",
		data:{no: btn.form.no.value,
			  userNo: btn.form.userNo.value,
			  content: btn.form.content.value
			 },
		success: function(result){
			if (result == "fail") {
				alert("이미 신청되었습니다.")
			} else {
				document.requestForm.submit()
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
	if ($("input:checkbox[name=userSelect]:checked").length < 1) {
		alert("1명 이상 체크해주세요")
		return false;
	}
	else {
		return true;
	}
}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div class="section-header">
          <h3 class="section-title">${vo.title}</h3>
          <span class="section-divider"></span>
	      <br/>
        </div>
<div class="contest-list">
	<table class="table">
    	<thead>
    		<tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
    			<th class="text-left">작성자: ${vo.userVo.userName }&nbsp;&nbsp;|&nbsp;&nbsp;${vo.regDate }&nbsp;&nbsp;|&nbsp;&nbsp;조회: ${vo.hit_cnt }</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr class="zxc">
    			<td class="content2">${vo.content}</td>
    		</tr>
    	</tbody>
    
    </table>
	<div class="margin"></div>
		
	<h6>신청 목록</h6>
		<table class="table">
		<tbody>
		
		<form:form method="POST" action="${pageContext.servletContext.contextPath}/recruit/confirm"
			onsubmit="return checkform(this)">
			<c:if test="${not empty authUser}">
			<c:if test="${authUser.userNo == vo.userNo}">
				<input class="makeTeam" type="submit" value="팀만들기" style="margin-left:5%;">
			</c:if>
		</c:if>
		<th> </th>
		<th>제목</th>
		<th>신청자명</th>
		<th>지역</th>
		<th>신청일</th>
		<th> </th>
		<th> </th>
		<c:forEach items="${list}" var="rvo">
				<tr>
					<td>
					<c:if test="${not empty authUser}">
						<c:if test="${authUser.userNo == vo.userNo}">
							<c:if test="${authUser.userNo != rvo.userNo}">
								<input type="checkbox" name="userSelect" value="${rvo.userNo}">
							</c:if>
						</c:if>
					</c:if>
					</td>
					<td>${rvo.content}</td>
					<td>${rvo.userVo.userName}</td>
					<td>${rvo.userVo.locationVo.userLocation }</td>
					<td>2019-12-12</td>
					<td>
					<c:if test="${not empty authUser}">
						<c:if test="${authUser.userNo == rvo.userNo}">
							<a href="<%= request.getContextPath() %>/recruit/req/modify/${rvo.reqNo}">수정</a>
						</c:if>
					</c:if>
					</td>
					<td>
					<c:if test="${not empty authUser}">
						<c:if test="${authUser.userNo == rvo.userNo}">
							<a href="<%= request.getContextPath() %>/recruit/req/delete/${rvo.reqNo}" 
								onclick="return delete_confirm()">삭제</a>
						</c:if>
					</c:if>
					</td>
				</tr>
			
	</c:forEach>
	</form:form>
	</table>
	<br/><br/>
	<div class="">
		<c:if test="${not empty authUser}">
			<form:form modelAttribute="requestVo" id="requestForm" name="requestForm" method="POST"
				action="${pageContext.servletContext.contextPath}/recruit/req/write">
   				<input type="hidden" name="userNo" value="${authUser.userNo}">
   				<input type="hidden" name="no" value="${vo.no}">
				<div class="makeTeamdiv2">
					<label for="content">${authUser.userId}  </label>
					<input type="text" name="content" placeholder="입력하세요"/>
				</div>
	   			<div class="makeTeamdiv" style="margin-left:-74%; ">
	   				<input class="makeTeam" type="button" value="신청하기" onclick="checkReq(this)">
	   			</div>
   			</form:form>
   		</c:if>
   		<div class="back">
   		<ul>
   			<li><a href="<%= request.getContextPath() %>/recruit/list">목록으로</a></li>
   			</ul>
   			</div>
	</div>
	</div>
</body>
</html>