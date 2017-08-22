package cn.wow.common.domain;

import java.io.Serializable;

public class RolePermission implements Serializable, MybatisVO{
	
	private static final long serialVersionUID = -2394074790258760100L;

	private Long id;
	
	private Long roleId;
	
	private String permission;
	
	public RolePermission(){
		
	}
	
	public RolePermission(String permission){
		this.permission = permission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
