<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/head.jspf"%>
<div class="content-wrapper kanban" style="">
<section class="content-header">
<div class="container-fluid">
<div class="row">
<div class="col-sm-6">
<h1>
   <a href='javascript:registBookMark("/dagachi/projectS/list", "개인 업무관리")'>
   <i class="fas fa-star bookmarkCheck"></i>
   </a>
      <span style="color: black;">
     개인 업무 관리
      </span>
   </h1>
</div>
<div class="col-sm-6  d-none d-sm-block">
<ol class="breadcrumb float-sm-right">
<li class="breadcrumb-item"><a href="#">업무 관리</a></li>
<li class="breadcrumb-item active">업무 현황</li>
</ol>
</div>
</div>
</div>
</section>

<section class="content pb-3 row resultPs">
<div class="container-fluid col-sm-11 h-100 justify-content-center">

<div class="card card-row col-sm-3 card-primary">
<div class="card-header">
<h3 class="card-title">
	할 일
</h3>
</div>
	<div class="row">
	<c:forEach var="wait" items="${pSWaitList }">
		<div class="col-12">
		
		<div class="card">
		<div class="card-header">
		<h3 class="card-title">${wait.extra_pl_name }</h3>
		<div class="card-tools">
		<button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
		<i class="fas fa-minus"></i>
		</button>
		
		</div>
		</div>
			<div class="card-body">
				<div>
				${wait.ps_title }
				</div>
				<div>
				<fmt:formatDate value="${wait.ps_EndDate }" pattern="yyyy-MM-dd" var="endDate"/>
				마감기한: ${endDate }
				</div>
				
			</div>
		
		<div class="card-footer">
		<button class="btn btn-block btn-primary" onclick="doing(1,'${wait.ps_Id}');">
			진행
		</button>
		</div>
		
		</div>
		
		</div>
	</c:forEach>
</div>
</div>
<div class="card card-row col-sm-3 card-primary">
<div class="card-header">
<h3 class="card-title">
	진행 중 
</h3>
</div>
	<div class="row">
	<c:forEach var="doing" items="${pSdoingList }">
		<div class="col-12">
		
		<div class="card">
		<div class="card-header">
		<h3 class="card-title">${doing.extra_pl_name }</h3>
		<div class="card-tools">
		<button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
		<i class="fas fa-minus"></i>
		</button>
		</div>
		</div>
			<div class="card-body">
				<div>
				${doing.ps_title }
				</div>
				<div>
				<fmt:formatDate value="${doing.ps_EndDate }" pattern="yyyy-MM-dd" var="endDate"/>
				마감기한: ${endDate }
				</div>
			</div>
		
		<div class="card-footer">
		<button class="btn btn-block btn-primary" onclick="doing(2,'${doing.ps_Id}');">
			진행
		</button>
		</div>
		
		</div>
		
		</div>
	</c:forEach>
</div>
</div>
<div class="card card-row col-sm-3 card-primary">
<div class="card-header">
<h3 class="card-title">
	완료
</h3>
</div>
	<div class="row">
	<c:forEach var="complete" items="${pScompleteList }">
		<div class="col-12">
		
		<div class="card">
		<div class="card-header">
		<h3 class="card-title">${complete.extra_pl_name }</h3>
		<div class="card-tools">
		<button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
		<i class="fas fa-minus"></i>
		</button>
		</div>
		</div>
			<div class="card-body">
				<div>
				${complete.ps_title }
				</div>
				<div>
				<fmt:formatDate value="${complete.ps_EndDate }" pattern="yyyy-MM-dd" var="endDate"/>
				마감기한: ${endDate }
				</div>
			</div>
		
		</div>
		
		</div>
	</c:forEach>
</div>
</div>

<div class="card card-row col-sm-3 card-primary">
<div class="card-header">
<h3 class="card-title">
	취소
</h3>
</div>
	<div class="row">
	<c:forEach var="cancel" items="${pSCancelList }">
		<div class="col-12">
		
		<div class="card">
		<div class="card-header">
		<h3 class="card-title">${cancel.extra_pl_name }</h3>
		<div class="card-tools">
		<button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
		<i class="fas fa-minus"></i>
		</button>
		<button type="button" class="btn btn-tool" onclick="remove('${cancel.ps_Id}')">
		<i class="fas fa-times"></i>
		</button>
		</div>
		</div>
			<div class="card-body">
				<div>
				${cancel.ps_title }
				</div>
				<div>
				<fmt:formatDate value="${cancel.ps_EndDate }" pattern="yyyy-MM-dd" var="endDate"/>
				마감기한: ${endDate }
				</div>
			</div>
		
		</div>
		
		</div>
	</c:forEach>
</div>
</div>



</div>
</section>
</div>
<input type="hidden" name="inputIdValue" value="" />

<!-- 모달창 -->
				


<%@include file="../include/foot.jspf"%>

<script>
function doing(stat,id){

if(confirm("상태를 변경 하시겠습니까?")){
	
	$.ajax({
		url:'modify',
		data:{
			stat: stat,
			ps_id: id
		},
		dataType: 'text',
		traditional: true,
		success: function(data){
			$('.resultPs').html(data);
		}
	});
}	
}

function remove(ps_Id){
	$.ajax({
		url:'remove',
		data:{
			ps_id: ps_Id
		},
		dataType: 'text',
		traditional: true,
		success:function(data){
			$('.resultPs').html(data);
		}
	})
}


window.onload=function(){
	bookMarkList();
    checkedBookMark('/dagachi/projectS/list');
}
</script>