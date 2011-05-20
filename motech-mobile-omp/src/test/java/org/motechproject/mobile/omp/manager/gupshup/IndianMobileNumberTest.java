package org.motechproject.mobile.omp.manager.gupshup;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IndianMobileNumberTest {

    @Test
    public void shouldAppendIndiaCode() {
        assertEquals("919886771156", new IndianMobileNumber("9886771156").toString());
        assertEquals("919886771156", new IndianMobileNumber("919886771156").toString());
        assertEquals("919886771156", new IndianMobileNumber("09886771156").toString());
    }
}
