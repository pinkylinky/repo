package com.tm.utils.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ReflectionUtils {
	
	public static List<FieldDescriptor> getFields(Class<?> clazz) {
		Field[] fields = getDeclaredFields(clazz);
		List<FieldDescriptor> list = new ArrayList<FieldDescriptor>();
		for (Field field : fields) {
			//field.setAccessible(true);
			list.add(new FieldDescriptor(field.getName(), field.getType()));
		}
		return list;
	}
	
	public static List<FieldDescriptor> getFieldValues(Object obj) {
		Field[] fields = getDeclaredFields(obj.getClass());
		List<FieldDescriptor> list = new ArrayList<FieldDescriptor>();
		for (Field field : fields) {
			field.setAccessible(true);
			FieldDescriptor desc = new FieldDescriptor(field.getName(), field.getType());
			try {
				desc.setValue(field.get(obj));
				list.add(desc);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return list;
	}
	
	public static void setField(String fieldName, Object value, Object obj) {
		Field field;
		try {
			field = getDeclaredField(obj.getClass(), fieldName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Field[] getDeclaredFields(Class clazz) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		addPrimitives(list, fields);
		fields = clazz.getSuperclass().getDeclaredFields();
		addPrimitives(list, fields);
		return list.toArray(new Field[]{});
	}
	
	public static Field getDeclaredField(Class clazz, String fieldName) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			try {
				field = clazz.getSuperclass().getDeclaredField(fieldName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return field;
	}
	
	public static Object convertValue(FieldDescriptor field, Object value) {
		Class<?> clazz = field.getClazz();
		Class<?> valueClass = value.getClass();
		if (clazz == Long.class && valueClass == Integer.class) {
			return ((Integer) value).longValue();
		} else if (clazz == Integer.class && valueClass == Long.class) {
			return ((Long) value).intValue();
		}{
			
		}
		return value;
	}
	
	private static void addPrimitives(List<Field> list, Field[] fields) {
		for (Field field : fields) {
			Class<?> clazz = field.getType();
			if (clazz.isPrimitive() 
					|| clazz == String.class 
					|| clazz == Long.class 
					|| clazz == Integer.class 
					|| clazz == Date.class 
					)
			list.add(field);
		}
	}
	
	public static class FieldDescriptor {
		private String name;
		private Class clazz;
		private Object value;
		
		public FieldDescriptor(String name, Class clazz) {
			super();
			this.name = name;
			this.clazz = clazz;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Class getClazz() {
			return clazz;
		}

		public void setClazz(Class clazz) {
			this.clazz = clazz;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
		
		
	}

}
