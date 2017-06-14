package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.UserManagementSvc.Group;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Group extends  NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";

    public String getUID (String groupName) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Groups?name=" + groupName + "&";
        return getUID_Base(Group.class, uri, groupName,keyFieldName,valueFieldName);
    }
}
