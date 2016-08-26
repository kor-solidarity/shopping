<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"></jsp:useBean>
<%
request.setCharacterEncoding("utf-8");
String id=request.getParameter("id");
boolean b=memberMgr.checkId(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<%=id %>
<%if(b){%>	

	는 이미 사용중 입니다.<p/>
	<a href="#" onclick="opener.document.regForm.id.focus(); window.close()">닫기</a>
<%}else{%>
	는(은) 사용 가능합니다. 
	<a href="#" onclick="opener.document.regForm.passwd.focus(); window.close()">닫기</a>
<%}%>
</body>
</html>