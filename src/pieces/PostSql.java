package com.hanborq.edrop.msc.atuosrc.pieces;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hanborq.edrop.msc.atuosrc.Env;
import com.hanborq.edrop.msc.atuosrc.FreeMarkerUtil;

public class PostSql {
	private static String testPath = Env.APPLICATION_REAL_PATH + "/../output/";
	public class PostInfo
	{
		public String key1="";
		public String key2="";
		public String key3="";
		public String key4="";
		public String key5="";
		
		public PostInfo(String key1, String key2, String key3, String key4,
				String key5) {
			super();
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
			this.key4 = key4;
			this.key5 = key5;
		}

		public String getKey1() {
			return key1;
		}

		public void setKey1(String key1) {
			this.key1 = key1;
		}

		public String getKey2() {
			return key2;
		}

		public void setKey2(String key2) {
			this.key2 = key2;
		}

		public String getKey3() {
			return key3;
		}

		public void setKey3(String key3) {
			this.key3 = key3;
		}

		public String getKey4() {
			return key4;
		}

		public void setKey4(String key4) {
			this.key4 = key4;
		}

		public String getKey5() {
			return key5;
		}

		public void setKey5(String key5) {
			this.key5 = key5;
		}
	}
	
	@Test
	public void postSql() throws Exception {

		List<PostInfo> list = new ArrayList<PostInfo>();
		list.add(new PostInfo("Email","email","Postemail","Email","email"));
		list.add(new PostInfo("Sms","tel","Postsms","Sms","sms"));
		list.add(new PostInfo("FailEmail","email","Postemailfail","Email","emailfail"));
		list.add(new PostInfo("FailSms","tel","Postsmsfail","Sms","smsfail"));
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("infos", list);

		File file = new File(testPath  + "postSql.java");
		FreeMarkerUtil.getInstance().createFile("postSql.ftl", root, file);
	}

}
