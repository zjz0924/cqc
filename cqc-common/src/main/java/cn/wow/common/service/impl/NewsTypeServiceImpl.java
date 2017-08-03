package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.utils.pagination.PageHelperExt;
import cn.wow.common.dao.NewsTypeDao;
import cn.wow.common.domain.NewsType;
import cn.wow.common.service.NewsTypeService;

@Service
@Transactional
public class NewsTypeServiceImpl implements NewsTypeService{

    private static Logger logger = LoggerFactory.getLogger(NewsTypeServiceImpl.class);

    @Autowired
    private NewsTypeDao newsTypeDao;

    public NewsType selectOne(Long id){
    	return newsTypeDao.selectOne(id);
    }

    public int save(NewsType newsType){
    	return newsTypeDao.insert(newsType);
    }

    public int update(NewsType newsType){
    	return newsTypeDao.update(newsType);
    }

    public int deleteByPrimaryKey(Long id){
    	return newsTypeDao.deleteByPrimaryKey(id);
    }

    public List<NewsType> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return newsTypeDao.selectAllList(map);
    }

}
