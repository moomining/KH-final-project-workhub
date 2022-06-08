

/* 결재자 설정 여러명 체크 */
//상단 선택 버튼 클릭시 체크된 Row의 값을 가져온다.
var receiverCount = 0;
$("#selectBtn").click(function(){

    var rowData = new Array();
    var tdArr = new Array();
    var checkbox = $("input[name=user_CheckBox]:checked");

    //체크된 체크박스 값을 가져온다
    checkbox.each(function(i){

        //checkbox.parent() : checkbox의 부모는 <td>
        //checkbox.parent().parent() : <td>의 부모는 <tr>
        var tr = checkbox.parent().parent().eq(i);
        var td = tr.children();

        //체크된 row의 모든 값을 배열에 담는다.
        rowData.push(tr.text());

        //td.eq(0)은 체크박스 이므로 td.eq(1)의 값부터 가져온다.
        // var dep = td.eq(1).text();
        // var rank = td.eq(2).text();
        var no = td.eq(0).children().val();
        var name = td.eq(3).text();
        
/*        console.log(no);
        console.log(name);*/

        //가져온 값을 배열에 담는다.
        // tdArr.push(dep);
        // tdArr.push(rank);
        tdArr.push(no);
        tdArr.push(name);

        let str = "";
        
/*        console.log(tdArr);*/

        // tdArr에 있는 값 하나씩 <button> 안에 넣어주기
        for(let i = 0; i < tdArr.length; i++) {
        // ``로 감싸야 안에 ""도 살아 있고 ${}도 쓸 수 있음
        str += `<button type="button" class="list-group-item list-group-item-action" no="${tdArr[i++]}">${tdArr[i]}</button>`;
    }
 
    // html에 넣어주기
     $("#result").html(str);

/*         console.log("name : " + name);*/

        // $("#result").html(tdArr[i]);
    });
});



//결재자 사원번호 input type=hidden 값으로 불러오기
$("#selectBtn").click(function(){

    var tdArr = new Array();
    var checkbox = $("input[name=user_CheckBox]:checked");

    //체크된 체크박스 값을 가져온다
    checkbox.each(function(i){

        var tr = checkbox.parent().parent().eq(i);
        var td = tr.children();

        var no = td.eq(0).children().val();
        var name = td.eq(3).text();

        tdArr.push(no);

        let str = "";
        
        console.log(tdArr);

        // tdArr에 있는 값 하나씩 <input> 안에 넣어주기
        for(let i = 0; i < tdArr.length; i++) {	
        //str += `<input type="hidden" name="receiver[${i}].no" value="${tdArr[i]}"/>`;
        str += `<input type="hidden" name="receiver" value="${tdArr[i]}"/>`;
    }
    
    receiverCount = tdArr.length;
    console.log("결재자 수 : " + receiverCount);
 
    // html에 넣어주기
     $("#appList").html(str);

        // $("#result").html(tdArr[i]);
    });
});



//참조자 선택
$("#selectBtn2").click(function(){

    var rowData2 = new Array();
    var tdArr2 = new Array();
    var checkbox2 = $("input[name=user_CheckBox2]:checked");

    //체크된 체크박스 값을 가져온다
    checkbox2.each(function(i){

        //checkbox.parent() : checkbox의 부모는 <td>
        //checkbox.parent().parent() : <td>의 부모는 <tr>
        var tr2 = checkbox2.parent().parent().eq(i);
        var td2 = tr2.children();

        //체크된 row의 모든 값을 배열에 담는다.
        rowData2.push(tr2.text());

        //td.eq(0)은 체크박스 이므로 td.eq(1)의 값부터 가져온다.
        // var dep = td.eq(1).text();
        // var rank = td.eq(2).text();
        var no2 = td2.eq(0).children().val();
        var name2 = td2.eq(3).text();

        //가져온 값을 배열에 담는다.
        // tdArr.push(dep);
        // tdArr.push(rank);
        tdArr2.push(no2);
        tdArr2.push(name2);


        let str2 = "";

        // tdArr에 있는 값 하나씩 <button> 안에 넣어주기
        for(let i = 0; i < tdArr2.length; i++) {
        // ``로 감싸야 안에 ""도 살아 있고 ${}도 쓸 수 있음
        str2 += `<button type="button" class="list-group-item list-group-item-action" no="${tdArr2[i++]}">${tdArr2[i]}</button>`;
        refCount++;
    }
 
    // html에 넣어주기
     $("#result2").html(str2);

        // $("#result").html(tdArr[i]);
    });
});

var refCount = 0;

//참조자 사원번호 input type=hidden 값으로 불러오기
$("#selectBtn2").click(function(){

    var tdArr2 = new Array();
    var checkbox2 = $("input[name=user_CheckBox2]:checked");

    //체크된 체크박스 값을 가져온다
    checkbox2.each(function(i){

        var tr2 = checkbox2.parent().parent().eq(i);
        var td2 = tr2.children();

        var no2 = td2.eq(0).children().val();

	
        tdArr2.push(no2);

        let str2 = "";
        
/*        console.log(tdArr2);*/

        // tdArr에 있는 값 하나씩 <input> 안에 넣어주기
        for(let i = 0; i < tdArr2.length; i++) {	
        //str2 += `<input type="hidden" name="ref[${i}].no" value="${tdArr2[i]}"/>`;
        str2 += `<input type="hidden" name="ref" value="${tdArr2[i]}"/>`;
    }
 
    // html에 넣어주기
     $("#refList").html(str2);

    });
});

function valUpdate(){
	var recCount = document.getElementById("receiverCount");
	recCount.value = receiverCount;
	console.log(recCount.value);
	
	var refCount = document.getElementById("refCount");
	refCount.value = refCount;
	console.log(refCount.value);
}

//결재자 순번 위아래 버튼
function up(){
    let result = document.getElementById("result");
    result.lastElementChild.after(result.firstElementChild);
}
function down(){
    let result = document.getElementById("result");
    result.firstElementChild.before(result.lastElementChild);
}

