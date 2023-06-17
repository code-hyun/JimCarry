package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.*;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.service.ReviewFileService;
import com.app.jimcarry.service.ReviewService;
import com.app.jimcarry.service.StorageFileService;
import com.app.jimcarry.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("storages/search/*")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final ReviewService reviewService;
    private final StorageService storageService;
    private final StorageFileService storageFileService;
    private final ReviewFileService reviewFileService;

    /*지역별 창고 목록 검색*/
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> searchByAddress(@RequestBody Map<String, Object> requestData /*Integer storageAddressNumber*/, Criteria criteria){
        Integer storageAddressNumber = (Integer) requestData.get("storageAddressNumber"); // 지역
        Integer page = (Integer) requestData.get("page"); // page
        String keyword = (String) requestData.get("keyword"); // keyword
        List<String> size = (List<String>) requestData.get("size"); // sizes
        String order = (String) requestData.get("order"); // order

        log.info("================================");
        log.info("page" + page);
        log.info("================================");
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 6;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /*창고주소번호 */
        List<String> types = new ArrayList<>();

        types.add("storageAddress");
        types.add("storageAddressNumber");
        SearchDTO searchDTO = new SearchDTO().createTypes(types);
        searchDTO.setStorageAddress(keyword);
        searchDTO.setStorageAddressNumber(storageAddressNumber);
        searchDTO.setSizes(size);
        searchDTO.setOrder(order);


//        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("storageAddressNumber")));
//        searchDTO.setStorageAddressNumber(storageAddressNumber);

        PaginationDTO paginationDTO = new PaginationDTO();

        //         페이지 번호가 없을 때, 디폴트 1페이지
        if(page == 0) {
            page = 1;
        }
        criteria.create(page, amount);
//        if (criteria.getPage() == 0) {
//            criteria.create(1, amount);
//        } else {
//            criteria.create(page, amount);
//        }
        log.info("================================");
        log.info(criteria.toString());
        log.info("================================");
        total = storageService.getTotalBy(searchDTO);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        List<StorageDTO> storageList = storageService.getStorageDTOBy(pageDTO);
        for(StorageDTO storageDTO: storageList){
            storageDTO.setFiles(storageFileService.getByStorageId(storageDTO.getStorageId()));
        }

        paginationDTO.setPageDTO(pageDTO);
        paginationDTO.setStorageDTO(storageList);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("paginationDTO", paginationDTO);

        return resultMap;
    }

    /* 창고 상세페이지 조회*/
    @GetMapping("detail/{storageId}")
    public String searchDetail(@PathVariable("storageId") Long storageId, Model model, Criteria criteria){
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 3;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("storageId")));
        searchDTO.setStorageId(storageId);

        //         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = reviewService.getTotalBy(searchDTO);
        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        List<ReviewDTO> reviewDTOList = reviewService.getListBy(pageDTO);
        log.info("----------------------------------------------" + reviewDTOList);
        insertFileVOS(reviewDTOList);
        model.addAttribute("total", total);
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("storages", storageService.getStorageBy(storageId).get(0));
        model.addAttribute("file", storageFileService.getByStorageId(storageId));


        model.addAttribute("pagination", pageDTO);

        model.addAttribute("reviews", reviewDTOList);
//        model.addAttribute("reviewFile", reviewFileService.getListByStorageId(storageId));


        return "detail-info/detail-info";
    }
    
    /* 창고 검색 */
    @PostMapping("keyword")
    public RedirectView registerProduct(String storageAddress, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("storageAddress", storageAddress);
        return new RedirectView("/storages/search/keyword");
    }


    /* 창고 검색 목록*/
    @GetMapping("keyword")
    public String searchByKeyword(String storageAddress, Criteria criteria, Model model) {
         /*한 페이지에 보여줄 게시글 개수 */
        int amount = 6;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("storageAddress")));
        searchDTO.setStorageAddress(storageAddress);

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

        model.addAttribute("total", total);
        model.addAttribute("storage", storageList);
        model.addAttribute("pagination", pageDTO);

        return "main/search-page";
    }

    /*창고 이미지 불러오기*/
    @GetMapping("files/display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("/C:/upload", fileName));
    }

    /*dto set*/
    private List<ReviewDTO> insertFileVOS(List<ReviewDTO> dtos){
        return dtos.stream().map(dto -> {
            dto.setFileVOS(reviewFileService.getListByReviewId(dto.getReviewId()));
            return dto;
        }).collect(Collectors.toList());
    }

    private List<StorageDTO> insertFiles(List<StorageDTO> storageDTOS){
        return storageDTOS.stream().map(dto -> {
            dto.setFiles(storageFileService.getByStorageId(dto.getStorageId()));
            return dto;
        }).collect(Collectors.toList());
    }
}
