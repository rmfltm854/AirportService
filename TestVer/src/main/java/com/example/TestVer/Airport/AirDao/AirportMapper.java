package com.example.TestVer.Airport.AirDao;

import com.example.TestVer.Airport.AirVO.AirportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirportMapper {
    List<AirportVO> DomesticAirport();

    List<AirportVO> InternationalAirport();
}
