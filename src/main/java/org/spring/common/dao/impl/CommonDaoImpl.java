package org.spring.common.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.spring.common.dao.CommonDao;
import org.spring.common.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonDaoImpl<T, ID extends Serializable> extends SqlSessionDaoSupport implements CommonDao<T, ID> {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#queryListByMap(java.util.Map,
	 * java.lang.String)
	 */
	@Override
	public List<T> queryListByMap(Map<String, Object> paramsMap, String statement) throws DAOException {
		return this.getSqlSession().selectList(statement, paramsMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#insert(T, java.lang.String)
	 */
	@Override
	public T insert(T t, String statement) throws DAOException {
		getSqlSession().insert(statement, t);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#queryList(java.lang.String)
	 */
	@Override
	public List<T> queryList(String statement) throws DAOException {
		return this.getSqlSession().selectList(statement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.spring.common.dao.CommonDao#get(java.util.Map, java.lang.String)
	 */
	@Override
	public T get(Map<String, Object> paramsMap, String statement) {
		return this.getSqlSession().selectOne(statement, paramsMap);
	}

	/*
	 * (non-Javadoc)
	 * @see org.spring.common.dao.CommonDao#queryPageByMap(java.util.Map, java.lang.String)
	 */
	@Override
	public List<T> queryPageByMap(Map<String, Object> paramsMap, String statement) throws DAOException {
		return this.getSqlSession().selectList(statement, paramsMap);
	}

}
