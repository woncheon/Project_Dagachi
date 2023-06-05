<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="메인" />

<%@include file="../common/head.jspf" %>

<!-- fullcalendar css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<!-- fullcalendar 언어 설정관련 script -->
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/locales-all.js"></script>

<style>
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

  .progress {
    width: 100%;
    background-color: #f1f1f1;
    border-radius: 5px;
  }

  .progress-bar {
    width: 0%;
    height: 20px;
    background-color: #4CAF50;
    border-radius: 5px;
    transition: width 0.5s ease-in-out;
  }
</style>

<div class="content-wrapper">
  <div class="row" style="padding-top: 60px;">
    <div class="col-7">
      <div class="row m-4" style="width:100%;">
        <nav class="">
          <div class="nav nav-tabs" id="product-tab" role="tablist">
            <a class="nav-item nav-link active" id="approval-list-tab" data-toggle="tab" href="#approval-list" role="tab" aria-controls="approval-list" aria-selected="true">결재 목록</a>
            <a class="nav-item nav-link" id="approval-success-list-tab" data-toggle="tab" href="#approval-success-list" role="tab" aria-controls="approval-success-list" aria-selected="false">결재 승인 목록</a>
            <a class="nav-item nav-link" id="approval-false-list-tab" data-toggle="tab" href="#approval-false-list" role="tab" aria-controls="approval-false-list" aria-selected="false">결재 반려 목록</a>
          </div>
        </nav>
        <div class="tab-content" style="width:100%;" id="nav-tabContent">
          <div class="tab-pane fade show active" id="approval-list" role="tabpanel" aria-labelledby="product-desc-tab">
            <div class="card">
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th style="width:5%;">문서 번호</th>
                      <th style="width:40%;">문서 제목</th>
                      <th style="width:10%;">결재 날자</th>
                      <th style="width:5%;">첨부파일</th>
                      <th style="width:10%;">최초 결재자</th>
                      <th style="width:10%;">중간 결재자</th>
                      <th style="width:10%;">최종 결재자</th>
                      <th style="width:10%;">결재 상태</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:set var="count" value="0" />

                    <c:forEach var="test" items="${tests}">
                      <c:if test="${count lt 5}">
                        <tr>
                          <td>${test.tno}</td>
                          <td><a href="#" onclick="" style="color:black;">${test.title}</a></td>
                          <td>${test.date}</td>
                          <td style="text-align:center;">
                            <c:if test="${test.type ne null}">
                              <i class='fa fa-file'></i> <!-- Add your file icon element here -->
                            </c:if>
                          </td>
                          <td>${test.name}</td>
                          <td>${test.name}</td>
                          <td>${test.name}</td>
                          <td>
                            <c:choose>
                              <c:when test="${test.status eq 1}">대기</c:when>
                              <c:when test="${test.status eq 2}">승인</c:when>
                              <c:when test="${test.status eq 3}">반려</c:when>
                              <c:otherwise>Unknown</c:otherwise>
                            </c:choose>
                          </td>
                        </tr>
                        <c:set var="count" value="${count + 1}" />
                      </c:if>
                    </c:forEach>

                    <c:if test="${empty tests}">
                      <tr>
                        <td colspan="8">결재 내역이 없습니다.</td>
                      </tr>
                    </c:if>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="tab-pane fade" id="approval-success-list" role="tabpanel" aria-labelledby="product-comments-tab">
            <div class="card">
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th style="width:5%;">문서 번호</th>
                      <th style="width:40%;">문서 제목</th>
                      <th style="width:10%;">결재 날자</th>
                      <th style="width:5%;">첨부파일</th>
                      <th style="width:10%;">최초 결재자</th>
                      <th style="width:10%;">중간 결재자</th>
                      <th style="width:10%;">최종 결재자</th>
                      <th style="width:10%;">결재 상태</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:set var="count" value="0" />

                    <c:forEach var="test" items="${tests}">
                      <c:if test="${count lt 5}">
                      	<c:if test="${test.status eq 2}">
                        <tr>
                          <td>${test.tno}</td>
                          <td><a href="#" onclick="" style="color:black;">${test.title}</a></td>
                          <td>${test.date}</td>
                          <td style="text-align:center;">
                            <c:if test="${test.type ne null}">
                              <i class='fa fa-file'></i> <!-- Add your file icon element here -->
                            </c:if>
                          </td>
                          <td>${test.name}</td>
                          <td>${test.name}</td>
                          <td>${test.name}</td>
                          <td>
                            <c:if test="${test.status eq 2}">승인</c:if>
                          </td>
                        </tr>
                        <c:set var="count" value="${count + 1}" />
                        </c:if>
                      </c:if>
                    </c:forEach>
                    <c:if test="${empty tests}">
                      <tr>
                        <td colspan="8">승인된 결재 내역이 없습니다.</td>
                      </tr>
                    </c:if>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="tab-pane fade" id="approval-false-list" role="tabpanel" aria-labelledby="product-rating-tab">
            <div class="card">
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th style="width:5%;">문서 번호</th>
                      <th style="width:40%;">문서 제목</th>
                      <th style="width:10%;">결재 날자</th>
                      <th style="width:5%;">첨부파일</th>
                      <th style="width:10%;">최초 결재자</th>
                      <th style="width:10%;">중간 결재자</th>
                      <th style="width:10%;">최종 결재자</th>
                      <th style="width:10%;">결재 상태</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:set var="count" value="0" />

                    <c:forEach var="test" items="${tests}">
                      <c:if test="${count lt 5}">
                      	<c:if test="${test.status eq 3}">
                        <tr>
                          <td>${test.tno}</td>
                          <td><a href="#" onclick="" style="color:black;">${test.title}</a></td>
                          <td>${test.date}</td>
                          <td style="text-align:center;">
                            <c:if test="${test.type ne null}">
                              <i class='fa fa-file'></i> <!-- Add your file icon element here -->
                            </c:if>
                          </td>
                          <td>${test.name}</td>
                          <td>${test.name}</td>
                          <td>${test.name}</td>
                          <td>
                            <c:if test="${test.status eq 3}">반려</c:if>
                          </td>
                        </tr>
                        <c:set var="count" value="${count + 1}" />
                        </c:if>
                      </c:if>
                    </c:forEach>
                    <c:if test="${empty tests}">
                      <tr>
                        <td colspan="8">반려된 결재 내역이 없습니다.</td>
                      </tr>
                    </c:if>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row m-4" style="width:100%;">
        <nav class="">
          <div class="nav nav-tabs" id="product-tab" role="tablist">
            <a class="nav-item nav-link active" id="project-send-tab" data-toggle="tab" href="#project-send" role="tab" aria-controls="project-send" aria-selected="true">받은 요청 목록</a>
            <a class="nav-item nav-link" id="project-receive-tab" data-toggle="tab" href="#project-receive" role="tab" aria-controls="project-receive" aria-selected="false">보낸 요청 목록</a>
          </div>
        </nav>
        <div class="tab-content" style="width:100%;" id="nav-tabContent">
          <div class="tab-pane fade show active" id="project-send" role="tabpanel" aria-labelledby="product-desc-tab">
            <div class="card">
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th style="width:5%;">요청 번호</th>
                      <th>요청 제목</th>
                      <th style="width:10%;">요청 날자</th>
                      <th style="width:5%;">첨부파일</th>
                      <th style="width:10%;">요청자</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:set var="count" value="0" />

                    <c:forEach var="test" items="${tests}">
                      <c:if test="${count lt 5}">
                        <tr>
                          <td>${test.tno}</td>
                          <td><a href="#" onclick="" style="color:black;">${test.title}</a></td>
                          <td>${test.date}</td>
                          <td style="text-align:center;">
                            <c:if test="${test.type ne null}">
                              <i class='fa fa-file'></i> <!-- Add your file icon element here -->
                            </c:if>
                          </td>
                          <td>${test.name}</td>
                        </tr>
                        <c:set var="count" value="${count + 1}" />
                      </c:if>
                    </c:forEach>
                    <c:if test="${empty tests}">
                      <tr>
                        <td colspan="8">받은 요청이 없습니다.</td>
                      </tr>
                    </c:if>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="tab-pane fade" id="project-receive" role="tabpanel" aria-labelledby="product-comments-tab">
            <div class="card">
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th style="width:5%;">요청 번호</th>
                      <th>요청 제목</th>
                      <th style="width:10%;">요청 날자</th>
                      <th style="width:5%;">첨부파일</th>
                      <th style="width:10%;">요청자</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:set var="count" value="0" />

                    <c:forEach var="test" items="${tests}">
                      <c:if test="${count lt 5}">
                        <tr>
                          <td>${test.tno}</td>
                          <td><a href="#" onclick="" style="color:black;">${test.title}</a></td>
                          <td>${test.date}</td>
                          <td style="text-align:center;">
                            <c:if test="${test.type ne null}">
                              <i class='fa fa-file'></i> <!-- Add your file icon element here -->
                            </c:if>
                          </td>
                          <td>${test.name}</td>
                        </tr>
                        <c:set var="count" value="${count + 1}" />
                      </c:if>
                    </c:forEach>
                    <c:if test="${empty tests}">
                      <tr>
                        <td colspan="8">보낸 요청이 없습니다.</td>
                      </tr>
                    </c:if>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row m-4 w-100">
        <div class="col-6 pl-0">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">업무 진행도</h3>
            </div>
            <div class="card-body table-responsive p-0">
              <table class="table table-hover text-nowrap">
                <thead>
                  <tr>
                    <th style="width:20px;">번호</th>
                    <th>제목</th>
                    <th>진척도</th>
                  </tr>
                </thead>
                <tbody>
                  <c:set var="count" value="0" />

                  <c:forEach var="test" items="${tests}">
                    <c:if test="${count lt 5}">
                      <tr>
                        <td>${test.tno}</td>
                        <td><a href="#" onclick="" style="color:black;">${test.title}</a></td>
                        <td>
                          <div class="progress">
                            <div class="progress-bar" style="width: ${test.progress}%;" title="${test.progress}% 완료"></div>
                          </div>
                        </td>
                      </tr>
                      <c:set var="count" value="${count + 1}" />
                    </c:if>
                  </c:forEach>
                  <c:if test="${empty tests}">
                    <tr>
                      <td colspan="8">업무가 없습니다.</td>
                    </tr>
                  </c:if>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="col-6 pr-0">
          <div class=" card">
            <div class="card-header">
              <h3 class="card-title">공지사항</h3>
            </div>

            <div class="card-body table-responsive p-0">
              <table class="table table-hover text-nowrap">
                <thead>
                  <tr>
                    <th style="width:20px;">번호</th>
                    <th>제목</th>
                    <th>작성날자</th>
                  </tr>
                </thead>
                <tbody>

                  <c:set var="count" value="0" />

                  <c:forEach var="test" items="${tests}">
                    <c:if test="${count lt 5}">
                      <tr>
                        <td>${test.tno}</td>
                        <td><a href="#" onclick="" style="color:black;">${test.title}</a></td>
                        <td>${test.date}</td>
                      </tr>
                      <c:set var="count" value="${count + 1}" />
                    </c:if>
                  </c:forEach>
                  <c:if test="${empty tests}">
                    <tr>
                      <td colspan="8">공지사항이 없습니다.</td>
                    </tr>
                  </c:if>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-5">
      <div class="row mt-4">
        <div id='calendar'></div>
      </div>
      <div class="row m-4">
        <div class="card card-row card-secondary w-100 m-4 h-100">
          <div class="card-header">
            <h3 class="card-title">
              오늘 할일
            </h3>
          </div>
          <div class="card-body">
            <div class="card card-info card-outline">
              <div class="card-body">
                <div class="custom-control custom-checkbox">
                  <input class="custom-control-input" type="checkbox" id="customCheckbox1" disabled="">
                  <label for="customCheckbox1" class="custom-control-label">
                    ${empty project_s ? '업무가 없습니다.' : project_s}
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script>
    document.addEventListener('DOMContentLoaded', function() {
      var calendarEl = document.getElementById('calendar');
      var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
        headerToolbar: { // 헤더에 표시할 툴 바
          start: 'prev next today',
          center: 'title',
          end: 'dayGridMonth,dayGridWeek,dayGridDay'
        },
        titleFormat: function(date) {
          return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
        },
        //initialDate: '2021-07-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
        selectable: true, // 달력 일자 드래그 설정가능
        droppable: true,
        editable: true,
        nowIndicator: true, // 현재 시간 마크
        locale: 'ko' // 한국어 설정
      });
      calendar.render();
    });
  </script>

  <%@include file="../common/foot.jspf" %>