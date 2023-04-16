<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div class="margin"></div>
<footer id="footer">
   	<div class="container">
      	<div class="row">
			<div class="col-lg-6">
    			<nav class="footer-links text-lg-right text-right pt-2 pt-lg-0">
    			
    			
    				<!--  글쓰기 페이지  -->
    				
    				<c:if test="${!empty authUser && pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/recruit/rlist.jsp' }">
    					<a href="<%= request.getContextPath() %>/recruit/write">글쓰기</a>
    				</c:if>
    				
    				<c:if test="${authUser.userId eq 'admin' && pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/noticeboard.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/noticewrite">글쓰기</a>
    				</c:if>
    				<c:if test="${!empty authUser && pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/jisikboard.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/jisikwrite">글쓰기</a>
    				</c:if>
    				<c:if test="${!empty authUser && pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/freeboard.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/freewrite">글쓰기</a>
    				</c:if>
    				<c:if test="${!empty authUser && pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/ideaboard.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/ideawrite">글쓰기</a>
    				</c:if>
    				<c:if test="${!empty authUser && pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/reviewboard.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/reviewwrite">글쓰기</a>
    				</c:if>
    				
    				
    				
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/noticeboard.jsp' 
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/jisikboard.jsp' 
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/freeboard.jsp'
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/ideaboard.jsp'
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/reviewboard.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/communitymain">뒤로가기</a>
    				</c:if>
    				
    				
    				
    				<!--  content에서 board로  -->
    				
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/noticecontent.jsp'
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/noticewrite.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/noticeboard">뒤로가기</a>
    				</c:if>
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/jisikcontent.jsp'
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/jisikwrite.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/jisikboard">뒤로가기</a>
    				</c:if>
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/freecontent.jsp'
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/freewrite.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/freeboard">뒤로가기</a>
    				</c:if>
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/ideacontent.jsp'
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/ideawrite.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/ideaboard">뒤로가기</a>
    				</c:if>
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/reviewcontent.jsp'
    					|| pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/community/reviewwrite.jsp' }">
    					<a href="<%= request.getContextPath() %>/community/reviewboard">뒤로가기</a>
    				</c:if>
    				
    				
    				
    				<!--  팀만들기 메인으로  -->
    				
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/recruit/detail.jsp' }">
    					<a href="<%= request.getContextPath() %>/recruit/list">뒤로가기</a>
    				</c:if>
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/recruit/modify.jsp' }">
    					<a href="<%= request.getContextPath() %>/recruit/list">취소</a>
    				</c:if>
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/recruit/recruit.jsp' }">
    					<a href="<%= request.getContextPath() %>/recruit/list">취소</a>
    				</c:if>
    				
    				
    				<!--  팀메인 페이지로  -->
    				<c:if test="${ pageContext.request.requestURI eq '/Bimanda/WEB-INF/views/recruit/teamsuccess.jsp' }">
    					<a href="<%= request.getContextPath() %>/team/teammain">팀 페이지</a>
    				</c:if>
    				
   					<a href="<%= request.getContextPath() %>/">메인으로</a>
   				</nav>
    		</div>
    	</div>
    </div>
</footer>
