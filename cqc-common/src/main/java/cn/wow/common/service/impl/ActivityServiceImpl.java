package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.dao.ActivityDao;
import cn.wow.common.domain.Activity;
import cn.wow.common.service.ActivityService;
import cn.wow.common.utils.pagination.PageHelperExt;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{

    private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Autowired
    private ActivityDao activityDao;

    public Activity selectOne(Long id){
    	return activityDao.selectOne(id);
    }

    public int save(Activity activity){
    	return activityDao.insert(activity);
    }

    public int update(Activity activity){
    	return activityDao.update(activity);
    }

    public int deleteByPrimaryKey(Long id){
    	return activityDao.deleteByPrimaryKey(id);
    }

    public List<Activity> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return activityDao.selectAllList(map);
    }

}
