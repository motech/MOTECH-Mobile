/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author administrator
 */
public class XMLUtilTest {

    public XMLUtilTest() {
    }
    
    /**
     * Test of getElement method, of class XMLUtil.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");
        String xml = "<?xml version='1.0' encoding='UTF-8' ?><rootElement><sample>sometest</sample></rootElement>";
        InputStream in = new ByteArrayInputStream(xml.getBytes());
        SAXBuilder saxb = new SAXBuilder();
        Document doc = null;

        try {
            doc = saxb.build(in);
        } catch (JDOMException jDOMException) {
            System.out.println("JDOMException: " + jDOMException.getMessage());
        } catch (IOException iOException) {
            System.out.println("IOExceptionL " + iOException.getMessage());
        }

        String tagName = "sample";
        XMLUtil instance = new XMLUtil();
        String expResult = "sometest";
        Element result = instance.getElement(doc, tagName);
         assertEquals(expResult, result.getText());
    }

}