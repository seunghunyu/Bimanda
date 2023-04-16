<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
   prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컨텐츠</title>

<script type="text/javascript"
   src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js">

</script>

<script type="text/javascript">
 // 현재 클릭한게 개인리스트인지 팀리스트인지 확인 
var listState="person";
var pS;


function personkeyword(){ // 개인버튼눌렀을때 실행되서 개인키워드리스트 보여주는것
//   alert("개인"+id);
   document.getElementById("teamNo").disabled = true;
   document.getElementById("userNo").disabled = false;
   
   
   listState="person";
   // alert(listState);
   $.ajax({   
      url: "${pageContext.servletContext.contextPath}/contents/stay",
      type: "get",
      dataType: "text",
      data:{
          stay: listState,
             },
       success: function(response){
           if (response){
              document.getElementById("teamkeywordlist").style.display = "none";
              document.getElementById("personkeywordlist").style.display = "block";
            // alert(as);
              //alert("성공"+listState);
            console.log("성공");
            //alert(response);
         }else{
            alert("실패")
         }
      },
      error: function(xhr, status, error){
         alert(status);
         alert(error);
      }
   });
   
}
function teamkeyword(){ // 팀버튼눌렀을때 실행되서 팀키워드리스트 보여주는것
   teamNo='${authUser.teamNo}'
   if(teamNo==0){
      alert("팀이없습니다. 팀을먼저 만드세요.")
      return false;
   }
   document.getElementById("teamNo").disabled = false;
   document.getElementById("userNo").disabled = true;
   
   listState="team";
   
   //alert(listState);
   $.ajax({   
      url: "${pageContext.servletContext.contextPath}/contents/stay",
      type: "get",
      dataType: "text",
      data:{
          stay: listState,
             },
       success: function(response){
           if (response){
              document.getElementById("teamkeywordlist").style.display = "block"; //보여지는거 
              document.getElementById("personkeywordlist").style.display = "none";//안보여지는거
            //alert("성공"+listState);
              //alert(response);
            console.log("성공");
            
         }else{
            alert("실패")
            }
      },
      error: function(xhr, status, error){
         alert(status);
         alert(error);
      }
   });
}


function checkkeyword2(obj){ //키워드 삽입할때 사용 
   var id = '${authUser.userId}';
   var teamNo = '${authUser.teamNo}';
   console.log(listState);
   //alert(listState);
   if (obj.keyName.value.trim().length==0){
         alert("키워드를 입력해주세요")
         return false;
   } 
   if(listState=="person"){
      //alert("아이디뭐야"+id);
      console.log(listState);
          //jQuery Ajax 수행
      $.ajax({   
         url: "http://127.0.0.1:5000/crawl",
         type: "get",
         dataType: "text",
         data:{
             userId: id,
                 keyName: obj.keyName.value},
          success: function(response){
              if (response.data == "true"){
                 //alert(response)
               //alert(id);
               console.log(id);
               document.keywordInsert.submit();
            }else{
               alert("실패")
               }
         },
         error: function(xhr, status, error){
            alert(status);
            alert(error);
         }
      });
   }
   if(listState=="team"){
         //alert(teamNo);
      $.ajax({   
         url: "http://127.0.0.1:5000/teamCrawl",
         type: "get",
         dataType: "json",
         data:{
              teamNo: teamNo,
              keyName: obj.keyName.value},
         success: function(response){
            if (response.data == "true"){
               //alert(id);
               console.log(id);
               document.keywordInsert.submit();
            }else{
               alert("실패")
            }
         },
         error: function(xhr, status, error){
            alert(status);
            alert(error);
         }
      });   
   }
}
function checkkeywordDelete(obj){ //키워드 삭제할때 사용 
   var id = '${authUser.userId}';
   var teamNo = '${authUser.teamNo}';
   console.log(listState);
   //alert(listState);
   if (obj.keyName.value.trim().length==0){
         alert("키워드를 입력해주세요")
         return false;
   } 
   if(listState=="person"){
      alert("키워드 삭제되었습니다.");
      document.keywordDelete.submit();
      //alert("아이디뭐야"+id);
          //jQuery Ajax 수행
      
   }
   if(listState=="team"){
      alert("키워드 삭제되었습니다.");
      document.keywordDelete.submit();
         //alert(teamNo);
   }
}   
</script>
 <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
   <jsp:include page="/WEB-INF/views/includes/header.jsp" />
<div class="container-fluid">
    <div class="section-header">
      <div class="section-header">
                <h3 class="section-title">컨텐츠</h3>
                <span class="section-divider"></span>
      </div>
   </div>
</div>
 
<c:choose>
    <c:when test="${ empty authUser}">
        로그인해주세요 
    </c:when>

    <c:otherwise>
      
      <div class="keyword-button" style="margin-left:12%;">    
          <input type="button" class="makeTeam" value="개인" onclick="personkeyword(this)"/>
          <input type="button" class="makeTeam" value="팀" onclick="teamkeyword(this)"/>           
       </div>  
       		<div style="overflow:hidden">
               <div id="personkeywordlist" class="contest-cate-list2" style="overflow:auto; width:200px; height:400px; display:none;">
                  <c:forEach items="${klist }" var="klist">
                     <a href="${pageContext.servletContext.contextPath }/contents/contentsmain?keyName=${klist.keyName}&userId=${authUser.userId}&stay=person"><option value="${klist.keyName }">${klist.keyName }</option></a>                     
                  </c:forEach>
               </div>                  
               <div id="teamkeywordlist" class="contest-cate-list2" style="overflow:auto;  width:200px; height:400px; display:none;">         
                  <c:forEach items="${teamklist }" var="teamklist">
                     <a href="${pageContext.servletContext.contextPath }/contents/contentsmain?keyName=${teamklist.keyName}&userId=${authUser.userId}&teamNo=${authUser.teamNo}&stay=team" ><option value="${teamklist.keyName }">${teamklist.keyName }</option></a>                  
                  </c:forEach>
               </div>   
    		</div>
    
    </c:otherwise>
