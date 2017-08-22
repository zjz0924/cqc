package cn.wow.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.dao.OperationLogDao;
import cn.wow.common.domain.operationlog.ClientInfo;
import cn.wow.common.domain.OperationLog;
import cn.wow.common.domain.operationlog.OperationType;
import cn.wow.common.domain.operationlog.ServiceType;
import cn.wow.common.service.OperationLogService;
import cn.wow.common.utils.pagination.PageHelperExt;
import cn.wow.common.utils.pagination.PageMap;

@Service
@Transactional
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private OperationLogDao operationLogDao;

	// make it thread-safe
	private static final Map<String, ClientInfo> clientInfoMap = new ConcurrentHashMap<String, ClientInfo>();

	public OperationLog selectOne(Long id) {
		return operationLogDao.selectOne(id);
	}

	public Long save(String theUserName, OperationType operationType, ServiceType serviceType, String jsonDetail) {
		String clientIp = null;
		String userAgent = null;
		String theUser1 = theUserName;
		ClientInfo clientInfo = getClientInfoByUserName(theUserName);
		clientIp = clientInfo.getClientIp();
		userAgent = clientInfo.getUserAgent();

		OperationLog operationLog = new OperationLog();
		operationLog.setUserName(theUser1);
		operationLog.setType(serviceType.getDisplayName());
		operationLog.setTime(new Date());
		operationLog.setClientIp(clientIp);
		operationLog.setUserAgent(userAgent);
		if (StringUtils.isNotBlank(jsonDetail)) {
			operationLog.setDetail(jsonDetail);
		}

		try{
			operationLogDao.insert(operationLog);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return operationLog.getId();
	}

	public void createOrUpdateUserClientInfo(String userName, ClientInfo clientInfo) {
		if (userName != null && userName.trim().length() > 0 && clientInfo != null) {
			// HashMap will overwrite the clientInfo record if the userName already exists
			clientInfoMap.put(userName, clientInfo);
		}
	}

	public ClientInfo getClientInfoByUserName(String userName) {
		if (userName == null) {
			return null;
		}
		return clientInfoMap.get(userName);
	}

	public List<OperationLog> selectAllList(String pageNum, String pageSize, String userName, String type,
			String startTimeFrom, String startTimeTo, String detail) {
		Map<String, Object> map = new PageMap(false);

		if (StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)) {
			new PageMap(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		}

		if (StringUtils.isNotBlank(userName)) {
			map.put("userName", userName);
		}
		if (StringUtils.isNotBlank(type)) {
			map.put("type", type);
		}
		if (StringUtils.isNotBlank(detail)) {
			map.put("detail", detail);
		}
		if (StringUtils.isNotBlank(startTimeFrom)) {
			map.put("startTimeFrom", startTimeFrom + " 00:00:00");
		}
		if (StringUtils.isNotBlank(startTimeTo)) {
			map.put("endCreateTime", startTimeTo + " 23:59:59");
		}

		PageHelperExt.startPage(map);
		return operationLogDao.selectAllList(map);
	}

}