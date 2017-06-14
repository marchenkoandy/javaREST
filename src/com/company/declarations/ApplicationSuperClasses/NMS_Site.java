package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.UserManagementSvc.Site;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Site extends NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";

    public String getUID (String organizationUID, String siteName) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Sites?organizationUID=" + organizationUID + "&";
        return getUID_Base(Site.class,uri,siteName,keyFieldName,valueFieldName);
    }
}
