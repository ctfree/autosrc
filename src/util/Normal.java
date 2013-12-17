package com.hanborq.edrop.msc.atuosrc.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Normal {

	
	public static List<String> getMethodNames(Class clazz)
	{
		List<String> nameList = new ArrayList<String>();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			nameList.add(method.getName());
		}
		return nameList;
	}
}
