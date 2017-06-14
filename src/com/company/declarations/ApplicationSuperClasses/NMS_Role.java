package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.UserManagementSvc.Organization;
import com.company.declarations.nms_classes.UserManagementSvc.Role;
import com.company.utilities.REST_METHOD;

import java.util.List;

/**
 * Created by AMarchenko on 2017-06-14.
 */
public class NMS_Role extends NMS_Base{
    public String getUID (String roleName ) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Roles?";
        return getUID_Base(Role.class,uri,roleName);
    }
}
