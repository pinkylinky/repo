package com.tm.jdbc.dbtype;

import java.util.HashMap;
import java.util.Map;


public class DBDescriptorFactory {
	
	private static Map<DBType, DBDescriptor> map = new HashMap<DBType, DBDescriptor>();
	
	static {
		map.put(DBType.DB2, new DB2Descriptor());
		map.put(DBType.Derby, new DerbyDescriptor());
	}
	
	public static DBDescriptor getDBDescriptor(DBType dbType) {
		DBDescriptor desc = map.get(dbType);
		if (desc == null)
			throw new RuntimeException("Не найден дескриптор для БД " + dbType);
		return desc;
	}

}
