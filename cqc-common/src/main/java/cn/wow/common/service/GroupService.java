package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Group;

public interface GroupService {
    public Group selectOne(Long id);

    public int save(Group group);

    public int update(Group group);

    public int deleteByPrimaryKey(Long id);

    public List<Group> selectAllList(Map<String, Object> map);

}
