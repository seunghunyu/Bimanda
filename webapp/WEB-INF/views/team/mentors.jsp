<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멘토 리스트</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div class="section-header">
          <h3 class="section-title">멘토 리스트</h3>
          <span class="section-divider"></span>
	      <br/>
        </div>
	<div class="contest-list">     
		<table class="table">
		   <thead>
		      <tr>
		         <th>이름</th>
		         <th>학력</th>
		         <th>경력</th>
		         <th>점수</th>
		         <th> </th>
		      </tr>
		   </thead>
		   <tbody>
		      <c:forEach items="${mlist}" var="mvo">
		         <c:if test="${mvo.score > 0 }">
		            <tr>
		               <td>${mvo.userVo.userName}</td>
		               <td>${mvo.education}</td>
		               <td>${mvo.career}</td>
		               <td>${mvo.score}</td>
		               <td><a href="<%= request.getContextPath() %>/team/apply/${authUser.teamNo}/${mvo.userNo}">신청</a></td>
		            </tr>
		         </c:if>
		      </c:forEach>
		   </tbody>
		</table>
	</div>
</body>
</html>