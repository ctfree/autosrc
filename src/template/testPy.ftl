\

<#list classInfoList as classInfo>
    def ${classInfo.key?uncap_first}(self,${classInfo.onlyArgs}):
        response=self.metaServer.${classInfo.key?uncap_first}(${classInfo.onlyArgs})
 
</#list>
