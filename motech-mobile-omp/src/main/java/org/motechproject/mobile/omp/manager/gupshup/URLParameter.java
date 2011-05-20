package org.motechproject.mobile.omp.manager.gupshup;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLParameter {
    private String name;
    private String value;

    public URLParameter(String name, String value){
        this(name,value,false);
    }

    public URLParameter(String name, String value, boolean urlEncode){
        this.name = name;
        try {
            this.value = urlEncode ? URLEncoder.encode(value, "UTF-8") : value;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }
}
