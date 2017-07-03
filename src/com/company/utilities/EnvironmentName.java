package com.company.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by AMarchenko on 7/3/2017.
 */
public class EnvironmentName {
    private static final String path = "environmentName.properties";

    public EnvironmentName(){
        File file = new File(path);
        FileInputStream fis=null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(file);
            property.load(fis);
            this.roleName               = property.getProperty("roleName");
            this.nms                    = property.getProperty("nms");
            this.platform               = property.getProperty("platform");
            this.authenticationSvc      = property.getProperty("authenticationSvc");
            this.userManagementSvc      = property.getProperty("userManagementSvc");
            this.configurationSvc       = property.getProperty("configurationSvc");
            this.v1                     = property.getProperty("v1");

            this.organizations          = property.getProperty("organizations");
            this.sites                  = property.getProperty("sites");
            this.groups                 = property.getProperty("groups");
            this.users                  = property.getProperty("users");
            this.status                 = property.getProperty("status");
            this.roles                  = property.getProperty("roles");
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
//        Section for enlargement of fields
        platformAuthenticationSvc       = nms + platform + authenticationSvc + v1;
        platformUserManagementSvc       = nms + platform + userManagementSvc + v1;
        platformConfigurationSvc        = nms + platform + configurationSvc + v1;


    }

    public static String roleName;
    private static String nms;
    private static String platform;
    private static String authenticationSvc;
    private static String userManagementSvc;
    private static String configurationSvc;
    private static String v1;



    public static String platformAuthenticationSvc;
    public static String platformUserManagementSvc;
    public static String platformConfigurationSvc;

    public static String organizations;
    public static String sites;
    public static String groups;
    public static String users;
    public static String status;
    public static String roles;



}
