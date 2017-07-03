package com.company;

import com.company.utilities.EnvironmentName;
import com.company.utilities.Help;
import com.company.utilities.NMS_Action;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Help help = new Help();
        Application App = new Application(args);
        String action = App.config.getAction();
        switch (action) {
            case NMS_Action.serverNMSVersion:
                System.out.println(EnvironmentName.platformAuthenticationSvc);
                System.out.println(EnvironmentName.platformUserManagementSvc);
                System.out.println(EnvironmentName.platformConfigurationSvc);
                System.out.println(EnvironmentName.roleName);
                help.print();
//                System.out.println(App.getNMS_ServerVersion());
//                System.out.println(App.orgainzationUID());
//                System.out.println(App.roleUID());
//                System.out.println(App.siteUID());
//                System.out.println(App.groupUID());
//                System.out.println(App.userUID());
                break;
            case NMS_Action.siteCreate:
                break;
            case NMS_Action.siteDelete:
                break;
            case NMS_Action.siteExist:
                break;
            case NMS_Action.groupCreate:
                break;
            case NMS_Action.groupDelete:
                break;
            case NMS_Action.groupExist:
                break;
            case NMS_Action.userCreate:
                break;
            case NMS_Action.userDelete:
                break;
            case NMS_Action.userExist:
                break;
            case NMS_Action.userGrantLicense:
                break;
            default:
                help.print();
                break;
        }
//        System.out.println(App.token());
    }
}
