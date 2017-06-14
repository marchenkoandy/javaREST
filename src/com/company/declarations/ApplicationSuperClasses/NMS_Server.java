package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.ConfigurationSvc.PlatformStatus;
import com.company.utilities.REST_Call;
import com.company.utilities.REST_METHOD;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Server{

    public String getVersion(){
        String uri = "/NMS/Platform/ConfigurationSvc/v1/Status";
        PlatformStatus server = new REST_Call().getResponse(PlatformStatus.class, uri, REST_METHOD.GET);
//        PlatformStatus server = new REST_Call().getResponse(uri, REST_METHOD.GET);
        return server.NMSVersion;
    }

}
