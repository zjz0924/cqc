package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Role;

public interface RoleService {
    public Role selectOne(Long id);

    public int save(Role role);

    public int update(Role role);

    public int deleteByPrimaryKey(Long id);

    public List<Role> selectAllList(Map<String, Object> map);

}
