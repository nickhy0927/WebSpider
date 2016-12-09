package org.spring.common.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.spring.common.dao.CommonDao;
import org.spring.common.exception.ServiceException;
import org.spring.common.utils.PageSupport;
import org.spring.common.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonService<T, ID extends Serializable> {

	@Autowired
	private CommonDao<T, ID> commonDao;

	protected Class<T> clazz;

	private String packageName;

	private String getPackageName(Object method) {
		method = StringUtils.isNotEmpty(method.toString()) ? method : null;
		Assert.assertNotNull("参数方法名称不能为空", method);
		return packageName + "." + method;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@SuppressWarnings("unchecked")
	public CommonService() {
		Class<?> clazz = getClass();
		while (clazz != Object.class) {
			Type t = clazz.getGenericSuperclass();
			if (t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
				if (args[0] instanceof Class) {
					this.clazz = (Class<T>) args[0];
					this.packageName = this.clazz.getName();
					break;
				}
			}
		}
	}

	/**
	 * 插入数据
	 * @param t
	 * @return
	 */
	public T insert(T t) {
		try {
			return commonDao.insert(t, getPackageName("insert"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 查询所有的数据
	 * @return
	 */
	public List<T> queryList() {
		try {
			return commonDao.queryList(getPackageName("queryList"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一条数据
	 * @param paramsMap
	 * @param statement
	 * @return
	 */
	public T get(Map<String, Object> paramsMap, String statement) {
		try {
			return commonDao.get(paramsMap, getPackageName("get"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询一个不带分页的集合
	 * @param paramsMap
	 * @return
	 */
	public List<T> queryListByMap(Map<String, Object> paramsMap) {
		try {
			return commonDao.queryListByMap(paramsMap, getPackageName("queryListByMap"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分页
	 * @param paramsMap
	 * @param support
	 * @return
	 * @throws ServiceException
	 */
	public Pager<T> queryPageByMap(Map<String, Object> paramsMap, PageSupport support) throws ServiceException {
		support.setTotalRecord(queryListByMap(paramsMap).size());
		paramsMap.put("startRow", support.getStartRow());
		paramsMap.put("size", support.getSize());
		List<T> list = commonDao.queryPageByMap(paramsMap, getPackageName("queryPageByMap"));
		Pager<T> pager = new Pager<T>(support);
		pager.setContent(list);
		return pager;
	}

}
