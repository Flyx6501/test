package com.example.test.service.impl;

import com.example.test.converter.JpaLogin2LoginDTOConverter;
import com.example.test.converter.JpaRedlien2RedlineDTOConverter;
import com.example.test.dao.LimitRepository;
import com.example.test.dao.RedlineRepository;
import com.example.test.dto.LoginDTO;
import com.example.test.dto.RedlineDTO;
import com.example.test.enums.ResultEnum;
import com.example.test.exception.SellException;
import com.example.test.jpaentity.jpaLimit;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaRedline;
import com.example.test.service.JpaLimitService;
import com.example.test.service.JpaRedlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JpaRedlineServiceImpl implements JpaRedlineService {

    @Autowired
    private RedlineRepository redlineRepository;

    //查找未删除的牛只信息

    @Override
    public List<jpaRedline> findByDelFlag(Integer delFlag) {
        List<jpaRedline> redlines = redlineRepository.findByDelFlag(delFlag);
        if (redlines == null) {
            throw new SellException(ResultEnum.REDLINE_NOT_EXIST);
        }
        return redlines;
    }

    /** 新增牛只信息 */
    @Override
    public jpaRedline save(jpaRedline jparedline){return redlineRepository.save(jparedline);}

    @Override
    public Page<RedlineDTO> findList(Integer userId, Pageable pageable) {
        Page<jpaRedline> redlinePage = redlineRepository.findByUserId(userId, pageable);
        List<RedlineDTO> redlineDTOList = JpaRedlien2RedlineDTOConverter.convert(redlinePage.getContent());

        return new PageImpl<RedlineDTO>(redlineDTOList, pageable, redlinePage.getTotalElements());
    }

    @Override
    public jpaRedline  findNodelByRedlineName(String redlineName) {
        jpaRedline redline = redlineRepository.findNodelByRedlineName(redlineName);
        return redline;
    }

    /** 查询单个牛只判断是否存在. */
    @Override
    public jpaRedline   findOne(Integer redlineId){
        Optional<jpaRedline> redline = redlineRepository.findByRedlineId(redlineId);
        if(redline==null){
            throw new SellException(ResultEnum.REDLINE_NOT_EXIST);

        }

        return redline.orElse(new jpaRedline());
    }

    @Override
    public jpaRedline delredline(jpaRedline jparedline) {
        //判断用户状态
        if (!jparedline.getDelFlag().equals(0)) {
            log.error("【删除牛只】删除状态不正确, Id={}, delflag={}", jparedline.getRedlineId(), jparedline.getDelFlag());
            throw new SellException(ResultEnum.FILE_STATUS_ERROR);
        }
        jparedline.setDelFlag(1);
        jpaRedline updateResult =   redlineRepository.save(jparedline);

        if (updateResult == null) {
            log.error("【删除牛只失败】更新失败, jparedline={}", jparedline);
            throw new SellException(ResultEnum.FILE_UPDATE_FAIL);
        }
        return jparedline;
    }

    @Override
    public RedlineDTO findDtoById(Integer cattleId) {
        jpaRedline jparedline = new jpaRedline();
        Optional<jpaRedline> redline = redlineRepository.findById(cattleId);
//        jpalogin.setId();
//        jpalogin.setUsername();
//        jpalogin.setPassword();login.get().getUsername();
        if(redline==null){
            throw new SellException(ResultEnum.CATTLE_NOT_EXIST);

        }
        RedlineDTO redlineDTO = new RedlineDTO();
        BeanUtils.copyProperties(redline.get(), redlineDTO);
        return redlineDTO;
    }

}
