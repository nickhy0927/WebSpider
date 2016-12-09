package org.spring.platform.role.dao.impl;

import org.spring.common.dao.impl.CommonDaoImpl;
import org.spring.platform.role.dao.RoleDao;
import org.spring.platform.role.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends CommonDaoImpl<Role, Long> implements RoleDao{

}
