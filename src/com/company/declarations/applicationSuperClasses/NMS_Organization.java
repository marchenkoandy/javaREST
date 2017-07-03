package com.company.declarations.applicationSuperClasses;

import com.company.declarations.nms_classes.userManagementSvc.Organization;
import com.company.utilities.EnvironmentName;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Organization extends NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";

    public String getUID (String organizationName ) throws IllegalAccessException {
        String uri = EnvironmentName.platformUserManagementSvc + EnvironmentName.organizations + "?";
        return getUID_Base(Organization.class,uri,organizationName,keyFieldName,valueFieldName);
    }
}
