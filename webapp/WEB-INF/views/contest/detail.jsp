<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
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
        <br/>
	<div class="contest-cate-list">
		<c:forEach items="${cateList }" var="cate">
			<ul>
				<li><a href="${pageContext.servletContext.contextPath }/Contest?c=${cate.cateNo}">${cate.cateName }</a></li> 
			</ul>
		</c:forEach>
	</div>
	
	<div class="detail">
	<table class="table">
	<%
	int contNo = (int)request.getAttribute("contNo");	// 컨트롤러로부터 contNo(공모전 넘버)를 받음
	%>
	<thead>
		<tr>
			<th class="text-left" style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF;line-height:100%;">${file.get(contNo).get(1) }</th>
		</tr>
		<tr>
			<th class="text-left" style="line-height:100%;"><a target="_blank" href="${file.get(contNo).get(2) }">홈페이지 바로가기</a></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><a target="_blank" href="${pageContext.servletContext.contextPath }/img/${file.get(contNo).get(3) }"><img class="image" src="${pageContext.servletContext.contextPath }/img/${file.get(contNo).get(3) }"></a></td>
		</tr>
		
	</tbody>
</table>
	<div class="pcontent">
		<p style="margin-left:10%;">${content.get(contNo) }</p>
	</div>
</div>
</body>
</html>
