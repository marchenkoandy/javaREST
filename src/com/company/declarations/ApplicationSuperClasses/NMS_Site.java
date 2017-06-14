package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.UserManagementSvc.Organization;
import com.company.declarations.nms_classes.UserManagementSvc.Site;
import com.company.utilities.REST_METHOD;
import cucumber.api.java.ro.Si;

import java.util.List;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Site extends NMS_Base{
    public String getUID (String organizationUID, String siteName) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Sites?organizationUID=" + organizationUID + "&";
        return getUID_Base(Site.class,uri,siteName);
    }
}
