<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="boardMgr" class="shop.board.BoardMgr"/>
<jsp:useBean id="bean" class="shop.board.BoardBean"/>
<jsp:setProperty property="*" name="bean"/>
<%

String spage=request.getParameter("page");
boolean b=boardMgr.checkPass(bean.getNum(),bean.getPass());
if(b){
	boardMgr.saveEdit(bean);
	response.sendRedirect("boardlist.jsp?page="+spage);
}else{
%>
	<script>
		alert("비밀번호 틀림");
		history.back();
	</script>
<%	
}
%>