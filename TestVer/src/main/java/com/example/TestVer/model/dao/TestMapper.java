package com.example.TestVer.model.dao;

import com.example.TestVer.model.DTO.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<UserVO> selectAll();

    int joinUser(UserVO vo);

    String idCheck(String id);

    UserVO userInfo(String id);

    String getPw(String id);
}
