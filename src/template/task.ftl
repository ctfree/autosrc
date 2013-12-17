package com.hanborq.edrop.msc.task.cospace;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.GeneratedMessage;
import com.hanborq.edrop.msc.task.MsgType;
import com.hanborq.edrop.msc.task.MsgType.Deserializer;
import com.hanborq.edrop.msc.task.SendResponseException;
import com.hanborq.edrop.msc.task.Task;
import com.hanborq.edrop.msc.task.TaskException;
import com.hanborq.edrop.msg.${parentClassName};
import com.hanborq.edrop.msg.${parentClassName}.Req${key};
import com.hanborq.edrop.msg.${parentClassName}.Resp${key};
import com.hanborq.util.netty.wrap.Comm;
import com.hanborq.util.netty.wrap.message.Message;


public class ${key}Task extends
		Task<${parentClassName}.Req${key}, ${parentClassName}.Resp${key}> {

	private static Logger LOG = LoggerFactory.getLogger(${key}Task.class);

	
	public ${key}Task() {
		super();
	}

	public ${key}Task(Message requestMessage, Req${key} request,
			SocketAddress remote, SocketAddress local, Comm comm) {
		super(requestMessage, request, remote, local, comm);
	}

	@Override
	public short getReqMsgType() {
		return MsgType.${key?upper_case}REQ;
	}

	@Override
	public short getRespMsgType() {
		return MsgType.${key?upper_case}RESP;
	}

	@Override
	public Class<? extends Req${key}> getReqMsgClass() {
		return Req${key}.class;
	}

	@Override
	public Class<? extends Resp${key}> getRespMsgClass() {
		return Resp${key}.class;
	}

	@Override
	public com.hanborq.edrop.msc.task.Task.TaskCreater getTaskCreater() {
		return new com.hanborq.edrop.msc.task.Task.TaskCreater() {
			@Override
			public Task create(Message requestMessage,
					GeneratedMessage request, SocketAddress remote,
					SocketAddress local, Comm comm) {
				return new ${key}Task(requestMessage,
						(Req${key}) request, remote, local, comm);
			}
		};
	}

	@Override
	public Deserializer getReqMsgDeserializer() {
		return new Deserializer() {
			@Override
			public GeneratedMessage deserializer(InputStream is)
					throws IOException {
				return Req${key}.parseFrom(is);
			}
		};
	}

	@Override
	public Deserializer getRespMsgDeserializer() {
		return new Deserializer() {
			@Override
			public GeneratedMessage deserializer(InputStream is)
					throws IOException {
				return Resp${key}.parseFrom(is);
			}
		};
	}

	@Override
	public void process() throws TaskException, SendResponseException {

		LOG.info("Process ${key} : ["
        <#list classInfo.methodInfos as methodInfo>
        <#if methodInfo_has_next>
        +request.${methodInfo.name?replace("set","get")}()+"]["
        <#else>
        +request.${methodInfo.name?replace("set","get")}()+"]"
        </#if>
        </#list>
        );
        
        
        
        
        
		LOG.info("Process ${key} OK : ["
        <#list classInfo.methodInfos as methodInfo>
        <#if methodInfo_has_next>
        +request.${methodInfo.name?replace("set","get")}()+"]["
        <#else>
        +request.${methodInfo.name?replace("set","get")}()+"]"
        </#if>
        </#list>
        );
	}

	@Override
	public void saveEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void publish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkRegions() throws TaskException {
//		checkRegions(request.getSuspendId());
	}
}

	
	
