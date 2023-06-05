<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include/openhead.jspf"%>
	<div class="wrapper col-12" style="background: #f9f9f9;">
	<section class="content-header">
        <div class="container-fluid">
           <div class="row md-2">
              <div class="col-sm-6">
                 <h1>주소록 상세조회</h1>              
              </div>
              <div class="col-sm-6">
                 <ol class="breadcrumb float-sm-right">
                 <li class="breadcrumb-item">
                    <a href="#">
                       <i class="fa fa-dashboard">주소록</i>
                    </a>
                 </li>
                 <li class="breadcrumb-item active">
                    주소록 상세조회
                 </li>              
            </ol>
              </div>
           </div>
        </div>
        <hr/>
     </section>
     <section class="col-10 mx-auto content-body">
     	<div class="btns row">
     		<button type="button" class="btn bg-gradient col-sm-6" style="background: #5865F2; color:#ffffff;">공유</button>
     		<button type="button" class="btn bg-gradient col-sm-6" style="background: #f1f1f1">개인</button>
     	</div>
     	<div class="mt-4">

     		<div class="row">
 	    		<label for="" class="col-sm-3">이름*</label>
    	 		<input type="text" class="col-sm-6 form-control" disabled="disabled" value="${phoneBook.member_name }"/>
   			</div>

     		<div class="row mt-2">
     		<label for="" class="col-sm-3">전화번호</label>
     		<select class="form-control col-sm-2 mr-2" disabled="disabled" name="" id="">
     			<option value="010">010</option>
     			<option value="011">011</option>
     			<option value="016">016</option>
     			<option value="017">017</option>
     		</select>
     		<input type="text" class="col-sm-2 mr-2 form-control" disabled="disabled"/>
     		<input type="text" class="col-sm-2 form-control" disabled="disabled"/>
     		</div>
     		
     		<div class="row mt-2">
 	    		<label for="" class="col-sm-3">이메일</label>
    	 		<input type="text" class="col-sm-2 form-control" disabled="disabled"/>&nbsp;@&nbsp;
    	 		<input type="text" class="col-sm-3 form-control" disabled="disabled"/>
   			</div>
     		
   			</div>
     		
     		<div class="row mt-2">
 	    		<label for="" class="col-sm-3" >부서</label>
    	 		<input type="text" class="col-sm-6 form-control" disabled="disabled" value="${phoneBook.member_department }"/>
   			</div>

     		<div class="row mt-2">
 	    		<label for="" class="col-sm-3">팀</label>
    	 		<input type="text" class="col-sm-6 form-control" disabled="disabled" value="${phoneBook.member_team }"/>
   			</div>

     		<div class="row mt-2">
 	    		<label for="" class="col-sm-3">직급</label>
    	 		<input type="text" class="col-sm-6 form-control" disabled="disabled" value="${phoneBook.member_rank }"/>
   			</div>
     		
     		<div class="row mt-2">
 	    		<label for="" class="col-sm-3">주소</label>
    	 		<input type="text" class="col-sm-6 form-control" disabled="disabled" value="${phoneBook.member_address }"/>
   			</div>
     		<br />
     		<span style="color:gray">*표시 되어있는 항목은 필수입력 사항입니다.</span>
     	</div>
     </section>
     
     <div class="text-center mt-4">
     	<button type="button" class="btn bg-gradient col-sm-2" onclick="window.close()" 
     	style="background: #5865F2; color:#ffffff;">창 닫기</button>
     	
     </div>
     
     
     
     
	
<%@include file="/WEB-INF/jsp/include/openfoot.jspf"%>
<script>
function deleteGo(phoneBookId){
	if(!confirm('정말 삭제 하시겠습니까?')){
		//alert('아니오');
	}else{
		//alert('예');
		location.href='moveTrash?phone_Book_Id='+phoneBookId;
		alert("해당 정보는 휴지통으로 이동되었으며 30일 경과 후 자동으로 삭제됩니다.");
		window.opener.location.reload(true);
		window.close();
	}

}
</script>