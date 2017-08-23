package wow.operationlog.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wow.common.domain.Account;
import cn.wow.common.domain.Role;
import cn.wow.common.domain.operationlog.ServiceType;

public final class EntityServiceTypeMap {
	private EntityServiceTypeMap() {
		// not called
	}

	private static Map<String, ServiceType> typeMap = new HashMap<String, ServiceType>();
	static {
		initMap();
	}

	static void initMap() {
		typeMap.clear();
		typeMap.put(Account.class.getName(), ServiceType.ACCOUNT);
		typeMap.put(Role.class.getName(), ServiceType.ROLE);
	}

	public static ServiceType getServiceType(Class<?> clazz) {
		if (clazz != null) {
			String className = clazz.getName();
			return getServiceType(className);
		}
		return null;
	}

	public static ServiceType getServiceType(String className) {
		return typeMap.get(className);
	}

	public static boolean contains(ServiceType type) {
		for (ServiceType t : typeMap.values()) {
			if (t.equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static List<String> getAllType() {
		List<String> typeList = new ArrayList<String>();
		for (ServiceType type : ServiceType.values()) {
			typeList.add(type.getDisplayName());
		}
		return typeList;
	}

}
