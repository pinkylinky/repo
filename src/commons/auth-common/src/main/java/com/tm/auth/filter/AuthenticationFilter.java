package com.tm.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tm.auth.exception.IncorrectLoginPasswordException;
import com.tm.auth.strategy.AuthStrategy;
import com.tm.auth.utils.AuthUtils;
import com.tm.auth.entity.AuthPrincipal;
import com.tm.utils.common.Logger;

/**
 * Абстрактный фильтр для аутентификации<br>
 */
public abstract class AuthenticationFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(AuthenticationFilter.class);

	@Override
	public final void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        boolean isAuth = isUserAuthenticated(request);	
        if (!isAuth) {

    		String login = getLogin(request);
    		String password = getPassword(request);
    		
    		if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
    			String path = request.getContextPath();
            	try {
            		AuthPrincipal principal = getAuthStrategy().authenticate(login, password);
            		if (principal == null) {
        				logger.info("[%s] Пользователь %s не аутентифицирован", path, login);
        			} else {
        				setUserAuthenticated(principal, request);
                		logger.info("[%s] Пользователь %s успешно аутентифицирован", path, login);
                		chain.doFilter(req, res);
        			}
            	} catch (IncorrectLoginPasswordException e) {
            		logger.info("[%s] Неверный логин или пароль для пользователя %s", path, login);
            		response.sendRedirect(getErrorPage());
            	} catch (Exception e) {
            		logger.error("[%s] Внутренняя ошибка при аутентификации пользователя %s", e, login, path);
            		response.sendRedirect(getErrorPage());
            	}
    		} else {
    			response.sendRedirect(getErrorPage());
    		}

        } else {
        	chain.doFilter(req, res);
        }
        
	}	

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
    private boolean isUserAuthenticated(HttpServletRequest request) {
    	AuthPrincipal principal = AuthUtils.getAuthPrincipal(request);
    	return principal != null;
    }
    
    private void setUserAuthenticated(AuthPrincipal principal, HttpServletRequest request) {
    	AuthUtils.setAuthPrincipal(request, principal);
    }
    
    public abstract String getLogin(HttpServletRequest request);
    
    public abstract String getPassword(HttpServletRequest request);
    
    public abstract String getLoginPage();
    
    public abstract String getErrorPage();
    
    public abstract AuthStrategy getAuthStrategy();

}
