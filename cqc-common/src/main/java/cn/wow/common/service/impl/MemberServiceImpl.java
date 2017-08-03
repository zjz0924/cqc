package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.utils.pagination.PageHelperExt;
import cn.wow.common.dao.MemberDao;
import cn.wow.common.domain.Member;
import cn.wow.common.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberDao memberDao;

    public Member selectOne(Long id){
    	return memberDao.selectOne(id);
    }

    public int save(Member member){
    	return memberDao.insert(member);
    }

    public int update(Member member){
    	return memberDao.update(member);
    }

    public int deleteByPrimaryKey(Long id){
    	return memberDao.deleteByPrimaryKey(id);
    }

    public List<Member> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return memberDao.selectAllList(map);
    }
    
    public Member selectByUserName(String userName){
    	return memberDao.selectByUserName(userName);
    }

}
