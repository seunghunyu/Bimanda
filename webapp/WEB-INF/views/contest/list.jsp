<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta charset="UTF-8">
<title>공모전 현황</title>
</head>
<body>
 
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
 <div class="container-fluid">
        <div class="section-header">
          <h3 class="section-title">공모전 현황</h3>
          <span class="section-divider"></span>
        </div>
        </div>
        
	<div class="contest-cate-list">
		<c:forEach items="${cateList }" var="cate">
			<ul>
				<li><a href="${pageContext.servletContext.contextPath }/Contest?c=${cate.cateNo}">${cate.cateName }</a></li> 
			</ul>
		</c:forEach>
	</div>

	<div class="contest-list">
	 <table class="table">
	 	<thead>
	 		<tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
	 			<th class="text-left">번호</th>
	 			<th class="text-left">제목</th>
	 		</tr>
	 	</thead>
	 	<tbody>
		 <c:forEach items="${file }" var="list" varStatus="status">
		 <%
	 	Long cateNo = (Long)request.getAttribute("cateNo"); // 컨트롤러로부터 cateNo을 받음
	 	%>
	 	<tr class="">
	 		<td class="text-left">${10*(pageNo-1)+pageNum[status.index]}</td>
	 		<td class="text-left">
		 	<ul>
		 		<li><a href="${pageContext.servletContext.contextPath }/Contest/detail?c=${cateNo }&n=${list.get(0)}" style="color:#000;">${list.get(1) }</a></li> 
		 	</ul>	
		 	</td>
		 	</tr>
		 </c:forEach>	<!-- 현재 cateNo과 글 목록의 index 파라미터를 컨트롤러로 넘겨줌 (list: 카테고리의 글 목록, list.get(0): 글의 index, list.get(1): 글의 제목 -->
		 	</tbody>
		 	</table>
		 	
	<div class="text-center">
    <c:if test="${pageNo ge 6 }">
        <a class="asd" href="${pageContext.servletContext.contextPath }/Contest?c=${cateNo}&next=-2"><<</a> 
    </c:if>
    <c:if test="${pageNo ge 6}">
        <a class="asd" href="${pageContext.servletContext.contextPath }/Contest?c=${cateNo}&next=-1">이전</a> 
    </c:if>
    <c:forEach var="pageNum" begin="${startPage }" end="${endPage }">
        <c:choose>
            <c:when test="${pageNum eq pageNo}">
                <span style="font-weight: bold;">
                <a class="asd" href="${pageContext.servletContext.contextPath }/Contest?c=${cateNo}&pageNo=${pageNum}" onclick="return false;">${pageNum }</a></span> 
            </c:when>
            <c:otherwise>
                <a class="asd" href="${pageContext.servletContext.contextPath }/Contest?c=${cateNo}&pageNo=${pageNum}">${pageNum }</a> 
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${pageRange > endPage}">
        <a class="asd" href="${pageContext.servletContext.contextPath }/Contest?c=${cateNo}&next=1">다음</a> 
    </c:if>
    <c:if test="${pageRange > endPage}">
        <a class="asd" href="${pageContext.servletContext.contextPath }/Contest?c=${cateNo}&next=2">>></a> 
    </c:if>
</div>
</div>
</body>
</html>

