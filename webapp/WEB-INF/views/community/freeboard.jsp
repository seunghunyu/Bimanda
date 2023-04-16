<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
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
          <h3 class="section-title">자유게시판</h3>
          <span class="section-divider"></span>
        </div>
        <br/>
        <div class="commu-cate-list">
			<ul>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/noticeboard">공지사항</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/jisikboard">질문게시판</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/freeboard">자유게시판</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/ideaboard">아이디어게시판</a></li>
				<li><a style="color:#000;" href="${pageContext.servletContext.contextPath }/community/reviewboard">공모전후기</a></li> 
			</ul>
			</div>
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
		<c:forEach items="${list }" var="list" varStatus="status">
		<tr class="zxc">
			<td class="text-left">${pageNum[status.index]}</td>
	    	<td class="title"><a href="<%=request.getContextPath() %>/community/readcontent?boardNo=${list.boardNo}">${list.boardTitle }</a></td>
	    	<td class="text-left">${list.userVo.userName }</td>
	    	<td class="text-left">${ list.regDate}</td>
	    	<td class="text-left">${ list.hit_cnt}</td>
	    	<td class="">
	    		<c:if test="${not empty authUser}">
					<c:if test="${authUser.userNo == list.userNo}">
					<button type="button" rel="tooltip" title="수정" class="btn btn-success btn-simple btn-xs"
						onclick="location.href='<%= request.getContextPath() %>/community/modify?boardNo=${list.boardNo}'">
						<i class="fa fa-edit"></i>
					</button>
				</c:if>
			</c:if>
			</td>
			<td class="">
			<c:if test="${not empty authUser}">
				<c:if test="${authUser.userNo == list.userNo}">
					<a href="<%= request.getContextPath() %>/community/delete?boardNo=${list.boardNo}" 
						rel="tooltip" title="삭제" class="btn btn-danger btn-simple btn-xs"
						onclick="return delete_confirm()">
                    	<i class="fa fa-times"></i>
                	</a>
				</c:if>
			</c:if>
			</td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
    <c:if test="${!empty authUser && pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/freeboard.jsp' }">
		<a class="makeTeam" style="margin-left:830px; font-weight: bold; font-size: 15px; color:#fff;" href="<%= request.getContextPath() %>/community/freewrite">글쓰기</a>
	</c:if>
    </div>
</body>
</html>