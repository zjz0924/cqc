package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Role;
import cn.wow.common.domain.RolePermission;

public interface RoleService {
    public Role selectOne(Long id);

    public int save(Role role);

    public int update(Role role);

    public int deleteByPrimaryKey(Long id);

    public List<Role> selectAllList(Map<String, Object> map);
    
    public void addRole(Role role, RolePermission rolePermission);
    
    public void updateRole(Role role, RolePermission rolePermission);

}
