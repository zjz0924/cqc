package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.utils.pagination.PageHelperExt;
import cn.wow.common.dao.LinkDao;
import cn.wow.common.domain.Link;
import cn.wow.common.service.LinkService;

@Service
@Transactional
public class LinkServiceImpl implements LinkService{

    private static Logger logger = LoggerFactory.getLogger(LinkServiceImpl.class);

    @Autowired
    private LinkDao linkDao;

    public Link selectOne(Long id){
    	return linkDao.selectOne(id);
    }

    public int save(Link link){
    	return linkDao.insert(link);
    }

    public int update(Link link){
    	return linkDao.update(link);
    }

    public int deleteByPrimaryKey(Long id){
    	return linkDao.deleteByPrimaryKey(id);
    }

    public List<Link> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return linkDao.selectAllList(map);
    }

}
