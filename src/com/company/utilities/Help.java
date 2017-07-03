package com.company.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by AMarchenko on 2017-06-09.
 */
public class Help {
    private static final String path = "helper.properties";
    private static String commonPart;
    private static Map<String,String>   myMap;
    private static Map<String,String>   createMap           (){
        Map<String,String>myMap = new HashMap<>();

        File file = new File(path);
        FileInputStream fis=null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(file);
            property.load(fis);
            commonPart                                 =property.getProperty("commonPart");
            myMap.put(NMS_Action.serverNMSVersion,      property.getProperty(NMS_Action.serverNMSVersion));
            myMap.put(NMS_Action.userCreate,            property.getProperty(NMS_Action.userCreate));
            myMap.put(NMS_Action.userDelete,            property.getProperty(NMS_Action.userDelete));
            myMap.put(NMS_Action.userExist,             property.getProperty(NMS_Action.userExist));
            myMap.put(NMS_Action.userGrantLicense,      property.getProperty(NMS_Action.userGrantLicense));

            myMap.put(NMS_Action.groupCreate,           property.getProperty(NMS_Action.groupCreate));
            myMap.put(NMS_Action.groupDelete,           property.getProperty(NMS_Action.groupDelete));
            myMap.put(NMS_Action.groupExist,            property.getProperty(NMS_Action.groupExist));

            myMap.put(NMS_Action.siteCreate,            property.getProperty(NMS_Action.siteCreate));
            myMap.put(NMS_Action.siteDelete,            property.getProperty(NMS_Action.siteDelete));
            myMap.put(NMS_Action.siteExist,             property.getProperty(NMS_Action.siteExist));

        }catch (IOException e){
            throw new RuntimeException(e);
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
        String sOut =">>NMSClient.exe " + NMS_Action.action + ":" + act +  " " + myMap.get(act);
        sOut = String.format(sOut,commonPart);
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
