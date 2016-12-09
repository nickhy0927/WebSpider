package org.spring.platform.user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
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

	public User findByUsername(Map<String, Object> pramasMap) {
		return userDao.findByUsername("org.spring.platform.user.entity.User.findByUsername", pramasMap);
	}

	public User findByUsernameAndPassword(String loginname, String password) {
		Assert.assertNotNull("用户名loginname : " + loginname + "不能为空", loginname);
		Assert.assertNotNull("用户密码 : " + password + "不能为空", password);
		Map<String, Object> pramasMap = new HashMap<String, Object>();
		pramasMap.put("loginname", loginname);
		User user = findByUsername(pramasMap);
		return user;
	}

	public User save(User user) {
		user = EndecryptUtils.endecrptPassword(user);
		user = userDao.insert(user, "org.spring.platform.user.entity.User.save");
		if (user.getId() != null) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
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
	 * @param statement
	 * @return
	 */
	public User get(Map<String, Object> paramsMap) {
		return userDao.get(paramsMap, "org.spring.platform.user.entity.User.get");
	}

	public Set<String> findRoles(Long userId) {
		return userDao.findRoles("org.spring.platform.user.entity.User.findRoles", userId);
	}

	public Set<String> findPermissions(Long userId) {
		return userDao.findPermissions("org.spring.platform.user.entity.User.findPermissions", userId);
	}

}
