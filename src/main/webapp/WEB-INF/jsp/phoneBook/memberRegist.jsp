<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/jsp/include/openhead.jspf"%>
   <div class="wrapper col-12" style="background: #f9f9f9;">
   <section class="content-header">
        <div class="container-fluid">
           <div class="row">
              <div class="col-sm-6">
                 <h1>신규사원 등록</h1>              
              </div>
              <div class="col-sm-6">
                 <ol class="breadcrumb float-sm-right">
                 <li class="breadcrumb-item">
                    <a href="#">
                       <i class="fa fa-dashboard">주소록</i>
                    </a>
                 </li>
                 <li class="breadcrumb-item active">
                    신규사원 등록
                 </li>              
            </ol>
              </div>
           </div>
        </div>
        <hr/>
     </section>
     
        <div id="pictureView" class="border col-sm-3 mx-auto mb-2" style="height:200px;"></div>
      <div class="row col-sm-10 mx-auto">
         <label for="inputFile" class=" btn bg-primary btn-sm btn-flat col-sm-3">이미지 선택</label>
          <input id="inputFileName" class="col-sm-6 form-control mr-2" type="text" name="tempPicture" disabled/>
           <label class="btn bg-primary col-sm-2" onclick="upload_go();">업로드</label>
      </div>
     
     <form role="form" action="memberRegistSuccess">
     <section class="col-10 mx-auto content-body"  >
        
        <div class="">
           <input type="hidden" name="member_pic" value="" />
         <div class="row mt-2">
              <label for="" class="col-sm-3">아이디*</label>
              <input type="text" name="member_id" class="col-sm-6 form-control mr-2"/>
              <button type="button" class="btn bg-primary col-sm-2 " onclick="idCheck();"> 중복확인</button>
              <input type="hidden" name="idCheck2" value="0" />
            </div>
           <div class="row mt-2">
              <label for="" class="col-sm-3">비밀번호*</label>
              <input name="member_pwd" type="password" class="col-sm-6 form-control"/>
              <input type="hidden" name="pwdCheck2" value="0" />
              
            </div>
           <div class="row mt-2">
              <label for="" class="col-sm-3">비밀번호 확인*</label>
              <input name="pwd_check" type="password" class="col-sm-6 form-control" onchange="pwdCheck();"/>
            </div>
           <div class="row mt-2">
              <label for="" class="col-sm-3">이름*</label>
              <input name="member_name" type="text" class="col-sm-6 form-control"/>
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
              <label for="" class="col-sm-3">부서 및 소속</label>
              <select class="col-sm-3 form-control" name="member_dep" onchange="changeTeam(this.value)">
                 <option value="$">부서 선택</option>
              <c:forEach var="dep" items="${depList }">
                 <option value="${dep.department_id }">${dep.department_name }</option>
              </c:forEach>
              </select>
              <select id="teamList" name="member_team" class="col-sm-3 form-control">
                 
              </select>
            </div>

           <div class="row mt-2">
              <label for="" class="col-sm-3">직급</label>
              <select class="form-control col-sm-3"name="member_rank">
                 <option value="부장">부장</option>
                 <option value="대리">대리</option>
                 <option value="사원">사원</option>
              </select>
            </div>
            
           
           <div class="row mt-2">
              <label for="" class="col-sm-3">생년월일</label>
              <input type="date" name="member_birth" class="col-sm-6 form-control"/>
            </div>

           <div class="row mt-2">
              <label for="" class="col-sm-3">주소</label>
              <input type="text" name="member_address" class="col-sm-6 form-control"/>
            </div>
           <br />
     
        </div>
     </section>
     
     <div class="text-center mt-4">
        <button type="button" class="btn bg-gradient col-sm-3" style="background: #5865F2; color:#ffffff;" onclick="registMember()">등록</button>
     </div>
     
     </form>
  
  
  <form role="imageForm" action="picture.do" method="post" enctype="multipart/form-data">
   <input id="inputFile" name="pictureFile" type="file" class="form-control" style="display:none;" onchange="picture_go();">
   <input id="oldFile" type="hidden" name="oldPicture" value="test" />
   <input type="hidden" name="checkUpload" value="0" />   
   </form>
     
     
   </div>
