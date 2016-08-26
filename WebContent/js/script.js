function zipCheck(){
	url="zipcheck.jsp?check=y";
	window.open(url,"zip","toolbar=no,width=350,heigth=300,top=200,left=300,status=yes,scrollbars=yes,menubar=no");
}
function idCheck(){
	if(regForm.id.value=""){
		alert("id를 입력하시오");
		regForm.id.focus();
	}else{
		url="idcheck.jsp?id="+regForm.id.value;
		window.open(url,"id","width=300,height=150,top=150,left=150");
	}
}
function inputCheck(){
	//입력자료 오류 검사

	document.regForm.submit();
}
