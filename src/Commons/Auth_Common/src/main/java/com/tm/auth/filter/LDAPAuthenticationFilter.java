package com.tm.auth.filter;

import javax.servlet.http.HttpServletRequest;
import com.tm.auth.strategy.AuthStrategy;
import com.tm.auth.strategy.LDAPAuthStrategy;
import com.tm.auth.strategy.LDAPConfigurationHolder;
import com.tm.ldap.config.LDAPConfiguration;

/**
 * Фильтр для аутентификации в LDAP.<br>
 * Для использования нужно перед вызовом фильтра
 * проинициализировать конфигурацию LDAP с помощью метода<br>
 * <pre>LDAPConfigurationHolder.initConfig(..)</pre>
 *  
 */
public class LDAPAuthenticationFilter extends AuthenticationFilter {
	
	private static final String J_USERNAME = "j_username";
	private static final String J_PASSWORD = "j_password";
	
	private static final String LOGIN_PAGE = "login.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private AuthStrategy authStrategy;
    
    @Override
	public String getLogin(HttpServletRequest request) {
    	return request.getParameter(J_USERNAME);
    }
    
    @Override
	public String getPassword(HttpServletRequest request) {
    	return request.getParameter(J_PASSWORD);
    }
    
    @Override
	public String getLoginPage() {
    	return LOGIN_PAGE;
    }
    
    @Override
	public String getErrorPage() {
    	return ERROR_PAGE;
    }
    
    @Override
	public AuthStrategy getAuthStrategy() {
    	if (authStrategy == null) {
    		LDAPConfiguration ldapConfig = LDAPConfigurationHolder.getConfig();
    		authStrategy = new LDAPAuthStrategy(ldapConfig);
    	}		
    	return authStrategy;
    }

}
