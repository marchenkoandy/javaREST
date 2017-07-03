package com.company.declarations.applicationSuperClasses;

import com.company.declarations.nms_classes.userManagementSvc.Group;
import com.company.utilities.EnvironmentName;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Group extends  NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";

    public String getUID (String groupName) throws IllegalAccessException {
        String uri = EnvironmentName.platformUserManagementSvc + EnvironmentName.groups + "?name=" + groupName + "&";
        return getUID_Base(Group.class, uri, groupName,keyFieldName,valueFieldName);
    }
}
