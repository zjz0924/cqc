package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wow.common.dao.LawDao;
import cn.wow.common.domain.Law;
import cn.wow.common.service.LawService;
import cn.wow.common.utils.pagination.PageHelperExt;

@Service
@Transactional
public class LawServiceImpl implements LawService{

    private static Logger logger = LoggerFactory.getLogger(LawServiceImpl.class);

    @Autowired
    private LawDao lawDao;

    public Law selectOne(Long id){
    	return lawDao.selectOne(id);
    }

    public int save(Law law){
    	return lawDao.insert(law);
    }

    public int update(Law law){
    	return lawDao.update(law);
    }

    public int deleteByPrimaryKey(Long id){
    	return lawDao.deleteByPrimaryKey(id);
    }

    public List<Law> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return lawDao.selectAllList(map);
    }

}
