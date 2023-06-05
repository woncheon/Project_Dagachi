<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="/WEB-INF/jsp/include/openhead.jspf" %>
<style>
.rounded_div {
  border-radius: 50%;
  color:black;
  height: 100px;
  width: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
}
.roundeddiv {
  border-radius: 50%;
  background-color: whtie;
  height: 100px;
  width: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
   font-weight: bold;
   font-size:1.5rem;
}

</style>

  <div class="inner" style="border:1px solid black; width: 550px;height: 100px; color: white; font-size: 1.2rem; display: flex; margin-left:230px; margin-top:15px;">
    <input type="hidden" name="vacation_member" />

    <div class="rounded_div" style="margin-right: 50px;">
   
				<span style="margin-right:10px;">총 연차 <div style="  font-weight: bold;">${vacationYear }</div></span>
				</div><div class="roundeddiv" style="margin-right: 50px;">
				<div style="color:black; height:20px;  text-align:center; ">-</div></div>
				<div class="rounded_div" style="margin-right: 50px;">
				<span >사용 연차<div style="  font-weight: bold;"> ${DelYear }</div></span>
				</div>
				<div class="roundeddiv" style="margin-right: 50px;">
				<div style="color:black; height:20px;  text-align:center;">=</div>
				</div>
				<div class="rounded_div" style="margin-right: 50px;">
				<span class="delYear">잔여 연차<div style="font-weight: bold;"> ${vacationYear -  DelYear }</div></span>
		
    </div>
    </div>
<div class="inner" style=" border:1px solid black;width: 550px; height: 100px; color: white; font-size: 1.2rem; display: flex; margin-top:10px; margin-left:230px;">
    <div class="rounded_div"style="margin-right: 50px;">
    <span >총 월차 <div style="font-weight: bold;">${vacationMonth }</div></span>
    </div>
    <div class="roundeddiv" style="margin-right: 50px;">
				<div style="color:black; height:20px;  text-align:center;">-</div></div>
    <div class="rounded_div" style="margin-right: 50px;">
				<span >사용 월차 <div style="font-weight: bold;">${DelYear }</div></span>
     
    </div>
    	<div class="roundeddiv" style="margin-right: 50px;">
				<div style="color:black; height:20px;  text-align:center;">=</div>
				</div>
    <div class="rounded_div" style="margin-right: 50px;">
				<span>잔여 월차 <div style="font-weight: bold;">${vacationMonth - DelMonth }</div></span>
	</div>
	
 
</div>


	<div class="col-sm-11">
	<div class="card card-success"  style="margin-top: 15px; margin-left:70px;">
<div class="card-header" style="background-color:#333c9e;">
 

  <div style="text-align: left;">
    <h6 style="text-align: left;">연/월차 조회</h6>
  </div>
<div class="card-tools" style="text-align: left;">
</div>

</div>


<div class="card-body ">
<table class="table table-fixed w-full">
	<thead>
				<tr>
					<th>연/월차 생성일(생성갯수)</th>
					<th>연/월차 사용일</th>
					<th>연/월차 사용개수</th>
					<th>사유</th>
				</tr>
				</thead>
<tbody>
				<c:forEach items="${vacation }" var="vacation">
					<tr>
						<td><fmt:formatDate value="${vacation.year_vacation_regDate }" pattern="yyyy-MM-dd" /><fmt:formatDate value="${vacation.month_vacation_regDate }" pattern="yyyy-MM-dd" />(연차:${vacationYear }개/월차:${vacationMonth }개)</td>
						<td><fmt:formatDate value="${vacation.vacation_updateDate }" pattern="yyyy-MM-dd" /></td>
						<td>연차:${vacation.del_year_vacation_count }개/월차:${vacation.del_month_vacation_count }개</td>
						<td>
							<c:if test="${vacation.del_year_vacation_count > 0}">
							<button type="button" style="background-color:#bef78b; border:none;">연차</button>
							</c:if>
							<c:if test="${vacation.del_month_vacation_count > 0}">
							<button type="button" style="background-color:#f8e589; border:none;">월차</button>
							</c:if>
						${vacation.vacation_reason }
						</td>
					</tr>
				</c:forEach>
				</tbody>

</table>
</div>
</div>
</div>
		
	<%-- <div class="content-list" style="margin:10px;">
		<div class="mt-3">
	      <table class="table table-fixed w-full">
	      	<thead>
				<tr>
					<th>연/월차 생성일(생성갯수)</th>
					<th>연/월차 사용일</th>
					<th>연/월차 사용개수</th>
					<th>사유</th>
				</tr>
				<thead>
				<tbody>
				<c:forEach items="${vacation }" var="vacation">
					<tr>
						<td><fmt:formatDate value="${vacation.year_vacation_regDate }" pattern="yyyy-MM-dd" /><fmt:formatDate value="${vacation.month_vacation_regDate }" pattern="yyyy-MM-dd" />(연차:${vacationYear }개/월차:${vacationMonth }개)</td>
						<td><fmt:formatDate value="${vacation.vacation_updateDate }" pattern="yyyy-MM-dd" /></td>
						<td>연차:${vacation.del_year_vacation_count }개/월차:${vacation.del_month_vacation_count }개</td>
						<td>
							<c:if test="${vacation.del_year_vacation_count > 0}">
							<button type="button" style="background-color:#81c147; border:none;">연차</button>
							</c:if>
							<c:if test="${vacation.del_month_vacation_count > 0}">
							<button type="button" style="background-color:#ffd400; border:none;">월차</button>
							</c:if>
						${vacation.vacation_reason }
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
	</div> --%>


<%@include file="/WEB-INF/jsp/include/openfoot.jspf" %>