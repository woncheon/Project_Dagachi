<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/jsp/include/openhead.jspf"%>
   <div class="wrapper col-12" style="background: #f9f9f9;">
   <section class="content-header">
        <div class="container-fluid">
           <div class="row md-2">
              <div class="col-sm-6">
                 <h1>주소록 등록</h1>              
              </div>
              <div class="col-sm-6">
                 <ol class="breadcrumb float-sm-right">
                 <li class="breadcrumb-item">
                    <a href="#">
                       <i class="fa fa-dashboard">주소록</i>
                    </a>
                 </li>
                 <li class="breadcrumb-item active">
                    주소록 등록
                 </li>              
            </ol>
              </div>
           </div>
        </div>
        <hr/>
     </section>
     <form role="form" action="regist">
     <section class="col-10 mx-auto content-body"  >
        <div class="btn-group btn-group-toggle row col-sm-12" data-toggle="buttons">
         <label class="btn btn-secondary active col-sm-3">
            <input type="radio" name="phone_Book_Type" value="1"> 공유
         </label>
         <label class="btn btn-secondary col-sm-3">
            <input type="radio" name="phone_Book_Type" value="2" > 개인
         </label>
      </div>
        <div class="mt-4">

           <div class="row">
              <label for="" class="col-sm-3">이름*</label>
              <input name="phone_Book_Name" type="text" class="col-sm-6 form-control"/>
            </div>

           <div class="row mt-2">
           <label for="" class="col-sm-3">전화번호</label>
           <select class="form-control col-sm-2 mr-2 phone" name="phone1" onchange="inputPhone();">
              <option value="010">010</option>
              <option value="011">011</option>
              <option value="016">016</option>
              <option value="017">017</option>
           </select>
           <input name="phone2" type="text" class="col-sm-2 mr-2 form-control phone" onchange="inputPhone();"/>
           <input name="phone3"type="text" class="col-sm-2 form-control phone" onchange="inputPhone();"/>
           </div>
           
           <div class="row mt-2">
              <label for="" class="col-sm-3">이메일</label>
              <input name="email1" type="text" class="col-sm-2 form-control" onchange="inputEmail();"/>&nbsp;@&nbsp;
              <input name="email2" type="text" class="col-sm-3 form-control" onchange="inputEmail();"/>
            </div>
           
           <div class="row mt-2">
              <label for="" class="col-sm-3">태그</label>
              <select class="form-control col-sm-6 mr-2" name="selectTag" id="" onchange="hideNewTag();">
                 <option disabled="disabled" selected="selected">==태그 선택==</option>
                 <option value="newTag">새 태그 입력하기</option>
              <c:forEach var="tag" items="${tags }">
                 <c:if test="${not empty tag || tag eq ' '}">
                    <c:if test="${not fn:contains(tag,',')}">
                       <option value="${tag }">${tag }</option>                          
                    </c:if>
                 </c:if>
              </c:forEach>
              
           </select>
              <button type="button" name="addTagBtn" onclick="addTag_do()" class="btn bg-gradient col-sm-2" 
              style="background: #5865F2; color:#ffffff;">태그 추가</button>
            </div>
           
           <div class="row mt-2 inputTag" style="display: none">
              <label for="" class="col-sm-3">새 태그</label>
              <input type="text" name="selectNewTag" class="col-sm-6 form-control"/>
              <button type="button" name="addTag" onclick="addNewTag_do()" class="btn bg-gradient col-sm-2" style="background: #5865F2; color:#ffffff;">태그 추가</button>
            </div>
            
            <div class="tagInput">
               
            </div>

           <div class="row mt-2">
              <label for="" class="col-sm-3">회사명</label>
              <input type="text" name="phone_Book_Company"class="col-sm-6 form-control"/>
            </div>

           <div class="row mt-2">
              <label for="" class="col-sm-3">부서</label>
              <input type="text" name="phone_Book_Dep" class="col-sm-6 form-control"/>
            </div>

           <div class="row mt-2">
              <label for="" class="col-sm-3">직급</label>
              <input type="text" name="phone_Book_Rank" class="col-sm-6 form-control"/>
            </div>
           
           <div class="row mt-2">
              <label for="" class="col-sm-3">웹사이트</label>
              <input type="text" name="phone_Book_Web" class="col-sm-6 form-control"/>
            </div>
           
           <div class="row mt-2">
              <label for="" class="col-sm-3">팩스</label>
              <input type="text" name="phone_Book_Fax" class="col-sm-6 form-control"/>
            </div>
           
           <div class="row mt-2">
              <label for="" class="col-sm-3">주소</label>
              <input type="text" name="phone_Book_Address" class="col-sm-6 form-control"/>
            </div>
           <br />
           <span style="color:gray">*표시 되어있는 항목은 필수입력 사항입니다.</span>
        </div>
     </section>
     
     <div class="text-center mt-4">
        <button type="button" class="btn bg-gradient col-sm-3" style="background: #5865F2; color:#ffffff;" onclick="checkName()">등록</button>
     </div>
     <input type="hidden" name="phone_Book_Phone" value="" />
     <input type="hidden" name="phone_Book_Email" value="" />
     <input type="hidden" name="phone_Book_Tag" value="" />
     <input type="hidden" name="phone_Book_Register" value="${param.phone_book_register }" />
     
     </form>
    
     
     
   </div>
