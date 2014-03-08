package com.tm.utils.common;

import java.util.Collection;

public class CollectionUtils {
	
	public static boolean isCollectionNullOrEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

}
