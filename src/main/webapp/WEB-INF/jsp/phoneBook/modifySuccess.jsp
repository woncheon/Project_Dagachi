<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
		
	window.opener.location.href='showList?phone_book_type=${param.phone_Book_Type}&phone_book_register=${register}';
	window.close();
</script>