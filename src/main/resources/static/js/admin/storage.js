/*창고 목록 불러오기*/
const storageTableContainer = $(".table");
const createDOM = function(box){
    let text =
        `
        <tr class="table__content">
        <td>
        <label class="check-label">
            <input type="checkbox" name="check" />
            </label>
            </td>
            <td class="content__id">${box.storageId}</td>
            <td>${box.userIdentification}</td>
            <td>${box.userEmail}</td>
            <td>${box.storageAddress} ${box.storageAddressDetail}</td>
            <td>${box.storageUseDate} ~ ${box.storageEndDate}</td>
            <td>${box.storageSize}</td>
            <td> 
            <button class="content__detail__btn button__type_2 button__color__green"> 상세보기 </button> 
        </td>
        </tr>
        `
    return text;
}

$('#Glyph').on('click', function() {
    const conditiontest = $('.listbox-selecter').text();

    var condition;
    if (conditiontest == '창고위치') {
        condition = "storageAddress";
    } else if (conditiontest == '창고크기') {
        condition = "storageSize";
    } else if( conditiontest == '검색조건 선택'){
        alert("검색 조건을 선택해주세요.");
        return;
    }
    $("input[name='condition']").val(condition);

    document.searchForm.submit();
});

boxes.forEach((box, i) => {
    console.log("들어옴");
    storageTableContainer.append(
        createDOM(box)
    );
});

//
$(".modal-stage").append(
    `
     <section class="modal">
      <div class="modal__header" >
        <h3 class="modal__title">창고 상세보기</h3>
        <a class="modal-close">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            data-name="Capa 1"
            id="Capa_1"
            viewBox="0 0 20 19.84">
            <path
              d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z" />
          </svg>
        </a>
      </div>
      <form class="storage_form" action="">
        <main class="modal__main">
          <div class="modal__content">
            <div class="content__title">
              <h4>창고 상세보기</h4>
            </div>
            <div class="content__intput input_box_shadow">
              <input type="text" name="file" value="경기 의왕시 경수대로 262" readonly="true" />
            </div>
            <div class="content__main">
              <div class="content__img__wrap">
                <!-- 임시로 name='file' 해둠 -->
                <label class="attach">
                  <div class="content__img">
                    <img
                      src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" />
                  </div>
                  <input type="file" name="file" style="display: none;"/>
                </label>
                <label class="attach">
                  <div class="content__img">
                    <img
                      src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" />
                  </div>
                  <input type="file" name="file" style="display: none;"/>
                </label>
                <label class="attach">
                  <div class="content__img">
                    <img
                      src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" />
                  </div>
                  <input type="file" name="file" style="display: none;"/>
                </label>
                <label class="attach">
                  <div class="content__img">
                    <img
                      src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" />
                  </div>
                  <input type="file" name="file" style="display: none;"/>
                </label>
                <label class="attach">
                  <div class="content__img">
                    <img
                      src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" />
                  </div>
                  <input type="file" name="file" style="display: none;"/>
                </label>
                <label class="attach">
                  <div class="content__img">
                    <img
                      src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" />
                  </div>
                  <input type="file" name="file" style="display: none;"/>
                </label>
              </div>
              <ul >
                <li class="content__list">
                  <span>항목1</span>
                  <div  class="content__intput input_box_shadow">
                    <input type="text">
                  </div>
                </li>
                <li class="content__list">
                  <span>항목2</span>
                  <div  class="content__intput input_box_shadow">
                    <input type="text">
                  </div>
                </li>
                <li class="content__list">
                  <span>항목3</span>
                  <div  class="content__intput input_box_shadow">
                    <input type="text">
                  </div>
                </li>
                <li class="content__list">
                  <span>항목4</span>
                  <div  class="content__intput input_box_shadow">
                    <input type="text">
                  </div>
                </li>
                <li class="content__list">
                  <span>항목5</span>
                  <div  class="content__intput input_box_shadow">
                    <input type="text">
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div class="user__profile__button">
            <button id="completeBtn" class="button__type_2 button__color__green">작성완료</button>
          </div>
        </main>
      </form>
    </section>
    `
);

$(".content__detail__btn").on('click', function () {
    console.log("click");
    const i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    const contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});

/* 상세보기 모달 내용 submit 이벤트 */
$('#completeBtn').on('click', function (e) {
    e.preventDefault();
    return new Promise(
        function () {
            console.log('으으아');
            $modalStage.fadeOut(500);
        },
        () => {
            $('.storage_form').submit();
        }
    );
});

/* 체크박스 */
const $checkAll = $('#checkAll');
const $check = $("input[name='check']");
let $checkArr = [];
$('input[name=check]').on('click', function() {
    if ($(this).is(':checked')) {
        $checkArr.push($(this).parent().parent().next().text());
    } else {
        var toRemove = $(this).parent().parent().next().text();
        $checkArr = $checkArr.filter(function (item) {
            return item !== toRemove;
        });
    }
    console.log($checkArr);
});

/* 체크박스 이벤트 ======================================= */
$checkAll.click(function () {
    if ($checkAll.is(':checked')) {
        $check.prop('checked', true);
        $check.each((i,e)=> {
            $checkArr.push($check.eq(i).parent().parent().next().text());
        });
    }
    else {
        $check.prop('checked', false);
        $check.each((i, e) => {
            var toRemove = $check.eq(i).parent().parent().next().text();
            $checkArr = $checkArr.filter(function (item) {
                return item !== toRemove;
            });
        });
    }
    console.log($checkArr);
});

$check.click(function () {
    var total = $check.length;
    var checked = $('input[name=check]:checked').length;
    if (total != checked) {
        $checkAll.prop('checked', false);
    }
    else {
        $checkAll.prop('checked', true);
    }
});

confirmButton.on('click', function () {
    // 삭제를 실행하는 코드 작성
    $.ajax({
        url: "/admin/storage/delete",
        type: "post",
        data: { storageIds : $checkArr },
        traditional : true,
        success : function(result){
            if(result) {
                location.reload();
            }
        }
    });
});
