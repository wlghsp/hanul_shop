/**
 * 
 */
function go_next() {
	if (document.frm.okon1[0].checked == true) {
		document.frm.action = "join_form.hanul";
		document.frm.submit();
	} else if (document.frm.okon1[1].checked == true) {
		alert("약관에 동의하셔야 합니다.");
	}
}

function idcheck() {
	if (document.frm.id.value == "") {
		alert("아이디를 입력해 주세요");
		document.frm.id.focus();
		return;
	}
	
	var url = "id_check_form.hanul?id=" + document.frm.id.value;
	
	var w = 400;
	var h = 200;
	
	var LeftPosition = (screen.width-w)/2;
	var TopPosition = (screen.height-h)/2;
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=" + w + ", height=" + h + ", top=" + TopPosition + ", left=" + LeftPosition);
}

function post_zip() {
	var url = "find_zip_num.hanul";
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=300, top=300, left=300");

}










