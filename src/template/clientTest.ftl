package com.hanborq.edrop.msc.client.test;

import org.apache.log4j.Logger;

import com.hanborq.edrop.msc.client.MetaServerClient;
import com.hanborq.edrop.msg.Data.GroupId;
import com.hanborq.edrop.msg.Data.UserId;
<#list classInfoList as classInfo>
import com.hanborq.edrop.msg.${parentClassName}.${classInfo.className};
import com.hanborq.edrop.msg.${parentClassName}.Resp${classInfo.key};
</#list>

public class MetaServerTest {

    private static Logger LOG = Logger.getLogger(MetaServerTest.class);
    
    private static MetaServerTest instance = null;
    private static Object LOCK = new Object();
    private String hostId = "Test";
	protected UserId userId;
	
    private MetaServerClient client = null;
    
    public static MetaServerTest getInstance() {
        if (instance == null) {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = new MetaServerTest();
                }
            }
        }
        return instance;
    }
    
    private MetaServerTest() {
        client = MetaServerClient.getInstance();
    }
	
<#list classInfoList as classInfo>
	public Resp${classInfo.key} ${classInfo.key}Task(${classInfo.args}) throws Exception{
		${classInfo.className}.Builder msgBody = ${classInfo.className}.newBuilder();
        <#list classInfo.methodInfos as methodInfo>
        msgBody.${methodInfo.name}(${methodInfo.param});
        </#list>
        
        TestBlockHandler handler = new TestBlockHandler();
        client.${classInfo.key?uncap_first}(msgBody.build(), handler);
        handler.waitResponse();
        
        if (!handler.success) {
            if(handler.getException()!=null)
            {
            	throw handler.getException();
            }
            else
            {
            	throw new Exception("no info");
            }
        }
		return (Resp${classInfo.key})handler.getResponse();
	}
</#list>


}
