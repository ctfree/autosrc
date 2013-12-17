package com.hanborq.edrop.msc.atuosrc.pieces;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hanborq.edrop.msc.atuosrc.Env;
import com.hanborq.edrop.msc.atuosrc.FreeMarkerUtil;

public class EventType {
	private static String testPath = Env.APPLICATION_REAL_PATH + "/../output/";
	public static List<String> list = new ArrayList<String>();

	static {
		list.add("MKDIR");
		list.add("PUTFILE");
		list.add("MODIFY");
		list.add("RENAME");
		list.add("MOVE");
		list.add("COPY");
		list.add("DELETE");
		list.add("PURGE");
		list.add("RESTORE");
		list.add("RESTOREVERSION");

		list.add("SHARE");
		list.add("INVITE");
		list.add("ACCEPT");
		list.add("KICK");
		list.add("LEAVE");
		list.add("CANCEL");
		list.add("RESETACL");
		list.add("INVITEGROUP");
		list.add("KICKGROUP");
		list.add("RESETGROUPACL");

		list.add("FOLLOW");
		list.add("UNFOLLOW");

		list.add("SHAREFILE");
		list.add("INVITESHAREFILE");
		list.add("KICKSHAREFILE");
		list.add("CANCELSHAREFILE");
		list.add("INVITEGROUPSHAREFILE");
		list.add("KICKGROUPSHAREFILE");
		list.add("PUBLICSHAREFILE");
		list.add("CANCELPUBLICSHAREFILE");

		list.add("ARCHIVE");

		list.add("CREATECOSPACE");
		list.add("DROPCOSPACE");
		list.add("SUSPENDCOSPACE");
		list.add("RESTORECOSPACE");
	}
	
	
	@Test
	public void eventType() throws Exception {
		int i = 0;
		for (String string : list) {
			System.out.println("    EV_" + string + " = " + i + ";");
			i++;
		}		
	}
	
	@Test
	public void eventClass() throws Exception {
		list.clear();
		list.add("CREATECOSPACE");
		list.add("DROPCOSPACE");
		list.add("SUSPENDCOSPACE");
		list.add("RESTORECOSPACE");
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("types", list);

		File file = new File(testPath  + "Events.txt");
		FreeMarkerUtil.getInstance().createFile("event.ftl", root, file);
	}
	
	
	
	@Test
	public void eventCode() throws Exception {
		list.clear();
		list.add("CREATECOSPACE");
		list.add("DROPCOSPACE");
		list.add("SUSPENDCOSPACE");
		list.add("RESTORECOSPACE");
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("types", list);

		File file = new File(testPath  + "Events_code.txt");
		FreeMarkerUtil.getInstance().createFile("event_code.ftl", root, file);
	}
}
