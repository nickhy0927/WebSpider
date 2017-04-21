<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.hy.include" prefix="hy" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<hy:extends name="title">用户登录</hy:extends>
<hy:extends name="css">
    <style type="text/css">
        * {
            font-size: 30px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/wopop/style_log.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/wopop/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/wopop/userpanel.css">
</hy:extends>
<hy:extends name="javascript">
    <script type="text/javascript">
    </script>
</hy:extends>
<hy:extends name="body">
    <div>你好，已经测试成功了...</div>
    <div class="login" mycollectionplug="bind">
    <div class="login_m">
    <div class="login_logo"><img src="./Wopop_files/logo.png" width="196" height="46"></div>
    <div class="login_boder">
        <div class="login_padding" id="login_model">
            <h2>USERNAME</h2>
            <label>
                <input type="text" id="username" class="txt_input txt_input2" value="Your name">
            </label>
            <h2>PASSWORD</h2>
            <label>
                <input type="password" name="textfield2" id="userpwd" class="txt_input" value="******">
            </label>
            <p class="forgot"><a id="iforget" href="javascript:void(0);">Forgot your password?</a></p>
            <div class="rem_sub">
                <div class="rem_sub_l">
                    <input type="checkbox" name="checkbox" id="save_me">
                    <label for="save_me">Remember me</label>
                </div>
                <label>
                    <input type="submit" class="sub_button" name="button" id="button" value="SIGN-IN"
                           style="opacity: 0.7;">
                </label>
            </div>
        </div>

        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/">企业网站模板</a></div>

        <div id="forget_model" class="login_padding" style="display:none">
            <br>

            <h1>Forgot password</h1>
            <br>
            <div class="forget_model_h2">(Please enter your registered email below and the system will automatically
                reset users’ password and send it to user’s registered email address.)
            </div>
            <label>
                <input type="text" id="usrmail" class="txt_input txt_input2">
            </label>
            <div class="rem_sub">
                <div class="rem_sub_l">
                </div>
                <label>
                    <input type="submit" class="sub_buttons" name="button" id="Retrievenow" value="Retrieve now"
                           style="opacity: 0.7;">
                    <input type="submit" class="sub_button" name="button" id="denglou" value="Return"
                           style="opacity: 0.7;">　　
                </label>
            </div>
        </div>
    </div>
</hy:extends>
<jsp:include page="/page/basepage.jsp"></jsp:include>