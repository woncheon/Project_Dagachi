<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:forEach var="sharePhone" items="${phoneBookList }">
	          <tr>
	           <td><input type="checkbox" class="ab" value="${sharePhone.phone_Book_Id }"/></td>
	           
	           <td>
	           	<a href="javascript:registFav('${sharePhone.phone_Book_Id }');">
	           		<i class="fas fa-star"></i>
	           	</a>
	           </td>
	           
	           <td>
	           	<a href="javascript:OpenWindow('detail?phone_Book_Id=${sharePhone.phone_Book_Id }','주소록 상세조회',800,800);">
	           		${sharePhone.phone_Book_Name }
	           	</a>
	           </td>
	           
	           <td>${sharePhone.phone_Book_Phone }</td>
	           
	           <td>${sharePhone.phone_Book_Email }</td>
	           
	           <td>${sharePhone.phone_Book_Company }</td>
	           
	           <td>
	           		<c:if test="${fn:contains(sharePhone.phone_Book_Tag,',') }">
		           		<c:forEach items="${fn:split(sharePhone.phone_Book_Tag, ',') }" var="tag">
		           			<a href="showList?searchType=tag&phone_book_type=${phoneBookType}&keyword=${tag }">
		           				${tag }
		           			</a>
		           		</c:forEach>
	           		</c:if>
	           		<c:if test="${not fn:contains(sharePhone.phone_Book_Tag,',') }">
		           			<a href="showList?searchType=tag&phone_book_type=${phoneBookType}&keyword=${sharePhone.phone_Book_Tag }">
		           				${sharePhone.phone_Book_Tag}
		           			</a>
	           		</c:if>
	           		
	           </td>
	          </tr>
	        </c:forEach>