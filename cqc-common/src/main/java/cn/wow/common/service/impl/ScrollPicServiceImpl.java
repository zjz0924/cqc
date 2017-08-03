package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.utils.pagination.PageHelperExt;
import cn.wow.common.dao.ScrollPicDao;
import cn.wow.common.domain.ScrollPic;
import cn.wow.common.service.ScrollPicService;

@Service
@Transactional
public class ScrollPicServiceImpl implements ScrollPicService{

    private static Logger logger = LoggerFactory.getLogger(ScrollPicServiceImpl.class);

    @Autowired
    private ScrollPicDao scrollPicDao;

    public ScrollPic selectOne(Long id){
    	return scrollPicDao.selectOne(id);
    }

    public int save(ScrollPic scrollPic){
    	return scrollPicDao.insert(scrollPic);
    }

    public int update(ScrollPic scrollPic){
    	return scrollPicDao.update(scrollPic);
    }

    public int deleteByPrimaryKey(Long id){
    	return scrollPicDao.deleteByPrimaryKey(id);
    }

    public List<ScrollPic> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return scrollPicDao.selectAllList(map);
    }

}
