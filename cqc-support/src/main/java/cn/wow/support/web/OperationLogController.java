package cn.wow.support.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.wow.common.domain.OperationLog;
import cn.wow.common.service.OperationLogService;
import cn.wow.common.utils.operationlog.FieldValue;
import cn.wow.common.utils.pagination.PageMap;
import cn.wow.support.utils.Contants;
import wow.operationlog.manager.EntityServiceTypeMap;

@Controller
@RequestMapping(value = "operationlog")
public class OperationLogController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(OperationLogController.class);

	private final static String moduleName = Contants.OPERATIONLOG;
	
	@Autowired
	private OperationLogService operationLogService;

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest httpServletRequest, Model model, String userName, String type,
			String startTimeFrom, String startTimeTo, String detail, String operation) {
		Map<String, Object> map = new PageMap(httpServletRequest);
		map.put("custom_order_sql", "time desc");

		if (StringUtils.isNotBlank(userName)) {
			map.put("userName", userName);
		}
		if (StringUtils.isNotBlank(type)) {
			map.put("type", type);
		}
		if (StringUtils.isNotBlank(operation)) {
			map.put("operation", operation);
		}
		if (StringUtils.isNotBlank(detail)) {
			map.put("detail", detail);
		}
		if (StringUtils.isNotBlank(startTimeFrom)) {
			map.put("startTimeFrom", startTimeFrom + " 00:00:00");
		}
		if (StringUtils.isNotBlank(startTimeTo)) {
			map.put("startTimeTo", startTimeTo + " 23:59:59");
		}
		List<OperationLog> opeartionLogList = operationLogService.selectAllList(map);

		if (opeartionLogList != null && opeartionLogList.size() > 0) {
			for (OperationLog operationLog : opeartionLogList) {
				if (StringUtils.isNotBlank(operationLog.getDetail())) {
					operationLog
							.setDetail(operationLog.getDetail().replaceAll("\\\\r\\\\n", "").replaceAll("\\\\", ""));
				}
			}
		}

		model.addAttribute("userName", userName);
		model.addAttribute("type", type);
		model.addAttribute("operation", operation);
		model.addAttribute("detail", detail);
		model.addAttribute("startTimeFrom", startTimeFrom);
		model.addAttribute("startTimeTo", startTimeTo);
		model.addAttribute("typeList", EntityServiceTypeMap.getAllType());
		model.addAttribute("dataList", opeartionLogList);
		return "sys/operationlog/operationlog_list";
	}

	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest httpServletRequest, Model model, Long id) {
		Map<String, String> entityMap = new HashMap<String, String>();
		Map<String, String> oldEntityMap = new HashMap<String, String>();
		List<FieldValue> dataList = new ArrayList<FieldValue>();

		OperationLog operationLog = operationLogService.selectOne(id);
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(operationLog.getDetail());
			JsonNode entity = root.path("ENTITY");
			JsonNode oldEntity = root.path("OLDENTITY");
			JsonNode operation = root.path("OPERATION");

			entityMap = mapper.readValue(entity.asText(), new TypeReference<HashMap<String, String>>() {});
			oldEntityMap = mapper.readValue(oldEntity.asText(), new TypeReference<HashMap<String, String>>() {});

			dataList = toFacade(entityMap, oldEntityMap);
			
			model.addAttribute("operation", operation.asText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		model.addAttribute("opeartionLog", operationLog);
		model.addAttribute("dataList", dataList);
		return "sys/operationlog/operationlog_detail";
	}

	public List<FieldValue> toFacade(Map<String, String> entityMap, Map<String, String> oldEntityMap) {
		List<FieldValue> dataList = new ArrayList<FieldValue>();

		if (entityMap != null) {
			Iterator<Map.Entry<String, String>> entityEntries = entityMap.entrySet().iterator();
			while (entityEntries.hasNext()) {
				Map.Entry<String, String> entry = entityEntries.next();

				FieldValue val = new FieldValue();
				val.setName(entry.getKey());
				val.setNewValue(entry.getValue());
				dataList.add(val);
			}
		}

		if (oldEntityMap != null) {
			Iterator<Map.Entry<String, String>> oldEntityEntries = oldEntityMap.entrySet().iterator();
			while (oldEntityEntries.hasNext()) {
				Map.Entry<String, String> entry = oldEntityEntries.next();

				boolean hasVal = false;
				for (FieldValue val : dataList) {
					if (val.getName().equals(entry.getKey())) {
						val.setOldValue(entry.getValue());
						hasVal = true;
						break;
					}
				}

				if (!hasVal) {
					FieldValue val = new FieldValue();
					val.setName(entry.getKey());
					val.setOldValue(entry.getValue());
					dataList.add(val);
				}
			}
		}
		return dataList;
	}

}