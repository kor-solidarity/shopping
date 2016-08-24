<%@page import="shop.board.BoardDto"%>
<%@page import="shop.board.BoardMgr"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int spage=1;%>
<jsp:useBean id="boardMgr" class="shop.board.BoardMgr"/>
<jsp:useBean id="dto" class="shop.board.BoardDto"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
board

<script type="text/javascript">

</script>
</head>
<body>
<table>
<tr>
<td>
	[<a href="../index.jsp">메인으로</a>]&nbsp;
	[<a href="boardlist.jsp?page=1">최신목록</a>]&nbsp;
	[<a href="boardwrite.jsp">새글작성</a>]&nbsp;
	[<a href="#" onclick="window.open('admin.jsp','','width=300,height=150,top=200,left=300')">관리자용</a>]&nbsp;
</td>
</tr>


<%request.setCharacterEncoding("utf-8");

try{
	spage=Integer.parseInt(request.getParameter("page"));
	
}catch(Exception e){
	spage=1;
}
if(spage<=0) spage=1;
ArrayList<BoardDto> list=boardMgr.getDataAll();
for(int i=0;i<list.size();i++){
	dto=(BoardDto)list.get(i);
%>
	<tr>
		<td><%=dto.getNum() %></td>
		<td>
		<a href="boardcontent.jsp?num=<%=dto.getNum()%>&page=<%=spage%>"><%=dto.getTitle() %></a>
		</td>
		<td><%=dto.getName() %></td>
		<td><%=dto.getBdate() %></td>
		<td><%=dto.getReadcnt() %></td>


	</tr>
<%
}
%>
</table>
</body>
</html>