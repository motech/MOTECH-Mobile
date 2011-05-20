package org.motechproject.mobile.omp.manager.gupshup;

import java.util.ArrayList;
import java.util.List;

public class GatewayURL {

    private String gatewayUrl;

    private List<URLParameter> parameters = new ArrayList<URLParameter>();
    private static final String EMPTY = "";

    public GatewayURL(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public GatewayURL append(URLParameter urlParameter) {
        parameters.add(urlParameter);
        return this;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("?");
        for (URLParameter parameter : parameters) {
            builder.append("&").append(parameter);
        }
        String parameters = builder.toString().replaceFirst("&", EMPTY);
        return gatewayUrl + parameters;
    }
}
