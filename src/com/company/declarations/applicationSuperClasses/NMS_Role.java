package com.company.declarations.applicationSuperClasses;

import com.company.declarations.nms_classes.userManagementSvc.Role;
import com.company.utilities.EnvironmentName;

/**
 * Created by AMarchenko on 2017-06-14.
 */
public class NMS_Role extends NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";
    public String getUID (String roleName ) throws IllegalAccessException {
        String uri = EnvironmentName.platformUserManagementSvc + EnvironmentName.roles + "?";
        return getUID_Base(Role.class,uri,roleName,keyFieldName,valueFieldName);
    }
}
