<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/openhead.jspf"%>
	<section class="content-header">
        <div class="container-fluid">
           <div class="row md-2">
              <div class="col-sm-6">
                 <h1>업무 등록</h1>              
              </div>
              <div class="col-sm-6">
                 <ol class="breadcrumb float-sm-right">
                 <li class="breadcrumb-item">
                    <a href="#">
                       <i class="fa fa-dashboard">업무 관리</i>
                    </a>
                 </li>
                 <li class="breadcrumb-item active">
                    업무 등록
                 </li>              
            </ol>
              </div>
           </div>
        </div>
        <hr/>
     </section>
     <form role="form" action="regist">
     <section class="col-10 mx-auto content-body"  >
     	<div class="mt-2">
     		<div class="row">
 	    		<label for="" class="col-sm-3">업무 명*</label>
    	 		<input name="pl_name" type="text" class="col-sm-6 form-control"/>
   			</div>

     		<div class="row mt-2">
    	 		<label for="" class="col-sm-3">마감기한*</label>
	     		<input name="pl_endDate"type="date" class="col-sm-6 form-control phone"/><!-- 데이트 받을곳 -->
     		</div>
     		
     		<div class="row mt-2">
    	 		<label for="" class="col-sm-3">상세업무 입력</label>
	     		<input name="pl_body"type="text" class="col-sm-6 form-control mr-2" onkeyup="enterKey();"/>
     			<button type="button" class="btn bg-gradient col-sm-2"
     			style="height:40px;background: #5865F2; color:#ffffff;" >업무 추가</button>
     		</div>
     		
     		<div class="row mt-2">
 	    		<label for="" class="col-sm-3">상세업무*</label>
    	 		<div class="form-control col-sm-6 mr-2" style="height:200px;overflow-y: scroll;">
    	 			<ul id="detailLi" class="col-sm-12 h-full" >
    	 			
    	 			</ul>
    	 		</div>
    	 		
     			
   			</div>
     		
   			
   			
     		<div class="row mt-2">
 	    		<label for="" class="col-sm-3">담당 부서</label>
    	 		<select name="pl_team" class="col-sm-6 form-control">
					<c:forEach var="team" items="${teamlist }">
						<option  value="${team.team_name}">${team.team_name }</option>
					</c:forEach>
    	 		</select>
   			</div>
     		
     		<br />
     		<span style="color:gray">*표시 되어있는 항목은 필수입력 사항입니다.</span>
     	</div>
     </section>
     
     <div class="text-center mt-4">
     	<button type="button" class="btn bg-gradient col-sm-3 mb-4" style="background: #5865F2; color:#ffffff;" onclick="registPl();">등록</button>
     </div>
     
     </form>
<%@include file="../include/openfoot.jspf"%>

<script>
function remove_go(dataNum){
	var classText='.detail'+dataNum;
	   $(classText).remove();
}


var i=1;
function enterKey() {
    if (window.event.keyCode == 13) {
		//alert($('input[name=pl_body]').val());
		var pmLi='<li class="detail'+i+' row mt-2" >'+'</li>';
		var pmLiSpan='<span class="col-sm-11" style="overflow-x:hidden;">'+$('input[name=pl_body]').val()+'</span>'
		var pmLiBtn="<button onclick='remove_go("+i+");'style='border:0;outline:0;' class='badge bg-red text-right' type='button'>x</button>"
		//alert(pmLi);
		$('#detailLi').append(pmLi);
		var detailName='.detail'+i;
		$(detailName).append(pmLiSpan);
		$(detailName).append(pmLiBtn);
		i++;
		$('input[name=pl_body]').val('');
    }
}

function replaceResult(str){
	//alert(str);
	
	$.ajax({
		url: 'plList',
		dataType: 'text',
		data: {
			teamName:str
		},
		traditional: true,
		success: function(data){
			
			$('.resultSection').html(data);
			
		}
	});
}

function registPl(){
	var formvalue=$('form[role=form]').serialize();
	var teamname=$('select[name=pl_team]').val();
	var arr=[];
	$('#detailLi li span').each(function(index, item){
		//console.log(item.value);
		arr.push(item.innerText);
	});
	
	
	
	$.ajax({
		url: "regist",
		
		data: {
			pl_name:$('input[name=pl_name]').val(),
			pl_endDate:$('input[name=pl_endDate]').val(),
			pl_manager:'',
			pl_team: $('select[name=pl_team]').val(),
			pl_bodyArr: arr 
		},
		traditional: true,
		dataType: 'text',
		
		success: function(data){
			window.opener.replaceResult($('select[name=pl_team]').val());
			window.close();
		}
	});
}
</script>