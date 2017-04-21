<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.hy.include" prefix="hy" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<hy:extends name="title">用户登录</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/wopop/style_log.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/wopop/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/wopop/userpanel.css">
</hy:extends>
<hy:extends name="javascript">
    <script type="text/javascript">
    </script>
</hy:extends>
<hy:extends name="body">
    <div class="login_m">
	    <div class="login_logo">
	    	<img src="${ctx}/static/wopop/logo.png" width="196" height="46">
	    </div>
	    <div class="login_boder">
	        <div class="login_padding" id="login_model">
	            <h2>登录账户</h2>
	            <label>
	                <input type="text" id="loginName" name="loginName" class="txt_input txt_input2" value="" placeholder="电话号码/邮箱/注册账号">
	            </label>
	            <h2>登录密码</h2>
	            <label>
	                <input type="password" name="password" id="password" class="txt_input" value="" placeholder="账户密码">
	            </label>
	            <p class="forgot">
	            	<a id="iforget" href="javascript:void(0);">忘记密码?</a>
	            </p>
	            <div class="rem_sub">
	                <div class="rem_sub_l">
	                    <input type="checkbox" name="checkbox" id="save_me" style="vertical-align: middle;">
	                    <label for="save_me">记住我</label>
	                </div>
	                <label>
	                    <input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;">
	                </label>
	            </div>
	        </div>
	    </div>
    </div>
</hy:extends>
<jsp:include page="/page/basepage.jsp"></jsp:include>