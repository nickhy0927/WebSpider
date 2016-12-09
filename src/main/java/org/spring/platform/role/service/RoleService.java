package org.spring.platform.role.service;

import java.util.HashMap;
import java.util.Map;

import org.spring.platform.role.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.spring.platform.role.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	private static String getStatementName(String methodName) {
		return RoleService.class.getName() + "." + methodName;
	}

	@Autowired
	private RoleDao roleDao;

	public Role save(Role role) {
		role = roleDao.insert(role, getStatementName("save"));
		if (role.getId() != null) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			role = get(paramsMap);
		}
		return role;
	}

	public Role get(Map<String, Object> paramsMap) {
		Role role = roleDao.get(paramsMap, getStatementName("get"));
		return role;
	}
}
