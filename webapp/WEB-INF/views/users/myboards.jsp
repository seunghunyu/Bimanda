<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 게시글</title>
<style type="text/css">
/*TAB CSS*/
ul.tabs {
	margin: 0;
	padding: 0;
	float: left;
	list-style: none;
	height: 40px; /*--Set height of tabs--*/
	
	/*border-left: 1px solid #999;*/
	width: 100%;
	
}
ul.tabs li {
	float: left;
	margin: 0;
	padding: 0;
	height: 40px; /*--Subtract 1px from the height of the unordered list--*/
	line-height: 40px; /*--Vertically aligns the text within the tab--*/
	border: 1px solid #999;
	border-left: none;
	border-top-left-radius: 20px;
	border-top-right-radius: 20px;
	border: 3px solid #1de099;
	margin-bottom: -10px; /*--Pull the list item down 1px--*/
	overflow: hidden;
	position: relative;
	background-color: #fff;
}
ul.tabs li a {
	text-decoration: none;
	color: #000;
 	display: block;
	font-size: 1.2em;
	padding: 0 20px;
	/*--Gives the bevel look with a 1px white border inside the list item--*/
	border: 0px solid #fff; 
	outline: none;
}

ul.tabs li a:selected{
	color: #fff;
	background-color: #ccc;
}

/*Tab Conent CSS*/
.tab_container {
	
	border-top: none;
	overflow: hidden;
	clear: both;
	float: left; 
	width: 100%;
	background: #fff;
}
.tab_content {
	padding: 20px;
	font-size: 1.2em;
	width:100%;
	border: 3px solid #1de099;
	
}

ul.tabs li a:hover, ul.tabs li a:active {
	background: #1de099;
	color: white;
}

