package com.spring.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.common.utils.EndecryptUtils;
import org.spring.common.utils.PageSupport;
import org.spring.common.utils.Pager;
import org.spring.platform.user.entity.User;
import org.spring.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class StartTest {

	@Autowired
	private UserService userService;

	@Test
	public void start() {
		User user = null;
		for (int i = 0; i < 1000; i++) {
			user = new User();
			user.setLocked(Boolean.FALSE);
			Integer n = (int) Math.floor(Math.random() * 1000000000);
			String username = String.valueOf(n);
			user.setLoginName(username);
			user.setPassword(username);
			user.setRealName(username);
			user.setEmail("h_y_12@163.com");
			user.setPhone("13299999999");
			user.setBorthday(new Date());
			user = EndecryptUtils.endecrptPassword(user);
			user = userService.save(user);
			System.out.println("id : " + user.getId());
		}
	}

	@Test
	public void queryPage() {
		PageSupport support = new PageSupport(1);
		Map<String, Object> paramsMap = new HashMap<>();
		Pager<User> pager = userService.queryPageByMap(paramsMap, support);
		System.out.println(pager);
	}

}
