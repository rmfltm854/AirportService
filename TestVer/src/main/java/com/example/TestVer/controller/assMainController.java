package com.example.TestVer.controller;


import com.example.TestVer.Airport.AirService.AirportService;
import com.example.TestVer.Airport.AirVO.AirportVO;
import com.example.TestVer.model.DTO.UserVO;
import com.example.TestVer.service.EmailSenderService;
import com.example.TestVer.service.userService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class assMainController {
    @Autowired
    userService service;

    @Autowired
    AirportService airService;

    @Autowired
    private EmailSenderService mailservice;

    @RequestMapping("/")
    public String Main(){
        return "Main";
    }

    @RequestMapping("/Test")
    public String test(Model model){
        model.addAttribute("Test",service.allUser());
        List<UserVO> list =  service.allUser();
        System.out.println(list);
        return "index";
    }

    @GetMapping ("/Join")
    public String join(){return "Join";
    }

    @PostMapping("/Join")
    public String joinProc(UserVO vo){
        System.out.println(vo.getId());
        vo.setRole("USER");
        System.out.println(vo.getRole());
        int result = service.joinUser(vo);
        if(result > 0){
            return "Domestic";
        }else{
            return "fail";
        }
    }
    @RequestMapping("/JoinSuccess")
    public String JoinSuccess(){
        return "Main";
    }


    @RequestMapping("/mail")
    @ResponseBody
    public String mailCheck(String mail,Model model){
        System.out.println(mail);
        int[] ranNum = mailservice.makeRandomNumber();
        String str = Arrays.toString(ranNum).replaceAll("[^0-9]","");
        mailservice.sendEmail(mail,"ASS회원가입인증번호입니다.",str);
        return str;
    }

    @RequestMapping("/idCheck")
    @ResponseBody
    public int idCheck(String id){
        System.out.println(id);
        int result = service.idCheck(id);
        System.out.println(result);
        return result;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login&out";
    }

    @GetMapping("/login&logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "Main";
    }

    @PostMapping("/mainIndex")
    public String loginForm(String id,String pw,Model model,HttpSession session){
        boolean result = service.getPw(id,pw);
        if(result == true){
            UserVO vo = service.userInfo(id);
            System.out.println(vo.getName());
            session.setAttribute("USERNAME",vo.getName());
            return "Main";
        }else{
            return "Failure";
        }
    }

    @GetMapping ("/Domestic")
    public String Domestic(Model model){
        List<AirportVO> KorList = airService.DomesticAirport();
        model.addAttribute("Domestic",KorList);
        return "Domestic";
    }
    @GetMapping("/Domestic/domesticLine")
    public String Domestic(String goCode, String arriveCode, String goDate,Model model) throws IOException, ParserConfigurationException, SAXException {
        System.out.println(goCode);
        System.out.println(arriveCode);
        System.out.println(goDate);
        goDate = goDate.replaceAll("[^0-9]", "");
        System.out.println(goDate);
        List<List<String>> domesticList = airService.getDomestic(goCode,arriveCode,goDate);
        model.addAttribute("domestic",domesticList);
        return "Test";
    }

    @GetMapping ("/International")
    public String International(Model model){
        List<AirportVO> KorList = airService.DomesticAirport();
        model.addAttribute("Domestic",KorList);
        List<AirportVO> InterList = airService.InternationalAirport();
        model.addAttribute("International",InterList);
        return "International";
    }

    @GetMapping ("/International/internationalLine")
    public String InternationalTest(String goCode, String arriveCode, String goDate,Model model) throws IOException, ParserConfigurationException, SAXException{
        goDate = goDate.replaceAll("[^0-9]", "");
        System.out.println(goDate);
        List<List<String>> interList = airService.getInternational(goCode,arriveCode,goDate);
        model.addAttribute("interList",interList);
        return "Test2";
    }




    @RequestMapping("/InternationalLine")
    @ResponseBody
    public int InternationalLine(String goCode, String arriveCode, String goDate,String arriveDate){
        System.out.println(goCode);
        System.out.println(arriveCode);
        System.out.println(goDate);
        System.out.println(arriveDate);
        return 1;
    }
    @GetMapping("/Test")
    public String test(){
        return "Test";
    }




}
