<%@ page contentType="text/html; charset=EUC-KR"%>
<html>
<head>
<title>�Խ���</title>
<link rel="stylesheet" type="text/css" href="../style/board.css">
<script>
function check(){
	if(frm.name.value==""){
		alert("�̸��� �Է��ϼ���");
		frm.name.focus();
	}else if(frm.pass.value ==""){
		alert("��й�ȣ�� �Է��ϼ���");
		frm.pass.focus();
	}else if(frm.mail.value ==""){
		alert("�̸����� �Է��ϼ���");
		frm.mail.focus();
	}else if(frm.title.value ==""){
		alert("������ �Է��ϼ���");
		frm.title.focus();
	}else if(frm.cont.value ==""){
		alert("������ �Է��ϼ���");
		frm.cont.focus();
	}else
		frm.submit();
}
</script>
</head>
<body topmargin="50" leftmargin="50">
<h2>*** �۾��� ***</h2>
<form name="frm" method="post" action="boardsave.jsp">
	<table border="1" width="530">
		<tr>
			<td align="center" width="100">�� ��</td>
			<td width="430"><input name="name" size="15"></td>
		</tr>
		<tr>
			<td align="center">�� ȣ</td>
			<td><input type="password" name="pass" size="15"></td>
		</tr>
		<tr>
			<td align="center">�� ��</td>
			<td><input name="mail" size="25"></td>
		</tr>
		<tr>
			<td align="center">�� ��</td>
			<td><input name="title" size="50"></td>
		</tr>
		<tr>
			<td align="center">�� ��</td>
			<td><textarea name="cont" cols="50" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="30">
			    <input type="button"
				 value="��  ��" onClick="location.href='../index.html'">&nbsp;
				<input type="button" value="��  ��" onClick="check()">&nbsp;
				<input type="button" value="��  ��"
				  onClick="location.href='boardlist.jsp'"></td>
		</tr>
	</table>
</form>
</body>
</html>