</c:choose>
<div class="keyword-input">
   <form:form modelAttribute="KeywordVo"  name="kewordInsert" method="POST"
               action="${pageContext.servletContext.contextPath }/contents/insertKeyword"
               onsubmit="return checkkeyword2(this)" >
         <input type="hidden" id="userNo" name="userNo" value="${authUser.userNo}"> 
         <input type="hidden" id="teamNo" name="teamNo" value="${authUser.teamNo }" disabled="true">
         <input style="border-radius: 5px;" type="text" name="keyName" placeholder="키워드를 입력하세요" ><br>
         <input type="submit" value="등록" class="makeTeam" style="margin-left: 40px;"><br/>
   </form:form>
   <c:if test="${! empty selectKeyName }">
      <div class="">
        
         <form:form modelAttribute="KeywordVo"  name="kewordDelete" method="POST"
                action="${pageContext.servletContext.contextPath }/contents/deleteKeyword"
                onsubmit="return checkkeywordDelete(this)">
            <input type="hidden" id="userNo" name="userNo" value="${authUser.userNo}">
            <input type="hidden" id="teamNo" name="teamNo" value="${authUser.teamNo }" disabled="true">
            <input type="hidden" name="keyName" value="${selectKeyName }"> <h3>검색어 : ${selectKeyName}</h3>
            <input type="submit" value="키워드 삭제" class="makeTeam">
         </form:form>
      </div>
   </c:if>
</div>


<c:if test="${not empty cafelist }"><!-- cafelist 값 비어있으면 안보여주기  -->
 <div class="container" style="margin-top:auto; margin-left:500px;">
      <div class="row">

            <ul class="nav nav-tabs">
              <li class="active">
                <a   data-toggle="tab" href="#navercafe">네이버카페</a>
              </li>
              <li >
                <a   data-toggle="tab" href="#naverblog">네이버블로그</a>
              </li>
              <li >
                <a   data-toggle="tab" href="#daumcafe">다음카페</a>
              </li>
              <li >
                <a   data-toggle="tab" href="#daumblog">다음블로그</a>
              </li>
            </ul>
            <div class="tab-content clearfix">
              <div class="tab-pane active" id="navercafe">
                <div class="">
                  <table class="">
                        <thead>
                           <tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
                              <th>links</th><th>contents</th>
                           </tr>
                        </thead>
                     
                        <c:forEach items="${cafelist }" var="excelList">                  
                                     <c:forEach items="${excelList }" var="i" varStatus="status">
                                        <tr class="">
                                         <td class="">${i.links}</td>
                                         <td class="" >${i.contents }</td>
                                         </tr>    
                                     </c:forEach>                                     
                        </c:forEach>
                  </table>
               </div>
              </div>
              <div class="tab-pane" id="naverblog">
                <div class="">
                  <table class="">
                     <tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
                        <th>links</th><th>contents</th>
                     </tr>
                        <c:forEach items="${nbloglist }" var="excelList2">
                                     <c:forEach items="${excelList2 }" var="i2">
                                         <tr class="">
                                            <td class="">${i2.links}</td>
                                            <td class="">${i2.contents }</td>
                                          </tr>    
                                     </c:forEach>                   
                        </c:forEach>
                  </table>
               </div>
              </div>
              <div class="tab-pane" id="daumcafe">
                   <div class="">
                  <table class="">
                        <thead>
                        <tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
                           <th>thumbnail</th><th>title</th><th>url</th><th>contents</th>
                        </tr>
                        </thead>
                           <c:forEach items="${daumcafelist }" var="excelList3">
                                        <c:forEach items="${excelList3 }" var="i3">
                                            <tr class="">
                                                  <td class=""><img src="${i3.thumbnail }"  width="80" height="80" alt="thumbnail"/></td>
                                                  <td class="">${i3.title}</td>
                                                  <td class=""><a href="${i3.url }">${i3.url }</a></td>
                                                  <td class="">${i3.contents }</td>
                                            </tr>
                                        </c:forEach>                         
                           </c:forEach>
                  </table>
               </div>
              </div>
              <div class="tab-pane" id="daumblog">
                   <div class="">
                  <table class="">
                     <thead>   
                        <tr style="background:linear-gradient(45deg, #1de099, #1dc8cd);color:#FFF">
                           <th>thumbnail</th><th>title</th><th>url</th><th>contents</th>
                        </tr>
                     </thead>
                           <c:forEach items="${daumbloglist }" var="excelList4">
                                     <c:forEach items="${excelList4 }" var="i4">
                                            <tr class="">
                                               <td class=""><img src="${i4.thumbnail }"  width="80" height="80" alt="thumbnail"/></td>
                                               <td class="">${i4.title}</td>
                                               <td class=""><a href="${i4.url }" >${i4.url }</a></td>
                                               <td class="">${i4.contents }</td> 
                                          </tr>
                                     </c:forEach>                    
                           </c:forEach>
                  </table>
               </div>
              </div>
            </div>
        </div>
      </div>

</c:if>

   
   <script>
      $(function() {
         ps='${pleaseStay}';
         
         if(ps=="person"){
            document.getElementById("teamkeywordlist").style.display = "none";
              document.getElementById("personkeywordlist").style.display = "block";
         }else {
            document.getElementById("teamkeywordlist").style.display = "block";
              document.getElementById("personkeywordlist").style.display = "none";
         }
         
      });
   </script>
   
</body>
</html>