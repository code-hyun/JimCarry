globalThis.uuids;
FileList.prototype.forEach = Array.prototype.forEach;
let prevFileList;

/* 저장할 파일들의 Array */


const $photoPicker = $("#photo-picker");
const uploadAjaxConfig = (data) => {
    return {
        url: "/users/mypage/files/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false
    }
}

$photoPicker.on("change", function () {
    let $files = $(this)[0].files;
    let formData = new FormData();

    if ($files.length > 8) {
        alert("등록할 수 있는 파일의 최대 갯수는 8개 입니다.");
        /* 파일 input 초기화 */
        $photoPicker[0].files = prevFileList;
        console.log($photoPicker[0].files);
        return;
    }

    prevFileList = $files;

    $files.forEach(file => formData.append("file", file));

    $doAjax(uploadAjaxConfig(formData), (result) => {
        console.log(result)
        globalThis.uuids = result.uuids;
        $thumbnailWrap.empty();
        result.paths.forEach((path, i) => {
            if ($files[i].type.startsWith("image")) {
                $thumbnailWrap.append(`<img class="imageThumbnail" src="/users/mypage/files/display?fileName=${result.paths[i]}">`);
            } else $thumbnailWrap.append(`<img style="width: 100px"/>`);
        });
    });
});

let $fileAjax = (contentId, table) => {
    const $files = $photoPicker[0].files;

    if ($files.length === 0) {
        return;
    }

    fileVOs = new Array();

    $files.forEach((file, i) => {
        let fileVO = new Object();
        fileVO.fileOrgName = file.name;
        fileVO.fileUuid = globalThis.uuids[i];
        fileVOs.push(fileVO);
    });

    let config = {
        url: `/users/mypage/files/save/${contentId}?table=${table}`,
        method: "POST",
        data: JSON.stringify(fileVOs),
        contentType: "application/json; charset=utf-8",
    }

}