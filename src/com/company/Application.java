package com.company;

import com.company.declarations.ApplicationSuperClasses.*;
import com.company.utilities.*;

/**
 * Created by AMarchenko on 2017-06-06.
 */

public class Application{
    public                                      Application                 (String args[]) {
        config = getConfig(args);
    }
    public static Config    config;
    public Config           getConfig                                       (String[] args){
        config = new Config(args);
        return config;
    }

    public String           orgainzationUID                                 () throws IllegalAccessException {
        return new NMS_Organization().getUID(null);

    }
    public String           roleUID                                         () throws IllegalAccessException {
        String roleName = "Dragon Medical Author";//AM 06/14/2017 at this moment role name is only this;
        return new NMS_Role().getUID(roleName);
    }
    public String           siteUID                                         () throws IllegalAccessException {
        return new NMS_Site().getUID(orgainzationUID(),mustExist(config.getSiteName()));
    }
    public String           groupUID                                        () throws IllegalAccessException {
        return new NMS_Group().getUID(mustExist(config.getGroupName()));
    }
    public String           userUID                                         () throws IllegalAccessException {
        return new NMS_User().getUID(groupUID(), mustExist(config.getUserLogin()));
    }


    public String partnerUID(){
        return "";
    }
    public String licenseTypeUID(){
        return "";
    }
    public String speechNodeUID(){
        return "";
    }


    public final String                         getNMS_ServerVersion        (){
        return new NMS_Server().getVersion();
    }

    public String                               mustExist                   (String st) throws IllegalAccessException {
        if (st == null || st.equals("")){
            new Help().print(config.getAction());
            throw new RuntimeException();
        }
        return st;
    }
}

