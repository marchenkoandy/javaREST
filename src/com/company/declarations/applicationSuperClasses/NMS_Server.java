package com.company.declarations.applicationSuperClasses;

import com.company.declarations.nms_classes.configurationSvc.PlatformStatus;
import com.company.utilities.EnvironmentName;
import com.company.utilities.REST_METHOD;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Server extends  NMS_Base{

    public String getVersion(){
        String uri = EnvironmentName.platformConfigurationSvc + EnvironmentName.status;
        PlatformStatus server = rest_call.getDeserializedClass(PlatformStatus.class, uri, REST_METHOD.GET,null);
        return server.NMSVersion;
    }
}
