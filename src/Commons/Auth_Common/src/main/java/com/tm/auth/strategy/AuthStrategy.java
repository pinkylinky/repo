package com.tm.auth.strategy;

import com.tm.auth.entity.AuthPrincipal;
import com.tm.auth.exception.AuthException;

public interface AuthStrategy  {

	AuthPrincipal authenticate(String login, String password) throws AuthException; 

}
