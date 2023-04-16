<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us</title>
</head>
<body>
<div id="header">
	<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
 </div>
 <br/><br/><br/><br/><br/>
<!--==========================
      About Us Section
    ============================-->
    <section id="about" class="">
      <div class="container-fluid">
        <div class="section-header">
          <h3 class="section-title">About Us</h3>
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
      Contact Section
    ============================-->
    <section id="contact">
      <div class="container">
        <div class="row" data-aos="fade-up">

          <div class="col-lg-4 col-md-4">
            <div class="contact-about">
              <h3>Avilon</h3>
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
</body>
</html>