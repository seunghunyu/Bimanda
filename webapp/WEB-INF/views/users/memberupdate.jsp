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
<title>개인정보 수정</title>
<script type="text/javascript">
function checkform(obj){
	if (obj.keyName.value.trim().length==0){
		alert("키워드를 입력해주세요")
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
			<h3>개인정보 수정</h3>	
			<form method="post" action="<%=request.getContextPath() %>/users/memberupdateaction" name="updateCheckForm">
				<input type="hidden" name="userId" value=${authUser.userId }><br>
				<label for="이름" class="control-label">이름</label>
				<input type="text" name="userName" class="form-control"  placeholder="이름을 입력해주세요"><br/>
				<label for="비밀번호" class="control-label" >비밀번호</label>
				<input type="text" name="userPassword" class="form-control" placeholder="비밀번호를 입력해주세요"><br>
				<label for="번호" class="control-label">핸드폰 번호(-없이 입력해주세요.)</label>
				<input type="text" name="userPhone" class="form-control" placeholder="번호를 입력해주세요"><br>
				<div class="makeTeamdiv">
					<input style="margin-left:0%;" type="button" value="수정" onclick="updateCheck(this)" class="makeTeam">
				</div>
			</form>
		</div>
</body>
</html>