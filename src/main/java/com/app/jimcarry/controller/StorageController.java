package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.StorageVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageFileService;
import com.app.jimcarry.service.StorageService;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.Multipart;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/storages/*")
@RequiredArgsConstructor
@Slf4j
public class StorageController {

    private final StorageService storageService;
    private final UserService userService;
    private final StorageFileService storageFileService;
    private final ReviewService reviewService;

    /*로그인 해야 창고등록 이용가능 클릭하면 자동으로 로그인 페이지로 이동함*/
    @GetMapping("register")
    public String register(Model model, HttpSession httpSession) {
        model.addAttribute("userId", userService.getUser(((UserVO)httpSession.getAttribute("user")).getUserId()));
        return "storageRegister/storageRegister"; }


    /*창고등록*/
    @PostMapping("register")
    @ResponseBody
    public String storageSave(@RequestBody StorageDTO storageDTO, HttpSession httpSession) {
        storageDTO.setUserId(((UserVO)httpSession.getAttribute("user")).getUserId());
        // 자바 스크립트 파일 리스트
        log.info(String.valueOf(storageDTO.getStorageAddressNumber()));
        log.info(String.valueOf(storageDTO.getStorageSize()));
        storageService.registerStorage(storageDTO);


        /*창고등록 후 이동할 페이지*/
        return "/main/";
    }

    /*헤더 지역별 창고 조회 */
    @GetMapping("list/{storageAddressNumber}")
    public String showList(@PathVariable("storageAddressNumber") Integer storageAddressNumber, Model model, Criteria criteria){
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 6;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("storageAddressNumber")));
        searchDTO.setStorageAddressNumber(storageAddressNumber);

        //         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = storageService.getTotalBy(searchDTO);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);

        List<StorageDTO> storageList = storageService.getStorageDTOBy(pageDTO);
        for(StorageDTO storageDTO: storageList){
            storageDTO.setFiles(storageFileService.getByStorageId(storageDTO.getStorageId()));
        }

        model.addAttribute("storageAddressNumber", storageAddressNumber);
        model.addAttribute("total", total);
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("storage", storageList);

        return "main/search-page";
    }

}
