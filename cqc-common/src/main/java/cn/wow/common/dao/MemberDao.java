package cn.wow.common.dao;

import cn.wow.common.domain.Member;

public interface MemberDao extends SqlDao {
	
	public Member selectByUserName(String name);
}