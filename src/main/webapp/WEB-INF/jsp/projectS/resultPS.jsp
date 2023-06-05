<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
