package org.spring.platform.user.api;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.spring.platform.user.entity.User;
import org.spring.platform.user.service.UserService;
import org.spring.platform.utils.MessageObject;
import org.spring.platform.utils.MessageObject.ResultCode;
import org.spring.platform.utils.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Scope("prototype")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public void login(HttpServletRequest request, HttpServletResponse response) {
        MessageObject messageObject = MessageObject.getInstance();
        String loginName = request.getParameter("loginname");
        String password = request.getParameter("password");
        try {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(loginName, password);
            subject.login(token);
            messageObject.setCode(ResultCode.SUCCESS);
        } catch (Exception e1) {
            e1.printStackTrace();
            messageObject.setCode(ResultCode.FAILIAR);
            messageObject.setMessage("用户名或密码错误.");
        } finally {
            try {
                messageObject.returnData(response, messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
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
//        view.addObject("sessionId", request.getSession().getId());
        return view;
    }
}
