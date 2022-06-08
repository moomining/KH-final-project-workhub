function slideToggle(t,e,o){0===t.clientHeight?j(t,e,o,!0):j(t,e,o)}function slideUp(t,e,o){j(t,e,o)}function slideDown(t,e,o){j(t,e,o,!0)}function j(t,e,o,i){void 0===e&&(e=400),void 0===i&&(i=!1),t.style.overflow="hidden",i&&(t.style.display="block");var p,l=window.getComputedStyle(t),n=parseFloat(l.getPropertyValue("height")),a=parseFloat(l.getPropertyValue("padding-top")),s=parseFloat(l.getPropertyValue("padding-bottom")),r=parseFloat(l.getPropertyValue("margin-top")),d=parseFloat(l.getPropertyValue("margin-bottom")),g=n/e,y=a/e,m=s/e,u=r/e,h=d/e;window.requestAnimationFrame(function l(x){void 0===p&&(p=x);var f=x-p;i?(t.style.height=g*f+"px",t.style.paddingTop=y*f+"px",t.style.paddingBottom=m*f+"px",t.style.marginTop=u*f+"px",t.style.marginBottom=h*f+"px"):(t.style.height=n-g*f+"px",t.style.paddingTop=a-y*f+"px",t.style.paddingBottom=s-m*f+"px",t.style.marginTop=r-u*f+"px",t.style.marginBottom=d-h*f+"px"),f>=e?(t.style.height="",t.style.paddingTop="",t.style.paddingBottom="",t.style.marginTop="",t.style.marginBottom="",t.style.overflow="",i||(t.style.display="none"),"function"==typeof o&&o()):window.requestAnimationFrame(l)})}

let sidebarItems = document.querySelectorAll('.sidebar-item.has-sub');
for(var i = 0; i < sidebarItems.length; i++) {
    let sidebarItem = sidebarItems[i];
	sidebarItems[i].querySelector('.sidebar-link').addEventListener('click', function(e) {
        e.preventDefault();
        
        let submenu = sidebarItem.querySelector('.submenu');
        if( submenu.classList.contains('active') ) submenu.style.display = "block"

        if( submenu.style.display == "none" ) submenu.classList.add('active')
        else submenu.classList.remove('active')
        slideToggle(submenu, 300)
    })
}

window.addEventListener('DOMContentLoaded', (event) => {
    var w = window.innerWidth;
    if(w < 1200) {
        document.getElementById('sidebar').classList.remove('active');
    }
});
window.addEventListener('resize', (event) => {
    var w = window.innerWidth;
    if(w < 1200) {
        document.getElementById('sidebar').classList.remove('active');
    }else{
        document.getElementById('sidebar').classList.add('active');
    }
});

document.querySelector('.burger-btn').addEventListener('click', () => {
    document.getElementById('sidebar').classList.toggle('active');
})
document.querySelector('.sidebar-hide').addEventListener('click', () => {
    document.getElementById('sidebar').classList.toggle('active');

})


// Perfect Scrollbar Init
if(typeof PerfectScrollbar == 'function') {
    const container = document.querySelector(".sidebar-wrapper");
    const ps = new PerfectScrollbar(container, {
        wheelPropagation: false
    });
}

// Scroll into active sidebar
document.querySelector('.sidebar-item.active').scrollIntoView(false)

//체크박스 전체 선택
function selectAll(selectAll)  {
    const checkboxes 
       = document.querySelectorAll('input[type="checkbox"]');
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = selectAll.checked
    })
  }


//주소록 값 가져오기
$("#selectBtn").click(function(){
    var rowData = new Array();
    var tdArr = new Array();
    var checkbox = $("input[name=userCheckbox]:checked");
	var memberNos = '';
	var memberIds = '';
    checkbox.each(function(i, obj) {
		memberNos += obj.value + ',';
        var tr = checkbox.parent().parent().eq(i);
        var td = tr.children();

        rowData.push(tr.text());
        
        memberIds += td.eq(1).text() + ',';

        var name = td.eq(2).text();
        var position = td.eq(3).text();
        var dept = td.eq(4).text();
        var email = " <"+td.eq(5).text()+"> ";

        tdArr.push(name);
        tdArr.push(position);
        tdArr.push(dept);
        tdArr.push(email);
       	

    });
    memberNos = memberNos.substring(0,memberNos.length-1);
    memberIds = memberIds.substring(0,memberIds.length-1);
    $('#memberNos').val(memberNos);
    $('#memberIds').val(memberIds);
    $('#contactResult').val(tdArr);
    

})

//받은 쪽지 확인
var SENDERNAME = '';
var TITLE = '';
var CONTENT = '';
var RECEIVEDTIME = '';
$('#readMessage').on('show.bs.modal', function (event) {
    SENDERNAME = $(event.relatedTarget).data('sender-name');
    var modal = $(this)
    modal.find('#sender-name').text(SENDERNAME); // 모달위도우에서 .modal-title을 찾아 titleTxt 값을 치환
})

