package com.example.TestVer.Airport.AirService;

import com.example.TestVer.Airport.AirDao.AirportMapper;
import com.example.TestVer.Airport.AirVO.AirportVO;
import com.example.TestVer.Airport.getSchedule.Domestic;
import com.example.TestVer.Airport.getSchedule.International;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    @Autowired
    private AirportMapper mapper;

    private AirportVO vo;

    private International inter;

    public List<AirportVO> DomesticAirport(){
        List<AirportVO> KorList = mapper.DomesticAirport();
        return KorList;
    }

    public List<AirportVO> InternationalAirport(){
        List<AirportVO> InterList = mapper.InternationalAirport();
        return InterList;
    }

    public List<List<String>> getDomestic(String schDeptCityCode,String schArrvCityCode,String schDate) throws IOException, ParserConfigurationException, SAXException {
        Domestic domestic = new Domestic();
        List<List<String>> domesticLine = domestic.getDomestic(schDeptCityCode,schArrvCityCode,schDate);
        return domesticLine;
    }

    public List<List<String>> getInternational(String schDeptCityCode,String schArrvCityCode,String schDate) throws IOException, ParserConfigurationException, SAXException {
        International International = new International();
        List<List<String>> inter = International.getInternational(schDeptCityCode,schArrvCityCode,schDate);
        return inter;
    }
}
