package com.company.utilities;

import java.io.*;
import java.util.Properties;

/**
 * Created by AMarchenko on 2017-06-07.
 */
public class PropertyReader {
    private static final String path = "config.properties";

    public String nmsURL;
    public String nmsAdminLogin;
    public String nmsAdminPassword;
    public String SessionToken;
    public Long SessionTokenExpirationTime;


    private void beSurePropertyFileExists(File f){
//        System.out.println(f.getAbsoluteFile());
        if (!f.exists()){
            try {
                f.createNewFile();
            }catch (IOException e){
                throw new RuntimeException(e);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }
    public PropertyReader(){
        File file = new File(path);
        FileInputStream fis=null;
        Properties property = new Properties();
        try {
            beSurePropertyFileExists(file);
            fis = new FileInputStream(file);
            property.load(fis);
            this.nmsURL = property.getProperty("nmsURL");
            this.nmsAdminLogin = property.getProperty("nmsAdminLogin");
            this.nmsAdminPassword = property.getProperty("nmsAdminPassword");
            this.SessionToken = property.getProperty("SessionToken");
            String s = property.getProperty("SessionTokenExpirationTime");
            this.SessionTokenExpirationTime = (s == null ? null :Long.parseLong(s));
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
    }
    public void Close() {
        File file = new File(path);
        FileOutputStream fr = null;
        try {
            fr = new FileOutputStream(file);
            Properties property = new Properties();
            property.setProperty("nmsURL",this.nmsURL);
            property.setProperty("nmsAdminLogin",this.nmsAdminLogin);
            property.setProperty("nmsAdminPassword",this.nmsAdminPassword);
            property.setProperty("SessionToken",this.SessionToken);
            property.setProperty("SessionTokenExpirationTime",this.SessionTokenExpirationTime.toString());
            property.store(fr,"");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (fr !=null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
