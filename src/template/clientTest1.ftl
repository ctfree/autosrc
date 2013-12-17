<#list classInfoList as classInfo>
import com.hanborq.edrop.msg.${parentClassName}.${classInfo.className};
import com.hanborq.edrop.msg.${parentClassName}.Resp${classInfo.key};
</#list>


<#list classInfoList as classInfo>
	public Resp${classInfo.key} ${classInfo.key?uncap_first}(${classInfo.args}) throws Exception{
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
