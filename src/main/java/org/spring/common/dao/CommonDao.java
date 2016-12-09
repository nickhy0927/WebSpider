package org.spring.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.spring.common.exception.DAOException;

public interface CommonDao<T, ID extends Serializable> {

	List<T> queryListByMap(Map<String, Object> paramsMap, String statement) throws DAOException;
	
	List<T> queryPageByMap(Map<String, Object> paramsMap, String statement) throws DAOException;

	T insert(T t, String statement) throws DAOException;

	List<T> queryList(String statement) throws DAOException;

	T get(Map<String, Object> paramsMap, String statement) throws DAOException;

}