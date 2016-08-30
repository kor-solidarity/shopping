<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8"/>

<title>관리자</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
**관리자 화면**<p/>
<%@ include file="admin_top.jsp" %>
<table style="width:80%">
	<tr>
	 <td style="background-image: url(../image/adminpic.gif); background-repeat: no-repeat; background-position: center;">
	 <br><br><br>
	 <h2 style="color:white;text-align:center;">관리자페이지</h2>
	 <br><br><br>
	 </td>
	</tr>
</table>
<%@include file="admin_bottom.jsp" %>
</body>
</html>