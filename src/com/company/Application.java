package com.company;



import com.company.declarations.ApplicationSuperClasses.NMS_Organization;
import com.company.declarations.ApplicationSuperClasses.NMS_Server;
import com.company.utilities.*;

/**
 * Created by AMarchenko on 2017-06-06.
 */

public class Application{

    public static Config config;
    public Config getConfig (String[] args){
        config = new Config(args);
        return config;
    }

    public Long orgainzationUID(){
        return new NMS_Organization().getUID("");
    }
    public String roleUID(){
        return "";
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
    public String siteUID(){
        return "";
    }
    public String groupUID(){
        return "";
    }
    public String userUID(){
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

    public                                      Application                 (String args[]) {
        config = getConfig(args);
    }
}

