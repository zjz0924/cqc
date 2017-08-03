package cn.wow.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.dao.IntroductionDao;
import cn.wow.common.domain.Introduction;
import cn.wow.common.service.IntroductionService;

@Service
@Transactional
public class IntroductionServiceImpl implements IntroductionService{

    private static Logger logger = LoggerFactory.getLogger(IntroductionServiceImpl.class);

    @Autowired
    private IntroductionDao introductionDao;

    public Introduction selectOne(Long id){
    	return introductionDao.selectOne(id);
    }

    public int update(Introduction introduction){
    	return introductionDao.update(introduction);
    }
}
