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

//쇼핑몰 로그인 후 자신의 정보 수정시
function memberUpdate(){
	//입력자료 오류검사 (생략)
	document.updateForm.submit();
}
function memberUpdateCancel(){
	location.href="../guest/guest_index.jsp";
}
function memberDelete(){
	alert("회원탈퇴는 죽음을 부름!!!");
}


//관리자에서  회원 수정할때
function memUpdate(id){
	//alert(id);
	document.updateFrm.id.value=id;
	document.updateFrm.submit();
}
function memberUpdateAdmin(){
	document.updateFormAdmin.submit();
}
function memberUpdateCancelAdmin(){
	location.href="memberManager.jsp";
}
//관리자에서 상품 처리 시
function productDetail(no){
	//alert(no);
	document.detailFrm.no.value=no;
	document.detailFrm.submit();
}
function productUpdate(no){
	document.updateFrm.no.value=no;
	document.updateFrm.submit();
}
function productDelete(no){
	if(confirm("정말 삭제할까요?")){
		document.deleteFrm.no.value=no;
		document.deleteFrm.submit();
	}
	
}