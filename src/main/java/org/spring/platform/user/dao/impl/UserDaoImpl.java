package org.spring.platform.user.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.spring.common.dao.impl.CommonDaoImpl;
import org.spring.platform.user.dao.UserDao;
import org.spring.platform.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends CommonDaoImpl<User, Long> implements UserDao {

	/* (non-Javadoc)
	 * @see org.spring.platform.user.dao.UserDao#findByUsername(java.lang.String, java.lang.String)
	 */
	@Override
	public User findByUsername(String statement, Map<String, Object> pramasMap) {
		return this.getSqlSession().selectOne(statement, pramasMap);
	}

	/* (non-Javadoc)
	 * @see org.spring.platform.user.dao.UserDao#findRoles(java.lang.String, java.lang.Long)
	 */
	@Override
	public Set<String> findRoles(String statement, String userId) {
		List<String> list = this.getSqlSession().selectList(statement, userId);
		Set<String> roles = new HashSet<String>();
		roles.addAll(list);
		return roles;
	}
	
	/* (non-Javadoc)
	 * @see org.spring.platform.user.dao.UserDao#findPermissions(java.lang.String, java.lang.Long)
	 */
	@Override
	public Set<String> findPermissions(String statement, String userId) {
		List<String> list = this.getSqlSession().selectList(statement, userId);
		Set<String> permissions = new HashSet<String>();
		permissions.addAll(list);
		return permissions;
	}
}
