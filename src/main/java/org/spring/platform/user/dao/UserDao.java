package org.spring.platform.user.dao;

import java.util.Map;
import java.util.Set;

import org.spring.common.dao.CommonDao;
import org.spring.platform.user.entity.User;

public interface UserDao extends CommonDao<User, Long>{

	/**
	 * @author：HUANGYUAN
	 * @time 2016年11月24日 下午2:02:36
	 * @方法注释：查询用户
	 * @param statement
	 * @param username
	 * @return
	 */
	User findByUsername(String statement, Map<String, Object> pramasMap);

	Set<String> findRoles(String statement, Long userId);

	Set<String> findPermissions(String statement, Long userId);

}