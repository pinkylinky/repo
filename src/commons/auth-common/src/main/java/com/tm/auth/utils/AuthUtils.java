package com.tm.auth.utils;

import javax.servlet.http.HttpServletRequest;

import com.tm.auth.entity.AuthPrincipal;

public class AuthUtils {
	
	private static final String AUTH = "AUTH";

	public static AuthPrincipal getAuthPrincipal(HttpServletRequest request) {
		String name = buildAuthPrincipalAttrName(request);
        return (AuthPrincipal) request.getSession().getAttribute(name);
	}
	
	public static void setAuthPrincipal(HttpServletRequest request, AuthPrincipal principal) {
		String name = buildAuthPrincipalAttrName(request);
		request.getSession().setAttribute(name, principal);
	}	
    
	public static String buildAuthPrincipalAttrName(HttpServletRequest request) {
    	return AUTH;
    }
	
	public static boolean isUserAuthenticated(HttpServletRequest request) {
    	AuthPrincipal principal = getAuthPrincipal(request);
    	return principal != null;
    }
	
	

}
