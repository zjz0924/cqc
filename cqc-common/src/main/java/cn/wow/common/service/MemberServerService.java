package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.MemberServer;

public interface MemberServerService {
    public MemberServer selectOne(Long id);

    public int save(MemberServer memberServer);

    public int update(MemberServer memberServer);

    public int deleteByPrimaryKey(Long id);

    public List<MemberServer> selectAllList(Map<String, Object> map);

}
