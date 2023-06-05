<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/openhead.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.card {
	height:100vh;
	margin: auto;
}
span{
margin-left:70px;
width:60px;
text-align: center;
}

input{
width:250px;
margin-left:20px;

}
</style>
<div class="content-wrapper">
	<div class="card">
		<div class="card-header"
			style="display: flex; justify-content: center; margin-top: 20px;">
			<h3>아이디/비밀번호 찾기</h3>
		</div>
		<div class="card-body">
<div class="box"
    style="box-shadow: 0px 0px 2px 0px; width: 600px; height: 300px; margin: auto; display: flex; flex-direction: column;">
   <form id="id"action="findId" method="post">
    <div style="display: flex; justify-content: center;">
        <h4>아이디 찾기</h4>
    </div>
    <div style="margin-left: 30px; height: 200px; display: flex; flex-direction: column; justify-content: space-around;">
        <div style="display: flex; align-items: center;">
            <span>이름</span> <input placeholder="이름을 입력해주세요." name="name" type="text" value="${name }">
        </div>
        <div style="display: flex; align-items: center;">
            <span>전화번호</span> <input placeholder="ex)010-0000-0000" name="phone"type="text" value="${phone }">
        </div>
        <div style="display: flex; align-items: center;">
            <span>이메일</span> <input  placeholder="example@xxxx.com" name="email"type="text" value="${email }">
        </div>
    </div>

    <div style="margin-right: 50px; display: flex; justify-content: flex-end;">
        <button type="button"onclick="idfind_go()"class="btn btn-block btn-primary" style="width: 60px; height: 40px;">찾기</button>
    </div>
    </form>
</div>

<div style="height: 10px;"></div>

<div class="box"
    style="padding-top: 20px; box-shadow: 0px 0px 2px 0px; width: 600px; height: 300px; margin: auto; display: flex; flex-direction: column;">
    <div style="display: flex; justify-content: center;">
        <h4>비밀번호 찾기</h4>
    </div>
        <form id="pwd" method="post" action="findPwd">
    <div style="margin-left: 30px; height: 200px; display: flex; flex-direction: column; justify-content: space-around;">
        <div style="display: flex; align-items: center;"> 
            <span>아이디</span> <input placeholder="아이디를 입력해주세요."   name="id" type="text"value="${id }" >
        </div>
        <div style="display: flex; align-items: center;">
            <span>이름</span> <input placeholder="이름을 입력해주세요."name="nameForPwd" type="text" value="${nameForPwd }">
        </div>
        <div style="display: flex; align-items: center;">
            <span>전화번호</span> <input placeholder="ex)010-0000-0000" name="phoneForPwd"type="text"value="${phoneForPwd }">
        </div>
        <div style="display: flex; align-items: center;">
            <span>이메일</span> <input placeholder="example@xxxx.com"name="emailForPwd" type="text" value="${emailForPwd}">
        </div>
    </div>
   

    <div style="margin-right: 50px; display: flex; justify-content: flex-end;">
        <button type="button" onclick="pwdfind_go()"class="btn btn-block btn-primary" style="width: 60px; height: 40px;">찾기</button>
    </div>
    </form>
</div>

		</div>
		
		<div style="margin-right:50px;display:flex; justify-content:flex-end;">
		<button onclick="window.close()"class="btn btn-block btn-secondary" style="width:60px; height:40px;">취소</button>
		</div>
	</div>
</div>



<script>
var msg = "${msg}";
if (msg !== null && msg !== "") {
  alert(msg);
}
	

function idfind_go() {
	  var name = document.getElementsByName("name")[0].value;
	  var phone = document.getElementsByName("phone")[0].value;
	  var email = document.getElementsByName("email")[0].value;

	  if (name.trim() === "" || phone.trim() === "" || email.trim() === "") {
	    alert("이름, 전화번호 및 이메일을 모두 입력해주세요.");
	    return;
	  }

	  // Email validation
	  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	  if (!emailRegex.test(email)) {
	    alert("이메일을 올바른 형식으로 입력해주세요. (ex: example@xxxx.com)");
	    return;
	  }

	  // Perform the rest of the logic for finding the ID
	  // ...

	  // Submit the form
	  document.forms["id"].submit();
	}
function pwdfind_go() {
	  var id = document.getElementsByName("id")[0].value;
	  var nameForPwd = document.getElementsByName("nameForPwd")[0].value;
	  var phoneForPwd = document.getElementsByName("phoneForPwd")[0].value;
	  var emailForPwd = document.getElementsByName("emailForPwd")[0].value;

	  if (id.trim() === "" || nameForPwd.trim() === "" || phoneForPwd.trim() === "" || emailForPwd.trim() === "") {
	    alert("아이디, 이름, 전화번호 및 이메일을 모두 입력해주세요.");
	    return;
	  }
	  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	  if (!emailRegex.test(emailForPwd)) {
	    alert("이메일을 올바른 형식으로 입력해주세요. (ex: example@xxxx.com)");
	    return;
	  }

	  // Phone number validation
	  var phoneRegex = /^\d{3}-\d{4}-\d{4}$/;
	  if (!phoneRegex.test(phoneForPwd)) {
	    alert("전화번호를 형식에 맞게 입력해주세요. (ex: 000-0000-0000)");
	    return;
	  }

	  // Perform the rest of the logic for finding the password
	  // ...

	  // Submit the form
	  document.forms["pwd"].submit();
	}
	
	
</script>





<%@include file="../include/openfoot.jspf"%>