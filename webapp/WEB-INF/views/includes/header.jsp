<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c"%>  
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>Bimanda Main</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="${pageContext.servletContext.contextPath}/images/favicon.png" rel="icon">
  <link href="${pageContext.servletContext.contextPath}/images/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,700|Open+Sans:300,300i,400,400i,700,700i" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="${pageContext.servletContext.contextPath}/lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="${pageContext.servletContext.contextPath}/lib/aos/aos.css" rel="stylesheet">
  <link href="${pageContext.servletContext.contextPath}/lib/magnific-popup/magnific-popup.css" rel="stylesheet">


  <!-- Main Stylesheet File -->
  <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
  <link href="${pageContext.servletContext.contextPath}/css/material-kit.css" rel="stylesheet">
	
    
  <!-- =======================================================
    Theme Name: Avilon
    Theme URL: https://bootstrapmade.com/avilon-bootstrap-landing-page-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>

  <!--==========================
    Header
  ============================-->
  <header id="header">
    <div class="container">

      <div id="logo" class="pull-left">
        <h1><a href="<%= request.getContextPath() %>" class="scrollto">BIMANDA</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="#intro"><img src="img/logo.png" alt="" title="" /></img></a> -->
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li class="menu-active"><a href="<%= request.getContextPath() %>">Home</a></li>
          <li><a href="<%= request.getContextPath() %>/about">About Us</a></li>
          <li><a href="<%= request.getContextPath() %>/contents/contentsmain?userId=${authUser.userId}&stay=person">컨텐츠</a></li>
          <li><a href="<%= request.getContextPath() %>/Contest">공모전 현황</a></li>
          <li><a href="<%= request.getContextPath() %>/community/communitymain">커뮤니티</a></li>
          <c:choose>
             <c:when test="${empty authUser }">
               <li class="menu-has-children"><a href="#" onclick="return false;">계정</a>
               <ul>
                  <li><a href="<%= request.getContextPath() %>/users/join">회원가입</a></li>
                  <li><a href="<%= request.getContextPath() %>/users/loginform">로그인</a></li>
               </ul>
               </li>
         </c:when>
         <c:otherwise>
            <c:choose>
                   <c:when test="${authUser.teamNo == null }">
                     <li class="menu-has-children"><a href="" a href="#"  onclick="return false;">${authUser.userName }</a>
                  <ul>
                     <li><a href="<%= request.getContextPath() %>/users/logout">로그아웃</a></li>
                     <li><a href="<%= request.getContextPath() %>/users/mypage?userId=${authUser.userId}">마이페이지</a></li>
                     <li><a href="<%= request.getContextPath() %>/recruit/list">팀만들기</a></li>
                  </ul>
                      </li>
               </c:when>
               <c:otherwise>
                  <li class="menu-has-children"><a href="" a href="#"  onclick="return false;">${authUser.userName }</a>
                  <ul>
                     <li><a href="<%= request.getContextPath() %>/users/logout">로그아웃</a></li>
                     <li><a href="<%= request.getContextPath() %>/users/mypage?userId=${authUser.userId}">마이페이지</a></li>
                     <c:choose>
                        <c:when test="${authUser.userStatus eq 'Mentor' }">
                           <li><a href="<%= request.getContextPath() %>/team/teamlist?userId=${authUser.userId}">팀리스트</a></li>
                        </c:when>
                        <c:otherwise>
                           <li><a href="<%= request.getContextPath() %>/team/teammain">팀페이지</a></li>
                        </c:otherwise>
                     </c:choose>
                  </ul>
                      </li>
               </c:otherwise>
              </c:choose>
         </c:otherwise>
        </c:choose>
        </ul>
      </nav><!-- #nav-menu-container -->
    </div>
  </header><!-- #header -->
	<br/><br/><br/><br/>
  <!--==========================
    Intro Section
  ============================-->
<br/>

  <!-- JavaScript Libraries -->
  <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery-migrate.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/easing/easing.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/aos/aos.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/superfish/hoverIntent.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/superfish/superfish.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/magnific-popup/magnific-popup.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap-carousel.js"></script>
  
  <!-- Contact Form JavaScript File -->
  <script src="${pageContext.servletContext.contextPath}/contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="${pageContext.servletContext.contextPath }/js/main.js"></script>
  <script src="${pageContext.servletContext.contextPath }/js/bootstrap.min.js"></script>
  <script src="${pageContext.servletContext.contextPath }/js/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath }/js/material-kit.js"></script>
  <script src="${pageContext.servletContext.contextPath }/js/material.min.js"></script>
  
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>