html ul.tabs li.active, html ul.tabs li.active a:hover, html ul.tabs li.active a  {
	/*--Makes sure that the active tab does not listen to the hover properties--*/
	background: #1de099;
	color: #fff;
	/*--Makes the active tab look like it's connected with its content--*/
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
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
<script type="text/javascript">
$(document).ready(function() {
	//When page loads...
	$(".tab_content").hide(); //Hide all content
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab
	$(".tab_content:first").show(); //Show first tab content

	//On Click Event
	$("ul.tabs li").click(function() {
		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});

});
</script>
</head>
<body>
<jsp:include 
	    	page="/WEB-INF/views/includes/header.jsp" />
	    	<div class="container-fluid">
		        <div class="section-header">
		          <h3 class="section-title">마이 페이지</h3>
		          <span class="section-divider"></span>
		        </div>
     	    </div>
		<div class="contest-cate-list" style="line-height:250%; font-size: 15px; padding-left:15px;">
	         <h4>내 정보</h4>
	            <ul>
	               <li><a href="<%= request.getContextPath() %>/users/memberupdate">개인정보 수정</a></li>
	               <c:choose>
	                  <c:when test="${authUser.userStatus eq 'Mentor' }">
	                     <li><a href="<%= request.getContextPath() %>/users/menteechoose/${authUser.userNo}">멘티 관리</a></li>
	                  </c:when>
	                  <c:otherwise>
	                     <c:choose>
	                        <c:when test="${authUser.userId eq 'admin' }">
	                           <li><a href="<%= request.getContextPath() %>/users/mentorconfirm">멘토 신청 관리</a></li>
	                        </c:when>
	                        <c:otherwise>
	                           <li><a href="<%= request.getContextPath() %>/users/mentor">멘토 신청</a></li>
	                        </c:otherwise>
	                     </c:choose>
	                  </c:otherwise>
	               </c:choose>
	               <li><a href="<%= request.getContextPath() %>/users/myboards?userNo=${authUser.userNo}">내가 쓴 게시글</a></li>
	             <li><a href="<%= request.getContextPath() %>/users/mycomments?userNo=${authUser.userNo}">내가 쓴 댓글</a></li>
	               <li><a href="<%= request.getContextPath() %>/users/withdraw">회원 탈퇴</a></li>
	            </ul>
	            <c:if test="${authUser.teamNo ne null }">
	               <h4>팀 정보</h4>
	                  <ul>
	                     <c:choose>
	                              <c:when test="${authUser.userStatus eq 'Mentor' }">
	                                 <li><a href="<%= request.getContextPath() %>/team/teamlist?userId=${authUser.userId}">팀리스트</a></li>
	                              </c:when>
	                              <c:otherwise>
	                                 <li><a href="<%= request.getContextPath() %>/team/teammain">팀 페이지</a></li>
	                              </c:otherwise>
	                           </c:choose>
	                      <li><a href="<%= request.getContextPath() %>/users/leaveTeam">팀 탈퇴</a></li>
	                  </ul>
	           </c:if>
		</div>
		<div class="mypage">
		<h3>내가 쓴 게시글</h3><br/>
<div id="wrapper">    
    <!--탭 메뉴 영역 -->
    <ul class="tabs">
        <li><a href="#tab1">커뮤니티</a></li>
        <li><a href="#tab2">공모전후기</a></li>
        <li><a href="#tab3">팀원모집글</a></li>
        <c:if test="${authUser.userStatus != 'Mentor'}">
        	<li><a href="#tab4">멘토신청글</a></li>
        </c:if>
    </ul>

    <!--탭 콘텐츠 영역 -->
    <div class="tab_container">
        <div id="tab1" class="tab_content">
            <!--Content-->
            <table class="table4">
				<thead>
					<tr>
						<th>분류</th>
						<th>제목</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${blist}" var="blist">
					<tr>
						<td>${blist.boardCategory}</td>	
						<td><a href="<%= request.getContextPath() %>/community/readcontent?boardNo=${blist.boardNo }&starting=mypage">${blist.boardTitle}</a></td>
						<td><a href="<%= request.getContextPath() %>/community/modify?userNo=${authUser.userNo }&boardNo=${blist.boardNo }&starting=mypage">수정</a></td>
						<td><a href="<%= request.getContextPath() %>/community/delete?userNo=${authUser.userNo }&boardNo=${blist.boardNo }&starting=mypage"
							onclick="return delete_confirm()">삭제</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
        </div>

        <div id="tab2" class="tab_content">
           <!--Content-->
           <table class="table4">
				<thead>
					<tr>
						<th>분류</th>
						<th>제목</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${rvlist}" var="rvlist">
					<tr>
						<td>${rvlist.boardCategory}</td>
						<td><a href="<%= request.getContextPath() %>/community/readreview?boardNo=${rvlist.boardNo}">${rvlist.boardTitle}</a></td>
						<td><a href="<%= request.getContextPath() %>/community/modify?userNo=${authUser.userNo }&boardNo=${rvlist.boardNo}&starting=mypage">수정</a></td>
						<td><a href="<%= request.getContextPath() %>/community/delete?userNo=${authUser.userNo }&boardNo=${rvlist.boardNo}&starting=mypage"
							onclick="return delete_confirm()">삭제</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
        </div>
        <div id="tab3" class="tab_content">
           <!--Content-->
           <table class="table4">
				<thead>
					<tr>
						<th>제목</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${rlist}" var="rlist">
					<tr>
						<td><a href="<%= request.getContextPath() %>/recruit/detail?no=${rlist.no}">${rlist.title}</a></td>
						<td><a href="<%= request.getContextPath() %>/recruit/modify?userNo=${authUser.userNo }&no=${rlist.no}&starting=mypage">수정</a></td>
						<td><a href="<%= request.getContextPath() %>/recruit/delete?userNo=${authUser.userNo }&no=${rlist.no}&starting=mypage"
							onclick="return delete_confirm()">삭제</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
        </div>
        <div id="tab4" class="tab_content">
           <!--Content-->
           <table class="table4">
				<thead>
					<tr>
						<th>학력</th>
						<th>경력</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${mlist}" var="mlist">
					<tr>
						<td>${mlist.education}</td>
						<td>${mlist.career}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
        </div>
    </div>
</div>
</div>
</body>
</html>