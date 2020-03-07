package com.lion.controllerability.basisposition.service;

import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.data.BasispositionConstant;
import com.lion.controllerability.basisposition.data.BasispositionExample;
import com.lion.controllerability.basisposition.mapper.BasispositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/03
 **/
@Service
public class BasispositionService {
    @Autowired
        private BasispositionMapper mapper ;

        /**
         *
         * 新增小区信息
         *
         * */
        public int insert(Basisposition basisposition) {
            if (exist(basisposition)){
                return 0;
            }
            int result = mapper.insert(basisposition);
            return result ;
        }

        /**
         * 查询所有小区信息
         * */
        public List<Basisposition> selectAll() {
            BasispositionExample example = new BasispositionExample();
            BasispositionExample.Criteria criteria = example.createCriteria();
            //限制查询小区
            criteria.andTypeflagEqualTo(BasispositionConstant.VILLAGE);
            //小区处于启用状态
//            criteria.andActiveflagEqualTo(BasispositionConstant.ENABLE);
            List<Basisposition> basispositions = mapper.selectByExample(example);
            return basispositions ;
        }
        /**
         * 查询该小区小有多少楼栋
         * */
        public int conutBuilding(String positionno,String disdistrictcode) {
            BasispositionExample example = new BasispositionExample();
            BasispositionExample.Criteria criteria = example.createCriteria();
            criteria.andPositionnoLike((positionno+"%"));
            criteria.andDistrictcodeEqualTo(disdistrictcode);
            criteria.andTypeflagEqualTo(BasispositionConstant.BUILDING);
            int count = mapper.countByExample(example);
            return count ;
        }

        /**
         * 查询小区所属下的楼栋信息
         * */
        public List<Basisposition> selectBuilding(String positionno,String districtcode) {
            BasispositionExample example = new BasispositionExample();
            BasispositionExample.Criteria criteria = example.createCriteria();
            criteria.andPositionnoLike((positionno+"%"));
            criteria.andDistrictcodeEqualTo(districtcode);
            criteria.andTypeflagEqualTo(BasispositionConstant.BUILDING);
            List<Basisposition> basispositions = mapper.selectByExample(example);
            return basispositions;
        }

    /**
     * 查询该楼栋下有多少单元
     * */
    public int conutUnit(String positionno,String disdistrictcode) {
        BasispositionExample example = new BasispositionExample();
        BasispositionExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictcodeEqualTo(disdistrictcode);
        criteria.andPositionnoLike((positionno+"%"));
        criteria.andTypeflagEqualTo(BasispositionConstant.UNIT);
        int count = mapper.countByExample(example);
        return count ;
    }
        /**
         * 查询小区所属下的所属单元的信息
         * */
        public List<Basisposition> selectUntit(String buildingName ,String districtcode) {
            BasispositionExample example = new BasispositionExample();
            BasispositionExample.Criteria criteria = example.createCriteria();
            criteria.andPositionnoLike(buildingName+"%");
            criteria.andDistrictcodeEqualTo(districtcode);
            //排除小区选择
            criteria.andTypeflagEqualTo(BasispositionConstant.UNIT);
            List<Basisposition> basispositions = mapper.selectByExample(example);
            return basispositions ;
        }

        /**
         * 查询单元所属下的所属住户的信息
         * */
        public List<Basisposition> selectHouse(String positionno,String districtcode) {
            BasispositionExample example = new BasispositionExample();
            BasispositionExample.Criteria criteria = example.createCriteria();
            criteria.andDistrictcodeEqualTo(districtcode);
            criteria.andPositionnoLike(positionno+"%");
            //排除小区选择
            criteria.andTypeflagEqualTo(BasispositionConstant.HOUSE);
            List<Basisposition> basispositions = mapper.selectByExample(example);
            return basispositions ;
        }


    public int conutHouse(String positionno,String disdistrictcode) {
        BasispositionExample example = new BasispositionExample();
        BasispositionExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictcodeEqualTo(disdistrictcode);
        criteria.andPositionnoLike(positionno+"%");
        criteria.andTypeflagEqualTo(BasispositionConstant.HOUSE);
        int count = mapper.countByExample(example);
        return count ;
    }

    /**
     * 查询已存在多少小区
     * */
    public int countVillage() {
        BasispositionExample example = new BasispositionExample();
        BasispositionExample.Criteria criteria = example.createCriteria();
        criteria.andTypeflagEqualTo(BasispositionConstant.VILLAGE);
        List<Basisposition> basispositions = mapper.selectByExample(example);
        return basispositions.size();
    }


        /**
         *
         *
         * 用在新增、修改时检查数据库中是否存在重复记录（请务必保留该方法）
         *
         * @param temp 将要修改的小区、楼栋、单元、房屋信息POJO
         * @return true：已经存在 false：不存在
         */
        private boolean exist(Basisposition temp) {
            // 检查修改的小区、楼栋、单元、房屋信息是否有重复记录
        BasispositionExample example = new BasispositionExample();
        BasispositionExample.Criteria criteria = example.createCriteria();
        if (temp.getPositionid() != null)
            criteria.andPositionidNotEqualTo(temp.getPositionid());
                // 行政区划编码，关联config_item_base.ItemCode[Category=行政区划]
            criteria.andDistrictcodeEqualTo(temp.getDistrictcode());
            // 小区全称，或者楼栋、单元、房屋号
            criteria.andPositionnameEqualTo(temp.getPositionname());
            // 类型标识{1：小区2：楼栋3：单元4：房屋}
            criteria.andTypeflagEqualTo(temp.getTypeflag());
            // 是否启用标识
            criteria.andActiveflagEqualTo(temp.getActiveflag());

            return mapper.selectByExample(example) == null;


        }
}
