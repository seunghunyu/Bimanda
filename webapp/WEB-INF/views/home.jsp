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
  <link href="images/favicon.png" rel="icon">
  <link href="images/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,700|Open+Sans:300,300i,400,400i,700,700i" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="lib/aos/aos.css" rel="stylesheet">
  <link href="lib/magnific-popup/magnific-popup.css" rel="stylesheet">


  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">

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
          <li class="menu-active"><a href="#intro">Home</a></li>
          <li><a href="#about">About Us</a></li>
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
                   <c:when test="${authUser.teamNo == null and authUser.userStatus ne 'Mentor' }">
                     <li class="menu-has-children"><a href="#"  onclick="return false;">${authUser.userName }</a>
                  <ul>
                     <li><a href="<%= request.getContextPath() %>/users/logout">로그아웃</a></li>
                     <li><a href="<%= request.getContextPath() %>/users/mypage?userId=${authUser.userId}">마이페이지</a></li>
                     <li><a href="<%= request.getContextPath() %>/recruit/list">팀만들기</a></li>
                  </ul>
                      </li>
               </c:when>
               <c:otherwise>
                  <li class="menu-has-children"><a href="#"  onclick="return false;">${authUser.userName }</a>
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
        	<li><a href="#contact">Contact Us</a></li>
        </ul>
      </nav><!-- #nav-menu-container -->
    </div>
  </header><!-- #header -->

  <!--==========================
    Intro Section
  ============================-->
  <section id="intro">

    <div class="intro-text">
      <h2>비만다에 오신걸 환영합니다</h2>
      <p>B I M A N D A</p>
      <a href="#about" class="btn-get-started scrollto">비만다 시작하기</a>
    </div>

    <div class="product-screens">

      <div class="product-screen-1" data-aos="fade-up" data-aos-delay="400">
        <img src="images/product-screen-1.png" alt="">
      </div>

      <div class="product-screen-2" data-aos="fade-up" data-aos-delay="200">
        <img src="images/product-screen-2.png" alt="">
      </div>

      <div class="product-screen-3" data-aos="fade-up">
        <img src="images/product-screen-3.png" alt="">
      </div>

    </div>

  </section><!-- #intro -->

  

    <!--==========================
      About Us Section
    ============================-->
    <section id="about" class="section-bg">
      <div class="container-fluid">
        <div class="section-header">
          <h3 class="section-title2">About Us</h3>
          <span class="section-divider"></span>
          <p class="section-description">
               비만다
          </p>
        </div>

        <div class="row">
          <div class="col-lg-6 about-img" data-aos="fade-right">
            <img src="images/about-img.jpg" alt="">
          </div>

          <div class="col-lg-6 content" data-aos="fade-left">
            <h2>협업툴과 공모전 정보를 함께 제공하는 서비스</h2>
            <h3>여러 정보를 비만다에서 한 번에 찾을 수 있습니다.</h3>
            <p>
                 키워드에 따라 다양한 정보를 얻을 수 있습니다.
            </p>

            <ul>
              <li><i class="ion-android-checkmark-circle"></i> 키워드를 통한 팀원, 멘토 추천</li>
              <li><i class="ion-android-checkmark-circle"></i> 언제든 나에게 맞는 공모전 정보 확인 가능</li>
              <li><i class="ion-android-checkmark-circle"></i> 프로젝트 생성부터 진행까지 가능한 협업툴 기능 제공</li>
            </ul>

            <p>
                 프로젝트에만 집중할 수 있도록
            </p>
          </div>
        </div>

      </div>
    </section><!-- #about -->

    

    <!--==========================
      Call To Action Section
    ============================-->
    <section id="call-to-action">
      <div class="container">
        <div class="row">
          <c:choose>
             <c:when test="${empty authUser }">
                <div class="col-lg-9 text-center text-lg-left">
                  <h3 class="cta-title">협업공간</h3>
                  <p class="cta-text"> 로그인 후 팀을 생성해주세요</p>
                </div>
                <div class="col-lg-3 cta-btn-container text-center">
                  <a class="cta-btn align-middle" 
                     href="<%= request.getContextPath() %>/users/login">로그인하러 가기</a>
                </div>
             </c:when>
             <c:otherwise>
                <c:choose>
                   <c:when test="${authUser.teamNo == null }">
                      <div class="col-lg-9 text-center text-lg-left">
                        <h3 class="cta-title">협업공간</h3>
                        <p class="cta-text"> 팀을 생성하고 협업공간을 이용해보세요</p>
                      </div>
                      <div class="col-lg-3 cta-btn-container text-center">
                        <a class="cta-btn align-middle" 
                           href="<%= request.getContextPath() %>/recruit/list">팀만들러 가기</a>
                      </div>
                   </c:when>
                   <c:when test="${authUser.userStatus eq 'Mentor' }">
                      <div class="col-lg-9 text-center text-lg-left">
                        <h3 class="cta-title">협업공간</h3>
                        <p class="cta-text"> 팀리스트로 이동하여 멘티를 관리하세요</p>
                      </div>
                      <div class="col-lg-3 cta-btn-container text-center">
	                      <a class="cta-btn align-middle" 
	                        href="<%= request.getContextPath() %>/team/teamlist?userId=${authUser.userId}">팀리스트로 이동</a>
                      </div>
                   </c:when>
                   <c:otherwise>
                      <div class="col-lg-9 text-center text-lg-left">
                        <h3 class="cta-title">협업공간</h3>
                        <p class="cta-text"> 팀페이지로 이동하여 협업에 참여하세요</p>
                      </div>
                      <div class="col-lg-3 cta-btn-container text-center">
                        <a class="cta-btn align-middle" 
                        href="<%= request.getContextPath() %>/team/teammain">팀페이지로 이동</a>
                      </div>
                   </c:otherwise>
                </c:choose>
             </c:otherwise>
          </c:choose>
          </div>
      </div>
    </section><!-- #call-to-action -->


    <!--==========================
      More Features Section
    ============================-->
    <section id="more-features" class="section-bg">
      <div class="container">

        <div class="section-header">
          <h3 class="section-title">바로가기</h3>
          <span class="section-divider"></span>
          <p class="section-description">사이트의 다양한 기능을 이용해보세요</p>
        </div>

        <div class="row">

          <div class="col-lg-6">
            <div class="box" data-aos="fade-right">
              <div class="icon"><i class="ion-ios-stopwatch-outline"></i></div>
              <h4 class="title"><a href="">공모전 현황</a></h4>
              <p class="description">현재 진행중인 공모전들을 확인하고 참여해보세요</p>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="box" data-aos="fade-left">
              <div class="icon"><i class="ion-ios-bookmarks-outline"></i></div>
              <h4 class="title"><a href="">수집 컨텐츠</a></h4>
              <p class="description">내 키워드와 관련된 다양한 컨텐츠들을 확인해보세요</p>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="box" data-aos="fade-right">
              <div class="icon"><i class="ion-ios-heart-outline"></i></div>
              <h4 class="title"><a href="">커뮤니티</a></h4>
              <p class="description">다른 회원들과 자유롭게 대화를 나누어보세요</p>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="box" data-aos="fade-left">
              <div class="icon"><i class="ion-ios-analytics-outline"></i></div>
              <h4 class="title"><a href="">마이페이지</a></h4>
              <p class="description">내 정보를 확인하고 나에게 맞는 키워드를 추가해주세요</p>
            </div>
          </div>

        </div>
      </div>
    </section><!-- #more-features -->


    <!--==========================
      Contact Section
    ============================-->
    <section id="contact">
      <div class="container">
        <div class="row" data-aos="fade-up">

          <div class="col-lg-4 col-md-4">
            <div class="contact-about">
              <h3>BIMANDA</h3>
              <p>Cras fermentum odio eu feugiat. Justo eget magna fermentum iaculis eu non diam phasellus. Scelerisque felis imperdiet proin fermentum leo. Amet volutpat consequat mauris nunc congue.</p>
              <div class="social-links">
                <a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
                <a href="#" class="facebook"><i class="fa fa-facebook"></i></a>
                <a href="#" class="instagram"><i class="fa fa-instagram"></i></a>
                <a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a>
                <a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-4">
            <div class="info">
              <div>
                <i class="ion-ios-location-outline"></i>
                <p>A108 Adam Street<br>New York, NY 535022</p>
              </div>

              <div>
                <i class="ion-ios-email-outline"></i>
                <p>info@example.com</p>
              </div>

              <div>
                <i class="ion-ios-telephone-outline"></i>
                <p>+1 5589 55488 55s</p>
              </div>

            </div>
          </div>

          <div class="col-lg-5 col-md-8">
            <div class="form">
              <div id="sendmessage">Your message has been sent. Thank you!</div>
              <div id="errormessage"></div>
              <form action="" method="post" role="form" class="contactForm">
                <div class="form-row">
                  <div class="form-group col-lg-6">
                    <input type="text" name="name" class="form-control" id="name" placeholder="Your Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                    <div class="validation"></div>
                  </div>
                  <div class="form-group col-lg-6">
                    <input type="email" class="form-control" name="email" id="email" placeholder="Your Email" data-rule="email" data-msg="Please enter a valid email" />
                    <div class="validation"></div>
                  </div>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="subject" id="subject" placeholder="Subject" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
                  <div class="validation"></div>
                </div>
                <div class="form-group">
                  <textarea class="form-control" name="message" rows="5" data-rule="required" data-msg="Please write something for us" placeholder="Message"></textarea>
                  <div class="validation"></div>
                </div>
                <div class="text-center"><button type="submit" title="Send Message">Send Message</button></div>
              </form>
            </div>
          </div>

        </div>

      </div>
    </section><!-- #contact -->

  </main>

  <!--==========================
    Footer
  ============================-->
  <footer id="footer">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 text-lg-left text-center">
          <div class="copyright">
            &copy; Copyright <strong>Bimanda</strong>. All Rights Reserved
          </div>
          <div class="credits">
            <!--
              All the links in the footer should remain intact.
              You can delete the links only if you purchased the pro version.
              Licensing information: https://bootstrapmade.com/license/
              Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Avilon
            -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
          </div>
        </div>
        <div class="col-lg-6">
          <nav class="footer-links text-lg-right text-center pt-2 pt-lg-0">
            <a href="#intro" class="scrollto">Home</a>
            <a href="#about" class="scrollto">About</a>
            <a href="#">Privacy Policy</a>
            <a href="#">Terms of Use</a>
          </nav>
        </div>
      </div>
    </div>
  </footer><!-- #footer -->

  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/jquery/jquery-migrate.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="lib/easing/easing.min.js"></script>
  <script src="lib/aos/aos.js"></script>
  <script src="lib/superfish/hoverIntent.js"></script>
  <script src="lib/superfish/superfish.min.js"></script>
  <script src="lib/magnific-popup/magnific-popup.min.js"></script>

  <!-- Contact Form JavaScript File -->
  <script src="contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>

</body>
</html>