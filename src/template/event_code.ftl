<#list types as type>
 		Events.${type}Event EV = new Events.${type}Event();
		EV.name = "/" + request.getName();
		interSaveEvent(request.getUserId(), request.getUserId(),
				request.getUserId(), ts, rootFolder, folderInode,
				request.getName(), EV);
				
				
				
</#list>