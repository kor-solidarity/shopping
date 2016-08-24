<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="bean" class="shop.board.BoardBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="BoardMgr" class="shop.board.BoardMgr"/>

<%
int maxNum=BoardMgr.curruntGetNum()+1;
out.print(maxNum);
%>