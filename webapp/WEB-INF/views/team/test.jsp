<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Sortable</title>
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
<style type="text/css">
.sortable-5, #sortable-6,#sortable-7 { 
      list-style-type: none; margin: 0; padding: 0;
      width: 20%;float:left }
#sortable-5 li, #sortable-6 li,#sortable-7 li { 
   margin: 0; padding: 0.4em; 
padding-left: 1.5em; font-size: 17px; height: 16px; }
.default {
   background: #cedc98;
   border: 1px solid #DDDDDD;
   color: #333333;
}
</style>

<script type="text/javascript">
function checkform4(btn) {
	// 할일 필드 체크
	alert("삭제한다");
	//document.modify.taskTitle.value = null;
	document.modify.del.value = true;
	document.modify.taskTitle.value = null;
	document.modify.taskNo.value = taskNo;
	document.modify.submit();
	return true;
}
</script>
<script type="text/javascript">
var taskNo;
function checkform3(btn) {
	// 할일 필드 체크
	if (btn.form.taskTitle.value.trim().length==0){
		alert("입력해주세요");
		return false; 
	}else{
		document.modify.taskNo.value = taskNo;
		document.modify.submit();
		return true;
	}
}
</script>
<script type="text/javascript">
function checkform2(obj) {
	// 할일 필드 체크
	if (obj.taskTitle.value.trim().length==0){
		alert("입력해주세요");
		return false;
	} else {
		return true;
	}
}
</script>
<script type="text/javascript">
function checkform(obj) {
	if (obj.projName.value.trim().length==0){
		alert("프로젝트명을 입력해주세요")
	} else if (obj.projDescription.value.trim().length==0) {
		alert("프로젝트 설명을 입력해주세요")
	} else {
		return true;
	}
	return false;
}
</script>
<script type="text/javascript">
function closeLayer( obj ) {
	$(obj).parent().parent().hide();
}
$(function(){
	/* 클릭 클릭시 클릭을 클릭한 위치 근처에 레이어가 나타난다. */
	$('.default').click(function(e)
	{
		var sWidth = window.innerWidth;
		var sHeight = window.innerHeight;
		//alert(e.target.value);
		var oWidth = $('.popupLayer').width();
		var oHeight = $('.popupLayer').height();
		
		var projNo = e.target.value;
		taskNo = e.target.id;
		
		// 레이어가 나타날 위치를 셋팅한다.
		var divLeft = e.clientX + 10;
		var divTop = e.clientY + 5;

		// 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
		if( divLeft + oWidth > sWidth ) divLeft -= oWidth;
		if( divTop + oHeight > sHeight ) divTop -= oHeight;

		// 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
		if( divLeft < 0 ) divLeft = 0;
		if( divTop < 0 ) divTop = 0;

		$('.popupLayer').css({
			"top": divTop,
			"left": divLeft,
			"position": "absolute",
		}).show();
	});
});
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
<!-- 
handle: 'li',
      start: function(e, ui){
    	  $(this).attr('data-previndex', ui.item.index());
      },
      update: function(e, ui){
    	  var newOrd = Number(ui.item.index()) + 1;
    	  var oldOrd = Number($(this).attr('data-previndex')) + 1;
    	  var grpno = ${param.grpno};
    	  
    	  $.ajax({
    		  type:"POST",
    		  url: "/changeItemsOrder",
    		  data: {grpno:grpno, neword:newOrd, oldord:oldOrd,taskState: pro, taskNo: c},
    		  dataType: "json",
    		  success: function(res){
    				alert("성s")
    			},
    			error: function(xhr, status, error){
    				alert(status);
    				alert(error);
    			}
    		  
    		  
    	  })
    	  
      }
      -->
<script>
      $('.a').sortable(
    		  item: '>.sortable-5>.default'
    		  )
    
</script>
</head>
<body>
	<div class="a" ondrop="drop(event)" ondragover="allowDrop(event)">
		<ul class="sortable-5">
			<h3>List 1</h3>
		      <c:forEach items="${list }" var="list">
				<c:set var="taskState" value="${list.taskState }"/>
					<c:if test="${taskState eq 0}"> 
						<li class="default" draggable="true" ondragstart="drag(event)" id="${list.taskNo } " value="${list.projNo }" onmouseover="showOption(event)">${list.taskTitle }</li>
					</c:if>
			</c:forEach>
	   </ul>	
   </div>
   <div id="b" ondrop="drop(event)" ondragover="allowDrop(event)">
	   <ul id="sortable-6">
		   <h3>DOING</h3>
		      <c:forEach items="${list }" var="list">
				<c:set var="taskState" value="${list.taskState }"/>
					<c:if test="${taskState eq 1}"> 
						<li class="default" draggable="true" ondragstart="drag(event)" id="${list.taskNo }" value="${list.projNo }">${list.taskTitle }</li>
					</c:if>
			</c:forEach>
	   </ul>
   </div>
	   
   <div id="c" ondrop="drop(event)" ondragover="allowDrop(event)">
	   <ul id="sortable-7">
		   <h3>DONE</h3>
		     <c:forEach items="${list }" var="list">
				<c:set var="taskState" value="${list.taskState }"/>
					<c:if test="${taskState eq 2}"> 
						<li class="default" draggable="true" ondragstart="drag(event)" id="${list.taskNo }" value="${list.projNo }">${list.taskTitle }</li>
					</c:if>
			</c:forEach>
	   </ul>
   </div>
	   
	   <form method="POST" name="add" id="add" 
				action="<%=request.getContextPath() %>/team/add"	
				onsubmit="return checkform2(this)">
				<input type="text" class="form-control" name="taskTitle"
					placeholder="할 일을 입력해주세요">
				<input type="hidden" value=${pv.projNo } name="projNo">
				<p class="makeTeamdiv">
					<input class="makeTeam" type="submit" value="할 일 추가">
				</p>
			</form>
			
		<div class="popupLayer">
			<div>
			<span onClick="closeLayer(this)" style="cursor:pointer;font-size:1em" title="닫기">X</span>
			</div>
			<form method="POST" name="modify" id="modify" action="<%=request.getContextPath() %>/team/modify">
    			<input type="text"  name="taskTitle">
    			<input type="hidden" name="taskNo" value="taskNo">
    			<input type="hidden" name="del" value="false">
    			<input type="submit" value="수정" onclick="return checkform3(this)">
    			<input type="button" value="삭제" onclick="checkform4(this)">
    		</form>
		</div>	
			
</body>
</html>