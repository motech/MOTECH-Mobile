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
        String message = "type=death-jf\nchpsid=12342\nfacilityid=9\nsender=233274310171";
        String result = "some match";
        String phoneData = "sender=233274310171";

        Pattern p = Pattern.compile("^(([tT][yY][pP][eE])|([qQ][uU][eE][rR][yY]))\\s*=\\s*[a-zA-Z0-9_-]+");
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