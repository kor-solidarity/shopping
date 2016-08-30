<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <jsp:useBean id="cartmgr" class="shop.order.CartMgr" scope="session"/>
 <jsp:useBean id="orderMgr" class="shop.order.OrderMgr"/>
 <jsp:useBean id="productMgr" class="shop.product.ProductMgr"/>
<%
Hashtable hCart=cartmgr.getCartList();

%>