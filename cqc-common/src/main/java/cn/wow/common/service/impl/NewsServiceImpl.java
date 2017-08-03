package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wow.common.dao.NewsDao;
import cn.wow.common.domain.News;
import cn.wow.common.service.NewsService;
import cn.wow.common.utils.pagination.PageHelperExt;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{

    private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsDao newsDao;

    public News selectOne(Long id){
    	return newsDao.selectOne(id);
    }

    public int save(News news){
    	return newsDao.insert(news);
    }

    public int update(News news){
    	return newsDao.update(news);
    }

    public int deleteByPrimaryKey(Long id){
    	return newsDao.deleteByPrimaryKey(id);
    }

    public List<News> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return newsDao.selectAllList(map);
    }

}
