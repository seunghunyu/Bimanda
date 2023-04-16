<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ��Ż��</title>
<script type="text/javascript">
function checkform(obj){
   // ���̵� �ʵ� üũ
   if(obj.userPassword.value.trim().length==0){
      alert("��й�ȣ�� �Է����ּ���")
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
		          <h3 class="section-title">���� ������</h3>
		          <span class="section-divider"></span>
		        </div>
     	    </div>
	<div class="contest-cate-list" style="line-height:250%; font-size: 15px; padding-left:15px;">
         <h4>�� ����</h4>
            <ul>
               <li><a href="<%= request.getContextPath() %>/users/memberupdate">�������� ����</a></li>
               <c:choose>
                  <c:when test="${authUser.userStatus eq 'Mentor' }">
                     <li><a href="<%= request.getContextPath() %>/users/menteechoose/${authUser.userNo}">��Ƽ ����</a></li>
                  </c:when>
                  <c:otherwise>
                     <c:choose>
                        <c:when test="${authUser.userId eq 'admin' }">
                           <li><a href="<%= request.getContextPath() %>/users/mentorconfirm">���� ��û ����</a></li>
                        </c:when>
                        <c:otherwise>
                           <li><a href="<%= request.getContextPath() %>/users/mentor">���� ��û</a></li>
                        </c:otherwise>
                     </c:choose>
                  </c:otherwise>
               </c:choose>
               <li><a href="<%= request.getContextPath() %>/users/myboards?userNo=${authUser.userNo}">���� �� �Խñ�</a></li>
             <li><a href="<%= request.getContextPath() %>/users/mycomments?userNo=${authUser.userNo}">���� �� ���</a></li>
               <li><a href="<%= request.getContextPath() %>/users/withdraw">ȸ�� Ż��</a></li>
            </ul>
            <c:if test="${authUser.teamNo ne null }">
               <h4>�� ����</h4>
                  <ul>
                     <c:choose>
                              <c:when test="${authUser.userStatus eq 'Mentor' }">
                                 <li><a href="<%= request.getContextPath() %>/team/teamlist?userId=${authUser.userId}">������Ʈ</a></li>
                              </c:when>
                              <c:otherwise>
                                 <li><a href="<%= request.getContextPath() %>/team/teammain">�� ������</a></li>
                              </c:otherwise>
                           </c:choose>
                      <li><a href="<%= request.getContextPath() %>/users/leaveTeam">�� Ż��</a></li>
                  </ul>
           </c:if>
		</div>
		 <div class="mypage2">
		 <h3>ȸ��Ż��</h3><br>
			<p>${authUser.userName } �� Ż�� �Ͻ÷��� ��й�ȣ�� �Է����ּ���.</p><br/>
			<form method="post" action="<%=request.getContextPath() %>/users/withdrawaction"
			onsubmit="return checkform(this)">
			<input type="hidden" name="userId" value=${authUser.userId }>
			<label for="��й�ȣ" class="control-label">��й�ȣ</label><br/>
			<input type="password" name="userPassword" class="form-control" placeholder="��й�ȣ�� �Է����ּ���">
			<div class="makeTeamdiv">
				<input style="margin-left:0%;" type="submit" value="Ż���ϱ�" class="makeTeam">
			</div>
			</form>
		</div>
</body>
</html>