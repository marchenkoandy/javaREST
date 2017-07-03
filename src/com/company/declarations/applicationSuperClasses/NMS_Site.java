package com.company.declarations.applicationSuperClasses;

import com.company.declarations.nms_classes.userManagementSvc.Site;
import com.company.utilities.EnvironmentName;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Site extends NMS_Base{
    private final String keyFieldName =         "Name";
    private final String valueFieldName =       "UID";

    public String getUID (String organizationUID, String siteName) throws IllegalAccessException {
        String uri = EnvironmentName.platformUserManagementSvc +  EnvironmentName.sites + "?organizationUID=" + organizationUID + "&";
        return getUID_Base(Site.class,uri,siteName,keyFieldName,valueFieldName);
    }
}
