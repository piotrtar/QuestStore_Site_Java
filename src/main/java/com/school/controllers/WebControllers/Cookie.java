package com.school.controllers.WebControllers;

import com.school.models.User;
import com.sun.net.httpserver.Headers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Cookie {

    public Integer getIdFromExistingCookies(Headers requestHeaders) {

        Integer userID;
        try {
            String key = "Cookie";
            List values = requestHeaders.get(key);
            String keyValue = (String) values.get(0);
            String id = keyValue.split("=")[1];
            userID = Integer.valueOf(id);
        } catch (Exception e) {
            return null;
        }

        return userID;
    }

    public String setupCookies(User user) {

        OffsetDateTime oneHourFromNow = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofSeconds(500));
        String cookieExpire = "expires=" + DateTimeFormatter.RFC_1123_DATE_TIME.format(oneHourFromNow);
        String cookie = String.format("id=%s; %s;", user.getId(), cookieExpire);

        return cookie;
    }

    public String removeCookies(User user) {

        OffsetDateTime oneHourFromNow = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofSeconds(0));
        String cookieExpire = "expires=" + DateTimeFormatter.RFC_1123_DATE_TIME.format(oneHourFromNow);
        String cookie = String.format("id=%s; %s;", user.getId(), cookieExpire);

        return cookie;
    }

    public static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }


}
