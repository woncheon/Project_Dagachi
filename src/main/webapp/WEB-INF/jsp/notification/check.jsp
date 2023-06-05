<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<span class="dropdown-header">
  ${not empty notificationsWhereDelstatusIsZeroCount ? notificationsWhereDelstatusIsZeroCount : '0'}개 입니다.
</span>
<div class="dropdown-divider"></div>
<c:forEach items="${notifications}" var="notification">
  <a href='${notification.notification_url}' class='dropdown-item'>
    <i class='fas fa-envelope mr-2'></i>
    ${notification.notification_body }
    <span class='float-right text-muted text-sm'>
      ${notification.notification_date }
    </span>
  </a>
  <div class='dropdown-divider'></div>
</c:forEach>
