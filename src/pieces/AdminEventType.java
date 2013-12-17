package com.hanborq.edrop.msc.atuosrc.pieces;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.math.util.OpenIntToDoubleHashMap.Iterator;
import org.junit.Test;

import com.hanborq.edrop.msc.atuosrc.Env;
import com.hanborq.edrop.msc.atuosrc.FreeMarkerUtil;

public class AdminEventType {
	private static String testPath = Env.APPLICATION_REAL_PATH + "/../output/";
	public static List<String> list = new ArrayList<String>();

	static {
		
		
		
		list.add("USER_NEW");
		list.add("USER_SUSPEND");
		list.add("USER_DELETE");
		list.add("USER_RESTORE");	
		list.add("USER_MODIFYQUOTA");
		list.add("USER_MODIFYACL");
		
		
		list.add("");
		list.add("DEPT_NEW");
		list.add("DEPT_DELETE");
		list.add("DEPT_ADD_USER");
		list.add("DEPT_DELETE_USER");
		
		list.add("");
		list.add("GORUP_NEW");
		list.add("GORUP_DELETE");
		list.add("GORUP_ADD_USER");
		list.add("GORUP_DELETE_USER");
		
		list.add("");
		list.add("COSPACE_NEW");
		list.add("COSPACE_SUSPEND");
		list.add("COSPACE_DELETE");
		list.add("COSPACE_RESTORE");	
		list.add("COSPACE_MODIFYQUOTA");
		list.add("COSPACE_MODIFYMANAGER");

		list.add("");
		list.add("ENTERPRISE_CONFIG");
	}
	
	
	@Test
	public void eventType() throws Exception {
		int i = 0;
		for (String string : list) {
			if(string.isEmpty())
			{
				System.out.println("");
				continue;
			}
			System.out.println("    EV_" + string + " = " + i + ";");
			i++;
		}		
	}
	
	@Test
	public void eventClass() throws Exception {

		ListIterator<String> it = list.listIterator();
		while(it.hasNext())
		{
			if(it.next().isEmpty())
			{
				it.remove();
			}
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("types", list);

		File file = new File(testPath  + "AdminEvents.txt");
		FreeMarkerUtil.getInstance().createFile("admin_event.ftl", root, file);
	}
	
	@Test
	public void eventModel() throws Exception {

		ListIterator<String> it = list.listIterator();
		while(it.hasNext())
		{
			if(it.next().isEmpty())
			{
				it.remove();
			}
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("types", list);

		File file = new File(testPath  + "AdminEventModeltxt");
		FreeMarkerUtil.getInstance().createFile("admin_event_model.ftl", root, file);
	}
	
	
	
	@Test
	public void eventCode() throws Exception {
		ListIterator<String> it = list.listIterator();
		while(it.hasNext())
		{
			if(it.next().isEmpty())
			{
				it.remove();
			}
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("types", list);

		File file = new File(testPath  + "AdminEvents_code.txt");
		FreeMarkerUtil.getInstance().createFile("event_code.ftl", root, file);
	}
}
