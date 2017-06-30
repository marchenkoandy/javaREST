package com.company.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AMarchenko on 2017-06-13.
 */
public final class Config {
    private String siteName;
    private String groupName;
    private String userLogin;
    private String userPassword;
    private String userLicenseName;
    private String userType;

    private String nmsURL;
    private String nmsAdminLogin;
    private String nmsAdminPassword;
    private String action;

    public                          Config(String[] args){
        fillClassFieldsWithArgs(args);
    }
    private void                    fillClassFieldsWithArgs(String[] args){
        Map<String,String> argsHash = new HashMap<>();
        for(String item:args){
            String [] ar = item.split(":",2);
            argsHash.put(ar[0],ar[1]);
        }
        setNmsURL                   (argsHash.get("nmsURL"));
        setNmsAdminLogin            (argsHash.get("nmsAdminLogin"));
        setNmsAdminPassword         (argsHash.get("nmsAdminPassword"));
        setAction                   (argsHash.get("action"));
        setSiteName                 (argsHash.get("siteName"));
        setGroupName                (argsHash.get("groupName"));
        setUserLogin                (argsHash.get("userLogin"));
        setUserPassword             (argsHash.get("userPassword"));
        setUserLicenseName          (argsHash.get("userLicenseName"));
        setUserType                 (argsHash.get("userType"));
    }

    public String                   getNmsURL() {
        return nmsURL;
    }
    public void                     setNmsURL(String nmsURL) {
        this.nmsURL = nmsURL;
    }

    public String                   getNmsAdminLogin() {
        return nmsAdminLogin;
    }
    public void                     setNmsAdminLogin(String nmsAdminLogin) {
        this.nmsAdminLogin = nmsAdminLogin;
    }

    public String                   getNmsAdminPassword() {
        return nmsAdminPassword;
    }
    public void                     setNmsAdminPassword(String nmsAdminPassword) {
        this.nmsAdminPassword = nmsAdminPassword;
    }

    public String                   getAction() {
        return action;
    }
    public void                     setAction(String action) {
        this.action = action;
    }

    public String                   getSiteName() {
        return siteName;
    }
    public void                     setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String                   getGroupName() {
        return groupName;
    }
    public void                     setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String                   getUserLogin() {
        return userLogin;
    }
    public void                     setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String                   getUserPassword() {
        return userPassword;
    }
    public void                     setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String                   getUserLicenseName() {
        return userLicenseName;
    }
    public void                     setUserLicenseName(String userLicenseName) {
        this.userLicenseName = userLicenseName;
    }

    public String                   getUserType() {
        return userType;
    }
    public void                     setUserType(String userType) {
        this.userType = userType;
    }


    //    public void FillClassFieldsWithArgs(Object thisClass, String[] args) {
//        Field[] lFields = thisClass.getClass().getFields();
//        for (String str : args) {
//            String[] name_value = str.split(":", 2);
//            for (Field field : lFields) {
//                if (name_value[0].equals(field.getName().toString())) {
//                    Object v = ConvertUtils.convert(name_value[1], field.getType());
//                    try {
//                        field.set(thisClass, v);
//                    } catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//            }
//        }
//    }
}
