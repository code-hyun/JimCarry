package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.PaymentVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.PaymentService;
import com.app.jimcarry.service.StorageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay/*")
@RequiredArgsConstructor
@Slf4j
public class PayController {
    private final PaymentService paymentService;
    private final StorageFileService storageFileService;

    /*결제페이지*/
    @PostMapping("/payment")
    public String pay(String storageTitle, String paymentMonth, String paymentAmount, Long storageId, Model model, HttpSession httpSession) {

        /*HttpSession에서 현재 로그인한 사용자 정보를 가져와서, 해당 사용자의 정보를 Model 객체에 추가한다.*/
        model.addAttribute("user", paymentService.getUser(((UserVO)(httpSession.getAttribute("user"))).getUserId()));

        /*Model 객체에 결제에 필요한 정보를 추가한다.*/
        model.addAttribute("storageTitle",storageTitle);
        model.addAttribute("storageId",storageId);
        model.addAttribute("paymentMonth", paymentMonth);
        model.addAttribute("paymentAmount", paymentAmount);
        model.addAttribute("file", storageFileService.getByStorageId(storageId));

        /*pay/payment.html 뷰 페이지를 반환한다.*/
        return "pay/payment";
    }

    @GetMapping("/pay-reserve")
    public String payReserve(String paymentAmount, Model model, HttpSession httpSession){

        /*HttpSession에서 현재 로그인한 사용자 정보를 가져와서, 해당 사용자의 정보를 Model 객체에 추가한다.*/
        model.addAttribute("userVO", (UserVO)httpSession.getAttribute("user"));

        /*Model 객체에 결제에 필요한 정보를 추가한다.*/
        model.addAttribute("paymentAmount", paymentAmount);

        /*pay/pay-reserve.html 뷰 페이지를 반환한다.*/
        return "pay/pay-reserve";
    }

    /* 해당 메서드는 POST 방식으로 "/payRegister" 경로로 요청이 들어왔을 때 실행된다.
        @ResponseBody 어노테이션을 통해 반환되는 값이 HTTP 응답의 Body에 삽입된다.*/
    @PostMapping("/payRegister")
    @ResponseBody
    public String payRegister(String paymentAmount, String storageId, HttpSession httpSession) {
        /*결제 정보를 담을 PaymentVO 객체를 생성하고, 해당 객체에 결제 정보를 설정한다.*/
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setPaymentAmount(Integer.parseInt(paymentAmount));
        paymentVO.setUserId(((UserVO)httpSession.getAttribute("user")).getUserId());
        paymentVO.setStorageId(Long.valueOf(storageId));

        /*결제 정보를 등록한다.*/
        paymentService.register(paymentVO);

        /*"/pay/pay-reserve?paymentAmount=" + paymentAmount 경로로 리다이렉트한다.*/
        return "pay/pay-reserve?paymentAmount=" + paymentAmount;
    }

}
