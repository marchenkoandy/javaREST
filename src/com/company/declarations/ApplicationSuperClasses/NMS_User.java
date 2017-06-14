package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.UserManagementSvc.User;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_User extends NMS_Base{
    private final String keyFieldName =         "Login";
    private final String valueFieldName =       "UID";

    public String getUID (String groupUID, String userLogin) throws IllegalAccessException {
        String uri = "/NMS/Platform/UserManagementSvc/v1/Users?groupUID=" + groupUID + "&login=" + userLogin + "&";
        return getUID_Base(User.class, uri, userLogin,keyFieldName,valueFieldName);
    }
}
