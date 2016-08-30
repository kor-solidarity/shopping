<%@page import="shop.product.ProductBean"%>
<%@page import="shop.order.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="shop.order.OrderMgr"/>
<jsp:useBean id="productMgr" class="shop.product.ProductMgr"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>주문 목록</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
**주문 상품 목록 **<p/>
<%@include file="guest_top.jsp"%>
<table style="width:80%">
<tr><th>주문번호</th><th>상품명</th><th>주문수</th><th>주문일</th><th>주문상태</th></tr>
<%
String id=(String)session.getAttribute("idKey");
ArrayList<OrderBean> list = orderMgr.getOrder(id);
if(list.size()==0){
%>
<tr><td>주문항 상품이 없습니다</td></tr>

<%}else{
	for(OrderBean ord:list){
		ProductBean product=productMgr.getProduct(ord.getProduct_no());
	%>
	<tr style="text-align:center;">
		<td><%=ord.getNo() %></td>
		<td><%=product.getName()%></td>
		<td><%=ord.getQuantity() %></td>
		<td><%=ord.getSdate() %></td>
		<td>.</td>
	</tr>
	<%
	}
}
%>

</table>
<%@include file="guest_bottom.jsp"%>
</body>
</html>