package com.tm.ldap.config;

public class LDAPConfiguration {
	
	private String ldapUrl;
	private String baseDN;
	private String bindName;
	private String bindPassword;
	private String userFilter;
	private String userIdMap;
	
	
	/**
	 * Конфигурация LDAP-сервера.
	 * Описание параметров baseDN,bindName,bindPassword,userFilter,userIdMap
	 * приведено в расширенных настройках LDAP User Registry в IBM Websphere 7.0
	 * 
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
	 */
	public LDAPConfiguration(String ldapUrl, String baseDN, String bindName,
			String bindPassword, String userFilter, String userIdMap) {
		super();
		this.ldapUrl = ldapUrl;
		this.baseDN = baseDN;
		this.bindName = bindName;
		this.bindPassword = bindPassword;
		this.userFilter = userFilter;
		this.userIdMap = userIdMap;
	}

	public String getLdapUrl() {
		return ldapUrl;
	}

	public void setLdapUrl(String ldapUrl) {
		this.ldapUrl = ldapUrl;
	}

	public String getBaseDN() {
		return baseDN;
	}

	public void setBaseDN(String baseDN) {
		this.baseDN = baseDN;
	}

	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	public String getBindPassword() {
		return bindPassword;
	}

	public void setBindPassword(String bindPassword) {
		this.bindPassword = bindPassword;
	}

	public String getUserFilter() {
		return userFilter;
	}

	public void setUserFilter(String userFilter) {
		this.userFilter = userFilter;
	}

	public String getUserIdMap() {
		return userIdMap;
	}

	public void setUserIdMap(String userIdMap) {
		this.userIdMap = userIdMap;
	}
	
	

}
