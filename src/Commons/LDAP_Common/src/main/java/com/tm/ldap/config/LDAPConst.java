package com.tm.ldap.config;

public interface LDAPConst {
	
	//Connection
	public static final String NAMESPACE_LDAP = "ldap";
    public static final String PROPERTY_LDAP_HOST = "ldapHost";
    public static final String PROPERTY_LDAP_PORT = "ldapPort";
    public static final String PROPERTY_LDAP_BASE_DN = "baseDN";
    public static final String PROPERTY_LDAP_BIND_NAME = "bindName";
    public static final String PROPERTY_LDAP_BIND_PASSWORD = "bindPassword";
    public static final String PROPERTY_LDAP_USER_FILTER = "userFilter";
    public static final String PROPERTY_LDAP_USER_ID_MAP = "userIdMap";
    public static final String PROPERTY_LDAP_PROTOCOL = "ldapProtocol";
    
    //Attributes
    public static final String MEMBER_OF = "memberOf";    
    public static final String CN = "CN";
    public static final String DC = "DC";
    public static final String DISTINGUISHED_NAME = "distinguishedName";
    public static final String FIRST_NAME = "sn";
    public static final String LAST_NAME = "givenName";
    public static final String WHEN_CHANGED = "whenChanged";
    

}
