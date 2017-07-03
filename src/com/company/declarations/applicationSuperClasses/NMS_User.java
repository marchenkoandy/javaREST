package com.company.declarations.applicationSuperClasses;

import com.company.declarations.nms_classes.userManagementSvc.User;
import com.company.utilities.EnvironmentName;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_User extends NMS_Base{
    private final String keyFieldName =         "Login";
    private final String valueFieldName =       "UID";

    public String getUID (String groupUID, String userLogin) throws IllegalAccessException {
        String uri = EnvironmentName.platformUserManagementSvc + EnvironmentName.users + "?groupUID=" + groupUID + "&login=" + userLogin + "&";
        return getUID_Base(User.class, uri, userLogin,keyFieldName,valueFieldName);
    }
}
