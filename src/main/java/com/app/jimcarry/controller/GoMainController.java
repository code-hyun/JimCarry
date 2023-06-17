package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageFileService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GoMainController {
    private final StorageService storageService;
    private final ReviewService reviewService;
    private final StorageFileService storageFileService;

    /*창고최신순*/
    @GetMapping("")
    public String storageMain(Model model){
        /* storageService를 통해 모든 storage 정보를 가져온다.*/
        List<StorageDTO> storageDTOS = storageService.getStorageDTO();

        /* 가져온 storageDTOS 리스트를 순회하며, 해당 storageDTO에 해당하는 모든 파일 정보를 가져와 files 리스트에 저장한다.*/
        for(StorageDTO storageDTO: storageDTOS){
            storageDTO.setFiles(storageFileService.getByStorageId(storageDTO.getStorageId()));
        }

        /*모든 storage 정보를 가져오는데 사용되는 storageService를 통해 리뷰 정보를 가져온다.*/
        List<StorageDTO> reviewDTOs = storageService.getStorage();

        /*가져온 reviewDTOs 리스트를 순회하며, 해당 storageDTO에 해당하는 모든 파일 정보를 가져와 files 리스트에 저장한다.*/
        for(StorageDTO storageDTO: reviewDTOs){
            storageDTO.setFiles(storageFileService.getByStorageId(storageDTO.getStorageId()));
        }

        /* Model 객체에 가져온 storageDTOS 리스트와 reviewDTOs 리스트, 그리고 리뷰의 총 개수를 추가한다.*/
        model.addAttribute("storages", storageDTOS);
        model.addAttribute("countReviews", reviewDTOs);
        model.addAttribute("reviews", reviewService.getTotalById(1L));

        /* main/main.html 뷰 페이지를 반환한다.*/
        return "main/main";

    }

    /*창고 이미지 불러오기*/

    /* 해당 메서드는 GET 방식으로 "/display" 경로로 요청이 들어왔을 때 실행된다.
        @ResponseBody 어노테이션을 통해 반환되는 값이 HTTP 응답의 Body에 삽입된다.*/

    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {

        /* C:/upload 경로에 있는 fileName 파일을 읽어서 byte 배열 형태로 반환한다. */
        return FileCopyUtils.copyToByteArray(new File("/C:/upload", fileName));
    }
}
