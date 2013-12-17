public class ${className} extends AbstractOp{
<#list classInfoList as classInfo>
    <#assign uncapKey=classInfo.key?uncap_first>
	public void ${uncapKey}(${parentClassName}.Req${classInfo.key} request, MetaHandler handler) {
		 createAndSend(request, handler);
	}
</#list>

<#list classInfoList as classInfo>
    <#assign uncapKey=classInfo.key?uncap_first>
    public void ${uncapKey}(${parentClassName}.Req${classInfo.key} request, MetaHandler handler) {
        <#list classInfo.methodInfos as methodInfo>
        Data.UserId key = request.${methodInfo.name?replace("set","get")}();
        </#list>
        MetaServerClientImpl impl = getClientImpl(key);
        if (impl == null) {
            handler.handleException(new RuntimeException("Unknown host."));
            return;
        }
        impl.${uncapKey}(request, handler);
    }
</#list>

<#list classInfoList as classInfo>

//for msgType
public static final short ${classInfo.key?upper_case}REQ = 0x3020;
public static final short ${classInfo.key?upper_case}RESP = 0x3021;

		new ${classInfo.key}Task().regist();
</#list>

}