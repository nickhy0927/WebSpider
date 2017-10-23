package org.spring.platform.user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.spring.common.service.CommonService;
import org.spring.common.utils.EndecryptUtils;
import org.spring.platform.user.dao.UserDao;
import org.spring.platform.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CommonService<User, Long> {

	@Autowired
	private UserDao userDao;

	public User findByUsername(Map<String, Object> paramsMap) {
		return userDao.findByUsername("org.spring.platform.user.entity.User.findByUsername", paramsMap);
	}

	public User findByUsernameAndPassword(String loginName, String password) {
		Map<String, Object> pramasMap = new HashMap<>();
		pramasMap.put("loginName", loginName);
		User user = findByUsername(pramasMap);
		return user;
	}

	public User save(User user) {
		user = EndecryptUtils.endecrptPassword(user);
		user = userDao.insert(user, "org.spring.platform.user.entity.User.save");
		if (user.getId() != null) {
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("id", user.getId());
			user = get(paramsMap);
		}
		return user;
	}

	/**
	 * @author：HUANGYUAN
	 * @time 2016年11月24日 下午3:10:31
	 * @方法注释：查询一条记录
	 * @param paramsMap
	 * @return
	 */
	public User get(Map<String, Object> paramsMap) {
		return userDao.get(paramsMap, "org.spring.platform.user.entity.User.get");
	}

	public Set<String> findRoles(String userId) {
		return userDao.findRoles("org.spring.platform.user.entity.User.findRoles", userId);
	}

	public Set<String> findPermissions(String userId) {
		return userDao.findPermissions("org.spring.platform.user.entity.User.findPermissions", userId);
	}

}
