<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<table style="width:80%; position:relative; left:10%;" >
	<tr>
		<td style="font-size:30px;">전문 쇼핑몰</td>
	</tr>
</table>

<%@include file="guest_top.jsp"%>
<table style="width:80%; " align="center">
<% if(memid!=null){%>
	<tr style="text-align:center;">
		<td style="font-size:20px;">
			<%=memid %>님 방문을 환영합니다.
			<img title="환영" src="../image/pic2.gif">
		</td>
	</tr>
<%}else{%>
	<tr style="text-align:center;">
		<td style="font-size:20px; background-image:url(../image/pic.jpg;); background-size:100%;">
			고객님 어서오세요.
			<br><br><br><br>로그인 후 이용 바랍니다.
		</td>
	</tr>
<%}%>
</table>


<%@ include file="guest_bottom.jsp"%>
</body>
</html>