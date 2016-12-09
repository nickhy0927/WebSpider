package org.spring.common.utils;

import java.security.Key;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.spring.platform.user.entity.User;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class EndecryptUtils {

	private static String salt;

	public static String getSalt() {
		return salt;
	}

	public EndecryptUtils(String salt) {
		EndecryptUtils.salt = salt;
	}

	/**
	 * base64进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytBase64(String password) {
		Preconditions.checkArgument(!StringUtils.isNotEmpty(password), "不能为空");
		byte[] bytes = password.getBytes();
		return Base64.encodeToString(bytes);
	}

	/**
	 * base64进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptBase64(String cipherText) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
		return Base64.decodeToString(cipherText);
	}

	/**
	 * 16进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytHex(String password) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "不能为空");
		byte[] bytes = password.getBytes();
		return Hex.encodeToString(bytes);
	}

	/**
	 * 16进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptHex(String cipherText) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
		return new String(Hex.decode(cipherText));
	}

	public static String generateKey() {
		AesCipherService aesCipherService = new AesCipherService();
		Key key = aesCipherService.generateNewKey();
		return Base64.encodeToString(key.getEncoded());
	}

	public static User endecrptPassword(User user) {
		String username = user.getLoginname();
		String password = user.getPassword();
		Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "username不能为空");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "password不能为空");
		String password_cipherText = new Md5Hash(password, username + EndecryptUtils.salt, 2).toHex();
//		System.out.println("原始密码：" + password + ",加密的盐：" + EndecryptUtils.salt + "，加密后的密码：" + password_cipherText);
		user.setPassword(password_cipherText.toUpperCase());
		return user;
	}

}
