<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head profile="http://www.w3.org/2005/10/profile">
<link rel="icon" type="image/png" href="http://example.com/myicon.png">
<meta charset="UTF-8">
<title>팀 페이지 메인</title>
<script type="text/javascript"
   src="${pageContext.servletContext.contextPath}/javascript/jquery/jquery-1.9.0.js">
</script>
<!-- 칸반보드 ui css, javascript -->
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script> 
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
<script type="text/javascript">
function delete_confirm(){
//   삭제할 것인지 확인
//   confirm
   var result = confirm("정말로 삭제하시겠습니까?")
   if (result) {
   //   확인
      return true;
   } else {
      return false;
   }
}
</script>
<link href="${pageContext.servletContext.contextPath }/css/teammain.css" rel="stylesheet">
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

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
   $('.bubble2').click(function(e)
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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link href="${pageContext.servletContext.contextPath }/css/progress.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/includes/header.jsp" />

<div class="file">
   <div class="teammoklok">
      <h3 style="text-align: center;">팀원 목록</h3>
         <c:set var="mentorexist" value='0'/>
            <c:forEach items="${teamMemlist }" var="teamMemlist">
               <ul>
                  <c:if test="${teamMemlist.userStatus eq 'Mentor' }">
                     <c:set var="mentorexist" value='1' />
                     <li style="font-weight: bold; color: black; text-align: left; margin-right: 30px;">${teamMemlist.userName } (Mentor)</li>
                  </c:if>
               </ul>
            </c:forEach>
            <c:forEach items="${teamMemlist }" var="teamMemlist">
               <ul>
                  <c:if test="${teamMemlist.userStatus ne 'Mentor' }">
                     <li style="color: black; text-align: left; margin-right: 30px;">${teamMemlist.userName }</li>
                  </c:if>
               </ul>
            </c:forEach>
         <c:if test="${mentorexist != 1 &&  !empty pvo}">
            <h6><a class="makeTeam" style="text-align: center; margin-left: 220px; width:150px;" href="${pageContext.servletContext.contextPath}/team/findmentor/${authUser.teamNo}">멘토찾기</a></h6>
         </c:if>
</div>

   <div class="section-header">
      <span class="section-divider"></span>
   </div>
   <div class="filelist">
      <div class="filescroll" id="scrollDiv" style="overflow:auto">
      <h3 style="text-align: center;">파일 목록</h3>
      <br>
      <table class="table2">
         <c:forEach items="${files}" var="file">
         <tr>
            <td>${file}</td>
             <td>
                <a href="${pageContext.servletContext.contextPath}/team/download?file=${file}&projNo=${pvo.projNo}">다운로드</a>
             </td>
             <td>
                <a href="${pageContext.servletContext.contextPath}/team/delete?file=${file}&projNo=${pvo.projNo}"
                   onclick="return delete_confirm()">삭제</a>
             </td>
          <tr>
          </c:forEach>
      </table>
      </div>
   </div>
      
   <form:form enctype="multipart/form-data" modelAttribute="uploadFile" action="${pageContext.servletContext.contextPath}/team/upload?projNo=${pvo.projNo}">
   
      <label for="file">업로드할 파일 선택 : </label>
      <input type="file" name="file" id="file">
      <form:errors path="file" cssStyle="color:red" />
      <br>
      <p class="makeTeamdiv">
         <input class="makeTeam" type="submit" value="전송">
      </p>
   </form:form>
</div>
<div class="kanbanboard">
   
   
<c:choose>
   <c:when test="${empty pvolist }">
      <!-- 등록 form -->
      <input type="button" id=proAdd class="makeTeam" style="margin-left:0px; padding-left:20px; width: 150px; "value="프로젝트 추가" onclick="proAdder()"/>
      <div class="mypage">
      <form:form modelAttribute="projectVo" id="register" name="register" method="POST"
         action="${pageContext.servletContext.contextPath }/team/reg"
         onsubmit="return checkform(this)">
         <label for="projName">프로젝트명</label>
          <form:input path="projName" class="form-control" placeholder="입력해주세요"/>
          <input type="hidden" name="teamNo" value=${authUser.teamNo }>
          <br/>
         <label for="projDescription">프로젝트 설명</label>
          <form:input path="projDescription" class="form-control" placeholder="입력해주세요"/>
          <br/>
          <p class="makeTeamdiv">
             <input class="makeTeam" type="submit" value="등록">
          </p>
       </form:form>
       </div>
   </c:when>
   <c:otherwise>
      <!-- 등록 form -->
      <div id="allProjectList">
         <c:if test="${!empty pvo }">
            <h2 class="projName" style="text-align: center; margin-bottom:-40px;">${pvo.projName }</h2> 
            
            <div id="test"></div>
         </c:if>
         <select class="form-control" style="width:150px; height:40px; border: 1px solid #e0e0e0; border-radius: 15px;"name="projNo" onChange="window.location.href=this.value">
            <option value='' selected>--- 프로젝트 ---</option>
               <c:forEach items="${pvolist }" var="pvolist">
                    <a><option  value="${pageContext.servletContext.contextPath }/team/teammain?projNo=${pvolist.projNo}">${pvolist.projName }</option></a>
               </c:forEach>   
         </select>
         <input type="button" id=proAdd class="makeTeam" style="margin-left:0px; padding-left:20px; width: 150px; "value="프로젝트 추가" onclick="proAdder()"/>
      </div>
      <div id="regProject" style="display:none;">
         <form:form modelAttribute="projectVo" id="register" name="register" method="POST"
            action="${pageContext.servletContext.contextPath }/team/reg"
            onsubmit="return checkform(this)">
            <label for="projName">프로젝트명</label>
             <form:input path="projName" class="form-control" />
             <input type="hidden" name="teamNo" value=${authUser.teamNo }>
             <br/>
            <label for="projDescription">프로젝트 설명</label>
             <form:input path="projDescription" class="form-control"/>
             <br/>
             <p class="makeTeamdiv">
                <input class="makeTeam" type="submit" value="등록">
             </p>
          </form:form>
      </div>
      <c:if test="${!empty list }">
         <div id="page" class="page"><!-- 프로그레스바  -->
            
           <div class="progress-bar" style="background-color: #eff5f5;">
            <canvas id="inactiveProgress" class="progress-inactive" height="0px" width="0px"></canvas>
             <canvas id="activeProgress" class="progress-active"  height="220px" width="275px"></canvas>
             <p>0%</p>
           </div>
           <div id="progressControllerContainer">
             <input type="hidden" id="progressMax" value="${fn:length(list)}" />
             <input type="hidden" class="progressNow" id="progressNow" />
             <input type="hidden" id="doneCount" value="${doneCount}" />
           </div>
         </div>
      </c:if>
      <div id="kanban" style="display:none;">
         <div id="kanbanContent">
            <div id="a" ondrop="drop(event)" ondragover="allowDrop(event)">
               <h4 style="text-align: center;" class="kanbanTitle">TODO</h4>
               <ul id="list1" class="connectedSortable">
               <c:forEach items="${list }" var="list">
                  <c:set var="taskState" value="${list.taskState }"/>
                     <c:if test="${taskState eq 0}">
                        <li class="bubble2" draggable="true"  ondragstart="drag(event)" id="${list.taskNo } " value="${list.projNo }">
                        <strong class="inbubble">${list.userId}</strong>  ${list.taskTitle } </li>
                        
                     </c:if>
               </c:forEach>
               </ul>
            </div>
         
            <div id="b" ondrop="drop(event)" ondragover="allowDrop(event)">   
               <h4 style="text-align: center;" class="kanbanTitle">DOING</h4>
               <ul id="list2" class="connectedSortable">
               <c:forEach items="${list }" var="list">
                  <c:set var="taskState" value="${list.taskState }"/>
                     <c:if test="${taskState eq 1}"> 
                           <li class="bubble2" draggable="true" ondragstart="drag(event)" id="${list.taskNo }" value="${list.projNo }"><strong class="inbubble">${list.userId}</strong>  ${list.taskTitle }</li>
                     </c:if>
               </c:forEach>
               </ul>
            </div>
         
            <div id="c" ondrop="drop(event)" ondragover="allowDrop(event)">   
               <h4 style="text-align: center;" class="kanbanTitle">DONE</h4>   
               <ul id="list3" class="connectedSortable">
               <c:forEach items="${list }" var="list">
                  <c:set var="taskState" value="${list.taskState }"/>
                     <c:if test="${taskState eq 2}"> 
                           <li class="bubble2" draggable="true" ondragstart="drag(event)" id="${list.taskNo }" value="${list.projNo }"><strong class="inbubble">${list.userId}</strong>  ${list.taskTitle }</li>
                     </c:if>
               </c:forEach>
               </ul>
            </div>
         </div> <!-- kanbanContent  -->
         
         <div class="margin"></div>
         <div class="margin"></div>
         <div class="margin"></div>
         
         <form method="POST" name="add" id="add" action="<%=request.getContextPath() %>/team/add">   
            <input type="text" class="form-control" name="taskTitle"
               placeholder="할 일을 입력해주세요">
            <input type="hidden" value=${pvo.projNo } name="projNo">
            <input type="hidden"  value="${authUser.userId}" name="userId">
            <p class="makeTeamdiv">
               <input class="makeTeam" type="button" value="할 일 추가" onclick="return checkform2(this)">
            </p>
         </form>
      </div>
     
      <div class="popupLayer">
         <div>
         <span onClick="closeLayer(this)" style="cursor:pointer;font-size:1em" title="닫기">X</span>
         </div>
         <form method="POST" name="modify" id="modify" action="<%=request.getContextPath() %>/team/modify">
             <input type="text"  name="taskTitle" style="width:250px;">
             <input type="hidden" name="taskNo" value="taskNo">
             <input type="hidden" value=${pvo.projNo } name="projNo">
             <input type="hidden" name="del" value="false">
             <input type="submit" class="makeTeam" value="수정" onclick="return checkform3(this)">
             <input type="button" class="makeTeam" value="삭제" onclick="return checkform4(this)">
          </form>
          <script>
    
          </script>
      </div>
   </c:otherwise>
</c:choose>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
<div class="chatting">
   <div class="chat" id="chat">
      <h3 style="text-align:center;">채팅</h3>
      <br>
         <div class="chatscroll" id="chatscroll">
             <br><br>
             
             <!-- Server responses get written here -->
         
                   
                      <c:forEach items="${chatlist }" var="chatlist">
                         <c:choose>
                            <c:when test="${chatlist.userId eq authUser.userId}">
                               <div class="bubble">(${chatlist.userId}):${chatlist.chatContent }</div>
                            </c:when>
                            <c:otherwise>
                               <div class="bubbleyou">(${chatlist.userId}):${chatlist.chatContent }</div>
                            </c:otherwise>
                         </c:choose>
                      </c:forEach>
                   
                
                <div id="messages"></div>
          </div>
    </div>
    <div>
        <input type="text" id="sender" value="${authUser.userId }" style="display: none;">
        <input type="text" class="form-control" id="messageinput" 
           placeholder="내용을 입력해주세요" onkeydown="javascript:keyevent(this);">
    </div>
    <br>
    <div>
        <!--  button type="button" onclick="openSocket();">Open</button-->
        <!--  button type="button" onclick="send();">Send</button-->
        <!--  button type="button" onclick="closeSocket();">Close</button-->
        <p class="makeTeamdiv">
         <input class="makeTeam" type="button" value="Send" onclick="send();">
      </p>
    </div>
   
    
    <!-- websocket javascript -->
    <script type="text/javascript"
      src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">
   </script>
   <script type="text/javascript">
   
   var ws; // 소켓 저장 변수 
   var messages=document.getElementById("messages"); //내가쓰는 채팅을 보여줄 태그 
   var taskNo;
   var divdiv = document.getElementById("chatscroll");
   divdiv.scrollTop = divdiv.scrollHeight;
   var id = "${authUser.userId}"
   
   var kanban; //칸반 아이디값받는변수  
   var chatorkan; // 이벤트일어난게 칸반인지 채팅인지 구분해주는 변수 
   var projNo; // 프로젝트 클릭했을때  컨트롤러부터 받는 숫자 
   var teamNo;
   $(document).ready(function openSocket(){  //teammain 페이지 오픈 시 소켓 생성 
      projNo=String("${pvo.projNo}")
      teamNo="${authUser.teamNo}"
     // alert(projNo);
      $('#messageinput').focus();
      if(projNo!=0){
         document.getElementById("kanban").style.display = "block";
      }else {
         document.getElementById("kanban").style.display = "none";
      }
      
      if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
         writeResponse("WebSocket is already opened.");
         return;
      }
      //웹소켓 객체 만드는 코드
      ws=new WebSocket("ws://localhost:8085/Bimanda/echo.do");
      function kanbansort(){$('#list1, #list2, #list3').sortable({ // sortable: 원하는 순서에 끼워넣고 정렬해주는 메소드
         connectWith: ".connectedSortable",   // class가 connectedSortable인 애들끼리 연결?시켜주는 속성?
         cursor: 'move',   //아이콘 형태 
         opacity: 0.5,   // 드래그 했을 때 투명도(드래그 해보면 살짝 투명해짐)
         dropOnEmpty: true, // 비어있어도 떨굴 수 있게 해줌
         update: function(e, ui){ //드래그시동작  
            chatorkan="kan";
            if (this == ui.item.parent()[0]) {   // 이렇게 안해주면 메소드가 2번 발동함
            var arr = new Array();
            jQuery.ajaxSettings.traditional = true; // 컨트롤러로 배열형태로 보낼수 있게해줌
            
            var proNow = document.getElementById('progressNow').value;   
            var doneCount = document.getElementById('doneCount').value;
            var progressNow = parseInt(proNow);
            $('.bubble2').each(function(index){   // list1,2,3에 있는 모든 항목들을 index를 매겨서 배열 arr에 저장
                var fileName = $(this).attr('id');
                arr.push(fileName);
             });
            var c = ui.item.attr("id");   // 드래그 한 항목의 id 속성값 가져옴(taskNo)
            var now = ui.item.parent().attr("id");   // 항목을 drop했을 때 항목이 속해있는 list id를 가져옴
   
            if (now == "list1") {   // list1(TODO)에 놓으면 progress=0, list2(DOING)에 놓으면 progress=1, list3(DONE)에 놓으면 progress=2
               var pro = 0;
            } else if (now == "list2") {
               var pro = 1;
            } else if (now == "list3"){
               var pro = 2;
            }
            
            $.ajax({
               url:"${PageContext.servletContext.contextPath}/Bimanda/team/move",
               type: "post",
               data:{taskState: pro, taskNo: c, test: arr
               },
               success: function(res){
                  //alert("칸반성공");
                  var resInt = parseInt(res);
                  var progressUpdate = progressNow+resInt;
                  $('#progressNow').val(String(progressUpdate));
                  
                  var pm = document.getElementById('progressMax').value;
                  
                  var $pCaption = $('.progress-bar p');
                  var aProgress = document.getElementById('activeProgress');
                  
                  var pn = document.getElementById('progressNow').value;
                  var percentage = pn / pm;
                  drawProgress(aProgress, percentage, $pCaption);
                  send();
               },
               error: function(xhr, status, error){
                  alert(status);
                  alert(error);
               }
            });//ajax 끝나는 곳 
            }
         }//fucntion(e,ui) 끝나는곳 
      })//sorttable 끝나는곳 
      }
      ws.onopen=function(event){
         if(event.data===undefined) return;
                
         writeResponse(event.data);
      };
      ws.onmessage=function(event){ //        //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
         //alert(event); //object messageevent 출력 
     //    alert(event.data); ///kan 출력    
         if(event.data.includes("kan"+projNo)){   //칸반보드 움직였을때 작동 
       //    
       //    alert("이벤트데이터:"+event.data);
     //      alert("이벤트데이터칸프로젝넘버:kan"+projNo);
            if(event.data=="kan"+projNo+id){ //아이디 같으면 이벤트만 동작 
               writeResponse(event.data);      
            }
            else if(event.data!="kan"+projNo+id){ //아이디 다르면 리로드 
               writeResponse(event.data);
               location.reload();
            }
  
         }else if(event.data.includes("taskModify"+projNo)){
            if(event.data=="taskModify"+projNo+id){ //아이디 같으면 이벤트만 동작 
                writeResponse(event.data);      
             }
             else if(event.data!="taskModify"+projNo+id){ //아이디 다르면 리로드 
                writeResponse(event.data);
                location.reload();
             }                      
         }else if(event.data.includes("taskDelete"+projNo)){
            if(event.data=="taskDelete"+projNo+id){ //아이디 같으면 이벤트만 동작 
                 writeResponse(event.data);      
              }
              else if(event.data!="taskDelete"+projNo+id){ //아이디 다르면 리로드 
                 writeResponse(event.data);
                 location.reload();
              }               
          }else if(event.data.includes("taskAdd"+projNo)){
            if(event.data=="taskAdd"+projNo+id){ //아이디 같으면 이벤트만 동작 
                  writeResponse(event.data);      
               }
               else if(event.data!="taskAdd"+projNo+id){ //아이디 다르면 리로드 
                  writeResponse(event.data);
                  location.reload();
               }               
          }else if(event.data.substring(0,3)!="kan"&&event.data.substring(0,10)!="taskModify"&&event.data.substring(0,10)!="taskDelete"&&event.data.substring(0,7)!="taskAdd"){ //채팅  
            writeResponse(event.data);               //채팅
         
          }else{
            console.log("안돼");
            //alert("안돼"); 
         }
      };
      ws.onclose=function(event){
         writeResponse("Connection closed");
      }
      kanbansort();
      
      var pm = document.getElementById('progressMax').value;
      var doneCount = document.getElementById('doneCount').value;
      var $pCaption = $('.progress-bar p');
      var iProgress = document.getElementById('inactiveProgress');
      var aProgress = document.getElementById('activeProgress');
      var iProgressCTX = iProgress.getContext('2d');

      $('#progressNow').val(doneCount);
      
      drawInactive(iProgressCTX);
      
      var pn = document.getElementById('progressNow').value;
      
      var percentage = pn / pm;
      drawProgress(aProgress, percentage, $pCaption);
      
      $("#progressNow").on("propertychange change keyup paste input", function() {
          var currentVal = $(this).val();
          if(currentVal == pn) {
              return;
          }
          else {
             var percentage = currentVal / pm;
            drawProgress(aProgress, percentage, $pCaption);
          }
      });
      
      function drawInactive(iProgressCTX){
         iProgressCTX.lineCap = 'square';
         
         //progress bar
         iProgressCTX.beginPath();
         iProgressCTX.lineWidth = 0;
         iProgressCTX.fillStyle = '#e6e6e6';
         iProgressCTX.arc(137.5,137.5,121,0,2*Math.PI);
         iProgressCTX.fill();

         //progressbar caption
         iProgressCTX.beginPath();
         iProgressCTX.lineWidth = 0;
         iProgressCTX.fillStyle = '#000';
         iProgressCTX.arc(137.5,137.5,121,0,2*Math.PI);
         iProgressCTX.fill();

      }
      function drawProgress(bar, percentage, $pCaption){
         var barCTX = bar.getContext("2d");
         var quarterTurn = Math.PI / 2;
         var endingAngle = ((2*percentage) * Math.PI) - quarterTurn;
         var startingAngle = 0 - quarterTurn;

         bar.width = bar.width;
         barCTX.lineCap = 'square';

         barCTX.beginPath();
         barCTX.lineWidth = 10;
         barCTX.strokeStyle = '#00B4FF';
         barCTX.arc(137.5,137.5,61,startingAngle, endingAngle);
         barCTX.stroke();

         $pCaption.text( (parseInt(percentage * 100, 10)) + '%');
      }
      
   });
   
   function proAdder(){
   //   alert(kanban+"추가하기");
      document.getElementById("kanban").style.display = "none";
      document.getElementById("regProject").style.display = "block";
   }
   function checkform2(btn) {
      // 할일 필드 체크
      if (btn.form.taskTitle.value.trim().length==0){
         alert("입력해주세요");
         return false;
      } else {
          chatorkan="taskAdd";
          document.add.submit();
          send();
         return true;
      }
   }
   function checkform3(btn) { //할일 수정 
      // 할일 필드 체크
      if (btn.form.taskTitle.value.trim().length==0){
         alert("입력해주세요");
         return false; 
      }else{
        chatorkan="taskModify";
         document.modify.taskNo.value = taskNo;
         document.modify.submit();
         send();// 소켓을 통한수정위해 호출 
         return true;
      }
   }
   function checkform4(btn) { //할일삭제 
      // 할일 필드 체크
      var result = confirm("삭제하시겠습니까?")
      if (result){
        chatorkan="taskDelete";
        document.modify.del.value = true;
         document.modify.taskTitle.value = null;
         document.modify.taskNo.value = taskNo;
         document.modify.submit();
         send();  // 소켓을 통한삭제위해 호출 
         return true;
      }else{
         return false;
      }
      
   }
   
   function keyevent() {
      var keycode=event.keyCode;
      chatorkan="chat";
      if(keycode==13){
         send();
      }
   }
   function send(){//서버로 text를 보내는코드  
      var text;
  //    alert(chatorkan);
      if(chatorkan=="chat"){
      //   alert("send(chat)");
         text="("+document.getElementById("sender").value+")"+":"+document.getElementById("messageinput").value;
     //    alert(text);
         ws.send(text);
         $('#messageinput').val('');
         text="";   
      }
      else if(chatorkan=="kan")
      {
         //alert("send(kan)");
         text="kan"+projNo+id;
    //     alert(text)
         ws.send(text);
         text="";
      }
      else if(chatorkan=="taskModify")
      {
         text="taskModify"+projNo+id;
         ws.send(text);
           text="";
      }  
      else if(chatorkan=="taskDelete")
      {
         text="taskDelete"+projNo+id;
         ws.send(text);
           text="";
      }
      else if(chatorkan=="taskAdd")
      {
         text="taskAdd"+projNo+id;
         ws.send(text);
           text="";
      }  
      else {  // 둘 다 아닐때
         alert("안돼~");
      }      
      $('#messageinput').focus();
   }
   function closeSocket(){
      ws.close();
   }
   function writeResponse(text){
      if(text.substring(0,3)=="kan"){
         chatorkan="kan"; // chatorkan변수에 text의 값인 kan을 넣어준다.
      }else if(text.substring(0,10)=="taskModify"){
        chatorkan="taskModify";
      }else if(text.substring(0,10)=="taskDelete"){
        chatorkan="taskDelete";
      }else if(text.substring(0,7)=="taskAdd"){
        chatorkan="taskAdd";
      }  
      else if(text.substring(0,3)!="kan"&&text.substring(0,10)!="taskModify"&&text.substring(0,10)!="taskDelete"&&text.substring(0,7)!="taskAdd")
         {
         chatorkan="chat";
         if(id==document.getElementById("sender").value){
             messages.innerHTML+=text;
         }         
      }else{
         alert("안돼");
      }
      
      var divdiv = document.getElementById("chatscroll");
      divdiv.scrollTop = divdiv.scrollHeight;
   }
   
   </script>
   <script src="${pageContext.servletContext.contextPath }/js/material.min.js"></script>

</div>
</body>
</html>