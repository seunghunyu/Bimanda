<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원탈퇴</title>
<script type="text/javascript">
function checkform(obj){
   // 아이디 필드 체크
   if(obj.userPassword.value.trim().length==0){
      alert("비밀번호를 입력해주세요")
      return false;
   }else {
      return true;
   }
}
</script>
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
		 <div class="mypage2">
		 <h3>회원탈퇴</h3><br>
			<p>${authUser.userName } 님 탈퇴 하시려면 비밀번호를 입력해주세요.</p><br/>
			<form method="post" action="<%=request.getContextPath() %>/users/withdrawaction"
			onsubmit="return checkform(this)">
			<input type="hidden" name="userId" value=${authUser.userId }>
			<label for="비밀번호" class="control-label">비밀번호</label><br/>
			<input type="password" name="userPassword" class="form-control" placeholder="비밀번호를 입력해주세요">
			<div class="makeTeamdiv">
				<input style="margin-left:0%;" type="submit" value="탈퇴하기" class="makeTeam">
			</div>
			</form>
		</div>
</body>
</html>