package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.dao.GroupDao;
import cn.wow.common.domain.Group;
import cn.wow.common.service.GroupService;
import cn.wow.common.utils.pagination.PageHelperExt;

@Service
@Transactional
public class GroupServiceImpl implements GroupService{

    private static Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupDao groupDao;

    public Group selectOne(Long id){
    	return groupDao.selectOne(id);
    }

    public int save(Group group){
    	return groupDao.insert(group);
    }

    public int update(Group group){
    	return groupDao.update(group);
    }

    public int deleteByPrimaryKey(Long id){
    	return groupDao.deleteByPrimaryKey(id);
    }

    public List<Group> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return groupDao.selectAllList(map);
    }

}
