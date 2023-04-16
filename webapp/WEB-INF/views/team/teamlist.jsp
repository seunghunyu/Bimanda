<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀리스트</title>
</head>
<body>
<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
	 <div class="section-header">
          <h3 class="section-title">팀 리스트</h3>
          <span class="section-divider"></span>
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
		<table class="table4" style="width: 80%;">
			<thead>
				<tr>
					<th>팀명</th>
				</tr>
			</thead>
			<tbody>
			   <c:forEach items="${tlist}" var="tvo">
			      <tr>
			         <td><a href="<%= request.getContextPath() %>/team/teamchoose/${tvo.teamNo}?userId=${authUser.userId}">${tvo.teamName}</a></td>
			      </tr>
			   </c:forEach>
		   </tbody>
		</table>
</div>
</body>
</html>