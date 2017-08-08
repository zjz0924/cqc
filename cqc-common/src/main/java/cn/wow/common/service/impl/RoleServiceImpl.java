package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.utils.pagination.PageHelperExt;
import cn.wow.common.dao.RoleDao;
import cn.wow.common.domain.Role;
import cn.wow.common.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    public Role selectOne(Long id){
    	return roleDao.selectOne(id);
    }

    public int save(Role role){
    	return roleDao.insert(role);
    }

    public int update(Role role){
    	return roleDao.update(role);
    }

    public int deleteByPrimaryKey(Long id){
    	return roleDao.deleteByPrimaryKey(id);
    }

    public List<Role> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return roleDao.selectAllList(map);
    }

}
