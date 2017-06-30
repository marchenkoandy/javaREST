package com.company.utilities;

import java.io.*;
import java.util.*;

/**
 * Created by AMarchenko on 2017-06-13.
 */
public class TokenCache {
    private void                    beShurePropertyFileExists(File f){
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
    private volatile Map<String,String> cache = new HashMap<>();
    private static final String     path = "TokenCache.properties";
    private String                  key(String nmsURL, String nmsAdminLogin){
        String sOut = "\"%s\"";
        sOut = String.format(sOut,nmsURL + ":" + nmsAdminLogin);

        return sOut;
    }
    private String                  value(Long sessionTokenExpirationTime, String token){
        return sessionTokenExpirationTime.toString() + ":" + token;
    }

    public String                   getToken(String nmsURL, String nmsAdminLogin){
        String token;
        String val = cache.get(key(nmsURL, nmsAdminLogin));
        if (val != null) {
            String [] ar = val.split(":",2);
            Long dateNow = new Date().getTime();
            token = (Long.parseLong(ar[0])>dateNow) ? ar[1] : null;
        }
        else {
            token = null;
        }
        return token;
    }
    public synchronized void        addToken (String nmsURL, String nmsAdminLogin, Long sessionTokenExpirationTime, String token){
        if (getToken(nmsURL,nmsAdminLogin) == null){
            Map<String,String> newCache = new HashMap(cache);
            newCache.put(key(nmsURL, nmsAdminLogin),value(sessionTokenExpirationTime,token));
            cache = newCache;
            File file = new File(path);
            FileOutputStream fr = null;
            try {
                fr = new FileOutputStream(file);
                Properties property = new Properties();
                for(Map.Entry<String, String> item : cache.entrySet()){
                    property.setProperty(item.getKey(),item.getValue());
                }
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
    public synchronized void        init(){
        File file               = new File(path);
        FileInputStream fis     = null;
        Properties property     = new Properties();
        try {
            beShurePropertyFileExists(file);
            fis = new FileInputStream(file);
            property.load(fis);
            Enumeration enuKeys = property.keys();
            while (enuKeys.hasMoreElements()){
                String key      = (String) enuKeys.nextElement();
                String value    = property.getProperty(key);
                cache.put(key,value);
            }
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


//    private static final TokenCache tokenCache = new TokenCache();
//    public static TokenCache        getTokenCache(){
//        return tokenCache;
//    }
}
