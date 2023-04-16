<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티메인</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<section id="more-features" class="section-commu">
      <div class="container">
      
        <div class="section-header">
          <h3 class="section-title">커뮤니티</h3>
          <span class="section-divider"></span>
          <p class="section-description">각종 커뮤니티를 이용해보세요</p>
        </div>

        <div class="row">
        
          <div class="community-col">
            <div class="box" data-aos="fade-right">
              <div class="icon"><i class="ion-ios-stopwatch-outline"></i></div>
              <h4 class="title"><a href="<%= request.getContextPath() %>/community/noticeboard">공지사항</a></h4>
              <p class="description">공지사항을 확인할 수 있는 공간입니다</p>
            </div>
          </div>

          <div class="community-col">
            <div class="box" data-aos="fade-left">
              <div class="icon"><i class="ion-ios-analytics-outline"></i></div>
              <h4 class="title"><a href="<%= request.getContextPath() %>/community/jisikboard">질문게시판</a></h4>
              <p class="description">궁금한 사항들을 묻고 답하는 공간입니다</p>
            </div>
          </div>

          <div class="community-col">
            <div class="box" data-aos="fade-right">
              <div class="icon"><i class="ion-ios-heart-outline"></i></div>
              <h4 class="title"><a href="<%= request.getContextPath() %>/community/freeboard">자유게시판</a></h4>
              <p class="description">자유롭게 대화할 수 있는 공간입니다</p>
            </div>
          </div>

          <div class="community-col">
            <div class="box" data-aos="fade-left">
              <div class="icon"><i class="ion-social-buffer-outline"></i></div>
              <h4 class="title"><a href="<%= request.getContextPath() %>/community/ideaboard">아이디어게시판</a></h4>
              <p class="description">다른 사람들과 아이디어를 공유하는 공간입니다</p>
            </div>
          </div>

		  <div class="community-col">
            <div class="box" data-aos="fade-right">
              <div class="icon"><i class="ion-ios-bookmarks-outline"></i></div>
              <h4 class="title"><a href="<%= request.getContextPath() %>/community/reviewboard">공모전후기</a></h4>
              <p class="description">공모전 후기를 나누는 공간입니다</p>
            </div>
          </div>
          
        </div>
      </div>
    </section>
<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
</body>
</html>