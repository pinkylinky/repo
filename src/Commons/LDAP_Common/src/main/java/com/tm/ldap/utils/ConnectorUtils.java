package com.tm.ldap.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.tm.ldap.config.LDAPConfiguration;
import com.tm.ldap.exception.LDAPException;
import com.tm.ldap.exception.LDAPIncorrectLoginPassword;
import com.tm.ldap.utils.StringUtils;
import com.tm.logger.utils.Logger;

public class ConnectorUtils {

	private static Logger logger = Logger.getLogger(ConnectorUtils.class);
	
	public static Attributes authenticate(String login, String password, LDAPConfiguration config) throws LDAPException {
		return authenticate(login, password, config.getLdapUrl(), config.getBaseDN(), config.getBindName(),
				config.getBindPassword(), config.getUserFilter(), config.getUserIdMap());
	}
	
	/**
	 * Аутентифицирует пользователя в LDAP сервере по входным параметрам.
	 * Описание параметров baseDN,bindName,bindPassword,userFilter,userIdMap
	 * приведено в расширенных настройках LDAP User Registry в IBM Websphere 7.0
	 * 
	 * @param login
	 *            - логин пользователя
	 * @param password
	 *            - пароль пользователя
	 * @param ldapUrl
	 *            - адрес LDAP-сервера вида "ldap://{host}:{port}"
	 * @param baseDN
	 *            - начальная директория для поиска, к примеру dc=company,dc=com
	 * @param bindName
	 *            - имя пользователя для первоначального подключения к
	 *            LDAP-серверу, заданное относительно baseDN, к примеру CN=LDAP
	 *            Admin,cn=Users
	 * @param bindPassword
	 *            - пароль для пользователя bindName
	 * @param userFilter
	 *            - заданый фильтр для пользователей
	 * @param userIdMap
	 *            - маппинг типа объекта пользователя на атрибут, являющийся
	 *            уникальным идентификатором пользователя
	 * @return
	 * @throws LDAPException 
	 */
	public static Attributes authenticate(String login, String password,
			String ldapUrl, String baseDN, String bindName,
			String bindPassword, String userFilter, String userIdMap) throws LDAPException {
//		boolean result = false;
		InitialDirContext ctx = null;
		
		if (StringUtils.isBlank(login) || StringUtils.isBlank(password))
			throw new LDAPException("Не задан логин или пароль");
		
		if (StringUtils.isBlank(ldapUrl)
				|| baseDN == null
				|| StringUtils.isBlank(bindName)
				|| StringUtils.isBlank(bindPassword)
				|| StringUtils.isBlank(userIdMap)) {
			throw new LDAPException("Не заданы параметры сервера LDAP");
		}
		try {
			ctx = createContext(ldapUrl, bindName, bindPassword);

			String[] userMapArray = userIdMap.split("\\s*;\\s*");
			for (int i = 0; i < userMapArray.length; i++) {
				logger.debug("use userIDMap=" + userMapArray[i]
						+ " to attempt to authenticate...");
				String[] temp = userMapArray[i].split("\\s*:\\s*");
				String userObjectClass = temp[0];
				String userIdAttribute = temp[1];
				logger.debug("userObjectClass defined as=" + userObjectClass);
				logger.debug("userIdAttribute defined as=" + userIdAttribute);
				if (StringUtils
						.isBlank(userObjectClass)
						|| StringUtils
								.isBlank(userIdAttribute)) {
					logger.debug("incorrect user ID Map");
					continue;
				}
				// Create the search controls
				SearchControls searchCtls = new SearchControls();
				// Specify the attributes to return
				//String returnedAtts[] = { userIdAttribute };
				//searchCtls.setReturningAttributes(returnedAtts);
				// Specify the search scope
				searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
				// specify the LDAP search filter
				String searchFilter = buildResultUserFilter(login, userFilter,
						userIdAttribute, userObjectClass);
				// Specify the Base for the search
				String searchBase = baseDN;
				// Loop through the search results
				String userId = null;
				String userDN = null;
				// Search for objects using the filter
				logger.debug("searching...");
				NamingEnumeration answer = ctx.search(searchBase, searchFilter,
						searchCtls);
				while (answer.hasMoreElements()) {
					SearchResult sr = (SearchResult) answer.next();
					Attributes attrs = sr.getAttributes();
					Attribute attr = attrs.get(userIdAttribute);
					userId = (String) attr.get();
					logger.debug("userId defined as=" + userId);
					userDN = sr.getNameInNamespace();
					logger.debug("userDN defined as=" + userDN);
					if (StringUtils.isNotBlank(userDN)) {
						if (checkCredentials(ldapUrl, userDN, password)) {
							return attrs;
						} else {
							throw new LDAPIncorrectLoginPassword();
						}
					} else {
						logger.debug("userDN is blank");
					}
					logger.debug("going to next user in search result...");
				}
				logger.debug("going to next userMap...");
			}
		} catch (NamingException e) {
			throw new LDAPException("Ошибка обработки данных из LDAP-хранилища", e);
		} finally {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					logger.error("Ошибка закрытия контекста подключения к LDAP=" + e.getMessage());
				}
			}
		}
		throw new LDAPException("Не удалось аутентифицировать пользователя");
	}
	
	public static Attributes getUserAttributes(String login, LDAPConfiguration config) throws LDAPException {
		
		InitialDirContext ctx = null;
		
		if (StringUtils.isBlank(login))
			throw new LDAPException("Не задан логин");
		
		String ldapUrl = config.getLdapUrl();
		String bindName = config.getBindName();
		String bindPassword = config.getBindPassword();
		String baseDN = config.getBaseDN();
		String userIdMap = config.getUserIdMap();
		
		if (StringUtils.isBlank(ldapUrl)
				|| baseDN == null
				|| StringUtils.isBlank(bindName)
				|| StringUtils.isBlank(bindPassword)
				|| StringUtils.isBlank(userIdMap)) {
			throw new LDAPException("Не заданы параметры сервера LDAP");
		}
		try {
			ctx = createContext(ldapUrl, bindName, bindPassword);

			String[] userMapArray = userIdMap.split("\\s*;\\s*");
			for (int i = 0; i < userMapArray.length; i++) {
				logger.debug("use userIDMap=" + userMapArray[i]
						+ " to attempt to authenticate...");
				String[] temp = userMapArray[i].split("\\s*:\\s*");
				String userObjectClass = temp[0];
				String userIdAttribute = temp[1];
				logger.debug("userObjectClass defined as=" + userObjectClass);
				logger.debug("userIdAttribute defined as=" + userIdAttribute);
				if (StringUtils
						.isBlank(userObjectClass)
						|| StringUtils
								.isBlank(userIdAttribute)) {
					logger.debug("incorrect user ID Map");
					continue;
				}
				// Create the search controls
				SearchControls searchCtls = new SearchControls();
				// Specify the attributes to return
				//String returnedAtts[] = { userIdAttribute };
				//searchCtls.setReturningAttributes(returnedAtts);
				// Specify the search scope
				searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

				// specify the LDAP search filter
				String userFilter = config.getUserFilter();
				String searchFilter = buildResultUserFilter(login, userFilter,
						userIdAttribute, userObjectClass);
				// Specify the Base for the search
				String searchBase = baseDN;
				// Loop through the search results
				// Search for objects using the filter
				logger.debug("searching...");
				NamingEnumeration answer = ctx.search(searchBase, searchFilter,
						searchCtls);
				while (answer.hasMoreElements()) {
					SearchResult sr = (SearchResult) answer.next();
					Attributes attrs = sr.getAttributes();
					return attrs;
				}
				logger.debug("going to next userMap...");
			}
		} catch (NamingException e) {
			throw new LDAPException("Ошибка обработки данных из LDAP-хранилища", e);
		} finally {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					logger.error("Ошибка закрытия контекста подключения к LDAP=" + e.getMessage());
				}
			}
		}
		return null;
	}
	
	/**
	 * Поиск в контексте LDAP
	 * @param ctx
	 * @param searchBase
	 * @param searchFilter
	 * @param searchScope
	 * 				- скоуп поиска (напр. SearchControls.SUBTREE_SCOPE)
	 * @throws NamingException 
	 * @throws LDAPException 
	 */
	public static Attributes searchFirst(InitialDirContext ctx, String searchBase, 
			String searchFilter, int searchScope) throws NamingException {
		
		NamingEnumeration results = search(ctx, searchBase, searchFilter, searchScope);
		while (results.hasMoreElements()) {
			SearchResult sr = (SearchResult) results.next();
			return sr.getAttributes();
		}
		return null;
	}
	
	public static Attributes searchFirst(InitialDirContext ctx, String searchBase, 
			String searchFilter) throws NamingException {
		return searchFirst(ctx, searchBase, searchFilter, SearchControls.SUBTREE_SCOPE);
	}
	
	public static NamingEnumeration search(InitialDirContext ctx, String searchBase, 
			String searchFilter, int searchScope) throws NamingException {
		
		SearchControls searchCtls = new SearchControls();
		searchCtls.setSearchScope(searchScope);
	
		return ctx.search(searchBase, searchFilter, searchCtls);
	}
	
	/**
	 * Собирает фильтр отбора пользователей для проверки. Создает дополнительный
	 * фильтр на базе userIdAttribute и userObjectClass и добавляет его к
	 * входящему фильтру через условие AND. Если userFilter не определен, то
	 * используется только дополнительный фильтр
	 * 
	 * @param login
	 *            - логин пользователя
	 * @param userFilter
	 *            - определенный настройками безопасности фильтр
	 * @param userIdAttribute
	 *            - атрибут, являющийся уникальным идентификатором пользователя
	 * @param userObjectClass
	 *            - тип объекта пользователя
	 * @return - итоговый фильтр.
	 */
	public static String buildResultUserFilter(String login, String userFilter,
			String userIdAttribute, String userObjectClass) {
		userFilter = userFilter.replaceAll("\\%v", login);
		StringBuffer constCriteria = new StringBuffer();
		constCriteria.append("(");
		constCriteria.append(userIdAttribute);
		constCriteria.append("=");
		constCriteria.append(login);
		constCriteria.append(")");
		if (!"*".equals(userObjectClass)) {
			constCriteria.insert(0, "(&");
			constCriteria.append("(objectClass=");
			constCriteria.append(userObjectClass);
			constCriteria.append("))");
		}
		String consFilter = constCriteria.toString();

		String resultFilter = null;
		if (StringUtils.isBlank(userFilter)) {
			resultFilter = consFilter;
		} else {
			StringBuffer resultFilterBuffer = new StringBuffer();
			resultFilterBuffer.append("(&");
			resultFilterBuffer.append(consFilter);
			resultFilterBuffer.append(userFilter);
			resultFilterBuffer.append(")");
			resultFilter = resultFilterBuffer.toString();
		}
		return resultFilter;
	}
	
	/**
	 * @param resultLDAPUrl
	 * @param userDN
	 * @param password
	 * @return
	 * @throws NamingException
	 */
	private static boolean checkCredentials(String resultLDAPUrl, String userDN,
			String password) {
		InitialDirContext userCtx = null;
		logger.info("checkCredential in ldap=" + resultLDAPUrl + " for user="
				+ userDN);
		try {
			Hashtable<String, String> userEnv = new Hashtable<String, String>();
			userEnv.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			userEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			userEnv.put(Context.PROVIDER_URL, resultLDAPUrl);
			userEnv.put(Context.SECURITY_PRINCIPAL, userDN);
			userEnv.put(Context.SECURITY_CREDENTIALS, password);
			userCtx = new InitialDirContext(userEnv);
			logger.info("checkCredential in ldap=" + resultLDAPUrl
					+ " for user=" + userDN + " successfull");
			return true;
		} catch (NamingException e) {
			logger.error("checkCredential in ldap=" + resultLDAPUrl
					+ " for user=" + userDN + " failed with error message="
					+ e.getMessage());
			return false;
		} finally {
			if (userCtx != null) {
				try {
					userCtx.close();
				} catch (NamingException e) {
					logger.error("Ошибка закрытия контекста подключения к LDAP="
							+ e.getMessage());
				}
			}
		}
	}
	
	public static InitialDirContext createContext(String ldapUrl, String bindName, String bindPassword) throws NamingException {
		InitialDirContext ctx = null;
		logger.info("Creating context in ldap=" + ldapUrl + " for bindName=" + bindName);
		Hashtable<String, String> initialEnv = new Hashtable<String, String>();			
		initialEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		initialEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		initialEnv.put(Context.PROVIDER_URL, ldapUrl);
		initialEnv.put(Context.SECURITY_PRINCIPAL, bindName);
		initialEnv.put(Context.SECURITY_CREDENTIALS, bindPassword);

		ctx = new InitialDirContext(initialEnv);
		logger.info("Created context in ldap=" + ldapUrl + " for bindName=" + bindName + " sucessfully");
		return ctx;
	}

}

