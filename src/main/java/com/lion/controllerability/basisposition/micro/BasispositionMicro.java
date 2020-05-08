package com.lion.controllerability.basisposition.micro;

import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.data.BasispositionExample;
import com.lion.controllerability.basisposition.mapper.BasispositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wang.hongyu
 * @Version V1.02020/04/27
 **/
@ApiIgnore
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/basispositionMicro")
public class BasispositionMicro {
    @Autowired
    private BasispositionMapper mapper ;
////    判断是否已经存在数据
    @RequestMapping("/check")
    public Map check(String positionno, String positionname ,Byte typeflag){
        Map map = new HashMap();
        if ("".equals(positionname)){
            //判断是否输入
            map.put("status" , "null");
            return map;
        }
        Basisposition basisposition = mapper.check(positionno+"%", positionname ,typeflag);
        if (basisposition != null) {
            map.put("status","false");
            return map;
        }
        else
            map.put("status","true");
        return map;
    }
    //判断小区是否存在
    @RequestMapping("/basispositionCheck")
    public Map basispositionCheck(String positionname ,Byte typeflag){
        Map map = new HashMap();
        if ("".equals(positionname)){
            //判断是否输入
            map.put("status" , "null");
            return map;
        }
        Basisposition basisposition = mapper.basispositionCheck(positionname ,typeflag);
        if (basisposition != null) {
            map.put("status","false");
            return map;
        }
        else
            map.put("status","true");
        return map;
    }
}