<%@include file="/WEB-INF/jsp/include/openfoot.jspf"%>
<script>
function registMember(){
   
    var uploadCheck = $('input[name="checkUpload"]').val();
     if(uploadCheck=='0'){
        alert("사진 업로드는 필수입니다.");
        return;
     }
     
   if($('input[name="member_id"]').val()==""){
      alert("아이디를 입력해주세요");
      $('input[name="member_id"]').css("border-color","red");
      $('input[name="member_id"]').focus();
      return false;
   }
   if($('input[name="idCheck2"]').val()!=1){
      alert("아이디 중복확인을 해주세요");
      $('input[name="member_id"]').css("border-color","red");
      $('input[name="member_id"]').focus();
      return false;
   }
   if($('input[name="pwdCheck2"]').val()!=1){
      alert("비밀번호 확인이 올바르지 않습니다.");
      $('input[name="member_pwd"]').focus();
      return false;
   }
   if($('input[name="member_name"]').val()==""){
      alert("이름을 입력해주세요");
      $('input[name="member_name"]').focus();
      return false;
   }
   if($('select[name="phone1"]').val()==""||$('input[name="phone2"]').val()==""||$('input[name="phone3"]').val()==""){
      alert("전화번호 입력이 올바르지 않습니다.");
      $('input[name="phone2"]').focus();
      return false;
   }
   if($('input[name="email1"]').val()==""||$('input[name="email2"]').val()==""){
      alert("이메일 입력이 올바르지 않습니다.");
      $('input[name="email2"]').focus();
      return false;
   }
   if($('select[name="member_dep"]').val()==""){
      alert("소속부서를 선택해주세요.");
      $('select[name="member_dep"]').focus();
      return false;
   }
   if($('select[name="member_team"]').val()==""){
      alert("소속 팀을 선택해주세요.");
      $('select[name="member_team"]').focus();
      return false;
   }
   if($('select[name="member_rank"]').val()==""){
      alert("직급을 선택해주세요.");
      $('select[name="member_rank"]').focus();
      return false;
   }
   if($('input[name="member_address"]').val()==""){
      alert("주소를 입력해주세요");
      $('input[name="member_address"]').focus();
      return false;
   }
   
   
   var form=$('form[role="form"]');
   form.submit();
   
   
   
}

function idCheck(){
   var id=$('input[name="member_id"]').val();
   $.ajax({
      url: "idCheck",
      data: {
         member_id: id
      },
      dataType: "text",
      traditional: true,
      success:function(data){
         if(data==1){
            alert('사용가능한 아이디입니다.');
            $('input[name="idCheck2"]').val("1");
            $('input[name="member_id"]').css("border-color","green");
            //alert($('input[name="idCheck2"]').val());
         }else{
            alert('이미 존재하는 아이디입니다.');
            $('input[name="member_id"]').css("border-color","red");
            $('input[name="idCheck2"]').val("0");
            //alert($('input[name="idCheck2"]').val());
         }
      }
   });
   
   
   
}

function pwdCheck(){
   var pwd=$('input[name="member_pwd"]').val();
   var pwdCheck=$('input[name="pwd_check"]').val();
   
   if(pwd==pwdCheck){
      $('input[name="member_pwd"]').css("border-color","green");
      $('input[name="pwd_check"]').css("border-color","green");
      $('input[name="pwdCheck2"]').val("1");
   }else{
      $('input[name="member_pwd"]').css("border-color","red");
      $('input[name="pwd_check"]').css("border-color","red");
      $('input[name="pwdCheck2"]').val("0");
   }
   
   
}

