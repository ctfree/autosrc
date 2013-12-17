package com.hanborq.edrop.mas.task.impl;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hanborq.edrop.auth.Auth.AuthUser;
import com.hanborq.edrop.mas.TAG;
import com.hanborq.edrop.mas.server.Context;
import com.hanborq.edrop.mas.server.MasMetrics;
import com.hanborq.edrop.mas.task.CreateTaskException;
import com.hanborq.edrop.mas.task.Task;
import com.hanborq.edrop.msg.Data;
import com.hanborq.edrop.msg.FileMsg;
import com.hanborq.edrop.util.ErrorCodes;

public class ${key}Task extends Task<FileMsg.Req${key}> {

    private static Logger LOG = LoggerFactory.getLogger(${key}Task.class);

    public ${key}Task(String host, AuthUser user, HttpServletRequest request,
            HttpServletResponse response) {
        super(host, user, request, response);
    }

    @Override
    public void sendRequest() {
        Context.getClient().${key?uncap_first}(req, this);
    }

    @Override
    public void buildRequest() throws CreateTaskException {
        RootFolderAndINode rootFolderAndINode = parseFormRequest(request);
        String name = request.getParameter(TAG.TAG_NAME);
        String shareKey = request.getParameter(TAG.TAG_SHAREKEY);

        FileMsg.Req${key}.Builder builder = FileMsg.Req${key}
                .newBuilder()
                .setHostId(host)
                .setUserId(
                        Data.UserId.newBuilder()
                                .setEnterpriseId(user.enterpriseId)
                                .setUserId(user.userid).build())
                .setRootFolder(rootFolderAndINode.rootFolder)
                .setFolderInode(rootFolderAndINode.iNode)
                .setName(name).build();

        LOG.info(String
                .format("Create Req${key} : host(%s), user(%s), rootfolder(%d), inode(%d), name(%s)",
                        host, user, rootFolderAndINode.rootFolder,
                        rootFolderAndINode.iNode, name));
    }

    @Override
    public void addDelay() {
        MasMetrics.getInstance().add${key}Delay(delay);
    }
}
