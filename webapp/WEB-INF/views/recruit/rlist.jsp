<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀원모집글 리스트</title>
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
</head>
<body>
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
	 <div class="section-header">
          <h3 class="section-title">팀원모집글 리스트</h3>
          <span class="section-divider"></span>
        </div>
        <br/>
    <div class="contest-list">
	<table class="table">
		<thead>
			<tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
				<th class="text-left">번호</th>
				<th class="text-left">제목</th>
				<th class="text-left">작성자</th>
				<th class="text-left">날짜</th>
				<th class="text-left">조회수</th>
				<th>     </th>
				<th>     </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${rlist}" var="vo" varStatus="status">
		<tr class="zxc">
			<td>${pageNum[status.index]}</td>
			<td><a href="<%= request.getContextPath() %>/recruit/detail?no=${vo.no}">${vo.title}</a></td>
			<td>${vo.userVo.userName}</td>
			<td>${vo.regDate }</td>
			<td>${vo.hit_cnt }</td>
			<td class="buttons">
				<c:if test="${not empty authUser}">
					<c:if test="${authUser.userNo == vo.userNo}">
						<button type="button" rel="tooltip" title="수정" class="btn btn-success btn-simple btn-xs"
							onclick="location.href='<%= request.getContextPath() %>/recruit/modify?no=${vo.no}'">
							<i class="fa fa-edit"></i>
						</button>
					</c:if>
				</c:if>
			</td>
			<td class="buttons">
				<c:if test="${not empty authUser}">
					<c:if test="${authUser.userNo == vo.userNo}">
						<a href="<%= request.getContextPath() %>/recruit/delete?no=${vo.no}" 
							rel="tooltip" title="삭제" class="btn btn-danger btn-simple btn-xs"
							onclick="return delete_confirm()">
                    		<i class="fa fa-times"></i>
                		</a>
					</c:if>
				</c:if>
			</td>
		</c:forEach>
		</tbody>
	</table>
	
	<a style="margin-top:200px; margin-left:850px; font-weight: bold; font-size: 15px; color:#666;"href="<%= request.getContextPath() %>/recruit/write">글쓰기</a>
	</div>
</body>
</html>