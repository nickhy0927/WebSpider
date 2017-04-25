package org.spring.platform.user.api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.spring.platform.user.entity.User;
import org.spring.platform.user.service.UserService;
import org.spring.platform.utils.MessageObject;
import org.spring.platform.utils.MessageObject.ResultCode;
import org.spring.platform.utils.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request, Model model) {
		String msg = "";
		String userName = request.getParameter("loginName");
		String password = request.getParameter("password");
		System.out.println("userName = " + userName + ",password = " + password);
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				return "index";
			} else {
				return "login";
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
			model.addAttribute("message", "登录密码错误");
			System.out.println(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";
			model.addAttribute("message", "登录失败次数过多");
			System.out.println(msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
			model.addAttribute("message", "帐号已被锁定");
			System.out.println(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
			model.addAttribute("message", "帐号已被禁用");
			System.out.println(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
			model.addAttribute("message", "帐号已过期");
			System.out.println(msg);
		} catch (UnknownAccountException e) {
			msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
			model.addAttribute("message", "帐号不存在");
			System.out.println(msg);
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！" + e.getMessage();
			model.addAttribute("message", "您没有得到相应的授权");
			System.out.println(msg);
		}
		return "login";
	}

	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	public void register(HttpServletRequest request, HttpServletResponse response) {
		MessageObject messageObject = MessageObject.getInstance();
		try {
			Map<String, Object> properties = RequestData.getRequestDataToMap(request);
			User user = new User();
			BeanUtils.populate(user, properties);
			user = userService.save(user);
			user.setPassword(null);
			messageObject.setObject(user);
			messageObject.setCode(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setCode(ResultCode.FAILIAR);
			messageObject.setMessage("注册失败，请稍候再试.");
		} finally {
			try {
				messageObject.returnData(response, messageObject);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/forwardLogin")
	public ModelAndView forwardLogin(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("login");
		// view.addObject("sessionId", request.getSession().getId());
		return view;
	}
}
