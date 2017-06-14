package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.UserManagementSvc.Organization;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Organization extends NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";

    public String getUID (String organizationName ) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Organizations?";
        return getUID_Base(Organization.class,uri,organizationName,keyFieldName,valueFieldName);
    }
}
