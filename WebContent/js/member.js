/**
 * 
 */
function go_save() {
	var id = document.frm.id.value;
	var reid= document.frm.reid.value;
	var pw = document.frm.pwd.value;
	var pw2 =  document.frm.pwdChk.value;
	var name = document.frm.name.value;
	var em = document.frm.email.value;
	var zip= document.frm.zipNum.value;
	var add1= document.frm.addr1.value;
	var add2= document.frm.addr2.value;
	var tel = document.frm.phone.value;
	var reg =  /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;     //ex)010-0000-0000
	var rege = /^\w{5,12}@[a-z]{2,10}[\.][a-z]{2,3}[\.]?[a-z]{0,2}$/;   //email 아래보시면 타입자체를 메일로 설정하면 그걸로도 체크가 되지만 가끔씩 안할때가 많음.

	if (id=="") {
		alert("IDを入力してください");
		document.frm.id.focus();
	} else if (pw=="") {
		alert("パスワードを入力してください");
		document.frm.pwd.focus();
	} else if (pw != pw2) {
		alert("パスワードが違います");
		document.frm.pwd.focus();
	} else if (name=="") {
		alert("名前を入力してください");
		document.frm.name.focus();
	} else if (em=="") {
		alert("Eメールアドレスを入力してください");
		document.frm.email.focus();
	} else if (!rege.test(em)) {
		alert("正しい形式のEメールアドレスを入力してください");
		document.frm.email.focus();
	} else if (zip=="") {
		alert("住所検索をクリックしてください");
		document.frm.zipNum.focus();
	} else if (add2=="") {
		alert("詳細住所を入力してください");
		document.frm.addr2.focus();
	} else if (tel=="") {
		alert("電話番号を入力してください");
		document.frm.phone.focus();
	} else if (!reg.test(tel)) {
		alert("正しい形式の電話番号を入力してください");
		document.frm.phone.focus();
	} else {
		document.frm.action = "join.hanul";
		document.frm.submit();
	}
}

function go_next() {
	if (document.frm.okon1[0].checked == true) {
		document.frm.action = "join_form.hanul";
		document.frm.submit();
	} else if (document.frm.okon1[1].checked == true) {
		alert("規約に同意してください");
	}
}


function join() {
	document.frm.action= "contract.hanul";
	document.frm.submit();
}



function idcheck() {
	if (document.frm.id.value == "") {
		alert("IDを入力してください");
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

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
            
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipNum').value = data.zonecode;
            document.getElementById("addr1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2").focus();
        }
    }).open();
}


