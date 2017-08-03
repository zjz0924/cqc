package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.dao.MemberServerDao;
import cn.wow.common.domain.MemberServer;
import cn.wow.common.service.MemberServerService;
import cn.wow.common.utils.pagination.PageHelperExt;

@Service
@Transactional
public class MemberServerServiceImpl implements MemberServerService{

    private static Logger logger = LoggerFactory.getLogger(MemberServerServiceImpl.class);

    @Autowired
    private MemberServerDao memberServerDao;

    public MemberServer selectOne(Long id){
    	return memberServerDao.selectOne(id);
    }

    public int save(MemberServer server){
    	return memberServerDao.insert(server);
    }

    public int update(MemberServer server){
    	return memberServerDao.update(server);
    }

    public int deleteByPrimaryKey(Long id){
    	return memberServerDao.deleteByPrimaryKey(id);
    }

    public List<MemberServer> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return memberServerDao.selectAllList(map);
    }

}
