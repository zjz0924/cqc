package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Member;

public interface MemberService {
    public Member selectOne(Long id);

    public int save(Member member);

    public int update(Member member);

    public int deleteByPrimaryKey(Long id);

    public List<Member> selectAllList(Map<String, Object> map);
    
    public Member selectByUserName(String userName);

}
