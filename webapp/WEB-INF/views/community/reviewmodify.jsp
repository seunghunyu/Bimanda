<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공모전후기 수정</title>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
<script type="text/javascript"
   src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
</script>
<script type="text/javascript">
function reviewCheckForm(btn){
   // 아이디 필드 체크
   if (btn.form.boardTitle.value.trim().length==0){
      alert("제목을 입력해주세요")
      return;
   }else if(btn.form.boardContent.value.trim().length==0){
      alert("내용을 입력해주세요")
      return;
   }else if(btn.form.cateNo.value.trim().length==0){
      alert("분류를 선택해주세요")
      return;
   }else {
      document.reviewwriteaction.submit()
   }
}
</script>
<style>
body{
   background:#eff5f5;
}
</style>
</head>
<body>
<br/>
      <div class="section-header">
          <h3 class="section-title">수정하기</h3>
          <span class="section-divider"></span>
        <br/>
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
<br/>
<div class="writeform">
<form modelAttribute="reviewBoardVo" method="post" action="${pageContext.servletContext.contextPath }/community/modify?starting=${starting }" name="reviewwriteaction">
   <input type="hidden" name="userNo" value="${authUser.userNo}"><br>   
   <input type="hidden" name="userId" value="${authUser.userId}"><br>
   
    <label for="boardTitle" class="control-label">제목</label>
    <input type="text" name="boardTitle" class= "form-control" style="width:119%;" value="${rVo.boardTitle}"><br>
    
    <input type="hidden" name="boardCategory" value="${rVo.boardCategory}"><br>
    <input type="hidden" name="boardNo" value="${rVo.boardNo}"><br>
   
   <label for="boardContent" class="control-label">내용</label>
   <textarea  name="boardContent" id="summernote" cols=60 rows=5>${rVo.boardContent}</textarea><br>
   
   <input type="hidden" name="cateNo" value="${rVo.cateNo}">
   
   <div class="makeTeamdiv" style="margin-left:17%;">
      <input class="makeTeam" type="button" value="수정하기" onclick="reviewCheckForm(this)">
   </div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
   $('#summernote').summernote({
      placeholder: '내용을 입력해주세요',
      tabsize: 2,
      width: 800,
      height: 300,
      focus: true
   });
});
</script>
</body>
</html>