function picture_go(){
    //alert("change file");
    var form = $('form[role="imageForm"]');
    var picture = form.find('[name=pictureFile]')[0];
    
    var fileFormat = picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
    //이미지 확장자 jpg확인
    if(!(fileFormat=="JPG" || fileFormat=="JPEG")){
       alert("이미지는 jpg 형식만 가능합니다.");
       return;
    }
    //이미지파일 용량 체크
    if(picture.files[0].size>1024*1024*1){
       alert("사진 용량은 1MB 이하만 가능합니다.");
       return;
    }
    
    if(picture.files && picture.files[0]){
       var reader = new FileReader();
       
       reader.readAsDataURL(picture.files[0]);
       
       reader.onload = function (e) {
          var pictureView = $('div#pictureView')[0];
          //이미지 미리보기
          pictureView.style.backgroundImage ="url("+e.target.result+")";
          pictureView.style.backgroundPosition ="center";
          pictureView.style.backgroundSize ="cover";
          pictureView.style.backgroundRepeat ="no-repeat";
       }
    }
    
    form.find('[name="checkUpload"]').val(0);
    //alert(picture.files[0].name);
    //$('input[name=tempPicture]').val(picture.files[0].name);
    $('#inputFileName').val(picture.files[0].name);
   
    
 }
 
function upload_go(){
    //alert("upload btn");
    //$('form[role="imageForm"]').submit();
    if(!$('input[name="pictureFile"]').val()){
       alert("사진을 선택하세요.");
       $('input[name="pictureFile"]').click();
       return;
    }
    
    if($('input[name="checkUpload"]').val()==1){
       alert("이미 업로드된 사진 입니다.");
       return;
    }
    
    var formData = new FormData($('form[role="imageForm"]')[0]);
    
    $.ajax({
       url:"picture.do",
       data:formData,
       type:"post",
       processData:false,
       contentType:false,
       success:function(data){
          //업로드 확인변수 세팅
          $('input[name="checkUpload"]').val(1);
          //저장된 파일명 저장
          $('input#oldFile').val(data);//변경시 삭제될 파일명
          $('form[role="form"] input[name="member_pic"]').val(data);
          alert("사진이 업로드 되었습니다.");
       },
       error:function(error){
          
       }
    });
    
 }
 
 function changeTeam(dex){
    //alert(dex.value);
    $('#teamList').empty();
    
    var perArr=['인사1팀','인사2팀', '인사3팀'];
    var marArr=['마케팅1팀','마케팅2팀','마케팅3팀'];
    var yuArr=['영업1팀','영업2팀','영업3팀'];
    var careArr=['관리1팀','관리2팀','관리3팀'];
    
    
    var optionText2='</option>'
    var resultOption='';
    if(dex==1){
       /* alert(dex);
       alert(perArr.length); */
      for(var i=0;i<perArr.length;i++){
          //(optionText1+perArr[i]+optionText2).appendTo('#teamList');
          var j= i+1
          
          var optionText1='<option value="'+j+'">';
          resultOption+=optionText1+perArr[i]+optionText2;
       }
       $('#teamList').append(resultOption);
    }    
       
    if(dex==2){
       /* alert(dex);
       alert(perArr.length); */
      for(var i=0;i<marArr.length;i++){
          //(optionText1+perArr[i]+optionText2).appendTo('#teamList');
          var j= i+4
          var optionText1='<option value="'+j+'">';
          resultOption+=optionText1+marArr[i]+optionText2;
       }
       $('#teamList').append(resultOption);
       
       
    }
    if(dex==3){
       /* alert(dex);
       alert(perArr.length); */
      for(var i=0;i<yuArr.length;i++){
          //(optionText1+perArr[i]+optionText2).appendTo('#teamList');
          var j= i+7
          var optionText1='<option value="'+j+'">';
          resultOption+=optionText1+yuArr[i]+optionText2;
       }
       $('#teamList').append(resultOption);
       
       
    }
    if(dex==4){
       /* alert(dex);
       alert(perArr.length); */
      for(var i=0;i<careArr.length;i++){
          //(optionText1+perArr[i]+optionText2).appendTo('#teamList');
          var j= i+10
          var optionText1='<option value="'+j+'">';
          resultOption+=optionText1+careArr[i]+optionText2;
       }
       $('#teamList').append(resultOption);
       
       
    }
    
 }
</script>