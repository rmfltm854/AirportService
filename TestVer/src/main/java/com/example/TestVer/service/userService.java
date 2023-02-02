package com.example.TestVer.service;

import com.example.TestVer.model.DTO.UserVO;
import com.example.TestVer.model.dao.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class userService{
    @Autowired
    private TestMapper mapper;

    private UserVO vo;
    public List<UserVO> allUser(){
        List<UserVO> userList = mapper.selectAll();
        return userList;
    }

    public int joinUser(UserVO vo){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        vo.setPw(encoder.encode(vo.getPw()));
        int idx = mapper.joinUser(vo);
        return idx;
    }

    public int idCheck(String id){
         String resultID = mapper.idCheck(id);
         int result;
         if(resultID != null){
             result = 1;
         }else{
             result = 0;
         }
       return result;
    }

    public UserVO userInfo(String id){
        UserVO vo = mapper.userInfo(id);
        return vo;
    }

    public boolean getPw(String id,String pw){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String dbPW = mapper.getPw(id);
        boolean result = encoder.matches(pw,dbPW);
        return result;
    }


}
