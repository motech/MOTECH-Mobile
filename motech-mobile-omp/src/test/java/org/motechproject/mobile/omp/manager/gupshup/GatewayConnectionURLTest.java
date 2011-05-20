package org.motechproject.mobile.omp.manager.gupshup;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GatewayConnectionURLTest {

    @Test
    public void shouldTest() {
        GatewayURL url = new GatewayURL("http://enterprise.smsgupshup.com/GatewayAPI/rest");
        url.append(new URLParameter("method","sendMessage"));
        url.append(new URLParameter("msg","hi world",true));
        url.append(new URLParameter("msg_type","TEXT"));
        url.append(new URLParameter("userid","2000045678"));
        url.append(new URLParameter("password","password"));
        url.append(new URLParameter("auth_scheme","plain"));
        String expectedURL =  "http://enterprise.smsgupshup.com/GatewayAPI/rest?method=sendMessage&msg=hi+world&msg_type=TEXT&userid=2000045678&password=password&auth_scheme=plain";
        assertEquals(expectedURL,url.toString());
    }
}
