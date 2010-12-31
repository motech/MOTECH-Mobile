package org.motechproject.mobile.omp.manager.utils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class PostDataTest {

    @Test
    public void shouldReturnStringRepresentation() throws UnsupportedEncodingException {
        PostData data = postData();
        assertEquals("&user=john&password=password&sender=motech+%26+motech&text=hello+world",data.toString());
    }

    @Test
    public void shouldNotIncludeRestrictedValuesInStringRepresentation() throws UnsupportedEncodingException {
        PostData data = postData();
        assertEquals("&sender=motech+%26+motech&text=hello+world",data.without("password","user"));
    }

    private PostData postData() throws UnsupportedEncodingException {
        PostData data = new PostData();
        data.add("user","john");
        data.add("password","password");
        data.add("sender","motech & motech");
        data.add("text","hello world");
        return data;
    }

}
