package com.hanborq.edrop.msc.atuosrc;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassInfo {
	private String key;
	private String className;
	private List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
	private String args;
	private String onlyArgs;
	private Class<?> clazz;

	public ClassInfo(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<MethodInfo> getMethodInfos() {
		return methodInfos;
	}

	public void setMethodInfos(List<MethodInfo> methodInfos) {
		this.methodInfos = methodInfos;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getOnlyArgs() {
		return onlyArgs;
	}

	public void setOnlyArgs(String onlyArgs) {
		this.onlyArgs = onlyArgs;
	}

	public String fir2Upper(String property) {
		String firChar = property.substring(0, 1);
		String upperChar = firChar.toUpperCase();
		String res = upperChar + property.substring(1);
		return res;
	}

	public String fir2Lower(String property) {
		String firChar = property.substring(0, 1);
		String lowerChar = firChar.toLowerCase();
		String res = lowerChar + property.substring(1);
		return res;
	}

	public void analyClassInfo() {
		Method[] methods = clazz.getMethods();
		Field[] field = clazz.getDeclaredFields();
		String fieldType = null;
		boolean beFirst = true;
		args="";
		onlyArgs = "";
		for (int i = 0; i < field.length; i++) {
			String name = null;
//			String childName = null;
//			boolean noNeed = false;
			fieldType = field[i].getGenericType().toString();
			name = field[i].getName();
			if(name.equals("bitField0_"))
			{
				continue;
			}
			name = name.replace("_", "");
			String methodName = fir2Upper(name);
			Method setMethod = null;
			for (Method method : methods) {
//				System.out.println(method.getName());
				if (method.getName().endsWith(methodName)&&method.getName().startsWith("set")) {
					setMethod = method;
					break;
				}
			}

			if (setMethod == null) {
				continue;
			}
//			System.out.println(fieldType+" "+name);
			Class<?> typeClass= setMethod.getParameterTypes()[0];
			String typeName = typeClass.toString();
			if(typeName.endsWith("class java.lang.String"))
			{
				typeName="String";
			}
			else if(typeName.contains("class"))
			{
				typeName=typeName.split("\\$")[1];
			}
			System.out.println(typeName+" "+name);
			if(name.equals("chunkList"))
			{
				continue;
			}
			MethodInfo methodInfo = new MethodInfo(setMethod.getName(), name);
			methodInfo.setType(typeName);
			methodInfos.add(methodInfo);
			if(!name.equals("hostId")&&!name.equals("userId"))
			{
				if(beFirst)
				{
					args+=typeName+" "+name;
					onlyArgs+=name;
					beFirst =false;
				}
				else
				{
					onlyArgs+=", "+name;
					args+=", "+typeName+" "+name;
				}
			}
		}
//		className = clazz.getSimpleName();
//		key = className;
	}

	@Override
	public String toString() {
		return "ClassInfo [key=" + key + ", className=" + className
				+ ", methodInfos=" + methodInfos + "]";
	}
}
