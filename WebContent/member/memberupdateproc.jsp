<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="shop.member.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"></jsp:useBean>
<%

String id=(String)session.getAttribute("idKey");
boolean b=memberMgr.memberUpdate(bean,id);
%>


<script>
<%if(b){%>
	
	alert("수정 성공");
	location.href="../guest/guest_index.jsp";

<%}else{%>
	alert("수정 성공");
	history.back();
	
<%}%>
</script>