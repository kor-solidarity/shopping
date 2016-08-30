<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="bean" class="shop.board.BoardBean"></jsp:useBean>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardmgr" class="shop.board.BoardMgr"></jsp:useBean>
<%
String spage=request.getParameter("page");
int num=bean.getNum();
int gnum=bean.getGnum();
int onum=bean.getOnum()+1;
int nested=bean.getNested()+1;

//같은 그룹 내에서 새로운 댓글의 onum 보다  크거나 같은 값을 댓글 입력전에  먼저 수정하기 작으면 수정하지않음
boardmgr.updateOnum(gnum,onum); //onum 갱신처리
//댓글 저장
bean.setOnum(onum); // onum값이 수정되나?
bean.setNested(nested);
bean.setBip(request.getRemoteAddr());
bean.setBdate();
bean.setNum(boardmgr.curruntGetNum()+1);
boardmgr.saveReply(bean);
response.sendRedirect("boardlist.jsp?page="+spage);
%>