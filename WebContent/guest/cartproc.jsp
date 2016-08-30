<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartmgr" class="shop.order.CartMgr" scope="session"/>
<jsp:useBean id="order" class="shop.order.OrderBean"/>
<jsp:setProperty property="*" name="order"/>

<%
String flag=request.getParameter("flag"); //구매 목록 ,보기 ,수정 ,삭제 판단용
String id=(String)session.getAttribute("idKey");
if(id==null){
	response.sendRedirect("login.jsp");
}else{
	if(flag==null){
		order.setId(id);
		cartmgr.addCart(order); //cart에 주문 상품 담기
		%>
		alert("장바구니에 담았습니다");
		location.href="cartlist.jsp";
		<% 
	}else if(flag.equals("update")){
		
	}else if(flag.equals("delete")){
		
	}
}

%>
