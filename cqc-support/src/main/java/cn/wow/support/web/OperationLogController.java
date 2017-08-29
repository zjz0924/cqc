package cn.wow.support.web;

import java.io.IOException;
import java.util.HashMap;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.wow.common.domain.OperationLog;
import cn.wow.common.service.OperationLogService;
import cn.wow.common.utils.pagination.PageMap;
import wow.operationlog.manager.EntityServiceTypeMap;

@Controller
@RequestMapping(value = "operationlog")
public class OperationLogController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(OperationLogController.class);

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
		OperationLog operationLog = operationLogService.selectOne(id);

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(operationLog.getDetail());

			JsonNode entity = root.path("ENTITY");
			JsonNode oldEntity = root.path("OLDENTITY");
			JsonNode entityType = root.path("ENTITYTYPE");
			JsonNode operation = root.path("OPERATION");
			
			System.out.println(entity.asText());
			System.out.println(oldEntity.asText());
			System.out.println(entityType.asText());
			System.out.println(operation.asText());
			
			Map<String, String> map = new HashMap<String, String>();
			map = mapper.readValue(oldEntity.asText(), new TypeReference<HashMap<String,String>>(){});  
            System.out.println(map);  

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		model.addAttribute("opeartionLog", operationLog);
		return "sys/operationlog/operationlog_detail";
	}

}