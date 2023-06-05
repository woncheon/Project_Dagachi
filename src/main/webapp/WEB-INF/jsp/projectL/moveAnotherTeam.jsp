<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../include/openhead.jspf"%>
	
	<section class="content-header">
        <div class="container-fluid">
           <div class="row md-2">
              <div class="col-sm-6">
                 <h1>업무 설정</h1>              
              </div>
              <div class="col-sm-6">
                 <ol class="breadcrumb float-sm-right">
                 <li class="breadcrumb-item">
                    <a href="#">
                       <i class="fa fa-dashboard">업무 관리</i>
                    </a>
                 </li>
                 <li class="breadcrumb-item active">
                    업무 설정
                 </li>              
            </ol>
              </div>
           </div>
        </div>
        <hr/>
     </section>
     <section class="col-12 mx-auto content-body mb-2"  >
     	<div class="mt-2">
     		<div class="row justify-content-center">
	     		<div class="card card-row col-sm-5 mr-4 card-primary" style="min-height:500px;">
					<div class="card-header">
						<h3 class="card-title">
							${teamName } 업무 목록
						</h3>
					</div>
					<button class="btn moveall btn-outline-secondary" onclick="moveRightAll();" >&gt;&gt;</button>
					<ul id="beforeSelect">
						<c:forEach var="pl" items="${plList }">
							<li class="li li${pl.pl_Id }"  style="list-style:none; cursor: pointer;" value="${pl.pl_Id }" onclick="move('${pl.pl_Id}')">${pl.pl_Name }</li>
						</c:forEach>
					</ul>
				</div>
				
				<div class="card card-row col-sm-5 card-primary">
					<div class="card-header">
						<h3 class="card-title">
							선택한 업무 목록
						</h3>
					</div>
					<button class="btn moveall btn-outline-secondary" onclick="moveLeftAll();"> &lt;&lt;</button>
					<ul id="afterSelect">
					
					</ul>
				</div>
     		</div>
     	</div>
     </section>
     <section class="row justify-content-center">
     	<button type="button" class="btn bg-gradient col-sm-2 mr-2" data-toggle="modal" data-target="#modal-default"style="background: #5865F2; color:#ffffff;">
			팀 이동
		</button>
     	<button type="button" class="btn bg-gradient col-sm-2" style="background: #5865F2; color:#ffffff;" onclick="removePl();">삭제</button>
     </section>
     
     <input type="hidden" name="inputValue" value=""/>
     
     <div class="modal fade" id="modal-default" style="display: none;" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<h4 class="modal-title">업무 양도</h4>
<button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">×</span>
</button>
</div>
<div class="modal-body">
	해당 업무를 넘길 팀을 선택해 주세요
	<select name="" id="moveTeamName" class="col-sm-12 my-4">
		<c:forEach var="team" items="${teamlist }">
		<option value="${team.team_name }">${team.team_name }</option>
		</c:forEach>
	</select>
</div>
<div class="modal-footer justify-content-between">
<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<button type="button" class="btn btn-primary" onclick="moveTeam();" data-dismiss="modal">확인</button>
</div>
</div>

</div>

</div>
     
<%@include file="../include/openfoot.jspf"%>
<script>
	function moveRightAll(){
		var beforeSelect=$('#beforeSelect');
		beforeSelect.children().appendTo('#afterSelect').children();
		
		//alert(beforeSelect);
		
	}
	
	function moveLeftAll(){
		var afterSelect=$('#afterSelect');
		afterSelect.children().appendTo('#beforeSelect').children();
	}
	
	function move(dex){
		var classText=".li"+dex;
		var selectLi=$(classText);
		//alert(selectLi.val());
		
		if(selectLi.parent().is('#beforeSelect')===true){
			selectLi.appendTo('#afterSelect').children();
		}else{
			selectLi.appendTo('#beforeSelect').children();
		};
		
	}
	
	
	
	function removePl(){
	var arr=[];
		$('#afterSelect li').each(function(index, item){
			//console.log(item.value);
			arr.push(item.value);
		});
		//alert(arr);
		$('input[name=inputValue]').val(arr);
		//alert($('input[name=inputValue]').val());
		
		if(confirm('해당 업무를 취소 하시겠습니까?')){
			$.ajax({
				url: 'removePl',
				dataType: 'text',
				data: {
					selectArr: arr
				},
				traditional: true,
				success: function(data){
					$('#afterSelect').empty();
					window.opener.location.reload();
				}
				
			});
		}
		
	}
	
	function moveTeam(){
		var arr=[];
		$('#afterSelect li').each(function(index, item){
			//console.log(item.value);
			arr.push(item.value);
		});
		//alert(arr);
		$('input[name=inputValue]').val(arr);
		//alert($('input[name=inputValue]').val());
		var tn=$('#moveTeamName').val();
		
		if(confirm('해당 업무를 이동 하시겠습니까?')){
			$.ajax({
				url: 'movePl',
				dataType: 'text',
				data: {
					selectArr: arr,
					team_name: tn 
				},
				traditional: true,
				success: function(data){
					$('#afterSelect').empty();
					window.opener.location.reload();
				}
				
			});
		} 
	}
	
</script>