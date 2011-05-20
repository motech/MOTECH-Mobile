package org.motechproject.mobile.omp.manager.gupshup;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class URLParameterTest {

    @Test
    public void shouldReturnRequiredStringRepresentation() {
        URLParameter urlParameter = new URLParameter("text", "hello");
        assertEquals("text=hello",urlParameter.toString());
        URLParameter urlParameter1 = new URLParameter("text", "hello world",true);
        assertEquals("text=hello+world",urlParameter1.toString());
    }
}
