/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.serivce;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class FormRequestTest {

    public FormRequestTest() {
    }
    
   
    @Test
    public void testRegularExp() {
        String message = "date=2010-12-12 04:12:45 AM";
        String result = "some match";
        String phoneData = "sender=233274310171";

        Pattern p = Pattern.compile("[a-zA-Z0-9_\\-]+\\s*=\\s*([a-zA-Z0-9_\\-\\s/.,'])+");
        Matcher m = p.matcher(message);
        if (m.find()) {
            result = m.group();
            System.out.println("result: " + result);
        }else{
            System.out.println("No matches");
        }

        //assertEquals(phoneData, result);
    }

}