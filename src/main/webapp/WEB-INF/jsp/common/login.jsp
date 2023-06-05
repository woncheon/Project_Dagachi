<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 데이지 UI -->
	<!-- <link href="https://cdn.jsdelivr.net/npm/daisyui@2.51.5/dist/full.css" rel="stylesheet" type="text/css" /> -->
	
<style>
.back{
	
	 width: 800px; /* body의 너비를 800px로 조절 */
  height: 600px; /* body의 높이를 600px로 조절 */
	background-image:url('../resource/img/login.jpg');
	background-repeat:no-repeat;
	background-position:center;
    background-size:cover;
}
.id{
	font-size:15px;
	border:0px;
	border-radius:30px 30px 30px 30px;
	width:300px; 
	height:60px; 
	margin-left:28px; 
	margin-top:35px;

}
.pwd{
	font-size:15px;
	border:0px;
	border-radius:30px 30px 30px 30px;
	width:300px; 
	height:60px;
	margin-left:28px;
	margin-top:30px;
}
.button{
	width:300px; 
	height:60px;
	margin-left:32px;
	margin-top:20px;
	border-radius:30px 30px 30px 30px;
	border:0px;
	background-color:#5865F2;
}
</style>





<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="back">
	<div style="margin-left:750px;padding-top:100px;width:400px; height:300px;">
		<form method="POST" action="dologin">
		<input type="text"  name="member_id"class="id" placeholder="아이디를 입력하세요.">
		<input type="password" name="member_pwd"class="pwd" placeholder="비밀번호를 입력하세요.">
		</br>
		<div style="paddin:none;margin-top:10px; margin-left:40px; font-size:15px;"><input type="checkbox">아이디 기억하기</input></div>
		<button type="submit" class="btn btn-block btn-primary button">LOGIN</button>
		</form>
	     <a href="javascript:window.open('../common/forget', 'dd', 'width=800,height=800');"style="display:block; padding:none;margin-top:20px; margin-left:170px;">아이디/비밀번호 찾기</a>
	
	</div>	

<script>
var msg = "${msg}";
if (msg !== null && msg !== "") {
  alert(msg);
}


</script>	
	
	
 
</body>
</html>