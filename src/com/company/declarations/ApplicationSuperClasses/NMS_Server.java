package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.ConfigurationSvc.PlatformStatus;
import com.company.utilities.REST_METHOD;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Server extends  NMS_Base{

    public String getVersion(){
        String uri = "/NMS/Platform/ConfigurationSvc/v1/Status";
        PlatformStatus server = rest_call.getDeserializedClass(PlatformStatus.class, uri, REST_METHOD.GET,null);
        return server.NMSVersion;
    }
}
