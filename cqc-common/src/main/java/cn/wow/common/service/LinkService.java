package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Link;

public interface LinkService {
    public Link selectOne(Long id);

    public int save(Link link);

    public int update(Link link);

    public int deleteByPrimaryKey(Long id);

    public List<Link> selectAllList(Map<String, Object> map);

}
