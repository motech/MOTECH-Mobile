package org.motechproject.mobile.omp.manager.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PostData {


    private Map<String,String> data = new LinkedHashMap<String,String>();

    private String defaultEncoding = "UTF-8";

    public void add(String attribute, String value, String urlEncoding) throws UnsupportedEncodingException {
        data.put(attribute, URLEncoder.encode(value,urlEncoding));
    }

    public void add(String attribute, String value) throws UnsupportedEncodingException {
        add(attribute,value,defaultEncoding);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(String key : data.keySet()){
            builder.append("&").append(key).append("=").append(data.get(key));
        }
        return builder.toString();
    }

    public String without(String... values){
        List<String> ignoreList = Arrays.asList(values);
        StringBuilder builder = new StringBuilder();
        for(String key : data.keySet()){
            if(! ignoreList.contains(key)){
                 builder.append("&").append(key).append("=").append(data.get(key));
            }
        }
        return builder.toString();
    }
}
