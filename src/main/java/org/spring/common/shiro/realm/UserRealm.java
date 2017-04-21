package org.spring.common.shiro.realm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.spring.common.utils.EndecryptUtils;
import org.spring.platform.user.entity.User;
import org.spring.platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	private String username;
	private String password;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(user.getId()));
		authorizationInfo.setStringPermissions(userService.findPermissions(user.getId()));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> pramasMap = new HashMap<String, Object>();
		pramasMap.put("loginname", username);
		System.out.println(this.username);
		String password = new String((char[]) token.getCredentials());
		User user = userService.findByUsernameAndPassword(username, password);
		if (user == null) 
			throw new UnknownAccountException();// 没找到帐号
		if (Boolean.TRUE.equals(user.getLocked())) 
			throw new LockedAccountException(); // 帐号锁定
		String password_cipherText = new Md5Hash(password, username + EndecryptUtils.getSalt(), 2).toHex();
		if (!password_cipherText.equals(password)) // 密码不正确
			throw new IncorrectCredentialsException("用户密码错误");
		
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(username + EndecryptUtils.getSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

	// 重写权限判断方法，加入正则判断
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		AuthorizationInfo info = getAuthorizationInfo(principals);
		Collection<String> permissions = info.getStringPermissions();
		return permissions.contains(permission) || patternMatch(permissions, permission);
	}

	/**
	 * 正则
	 * 
	 * @param patternUrlList
	 * @param requestUri
	 * @return
	 */
	public boolean patternMatch(Collection<String> patternUrlList, String requestUri) {
		boolean flag = false;
		for (String patternUri : patternUrlList) {
			if (StringUtils.isNotEmpty(patternUri)) {
				Pattern pattern = Pattern.compile(patternUri);
				Matcher matcher = pattern.matcher(requestUri);
				System.out.println("matcher.matches() :" + matcher.matches());
				if (matcher.matches()) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}