<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멘토 신청 관리</title>
</head>
<body>
<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
	    	<div class="container-fluid">
		        <div class="section-header">
		          <h3 class="section-title">마이 페이지</h3>
		          <span class="section-divider"></span>
		        </div>
     	    </div>
        
		<div class="contest-cate-list" style="line-height:250%; font-size: 15px; padding-left:15px;">
         <h4>내 정보</h4>
            <ul>
               <li><a href="<%= request.getContextPath() %>/users/memberupdate">개인정보 수정</a></li>
               <c:choose>
                  <c:when test="${authUser.userStatus eq 'Mentor' }">
                     <li><a href="<%= request.getContextPath() %>/users/menteechoose/${authUser.userNo}">멘티 관리</a></li>
                  </c:when>
                  <c:otherwise>
                     <c:choose>
                        <c:when test="${authUser.userId eq 'admin' }">
                           <li><a href="<%= request.getContextPath() %>/users/mentorconfirm">멘토 신청 관리</a></li>
                        </c:when>
                        <c:otherwise>
                           <li><a href="<%= request.getContextPath() %>/users/mentor">멘토 신청</a></li>
                        </c:otherwise>
                     </c:choose>
                  </c:otherwise>
               </c:choose>
               <li><a href="<%= request.getContextPath() %>/users/myboards?userNo=${authUser.userNo}">내가 쓴 게시글</a></li>
             <li><a href="<%= request.getContextPath() %>/users/mycomments?userNo=${authUser.userNo}">내가 쓴 댓글</a></li>
               <li><a href="<%= request.getContextPath() %>/users/withdraw">회원 탈퇴</a></li>
            </ul>
            <c:if test="${authUser.teamNo ne null }">
               <h4>팀 정보</h4>
                  <ul>
                     <c:choose>
                              <c:when test="${authUser.userStatus eq 'Mentor' }">
                                 <li><a href="<%= request.getContextPath() %>/team/teamlist?userId=${authUser.userId}">팀리스트</a></li>
                              </c:when>
                              <c:otherwise>
                                 <li><a href="<%= request.getContextPath() %>/team/teammain">팀 페이지</a></li>
                              </c:otherwise>
                           </c:choose>
                      <li><a href="<%= request.getContextPath() %>/users/leaveTeam">팀 탈퇴</a></li>
                  </ul>
           </c:if>
		</div>
<div class="mypage">
	<h3>멘토신청 관리</h3><br/>
	<table class="table4">
	   <thead>
	      <tr>
	         <th>신청자</th>
	         <th>학력</th>
	         <th>경력</th>
	         <th> </th>
	         <th> </th>
	      </tr>
	   </thead>
	   <tbody>
	      
	      <c:forEach items="${mlist}" var="mvo">
	         <tr>
		         <td>${mvo.userVo.userId }</td>
		         <td>${mvo.education }</td>
		         <td>${mvo.career }</td>
		         <td><a href="<%= request.getContextPath() %>/users/confirm/${mvo.userNo}">승인</a></td>
		         <td><a href="<%= request.getContextPath() %>/users/deletementor/${mvo.userNo}">삭제</a></td>
	      	 <tr>	
	      </c:forEach>
	      
	   </tbody>
	</table>
</div>
</body>
</html>