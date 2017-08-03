package cn.wow.common.service;


import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Account;

public interface AccountService {
	
	public Account selectOne(Long id);
	
	public int save(Account account);
	
	public int update(Account account);
	
	public int deleteByPrimaryKey(Long id);
	
	public Account selectByAccountName(String userName);
	
	public List<Account> selectAllList(Map<String, Object> map);
}
