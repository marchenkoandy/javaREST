package com.company.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class Help {
    private static final String         CONSTANT_PARAMETER  = " nmsURL:https://nms-qa2.nuancehce.com nmsAdminLogin:ADMINLOGIN nmsAdminPassword:ADMINPWD";
    private static Map<String,String>   myMap;
    private static Map<String,String>   createMap           (){
        Map<String,String>myMap = new HashMap<>();
        myMap.put(NMS_Action.serverNMSVersion,      "%s");
        myMap.put(NMS_Action.userCreate,            "%s Hi");
        myMap.put(NMS_Action.userDelete,            "%s");
        myMap.put(NMS_Action.userExist,             "%s");
        myMap.put(NMS_Action.userGrantLicense,      "%s");

        myMap.put(NMS_Action.groupCreate,           "%s");
        myMap.put(NMS_Action.groupDelete,           "%s");
        myMap.put(NMS_Action.groupExist,            "%s");

        myMap.put(NMS_Action.siteCreate,            "%s");
        myMap.put(NMS_Action.siteDelete,            "%s");
        myMap.put(NMS_Action.siteExist,             "%s");
        return myMap;
    }
    public                              Help                () throws IllegalAccessException {
        new NMS_Action();
        myMap = createMap();
    }
    private final void                  head                (){
        System.out.println("***  Wrong arguments supplied into application    ***");
        System.out.println("Use example:");
    }
    private final void                  body                (String act){
        String sOut =">>NMSClient.exe " + NMS_Action.action + ":" + act +  myMap.get(act);
        sOut = String.format(sOut,CONSTANT_PARAMETER);
        System.out.println(sOut);
    }
    public void                         print               (String act){
        head();
        body(act);
    }
    public void                         print               (){
        head();
        for(Map.Entry<String, String> item : myMap.entrySet()){
                body(item.getKey());
        }
    }
}
