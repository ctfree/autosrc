package com.hanborq.edrop.msc.test.op;

import org.apache.log4j.Logger;

import com.hanborq.edrop.msc.test.NativeServer;
import com.google.protobuf.GeneratedMessage;
import com.hanborq.edrop.msg.Data.GroupId;
import com.hanborq.edrop.msg.Data.UserId;
<#list classInfoList as classInfo>
import com.hanborq.edrop.msg.${parentClassName}.${classInfo.className};
import com.hanborq.edrop.msg.${parentClassName}.Resp${classInfo.key};
</#list>

public class ${className} extends AbstractOp{
	private static Logger LOG = Logger.getLogger(${className}.class);
	
	public ${className}(UserId userId) throws Exception{
		super(userId);	
		nativeServer = NativeServer.getInstance();
	}
	
<#list classInfoList as classInfo>
	public Resp${classInfo.key} ${classInfo.key}Task(${classInfo.args}) throws Exception{
		${classInfo.className}.Builder msgBody = ${classInfo.className}.newBuilder();
        <#list classInfo.methodInfos as methodInfo>
        msgBody.${methodInfo.name}(${methodInfo.param});
        </#list>
		return (Resp${classInfo.key})nativeServer.doTask(msgBody.build());
	}
</#list>

	public static void main(String[] args) throws Exception {
		${className} op = new ${className}();
<#list classInfoList as classInfo>
        //Resp${classInfo.key} resp${classInfo.key}= op.${classInfo.key}Task(${classInfo.onlyArgs});
</#list>
	}
}
