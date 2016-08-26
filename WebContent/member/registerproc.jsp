<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memberbean" class="shop.member.MemberBean"></jsp:useBean>
<jsp:setProperty property="*" name="memberbean"/>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"></jsp:useBean>
<%boolean b= memberMgr.memberInsert(memberbean); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/board.css">
<title>Insert title here</title>
</head>
<body>
<%
if(b){
	out.println("<b> 회원가입을 축하합니다</b><br>");
	out.println("<a href='../guest/login.jsp'>로그인</a>");
}else{
	out.println("<b>회원가입실패! 관리자에 문의 바람 </b><br>");
	out.println("<a href='register.jsp'>가입 재시도</a>");
	}
%>
</body>
</html>