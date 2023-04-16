<%@ page language="java" contentType="text/html; charset=UTF-8" 
   pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멘토 신청</title>
<script type="text/javascript"
   src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function freeCheckForm(btn){
   // 아이디 필드 체크
   if(btn.form.career.value.trim().length==0){
      alert("경력을 입력해주세요")
      return;
   }else {
      document.mentorRegister.submit()
   }
}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />
<div class="section-header">
          <h3 class="section-title">멘토 신청</h3>
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
		<div class="mypage2">
			<h3>멘토 신청</h3>
			<form:form modelAttribute="mentorVo" method="post"
			   action="${pageContext.servletContext.contextPath }/users/mentor"
			   name="mentorRegister">
			   
			   <input type="hidden" name="userNo" value="${authUser.userNo}"><br/>
			   
			         <label for="education" class="control-label">학력</label>
			          <input type="text" name="education" placeholder="최종학력을 입력해주세요" 
			             class="form-control" /><br/>
			    
			         <label for="career" class="control-label">경력</label>
			         <textarea class="form-control" name="career" 
			            placeholder="경력을 입력해주세요" rows="5"></textarea><br/>
			   
			   <div class="makeTeamdiv" >
				   			<input class="makeTeam" type="button" value="신청하기" 
			         onclick="freeCheckForm(this)">
			   			</div>
			</form:form>
		</div>
</body>
</html>