<%@include file="/WEB-INF/jsp/include/openfoot.jspf"%>
<script>
   function inputPhone(){
   /* 
   alert($('select[name=phone1]').val());
   alert($('input[name=phone2]').val());
   alert($('input[name=phone3]').val());
    */
   
    const phoneArr=$('select[name=phone1]').val()+'-'+$('input[name=phone2]').val()+'-'+$('input[name=phone3]').val();
   $('input[name=phone_Book_Phone]').val(phoneArr);
   //alert($('input[name=phone_Book_Phone]').val());
      
   }
   
   function inputEmail(){
      const emailArr=$('input[name=email1]').val()+'@'+$('input[name=email2]').val();
      $('input[name=phone_Book_Email]').val(emailArr);
   }
   
   function hideNewTag(){
      if($('select[name=selectTag]').val()=='newTag'){
         $('.inputTag').slideDown();
         $('button[name=addTagBtn]').css("display","none");
         
      }else{
         $('.inputTag').slideUp();
         $('button[name=addTagBtn]').css("display","");
      }
   }
   
   var dataNum=0;
   
   
   function addTag_do(){
      //alert($('select[name=selectTag]').val());
      if($('input[name="addedTag"]').length>=3){
         alert("태그 추가는 3개까지 가능합니다.");
         return;
      }
      var div=$('<div>').addClass('inputRow row col-sm-12 mt-1 mb-1').attr('data-no',dataNum);
      var input=$('<input class="col-sm-6">').attr({"type":"text","name":"addedTag","disabled":"disabled","value":$('select[name=selectTag]').val()}).css("display","inline");
      var blankDiv=$('<div>').addClass('col-sm-3');
      div.append(blankDiv);
      div.append(input).append("<button onclick='remove_go("+dataNum+");'style='border:0;outline:0;' class='badge bg-red' type='button'>x</button>");
      $('.tagInput').append(div);
      
      //alert($('input[name="addedTag"]').length);
      
      const tagArr=[];
      var tags=$('input[name="addedTag"]');
      for(var tag of tags){
         tagArr.push(tag.value);
      }
      
      //alert(tagArr);
      
      
      $('input[name=phone_Book_Tag]').val(tagArr);
      dataNum++;
   }
   function addNewTag_do(){
      //alert($('select[name=selectTag]').val());
      if($('input[name="addedTag"]').length>=3){
         alert("태그 추가는 3개까지 가능합니다.");
         return;
      }
      var div=$('<div>').addClass('inputRow row col-sm-12 mb-1 mt-1').attr('data-no',dataNum);
      var input=$('<input class="col-sm-6">').attr({"type":"text","name":"addedTag","disabled":"disabled","value":$('input[name=selectNewTag]').val()}).css("display","inline");
      var blankDiv=$('<div>').addClass('col-sm-3');
      div.append(blankDiv);
      div.append(input).append("<button onclick='remove_go("+dataNum+");'style='border:0;outline:0;' class='badge bg-red' type='button'>x</button>");
      $('.tagInput').append(div);
      
      //alert($('input[name="addedTag"]').length);
      const tagArr=[];
      var tags=$('input[name="addedTag"]');
      for(var tag of tags){
         tagArr.push(tag.value);
      }
      
      //alert(tagArr);
      
      
      $('input[name=phone_Book_Tag]').val(tagArr);
      dataNum++;
   }
   
   function remove_go(dataNum){
         $('div[data-no="'+dataNum+'"]').remove();
         
         const tagArr=[];
         
         var tags=$('input[name="addedTag"]');
         for(var tag of tags){
            tagArr.push(tag.value);
         }
         $('input[name=phone_Book_Tag]').val(tagArr);
      }
   
   
   function checkName(){
       if($('input[name=phone_Book_Name]').val()==null || $('input[name=phone_Book_Name]').val()== ""){
          
          alert("이름을 입력해 주세요");
          $('input[name=phone_Book_Name]').focus();
          return false;
       }else{
          if(confirm("등록 하시겠습니까?")){
             
             $('form[role=form]').submit();
          }else{
             
          }
       }
   }
   
   function insertType(){
      //alert($('label.active').children('input[name=options]').val())
      $('input[name=phone_Book_Type]').val($('label.active').children('input[name=options]').val());
      
   }
   
</script>