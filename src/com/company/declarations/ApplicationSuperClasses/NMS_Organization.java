package com.company.declarations.ApplicationSuperClasses;

import com.company.declarations.nms_classes.ConfigurationSvc.PlatformStatus;
import com.company.declarations.nms_classes.UserManagementSvc.ArrayOfOrganization;
import com.company.declarations.nms_classes.UserManagementSvc.Organization;
import com.company.utilities.REST_Call;
import com.company.utilities.REST_METHOD;
import com.google.gson.internal.LinkedTreeMap;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class NMS_Organization {
    public Long getUID (String organizationName){
        Long lOut= Long.valueOf("1");
        String uri = "/NMS/Platform/UserManagementSvc/v1/Organizations?skip=0&take=100";
        List<Organization> arr = new REST_Call().getResponse(uri, REST_METHOD.GET,true);
        Object ar = arr.get(0);
//        lOut = Long. ((LinkedTreeMap) ar).get("UID");
//        lOut = Double.doubleToRawLongBits(dOut);
//        iOut = Integer.parseInt(Double.toString(dOut));
//        System.out.print(lOut);
        return lOut;
    }
}
