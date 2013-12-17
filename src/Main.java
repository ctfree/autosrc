package com.hanborq.edrop.msc.atuosrc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hanborq.edrop.msc.atuosrc.util.Normal;
import com.hanborq.edrop.msg.EventMsg;
import com.hanborq.edrop.msg.FileMsg;
import com.hanborq.edrop.msg.ShareMsg;
import com.hanborq.edrop.msg.UserMsg;

import freemarker.template.TemplateException;

public class Main {
	private static List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
	private static String testPath = Env.APPLICATION_REAL_PATH + "/../output/";
	private static String workPath = Env.APPLICATION_REAL_PATH + "/../../test/op/";
	private static Map<String, String> argrs = new HashMap<String, String>();

	private static String filter = null;
	private static List<String> filterList = new ArrayList<String>();
	
	public static boolean checkNeed(String name)
	{
		if(filterList.size()!=0)
		{
			for (String filter : filterList) {
				if(name.toLowerCase().contains(filter.toLowerCase()))
				{
					return true;
				}
			}
			return false;
		}
		if(filter!=null)
		{
			if(name.toLowerCase().contains(filter.toLowerCase()))
			{
				return true;
			}
			return false;
		}
		return true;
	}
	
	
	
	public static Map<String, Object> getInfos(Class<?> clazz, String calssName)
	{
		classInfoList.clear();
		argrs.clear();
		Class<?>[] ss = clazz.getClasses();
		for (Class<?> class1 : ss) {
			if (class1.isInterface()) {
				continue;
			}
			if (class1.getSimpleName().startsWith("Resp")) {
				continue;
			}

			ClassInfo classInfo = new ClassInfo(class1.getClasses()[0]);
			classInfo.analyClassInfo();
			classInfo.setClassName(class1.getSimpleName());
			classInfo.setKey(class1.getSimpleName().substring(3));
			System.out.println(classInfo);
			if(!checkNeed(classInfo.getClassName()))
			{
				continue;
			}
			
			classInfoList.add(classInfo);
		}
		for (ClassInfo classInfo : classInfoList) {
			for (MethodInfo method1 : classInfo.getMethodInfos()) {
				argrs.put(method1.getParam(), method1.getType());
			}
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("classInfoList", classInfoList);
		root.put("args", argrs);
		root.put("className", calssName);
		root.put("parentClassName", clazz.getSimpleName());
		return root;
	}
	
	public static void createFile(Class<?> clazz, String calssName) throws IOException, TemplateException {
		Map<String, Object> root =getInfos(clazz, calssName);
		File file = new File(testPath + calssName + ".java");
		FreeMarkerUtil.getInstance().createFile("NativeServer.ftl", root, file);
	}
	
	
	
	public static void createArgs(Class<?> clazz, String calssName) throws IOException, TemplateException {
		Map<String, Object> root =getInfos(clazz, calssName);
		File file = new File(testPath + calssName + "_args.java");
		FreeMarkerUtil.getInstance().createFile("args.ftl", root, file);
	}

	public static void createMas(Class<?> clazz, String calssName) throws IOException, TemplateException {		
		Map<String, Object> root =getInfos(clazz, calssName);
		for (ClassInfo  info : classInfoList) {
			Map<String, Object> root1= new HashMap<String, Object>();
		    root1.put("key", info.getKey());
		    root1.put("parentClassName", root.get("parentClassName"));
		    root1.put("classInfo", info);
			File file = new File(testPath + info.getKey() + "Task.java");
			FreeMarkerUtil.getInstance().createFile("mas.ftl", root1, file);
		}
	}
	
	public static void createOthers(Class<?> clazz, String calssName) throws IOException, TemplateException {
		Map<String, Object> root =getInfos(clazz, calssName);
		File file = new File(testPath + calssName + "_others.java");
		FreeMarkerUtil.getInstance().createFile("Others.ftl", root, file);
	}
	
	
	public static void createtTask(Class<?> clazz, String calssName) throws IOException, TemplateException {
		Map<String, Object> root =getInfos(clazz, calssName);
		for (ClassInfo  info : classInfoList) {
			Map<String, Object> root1= new HashMap<String, Object>();
		    root1.put("key", info.getKey());
		    root1.put("parentClassName", root.get("parentClassName"));
		    root1.put("classInfo", info);
			File file = new File(testPath + info.getKey() + "Task.java");
			FreeMarkerUtil.getInstance().createFile("task.ftl", root1, file);
		}

	}
	
	public static void createClientTest(Class<?> clazz, String calssName) throws IOException, TemplateException {
		List<String> methodNames =Normal.getMethodNames(com.hanborq.edrop.msc.client.MetaServerClient.class);
		filterList.addAll(methodNames);
		Map<String, Object> root =getInfos(clazz, calssName);
		File file = new File(testPath + calssName + ".java");
		FreeMarkerUtil.getInstance().createFile("clientTest1.ftl", root, file);
	}
	
	
	public static void createTestPy(Class<?> clazz, String calssName) throws IOException, TemplateException {
		List<String> methodNames =Normal.getMethodNames(com.hanborq.edrop.msc.client.MetaServerClient.class);
		filterList.addAll(methodNames);
		Map<String, Object> root =getInfos(clazz, calssName);
		File file = new File(testPath + calssName + ".java");
		FreeMarkerUtil.getInstance().createFile("testPy.ftl", root, file);
	}
	
	
	public static void main(String[] args) throws IOException,
			TemplateException {
//		createFile(FileMsg.class,"FileOp");
//		createFile(ShareMsg.class,"ShareOp");
//		createFile(UserMsg.class,"UserOp");
//		createFile(EventMsg.class,"EventOp");
//		
//		createArgs(ShareMsg.class,"ShareOp");
		
//		createArgs(UserMsg.class,"UserOp");
		
//		createOthers(UserMsg.class,"UserOp");
//		createOthers(ShareMsg.class,"ShareOp");
		
		
//		filter="modifyuserconfig";
//		createClientTest(UserMsg.class,"UserOp");
//		createClientTest(ShareMsg.class,"ShareOp");
//		createClientTest(FileMsg.class,"FileOp");
		
		
//		//py
//		createTestPy(FileMsg.class,"FileOp");
//		createTestPy(UserMsg.class,"UserOp");
		
		
		
		
////		share
//		filter="resetcospacequota";
//		filterList.add("resetcospacequota");
		filterList.add("modifyuseracl");
		Class clazz = UserMsg.class;
		String opName= "Op";
		createFile(clazz,opName);
		createtTask(clazz,opName);
		createOthers(clazz,opName);
//		createMas(clazz,opName);
		
		
		
	
	}

}
