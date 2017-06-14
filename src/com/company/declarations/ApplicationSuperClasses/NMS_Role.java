package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.UserManagementSvc.Role;

/**
 * Created by AMarchenko on 2017-06-14.
 */
public class NMS_Role extends NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";
    public String getUID (String roleName ) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Roles?";
        return getUID_Base(Role.class,uri,roleName,keyFieldName,valueFieldName);
    }
}
