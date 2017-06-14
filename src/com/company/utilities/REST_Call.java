package com.company.utilities;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.company.Application;
import com.company.declarations.nms_classes.AuthenticationSvc.SessionInfo;
import com.company.declarations.nms_classes.UserManagementSvc.Organization;
import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sun.javafx.collections.MappingChange;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * Created by AMarchenko on 2017-06-06.
 */

public class REST_Call{
    private static final Gson gson = new GsonBuilder()
        .registerTypeHierarchyAdapter(Date.class, new DateTypeAdapter())
        .create();
    private final PropertyReader                propertyReader          = new PropertyReader();
    private final Config                        config                  = Application.config;
    private boolean                             bTokenExpired           (){
        Long dateNow = new Date().getTime();
        boolean b1 = propertyReader.nmsURL == null                      || !config.getNmsURL().equals(propertyReader.nmsURL);
        boolean b2 = propertyReader.nmsAdminLogin == null               || !config.getNmsAdminLogin().equals(propertyReader.nmsAdminLogin);
        boolean b3 = propertyReader.nmsAdminPassword == null            || !config.getNmsAdminPassword().equals(propertyReader.nmsAdminPassword);
        boolean b4 = propertyReader.SessionTokenExpirationTime == null  || dateNow>propertyReader.SessionTokenExpirationTime;
        return  (b1 || b2 || b3 || b4);
    }
    public String                               token                   () {
        String token="";
        if (bTokenExpired()) {
            String url = "/NMS/Platform/AuthenticationSvc/v1/ValidateCredentials?productGuid=7CEADE5E-E98D-4AE7-A839-C3C4AA32162C";
            SessionInfo respond = getToken(SessionInfo.class, url);
            token = "session//" + respond.SessionToken + ":";
            token = Base64.encode(token.getBytes());
            propertyReader.nmsURL = config.getNmsURL();
            propertyReader.nmsAdminLogin = config.getNmsAdminLogin();
            propertyReader.nmsAdminPassword = config.getNmsAdminPassword();
            propertyReader.SessionTokenExpirationTime = respond.SessionTokenExpirationTime.getTime();
            propertyReader.SessionToken = token;
            propertyReader.Close();
        } else {
            token = propertyReader.SessionToken;
        }
        return token;
    }
    public static class DateTypeAdapter         implements JsonDeserializer<Date> {
        private static final String openNode = "/Date(";
        private static final String closeNode = ")/";
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {return null;}

            String jsonStr = json.getAsString();
            if (jsonStr == null || jsonStr.length() == 0){return null;}

            int beginIndex = jsonStr.indexOf(openNode);
            int endIndex = jsonStr.indexOf(closeNode);
            if (beginIndex == -1 || endIndex == -1 || endIndex <= beginIndex){
                throw new RuntimeException("Pattern /Date(0123456789012)/ was not detected.");
            }
            else {
                beginIndex += openNode.length();
                long timeInMilliseconds = Long.parseLong(jsonStr.substring(beginIndex, endIndex));
                return new Date(timeInMilliseconds);
            }
        }
    }
    private         String                      getResponseString       (boolean bToken, String requestURI, REST_METHOD method, String jsonBody){
        String requestURL = config.getNmsURL() + requestURI;
        String respond = "";
        HttpURLConnection connection=null;
        BufferedReader bufferedReader=null;
        int iTimeOut = 30000;
        try {
            URL url = new URL(requestURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toString());
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("ContentType","application/json");
            connection.setConnectTimeout(iTimeOut);
            connection.setReadTimeout(iTimeOut);
            String authorization = bToken ? tokenAuthorization() : token();
            connection.setRequestProperty("Authorization","Basic " + authorization);
            if (jsonBody != null && !jsonBody.equals("")){
                byte[] body = jsonBody.getBytes();
                BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
                bos.write(body);
            }
            int iRespondCode = connection.getResponseCode();
            switch (iRespondCode){
                case HttpURLConnection.HTTP_OK:
                    bufferedReader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        respond += line;
                    }
                    break;
                default: throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode()+" HTTP error message : " + connection.getResponseMessage());
            }
        }
        catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){connection.disconnect();}
        }
        return respond;
    }
    private final   String                      tokenAuthorization      (){
        String user_pwd = "nms//" + config.getNmsAdminLogin() + ":" + config.getNmsAdminPassword();
        return Base64.encode(user_pwd.getBytes());
    }
    public  String                              getStringResponse       (String requestURI,     REST_METHOD method,     String jsonBody){
        return getResponseString(false, requestURI, method, jsonBody);
    }

    public  <T>     T                           getToken                (Class<T>resultClass,   String requestURI) {
        String respond = getResponseString(true, requestURI, REST_METHOD.GET, null);
        return gson.fromJson(respond, resultClass);
    }

    public  <T>     T                           getDeserializedClass             (Class<T>resultClass,   String requestURI,      REST_METHOD method,     String jsonBody){
        String respond = getResponseString(false, requestURI, method, jsonBody);
        return gson.fromJson(respond,resultClass);
    }

    public  <T>     List<T>                     getDeserializedListOfClass       (Class<T>resultClass,   String requestURI,      REST_METHOD method,     String jsonBody){
        String respond = getResponseString(false, requestURI, method, null);//
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(respond).getAsJsonArray();
        List<T> objs = new ArrayList<T>();
        for (JsonElement jsonElement:jsonArray) {
            objs.add(gson.fromJson( jsonElement.toString(), resultClass));
        }
        return objs;
    }
}
