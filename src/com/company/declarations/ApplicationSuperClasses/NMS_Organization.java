package com.company.declarations.ApplicationSuperClasses;


import com.company.declarations.nms_classes.UserManagementSvc.Organization;
import com.company.declarations.nms_classes.UserManagementSvc.Role;
import com.company.utilities.REST_METHOD;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Organization extends NMS_Base{
    public String getUID (String organizationName ) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Organizations?";
        return getUID_Base(Organization.class,uri,organizationName);
    }
}
